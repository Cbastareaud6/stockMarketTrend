package com.CathyB.stockMarketTrend.Service;

import com.CathyB.stockMarketTrend.Model.Stock;
import com.CathyB.stockMarketTrend.Model.StockData;
import com.CathyB.stockMarketTrend.Repository.StockRepository;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class StockUpdateScheduler {

private static final int RATE_LIMIT = 60;
private AtomicInteger requestCount = new AtomicInteger(0);


  @Autowired
 private StockService stockService;

  @Autowired
  private StockRepository stockRepository;

  @Scheduled(fixedRate = 60000)
  public void updateStockData(){
    List<Stock> trackedStocks = stockRepository.findAll();
        for (Stock stock : trackedStocks){
          if (requestCount.get() >= RATE_LIMIT) {
            break;
          }

          StockData stockData = stockService.getStockData(stock.getSymbol());
          if (stockData != null){
            stock.setCurrentPrice(stockData.getC());
            stockRepository.save(stock);
          }
        }
  }

}
