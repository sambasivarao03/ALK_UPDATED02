package com.alk.dto.datasource;

import com.alk.dto.CommonPersonDto;

public class AadhaarRequestDto {

    private String aadhaarNumber;
    private CommonPersonDto person;
    private String parentGuardianName;
    private String mobile;
    private String email;
	public String getAadhaarNumber() {
		return aadhaarNumber;
	}
	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}
	public CommonPersonDto getPerson() {
		return person;
	}
	public void setPerson(CommonPersonDto person) {
		this.person = person;
	}
	public String getParentGuardianName() {
		return parentGuardianName;
	}
	public void setParentGuardianName(String parentGuardianName) {
		this.parentGuardianName = parentGuardianName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

    // getters + setters
    
}
