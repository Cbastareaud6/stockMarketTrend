package com.CathyB.stockMarketTrend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan(basePackages = "com.CathyB")
@Configuration
public class StockMarketTrendApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockMarketTrendApplication.class, args);
	}

}
