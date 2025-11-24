package com.alk.dto;

import java.util.UUID;

import com.alk.dto.datasource.*;

public class ManageRequestDto {

    private String action;   // INSERT, UPDATE, DELETE, SEARCH
    private String source;   // Aadhaar, PAN, Voter, Driving
    private UUID alk;


    private AadhaarRequestDto aadhaar;
    private PanRequestDto pan;
    private VoterRequestDto voter;
    private DrivingLicenseRequestDto driving;

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public AadhaarRequestDto getAadhaar() { return aadhaar; }
    public void setAadhaar(AadhaarRequestDto aadhaar) { this.aadhaar = aadhaar; }

    public PanRequestDto getPan() { return pan; }
    public void setPan(PanRequestDto pan) { this.pan = pan; }

    public VoterRequestDto getVoter() { return voter; }
    public void setVoter(VoterRequestDto voter) { this.voter = voter; }

    public DrivingLicenseRequestDto getDriving() { return driving; }
    public void setDriving(DrivingLicenseRequestDto driving) { this.driving = driving; }
    public UUID getAlk() {
    	return alk;
    }

}
