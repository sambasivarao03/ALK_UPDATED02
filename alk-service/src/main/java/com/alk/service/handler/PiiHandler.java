package com.alk.service.handler;

import com.alk.dto.PiiResponseDto;
import com.alk.model.Pii;
import com.alk.repository.PiiRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PiiHandler {

    private final PiiRepository piiRepository;

    public PiiHandler(PiiRepository piiRepository) {
        this.piiRepository = piiRepository;
    }

    public Pii createNew(String piiPk) {
        Pii pii = new Pii();
        pii.setPiiPk(piiPk);
        pii.setAlk(UUID.randomUUID());
        pii.setAadhaarCount(0);
        pii.setPanCount(0);
        pii.setVoterIdCount(0);
        pii.setDlCount(0);
        return piiRepository.save(pii);
    }

    public void increment(Pii pii, String source) {
        switch (source.toUpperCase()) {
            case "AADHAAR" -> pii.setAadhaarCount(pii.getAadhaarCount() + 1);
            case "PAN" -> pii.setPanCount(pii.getPanCount() + 1);
            case "VOTER" -> pii.setVoterIdCount(pii.getVoterIdCount() + 1);
            case "DRIVING" -> pii.setDlCount(pii.getDlCount() + 1);
        }
        piiRepository.save(pii);
    }

    public void decrement(Pii pii, String source) {
        switch (source.toUpperCase()) {
            case "AADHAAR" -> pii.setAadhaarCount(pii.getAadhaarCount() - 1);
            case "PAN" -> pii.setPanCount(pii.getPanCount() - 1);
            case "VOTER" -> pii.setVoterIdCount(pii.getVoterIdCount() - 1);
            case "DRIVING" -> pii.setDlCount(pii.getDlCount() - 1);
        }
        piiRepository.save(pii);
    }

    public boolean shouldDelete(Pii pii) {
        return pii.getAadhaarCount() == 0 &&
               pii.getPanCount() == 0 &&
               pii.getVoterIdCount() == 0 &&
               pii.getDlCount() == 0;
    }

    public PiiResponseDto toResponse(Pii pii) {
        PiiResponseDto dto = new PiiResponseDto();
        dto.setPiiPk(pii.getPiiPk());
        dto.setAadhaarLinkageKey(pii.getAlk().toString());
        dto.setAadhaarCount(pii.getAadhaarCount());
        dto.setPanCount(pii.getPanCount());
        dto.setVoterIdCount(pii.getVoterIdCount());
        dto.setDlCount(pii.getDlCount());
        return dto;
    }
}
