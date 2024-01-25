package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mutters")//テーブル名紐づけ
public class Mutter {
	@Id
	private int id;
	private String name;
	@NotBlank(message="テキストが未入力です")
	private String text;
}
