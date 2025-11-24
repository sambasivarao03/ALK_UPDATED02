package com.alk.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "pii")
public class Pii {

    @Id
    @Column(name = "pii_pk", nullable = false, unique = true)
    private String piiPk;  
    // SHA-256 hex of forename|lastname|dob|gender (computed in service layer)

    @Column(name = "alk", nullable = false, unique = true)
    private UUID alk;  
    // Aadhaar Linkage Key → UUID.randomUUID()

    @Column(name = "aadhaar_count", nullable = false)
    private int aadhaarCount = 0;

    @Column(name = "pan_count", nullable = false)
    private int panCount = 0;

    @Column(name = "voterid_count", nullable = false)
    private int voterIdCount = 0;

    @Column(name = "dl_count", nullable = false)
    private int dlCount = 0;

	public String getPiiPk() {
		return piiPk;
	}

	public void setPiiPk(String piiPk) {
		this.piiPk = piiPk;
	}

	public UUID getAlk() {
		return alk;
	}

	public void setAlk(UUID alk) {
		this.alk = alk;
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
    
    
}
