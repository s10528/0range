package com.fullneflower.orange.vo;


/**
 * [機能]社員情報を格納するプロパティクラス
 * [説明]DBのstaffテーブルと対応
 * @author Kawamori
 *
 */
public class StaffVO {
	/**
	 * 社員番号
	 */
	String staffId;

	/**
	 * 社員名
	 */
	String staffNameString;
	/**
	 * パスワード
	 */
	String password;
	/**
	 * デリートフラグ
	 */
	String staffDF;

	/**
	 * [機能]アクセッサー
	 * [説明]各フィールドへのゲッター及びセッター
	 * @return
	 */
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffNameString() {
		return staffNameString;
	}
	public void setStaffNameString(String staffNameString) {
		this.staffNameString = staffNameString;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStaffDF() {
		return staffDF;
	}
	public void setStaffDF(String staffDF) {
		this.staffDF = staffDF;
	}


}
