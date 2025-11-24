package com.alk.model;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

import com.alk.converter.EncryptionConverter;

import jakarta.persistence.*;

@Entity
@Table(name = "driving_license")
public class DrivingLicense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Convert(converter = EncryptionConverter.class)
    private String dlNumber;

    @Convert(converter = EncryptionConverter.class)
    private String forename;

    @Convert(converter = EncryptionConverter.class)
    private String middleName;

    @Convert(converter = EncryptionConverter.class)
    private String lastName;

    @Convert(converter = EncryptionConverter.class)
    private String dob;

    @Convert(converter = EncryptionConverter.class)
    private String address;

    @Convert(converter = EncryptionConverter.class)
    private LocalDate issueDate;

    @Convert(converter = EncryptionConverter.class)
    private LocalDate expiryDate;

    @Convert(converter = EncryptionConverter.class)
    private String licenseClass;

    @Convert(converter = EncryptionConverter.class)
    private String gender;

    @Convert(converter = EncryptionConverter.class)
    private String height;

    @Convert(converter = EncryptionConverter.class)
    private String bloodType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pii_id", nullable = false)
    private Pii pii;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDlNumber() {
		return dlNumber;
	}

	public void setDlNumber(String dlNumber) {
		this.dlNumber = dlNumber;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public Pii getPii() {
		return pii;
	}

	public void setPii(Pii pii) {
		this.pii = pii;
	}
    
    
}

