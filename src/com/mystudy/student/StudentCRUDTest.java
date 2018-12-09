package com.mystudy.student;

import java.util.ArrayList;


import com.mystudy.student.StudentVO;
import com.mystudy.student.StudentCRUD;

public class StudentCRUDTest {

	public static void main(String[] args) {
		StudentCRUD crud = new StudentCRUD();
		crud.dispData(20181113);

		ArrayList<StudentVO> list = crud.selectAll();
		System.out.println("--- 전체데이타 조회 결과 ---");
		for (StudentVO stu : list) {
			crud.dispData(stu.getSNO());
			
		}
		
//		System.out.println("----- selectOne(id) -----");
//		StudentVO svo = crud.selectOne(20181112);
////		System.out.println(svo.toString());
//		System.out.println(svo);
//
//		
//		
//		System.out.println("----- selectOne(VO) -----");
//		StudentVO STUDENT = new StudentVO(20183333, "pw1234", "정우성", 3, 3, "7512344567894" , 01011112222, "서울시 강남구");
//		svo = crud.selectOne(STUDENT);
//		System.out.println(svo);

		
		

		
//		System.out.println("---- 입력 테스트 -----");
//		//데이타 입력 테스트
//		crud.insertOne(20183333, "pw1234", "정우성", 3, 3, "7512344567894" , 01011112222, "서울시 강남구");
//		crud.dispData(20183333);
		//crud.insertData(new StudentVO("2018011", "홍길동11", 100, 90, 81));
		//crud.insertData(new StudentVO("2018012", "홍길동12", 100, 90, 81,0,0));
		

//		System.out.println("==============insertOne================");
//		STUDENT = new StudentVO(20182224, "pw1234", "차승원", 2, 2, "7506121234567" ,01011111111, "서울시");
//		int cnt = crud.insertOne(STUDENT);
//		System.out.println("입력건수: " + cnt);
//		System.out.println(crud.selectOne(STUDENT));
		
	
//		System.out.println("----- updateData(VO) -----");
//		STUDENT = new StudentVO(20182224, "pw1234", "홍길순", 2, 2, "7506121234567" ,01011111111, "인천시 부평구");
//		int cnt = crud.updateData(STUDENT);
//		System.out.println("수정건수: " + cnt);
//		System.out.println(crud.selectOne(STUDENT));
		
//		System.out.println("----- deleteOne(VO) -----");
//		STUDENT = new StudentVO(20182224, "pw1234", "차승원", 2, 2, "7506121234567" ,01011111111, "서울시");
//		int cnt = crud.deleteOne(STUDENT);
//		System.out.println("삭제건수: " + cnt);
//		System.out.println(crud.selectOne(STUDENT));
//
//		System.out.println("----- deleteOne(id) -----");
//		STUDENT = new StudentVO(20182224, "pw1234", "차승원", 2, 2, "7506121234567" ,01011111111, "서울시");
//		int cnt = crud.deleteOne(20182224);
//		System.out.println("삭제건수: " + cnt);
//		System.out.println(crud.selectOne(STUDENT));

		System.out.println("----- id, password 체크1 -----");
		boolean loginOk = crud.checkIdPassword(20183333, "pw1234");
		if (loginOk) {
			System.out.println(">>> 로그인 성공! 회원님 반갑습니다.");	
			System.out.println("[회원정보]\n ");
			System.out.println();

		} else {
			System.out.println(">>> 로그인 실패! 아이디/암호 확인하세요.");
		}


	}

}
