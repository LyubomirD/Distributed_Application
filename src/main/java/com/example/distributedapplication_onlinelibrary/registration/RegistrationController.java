package com.example.distributedapplication_onlinelibrary.registration;

import com.example.distributedapplication_onlinelibrary.mapper.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/client")
    public String registerClient(@RequestBody UserDto request) {
        return registrationService.registerClient(request);
    }
    @PostMapping("/administrator")
    public String registerAdministrator(@RequestBody UserDto request) {
        return registrationService.registerAdministrator(request);
    }
    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
