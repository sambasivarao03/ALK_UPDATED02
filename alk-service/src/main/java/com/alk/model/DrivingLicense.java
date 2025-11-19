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
    private LocalDate dob;

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
}

