package com.ichat.util;

public class Msg {
	public static final int is_received=0;
	public static final int is_sent=1;
	private String msg;
	private int type;
	public Msg(String content,int type){
		this.msg=content;
		this.type=type;
	}
	public String getMsg(){
		return msg;
	}
	public int getType(){
		return type;
	}
}
