package com.alk.dto.datasource;

import com.alk.dto.CommonPersonDto;
import java.time.LocalDate;

public class DrivingLicenseRequestDto {

    private String dlNumber;
    private CommonPersonDto person;

    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String licenseClass;
    private String height;
    private String bloodType;
	public String getDlNumber() {
		return dlNumber;
	}
	public void setDlNumber(String dlNumber) {
		this.dlNumber = dlNumber;
	}
	public CommonPersonDto getPerson() {
		return person;
	}
	public void setPerson(CommonPersonDto person) {
		this.person = person;
	}
	public LocalDate getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getLicenseClass() {
		return licenseClass;
	}
	public void setLicenseClass(String licenseClass) {
		this.licenseClass = licenseClass;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

    // getters + setters
    
}
