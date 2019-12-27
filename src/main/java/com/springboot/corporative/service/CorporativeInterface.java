package com.springboot.corporative.service;

import com.springboot.corporative.document.Corporative;
import com.springboot.corporative.dto.CorporativeDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CorporativeInterface  {

  public Flux<Corporative> findAll();

  public Mono<Corporative> findById(String id);
	
  public Mono<Corporative> save(Corporative Pyme);

  public Mono<Corporative> update(CorporativeDto CorporativeDto ,String id);

  public Mono<Void> delete(Corporative Pyme);
  
  public Mono<Corporative> saveDto(CorporativeDto CorporativeDto);
  
  public Mono<Corporative> nameSearch(String name);
  
  public Mono<Corporative> findByNumDoc(String numDoc);
  

	
}
