package com.alk.model;

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
    private LocalDate dob;

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
}

