USE [ShuangBaoTestDB]
GO
/****** Object:  StoredProcedure [dbo].[sp_JudgeT04006]    Script Date: 08/13/2019 09:35:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
 -- a
 ---创建存储过程（水泥胶砂强度试验）
 ALTER procedure [dbo].[sp_JudgeT04006]
  @SerialNumber varchar(100),
  @CementType nvarchar(30),
  @CementStrengthGrade nvarchar(30),
  @Age int
 as
  begin	
	DECLARE @id INT;
	DECLARE @BendFailureLoad1 decimal(15,1),@BendFailureLoad2 decimal(15,1),@BendFailureLoad3 decimal(15,1)
	DECLARE @CompFailureLoad1 decimal(15,1),@CompFailureLoad2 decimal(15,1),@CompFailureLoad3 decimal(15,1)
		   ,@CompFailureLoad4 decimal(15,1),@CompFailureLoad5 decimal(15,1),@CompFailureLoad6  decimal(15,1) 
	
	-- 多组平均抗折，多组平均抗压；	   
	DECLARE @AvgRuptureStrength decimal(15,1),@AvgCompFailureLoad decimal(15,1),@AvgRSCount int ,@AvgCFCount int; 
    set @AvgRuptureStrength = 0; 
    set @AvgCompFailureLoad = 0;
    set @AvgRSCount = 0;
    set @AvgCFCount = 0;
    
    -- 结果判定是否无效 (0:不合格 1:合格 2:无效)
	DECLARE @Result varchar(2);
	set @Result = 1;
  
	DECLARE tempCursor CURSOR
	FOR
		( SELECT Id,BendFailureLoad1,BendFailureLoad2,BendFailureLoad3
				,CompFailureLoad1,CompFailureLoad2,CompFailureLoad3,CompFailureLoad4,CompFailureLoad5,CompFailureLoad6
		  FROM Tes04006T01 
		 where SerialNumber= @SerialNumber
		)
		ORDER BY id;								                        --创建游标tempCursor，并定义游标所指向的集合   
	OPEN tempCursor;								                        --打开游标  
	FETCH NEXT FROM tempCursor INTO @id,@BendFailureLoad1,@BendFailureLoad2,@BendFailureLoad3
				,@CompFailureLoad1,@CompFailureLoad2,@CompFailureLoad3,@CompFailureLoad4,@CompFailureLoad5,@CompFailureLoad6;			
																			--游标读取下一个数据  
	WHILE @@fetch_status = 0                                                --游标读取下一个数据的状态，0表示读取成功  
    BEGIN  		
		-- 计算 抗折强度 =  (1.5抗折破坏荷载*100)/40³ 四舍五入精确到0.1
		DECLARE @RuptureStrength1 decimal(15,1),@RuptureStrength2 decimal(15,1),@RuptureStrength3 decimal(15,1),@RSAvg decimal(15,1),@RSSum decimal(15,1)
		declare @Count int
		set @Count = 0;
		set @RSSum = 0;
		--set @RuptureStrength1 = 0;
		--set @RuptureStrength2 = 0;
		--set @RuptureStrength3 = 0;

		if(@BendFailureLoad1 >0)
		begin
			set @RuptureStrength1 = ROUND((1.5*@BendFailureLoad1*100)/64000, 1);
			set @RSSum = @RSSum + @RuptureStrength1;
			set @Count = @Count+1;
		end
		
		if(@BendFailureLoad2 >0)
		begin
			set @RuptureStrength2 = ROUND((1.5*@BendFailureLoad2*100)/64000, 1);
			set @RSSum = @RSSum + @RuptureStrength2;
			set @Count = @Count+1;
		end
		
		if(@BendFailureLoad3 >0)
		begin
			set @RuptureStrength3 = ROUND((1.5*@BendFailureLoad3*100)/64000, 1);
			set @RSSum = @RSSum + @RuptureStrength3;
			set @Count = @Count+1;
		end
		
		-- 计算平均抗折强度 四舍五入精确到0.1
		if(@Count=3)
		begin
			DECLARE @iState int;
			set @iState = 0 ;
			
			set @RSAvg = ROUND(@RSSum/@Count, 1);
			
			-- 每个单值都与平均值做差值
			if(ABS(@RSAvg-@RuptureStrength1) > (@RSAvg *0.1))
			begin
				set @iState = @iState+1 ;
				set @RuptureStrength1 = null;
			end
			if(ABS(@RSAvg-@RuptureStrength2) > (@RSAvg *0.1))
			begin
				set @iState = @iState+1 ;
				set @RuptureStrength2 = null;
			end
			if(ABS(@RSAvg-@RuptureStrength3) > (@RSAvg *0.1))
			begin
				set @iState = @iState+1 ;
				set @RuptureStrength3 = null;
			end
			
			-- 如果有一个差值的绝对值超过平均值的10%
			if(@iState = 1)
			begin
				set @RSAvg = ROUND((isnull(@RuptureStrength1,0)+isnull(@RuptureStrength2,0)+isnull(@RuptureStrength3,0))/2, 1);
			end	
			-- 如果有两个差值的绝对值超过平均值的10%，则不计算平均值，显示“无效”
			if(@iState >= 2)
			begin
				set @RSAvg = null;
				set @Result = 2;
			end
			
			if(@RSAvg is not null)
			begin
				set @AvgRuptureStrength = @AvgRuptureStrength+@RSAvg; 
				set @AvgRSCount = @AvgRSCount+1;
			end
		end 
		else
		begin
			set @Result = 2;
		end
		
		-- 计算 抗压强度= 抗压破坏荷载/40mm² 四舍五入精确到0.1
		DECLARE @CompTrength1 decimal(15,1),@CompTrength2 decimal(15,1),@CompTrength3 decimal(15,1),@CTAvg  decimal(15,1)
		   ,@CompTrength4 decimal(15,1),@CompTrength5 decimal(15,1),@CompTrength6  decimal(15,1),@CTSum  decimal(15,1)
		set @Count = 0;
		set @CTSum = 0;
		--set @CompTrength1 = 0;
		--set @CompTrength2 = 0;
		--set @CompTrength3 = 0;
		--set @CompTrength4 = 0;
		--set @CompTrength5 = 0;
		--set @CompTrength6 = 0;
		if(@CompFailureLoad1 > 0)
		begin
			set @CompTrength1 = ROUND(@CompFailureLoad1/1600,1);
			set @CTSum = @CTSum+@CompTrength1;
			set @Count = @Count+1; 
		end
		if(@CompFailureLoad2 > 0)
		begin
			set @CompTrength2 = ROUND(@CompFailureLoad2/1600,1);
			set @CTSum = @CTSum+@CompTrength2;
			set @Count = @Count+1; 
		end
		if(@CompFailureLoad3 > 0)
		begin
			set @CompTrength3 = ROUND(@CompFailureLoad3/1600,1);
			set @CTSum = @CTSum+@CompTrength3;
			set @Count = @Count+1; 
		end
		if(@CompFailureLoad4 > 0)
		begin
			set @CompTrength4 = ROUND(@CompFailureLoad4/1600,1);
			set @CTSum = @CTSum+@CompTrength4;
			set @Count = @Count+1; 
		end
		if(@CompFailureLoad5 > 0)
		begin
			set @CompTrength5 = ROUND(@CompFailureLoad5/1600,1);
			set @CTSum = @CTSum+@CompTrength5;
			set @Count = @Count+1; 
		end
		if(@CompFailureLoad6 > 0)
		begin
			set @CompTrength6 = ROUND(@CompFailureLoad6/1600,1);
			set @CTSum = @CTSum+@CompTrength6;
			set @Count = @Count+1; 
		end
		
		-- 计算平均抗压强度 四舍五入精确到0.1
		if(@Count=6)
		begin
			DECLARE @iState2 int;
			set @iState2 = 0 ;
			
			set @CTAvg = ROUND(@CTSum/@Count, 1);
						
			-- 每个单值都与平均值做差值
			if(ABS(@CTAvg-@CompTrength1) > (@CTAvg *0.1))
			begin
				set @iState2 = @iState2+1 ;
				set @CompTrength1 = null;
			end
			if(ABS(@CTAvg-@CompTrength2) > (@CTAvg *0.1))
			begin
				set @iState2 = @iState2+1 ;
				set @CompTrength2 = null;
			end
			if(ABS(@CTAvg-@CompTrength3) > (@CTAvg *0.1))
			begin
				set @iState2 = @iState2+1 ;
				set @CompTrength3 = null;
			end
			if(ABS(@CTAvg-@CompTrength4) > (@CTAvg *0.1))
			begin
				set @iState2 = @iState2+1 ;
				set @CompTrength4 = null;
			end
			if(ABS(@CTAvg-@CompTrength5) > (@CTAvg *0.1))
			begin
				set @iState2 = @iState2+1 ;
				set @CompTrength5 = null;
			end
			if(ABS(@CTAvg-@CompTrength6) > (@CTAvg *0.1))
			begin
				set @iState2 = @iState2+1 ;
				set @CompTrength6 = null;
			end
				
			-- 如果有一个差值的绝对值超过平均值的10%
			if(@iState2 = 1)
			begin
				set @CTAvg = ROUND((isnull(@CompTrength1,0)+isnull(@CompTrength2,0)+isnull(@CompTrength3,0)+isnull(@CompTrength4,0)+isnull(@CompTrength5,0)+isnull(@CompTrength6,0))/5, 1);
			end	
			-- 如果有两个差值的绝对值超过平均值的10%，则不计算平均值，显示“无效”
			if(@iState2 >= 2)
			begin
				set @CTAvg = null;
				set @Result = 2;
			end
			
			if(@CTAvg is not null)
			begin
				set @AvgCompFailureLoad = @AvgCompFailureLoad+@CTAvg;
				set @AvgCFCount = @AvgCFCount+1;
			end						
		end
		else
		begin
			set @Result = 2; 
		end

		update Tes04006T01
		   set RuptureStrength1 = @RuptureStrength1
			  ,RuptureStrength2 = @RuptureStrength2
			  ,RuptureStrength3 = @RuptureStrength3
			  ,AvgRuptureStrength = @RSAvg
			  ,CompTrength1 = @CompTrength1
			  ,CompTrength2 = @CompTrength2
			  ,CompTrength3 = @CompTrength3
			  ,CompTrength4 = @CompTrength4
			  ,CompTrength5 = @CompTrength5
			  ,CompTrength6 = @CompTrength6
			  ,AvgCompTrength = @CTAvg
		WHERE Id  = @Id
		
        FETCH NEXT FROM tempCursor INTO @id,@BendFailureLoad1,@BendFailureLoad2,@BendFailureLoad3
            ,@CompFailureLoad1,@CompFailureLoad2,@CompFailureLoad3,@CompFailureLoad4,@CompFailureLoad5,@CompFailureLoad6;;    --继续用游标读取下一个数据  
    END  
	CLOSE tempCursor;								--关闭游标
	DEALLOCATE tempCursor;
    
    if(@AvgRSCount >0 or @AvgCFCount >0)
	begin
		-- 获取结果判定数值；
		DECLARE @RS3 decimal(15,1),@RS28 decimal(15,1),@CT3 decimal(15,1),@CT28 decimal(15,1),@RS decimal(15,1),@CT decimal(15,1)
		select @RS3=RuptureStrength3,@RS28=RuptureStrength28,@CT3=CompTrength3,@CT28=CompTrength28 
		  from JudgingBasis04006 
		 where CementType like '%'+@CementType+'%' and CementStrengthGrade = @CementStrengthGrade
		
		-- 按龄期判获取判定标准是3天还28天值；
		if(@Age = 3)
		begin
			set @RS = @RS3;
			set @CT = @CT3;
		end
		else if(@Age = 28)
		begin
			set @RS = @RS28;
			set @CT = @CT28;
		end
		
		DECLARE @IsQualifiedTest bit; 
		-- set @IsQualifiedTest = 1;
		-- (0:不合格 1:合格 2:无效)
		if (@Result = 2)
		begin
			set @IsQualifiedTest = null;
		end
		else
		begin
			if(@AvgRuptureStrength < @RS or @AvgCompFailureLoad<@CT)
			begin
				set @IsQualifiedTest = 0;
			end
			else
			begin
				set @IsQualifiedTest = 1;
			end 
		end		
		
		-- 更TestInfo表和Test04006T0试验主表；
		update TestInfo 
		   set IsQualifiedTest = @IsQualifiedTest 
		 where SerialNumber = @SerialNumber

		update Test04006T0 
		   set AvgRStrength = @AvgRuptureStrength
		      ,AvgCTrength = @AvgCompFailureLoad 
		 where SerialNumber = @SerialNumber
	end
    
	 return 1
  end