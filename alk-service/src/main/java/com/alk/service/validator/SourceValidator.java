package com.alk.service.validator;

import com.alk.dto.ManageRequestDto;

/**
 * Validator contract for source-specific validation.
 * Implementations must throw IllegalArgumentException on validation failure.
 */
public interface SourceValidator {
    /**
     * Validate the incoming ManageRequestDto for the given action (INSERT, UPDATE, DELETE, SEARCH).
     * Throw IllegalArgumentException with clear source+field messages on failure.
     */
    void validate(ManageRequestDto request, String action);
}
