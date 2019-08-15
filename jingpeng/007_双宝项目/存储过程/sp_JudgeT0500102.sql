USE [ShuangBaoTestDB]
GO
/****** Object:  StoredProcedure [dbo].[sp_JudgeT0500102]    Script Date: 08/09/2019 09:00:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- a
 ---创建存储过程（砂浆抗压强度）
 ALTER procedure [dbo].[sp_JudgeT0500102]
  @SerialNumber varchar(100),
  @DesignStrength decimal(15,1),
  @SampleAmount int
 as
  begin	
	DECLARE @id INT;
	DECLARE @UltimateLoad1 decimal(15,1),@UltimateLoad2 decimal(15,1),@UltimateLoad3 decimal(15,1)
	,@UltimateLoad4 decimal(15,1),@UltimateLoad5 decimal(15,1),@UltimateLoad6 decimal(15,1)

	-- 多组平均抗压；	   
	DECLARE @AvgCompFailureLoad decimal(15,1),@AvgCFCount int; 
    set @AvgCompFailureLoad = 0;
    set @AvgCFCount = 0;
  
	DECLARE tempCursor CURSOR
	FOR
		( SELECT Id,UltimateLoad1,UltimateLoad2,UltimateLoad3,UltimateLoad4,UltimateLoad5,UltimateLoad6
		  FROM Test0500102T01 
		 where SerialNumber= @SerialNumber
		)
		ORDER BY id;								                        --创建游标tempCursor，并定义游标所指向的集合   
	OPEN tempCursor;								                        --打开游标  
	FETCH NEXT FROM tempCursor INTO @id,@UltimateLoad1,@UltimateLoad2,@UltimateLoad3,@UltimateLoad4,@UltimateLoad5,@UltimateLoad6;			
																			--游标读取下一个数据  
	WHILE @@fetch_status = 0                                                --游标读取下一个数据的状态，0表示读取成功  
    BEGIN  		
		-- 计算 抗折强度 =   极限荷载/（70.7*70.7）*1000*1.35  四舍五入精确到0.1
		DECLARE @ComprStrength1 decimal(15,1),@ComprStrength2 decimal(15,1),@ComprStrength3 decimal(15,1),@CSAvg decimal(15,1),@CSSum decimal(15,1)
		,@ComprStrength4 decimal(15,1),@ComprStrength5 decimal(15,1),@ComprStrength6 decimal(15,1)
		declare @Count int
		set @Count = 0;
		set @CSSum = 0;

		if(@UltimateLoad1 >0)
		begin
			set @ComprStrength1 = ROUND(@UltimateLoad1/4998.49*1000, 1);
			set @CSSum = @CSSum + @ComprStrength1;
			set @Count = @Count+1;
		end
		
		if(@UltimateLoad2 >0)
		begin
			set @ComprStrength2 = ROUND(@UltimateLoad2/4998.49*1000, 1);
			set @CSSum = @CSSum + @ComprStrength2;
			set @Count = @Count+1;
		end
		
		if(@UltimateLoad3 >0)
		begin
			set @ComprStrength3 = ROUND(@UltimateLoad3/4998.49*1000, 1);
			set @CSSum = @CSSum + @ComprStrength3;
			set @Count = @Count+1;
		end
		
		if(@SampleAmount = 6)
		begin
			if(@UltimateLoad4 >0)
			begin
				set @ComprStrength4 = ROUND(@UltimateLoad4/4998.49*1000, 1);
				set @CSSum = @CSSum + @ComprStrength4;
				set @Count = @Count+1;
			end
			
			if(@UltimateLoad5 >0)
			begin
				set @ComprStrength5 = ROUND(@UltimateLoad5/4998.49*1000, 1);
				set @CSSum = @CSSum + @ComprStrength5;
				set @Count = @Count+1;
			end
			
			if(@UltimateLoad6 >0)
			begin
				set @ComprStrength6 = ROUND(@UltimateLoad6/4998.49*1000, 1);
				set @CSSum = @CSSum + @ComprStrength6;
				set @Count = @Count+1;
			end	
		end
		
		-- 计算平均抗折强度 四舍五入精确到0.1
		if(@Count>0)
		begin
			set @CSAvg = ROUND(@CSSum/@Count, 1);
			set @AvgCompFailureLoad = @AvgCompFailureLoad+@CSAvg; 
			set @AvgCFCount = @AvgCFCount+1;
			
			-- 计算 占设计强度百分率（%） = 抗压强度/设计强度*100 精确至0.1;
			DECLARE @Prop_DesignStrength decimal(15,1);
			set @Prop_DesignStrength = ROUND(@CSAvg/@DesignStrength*100,1);
			
			UPDATE Test0500102T01
			   SET ComprStrength1 = @ComprStrength1
				  ,ComprStrength2 = @ComprStrength2
				  ,ComprStrength3 = @ComprStrength3
				  ,ComprStrength4 = @ComprStrength4
				  ,ComprStrength5 = @ComprStrength5
				  ,ComprStrength6 = @ComprStrength6
				  ,CompressionStrength = @CSAvg
				  ,Prop_DesignStrength = @Prop_DesignStrength
			WHERE Id  = @Id
		end
		
        FETCH NEXT FROM tempCursor INTO @id,@UltimateLoad1,@UltimateLoad2,@UltimateLoad3,@UltimateLoad4,@UltimateLoad5,@UltimateLoad6;   --继续用游标读取下一个数据  
    END  
	CLOSE tempCursor;								--关闭游标
	DEALLOCATE tempCursor;
    
    if(@AvgCFCount >0)
	begin
        DECLARE @Avg decimal(15,1);
        set @Avg = ROUND(@AvgCompFailureLoad/@AvgCFCount,1);
		DECLARE @IsQualifiedTest bit; 
		set @IsQualifiedTest = 1;
		--if(@Avg < @DesignStrength)
		--begin
		--	set @IsQualifiedTest = 0;
		--end
		-- 更TestInfo表和Test0500101T0试验主表；
		update TestInfo 
		   set IsQualifiedTest = @IsQualifiedTest 
		 where SerialNumber = @SerialNumber

		update Test0500102T0 
		   set AvgCompStrength = @Avg
		 where SerialNumber = @SerialNumber
	end
	 return 1
  end