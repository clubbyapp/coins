package com.cryptoboard.coins.controller;

import com.cryptoboard.coins.model.Coin;
import com.cryptoboard.coins.model.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    CoinRepository coinRepository;

    @RequestMapping(value = "/importData", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void importData(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String coinmarketcapApiUrl = "https://api.coinmarketcap.com/v1/ticker/?convert=EUR";
        Coin[] coins = restTemplate.getForObject(coinmarketcapApiUrl, Coin[].class);
        List<Coin> coinList = new ArrayList<>(Arrays.asList(coins));
        coinRepository.save(coinList);
        double btcPriceEur = 0;
        double ethPriceEur = 0;
        for (Coin coin : coins) {

            if ("bitcoin".equals(coin.getShortcut())) {
                btcPriceEur = coin.getPrice_eur();
            } else if ("ethereum".equals(coin.getShortcut())) {
                ethPriceEur = coin.getPrice_eur();
            }
        }
        System.out.println("BTC: " + 0.15 * btcPriceEur);
        System.out.println("ETH; " + 2.3 * ethPriceEur);

        return;
    }
}
