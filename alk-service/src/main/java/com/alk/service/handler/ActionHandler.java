package com.alk.service.handler;

import com.alk.dto.ManageRequestDto;
import com.alk.dto.PiiResponseDto;

// action handlers implement this; getActionName returns "INSERT"/"UPDATE"/...
public interface ActionHandler {
    PiiResponseDto handle(ManageRequestDto request);
    String getActionName();
}
