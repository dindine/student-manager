package com.mystudy.student;

public class ProfessorVO {
	private int PNO;
	private String PW;
	private String PNAME;
	private int DEPTNO;
	private int SUBNO;
	private String SUB;
	private String JUMIN;
	private String PPHONE;

	public ProfessorVO(int PNO, String PW, String PNAME, int DEPTNO, int SUBNO, String SUB, String JUMIN, String PPHONE) {
		this.PNO = PNO;
		this.PW = PW;
		this.PNAME = PNAME;
		this.DEPTNO = DEPTNO;
		this.SUBNO= SUBNO;
		this.SUB =SUB;	
		this.JUMIN = JUMIN;
		this.PPHONE = PPHONE;
	}
	public int getPNO() {
		return PNO;
	}
	public void setPNO(int pNO) {
		PNO = pNO;
	}
	public String getPNAME() {
		return PNAME;
	}
	public void setPNAME(String pNAME) {
		PNAME = pNAME;
	}
	public int getDEPTNO() {
		return DEPTNO;
	}
	public void setDEPTNO(int dEPTNO) {
		DEPTNO = dEPTNO;
	}

	public String getPPHONE() {
		return PPHONE;
	}
	public void setPPHONE(String pPHONE) {
		PPHONE = pPHONE;
	}
	public String getPW() {
		return PW;
	}
	public void setPW(String pW) {
		PW = pW;
	}
	public String getSUB() {
		return SUB;
	}
	public void setSUB(String sUB) {
		SUB = sUB;
	}
	public int getSUBNO() {
		return SUBNO;
	}
	public String getJUMIN() {
		return JUMIN;
	}
	public void setJUMIN(String jUMIN) {
		JUMIN = jUMIN;
	}
	public void setSUBNO(int sUBNO) {
		SUBNO = sUBNO;
	}
	@Override
	public String toString() {
		return "					--------------------------------------------------------------\n"+ 
	"					|			  교수 정보 			     |\n					| 			- 교수번호 : " + PNO + "		     |\n					| 			- 교수 이름 : " + PNAME + "		     |\n					| 			- 학과번호 : " + DEPTNO 
				+ "    	      	     |\n					| 			- 핸드폰 번호 : " + PPHONE + "	     |\n					| 			- 비밀번호 : "
				+ PW +"		     |\n					| 			- 주민번호 : " + JUMIN +  "	     |\n					| 			- 과목 번호 : " + SUBNO +"		     	     |\n					| 			- 담당 과목 : " + SUB + "	     |\n					--------------------------------------------------------------";
	}





}
