package com.CathyB.stockMarketTrend.Controllers;


import com.CathyB.stockMarketTrend.DTO.AuthResponse;
import com.CathyB.stockMarketTrend.DTO.AuthorizationRequest;
import com.CathyB.stockMarketTrend.JWTUtil;
import com.CathyB.stockMarketTrend.Security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {

  @Autowired
  private  AuthenticationManager authenticationManager;
  @Autowired
  private  JWTUtil jwtUtil;
  @Autowired
  private CustomUserDetailsService customUserDetailsService;


  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@RequestBody AuthorizationRequest request){
    String username = request.getUsername();  // Lombok should generate this method
    String password = request.getPassword();  // Lombok should generate this method
    Authentication authentication = authenticationManager.authenticate(


    new UsernamePasswordAuthenticationToken(username, password));

   UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String token = jwtUtil.generateToken(userDetails);
    AuthResponse response = new AuthResponse(token);

    return ResponseEntity.ok(response);
  }
}
