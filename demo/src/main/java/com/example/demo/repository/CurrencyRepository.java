package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.CurrencyDo;

public interface CurrencyRepository extends JpaRepository<CurrencyDo, Integer>{
	
	CurrencyDo findByCode(String code);

}
