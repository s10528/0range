package com.fullneflower.orange.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fullneflower.orange.util.ResourceProperty;

/**
 * Servlet Filter implementation class EncodingFilter
 */
public class EncodingFilter implements Filter {

	/**
	 * [説明]デフォルトコンストラクタ
	 */
	public EncodingFilter() {
	}

	/**
	 * [説明]デストロイ
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * [説明]フィールド
	 */
	private String encoding;

	/**
	 * [概要]不正ログイン防止
	 * [説明]セッションの有無を確認し遷移先を決定
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try{
			request.setCharacterEncoding("UTF-8");
			// 遷移先URLの宣言（初期値:ログイン画面）
			String url = ResourceProperty.getUrl("backLogin.failure");
			String requestUrl = ((HttpServletRequest)request).getServletPath();
			System.out.println(requestUrl);
			if (url.equals(requestUrl) || "/page/back/error.jsp".equals(requestUrl)||"/login".equals(requestUrl)||requestUrl.startsWith("/image/")||requestUrl.startsWith("/page/front")||"/frontLogin".equals(requestUrl)) {
				chain.doFilter(request, response);
				return;
			}
			HttpSession session = ((HttpServletRequest)request).getSession(false);
			if (session == null){
				if ("/page/back/backLogin.jsp".equals(requestUrl)){
					/* ログイン画面からの遷移 */
					url = "/0range/page/back/backLogin.jsp";
					((HttpServletResponse)response).sendRedirect(url);
					return;
				}else{
					url = ResourceProperty.getUrl("filter.failure");
					((HttpServletResponse)response).sendRedirect(url);
					return;
				}
			}else{
				Object logincheck = 1;
				if (logincheck.equals(session.getAttribute("check"))) {
					url = "/0range/controller";
					chain.doFilter(request, response);
					return;
				}else {
					try {
						url = ResourceProperty.getUrl("filter.failure");
					} catch (OrangeException e) {
						e.printStackTrace();
					}
					((HttpServletResponse)response).sendRedirect(url);
				}
			}
		}catch (ServletException e){
			System.out.println("エラーが発生しました");
			e.printStackTrace();
			String url="/0range/page/back/error";
			request.getRequestDispatcher(url).forward(request,response);
		}catch (IOException e){
			System.out.println("エラーが発生しました");
			e.printStackTrace();
			String url="/0range/page/back/error";
			request.getRequestDispatcher(url).forward(request,response);
		} catch (OrangeException e) {
			System.out.println("エラーが発生しました");
			e.printStackTrace();
			String url="/0range/page/back/error";
			request.getRequestDispatcher(url).forward(request,response);
		}
	}

	/**
	 * [概要]初期化設定
	 * [説明]インスタンス生成時に１度呼び出される
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig filterconfig) throws ServletException {
		encoding = filterconfig.getInitParameter(encoding);
	}

}
