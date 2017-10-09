package com.javen.model;



import java.util.Date;

public class User {
    private Integer id;

    private String userName;

    private String userPassword;

    private String userType;

    private Date createTime;

    private Integer authentication;

    private String address;

    private String state;

    private String cardType;

    private String cardNumber;

    private String telephone;

    private String email;

    private String remarks;
    
    private String  newPassword;
   
    private String beiyong1;
    
	private String beiyong2;
	
	private String addressValue;
	
	private String stateValue;
	
	
	
	public String getAddressValue() {
		return addressValue;
	}

	public void setAddressValue(String addressValue) {
		this.addressValue = addressValue == null ? null : addressValue.trim();
	}

	public String getStateValue() {
		return stateValue;
	}

	public void setStateValue(String stateValue) {
		this.stateValue = stateValue == null ? null : stateValue.trim();
	}

	public String getbeiyong1() {
		return beiyong1;
	}

	public void setbeiyong1(String beiyong1) {
		this.beiyong1 = beiyong1 == null ? null : beiyong1.trim();
	}

	public String getBeiyong2() {
		return beiyong2;
	}

	public void setBeiyong2(String beiyong2) {
		this.beiyong2 = beiyong2 == null ? null : beiyong2.trim();
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Integer authentication) {
        this.authentication = authentication;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getstate() {
        return state;
    }

    public void setstate(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? null : cardNumber.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
    
    
}