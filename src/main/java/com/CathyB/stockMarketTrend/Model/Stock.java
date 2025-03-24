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

  // "currency": "USD",
  //        "description": "GREEN STREET CAPITAL CORP",
  //        "displaySymbol": "JAGR",
  //        "figi": "BBG000BC6BZ1",
  //        "isin": null,
  //        "mic": "OOTC",
  //        "shareClassFIGI": "BBG001S5S095",
  //        "symbol": "JAGR",
  //        "symbol2": "",
  //        "type": "Common Stock"
  //    },

  @Id
  @GeneratedValue
  private Long id;

  private String description;


  @Column(name = "symbol", nullable = false, unique = true)
  private String symbol;

  private String name;
  
  private double currentPrice;
  // add other fields as needed;




}
