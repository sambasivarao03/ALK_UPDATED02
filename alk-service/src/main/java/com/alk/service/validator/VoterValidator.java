package com.alk.service.validator;

import com.alk.dto.CommonPersonDto;
import com.alk.dto.ManageRequestDto;
import com.alk.dto.datasource.VoterRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class VoterValidator implements SourceValidator {

    @Override
    public void validate(ManageRequestDto request, String action) {
        if ("INSERT".equalsIgnoreCase(action) || "UPDATE".equalsIgnoreCase(action)) {
            VoterRequestDto dto = request.getVoter();
            ValidationUtils.requireNotNull(dto, "Voter.data");

            ValidationUtils.requireString(dto.getEpicNumber(), "Voter.epicNumber");

            CommonPersonDto p = dto.getPerson();
            ValidationUtils.requireNotNull(p, "Voter.person");
            ValidationUtils.requireString(p.getForename(), "Voter.person.forename");
            ValidationUtils.requireString(p.getLastName(), "Voter.person.lastName");
            ValidationUtils.requireDob(p.getDob(), "Voter.person.dob");

            ValidationUtils.requireString(dto.getRelativeName(), "Voter.relativeName");
//            ValidationUtils.requireString(dto.getAddress(), "Voter.address"); // model @NotNull

            // electoral district / assembly / partNumber optional
            // photograph is optional (byte[]), if provided ensure non-empty
            if (dto.getPhotograph() != null && dto.getPhotograph().length == 0) {
                throw new IllegalArgumentException("Voter.photograph if provided must be non-empty");
            }
        } else if ("DELETE".equalsIgnoreCase(action) || "SEARCH".equalsIgnoreCase(action)) {
            String alk = extractAlk(request);
            ValidationUtils.requireAadhaarLinkageKeyPresent(alk);
        } else {
            throw new IllegalArgumentException("Voter: unsupported action " + action);
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
