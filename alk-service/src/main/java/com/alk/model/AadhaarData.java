package com.alk.model;

import java.time.LocalDate;

import com.alk.converter.EncryptionConverter;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Convert;


@Entity
@Table(name = "aadhaar_data")
public class AadhaarData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 12, max = 12)
    @Column(unique = true)
    @Convert(converter = EncryptionConverter.class)
    private String aadhaarNumber;

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
    private LocalDate dob;

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
}
