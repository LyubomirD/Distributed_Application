package com.example.distributedapplication_onlinelibrary.registration;

import com.example.distributedapplication_onlinelibrary.mapper.dto.UserModelDto;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/client")
    public String registerClient(@RequestBody UserModelDto request) {
        return registrationService.registerClient(request);
    }
    @PostMapping("/administrator")
    public String registerAdministrator(@RequestBody UserModelDto request) {
        return registrationService.registerAdministrator(request);
    }
    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
