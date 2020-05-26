package com.bank.cc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.cc.model.ApplicationRequest;
import com.bank.cc.model.ApplicationResponse;
import com.bank.cc.model.User;
import com.bank.cc.service.ApplicationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/apply")
@Slf4j
public class ApplicationController {
	
	@Autowired
	private ApplicationService appService;

	@Autowired
	private ObjectMapper jsonMapper;

	@PostMapping("/creditcard")
	public ResponseEntity<ApplicationResponse> apply(@Valid @RequestBody ApplicationRequest application)
			throws JsonProcessingException {

		log.info("Application recieved: {}", jsonMapper.writeValueAsString(application));
		User user = appService.validateAndProcessApplication(application);

		return new ResponseEntity<>(ApplicationResponse.builder().status("Success")
				.message("Application " + application.getApplicationId() + " processed successfully").user(user)
				.build(), HttpStatus.OK);
	}

}
