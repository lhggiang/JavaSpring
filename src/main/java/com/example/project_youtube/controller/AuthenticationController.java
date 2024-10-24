package com.example.project_youtube.controller;

import com.example.project_youtube.dtos.reponses.APIResponse;
import com.example.project_youtube.dtos.reponses.AuthenticationResponse;
import com.example.project_youtube.dtos.reponses.IntrospectResponse;
import com.example.project_youtube.dtos.requests.AuthenticationRequest;
import com.example.project_youtube.dtos.requests.IntrospectRequest;
import com.example.project_youtube.service.AuthencationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthencationService authencationService;
    @PostMapping("/log-in")
    APIResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws JOSEException {
        AuthenticationResponse result = authencationService.authenticated(request);
          return APIResponse.<AuthenticationResponse>builder()
                  .result(result)
                  .build();
    }

    @PostMapping("/introspect")
    APIResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        IntrospectResponse result = authencationService.introspect(request);
        return APIResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
}
