package com.yun.yweb.user.dto;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userCode;

	private String bankAddress;

	private String bankNo;

	private String bankOwner;

	private String bankType;

	private Date createTime;

	private String creatorUser;

	private int dayPwderrorNum;

	private String email;

	private String idNo;

	private Date lastLoginTime;

	private String loginIp;

	private String loginMac;

	private String loginPwd;

	private String loginTime;

	private String mobile;

	private String nickName;

	private String officeTel;

	private String parentUsercode;

	private int pwdErrorNum;

	private Date pwdErrorTime;

	private String pwdInvalid;

	private String qqNumber;

	private String secondPwd;

	private String state;

	private String userName;

	private String userType;

	private String weixinNumber;

	public User() {
	}

	public User(UserTemp approveUser) {
		this.userCode = approveUser.getUserCode();
		this.userName = approveUser.getUserName();
		this.loginPwd = approveUser.getLoginPwd();
		this.email = approveUser.getEmail();
		this.mobile = approveUser.getMobile();
		this.officeTel = approveUser.getOfficeTel();
		this.createTime = approveUser.getApproveTime();
		this.creatorUser = approveUser.getApproveUser();
		this.secondPwd = approveUser.getSecondPwd();
		this.parentUsercode = approveUser.getParentUsercode();
		this.nickName = approveUser.getNickName();
		this.idNo = approveUser.getIdNo();
		this.qqNumber = approveUser.getQqNumber();
		this.weixinNumber = approveUser.getWeixinNumber();
		this.bankType = approveUser.getBankType();
		this.bankNo = approveUser.getBankNo();
		this.bankOwner = approveUser.getBankOwner();
		this.bankAddress = approveUser.getBankAddress();
		this.userType = approveUser.getUserType();
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getBankAddress() {
		return this.bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getBankNo() {
		return this.bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankOwner() {
		return this.bankOwner;
	}

	public void setBankOwner(String bankOwner) {
		this.bankOwner = bankOwner;
	}

	public String getBankType() {
		return this.bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getDayPwderrorNum() {
		return this.dayPwderrorNum;
	}

	public void setDayPwderrorNum(int dayPwderrorNum) {
		this.dayPwderrorNum = dayPwderrorNum;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginMac() {
		return this.loginMac;
	}

	public void setLoginMac(String loginMac) {
		this.loginMac = loginMac;
	}

	public String getLoginPwd() {
		return this.loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getOfficeTel() {
		return this.officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getParentUsercode() {
		return this.parentUsercode;
	}

	public void setParentUsercode(String parentUsercode) {
		this.parentUsercode = parentUsercode;
	}

	public int getPwdErrorNum() {
		return this.pwdErrorNum;
	}

	public void setPwdErrorNum(int pwdErrorNum) {
		this.pwdErrorNum = pwdErrorNum;
	}

	public Date getPwdErrorTime() {
		return this.pwdErrorTime;
	}

	public void setPwdErrorTime(Date pwdErrorTime) {
		this.pwdErrorTime = pwdErrorTime;
	}

	public String getPwdInvalid() {
		return this.pwdInvalid;
	}

	public void setPwdInvalid(String pwdInvalid) {
		this.pwdInvalid = pwdInvalid;
	}

	public String getQqNumber() {
		return this.qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public String getSecondPwd() {
		return this.secondPwd;
	}

	public void setSecondPwd(String secondPwd) {
		this.secondPwd = secondPwd;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getWeixinNumber() {
		return this.weixinNumber;
	}

	public void setWeixinNumber(String weixinNumber) {
		this.weixinNumber = weixinNumber;
	}

	public String getCreatorUser() {
		return creatorUser;
	}

	public void setCreatorUser(String creatorUser) {
		this.creatorUser = creatorUser;
	}

}