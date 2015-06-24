package com.fullneflower.orange.vo;

/**
 * [説明]顧客情報を扱うクラス
 * @author Wada
 *
 */

public class CustomerVO {
	/**顧客ID  */
	private String customerID;
	/**顧客名  */
	private String companyName;
	/**顧客住所  */
	private String address;
	/**顧客電話番号  */
	private String telno;
	/**顧客担当部署  */
	private String dept;
	/**顧客担当者氏名  */
	private String customerName;
	/**顧客担当者氏名(かな)  */
	private String customerKana;
	/**顧客メールアドレス  */
	private String email;
	/**パスワード  */
	private String password;
	/**論理削除フラグ  */
	private String customerDF;

	/**
	 * プロパティのアクセッサー
	 */

	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerKana() {
		return customerKana;
	}
	public void setCustomerKana(String customerKana) {
		this.customerKana = customerKana;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCustomerDF() {
		return customerDF;
	}
	public void setCustomerDF(String customerDF) {
		this.customerDF = customerDF;
	}
}
