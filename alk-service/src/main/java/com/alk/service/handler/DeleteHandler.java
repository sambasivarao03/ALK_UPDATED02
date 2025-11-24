package com.alk.service.handler;

import com.alk.dto.ManageRequestDto;
import com.alk.dto.PiiResponseDto;
import com.alk.model.Pii;
import com.alk.repository.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteHandler implements ActionHandler {

    private final PiiRepository piiRepository;
    private final PiiHandler piiHandler;

    private final AadhaarDataRepository aadhaarRepo;
    private final PanDataRepository panRepo;
    private final VoterDataRepository voterRepo;
    private final DrivingLicenseRepository dlRepo;

    public DeleteHandler(
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
        return "DELETE";
    }

    @Override
    @Transactional
    public PiiResponseDto handle(ManageRequestDto request) {

        String source = request.getSource().toUpperCase();

        // 1️⃣ Find PII by ALK
        Pii pii = piiRepository.findByAlk(request.getAlk())
                .orElseThrow(() -> new RuntimeException("PII not found for ALK: " + request.getAlk()));

        // 2️⃣ Delete source rows
        switch (source) {
            case "AADHAAR" -> aadhaarRepo.deleteAll(aadhaarRepo.findByPii_PiiPk(pii.getPiiPk()));
            case "PAN" -> panRepo.deleteAll(panRepo.findByPii_PiiPk(pii.getPiiPk()));
            case "VOTER" -> voterRepo.deleteAll(voterRepo.findByPii_PiiPk(pii.getPiiPk()));
            case "DRIVING" -> dlRepo.deleteAll(dlRepo.findByPii_PiiPk(pii.getPiiPk()));
        }

        // 3️⃣ Decrement counter
        piiHandler.decrement(pii, source);

        // 4️⃣ Delete PII if all counters = 0
        if (piiHandler.shouldDelete(pii)) {
            piiRepository.delete(pii);
        }

        return piiHandler.toResponse(pii);
    }
}
