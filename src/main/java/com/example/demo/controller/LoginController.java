package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.Mutter;
import com.example.demo.entity.RegisterUser;
import com.example.demo.service.MutterService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MutterService mutterService;
	
	@Autowired
	private HttpSession session;
	
	// インスタンスをモデルに格納
	@ModelAttribute("registerUser")
	public RegisterUser setUpRegisterUser() {
		return new RegisterUser();
	}
	@ModelAttribute("mutter")
	public Mutter setUpMutter() {
		return new Mutter();
	}

	@GetMapping("show")
	public String loginView() {
		session.invalidate();//セッション内容を消す
		return "index";
	}
	@PostMapping("show")//登録画面(regist)から遷移してくる
	public String postShowView(@Validated RegisterUser registerUser,
				BindingResult bindingResult,Model model) {
//		if(userService.registerCheck(registerUser)) {// 同じアカウント存在
//			bindingResult.rejectValue("name","",registerUser.getName()+"は登録済です");
//		}
		// 取得する		 
		// ↑は内容確認を行ったあとに登録
		// ↓はuserService.registerAccountで内容確認と登録を行う
		
		if(bindingResult.hasErrors()) {
			// System.out.println("エラーあるよ");
			return "register";//リダイレクトはうまくいかない！
		}
		
//		boolean result =userService.createNewAccount(registerUser);// 登録
		boolean result = userService.registerAccount(registerUser);
		if(result) {
			return "index";
		}
		return "redirect:/register";
	}
	@GetMapping("register")
	public String getRegisterView() {
	// 上の public RegisterUser setUpRegisterUser()がない場合は引数を渡す
	// public String getRegisterView(RegisterUser registerUser) { //なぜかregisterUserを引数にしてわたす
		return "register";
	}
	@PostMapping("login")
	public String loginResultView(LoginUser loginUser ,Model model) {
		RegisterUser registerUser = userService.loginCheck(loginUser);
		if(registerUser == null) {
			return "index";
		}
		session.setAttribute("registerUser", registerUser);// sessionに格納しても表示はされない
		model.addAttribute("registerUser", registerUser);
		return "loginOK";
	}
	@GetMapping("logout")
	public String logoutView(Model model) {
		RegisterUser registerUser = (RegisterUser) session.getAttribute("registerUser");
		if(registerUser == null) {
			return "redirect:/show";//リダイレクトの場合はマッピング名
		}
//		model.addAttribute("registerUser",session.getAttribute("registerUser"));
//		registerUser = (RegisterUser) session.getAttribute("registerUser");
		model.addAttribute("registerUser",registerUser);
		return "logout";
	}
	@GetMapping("remove")
	public String removeView(Model model) {
		RegisterUser registerUser = (RegisterUser) session.getAttribute("registerUser");
		model.addAttribute("registerUser",registerUser);
		return "remove";
	}
	@GetMapping("removeOK")
	public String removeOKView() {
		RegisterUser registerUser = (RegisterUser) session.getAttribute("registerUser");
		boolean result = userService.removeAccount(registerUser);
		if(result == true) {
			// System.out.println("削除成功！");
		}
		return "redirect:/show";
	}
	@GetMapping("loginOK")
	public String loginOKView(Model model) {
		RegisterUser registerUser = (RegisterUser) session.getAttribute("registerUser");
		model.addAttribute("registerUser",registerUser);
		return "loginOK";
	}
	@GetMapping("dokoTsubu")
	public String getDokoTsubuView(Model model) {
		RegisterUser registerUser = (RegisterUser) session.getAttribute("registerUser");
		List<Mutter> mutterList = mutterService.findAll();
		model.addAttribute("registerUser",registerUser);
		model.addAttribute("mutterList",mutterList);
		return "dokoTsubu";
	}
	@PostMapping("dokoTsubu")
	public String postDokoTsubuView(
			@Validated Mutter mutter,
			BindingResult bindingResult,
			Model model) {
		RegisterUser registerUser = (RegisterUser) session.getAttribute("registerUser");
		List<Mutter> mutterList = mutterService.findAll();
		if(bindingResult.hasErrors()) {
			model.addAttribute("registerUser",registerUser);
			model.addAttribute("mutterList",mutterList);
			return "dokoTsubu";
		}
		mutterService.insert(new Mutter(0,registerUser.getName(),mutter.getText()));
		return "redirect:/dokoTsubu";//つぶやき更新時はリダイレクト必要
	}
}
