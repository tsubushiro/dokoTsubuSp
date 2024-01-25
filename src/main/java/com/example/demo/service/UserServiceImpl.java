package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.RegisterUser;
import com.example.demo.repository.AccountRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	AccountRepository repository;
	
	@Override
	public RegisterUser loginCheck(LoginUser loginUser) {
		RegisterUser result = repository.findAccount(loginUser);
		return result;
	}

	@Override
	public boolean registerCheck(RegisterUser registerUser) {
		boolean result = repository.findAccount(registerUser);
		return result;
	}

	@Override
	public boolean createNewAccount(RegisterUser registerUser) {
		boolean result = repository.insert(registerUser);
		return result;
	}
	@Override
	public boolean registerAccount(RegisterUser registerUser) {
		if(repository.findAccount(registerUser)){
			return false;
		}
		boolean result = repository.insert(registerUser);
		return result;
	}

	@Override
	public boolean removeAccount(RegisterUser registerUser) {
		if(repository.findAccount(registerUser) == false){ //見つからなかったら抜ける
			// System.out.println("なかったよ");
			return false;
		}
		boolean result = repository.remove(registerUser);
		// System.err.println("result:"+result);
		return result;
	}
}
