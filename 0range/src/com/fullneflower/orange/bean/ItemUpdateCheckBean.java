package com.fullneflower.orange.bean;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.orange.db.ConnectionManager;
import com.fullneflower.orange.db.ItemAssortmentDAO;
import com.fullneflower.orange.db.ItemCategoryDAO;
import com.fullneflower.orange.servlet.OrangeException;
import com.fullneflower.orange.util.InputChecker;
import com.fullneflower.orange.util.ResourceProperty;
import com.fullneflower.orange.util.SanitizationUtil;


/**
 *[説明]商品更新入力画面 (P034)の入力項目が形式外データではないかチェックするクラス
 * @author Sanpei,Kawamori
 *
 */
public class ItemUpdateCheckBean implements IFBean {

	/**
	 * [説明]登録入力・更新入力チェックを行うメソッド
	 * @return url
	 * @throws Exception
	 * @auther Sanpei,Kawamori
	 */

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 遷移先URLの宣言（初期値：）
		ItemUpdateMoveBean itemUpdateMoveBean = new ItemUpdateMoveBean();
		String url = itemUpdateMoveBean.service(request, response);
		//入力された値の受け取り
		String item_no = request.getParameter("itemNo");
		String item_name = request.getParameter("itemName");
		String item_url = request.getParameter("itemUrl");
		String unit_price = request.getParameter("unitPrice");
		String size = request.getParameter("size");
		String item_assortment_code = request.getParameter("itemAssortmentCode");
		String item_category_code = request.getParameter("itemCategoryCode");
		String item_category_name = getCategoryName(item_category_code);
		String item_assortment_name = getAssortmentName(item_assortment_code);

		//使い方例) email = SanitizationUtil.HTMLEscape(email);
		item_no = SanitizationUtil.HTMLEscape(item_no);
		item_name = SanitizationUtil.HTMLEscape(item_name);
		item_url = SanitizationUtil.HTMLEscape(item_url);
		unit_price = SanitizationUtil.HTMLEscape(unit_price);
		size = SanitizationUtil.HTMLEscape(size);
		item_assortment_code = SanitizationUtil.HTMLEscape(item_assortment_code);
		item_category_code = SanitizationUtil.HTMLEscape(item_category_code);
		item_category_name = SanitizationUtil.HTMLEscape(item_category_name);
		item_assortment_name = SanitizationUtil.HTMLEscape(item_assortment_name);

		//入力チェックエラー管理の変数の宣言
		int error_check = 0;

		//item_noの空文字チェック
		if (InputChecker.isEmpty(item_no)) {
			System.out.println("空文字チェック「商品番号」 NG!");
			String msg = ResourceProperty.getMessage("item_maint.error.item_id");
			request.setAttribute("errMsg2", msg);
			error_check = 1;
		}

		//item_nameの空文字チェック
		if (InputChecker.isEmpty(item_name)) {
			System.out.println("空文字チェック「商品名」 NG!");
			String msg = ResourceProperty.getMessage("item_maint.error.item_name");
			request.setAttribute("errMsg3", msg);
			error_check = 1;
		}

		//unit_priceの空文字チェック
		if (InputChecker.isEmpty(unit_price)) {
			System.out.println("空文字チェック「単価」 NG!");
			String msg = ResourceProperty.getMessage("item_maint.error.unit_price");
			request.setAttribute("errMsg5", msg);
			error_check = 1;
		}

		//sizeの空文字チェック
		if (InputChecker.isEmpty(size)) {
			System.out.println("空文字チェック「寸法」 NG!");
			String msg = ResourceProperty.getMessage("item_maint.error.size");
			request.setAttribute("errMsg6", msg);
			error_check = 1;
		}

		//item_noの桁数チェック
		if (!InputChecker.itemIdLength(item_no)) {
			System.out.println("桁数チェック「商品番号」 NG!");
			String msg = ResourceProperty.getMessage("item_maint.error.item_id");
			request.setAttribute("errMsg2", msg);
			error_check = 1;
		}

		//item_noの半角数字チェック
		if (!InputChecker.isNum(item_no)) {
			System.out.println("半角数字チェック「商品番号」 NG!");
			String msg = ResourceProperty.getMessage("item_maint.error.item_id");
			request.setAttribute("errMsg2", msg);
			error_check = 1;
		}

