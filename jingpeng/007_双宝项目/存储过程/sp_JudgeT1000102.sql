USE [ShuangBaoTestDB]
GO
/****** Object:  StoredProcedure [dbo].[sp_JudgeT1000102]    Script Date: 08/09/2019 11:01:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
 -- a
 ---创建存储过程（钢筋接头抗拉强度、冷弯试验）
 ALTER procedure [dbo].[sp_JudgeT1000102]
  @SerialNumber varchar(100),
  @StrengthGrade varchar(50),
  @Diameter float
 as
  begin	
	DECLARE @id INT,@Area decimal(15,2), @TSCount int;
	DECLARE @MaxLoad1 decimal(15,1),@MaxLoad2 decimal(15,1),@MaxLoad3 decimal(15,1)
	,@TSAvg decimal(15,1),@TASum decimal(15,1)
	
	-- 获取自动判断界限值；
    DECLARE @TensileStrength float,@count int;
    select @TensileStrength=TensileStrength 
      from JudgingBasisTest10 
     where IsT1000201 = 1 and StrengthGrade = @StrengthGrade
    and Diameter1 <= @Diameter  and Diameter2 >= @Diameter
    set @count = 0;
 
	-- 截面积 =(A/2)²*π 精确至0.01
	set @Area = ROUND(power((@Diameter/2),2)*PI(),2);
	set @TSCount = 0;
	set @TASum = 0;
  
	DECLARE tempCursor CURSOR
	FOR
		( SELECT Id,MaxLoad1,MaxLoad2,MaxLoad3  
		  FROM Test1000201T01 
		 where SerialNumber= @SerialNumber
		)
		ORDER BY id;								                        --创建游标tempCursor，并定义游标所指向的集合   
	OPEN tempCursor;								                        --打开游标  
	FETCH NEXT FROM tempCursor INTO @id,@MaxLoad1,@MaxLoad2,@MaxLoad3;			
																			--游标读取下一个数据  
	WHILE @@fetch_status = 0                                                --游标读取下一个数据的状态，0表示读取成功  
    BEGIN 
		DECLARE @TensileStrength1 decimal(15,1),@TensileStrength2 decimal(15,1),@TensileStrength3 decimal(15,1);
		-- 计算 抗拉强度 = 最大力荷载/截面积*1000精确至1		
		if(@MaxLoad1 >0)
		begin
			set @TensileStrength1 = ROUND(@MaxLoad1/@Area*1000, 0);
			if(@TensileStrength >0 and @TensileStrength >@TensileStrength1)
			begin
				set @count = @count+1;
			end
			set @TASum = @TASum + @TensileStrength1;
			set @TSCount = @TSCount+1;
		end
		
		if(@MaxLoad2 >0)
		begin
			set @TensileStrength2 = ROUND(@MaxLoad2/@Area*1000, 0);
			if(@TensileStrength >0 and @TensileStrength >@TensileStrength2)
			begin
				set @count = @count+1;
			end
			set @TASum = @TASum + @TensileStrength2;
			set @TSCount = @TSCount+1;
		end
		
		if(@MaxLoad3 >0)
		begin
			set @TensileStrength3 = ROUND(@MaxLoad3/@Area*1000, 0);
			if(@TensileStrength >0 and @TensileStrength >@TensileStrength3)
			begin
				set @count = @count+1;
			end
			set @TASum = @TASum + @TensileStrength3;
			set @TSCount = @TSCount+1;
		end
		
		UPDATE Test1000201T01
		   SET TensileStrength1 = @TensileStrength1
			  ,TensileStrength2 = @TensileStrength2
			  ,TensileStrength3 = @TensileStrength3
		 WHERE Id = @Id
		
        FETCH NEXT FROM tempCursor INTO @id,@MaxLoad1,@MaxLoad2,@MaxLoad3;	  --继续用游标读取下一个数据  
    END  
	CLOSE tempCursor;								--关闭游标
	DEALLOCATE tempCursor;
	
	if(@TSCount >0)
	begin
		set @TSAvg = ROUND(@TASum/@TSCount,0);
	end
	
	-- 更新主表平均值
	update Test1000201T0 
	   set AvgTensileStrength = @TSAvg
	 where SerialNumber = @SerialNumber

  --  if(@TensileStrength >0 and @TSAvg >0)
  --  begin
		--if(@TensileStrength>@TSAvg)
		--	set @count = @count+1;
  --  end

    DECLARE @IsQualifiedTest bit; 
	set @IsQualifiedTest = 1;
	-- 不合格
    if(@count >=2)
    begin
        set @IsQualifiedTest = 0;
    end
    -- 复检
    if(@count >=1)
    begin
        set @IsQualifiedTest = null;
    end
	
	-- 更TestInfo表；
	update TestInfo 
	   set IsQualifiedTest = @IsQualifiedTest 
	 where SerialNumber = @SerialNumber
	 	
	 return 1
  end