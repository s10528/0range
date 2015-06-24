package com.fullneflower.orange.bean;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.orange.db.ConnectionManager;
import com.fullneflower.orange.db.ItemAssortmentDAO;
import com.fullneflower.orange.db.ItemCategoryDAO;
import com.fullneflower.orange.servlet.OrangeException;
import com.fullneflower.orange.util.ResourceProperty;
import com.fullneflower.orange.util.SanitizationUtil;
import com.fullneflower.orange.vo.ItemAssortmentVO;
import com.fullneflower.orange.vo.ItemCategoryVO;
import com.fullneflower.orange.vo.ItemVO;

/**
 * [説明] 商品追加入力画面の処理を行う
 * @author Kawamori,Sanpei
 *
 */
public class ItemInsertMoveBean implements IFBean {

	/**
	 *[説明]商品追加入力画面で入力された値を保持する
	 * @author Kawamori,Sanpei
	 */
	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws OrangeException {
		//入力された値の受け取り
		String jspName =request.getParameter("action").toString();
		// 遷移先URLの宣言（初期値:ログイン画面）
		String url = ResourceProperty.getUrl("insert.success");

		// ConnectionManagerをインスタンス生成
		ConnectionManager cm = new ConnectionManager();
		Connection connection = cm.getConnection();

		// ItemVOをインスタンス生成
		ItemVO itemVO = new ItemVO();

		//戻るボタンから来た場合
		if("insert2".equals(jspName)||"insertTransmit".equals(jspName) ){
			String item_no = request.getParameter("itemNo");
			String item_name = request.getParameter("itemName");
			String item_url = request.getParameter("itemUrl");
			String unit_price = request.getParameter("unitPrice");
			String size = request.getParameter("size");
			String item_assortment_code = request.getParameter("itemAssortmentCode");
			String item_category_code = request.getParameter("itemCategoryCode");

			// サニタイジング
			item_no = SanitizationUtil.HTMLEscape(item_no);
			item_name = SanitizationUtil.HTMLEscape(item_name);
			item_url = SanitizationUtil.HTMLEscape(item_url);
			unit_price = SanitizationUtil.HTMLEscape(unit_price);
			size = SanitizationUtil.HTMLEscape(size);
			item_assortment_code = SanitizationUtil.HTMLEscape(item_assortment_code);
			item_category_code = SanitizationUtil.HTMLEscape(item_category_code);

			// ItemVOに値を詰める
			itemVO.setItemNo(item_no);
			itemVO.setItemName(item_name);
			itemVO.setItemUrl(item_url);
			itemVO.setUnitPrice(unit_price);
			itemVO.setSize(size);
			itemVO.setItemAssortmentCode(item_assortment_code);
			itemVO.setItemCategoryCode(item_category_code);

			ArrayList<ItemCategoryVO> categoryList;
			ArrayList<ItemAssortmentVO> assortList;
			try{
			//ItemAssortmentDAOをインスタンス生成
			ItemAssortmentDAO assortmentDAO = new ItemAssortmentDAO(connection);
			//itemDAOのsearchメソッド利用
			assortList = assortmentDAO.allSearch();

			//ItemAssortmentDAOをインスタンス生成
			ItemCategoryDAO categoryDAO = new ItemCategoryDAO(connection);
			//itemDAOのsearchメソッド利用
			categoryList = categoryDAO.allSearch();
			} catch(Exception e) {
				throw new OrangeException(e);
			} finally {
				// Connectionの切断
				cm.closeConnection();
			}

			//requestに詰める
			request.setAttribute("itemNo", item_no);
			request.setAttribute("itemName", item_name);
			request.setAttribute("itemUrl", item_url);
			request.setAttribute("unitPrice", unit_price);
			request.setAttribute("size", size);
			request.setAttribute("itemAssortmentCode", item_assortment_code);
			request.setAttribute("itemCategoryCode", item_category_code);
			request.setAttribute("assortList", assortList);
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("itemVO", itemVO);
			return url;
		}

		try{
			//ItemAssortmentDAOをインスタンス生成
			ItemAssortmentDAO assortmentDAO = new ItemAssortmentDAO(connection);
			//itemDAOのsearchメソッド利用
			ArrayList<ItemAssortmentVO> assortList = assortmentDAO.allSearch();
			//requestでitemvoListを詰める
			request.setAttribute("assortList", assortList);
			//ItemAssortmentDAOをインスタンス生成
			ItemCategoryDAO categoryDAO = new ItemCategoryDAO(connection);
			//itemDAOのsearchメソッド利用
			ArrayList<ItemCategoryVO> categoryList = categoryDAO.allSearch();
			//requestでitemvoListを詰める
			request.setAttribute("categoryList", categoryList);
		} catch(Exception e) {
			throw new OrangeException(e);
		} finally {
			// Connectionの切断
			cm.closeConnection();
		}
		return url;
	}
}
