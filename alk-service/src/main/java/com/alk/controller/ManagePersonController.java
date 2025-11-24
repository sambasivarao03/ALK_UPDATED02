package com.alk.controller;

import com.alk.dto.ManageRequestDto;
import com.alk.dto.PiiResponseDto;
import com.alk.service.ManagePersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/person")
public class ManagePersonController {

    private final ManagePersonService managePersonService;

    public ManagePersonController(ManagePersonService managePersonService) {
        this.managePersonService = managePersonService;
    }

    /**
     * Single endpoint for INSERT / UPDATE / DELETE / SEARCH
     */
    @PostMapping("/manage")
    public ResponseEntity<PiiResponseDto> manage(@RequestBody ManageRequestDto request) {
        PiiResponseDto response = managePersonService.manage(request);
        return ResponseEntity.ok(response);
    }
}