		//item_nameの桁数チェック
		if (!InputChecker.itemNameLength(item_name)) {
			System.out.println("桁数チェック「商品名」 NG!");
			String msg = ResourceProperty.getMessage("item_maint.error.item_name");
			request.setAttribute("errMsg3", msg);
			error_check = 1;
		}

		//item_urlの桁数チェック
		if (!InputChecker.itemUrlLength(item_url)) {
			System.out.println("桁数チェック「商品画像url」 NG!");
			String msg = ResourceProperty.getMessage("item_maint.error.item_url");
			request.setAttribute("errMsg4", msg);
			error_check = 1;
		}

		//item_urlの半角英数記号チェック
		if (!InputChecker.isAlphaNumericSymbol(item_url)) {
			System.out.println("半角英数記号チェック「商品画像url」 NG!");
			String msg = ResourceProperty.getMessage("item_maint.error.item_url");
			request.setAttribute("errMsg4", msg);
			error_check = 1;
		}

		//unit_priceの桁数チェック
		if (!InputChecker.unitPriceLength(unit_price)) {
			System.out.println("桁数チェック「単価」 NG!");
			String msg = ResourceProperty.getMessage("item_maint.error.unit_price");
			request.setAttribute("errMsg5", msg);
			error_check = 1;
		}

		//unit_priceの半角数字チェック
		if (!InputChecker.isNum(unit_price)) {
			System.out.println("半角数字チェック「単価」 NG!");
			String msg = ResourceProperty.getMessage("item_maint.error.unit_price");
			request.setAttribute("errMsg5", msg);
			error_check = 1;
		}

		//sizeのフォーマットチェック
		if (!InputChecker.isSize(size)) {
			System.out.println("フォーマットチェック「寸法」 NG!");
			String msg = ResourceProperty.getMessage("item_maint.error.size");
			request.setAttribute("errMsg6", msg);
			error_check = 1;
		}

		//エラーチェック変数が1のとき入力画面に戻る
		if(error_check == 1){
		return url;
		}

		//色々つめる
		request.setAttribute("itemNo", item_no);
		request.setAttribute("itemName", item_name);
		request.setAttribute("itemUrl", item_url);
		request.setAttribute("unitPrice", unit_price);
		request.setAttribute("size", size);
		request.setAttribute("itemAssortmentCode", item_assortment_code);
		request.setAttribute("itemCategoryCode", item_category_code);
		request.setAttribute("itemCategoryName", item_category_name);
		request.setAttribute("itemAssortmentName", item_assortment_name);
		request.setAttribute("action", "insertConfirm");

		//遷移先URLの宣言
		url=ResourceProperty.getUrl("updateTransmit.success");
		return url;
	}

	/**
	 * [説明]DBの種別テーブルから登録されている項目を取得するメソッド
	 * @param CategoryCode
	 * @return itemCategoryName
	 * @throws OrangeException
	 * @author Kawamori
	 */
	public String getCategoryName(String CategoryCode) throws OrangeException {
		try{
			ConnectionManager cm = new ConnectionManager();
			Connection connection = cm.getConnection();
			ItemCategoryDAO itemCategoryDAO = new ItemCategoryDAO(connection);
			String itemCategoryName = itemCategoryDAO.search(CategoryCode);
			return itemCategoryName;
		}catch(OrangeException e){
			throw new OrangeException(e);
		}
	}
	/**
	 * [説明]DBのカテゴリテーブルから登録されている項目を取得するメソッド
	 * @param AssortmentCode
	 * @return itemAssortmentName
	 * @throws OrangeException
	 * @author Kawamori
	 */
	public String getAssortmentName(String AssortmentCode) throws OrangeException {
		try{
			ConnectionManager cm = new ConnectionManager();
			Connection connection = cm.getConnection();
			ItemAssortmentDAO itemAssortmentDAO = new ItemAssortmentDAO(connection);
			String itemAssortmentName = itemAssortmentDAO.search(AssortmentCode);
			return itemAssortmentName;
		}catch(OrangeException e){
			throw new OrangeException(e);
		}
	}

}
