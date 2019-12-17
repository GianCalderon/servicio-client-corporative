package com.springboot.corporative.service;

import com.springboot.corporative.document.Corporative;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CorporativeInterface  {

  public Flux<Corporative> findAll();

  public Mono<Corporative> findById(String id);
	
  public Mono<Corporative> save(Corporative corporative);

  public Mono<Corporative> update(Corporative corporative ,String id);

  public Mono<Void> delete(Corporative corporative);
  
//  public Mono<Enterprise> saveDto(EnterpriseDto personalDto);
//  
//  public Mono<Enterprise> nameSearch(String name);
	
}
