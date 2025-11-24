package com.alk.service;

import com.alk.dto.ManageRequestDto;
import com.alk.dto.PiiResponseDto;
import com.alk.repository.DrivingLicenseRepository;
import com.alk.service.handler.ActionHandler;
import com.alk.service.handler.InsertHandler;
import com.alk.service.handler.UpdateHandler;
import com.alk.service.handler.DeleteHandler;
import com.alk.service.handler.SearchHandler;
import com.alk.service.validator.AadhaarValidator;
import com.alk.service.validator.DrivingLicenseValidator;
import com.alk.service.validator.PanValidator;
import com.alk.service.validator.VoterValidator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ManagePersonServiceImpl implements ManagePersonService {

    private final InsertHandler insertHandler;
    private final UpdateHandler updateHandler;
    private final DeleteHandler deleteHandler;
    private final SearchHandler searchHandler;

    private final AadhaarValidator aadhaarValidator;
    private final PanValidator panValidator;
    private final VoterValidator voterValidator;
    private final DrivingLicenseValidator drivingValidator;

    public ManagePersonServiceImpl(
            InsertHandler insertHandler,
            UpdateHandler updateHandler,
            DeleteHandler deleteHandler,
            SearchHandler searchHandler,
            AadhaarValidator aadhaarValidator,
            PanValidator panValidator,
            VoterValidator voterValidator,
            DrivingLicenseValidator drivingValidator
    ) {
        this.insertHandler = insertHandler;
        this.updateHandler = updateHandler;
        this.deleteHandler = deleteHandler;
        this.searchHandler = searchHandler;
        this.aadhaarValidator = aadhaarValidator;
        this.panValidator = panValidator;
        this.voterValidator = voterValidator;
        this.drivingValidator = drivingValidator;
    }

    @Override
    public PiiResponseDto manage(ManageRequestDto request) {
        if (request == null) throw new IllegalArgumentException("Request cannot be null");
        String action = request.getAction() == null ? null : request.getAction().strip().toLowerCase();
        String source = request.getSource() == null ? null : request.getSource().strip().toLowerCase();
        if (!StringUtils.hasText(action)) throw new IllegalArgumentException("action is required");
        if (!StringUtils.hasText(source)) throw new IllegalArgumentException("source is required");
        System.out.println("this is source:"+source);
        // pre-route validation: ensure source/action is supported
        switch (action.toUpperCase()) {
            case "INSERT":
                return withSourceValidation(source, request, insertHandler);
            case "UPDATE":
                return withSourceValidation(source, request, updateHandler);
            case "DELETE":
                return withSourceValidation(source, request, deleteHandler);
            case "SEARCH":
                return searchHandler.handle(request); // SEARCH uses only ALK; handler will validate
            default:
                throw new IllegalArgumentException("Unsupported action: " + action);
        }
    }

    // Calls appropriate source validator before invoking handler
    private PiiResponseDto withSourceValidation(String source, ManageRequestDto request, ActionHandler handler) {
        switch (source) {
            case "aadhaar":
                aadhaarValidator.validate(request, handler.getActionName());
                break;
            case "pan":
                panValidator.validate(request, handler.getActionName());
                break;
            case "voter":
                voterValidator.validate(request, handler.getActionName());
                break;
            case "driving":
                drivingValidator.validate(request, handler.getActionName());
                break;
            default:
                throw new IllegalArgumentException("Unsupported source: " + source);
        }
        return handler.handle(request);
    }
}
