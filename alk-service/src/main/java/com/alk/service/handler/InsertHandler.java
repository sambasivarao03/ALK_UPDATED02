package com.alk.service.handler;

import com.alk.dto.CommonPersonDto;
import com.alk.dto.ManageRequestDto;
import com.alk.dto.PiiResponseDto;
import com.alk.dto.datasource.*;
import com.alk.model.*;
import com.alk.repository.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InsertHandler implements ActionHandler {

    private final PiiHandler piiHandler;
    private final AadhaarDataRepository aadhaarRepo;
    private final PanDataRepository panRepo;
    private final VoterDataRepository voterRepo;
    private final DrivingLicenseRepository dlRepo;
    private final PiiRepository piiRepository;

    public InsertHandler(
            PiiHandler piiHandler,
            AadhaarDataRepository aadhaarRepo,
            PanDataRepository panRepo,
            VoterDataRepository voterRepo,
            DrivingLicenseRepository dlRepo,
            PiiRepository piiRepository
    ) {
        this.piiHandler = piiHandler;
        this.aadhaarRepo = aadhaarRepo;
        this.panRepo = panRepo;
        this.voterRepo = voterRepo;
        this.dlRepo = dlRepo;
        this.piiRepository = piiRepository;
    }

    @Override
    public String getActionName() {
        return "INSERT";
    }

    @Override
    @Transactional
    public PiiResponseDto handle(ManageRequestDto request) {

        String source = request.getSource().toUpperCase();

        CommonPersonDto person = switch (source) {
            case "AADHAAR" -> request.getAadhaar().getPerson();
            case "PAN" -> request.getPan().getPerson();
            case "VOTER" -> request.getVoter().getPerson();
            case "DRIVING" -> request.getDriving().getPerson();
            default -> throw new IllegalArgumentException("Unknown source: " + source);
        };

        // 1️⃣ Generate piiPk
        String piiPk = PiiPkGenerator.compute(
                person.getForename(),
                person.getLastName(),
                person.getDob().toString(),
                person.getGender()
        );

        // 2️⃣ Lookup or create PII
        Pii pii = piiRepository.findByPiiPk(piiPk)
                .orElseGet(() -> piiHandler.createNew(piiPk));

        // 3️⃣ Save datasource object
        switch (source) {

            case "AADHAAR" -> insertAadhaar(request.getAadhaar(), pii);

            case "PAN" -> insertPan(request.getPan(), pii);

            case "VOTER" -> insertVoter(request.getVoter(), pii);

            case "DRIVING" -> insertDriving(request.getDriving(), pii);
        }

        // 4️⃣ Increment PII count
        piiHandler.increment(pii, source);

        return piiHandler.toResponse(pii);
    }

    private void insertAadhaar(AadhaarRequestDto dto, Pii pii) {
        AadhaarData data = new AadhaarData();
        data.setAadhaarNumber(dto.getAadhaarNumber());
        data.setForename(dto.getPerson().getForename());
        data.setMiddleName(dto.getPerson().getMiddleName());
        data.setLastName(dto.getPerson().getLastName());
        data.setDob(dto.getPerson().getDob());
        data.setGender(dto.getPerson().getGender());
        data.setAddress(dto.getPerson().getAddress());
        data.setParentGuardianName(dto.getParentGuardianName());
        data.setMobileNumber(dto.getMobile());
        data.setEmail(dto.getEmail());
        data.setPii(pii);
        aadhaarRepo.save(data);
    }
    
    private void insertPan(PanRequestDto dto, Pii pii) {
        PanData data = new PanData();
        data.setPanNumber(dto.getPanNumber());
        data.setForename(dto.getPerson().getForename());
        data.setMiddleName(dto.getPerson().getMiddleName());
        data.setLastName(dto.getPerson().getLastName());
        data.setFatherName(dto.getFatherName());
        data.setMotherName(dto.getMotherName());
        data.setDateOfBirth(dto.getPerson().getDob());
        data.setGender(dto.getPerson().getGender());
        data.setAddress(dto.getPerson().getAddress());
        data.setPhone(dto.getPhone());
        data.setEmail(dto.getEmail());
        data.setAadhaarNumberLinked(dto.getAadhaarLinked());
        data.setPii(pii);
        panRepo.save(data);
    }

    private void insertVoter(VoterRequestDto dto, Pii pii) {
        VoterData data = new VoterData();
        data.setEpicNumber(dto.getEpicNumber());
        data.setForename(dto.getPerson().getForename());
        data.setMiddleName(dto.getPerson().getMiddleName());
        data.setLastName(dto.getPerson().getLastName());
        data.setDob(dto.getPerson().getDob());
        data.setAddress(dto.getPerson().getAddress());
        data.setRelativeName(dto.getRelativeName());
        data.setPhotograph(dto.getPhotograph());
        data.setAssemblyConstituency(dto.getAssemblyConstituency());
        data.setElectoralDistrict(dto.getElectoralDistrict());
        data.setPartNumber(dto.getPartNumber());
        data.setPii(pii);
        voterRepo.save(data);
    }

    private void insertDriving(DrivingLicenseRequestDto dto, Pii pii) {
        DrivingLicense data = new DrivingLicense();
        data.setDlNumber(dto.getDlNumber());
        data.setForename(dto.getPerson().getForename());
        data.setMiddleName(dto.getPerson().getMiddleName());
        data.setLastName(dto.getPerson().getLastName());
        data.setDob(dto.getPerson().getDob());
        data.setAddress(dto.getPerson().getAddress());
        data.setLicenseClass(dto.getLicenseClass());
        data.setHeight(dto.getHeight());
        data.setBloodType(dto.getBloodType());
        data.setIssueDate(dto.getIssueDate());
        data.setExpiryDate(dto.getExpiryDate());
        data.setGender(dto.getPerson().getGender());
        data.setPii(pii);
        dlRepo.save(data);
    }
}
