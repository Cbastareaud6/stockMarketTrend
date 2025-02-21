package com.CathyB.stockMarketTrend.DTO;


import lombok.Data;
import lombok.Getter;

@Data
public class AuthorizationRequest {


  private String username;


  private String password;

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

}
