package com.alk.model;

import java.time.LocalDate;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Convert;
import lombok.*;
import com.alk.converter.EncryptionConverter;



@Entity
@Data
@NoArgsConstructor
@Table(name = "aadhaar_data")
public class AadhaarData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 12, max = 12)
    @Column(unique = true)
    @Convert(converter = EncryptionConverter.class)
    private  String aadhaarNumber;

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
    private String dob;

    @NotNull
    @Convert(converter = EncryptionConverter.class)
    private String gender;

    @NotNull
    @Convert(converter = EncryptionConverter.class)
    private String address;

    @Convert(converter = EncryptionConverter.class)
    private String parentGuardianName;

    @Convert(converter = EncryptionConverter.class)
    private String mobileNumber;

    @Email
    @Convert(converter = EncryptionConverter.class)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pii_id", nullable = false)
    private Pii pii;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
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

	public String getParentGuardianName() {
		return parentGuardianName;
	}

	public void setParentGuardianName(String parentGuardianName) {
		this.parentGuardianName = parentGuardianName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Pii getPii() {
		return pii;
	}

	public void setPii(Pii pii) {
		this.pii = pii;
	}
    
    
    
    
}
