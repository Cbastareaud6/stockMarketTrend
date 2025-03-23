package com.CathyB.stockMarketTrend.Service;


import com.CathyB.stockMarketTrend.Model.Stock;
import com.CathyB.stockMarketTrend.Model.StockData;
import com.CathyB.stockMarketTrend.Model.StockTracking;
import com.CathyB.stockMarketTrend.Model.User;
import com.CathyB.stockMarketTrend.Repository.StockRepository;
import com.CathyB.stockMarketTrend.Repository.StockTrackingRepository;
import jakarta.persistence.PostPersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.annotation.PostConstruct;



@Service
public class StockService {


  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private StockTrackingRepository stockTrackingRepository;

  @Value("${finnhub.api.key}")
  private String apiKEy;

  private final RestTemplate restTemplate = new RestTemplate();

  @PostConstruct
  public void init(){
    updateAllStockData();
  }


public  void updateAllStockData (){
  String url = UriComponentsBuilder.fromHttpUrl("https://finnhub.io/api/v1/stock/symbol")
      .queryParam("exchange","US")
      .queryParam("token", apiKEy)
      .toUriString();

  Stock [] stocks = restTemplate.getForObject(url, Stock[].class);

  if (stocks.length > 0 && stocks != null){
    for (Stock stock : stocks){
     if  (!stockRepository.existsByTicker(stock.getTicker())){
        stockRepository.save(stock);
      }

    }
  }
}

  public StockData getStockData (String ticker){
    String url = UriComponentsBuilder.fromHttpUrl("https://finnhub.io/api/v1/quote")
        .queryParam("symbol", ticker)
        .queryParam("token", apiKEy)
        .toUriString();

    return restTemplate.getForObject(url, StockData.class);

  }

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
