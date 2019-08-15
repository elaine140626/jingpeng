package com.jingpeng.util;



import java.util.List;



/*
 * 处理分页问题
 * tongnan
 * 2018.5.31
 */
public class PageInfoUtil {

	 public static <T> List<T> pagination(Integer cursor, Integer count,List<T> list) {
		 
		int pageCnt = 0;
		
		//分页
		 if(list!=null&&list.size()>0) {
			 
		    int Sum = list.size();
		    
		 	if (Sum > 0) {
				// 总页数
				if (Sum % count> 0) {
					pageCnt = Sum / count + 1;
				} else {
					pageCnt = Sum / count;
				}
			}
			
		 }
			
	 	
	     //总数
	     if(list!=null) {
	    	 	     
		     //开始条数
		     int   firstResult = (cursor - 1) * count;
		     //结果集
		     if(cursor<pageCnt) {
		    	 
		    	 list = list.subList(firstResult, firstResult+count);
		    	 
		     }else {
		    	
		    	 list = list.subList(firstResult, list.size());
		    	 
		     }
		     	     
	     }
	     
		return list;
	      
	 }
}
