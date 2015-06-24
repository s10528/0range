package com.fullneflower.orange.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.orange.util.InputChecker;
import com.fullneflower.orange.util.ResourceProperty;
import com.fullneflower.orange.util.SanitizationUtil;

/**
 * [説明]顧客登録入力の確認を行う
 * @author Sanpei
 */

public class CustomerEntryCheckBean implements IFBean {
	/**
	 *[説明] 顧客登録入力の確認を行うメソッド
	 * @param request
	 * @param response
	 * @return url 遷移先を指定するURL
	 * @auther Sanpei
	 */
	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("-----CustomerEntryCheckBean-----");

		//入力された値の受け取り
		String company_name = request.getParameter("companyName");
		String address = request.getParameter("address");
		String tel_no = request.getParameter("telno");
		String customer_name = request.getParameter("customerName");
		String customer_kana = request.getParameter("customerKana");
		String dept = request.getParameter("dept");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password_conf = request.getParameter("passwordConfirm");

		//記号の置換
		company_name = SanitizationUtil.HTMLEscape(company_name);
		address = SanitizationUtil.HTMLEscape(address);
		tel_no = SanitizationUtil.HTMLEscape(tel_no);
		customer_name = SanitizationUtil.HTMLEscape(customer_name);
		customer_kana = SanitizationUtil.HTMLEscape(customer_kana);
		email = SanitizationUtil.HTMLEscape(email);
		password = SanitizationUtil.HTMLEscape(password);
		password_conf = SanitizationUtil.HTMLEscape(password_conf);



		// 遷移先URLの宣言（初期値：顧客登録画面）
		CustomerEntryMoveBean customerEntryMoveBean = new CustomerEntryMoveBean();
		String url = customerEntryMoveBean.service(request, response);

		//入力チェックエラー管理の変数の宣言
		int error_check = 0;

		//company_nameの空文字チェック
		if (InputChecker.isEmpty(company_name)) {
			System.out.println("空文字チェック「会社名」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.null.company_name");
			request.setAttribute("errMsg1", msg);
			error_check = 1;
		}

		//adressの空文字チェック
		if (InputChecker.isEmpty(address)) {
			System.out.println("空文字チェック「住所」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.null.address");
			request.setAttribute("errMsg2", msg);
			error_check = 1;
		}

		//tel_noの空文字チェック
		if (InputChecker.isEmpty(tel_no)) {
			System.out.println("空文字チェック「電話番号」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.null.telno");
			request.setAttribute("errMsg3", msg);
			error_check = 1;
		}

		//customer_nameの空文字チェック
		if (InputChecker.isEmpty(customer_name)) {
			System.out.println("空文字チェック「顧客担当者（漢字）」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.null.customer_name");
			request.setAttribute("errMsg4", msg);
			error_check = 1;
		}

		//customer_kanaの空文字チェック
		if (InputChecker.isEmpty(customer_kana)) {
			System.out.println("空文字チェック「顧客担当者（かな）」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.null.customer_kana");
			request.setAttribute("errMsg5", msg);
			error_check = 1;
		}

		//emailの空文字チェック
		if (InputChecker.isEmpty(email)) {
			System.out.println("空文字チェック「メールアドレス）」");
		} else{
			//emailの桁数チェック
			if (!InputChecker.custMailLength(email)) {
				System.out.println("桁数チェック「メールアドレス」 NG!");
				String msg = ResourceProperty.getMessage("front_entry.error.over30");
				request.setAttribute("errMsg7", msg);
				error_check = 1;
			}

			//emailの半角英数記号チェック
			if (!InputChecker.isAlphaNumericSymbol(email)) {
				System.out.println("半角英数記号チェック「メールアドレス」 NG!");
				String msg = ResourceProperty.getMessage("front_entry.error.miss_email");
				request.setAttribute("errMsg7", msg);
				error_check = 1;
			}

			//emailのフォーマットチェック
			if (!InputChecker.isMail(email)) {
				System.out.println("フォーマットチェック「メールアドレス」 NG!");
				String msg = ResourceProperty.getMessage("front_entry.error.miss_email");
				request.setAttribute("errMsg7", msg);
				error_check = 1;
			}
		}

		//passwordの空文字チェック
		if (InputChecker.isEmpty(password)) {
			System.out.println("空文字チェック「パスワード」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.null.password");
			request.setAttribute("errMsg8", msg);
			error_check = 1;
		}

