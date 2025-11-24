package com.alk.service.validator;

import com.alk.dto.CommonPersonDto;
import com.alk.dto.ManageRequestDto;
import com.alk.dto.datasource.DrivingLicenseRequestDto;
import org.springframework.stereotype.Component;

@Component
public class DrivingLicenseValidator implements SourceValidator {

    @Override
    public void validate(ManageRequestDto request, String action) {
        if ("INSERT".equalsIgnoreCase(action) || "UPDATE".equalsIgnoreCase(action)) {
            DrivingLicenseRequestDto dto = request.getDriving();
            ValidationUtils.requireNotNull(dto, "Driving.data");

            ValidationUtils.requireString(dto.getDlNumber(), "Driving.dlNumber");

            CommonPersonDto p = dto.getPerson();
            ValidationUtils.requireNotNull(p, "Driving.person");
            ValidationUtils.requireString(p.getForename(), "Driving.person.forename");
            ValidationUtils.requireString(p.getLastName(), "Driving.person.lastName");
            ValidationUtils.requireDob(p.getDob(), "Driving.person.dob");

            ValidationUtils.requireNotNull(dto.getIssueDate(), "Driving.issueDate");
            ValidationUtils.requireNotNull(dto.getExpiryDate(), "Driving.expiryDate");

            // expiry must be after issue if both present
            if (dto.getIssueDate() != null && dto.getExpiryDate() != null) {
                if (!dto.getExpiryDate().isAfter(dto.getIssueDate())) {
                    throw new IllegalArgumentException("Driving.expiryDate must be after issueDate");
                }
            }

            ValidationUtils.requireString(dto.getLicenseClass(), "Driving.licenseClass");

            // height and bloodType optional (no checks)
        } else if ("DELETE".equalsIgnoreCase(action) || "SEARCH".equalsIgnoreCase(action)) {
            String alk = extractAlk(request);
            ValidationUtils.requireAadhaarLinkageKeyPresent(alk);
        } else {
            throw new IllegalArgumentException("Driving: unsupported action " + action);
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
