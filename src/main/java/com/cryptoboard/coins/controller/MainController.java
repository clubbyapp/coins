package com.cryptoboard.coins.controller;

import com.cryptoboard.coins.model.Coin;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void importData(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String coinmarketcapApiUrl = "https://api.coinmarketcap.com/v1/ticker/?convert=EUR";
        Coin[] coins = restTemplate.getForObject(coinmarketcapApiUrl, Coin[].class);
        return;
    }
}
