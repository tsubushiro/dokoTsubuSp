package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Mutter;

public interface MutterService {
	public List<Mutter> findAll();
	public boolean insert(Mutter mutter);
}
