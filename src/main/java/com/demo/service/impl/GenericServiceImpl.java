package com.demo.service.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.GenericDAO;
import com.demo.service.GenericService;

@Service
public class GenericServiceImpl implements GenericService {

	@Autowired
	GenericDAO genericDAO;
	
	@Override
	public BigInteger getData() {
		return genericDAO.getData();
	}

}
