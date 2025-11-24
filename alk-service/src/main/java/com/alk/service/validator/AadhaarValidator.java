package com.alk.service.validator;

import com.alk.dto.CommonPersonDto;
import com.alk.dto.ManageRequestDto;
import com.alk.dto.datasource.AadhaarRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AadhaarValidator implements SourceValidator {

    @Override
    public void validate(ManageRequestDto request, String action) {
        // action could be INSERT, UPDATE, DELETE, SEARCH
        if ("INSERT".equalsIgnoreCase(action) || "UPDATE".equalsIgnoreCase(action)) {
            AadhaarRequestDto dto = request.getAadhaar();
            ValidationUtils.requireNotNull(dto, "Aadhaar.data");

            String aad = dto.getAadhaarNumber();
            if (!StringUtils.hasText(aad)) {
                throw new IllegalArgumentException("Aadhaar.aadhaarNumber is required");
            }
            if (aad.length() != 12 || !ValidationUtils.isDigits(aad)) {
                throw new IllegalArgumentException("Aadhaar.aadhaarNumber must be a 12-digit numeric string");
            }

            CommonPersonDto p = dto.getPerson();
            ValidationUtils.requireNotNull(p, "Aadhaar.person");
            ValidationUtils.requireString(p.getForename(), "Aadhaar.person.forename");
            ValidationUtils.requireString(p.getLastName(), "Aadhaar.person.lastName");
            ValidationUtils.requireDob(p.getDob(), "Aadhaar.person.dob");
            // address is @NotNull in model -> require it
            ValidationUtils.requireString(p.getAddress(), "Aadhaar.person.address");

            // optional checks
            ValidationUtils.requireValidEmailIfPresent(dto.getEmail(), "Aadhaar.email");
            if (StringUtils.hasText(dto.getMobile())) {
                String m = dto.getMobile();
                if (!ValidationUtils.isDigits(m) || (m.length() < 7 || m.length() > 15)) {
                    throw new IllegalArgumentException("Aadhaar.mobile: if present must be digits (7-15 chars)");
                }
            }
        } else if ("DELETE".equalsIgnoreCase(action) || "SEARCH".equalsIgnoreCase(action)) {
            // require ALK present on request (spec uses aadhaarLinkageKey / oldAadhaarLinkageKey)
            // We expect ManageRequestDto to expose the ALK via a getter (oldAadhaarLinkageKey)
            String alk = extractAlk(request);
            ValidationUtils.requireAadhaarLinkageKeyPresent(alk);
        } else {
            throw new IllegalArgumentException("Aadhaar: unsupported action " + action);
        }
    }

    // helper: try common getters used in your DTOs. If your ManageRequestDto uses a different field,
    // update this method accordingly.
    private String extractAlk(ManageRequestDto request) {
        try {
            // prefer oldAadhaarLinkageKey if present
            java.lang.reflect.Method m = request.getClass().getMethod("getOldAadhaarLinkageKey");
            Object val = m.invoke(request);
            return val == null ? null : String.valueOf(val);
        } catch (NoSuchMethodException ns) {
            // fall back to aadhaarLinkageKey
            try {
                java.lang.reflect.Method m2 = request.getClass().getMethod("getAadhaarLinkageKey");
                Object val = m2.invoke(request);
                return val == null ? null : String.valueOf(val);
            } catch (Exception ex) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
