package com.oil.model;


public class ResponseInfo{

    /**
     * 返回信息
     */
   // private static final long serialVersionUID = -8918590349400215878L;
    private String code;
    private String message;
    private String data;
    private String amount;
    private int sid;
    
    

    public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
