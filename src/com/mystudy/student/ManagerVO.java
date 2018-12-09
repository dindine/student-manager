package com.mystudy.student;

public class ManagerVO {
	private int MNO;
	private String MPW;



	public ManagerVO(int MNO, String MPW) {
		super();
		this.MNO = MNO;
		this.MPW = MPW;

	}



	public int getMNO() {
		return MNO;
	}



	public void setMNO(int mNO) {
		MNO = mNO;
	}



	public String getMPW() {
		return MPW;
	}



	public void setMPW(String mPW) {
		MPW = mPW;
	}



	@Override
	public String toString() {
		return "ManagerVO [관리자번호 : " + MNO + ", 비밀번호 : " + MPW + "]";
	}

}
