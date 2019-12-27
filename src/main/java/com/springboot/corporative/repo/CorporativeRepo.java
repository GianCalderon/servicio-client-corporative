package com.springboot.corporative.repo;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.corporative.document.Corporative;

import reactor.core.publisher.Mono;

public interface CorporativeRepo extends ReactiveMongoRepository<Corporative,String> {

  public Mono<Corporative> findByName(String name);

  @Query("{'nombre': ?0 }")
  public Mono<Corporative> nameSearch(String name);
  
  
  public Mono<Corporative> findByNumDoc(String numDoc);


  
}
