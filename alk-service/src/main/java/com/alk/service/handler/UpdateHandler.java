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
public class UpdateHandler implements ActionHandler {

    private final PiiRepository piiRepository;
    private final PiiHandler piiHandler;

    private final AadhaarDataRepository aadhaarRepo;
    private final PanDataRepository panRepo;
    private final VoterDataRepository voterRepo;
    private final DrivingLicenseRepository dlRepo;

    public UpdateHandler(
            PiiRepository piiRepository,
            PiiHandler piiHandler,
            AadhaarDataRepository aadhaarRepo,
            PanDataRepository panRepo,
            VoterDataRepository voterRepo,
            DrivingLicenseRepository dlRepo
    ) {
        this.piiRepository = piiRepository;
        this.piiHandler = piiHandler;
        this.aadhaarRepo = aadhaarRepo;
        this.panRepo = panRepo;
        this.voterRepo = voterRepo;
        this.dlRepo = dlRepo;
    }

    @Override
    public String getActionName() {
        return "UPDATE";
    }

    @Override
    @Transactional
    public PiiResponseDto handle(ManageRequestDto request) {

        String source = request.getSource().toUpperCase();

        // 1️⃣ Lookup OLD PII
        Pii oldPii = piiRepository.findByAlk(request.getAlk())
                .orElseThrow(() -> new RuntimeException("PII not found for ALK: " + request.getAlk()));

        // 2️⃣ Extract person to compute NEW piiPk
        CommonPersonDto person = switch (source) {
            case "AADHAAR" -> request.getAadhaar().getPerson();
            case "PAN" -> request.getPan().getPerson();
            case "VOTER" -> request.getVoter().getPerson();
            case "DRIVING" -> request.getDriving().getPerson();
            default -> throw new RuntimeException("Unknown source: " + source);
        };

        String newPiiPk = PiiPkGenerator.compute(
                person.getForename(),
                person.getLastName(),
                person.getDob().toString(),
                person.getGender()
        );

        // 3️⃣ Create NEW PII
        Pii newPii = piiHandler.createNew(newPiiPk);

        // 4️⃣ Insert new data into new PII (same as insert handler)
        switch (source) {
            case "AADHAAR" -> insertAadhaar(request.getAadhaar(), newPii);
            case "PAN" -> insertPan(request.getPan(), newPii);
            case "VOTER" -> insertVoter(request.getVoter(), newPii);
            case "DRIVING" -> insertDriving(request.getDriving(), newPii);
        }

        // 5️⃣ Increment NEW PII count
        piiHandler.increment(newPii, source);

        // 6️⃣ Decrement OLD PII count
        piiHandler.decrement(oldPii, source);

        // 7️⃣ Delete OLD PII if counters become 0
        if (piiHandler.shouldDelete(oldPii)) {
            piiRepository.delete(oldPii);
        }

        return piiHandler.toResponse(newPii);
    }

    private void insertAadhaar(AadhaarRequestDto dto, Pii pii) {
        AadhaarData d = new AadhaarData();
        d.setAadhaarNumber(dto.getAadhaarNumber());
        d.setForename(dto.getPerson().getForename());
        d.setMiddleName(dto.getPerson().getMiddleName());
        d.setLastName(dto.getPerson().getLastName());
        d.setDob(dto.getPerson().getDob());
        d.setGender(dto.getPerson().getGender());
        d.setAddress(dto.getPerson().getAddress());
        d.setParentGuardianName(dto.getParentGuardianName());
        d.setMobileNumber(dto.getMobile());
        d.setEmail(dto.getEmail());
        d.setPii(pii);
        aadhaarRepo.save(d);
    }

    private void insertPan(PanRequestDto dto, Pii pii) {
        PanData d = new PanData();
        d.setPanNumber(dto.getPanNumber());
        d.setForename(dto.getPerson().getForename());
        d.setMiddleName(dto.getPerson().getMiddleName());
        d.setLastName(dto.getPerson().getLastName());
        d.setFatherName(dto.getFatherName());
        d.setMotherName(dto.getMotherName());
        d.setDateOfBirth(dto.getPerson().getDob());
        d.setGender(dto.getPerson().getGender());
        d.setAddress(dto.getPerson().getAddress());
        d.setPhone(dto.getPhone());
        d.setEmail(dto.getEmail());
        d.setAadhaarNumberLinked(dto.getAadhaarLinked());
        d.setPii(pii);
        panRepo.save(d);
    }

    private void insertVoter(VoterRequestDto dto, Pii pii) {
        VoterData d = new VoterData();
        d.setEpicNumber(dto.getEpicNumber());
        d.setForename(dto.getPerson().getForename());
        d.setMiddleName(dto.getPerson().getMiddleName());
        d.setLastName(dto.getPerson().getLastName());
        d.setDob(dto.getPerson().getDob());
        d.setAddress(dto.getPerson().getAddress());
        d.setRelativeName(dto.getRelativeName());
        d.setPhotograph(dto.getPhotograph());
        d.setAssemblyConstituency(dto.getAssemblyConstituency());
        d.setElectoralDistrict(dto.getElectoralDistrict());
        d.setPartNumber(dto.getPartNumber());
        d.setPii(pii);
        voterRepo.save(d);
    }

    private void insertDriving(DrivingLicenseRequestDto dto, Pii pii) {
        DrivingLicense d = new DrivingLicense();
        d.setDlNumber(dto.getDlNumber());
        d.setForename(dto.getPerson().getForename());
        d.setMiddleName(dto.getPerson().getMiddleName());
        d.setLastName(dto.getPerson().getLastName());
        d.setDob(dto.getPerson().getDob());
        d.setAddress(dto.getPerson().getAddress());
        d.setLicenseClass(dto.getLicenseClass());
        d.setHeight(dto.getHeight());
        d.setBloodType(dto.getBloodType());
        d.setIssueDate(dto.getIssueDate());
        d.setExpiryDate(dto.getExpiryDate());
        d.setGender(dto.getPerson().getGender());
        d.setPii(pii);
        dlRepo.save(d);
    }
}
