package com.fullneflower.orange.bean;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fullneflower.orange.db.ConnectionManager;
import com.fullneflower.orange.db.ItemDAO;
import com.fullneflower.orange.servlet.OrangeException;
import com.fullneflower.orange.util.ResourceProperty;
import com.fullneflower.orange.vo.ItemVO;

/**
 * [説明]商品一覧表示画面 (P031)の商品一覧を表示するListを作成するクラス
 * @author Wada
 */

public class ItemMaintenanceBean implements IFBean {

	/**
	 * [説明]商品一覧表示画面 (P031)の商品一覧を表示するListを作成するメソッド
	 * @return url
	 * @throws Exception
	 * @author Wada
	 */
	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ConnectionManagerをインスタンス生成
		ConnectionManager cm = new ConnectionManager();
		// 遷移先URLの宣言（初期値:ログイン画面）
		String url = ResourceProperty.getUrl("backLogin.failure");
		// connectionを取得
		Connection connection = cm.getConnection();

		try{
			//URL指定
			url=ResourceProperty.getUrl("itemMaintenance.success");
			//StaffDAOをインスタンス生成
			ItemDAO itemDAO = new ItemDAO(connection);
			ArrayList<ItemVO> itemList= itemDAO.allSearch();
			HttpSession session = request.getSession(false);
			session.setAttribute("itemvoList", itemList);
		} catch(Exception e) {
			throw new OrangeException(e);
		} finally {
			// Connectionの切断
			cm.closeConnection();
		}
		return url;
	}
}
