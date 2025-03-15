package com.CathyB.stockMarketTrend.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="stocks")
public class Stock {

  @Id
  @GeneratedValue
  private Long id;


  @Column(name = "ticker", nullable = false, unique = true)
  private String ticker;

  private String name;
  private double currentPrice;
  // add other fields as needed;




}
