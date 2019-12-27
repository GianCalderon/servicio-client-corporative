package com.springboot.corporative.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.corporative.document.Account;
import com.springboot.corporative.document.Corporative;
import com.springboot.corporative.dto.CorporativeDto;
import com.springboot.corporative.repo.CorporativeRepo;
import com.springboot.corporative.util.UtilConvert;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PymeImpl implements CorporativeInterface {

	
  private static final Logger LOGGER = LoggerFactory.getLogger(PymeImpl.class);
	 
	@Autowired
	CorporativeRepo repo;
	
	@Autowired
	UtilConvert convert;
	
	
	public Flux<Corporative> findAll() {
		
		return repo.findAll();
	}

	public Mono<Corporative> findById(String id) {
		
		return repo.findById(id);
	}
	
	@Override
	public Mono<Corporative> save(Corporative Pyme) {

		Pyme.setCreateDate(new Date());
		Pyme.setUpdateDate(new Date());
		Pyme.setListAccount(new ArrayList<Account>());
		
		return repo.save(Pyme);

	}

	@Override
	public Mono<Corporative> update(CorporativeDto PymeDto, String ruc) {
		
	    return repo.findByNumDoc(ruc).flatMap(corporative -> {
	      	
	        List<Account> list = corporative.getListAccount();
	        
	        Account account = new Account();
	        
	        account.setIdAccount(PymeDto.getIdAccount());
	        account.setNumberAccount(PymeDto.getNumberAccount());
	        account.setNameAccount(PymeDto.getNameAccount());

	        list.add(account);

	        corporative.setTipoDoc(PymeDto.getTipoDoc());
	        corporative.setNumDoc(PymeDto.getNumDoc());
	        corporative.setName(PymeDto.getName());
	        corporative.setAddress(PymeDto.getAddress());
	        corporative.setUpdateDate(new Date());
	        corporative.setListAccount(list);
	        
	        return repo.save(corporative);
	    
	      });
		
	
	}

	@Override
	public Mono<Void> delete(Corporative Pyme) {
		return repo.delete(Pyme);
	}
	
	//OPERACION QUE EXPONEN SERVICIOS
	
	@Override
	public Mono<Corporative> saveDto(CorporativeDto CorporativeDto) {
		
		return save(convert.convertPyme(CorporativeDto));
	}

	@Override
	public Mono<Corporative> nameSearch(String name) {
		
		return repo.findByName(name);
	}
	
	 @Override
	  public Mono<Corporative> findByNumDoc(String numDoc) {

	    return repo.findByNumDoc(numDoc);
	  }



}
