USE [ShuangBaoTestDB]
GO
/****** Object:  StoredProcedure [dbo].[sp_JudgeT1000101]    Script Date: 08/09/2019 10:53:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--a
 ---创建存储过程（钢筋抗拉强度、屈服强度、伸长率、冷弯试验）
 ALTER procedure [dbo].[sp_JudgeT1000101]
  @SerialNumber varchar(100),
  @StrengthGrade varchar(50),
  @Diameter float
 as
  begin	
	DECLARE @id INT,@Area decimal(15,2),@YSCount int, @TSCount int;
	DECLARE @YieldLoad1 decimal(15,1),@YieldLoad2 decimal(15,1),@MaxLoad1 decimal(15,1),@MaxLoad2 decimal(15,1)
	,@YSAvg decimal(15,1),@YASum decimal(15,1),@TSAvg decimal(15,1),@TASum decimal(15,1)
	
	-- 获取自动判断界限值；
    DECLARE @YieldStrth float,@TensileStrength float,@count int;
    select @YieldStrth=YieldStrth,@TensileStrength=TensileStrength 
      from JudgingBasisTest10 
     where IsT1000201 = 0 and StrengthGrade = @StrengthGrade
    and Diameter1 <= @Diameter  and Diameter2 >= @Diameter
    set @count = 0;
 
	-- 截面积 =(A/2)²*π 精确至0.01
	set @Area = ROUND(power((@Diameter/2),2)*PI(),2);
	set @YSCount = 0;
	set @TSCount = 0;
	set @YASum = 0;
	set @TASum = 0;
  
	DECLARE tempCursor CURSOR
	FOR
		( SELECT Id, YieldLoad1,YieldLoad2,MaxLoad1,MaxLoad2  
		  FROM Test1000101T01 
		 where SerialNumber= @SerialNumber
		)
		ORDER BY id;								                        --创建游标tempCursor，并定义游标所指向的集合   
	OPEN tempCursor;								                        --打开游标  
	FETCH NEXT FROM tempCursor INTO @id,@YieldLoad1,@YieldLoad2,@MaxLoad1,@MaxLoad2;			
																			--游标读取下一个数据  
	WHILE @@fetch_status = 0                                                --游标读取下一个数据的状态，0表示读取成功  
    BEGIN 
		DECLARE @YieldStrth1 decimal(15,1),@YieldStrth2 decimal(15,1),@TensileStrength1 decimal(15,1),@TensileStrength2 decimal(15,1);
		-- 计算 屈服强度 = 屈服荷载/截面积*1000 精确至1		
		if(@YieldLoad1 >0)
		begin
			set @YieldStrth1 = ROUND(@YieldLoad1/@Area*1000, 0);
			-- 判断是否合格
			if(@YieldStrth > 0 and @YieldStrth > @YieldStrth1)
			begin
				set @count = @count+1;
			end
			set @YASum = @YASum + @YieldStrth1;
			set @YSCount = @YSCount+1;
		end
		
		if(@YieldLoad2 >0)
		begin
			set @YieldStrth2 = ROUND(@YieldLoad2/@Area*1000, 0);
			-- 判断是否合格
			if(@YieldStrth > 0 and @YieldStrth > @YieldStrth2)
			begin
				set @count = @count+1;
			end
			set @YASum = @YASum + @YieldStrth2;
			set @YSCount = @YSCount+1;
		end
		-- 计算 抗拉强度 = 最大力荷载/截面积*1000精确至1		
		if(@MaxLoad1 >0)
		begin
			set @TensileStrength1 = ROUND(@MaxLoad1/@Area*1000, 0);
			-- 判断是否合格
			if(@TensileStrength > 0 and @TensileStrength > @TensileStrength1)
			begin
				set @count = @count+1;
			end
			set @TASum = @TASum + @TensileStrength1;
			set @TSCount = @TSCount+1;
		end
		
		if(@MaxLoad2 >0)
		begin
			set @TensileStrength2 = ROUND(@MaxLoad2/@Area*1000, 0);
			-- 判断是否合格
			if(@TensileStrength > 0 and @TensileStrength > @TensileStrength2)
			begin
				set @count = @count+1;
			end
			set @TASum = @TASum + @TensileStrength2;
			set @TSCount = @TSCount+1;
		end
		
		UPDATE Test1000101T01
		   SET YieldStrth1 = @YieldStrth1
			  ,YieldStrth2 = @YieldStrth2
			  ,TensileStrength1 = @TensileStrength1
			  ,TensileStrength2 = @TensileStrength2
		 WHERE Id = @Id
		
        FETCH NEXT FROM tempCursor INTO @id,@YieldLoad1,@YieldLoad2,@MaxLoad1,@MaxLoad2;   --继续用游标读取下一个数据  
    END  
	CLOSE tempCursor;								--关闭游标
	DEALLOCATE tempCursor;
    
    if(@YSCount >0)
	begin
		set @YSAvg = ROUND(@YASum/@YSCount,0);
	end
	
	if(@TSCount >0)
	begin
		set @TSAvg = ROUND(@TASum/@TSCount,0);
	end
	
	-- 更新主表平均值
	update Test1000101T0 
	   set AvgYieldStrth = @YSAvg
	      ,AvgTensileStrength = @TSAvg
	 where SerialNumber = @SerialNumber 
    
  --  if(@YieldStrth >0 and @YSAvg >0)
  --  begin
		--if(@YieldStrth > @YSAvg)
		--	set @count = @count+1;
  --  end
    
  --  if(@TensileStrength >0 and @TSAvg >0)
  --  begin
		--if(@TensileStrength>@TSAvg)
		--	set @count = @count+1;
  --  end
    
    DECLARE @IsQualifiedTest bit; 
	set @IsQualifiedTest = 1;
    if(@count > 0)
        set @IsQualifiedTest = 0;
	
	-- 更TestInfo表和Test0500101T0试验主表；
	update TestInfo 
	   set IsQualifiedTest = @IsQualifiedTest 
	 where SerialNumber = @SerialNumber
	 	
	 return 1
  end