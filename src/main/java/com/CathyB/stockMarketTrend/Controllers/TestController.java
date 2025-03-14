package com.CathyB.stockMarketTrend.Controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {


  @GetMapping
  @RequestMapping("/test")
  public ResponseEntity<String> testAccess() {


    return ResponseEntity.ok("you are authorized");

  }

}
