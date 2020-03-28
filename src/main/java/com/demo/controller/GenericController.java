package com.demo.controller;

import java.math.BigInteger;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.GenericService;

@RestController
public class GenericController {
	
//	private Logger logger = LoggerFactory.getILoggerFactory(GenericController.class);
	@Autowired
	GenericService genericService;
	
	@PostMapping("/data")
	public ResponseEntity postData(@RequestBody Map<String,Object> input) throws Exception{
		return new ResponseEntity<>(input,HttpStatus.OK);
		
	}
	@GetMapping("/data")
	public ResponseEntity getData() throws Exception{
		BigInteger heirarchyId=genericService.getData();
		return new ResponseEntity<>(heirarchyId,HttpStatus.OK);
		
	}

}
