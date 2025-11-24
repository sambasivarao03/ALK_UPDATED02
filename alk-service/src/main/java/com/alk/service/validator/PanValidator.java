package com.alk.service.validator;

import com.alk.dto.CommonPersonDto;
import com.alk.dto.ManageRequestDto;
import com.alk.dto.datasource.PanRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PanValidator implements SourceValidator {

    @Override
    public void validate(ManageRequestDto request, String action) {
        if ("INSERT".equalsIgnoreCase(action) || "UPDATE".equalsIgnoreCase(action)) {
            PanRequestDto dto = request.getPan();
            ValidationUtils.requireNotNull(dto, "PAN.data");

            String pan = dto.getPanNumber();
            if (!StringUtils.hasText(pan)) {
                throw new IllegalArgumentException("PAN.panNumber is required");
            }
            if (pan.length() != 10 || !ValidationUtils.isAlphanumeric(pan)) {
                throw new IllegalArgumentException("PAN.panNumber must be 10 alphanumeric characters");
            }

            CommonPersonDto p = dto.getPerson();
            ValidationUtils.requireNotNull(p, "PAN.person");
            ValidationUtils.requireString(p.getForename(), "PAN.person.forename");
            ValidationUtils.requireString(p.getLastName(), "PAN.person.lastName");
            ValidationUtils.requireDob(p.getDob(), "PAN.person.dob");

            ValidationUtils.requireString(dto.getFatherName(), "PAN.fatherName"); // mandatory per spec/model
            ValidationUtils.requireString(dto.getPhone(), "PAN.phone"); // model marks phone @NotNull
//            ValidationUtils.requireString(dto.getAddress(), "PAN.address"); // model @NotNull

            ValidationUtils.requireValidEmailIfPresent(dto.getEmail(), "PAN.email");
            // aadhaarLinked optional (if present must be 12 digits or a hashed value depending on flow)
        } else if ("DELETE".equalsIgnoreCase(action)) {
            String alk = extractAlk(request);
            ValidationUtils.requireAadhaarLinkageKeyPresent(alk);
        } else {
            // SEARCH handled by SearchHandler/ SearchValidator if needed; but allow SEARCH via generic check
            if ("SEARCH".equalsIgnoreCase(action)) {
                String alk = extractAlk(request);
                ValidationUtils.requireAadhaarLinkageKeyPresent(alk);
            } else {
                throw new IllegalArgumentException("PAN: unsupported action " + action);
            }
        }
    }

    private String extractAlk(ManageRequestDto request) {
        try {
            java.lang.reflect.Method m = request.getClass().getMethod("getOldAadhaarLinkageKey");
            Object val = m.invoke(request);
            return val == null ? null : String.valueOf(val);
        } catch (NoSuchMethodException ns) {
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
