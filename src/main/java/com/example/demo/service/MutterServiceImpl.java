package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Mutter;
import com.example.demo.repository.MuttersRepository;

@Service
public class MutterServiceImpl implements MutterService {

	@Autowired
	MuttersRepository repository;
	
	@Override
	public List<Mutter> findAll() {
		List<Mutter> result = repository.findAll();
		return result;
	}

	@Override
	public boolean insert(Mutter mutter) {
		boolean result = repository.create(mutter);
		return result;
	}

}
