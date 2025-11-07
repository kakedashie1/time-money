package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoryRegistForm {
	
	private Integer logId;
	
	@NotNull(message="カテゴリー名を入力してください。")
	@NotBlank(message="カテゴリー名を入力してください。")
	@Size(min=1, max=20, message="1文字以上20文字以内で入力してください。")
	private String categoryName;
}
