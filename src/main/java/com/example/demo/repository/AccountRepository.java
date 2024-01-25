package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.RegisterUser;

@Repository
public class AccountRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//nameとpassが、DBに存在するかを確認
	// public LoginUser findAccount(LoginUser loginUser) {
	
	// ユーザ取得
	public RegisterUser findAccount(LoginUser loginUser) {
//		System.out.println("よんだ？");
//		System.out.println(loginUser.getName());
//		System.out.println(loginUser.getPass());
//		if(jdbcTemplate == null ) {
//			System.out.println("ヌルだよ");
//			// jdbcTemplate = new JdbcTemplate();
//		}else {
//			System.out.println("ヌルじゃないよ");
//		}
		String sql="SELECT NAME,PASSWORD,AGE FROM USERS WHERE NAME = ? AND PASSWORD = ?";
//		System.out.println(sql);
		List<Map<String,Object>> resultList = jdbcTemplate.queryForList(sql,loginUser.getName(),loginUser.getPass());
//		System.out.println("サイズ"+(resultList.size()!=0));
//		System.out.println("ヌルかどうか"+(resultList != null));
		// resultListの値
		if(resultList.size()!=0 && resultList != null) {
			for(Map<String,Object> result :resultList) {
				String name = (String) result.get("name");
				String pass = (String) result.get("password");
				int age = (int) result.get("age");
				RegisterUser registerUser = new RegisterUser(name,pass,age);
				return registerUser;
			}
			/*for(Map<String,Object> result :resultList) {
				if(loginUser.getName().equals((String) result.get("name"))
						&& loginUser.getPass().equals((String) result.get("password"))) {
					return loginUser;
				}
			}*/
			// return loginUser;
		}
		/*if(resultList.size()!=0 && resultList != null) {
			return loginUser;
		}*/
		return null;
	}

	// 既存ユーザがあるかどうか？
	public boolean findAccount(RegisterUser registerUser) {
		String sql="SELECT COUNT(*) FROM USERS WHERE NAME = ?";
		int result ;
		result = jdbcTemplate.queryForObject(
				sql, Integer.class, registerUser.getName());
		// System.out.println("result:"+result);
		if( result == 0) {
			return false;
		}
		return true;
	}
	// ユーザ登録
	public boolean insert(RegisterUser registerUser) {
		String sql="INSERT INTO USERS(NAME,PASSWORD,AGE) VALUES(?,?,?)";
		int result = jdbcTemplate.update(
				sql,registerUser.getName(),registerUser.getPass(),registerUser.getAge());
		// System.out.println("result:"+result);
		if( result == 0) {
			return false;//登録失敗
		}
		return true;//登録成功
	}
	// ユーザ削除
	public boolean remove(RegisterUser registerUser) {
		String sql="DELETE FROM USERS WHERE NAME=?";
		int result = jdbcTemplate.update(sql,registerUser.getName());
		
		if( result == 0 ) {
			return false;//削除失敗
		}
		// System.out.println("削除成功");
		return true;//削除成功
	}
}
