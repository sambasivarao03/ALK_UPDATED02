package com.alk.model;

import java.time.LocalDate;

import com.alk.converter.EncryptionConverter;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
@Entity
@Table(name = "pan_data")
public class PanData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 10, max = 10)
    @Column(unique = true)
    @Convert(converter = EncryptionConverter.class)
    private String panNumber;

    @NotNull
    @Convert(converter = EncryptionConverter.class)
    private String forename;

    @Convert(converter = EncryptionConverter.class)
    private String middleName;

    @NotNull
    @Convert(converter = EncryptionConverter.class)
    private String lastName;

    @NotNull
    @Convert(converter = EncryptionConverter.class)
    private String fatherName;

    @Convert(converter = EncryptionConverter.class)
    private String motherName;

    @NotNull
    @Convert(converter = EncryptionConverter.class)
    private String dateOfBirth;

    @Convert(converter = EncryptionConverter.class)
    private String gender;

    @NotNull
    @Convert(converter = EncryptionConverter.class)
    private String address;

    @NotNull
    @Convert(converter = EncryptionConverter.class)
    private String phone;

    @Email
    @Convert(converter = EncryptionConverter.class)
    private String email;

    @Convert(converter = EncryptionConverter.class)
    private String aadhaarNumberLinked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pii_id", nullable = false)
    private Pii pii;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getAadhaarNumberLinked() {
		return aadhaarNumberLinked;
	}

	public void setAadhaarNumberLinked(String aadhaarNumberLinked) {
		this.aadhaarNumberLinked = aadhaarNumberLinked;
	}

	public Pii getPii() {
		return pii;
	}

	public void setPii(Pii pii) {
		this.pii = pii;
	}
    
    
}

