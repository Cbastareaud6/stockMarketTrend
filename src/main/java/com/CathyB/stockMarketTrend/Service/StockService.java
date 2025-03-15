package com.CathyB.stockMarketTrend.Service;


import com.CathyB.stockMarketTrend.Model.Stock;
import com.CathyB.stockMarketTrend.Model.StockTracking;
import com.CathyB.stockMarketTrend.Model.User;
import com.CathyB.stockMarketTrend.Repository.StockRepository;
import com.CathyB.stockMarketTrend.Repository.StockTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {


  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private StockTrackingRepository stockTrackingRepository;

  public Stock addOrUpdateStock(Stock stock){
    return stockRepository.save(stock);
  }

  public String trackStock(User user, Stock stock){
    StockTracking tracking = stockTrackingRepository.findByUserIdAndStockId(user.getId(),
        stock.getId());

    if (tracking != null) {
      return "Already being tracked";

    }
    StockTracking stockTracking = new StockTracking();
    stockTracking.setUser(user);
    stockTracking.setStock(stock);
    stockTracking.setIsFavorite(true);
    stockTrackingRepository.save(stockTracking);

    return "You are tracking this stock";
  }

  public String deleteTracking(User user, Stock stock){

    StockTracking stockTracking = stockTrackingRepository.findByUserIdAndStockId(user.getId(),
        stock.getId());

    if (stockTracking == null) {
      return "Stock not tracked";
    } else {
      stockTrackingRepository.delete(stockTracking);
      return "Successfully deleted";
    }
  }
}
