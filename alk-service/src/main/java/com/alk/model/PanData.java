package com.alk.model;

import java.time.LocalDate;

import com.alk.converter.EncryptionConverter;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
    private LocalDate dateOfBirth;

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
}

