package com.mystudy.student;


public class StudentVO {
	
	private int SNO;
	private String PW;
	private String NAME;
	private int DEPTNO;
	private String QPA; 
	private String JUMIN;
	private String PHONE;
	private String ADDRESS;
	private int SUBNO;
	
	
	public StudentVO(int SNO, String PW, String NAME, int DEPTNO, String QPA, 
			String JUMIN, String PHONE, String ADDRESS, int SUBNO) {
		super();
		this.SNO = SNO;
		this.PW = PW;
		this.NAME = NAME;
		this.DEPTNO = DEPTNO;
		this.QPA = QPA;
		this.JUMIN = JUMIN;
		this.PHONE = PHONE;
		this.ADDRESS = ADDRESS;
		this.SUBNO = SUBNO;
	}


	public int getSUBNO() {
		return SUBNO;
	}


	public void setSUBNO(int sUBNO) {
		SUBNO = sUBNO;
	}


	public int getSNO() {
		return SNO;
	}

	public void setSNO(int sNO) {
		SNO = sNO;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public int getDEPTNO() {
		return DEPTNO;
	}

	public void setDEPTNO(int dEPTNO) {
		DEPTNO = dEPTNO;
	}

	

	public String getQPA() {
		return QPA;
	}


	public void setQPA(String qPA) {
		QPA = qPA;
	}


	public String getPW() {
		return PW;
	}

	public void setPW(String pW) {
		PW = pW;
	}

	

	public String getJUMIN() {
		return JUMIN;
	}

	public void setJUMIN(String jUMIN) {
		JUMIN = jUMIN;
	}


	public String getPHONE() {
		return PHONE;
	}


	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}


	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

//	@Override
//	public String toString() {
//		return "					--------------------------------------------------------------\n"+"					|		  "+NAME+ " 학생의 정보   			     |\n 					|		- 학과번호 : " + SNO + "   		     |\n 					|		- 비밀번호 : " + PW + "   			     |\n 					|		- 이름 : "
//	+ NAME + "   			     |\n 					|		- 학과번호 : " + DEPTNO + "   			     |\n 					|		- 학점 : " + QPA+ "   			     	     |\n 					|		- 주민등록번호 : " + JUMIN + "   	     |\n 					|		- 핸드폰번호 : " 
//				+ PHONE + "   		     |\n 					|		- 주소 : " + ADDRESS + "   		     |\n 					|		- 과목번호 : " + SUBNO +"   			     |\n					--------------------------------------------------------------\n";
//	}	
	@Override
	public String toString() {
		return "					--------------------------------------------------------------\n"+"							  "+NAME+ " 학생의 정보   			     \n 							- 학과번호 : " + SNO + "   		     \n 							- 비밀번호 : " + PW + "   			     \n 							- 이름 : "
	+ NAME + "   			     \n 							- 학과번호 : " + DEPTNO + "   			     \n 							- 학점 : " + QPA+ "   			     	     \n 							- 주민등록번호 : " + JUMIN + "   	     \n 							- 핸드폰번호 : " 
				+ PHONE + "   		     \n 							- 주소 : " + ADDRESS + "   		     \n 							- 과목번호 : " + SUBNO +"   			     \n					--------------------------------------------------------------\n";
	}	
}