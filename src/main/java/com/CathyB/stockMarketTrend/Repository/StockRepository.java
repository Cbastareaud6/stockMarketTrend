package com.CathyB.stockMarketTrend.Repository;


import com.CathyB.stockMarketTrend.Model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository  extends JpaRepository<Stock,Long> {


  Stock findBySymbol(String ticker);

  boolean existsBySymbol(String ticker);





}

