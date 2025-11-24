package com.alk.service;

import com.alk.dto.ManageRequestDto;
import com.alk.dto.PiiResponseDto;

public interface ManagePersonService {
    PiiResponseDto manage(ManageRequestDto request);
}
