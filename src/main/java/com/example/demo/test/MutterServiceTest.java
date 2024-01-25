package com.example.demo.test;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Mutter;
import com.example.demo.service.MutterService;

@Component
public class MutterServiceTest implements Test {
	@Autowired
	MutterService mutterService;
	
	@Override
	public void execute() {
		List<Mutter> mutters;
		String[] fortuneList = {"😁大吉😁","😊吉😊","😰凶😰","😶大凶😶"};
		int fRnd = (new Random()).nextInt(fortuneList.length);
		System.out.println(fRnd);
		String fortune = fortuneList[fRnd];
		Mutter mutter = new Mutter(0, "おみくじ", fortune);
		boolean result = mutterService.insert(mutter);
		System.out.println("きたよ:"+result);
		
		mutters = mutterService.findAll();
		for(var Mutter:mutters) {
			System.out.println(Mutter.getName());
			System.out.println(Mutter.getText());
		}
	}

}
