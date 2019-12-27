package com.springboot.corporative.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.corporative.document.Account;
import com.springboot.corporative.document.Corporative;
import com.springboot.corporative.dto.CorporativeDto;
import com.springboot.corporative.service.CorporativeInterface;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/corporative")
public class CorporativeController {
	
  private static final Logger LOGGER = LoggerFactory.getLogger(CorporativeController.class);

	@Autowired
	CorporativeInterface service;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<Corporative>>> toList() {

		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.findAll()));

	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<Corporative>> search(@PathVariable String id) {

		return service.findById(id).map(e->ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(e))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@PostMapping
	public Mono<ResponseEntity<Corporative>> save(@RequestBody Corporative Corporative) {

		return service.save(Corporative).map(e->ResponseEntity.created(URI.create("/api/Corporative"))
				.contentType(MediaType.APPLICATION_JSON).body(e));

	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<Corporative>> update(@RequestBody CorporativeDto CorporativeDto, @PathVariable String id) {

		LOGGER.info("Empresa Recibida para Actualizar :--->"+CorporativeDto.toString());
		
		return service.update(CorporativeDto, id).map(e->ResponseEntity
						.created(URI.create("/api/Corporative".concat(e.getId())))
						.contentType(MediaType.APPLICATION_JSON)
						.body(e))
				.defaultIfEmpty(ResponseEntity.notFound().build());
			
	
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
		
		return service.findById(id).flatMap(e->{
			return service.delete(e).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

		
	}
	
	@PostMapping("/saveCorporative")
	public Mono<ResponseEntity<Corporative>> saveDto(@RequestBody CorporativeDto CorporativeDto) {
	
		LOGGER.info("Empresa Recibida :--->"+CorporativeDto.toString());

		return service.saveDto(CorporativeDto).map(e->ResponseEntity.created(URI.create("/api/Corporative"))
				.contentType(MediaType.APPLICATION_JSON).body(e));

	}
	
	  @GetMapping("/doc/{ruc}")
	  public Mono<ResponseEntity<Corporative>> searchRuc(@PathVariable String ruc) {

	    return service.findByNumDoc(ruc).map(p -> ResponseEntity.ok()
	      .contentType(MediaType.APPLICATION_JSON).body(p))
	      .defaultIfEmpty(ResponseEntity.notFound().build());

	  }
	
	  @GetMapping("/valid/{ruc}")
	  public Flux<Account> valid(@PathVariable String dni) {
	   
	    return service.findByNumDoc(dni).flatMapMany(cuentas ->{ 

	    	return Flux.fromIterable(cuentas.getListAccount());
	    		
	    });	
	    	
	  }


}
