package com.javaex.dao;

import java.util.List;

import com.javaex.vo.GuestbookVo;

public class Test {

	public static void main(String[] args) {
		
		//Dao 테스트용
		GuestbookDao guestbookDao = new GuestbookDao();
		List<GuestbookVo> gbList = guestbookDao.getList();

		System.out.println(gbList.toString());
	}
//test를 했는데 [] 투스트링값을 못가져온다면 오라클에서 커밋했는지 확인.
}
