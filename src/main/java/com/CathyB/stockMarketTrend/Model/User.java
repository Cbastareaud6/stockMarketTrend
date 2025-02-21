package com.CathyB.stockMarketTrend.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jdk.jfr.DataAmount;
import lombok.Data;

@Entity
@Data
@Table(name= "users")
public class User {

  @Id
  private Long id;
  private String userName;
  private String password;
  private String role;


}
