package com.example.demo.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.validation.UnusedName;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SessionScope
public class RegisterUser {
	@Id
	@NotBlank(message="名前を入力してください")
	@UnusedName
	private String name;
	
	@NotBlank(message="パスワードを入力してください")
	@Pattern(regexp="[a-zA-Z0-9]*",message="パスワードは半角英数字で入力してください")
	@Length(min=3,message="パスワードは3文字以上入力してください")
	private String pass;

	@Range(min=18,max=130,message="年齢は18から130の間で入力してください")
	private int age;
}
