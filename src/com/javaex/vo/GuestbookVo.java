package com.javaex.vo;

public class GuestbookVo {
//list제네릭에 들어갈 파라미터(필드에 있는 데이터중심)
	
	//필드
	//자바에서는 주로 낙타(camel)표기법 씀(언더바는 스네이크표기법)
	private int no;
    private String name;
    private String password;
    private String content;
    private String regDate;
	
	
	//생성자
	public GuestbookVo() {}
	public GuestbookVo(String name, String password, String content) {
		super();
		this.name = name;
		this.password = password;
		this.content = content;
	};		
	public GuestbookVo(String name, String password, String content, String regDate) {
		super();
		this.name = name;
		this.password = password;
		this.content = content;
		this.regDate = regDate;
	};		
	public GuestbookVo(int no, String name, String password, String content, String regDate) {
		super();
		this.no = no;
		this.name = name;
		this.password = password;
		this.content = content;
		this.regDate = regDate;
	}
	
	//메소드gs
	//private로 직접적인 접근을 막은 대신 get/set메서드를 통해서 접근할수있도록 제한한다.
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	//메소드일반
	@Override
	public String toString() {
		return "GuestbookVo [no=" + no + ", name=" + name + ", password=" + password + ", content=" + content
				+ ", regDate=" + regDate + "]";
	};
	
	
	
	
	
}
