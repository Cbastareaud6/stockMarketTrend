package com.CathyB.stockMarketTrend.Service;

import com.CathyB.stockMarketTrend.DTO.RegistrationRequest;
import com.CathyB.stockMarketTrend.Model.User;
import com.CathyB.stockMarketTrend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserRegistrationService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  public String registerUser(RegistrationRequest registrationRequest) {
    if (userRepository.findByUsername(registrationRequest.getUsername()).isPresent()) {
      return "Username already taken";
    }

    User user = new User();
    user.setUsername(registrationRequest.getUsername());
    user.setEmail(registrationRequest.getEmail());
    user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

    userRepository.save(user);

    return "User registered successfully";

  }


}
