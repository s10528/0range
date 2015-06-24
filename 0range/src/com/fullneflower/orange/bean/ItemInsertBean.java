package com.fullneflower.orange.bean;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.orange.db.ConnectionManager;
import com.fullneflower.orange.db.ItemDAO;
import com.fullneflower.orange.db.ItemStockDAO;
import com.fullneflower.orange.servlet.OrangeException;
import com.fullneflower.orange.util.ResourceProperty;
import com.fullneflower.orange.vo.ItemStockVO;
import com.fullneflower.orange.vo.ItemVO;

/**
 * [説明]商品追加確認画面の処理を行うクラス
 * @author Nakajima
 */
public class ItemInsertBean implements IFBean {

	/**
	 * [説明]商品追加入力画面で入力された値を受け取り、
	 *       DBに値を登録するメソッド
	 * @author Nakajima
	 */
	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ConnectionManagerをインスタンス生成
		ConnectionManager cm = new ConnectionManager();
		// 遷移先URLの宣言（初期値:ログイン画面）
		String url = ResourceProperty.getUrl("backLogin.failure");
		//入力された値を受け取る
		String itemNo = request.getParameter("itemNo");
		String itemName = request.getParameter("itemName");
		String itemUrl = request.getParameter("itemUrl");
		String unitPrice = request.getParameter("unitPrice");
		String size = request.getParameter("size");
		String itemAssortmentCode = request.getParameter("itemAssortmentCode");
		String itemCategryCode = request.getParameter("itemCategoryCode");

		//ItemVOをインスタンス生成
		ItemVO itemVO = new ItemVO();
		//ItemStockVOをインスタンス生成
		ItemStockVO itemStockVO = new ItemStockVO();
		//ItemVOに値を詰める
		itemVO.setItemNo(itemNo);
		itemVO.setItemName(itemName);
		itemVO.setItemUrl(itemUrl);
		itemVO.setUnitPrice(unitPrice);
		itemVO.setSize(size);
		itemVO.setItemAssortmentCode(itemAssortmentCode);
		itemVO.setItemCategoryCode(itemCategryCode);
		//ItemVO,ItemStockVOに値を詰める
		itemStockVO.setItemNo(itemNo);

		// connectionを取得
		Connection connection = cm.getConnection();
		try{
			//ItemDAOをインスタンス生成
			ItemDAO itemDAO = new ItemDAO(connection);
			//ItemDAOの呼び出し
			itemDAO.insert(itemVO);
			//ItemStockDAOをインスタンス生成
			ItemStockDAO itemStockDAO = new ItemStockDAO(connection);
			//ItemStockDAOの呼び出し
			itemStockDAO.insert(itemStockVO);
			//URL遷移先の決定
			cm.commit();
			ItemMaintenanceBean itemMaintenanceBean = new ItemMaintenanceBean();
			url = itemMaintenanceBean.service(request, response);
			//成功メッセージを表示
			String msg = ResourceProperty.getMessage("itemInsertBean.success");
			request.setAttribute("errMsg", msg);
		}catch(Exception e) {
			throw new OrangeException(e);
		} finally {
			// Connectionの切断
			cm.closeConnection();
		}
		return url;
	}
}
