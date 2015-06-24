package com.fullneflower.orange.bean;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.orange.db.ConnectionManager;
import com.fullneflower.orange.db.ItemDAO;
import com.fullneflower.orange.servlet.OrangeException;
import com.fullneflower.orange.util.ResourceProperty;
import com.fullneflower.orange.vo.ItemVO;

/**
 * [機能]商品更新確認画面の処理を行う
 * @author Nakajima
 *
 */
public class ItemUpdateBean implements IFBean {

	/**
	 * [機能]商品更新確認画面の処理を行うメソッド
	 * [説明]入力された商品番号、商品名、商品画像、単価、寸法、種別、カテゴリーの更新を行う
	 * [処理結果]更新完了メッセージを表示、商品一覧画面に遷移
	 * @author Nakajima
	 */
	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ConnectionManagerをインスタンス生成
		ConnectionManager cm = new ConnectionManager();
		// 遷移先URLの宣言（初期値:ログイン画面）
		String url = ResourceProperty.getUrl("bean.ItemUpdateBean.success");
		//入力された値を受け取る
		String itemNo = request.getParameter("itemNo");
		String itemName = request.getParameter("itemName");
		String itemUrl = request.getParameter("itemUrl");
		String unitPrice = request.getParameter("unitPrice");
		String size = request.getParameter("size");
		String itemAssortmentCode = request.getParameter("itemAssortmentCode");
		String itemCategoryCode = request.getParameter("itemCategoryCode");
		String itemDF = request.getParameter("itemDF");
		//ItemVOをインスタンス生成
		ItemVO itemVO = new ItemVO();
		//ItemVOに値を詰める
		itemVO.setItemNo(itemNo);
		itemVO.setItemName(itemName);
		itemVO.setItemUrl(itemUrl);
		itemVO.setUnitPrice(unitPrice);
		itemVO.setSize(size);
		itemVO.setItemAssortmentCode(itemAssortmentCode);
		itemVO.setItemCategoryCode(itemCategoryCode);
		itemVO.setItemDF(itemDF);
		// connectionを取得
		Connection connection = cm.getConnection();
		try{
			//ItemDAOをインスタンス生成
			ItemDAO itemDAO = new ItemDAO(connection);
			//ItemDAOの呼び出し
			int update = itemDAO.update(itemVO);
			if (update == 0) {
				String msg =ResourceProperty.getMessage("item_maint.error.itemempty");
				request.setAttribute("errMsg", msg);
			}else {
				String msg =ResourceProperty.getMessage("itemUpdateBean.success");
				request.setAttribute("errMsg", msg);
			}
			//URL遷移先の決定
			cm.commit();
			ItemMaintenanceBean itemMaintenanceBean = new ItemMaintenanceBean();
			url = itemMaintenanceBean.service(request, response);
		}catch(Exception e) {
			throw new OrangeException(e);
		} finally {
			// Connectionの切断
			cm.closeConnection();
		}
		return url;
	}
}
