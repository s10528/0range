package com.fullneflower.orange.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.orange.util.ResourceProperty;
import com.fullneflower.orange.util.SanitizationUtil;
import com.fullneflower.orange.vo.CustomerVO;

/**
 * [説明] 顧客登録画面への遷移の処理を行う
 * @author Tanaka
 *
 */
public class CustomerEntryMoveBean implements IFBean{

	/**
	 *[説明]商品追加入力画面で入力された値を保持する
	 * @author Kawamori,Sanpei
	 */
	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//入力された値の受け取り
		String jspName =request.getParameter("action").toString();
		String url = ResourceProperty.getUrl("entry.success");

		//戻るボタンから来た場合
		// CustomerVOをインスタンス生成
		CustomerVO customerVO = new CustomerVO();
		if("entryCheck".equals(jspName)||"entry.back".equals(jspName) ){
			String company_name = request.getParameter("companyName");
			String address = request.getParameter("address");
			String telno = request.getParameter("telno");
			String dept = request.getParameter("dept");
			String customer_name = request.getParameter("customerName");
			String customer_kana = request.getParameter("customerKana");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			// サニタイジング
			company_name = SanitizationUtil.HTMLEscape(company_name);
			address = SanitizationUtil.HTMLEscape(address);
			telno = SanitizationUtil.HTMLEscape(telno);
			dept = SanitizationUtil.HTMLEscape(dept);
			customer_name = SanitizationUtil.HTMLEscape(customer_name);
			customer_kana = SanitizationUtil.HTMLEscape(customer_kana);

			// CustomerVOに値を詰める
			customerVO.setCompanyName(company_name);
			customerVO.setAddress(address);
			customerVO.setTelno(telno);
			customerVO.setDept(dept);
			customerVO.setCustomerName(customer_name);
			customerVO.setCustomerKana(customer_kana);
			customerVO.setEmail(email);
			customerVO.setPassword(password);
			System.out.println(customerVO.getEmail());

			//requestに詰める
			request.setAttribute("customerVO", customerVO);
			return url;
		}
		return url;
	}
}
