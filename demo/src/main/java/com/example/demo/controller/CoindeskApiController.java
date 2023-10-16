package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.CoindeskDto;
import com.example.demo.model.RelayDto;
import com.example.demo.model.relayBpi;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/coindesk")
public class CoindeskApiController {

	@Autowired
	private CurrencyDataController currencyDataController;

	@GetMapping("/ori")
	public CoindeskDto callCoindeskApi() {
		ObjectMapper objectMapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		CoindeskDto coindeskDto = new CoindeskDto();
		try {
			String json = restTemplate.getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", String.class);
			coindeskDto = objectMapper.readValue(json, CoindeskDto.class);
		} catch (Exception e) {
			System.out.println("error in transfer json to bean");
		}

		return coindeskDto;
	}

	@GetMapping("/new")
	public RelayDto changeValueOfCoindeskApi() {

		CoindeskDto coindeskDto = this.callCoindeskApi();
		RelayDto relayDto = new RelayDto();
		try {
			DateTimeFormatter fromDateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
			LocalDateTime fromlocalDateTime = LocalDateTime.parse(coindeskDto.getTime().getUpdatedISO(),
					fromDateTimeFormatter);
			DateTimeFormatter toDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
			String toDatetime = fromlocalDateTime.format(toDateTimeFormatter);

			relayDto.setUpdateDate(toDatetime);
			List<relayBpi> relayBpiList = new ArrayList<relayBpi>();
			relayBpiList.add(new relayBpi(coindeskDto.getBpi().getUSD().getCode(),
					currencyDataController.readCurrency(coindeskDto.getBpi().getUSD().getCode()).getDescription(),
					coindeskDto.getBpi().getUSD().getRate_float()));
			relayBpiList.add(new relayBpi(coindeskDto.getBpi().getGBP().getCode(),
					currencyDataController.readCurrency(coindeskDto.getBpi().getGBP().getCode()).getDescription(),
					coindeskDto.getBpi().getGBP().getRate_float()));
			relayBpiList.add(new relayBpi(coindeskDto.getBpi().getEUR().getCode(),
					currencyDataController.readCurrency(coindeskDto.getBpi().getEUR().getCode()).getDescription(),
					coindeskDto.getBpi().getEUR().getRate_float()));
			relayDto.setRelayBpiList(relayBpiList);
		} catch (Exception e) {
			System.out.println("error in calling external API");
			//return null relayDto
		}
		return relayDto;

	}
}
