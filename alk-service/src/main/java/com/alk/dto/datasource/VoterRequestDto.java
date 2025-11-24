package com.alk.dto.datasource;

import com.alk.dto.CommonPersonDto;
import java.time.LocalDate;

public class VoterRequestDto {

    private String epicNumber;
    private CommonPersonDto person;

    private String relativeName;
    private byte[] photograph;
    private String electoralDistrict;
    private String assemblyConstituency;
    private String partNumber;
	public String getEpicNumber() {
		return epicNumber;
	}
	public void setEpicNumber(String epicNumber) {
		this.epicNumber = epicNumber;
	}
	public CommonPersonDto getPerson() {
		return person;
	}
	public void setPerson(CommonPersonDto person) {
		this.person = person;
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

    // getters + setters
    
}
