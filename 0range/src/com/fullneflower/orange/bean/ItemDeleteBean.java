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
 * [説明]選択された商品情報の削除を行う
 * @author Nakajima Sanpei
 *
 */
public class ItemDeleteBean implements IFBean {

	/**
	 * [説明]選択された商品情報の削除を行うメソッド
	 * @param request
	 * @param response
	 * @return url 遷移先を指定するURL
	 * @throws OrangeException
	 * @author Nakajima, Sanpei
	 */
	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConnectionManager cm = new ConnectionManager();
		// 遷移先URLの宣言（初期値:ログイン画面）
		String url = ResourceProperty.getUrl("backLogin.failure");

		//削除する商品番号の受け取り
		String[] aryItem = request.getParameterValues("itemNo");

		// connectionを取得
		Connection connection = cm.getConnection();
		try{
			for(String items : aryItem){
				String itemNo = items;
				//ItemStockVOをインスタンス生成
				ItemVO item = new ItemVO();
				//ItemStockVOに値を詰める
				item.setItemNo(itemNo);
				//ItemDAOをインスタンス生成
				ItemDAO itemDAO = new ItemDAO(connection);
				//ItemDAOの呼び出し
				int delete = itemDAO.delete(item);

				//削除件数が0件のとき
				if(delete == 0){
					String msg = ResourceProperty.getMessage("item_maint.error.itemempty");
					request.setAttribute("errMsg", msg);
					return url;
				}
			}
			cm.commit();
			//URL遷移先の決定
			ItemMaintenanceBean itemMaintenanceBean = new ItemMaintenanceBean();
			url = itemMaintenanceBean.service(request, response);
			// 成功メッセージ
			String msg = ResourceProperty.getMessage("itemDeleteBean.success");
			request.setAttribute("errMsg", msg);
		}catch(Exception e){
			throw new OrangeException(e);
		}finally{
			// Connectionの切断
			cm.closeConnection();
		}
		return url;
	}
}