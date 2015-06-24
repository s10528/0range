package com.fullneflower.orange.bean;

import java.sql.Connection;
import java.util.ArrayList;

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
 * [説明]商品削除確認画面の処理を行う
 * @author Nakajima,Sanpei,Kawamori
 *
 */
public class ItemDeleteCheckBean implements IFBean {

	/**
	 * [機能]削除のチェックボックスにチェックが入っていた商品の在庫有無を確認するメソッド
	 * [説明]選択された商品番号、商品名の削除を行う
	 * [処理結果]削除完了メッセージを表示、商品一覧画面に遷移
	 * @author Nakajima,Sanpei,Kawamori
	 */
	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionManager cm = new ConnectionManager();
		// 遷移先URLの宣言（初期値：商品一覧表示に戻る）
		String url = ResourceProperty.getUrl("itemMaintenance");
		//requestのnullチェック
		String check = request.getParameter("delete");
		if(check == null || check.length() == 0){
			// 遷移先URLの宣言
			ItemMaintenanceBean imBean = new ItemMaintenanceBean();
			url = imBean.service(request, response);
			request.setAttribute("action", "itemMaintenance");
			//エラーメッセージ
			String msg = ResourceProperty.getMessage("item_maint.error.deletenull");
			request.setAttribute("errMsg", msg);
			return url;
		}
		//入力された値（商品番号）の受け取り
		String[] aryItem = request.getParameterValues("delete");
		ArrayList<ItemVO> itemDelList = new ArrayList<ItemVO>();
		try{
			// connectionを取得
			Connection connection = cm.getConnection();
			//ItemStockDAOをインスタンス生成
			ItemStockDAO itemstockDAO = new ItemStockDAO(connection);
			//ItemDAOをインスタンス生成
			ItemDAO itemDAO = new ItemDAO(connection);
			for(String items : aryItem){
				String itemNo = items;
				//ItemStockVOをインスタンス生成
				ItemStockVO itemstockVO = new ItemStockVO();
				//ItemStockVOに値を詰める
				itemstockVO.setItemNo(itemNo);
				//ItemStockDAOの呼び出し
				itemstockVO = itemstockDAO.search(itemstockVO);
				if(itemstockVO.getItemStock() != 0){
					//URL遷移先の決定
					ItemMaintenanceBean imBean = new ItemMaintenanceBean();
					url = imBean.service(request, response);
					request.setAttribute("action", "itemMaintenance");
					//失敗メッセージを表示
					String errmsg = ResourceProperty.getMessage("item_maint.error.delete");
					request.setAttribute("errMsg", errmsg);
					return url;
				}else{
					//在庫0の削除対象商品の情報を取得する
					//ItemVOをインスタンス生成
					ItemVO itemVO = new ItemVO();
					//ItemVOに値を詰める
					itemVO.setItemNo(itemNo);
					itemDelList.add(itemDAO.search(itemVO));
				}
			}
		}catch(Exception e){
			throw new OrangeException(e);
		}finally{
			// Connectionの切断
			cm.closeConnection();
		}
		request.setAttribute("itemDelList", itemDelList);
		url = ResourceProperty.getUrl("bean.ItemDeleteCheckBean.success");
		return url;
	}
}
