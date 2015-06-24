package com.fullneflower.orange.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.orange.bean.IFBean;
import com.fullneflower.orange.util.ResourceProperty;

/**
 * [説明]遷移先をコントロールするクラス
 * @author kawamori
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			process(request,response);
		} catch (OrangeException e) {
			System.out.println("エラーが発生しました");
			e.printStackTrace();
			String url="/0range/page/back/backlogin";
			request.getRequestDispatcher(url).forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request,response);
		} catch (OrangeException e) {
			System.out.println("エラーが発生しました");
			e.printStackTrace();
			String url="/0range/page/back/backlogin";
			request.getRequestDispatcher(url).forward(request,response);
		}
	}

	/**
	 * [説明]doPost,doGet共通リクエスト処理メソッド
	 * input画面のhiddenの値を受け、サービスクラスを選定。
	 * サービスクラスのexecuteメソッドを実行する
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws OrangeException
	 * @author Kawamori
	 */
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, OrangeException {
		// 遷移先URLの宣言（初期値:ログイン画面）
		String url = ResourceProperty.getUrl("error");
		try {
			String beanName = request.getParameter("action");
			IFBean bean = createBean(beanName);
			if (bean != null) {
				url = bean.service(request, response);
			} else {
				url = ResourceProperty.getUrl("backLogin.failure");
			}
			request.setAttribute("action", beanName);
			request.getRequestDispatcher(url).forward(request,response);
		} catch (OrangeException e) {
			System.out.println("エラーが発生しました");
			e.printStackTrace();
			url=ResourceProperty.getUrl("error");
			request.getRequestDispatcher(url).forward(request,response);
		} catch(Exception e){
			System.out.println("エラーが発生しました");
			e.printStackTrace();
			url=ResourceProperty.getUrl("error");
			request.getRequestDispatcher(url).forward(request,response);
		}
	}

	/**
	 * [説明]指定されたbeanクラスのインスタンス生成をし、IFBean型を返すメソッド
	 * @param beanName
	 * @author Kawamori
	 */
	private IFBean createBean(String beanName) {
		IFBean bean = null;
		try {
			String clazz = ResourceProperty.getUrl(beanName);
			Class cls = Class.forName(clazz);
			bean = (IFBean)cls.newInstance();
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
		return bean;
	}
}
