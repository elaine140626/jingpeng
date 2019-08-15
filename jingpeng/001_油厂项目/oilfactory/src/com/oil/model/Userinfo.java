package com.oil.model;

/**
 * 用户信息表
 */
public class Userinfo {

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
     * 别名
     */
    private String anotherName;
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
     * 删除标记
     */
    private int isdel;
    /**
     * 创建人
     */
    private String creater;
    /**
     * 创建时间
     */
    private String createrdate;
    /**
     * 修改人
     */
    private String reviser;
    /**
     * 创建时间
     */
    private String reviserdate;

    public String getAnotherName() {
		return anotherName;
	}
	public void setAnotherName(String anotherName) {
		this.anotherName = anotherName;
	}
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
    public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	public String getCreater() {
        return creater;
    }
    public void setCreater(String creater) {
        this.creater = creater;
    }
    public String getReviser() {
        return reviser;
    }
    public void setReviser(String reviser) {
        this.reviser = reviser;
    }
	public String getCreaterdate() {
		return createrdate;
	}
	public void setCreaterdate(String createrdate) {
		this.createrdate = createrdate;
	}
	public String getReviserdate() {
		return reviserdate;
	}
	public void setReviserdate(String reviserdate) {
		this.reviserdate = reviserdate;
	}
}