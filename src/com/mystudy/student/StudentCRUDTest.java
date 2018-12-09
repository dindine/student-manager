package com.mystudy.student;

import java.util.ArrayList;


import com.mystudy.student.StudentVO;
import com.mystudy.student.StudentCRUD;

public class StudentCRUDTest {

	public static void main(String[] args) {
		StudentCRUD crud = new StudentCRUD();
		crud.dispData(20181113);

		ArrayList<StudentVO> list = crud.selectAll();
		System.out.println("--- ��ü����Ÿ ��ȸ ��� ---");
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
//		StudentVO STUDENT = new StudentVO(20183333, "pw1234", "���켺", 3, 3, "7512344567894" , 01011112222, "����� ������");
//		svo = crud.selectOne(STUDENT);
//		System.out.println(svo);

		
		

		
//		System.out.println("---- �Է� �׽�Ʈ -----");
//		//����Ÿ �Է� �׽�Ʈ
//		crud.insertOne(20183333, "pw1234", "���켺", 3, 3, "7512344567894" , 01011112222, "����� ������");
//		crud.dispData(20183333);
		//crud.insertData(new StudentVO("2018011", "ȫ�浿11", 100, 90, 81));
		//crud.insertData(new StudentVO("2018012", "ȫ�浿12", 100, 90, 81,0,0));
		

//		System.out.println("==============insertOne================");
//		STUDENT = new StudentVO(20182224, "pw1234", "���¿�", 2, 2, "7506121234567" ,01011111111, "�����");
//		int cnt = crud.insertOne(STUDENT);
//		System.out.println("�Է°Ǽ�: " + cnt);
//		System.out.println(crud.selectOne(STUDENT));
		
	
//		System.out.println("----- updateData(VO) -----");
//		STUDENT = new StudentVO(20182224, "pw1234", "ȫ���", 2, 2, "7506121234567" ,01011111111, "��õ�� ����");
//		int cnt = crud.updateData(STUDENT);
//		System.out.println("�����Ǽ�: " + cnt);
//		System.out.println(crud.selectOne(STUDENT));
		
//		System.out.println("----- deleteOne(VO) -----");
//		STUDENT = new StudentVO(20182224, "pw1234", "���¿�", 2, 2, "7506121234567" ,01011111111, "�����");
//		int cnt = crud.deleteOne(STUDENT);
//		System.out.println("�����Ǽ�: " + cnt);
//		System.out.println(crud.selectOne(STUDENT));
//
//		System.out.println("----- deleteOne(id) -----");
//		STUDENT = new StudentVO(20182224, "pw1234", "���¿�", 2, 2, "7506121234567" ,01011111111, "�����");
//		int cnt = crud.deleteOne(20182224);
//		System.out.println("�����Ǽ�: " + cnt);
//		System.out.println(crud.selectOne(STUDENT));

		System.out.println("----- id, password üũ1 -----");
		boolean loginOk = crud.checkIdPassword(20183333, "pw1234");
		if (loginOk) {
			System.out.println(">>> �α��� ����! ȸ���� �ݰ����ϴ�.");	
			System.out.println("[ȸ������]\n ");
			System.out.println();

		} else {
			System.out.println(">>> �α��� ����! ���̵�/��ȣ Ȯ���ϼ���.");
		}


	}

}
