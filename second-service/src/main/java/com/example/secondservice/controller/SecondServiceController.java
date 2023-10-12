package com.example.secondservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/second-service")
@Slf4j
public class SecondServiceController {

	Environment environment;

	@Autowired
	public SecondServiceController(Environment environment) {
		this.environment = environment;
	}

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to The second Service";
	}

	@GetMapping("/message")
	public String message(@RequestHeader(value = "second-request", required = false) String header) {
		log.info("header = " + header);
		return "Hello World in Second Service";
	}

	@GetMapping("/check")
	public String check(HttpServletRequest request) {
		log.info("Server Port = {}" + request.getServerPort());
		return String.format("Second Service Checked from PORT %s", environment.getProperty("local.server.port"));
	}
}
