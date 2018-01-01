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

    @RequestMapping(value = "/importData", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void importData(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String coinmarketcapApiUrl = "https://api.coinmarketcap.com/v1/ticker/?convert=EUR";
        Coin[] coins = restTemplate.getForObject(coinmarketcapApiUrl, Coin[].class);
        double btcPriceEur = 0;
        double ethPriceEur = 0;
        for (Coin coin : coins) {
            if ("bitcoin".equals(coin.getId())) {
                btcPriceEur = coin.getPrice_eur();
            } else if ("ethereum".equals(coin.getId())) {
                ethPriceEur = coin.getPrice_eur();
            }
        }
        System.out.println("BTC: " + 0.15 * btcPriceEur);
        System.out.println("ETH; " + 2.3 * ethPriceEur);

        return;
    }

    @RequestMapping(value = "/overview", method = RequestMethod.GET)
    public String getOverview(Model model) {
        final double bitcoinDeBTCAmount = 0.166656;
        final double bitcoinDeBCHAmount = 0.06669657;
        final double bitcoinDeBTGAmount = 0.0537848;
        final double bitcoinDeETHAmount = 2.20224;
        final double krakenComXRPAmount = 650.0;
        final double krakenComXMRAmount = 1.0;
        final double krakenComDASHAmount = 0.166656;
        final double paperwalletBTCAmount = 0.166656;
        final double freebitcoInBTCAmount = 0.166656;


        return "index";
    }
}
