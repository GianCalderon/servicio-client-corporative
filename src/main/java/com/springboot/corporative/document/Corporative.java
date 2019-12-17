package com.springboot.corporative.document;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection = "client-Corporative")
public class Corporative {

	@Id
	private String id;

	@NotNull(message = "User's name must not be null")
	@NotEmpty(message = "name may not be empty")
	private String name;

	@NotNull(message = "User's address must not be null")
	@NotEmpty(message = "address may not be empty")
	private String address;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateDate;

	private List<Map<String, String>> idCuentas;

	public Corporative() {

	}

}
