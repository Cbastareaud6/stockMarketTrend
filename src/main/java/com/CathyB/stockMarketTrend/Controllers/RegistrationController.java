package com.CathyB.stockMarketTrend.Controllers;

import com.CathyB.stockMarketTrend.DTO.RegistrationRequest;
import com.CathyB.stockMarketTrend.Service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class RegistrationController {


  @Autowired
  private UserRegistrationService userRegistrationService;


  @PostMapping("/register")
  public String register(@RequestBody RegistrationRequest registrationRequest) {
    return userRegistrationService.registerUser(registrationRequest);
  }
}
