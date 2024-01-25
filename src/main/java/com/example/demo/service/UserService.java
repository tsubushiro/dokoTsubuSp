package com.example.demo.service;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.RegisterUser;

public interface UserService {
	RegisterUser loginCheck(LoginUser loginUser);
	boolean registerCheck(RegisterUser registerUser);
	boolean createNewAccount(RegisterUser registerUser);
	boolean registerAccount(RegisterUser registerUser);
	boolean removeAccount(RegisterUser registerUser);
}
