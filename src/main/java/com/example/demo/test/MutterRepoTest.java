package com.example.demo.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Mutter;
import com.example.demo.service.MutterService;

// @Component
public class MutterRepoTest implements Test {
	@Autowired
	MutterService mutterService;
	
	@Override
	public void execute() {
		// TODO 自動生成されたメソッド・スタブ
		List<Mutter> mutters = mutterService.findAll();
		for(var Mutter:mutters) {
			System.out.println(Mutter.getName());
			System.out.println(Mutter.getText());
		}
	}

}
