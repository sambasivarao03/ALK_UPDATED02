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
}
