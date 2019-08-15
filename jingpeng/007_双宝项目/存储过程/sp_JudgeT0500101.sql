USE [ShuangBaoTestDB]
GO
/****** Object:  StoredProcedure [dbo].[sp_JudgeT0500101]    Script Date: 08/09/2019 09:01:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- a
 ---创建存储过程（水泥混凝土抗压强度（立方体））
 ALTER procedure [dbo].[sp_JudgeT0500101]
  @SerialNumber varchar(100),
  @SampleSize int,
  @DesignStrength decimal(15,1)
 as
  begin	
	DECLARE @id INT;
	DECLARE @UltimateLoad1 decimal(15,1),@UltimateLoad2 decimal(15,1),@UltimateLoad3 decimal(15,1)

	-- 多组平均抗压；	   
	DECLARE @AvgCompFailureLoad decimal(15,1),@AvgCFCount int; 
    set @AvgCompFailureLoad = 0;
    set @AvgCFCount = 0;
    
    -- 系数
    DECLARE @Coefficient decimal(10,2);
    if (@SampleSize = 150)
	begin
		set @Coefficient = 1;
	end
	
	if (@SampleSize = 100)
	begin
		set @Coefficient = 0.95;
	end
	
	if (@SampleSize = 200)
	begin
		set @Coefficient = 1.05;
	end
	
	-- 结果判定是否无效 (0:不合格 1:合格 2:无效)
	DECLARE @Result varchar(2);
	set @Result = 1;
  
	DECLARE tempCursor CURSOR
	FOR
		( SELECT Id,UltimateLoad1,UltimateLoad2,UltimateLoad3
		  FROM Test0500101T01 
		 where SerialNumber= @SerialNumber
		)
		ORDER BY id;								                        --创建游标tempCursor，并定义游标所指向的集合   
	OPEN tempCursor;								                        --打开游标  
	FETCH NEXT FROM tempCursor INTO @id,@UltimateLoad1,@UltimateLoad2,@UltimateLoad3;			
																			--游标读取下一个数据  
	WHILE @@fetch_status = 0                                                --游标读取下一个数据的状态，0表示读取成功  
    BEGIN  		
		-- 计算 抗折强度 =   极限荷载/(150 * 150)*1000  四舍五入精确到0.1
		DECLARE @ComprStrength1 decimal(15,1),@ComprStrength2 decimal(15,1),@ComprStrength3 decimal(15,1),@CSAvg decimal(15,1),@CSSum decimal(15,1)
		declare @Count int
		set @Count = 0;
		set @CSSum = 0;

		if(@UltimateLoad1 >0)
		begin			
			set @ComprStrength1 = ROUND(@UltimateLoad1*@Coefficient/(@SampleSize*@SampleSize)*1000, 1);						
			set @CSSum = @CSSum + @ComprStrength1;
			set @Count = @Count+1;
		end
		
		if(@UltimateLoad2 >0)
		begin
			set @ComprStrength2 = ROUND(@UltimateLoad2*@Coefficient/(@SampleSize*@SampleSize)*1000, 1);
			set @CSSum = @CSSum + @ComprStrength2;
			set @Count = @Count+1;
		end
		
		if(@UltimateLoad3 >0)
		begin
			set @ComprStrength3 = ROUND(@UltimateLoad3*@Coefficient/(@SampleSize*@SampleSize)*1000, 1);
			set @CSSum = @CSSum + @ComprStrength3;
			set @Count = @Count+1;
		end
		
		-- 计算平均抗折强度 四舍五入精确到0.1
		-- 3个试件中 最大值或最小值中如有一个与中间值之差超过中间值的15％，则把最大值和最小值舍去，以中间值作为试件的抗弯拉强度；
        --           如最大值和最小值与中间值之差值均超过中间值15％，则该组试验结果无效。  
        --           否则 三数求平均值 精确至 0.1
		
		if(@Count=3)
		begin
			DECLARE @dMax decimal(15,1),@dMin decimal(15,1),@dMedian decimal(15,1);
			DECLARE @iState int;
			set @iState = 0 ;
			
			select @dMax=Max(NewDate) from (values (@ComprStrength1),(@ComprStrength2),(@ComprStrength3)) as #temp(NewDate)
			select @dMin=Min(NewDate) from (values (@ComprStrength1),(@ComprStrength2),(@ComprStrength3)) as #temp(NewDate)
			select @dMedian=NewDate from (values (@ComprStrength1),(@ComprStrength2),(@ComprStrength3)) as #temp(NewDate) where NewDate> @dMin and NewDate <@dMax
		
		    -- 计算平均值
			if(ABS(@dMax-@dMedian) > (@dMedian *0.15))
				set @iState = @iState+1 ;
		
			if(ABS(@dMin-@dMedian) > (@dMedian *0.15))
				set @iState = @iState+1 ;
				
			if(@iState = 2)
			begin
				set @CSAvg = null;
				set @Result = 2;
			end
			else if(@iState = 1)
			begin
				set @CSAvg = @dMedian;
			end
			else
			begin			
				set @CSAvg = ROUND(@CSSum/@Count, 1);
			end
			
			-- 计算 占设计强度百分率（%） = 抗压强度/设计强度*100 精确至0.1;
			DECLARE @Prop_DesignStrength decimal(15,1);
			if (@CSAvg is not null and @DesignStrength is not null)
			begin
				set @AvgCompFailureLoad = @AvgCompFailureLoad+@CSAvg; 
				set @AvgCFCount = @AvgCFCount+1;
				set @Prop_DesignStrength = ROUND(@CSAvg/@DesignStrength*100,0);
				if (@Prop_DesignStrength < 115)
				begin
					set @Result = 0
				end
			end		
			
			UPDATE Test0500101T01
			   SET ComprStrength1 = @ComprStrength1
				  ,ComprStrength2 = @ComprStrength2
				  ,ComprStrength3 = @ComprStrength3
				  ,CompressiveStrength = @CSAvg
				  ,Prop_DesignStrength = @Prop_DesignStrength
			WHERE Id  = @Id
		end
		
        FETCH NEXT FROM tempCursor INTO @id,@UltimateLoad1,@UltimateLoad2,@UltimateLoad3;   --继续用游标读取下一个数据  
    END  
	CLOSE tempCursor;								--关闭游标
	DEALLOCATE tempCursor;
   
    if(@AvgCFCount >0)
	begin
        DECLARE @Avg decimal(15,1);
        set @Avg = ROUND(@AvgCompFailureLoad/@AvgCFCount,1);
		DECLARE @IsQualifiedTest bit; 
		---- (0:不合格 1:合格 2:无效)
		--if (@Result = 0)
		--begin
		--	set @IsQualifiedTest = 0;
		--end
		--if (@Result = 1)
		--begin
		--	set @IsQualifiedTest = 1;
		--end
		--if (@Result = 2)
		--begin
		--	set @IsQualifiedTest = null;
		--end	
		set @IsQualifiedTest = 1;
		--if(@Avg < @DesignStrength)
		--begin
		--	set @IsQualifiedTest = 0;
		--end

		-- 更TestInfo表和Test0500101T0试验主表；
		update TestInfo 
		   set IsQualifiedTest = @IsQualifiedTest 
		 where SerialNumber = @SerialNumber

		update Test0500101T0 
		   set AvgCompStrength = @Avg
		 where SerialNumber = @SerialNumber
	end
	 return 1
  end