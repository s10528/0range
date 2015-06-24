package com.fullneflower.orange.bean;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.orange.db.ConnectionManager;
import com.fullneflower.orange.db.CustomerDAO;
import com.fullneflower.orange.servlet.OrangeException;
import com.fullneflower.orange.util.ResourceProperty;
import com.fullneflower.orange.vo.CustomerVO;

public class CustomerEntryBean implements IFBean {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("-----customerEntryBean-----");
		// ConnectionManagerをインスタンス生成
		ConnectionManager cm = new ConnectionManager();
		// 遷移先URLの宣言（初期値:顧客登録完了画面）
		String url = ResourceProperty.getUrl("entryConfirm.success");
		//入力された値を受け取る
		//String  customerID= request.getParameter("customerID");
		String  companyName= request.getParameter("companyName");
		String  address= request.getParameter("address");
		String  telno= request.getParameter("telno");
		String  dept= request.getParameter("dept");
		String  customerName= request.getParameter("customerName");
		String  customerKana= request.getParameter("customerKana");
		String  email= request.getParameter("email");
		String  password= request.getParameter("password");
		//String  customerDF= request.getParameter("customerDF");

		//CustomerVOをインスタンス生成
		CustomerVO customerVO = new CustomerVO();
		//CustomerVOに値を詰める
		//customerVO.setCustomerID(customerID);
		customerVO.setCompanyName(companyName);
		customerVO.setAddress(address);
		customerVO.setTelno(telno);
		customerVO.setDept(dept);
		customerVO.setCustomerName(customerName);
		customerVO.setCustomerKana(customerKana);
		customerVO.setEmail(email);
		customerVO.setPassword(password);
		//customerVO.setCustomerDF(customerDF);

		// connectionを取得
		Connection connection = cm.getConnection();
		try{
			//CustomerDAOをインスタンス生成
			CustomerDAO customerDAO = new CustomerDAO(connection);
			//CustomerDAOの呼び出し
			customerDAO.insert(customerVO);
			String customerID = customerDAO.IDSearch();
			//リクエストに詰めます
			request.setAttribute("customerID", customerID);
			//URL遷移先の決定
			url=ResourceProperty.getUrl("entryConfirm.success");
			cm.commit();
		}catch(Exception e) {
			throw new OrangeException(e);
		} finally {
			// Connectionの切断
			cm.closeConnection();
		}
		return url;
	}
}

