package com.fullneflower.orange.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fullneflower.orange.servlet.OrangeException;
import com.fullneflower.orange.util.ResourceProperty;

/**
 *  [説明]ログアウト処理を行う
 *  @author Sanpei
 */

public class FrontLogoutBean implements IFBean{
	/**
	 * [説明]ログアウト処理を行うメソッド
	 *       セッションを持っている場合、所持しているセッションを破棄しログイン画面へ遷移
	 * @author Sanpei
	 */
	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws OrangeException {
		System.out.println("-----FrontLogoutBean-----");
		try {
			HttpSession session = request.getSession(false);
			if(session != null){
				Object check = session.getAttribute("check");
				check = "0";
				session.setAttribute("check", check);
				session.invalidate();
				// 遷移先URLの宣言
				String url = ResourceProperty.getUrl("frontLogout.success");
				return url;
			}
			// 遷移先URLの宣言
			String url = ResourceProperty.getUrl("frontLogout.success");
			return url;
		} catch (Exception e) {
			throw new OrangeException(e);
		}
	}

}
