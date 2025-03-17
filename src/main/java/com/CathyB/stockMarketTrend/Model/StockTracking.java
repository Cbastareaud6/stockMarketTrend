package com.CathyB.stockMarketTrend.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "stock_tracking")
public class StockTracking {



  @Id
  @GeneratedValue
  private Long id;


  @ManyToOne
  @JoinColumn(name = "stock_id", referencedColumnName = "id")
  private User user;


  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private Stock stock;

  private Boolean isFavorite;

}
