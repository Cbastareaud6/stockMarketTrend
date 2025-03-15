package com.CathyB.stockMarketTrend.Repository;

import com.CathyB.stockMarketTrend.Model.StockTracking;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StockTrackingRepository extends JpaRepository<StockTracking, Long> {

  List<StockTracking> findByUserId(Long userId);
  StockTracking findByUserIdAndStockId(Long userId, Long stockId);
}
