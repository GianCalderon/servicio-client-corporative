package com.springboot.corporative.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.corporative.document.Corporative;
import com.springboot.corporative.repo.CorporativeRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CorporativeImpl implements CorporativeInterface {

	
  private static final Logger LOGGER = LoggerFactory.getLogger(CorporativeImpl.class);
	 
	@Autowired
	CorporativeRepo repo;
	
//	@Autowired
//	UtilConvert convert;
	
	
	public Flux<Corporative> findAll() {
		
		return repo.findAll();
	}

	public Mono<Corporative> findById(String id) {
		
		return repo.findById(id);
	}
	@Override
	public Mono<Corporative> save(Corporative corporative ) {
		
		return repo.save(corporative);
	}

	@Override
	public Mono<Corporative> update(Corporative corporative, String id) {
		
		return repo.findById(id).flatMap(e -> {
			
			e.setName(corporative.getName());
			e.setAddress(corporative.getAddress());
			e.setUpdateDate(new Date());
			return repo.save(e);

		});
	}

	@Override
	public Mono<Void> delete(Corporative corporative) {
		return repo.delete(corporative);
	}
	
//	@Override
//	public Mono<Enterprise> saveDto(EnterpriseDto enterpriseDto) {
//		
//		return save(convert.convertEnterprise(enterpriseDto));
//	}
//
//	@Override
//	public Mono<Enterprise> nameSearch(String name) {
//		
//		return repo.findByName(name);
//	}



}
