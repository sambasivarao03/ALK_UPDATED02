package com.alk.dto.datasource;

import com.alk.dto.CommonPersonDto;

public class PanRequestDto {

    private String panNumber;
    private CommonPersonDto person;

    private String fatherName;
    private String motherName;
    private String phone;
    private String email;
    private String aadhaarLinked;
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public CommonPersonDto getPerson() {
		return person;
	}
	public void setPerson(CommonPersonDto person) {
		this.person = person;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAadhaarLinked() {
		return aadhaarLinked;
	}
	public void setAadhaarLinked(String aadhaarLinked) {
		this.aadhaarLinked = aadhaarLinked;
	}
	
	

    // getters + setters
    
}
