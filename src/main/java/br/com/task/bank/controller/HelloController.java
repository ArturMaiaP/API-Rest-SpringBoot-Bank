package br.com.task.bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	
	@GetMapping("/hello")
	ResponseEntity<String> hello() {
	    return new ResponseEntity<String>("<h2>Hello World!</h2>", HttpStatus.OK);
	}
	
}

