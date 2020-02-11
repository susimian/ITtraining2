package com.simian.it.tool;

public class Result {
	private String code;
	private String text;
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Result(String code, String text) {
		super();
		this.code = code;
		this.text = text;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "Result [code=" + code + ", text=" + text + "]";
	}
	
}
