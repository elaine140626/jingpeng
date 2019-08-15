package com.jingpeng.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class JDBCUtil {
	
	public static List<Map<String, Object>> execute(String sql) {
		Connection conn;
		Statement st;
		ResultSet rs;
		List<Map<String, Object>> list = null;
		try {
			Map<String, String> map = getValue();
			Class.forName(map.get("driverClassName"));
			conn = DriverManager.getConnection(map.get("url"), map.get("username"), map.get("password"));
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			list = resultSetToList(rs);
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
	
	public static List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int colCount = rsmd.getColumnCount();
		List<String> colNameList = new ArrayList<String>();
		for (int i = 0; i < colCount; i++) {
			colNameList.add(rsmd.getColumnName(i + 1));
		}
		while (rs.next()) {
			Map map = new HashMap<String, Object>();
			for (int i = 0; i < colCount; i++) {
				String key = colNameList.get(i);
				Object value = rs.getString(colNameList.get(i));
				map.put(key, value);
			}
			results.add(map);
		}
		return results;
	}
	
	public static Class getClazz() {
		return new Object() {
			public Class getClazz() {
				return this.getClass();
			}
		}.getClazz();
	}
	
	public static Map<String, String> getValue(){
		Properties prop = new Properties();
		Map<String, String> map = new HashMap();
		try {
			Class c = getClazz();
			InputStream in = c.getClassLoader().getResourceAsStream("jdbc.properties");
			prop.load(in);
			String driverClassName = prop.getProperty("jdbc.driverClassName");
			String url = prop.getProperty("jdbc.url");
			String username = prop.getProperty("jdbc.username");
			String password = prop.getProperty("jdbc.password");
			map.put("driverClassName", driverClassName);
			map.put("url", url);
			map.put("username", username);
			map.put("password", password);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
}