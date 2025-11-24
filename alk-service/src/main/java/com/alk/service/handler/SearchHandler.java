package com.alk.service.handler;

import com.alk.dto.ManageRequestDto;
import com.alk.dto.PiiResponseDto;
import com.alk.model.Pii;
import com.alk.repository.PiiRepository;
import org.springframework.stereotype.Component;

@Component
public class SearchHandler implements ActionHandler {

    private final PiiRepository piiRepository;
    private final PiiHandler piiHandler;

    public SearchHandler(PiiRepository piiRepository, PiiHandler piiHandler) {
        this.piiRepository = piiRepository;
        this.piiHandler = piiHandler;
    }

    @Override
    public String getActionName() {
        return "SEARCH";
    }

    @Override
    public PiiResponseDto handle(ManageRequestDto request) {

        Pii pii = piiRepository.findByAlk(request.getAlk())
                .orElseThrow(() -> new RuntimeException("PII not found for ALK: " + request.getAlk()));

        return piiHandler.toResponse(pii);
    }
}
