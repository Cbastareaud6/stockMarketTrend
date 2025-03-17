package com.CathyB.stockMarketTrend.Controllers;


import com.CathyB.stockMarketTrend.Model.Stock;
import com.CathyB.stockMarketTrend.Model.User;
import com.CathyB.stockMarketTrend.Repository.StockRepository;
import com.CathyB.stockMarketTrend.Repository.StockTrackingRepository;
import com.CathyB.stockMarketTrend.Repository.UserRepository;
import com.CathyB.stockMarketTrend.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

  @Autowired
  private StockTrackingRepository stockTrackingRepository;



  @PostMapping("/add")
  @PreAuthorize("isAuthenticated()")
public Stock addStock(@RequestBody Stock stock){
    System.out.println(SecurityContextHolder.getContext().getAuthentication());
    return stockService.addOrUpdateStock(stock);
  }



  @PostMapping("/track/{userId}/{ticker}")
  @PreAuthorize("isAuthenticated()")
  public String trackStock(@PathVariable Long userId, @PathVariable String ticker){
    System.out.println(SecurityContextHolder.getContext().getAuthentication() + "dkkdkdkdkdk");
    User user = userRepository.findById(userId)
        .orElseThrow(()-> new RuntimeException("User not found" ));
    System.out.println(user.getUsername());

    Stock stock = stockRepository.findByTicker(ticker);
    if (stock == null) {
      return "Stock not found";
    }

    return stockService.trackStock(user, stock);
  }

  @PostMapping("/untrack/{userId}/{ticker}")
  @PreAuthorize("isAuthenticated()")
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
