package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CurrencyDo;
import com.example.demo.repository.CurrencyRepository;

@RestController
@RequestMapping("/currency")
public class CurrencyDataController {

	@Autowired
	private CurrencyRepository currencyRepository;

	@PostMapping("/create")
	public String createCurrency(@RequestBody CurrencyDo currency) {

		currencyRepository.save(currency);

		return "is created.";
	}

	@GetMapping("/read/{code}")
	public CurrencyDo readCurrency(@PathVariable(value = "code") String code) {

		return currencyRepository.findByCode(code);
	}

	@PutMapping("update")
	public String updateCurrency(@RequestBody CurrencyDo inCurrency) {

		CurrencyDo queryCurrency = currencyRepository.findByCode(inCurrency.getCode());

		if (null != queryCurrency) {
			inCurrency.setId(queryCurrency.getId());
			currencyRepository.save(inCurrency);
			return "is updated.";
		} else {
			return "data not exist.";
		}
	}

	@DeleteMapping("/delete/{code}")
	public String deleteCurrency(@PathVariable(value = "code") String code) {

		CurrencyDo queryCurrency = currencyRepository.findByCode(code);
		currencyRepository.deleteById(queryCurrency.getId());

		return "is deleted.";
	}

	@GetMapping("/readAll")
	public List<CurrencyDo> readAllCurrencies() {

		return currencyRepository.findAll();
	}

}
