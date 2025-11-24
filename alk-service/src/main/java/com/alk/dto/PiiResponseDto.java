package com.alk.dto;

public class PiiResponseDto {

    private String piiPk;             // hashed PII identifier
    private String aadhaarLinkageKey; // ALK (UUID)

    private int aadhaarCount;
    private int panCount;
    private int voterIdCount;
    private int dlCount;
	public String getPiiPk() {
		return piiPk;
	}
	public void setPiiPk(String piiPk) {
		this.piiPk = piiPk;
	}
	public String getAadhaarLinkageKey() {
		return aadhaarLinkageKey;
	}
	public void setAadhaarLinkageKey(String aadhaarLinkageKey) {
		this.aadhaarLinkageKey = aadhaarLinkageKey;
	}
	public int getAadhaarCount() {
		return aadhaarCount;
	}
	public void setAadhaarCount(int aadhaarCount) {
		this.aadhaarCount = aadhaarCount;
	}
	public int getPanCount() {
		return panCount;
	}
	public void setPanCount(int panCount) {
		this.panCount = panCount;
	}
	public int getVoterIdCount() {
		return voterIdCount;
	}
	public void setVoterIdCount(int voterIdCount) {
		this.voterIdCount = voterIdCount;
	}
	public int getDlCount() {
		return dlCount;
	}
	public void setDlCount(int dlCount) {
		this.dlCount = dlCount;
	}

    // getters + setters
    
}
