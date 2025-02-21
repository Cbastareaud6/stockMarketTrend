package com.CathyB.stockMarketTrend.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;






@Component
public class PasswordEncoderRunner implements CommandLineRunner {

  // Inject the existing PasswordEncoder bean from SecurityConfig
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {
    // Define the plain password you want to hash
    String plainPassword = "test123";

    // Use the injected PasswordEncoder to hash the password
    String hashedPassword = passwordEncoder.encode(plainPassword);

    // Output the hashed password to the console
    System.out.println("Running PasswordEncoder");
    System.out.println("Hashed password: " + hashedPassword);
  }
}
