package com.mystudy.student;

public class ManagerCRUDTest {

	public static void main(String[] args) {
		ManagerCRUD crud = new ManagerCRUD();
		System.out.println("----- id, password 체크1 -----");
		boolean loginOk = crud.checkIdPassword(9999, "pw123");
		if (loginOk) {
			System.out.println(">>> 로그인 성공! 회원님 반갑습니다.");
		} else {
			System.out.println(">>> 로그인 실패! 아이디/암호 확인하세요.");
		}
		
		loginOk = crud.checkIdPassword2(9999, "pw123");
		if (loginOk) {
			System.out.println(">>>2 로그인 성공! 회원님 반갑습니다.");
		} else {
			System.out.println(">>>2 로그인 실패! 아이디/암호 확인하세요.");
		}
		

	}

}
