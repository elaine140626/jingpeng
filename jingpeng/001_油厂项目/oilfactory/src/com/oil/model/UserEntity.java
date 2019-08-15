package com.oil.model;

public class UserEntity {
	 private int id;
	    /**
	     * 用户名
	     */
	    private String username;
	    /**
	     * 用户密码
	     */
	    private String password;
	    /**
	     * 角色Code 0:管理员 1:销售人员 2:调度人员 3:检查员 4:财务 5:总经理
	     */
	    private String rolecode;
	    /**
	     * 姓名
	     */
	    private String name;
	    /**
	     * 年龄
	     */
	    private int age;
	    /**
	     * 性别
	     */
	    private String sex;
	    /**
	     * 出生日期
	     */
	    private String birthday;
	    /**
	     * 地址
	     */
	    private String address;
	    /**
	     * 电话
	     */
	    private String telephone;
	    /**
	     * 其他联系方式
	     */
	    private String othernumbers;    

	    /**
	     * 权限名称
	     */
	    private String rolename; 
	    public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUsername() {
	        return username;
	    }
	    public void setUsername(String username) {
	        this.username = username;
	    }
	    public String getPassword() {
	        return password;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }
	    public String getRolecode() {
	        return rolecode;
	    }
	    public void setRolecode(String rolecode) {
	        this.rolecode = rolecode;
	    }
	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    public int getAge() {
	        return age;
	    }
	    public void setAge(int age) {
	        this.age = age;
	    }
	    public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public String getSex() {
	        return sex;
	    }
	    public void setSex(String sex) {
	        this.sex = sex;
	    }
	    public String getAddress() {
	        return address;
	    }
	    public void setAddress(String address) {
	        this.address = address;
	    }
	    public String getTelephone() {
	        return telephone;
	    }
	    public void setTelephone(String telephone) {
	        this.telephone = telephone;
	    }
	    public String getOthernumbers() {
	        return othernumbers;
	    }
	    public void setOthernumbers(String othernumbers) {
	        this.othernumbers = othernumbers;
	    }
		public String getRolename() {
			return rolename;
		}
		public void setRolename(String rolename) {
			this.rolename = rolename;
		}
	}
