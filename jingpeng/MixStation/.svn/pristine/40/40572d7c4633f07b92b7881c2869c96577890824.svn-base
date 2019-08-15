package com.jingpeng.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jingpeng.model.Asph_TargetPropDetailed;
import com.jingpeng.model.Asph_TargetProportion;
import com.jingpeng.model.V_MaterialInfo;
import com.jingpeng.util.JDBCUtil;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class TestDao extends KDDaoSupport {

	public List<Map<String, Object>> get() {
		Connection conn;
		Statement st;
		ResultSet rs;
		List<Map<String, Object>> list = null;
		try {
			String URL = "jdbc:sqlserver://192.168.1.123:1433;databaseName=MixStationDB1";
			String USER = "sa";
			String PASSWORD = "123";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			st = conn.createStatement();
			rs = st.executeQuery("exec proc_Alert '2018-03-09 00:00:00','2018-05-07 00:00:00','3,17,22',0,1");
			
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
