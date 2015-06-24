package com.fullneflower.orange.bean;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.orange.db.ConnectionManager;
import com.fullneflower.orange.db.ItemAssortmentDAO;
import com.fullneflower.orange.db.ItemCategoryDAO;
import com.fullneflower.orange.db.ItemDAO;
import com.fullneflower.orange.servlet.OrangeException;
import com.fullneflower.orange.util.ResourceProperty;
import com.fullneflower.orange.util.SanitizationUtil;
import com.fullneflower.orange.vo.ItemAssortmentVO;
import com.fullneflower.orange.vo.ItemCategoryVO;
import com.fullneflower.orange.vo.ItemVO;

/**
 * [説明]商品一覧画面の商品名リンクの処理を行うクラス
 * @author Wada,Kawamori
 */
public class ItemUpdateMoveBean implements IFBean{

	/**
	 * [説明]商品名のitemNoの値を受け取り、
	 *       DBから商品情報を取り出すメソッド
	 * @author Wada,Kawamori
	 */
	public String service(HttpServletRequest request, HttpServletResponse response) throws OrangeException {
		String jspName =request.getParameter("action").toString();
		String url = ResourceProperty.getUrl("itemUpdate.success");

		// ItemVOをインスタンス生成
		ItemVO itemVO = new ItemVO();
		// ConnectionManagerをインスタンス生成
		ConnectionManager cm = new ConnectionManager();
		// connectionを取得
		Connection connection = cm.getConnection();

		if ("update".equals(jspName)) {
			//入力された値の受け取り
			String itemNo= request.getParameter("itemNo");
			// ItemVOに値を詰める
			itemVO.setItemNo(itemNo);

			try{
				//URL指定
				url=ResourceProperty.getUrl("itemUpdate.success");
				//ItemDAOをインスタンス生成
				ItemDAO itemDAO = new ItemDAO(connection);
				//itemDAOのsearchメソッド利用
				itemVO= itemDAO.search(itemVO);
				//requestでitemvoVOを詰める
				request.setAttribute("itemVO", itemVO);

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
		}else if ("update2".equals(jspName)||"updateTransmit".equals(jspName)) {
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

			// リストの準備
			ArrayList<ItemAssortmentVO> assortList;
			ArrayList<ItemCategoryVO> categoryList;
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
			//requestでitemvoListを詰める
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
		return url;
	}
}