		//password_confの空文字チェック
		if (InputChecker.isEmpty(password_conf)) {
			System.out.println("空文字チェック「パスワード（確認）」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.null.password_confirm");
			request.setAttribute("errMsg9", msg);
			error_check = 1;
		}

		//company_nameの桁数チェック
		if (!InputChecker.itemUrlLength(company_name)) {
			System.out.println("桁数チェック「会社名」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.over50");
			request.setAttribute("errMsg1", msg);
			error_check = 1;
		}

		//addressの桁数チェック
		if (!InputChecker.adressLength(address)) {
			System.out.println("桁数チェック「住所」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.over100");
			request.setAttribute("errMsg2", msg);
			error_check = 1;
		}

		//tel_noの桁数チェック
		if (!InputChecker.adressLength(tel_no)) {
			System.out.println("桁数チェック「電話番号」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.miss_telno");
			request.setAttribute("errMsg3", msg);
			error_check = 1;
		}

		//tel_noの半角英数記号チェック
		if (!InputChecker.isAlphaNumericSymbol(tel_no)) {
			System.out.println("半角英数記号チェック「電話番号」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.miss_telno");
			request.setAttribute("errMsg3", msg);
			error_check = 1;
		}

		//tel_noのフォーマットチェック
		if (!InputChecker.isTelno(tel_no)) {
			System.out.println("フォーマットチェック「電話番号」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.miss_telno");
			request.setAttribute("errMsg3", msg);
			error_check = 1;
		}

		//customer_nameの桁数チェック
		if (!InputChecker.itemNameLength(customer_name)) {
			System.out.println("桁数チェック「顧客担当者名（漢字）」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.over20");
			request.setAttribute("errMsg4", msg);
			error_check = 1;
		}

		//customer_kanaの桁数チェック
		if (!InputChecker.custNameKanaLength(customer_kana)) {
			System.out.println("桁数チェック「顧客担当者名（かな）」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.over40");
			request.setAttribute("errMsg5", msg);
			error_check = 1;
		}

		//customer_kanaの半角英数記号チェック
		if (!InputChecker.isHiragana(customer_kana)) {
			System.out.println("ひらがなチェック「顧客担当者名（かな）」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.miss_customer_kana");
			request.setAttribute("errMsg5", msg);
			error_check = 1;
		}

		//deptの桁数チェック
		if (!InputChecker.itemNameLength(dept)) {
			System.out.println("桁数チェック「部署」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.over20");
			request.setAttribute("errMsg6", msg);
			error_check = 1;
		}


		//passwordの桁数チェック
		if (!InputChecker.passwordLength(password)) {
			System.out.println("桁数チェック「パスワード」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.password.over8");
			request.setAttribute("errMsg8", msg);
			error_check = 1;
		}

		//passwordの半角英数字チェック
		if (!InputChecker.isAlphaNumeric(password)) {
			System.out.println("半角英数字チェック「パスワード」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.miss_password");
			request.setAttribute("errMsg8", msg);
			error_check = 1;
		}

		//passwordの桁数チェック
		if (!InputChecker.passwordLength(password_conf)) {
			System.out.println("桁数チェック「パスワード（確認）」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.password_confirm.over8");
			request.setAttribute("errMsg9", msg);
			error_check = 1;
		}

		//passwordの半角英数字チェック
		if (!InputChecker.isAlphaNumeric(password_conf)) {
			System.out.println("半角英数字チェック「パスワード（確認）」 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.miss_password_confirm");
			request.setAttribute("errMsg9", msg);
			error_check = 1;
		}

		//パスワードの一致確認
		if(!password.equals(password_conf)){
			System.out.println("パスワードの一致確認 NG!");
			String msg = ResourceProperty.getMessage("front_entry.error.disagreement_password");
			request.setAttribute("errMsg8", msg);
			error_check = 1;
		}

		//エラーチェック変数が1のとき入力画面に戻る
		if(error_check == 1){
			return url;
		}


		//つめる
		request.setAttribute("companyName", company_name);
		request.setAttribute("address", address);
		request.setAttribute("telno", tel_no);
		request.setAttribute("customerName", customer_name);
		request.setAttribute("customerKana", customer_kana);
		request.setAttribute("dept", dept);
		request.setAttribute("email", email);
		request.setAttribute("password", password);

		//遷移先URLの宣言
		url=ResourceProperty.getUrl("entryCheck.success");
		return url;
	}

}
