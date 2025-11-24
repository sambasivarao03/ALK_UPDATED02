package com.alk.model;
import com.alk.model.Pii;
import java.time.LocalDate;

import com.alk.converter.EncryptionConverter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "voter_data")
public class VoterData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Convert(converter = EncryptionConverter.class)
    private String epicNumber;

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
    private String relativeName;

    @Lob
    @Convert(converter = EncryptionConverter.class)
    private byte[] photograph;

    @NotNull
    @Convert(converter = EncryptionConverter.class)
    private String dob;

    @NotNull
    @Convert(converter = EncryptionConverter.class)
    private String address;

    @Convert(converter = EncryptionConverter.class)
    private String electoralDistrict;

    @Convert(converter = EncryptionConverter.class)
    private String assemblyConstituency;

    @Convert(converter = EncryptionConverter.class)
    private String partNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pii_id", nullable = false)
    private Pii pii;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEpicNumber() {
		return epicNumber;
	}

	public void setEpicNumber(String epicNumber) {
		this.epicNumber = epicNumber;
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

	public String getRelativeName() {
		return relativeName;
	}

	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}

	public byte[] getPhotograph() {
		return photograph;
	}

	public void setPhotograph(byte[] photograph) {
		this.photograph = photograph;
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

	public String getElectoralDistrict() {
		return electoralDistrict;
	}

	public void setElectoralDistrict(String electoralDistrict) {
		this.electoralDistrict = electoralDistrict;
	}

	public String getAssemblyConstituency() {
		return assemblyConstituency;
	}

	public void setAssemblyConstituency(String assemblyConstituency) {
		this.assemblyConstituency = assemblyConstituency;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public Pii getPii() {
		return pii;
	}

	public void setPii(Pii pii) {
		this.pii = pii;
	}
    
    
    
    
}

