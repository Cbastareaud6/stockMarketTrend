package com.CathyB.stockMarketTrend.Controllers;


import com.CathyB.stockMarketTrend.Model.Stock;
import com.CathyB.stockMarketTrend.Model.User;
import com.CathyB.stockMarketTrend.Repository.StockRepository;
import com.CathyB.stockMarketTrend.Repository.UserRepository;
import com.CathyB.stockMarketTrend.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stocks")
public class StockController {


  @Autowired
  private StockService stockService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private StockRepository stockRepository;


  @PostMapping("/add")
public Stock addStock(@RequestBody Stock stock){
    return stockService.addOrUpdateStock(stock);
  }
  @PostMapping("/track/{userId}/{ticker}")
  public String trackStock(@PathVariable Long userId, @PathVariable String ticker){
    User user = userRepository.findById(userId)
        .orElseThrow(()-> new RuntimeException("USer not found"));

    Stock stock = stockRepository.findByTicker(ticker);
    if (stock == null) {
      return "Stock not found";
    }

    return stockService.trackStock(user, stock);
  }

  @PostMapping("/untrack/{userId}/{ticker}")
  public String unTrackStock(@PathVariable Long userId, @PathVariable String ticker){
    User user = userRepository.findById(userId)
        .orElseThrow(()-> new RuntimeException("USer not found"));

    Stock stock = stockRepository.findByTicker(ticker);
    if (stock == null) {
      return "Stock not found";
    }

    return stockService.deleteTracking(user, stock);
  }
}
