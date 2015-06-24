package com.fullneflower.orange.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fullneflower.orange.servlet.OrangeException;
import com.fullneflower.orange.vo.ItemCategoryVO;

/**
 * [説明]DB上のカテゴリテーブルからカテゴリ名、もしくはカテゴリコードとカテゴリ名を取得するクラス
 * @author Tanaka,Nakajima,Kawamori
 */
public class ItemCategoryDAO {
	/** コネクション */
	private Connection connection;
	/**
	 * [説明]コンストラクタ
	 * @author Kawamori
	 */
	public ItemCategoryDAO(Connection connection){
		this.connection = connection;
	}

	/**
	 * [説明]DB上のカテゴリテーブルからカテゴリ名を取得するメソッド
	 * @param itemCategory
	 * @return itemCategoryName
	 * @throws OrangeException
	 * @author Tanaka
	 */
	public String search(String itemCategory) throws OrangeException{
		// ステートメントの定義
		PreparedStatement preparedStatement = null;
		try {
			// SQLの定義
			String sql = "SELECT item_category_name FROM item_category WHERE item_category_code=?";
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(sql);
			// SQLバインド変数への値設定
			preparedStatement.setString(1, itemCategory);
			// SQLの実行
			ResultSet result = preparedStatement.executeQuery();

			String itemCategoryName = "";
			while (result.next()) {
				itemCategoryName = result.getString("item_category_name");
			}
			return itemCategoryName;
		} catch (SQLException e) {
			throw new OrangeException("ITEMテーブルのUPDATEに失敗しました", e);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
					System.out.println("ステートメントの解放に成功しました");
				}
			} catch (SQLException e) {
				throw new OrangeException("ステートメントの解放に失敗しました", e);
			}
		}
	}

	/**
	 * [説明]各画面のプルダウンメニューに、商品がもともと持っている
	 * カテゴリ名を初期値に設定するためにカテゴリコードとカテゴリ名を取得するメソッド
	 * @return categoryList
	 * @throws OrangeException
	 * @author Wada
	 */
	public ArrayList<ItemCategoryVO> allSearch() throws OrangeException{
		PreparedStatement preparedStatement = null;
		try {
			// SQLの定義
			String sql = "SELECT ITEM_CATEGORY_CODE,ITEM_CATEGORY_NAME FROM ITEM_CATEGORY";
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(sql);
			// SQLバインド変数への値設定

			// SQLの実行
			ResultSet result = preparedStatement.executeQuery();
			ArrayList<ItemCategoryVO> categoryList = new ArrayList<ItemCategoryVO>();
			while(result.next()){
				String itemCategoryCode =result.getString("item_category_code");
				String itemCategoryName=result.getString("item_category_name");

				ItemCategoryVO itemCategoryVO = new ItemCategoryVO();
				itemCategoryVO.setItemCategoryCode(itemCategoryCode);
				itemCategoryVO.setItemCategoryName(itemCategoryName);
				categoryList.add(itemCategoryVO);
			}
			return categoryList;
		} catch (SQLException e) {
			throw new OrangeException("ITEMテーブルのSELECTに失敗しました", e);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
					System.out.println("ステートメントの解放に成功しました");
				}
			} catch (SQLException e) {
				throw new OrangeException("ステートメントの解放に失敗しました", e);
			}
		}
	}

	/**
	 * [機能]
	 * [説明]
	 * @return
	 * @throws OrangeException
	 */
	public int update(ItemCategoryVO ItemCategory) throws OrangeException{
		return 0;

	}

	/**
	 * [機能]
	 * [説明]
	 * @return
	 * @throws OrangeException
	 */
	public int delete(ItemCategoryVO ItemCategory) throws OrangeException{
		return 0;

	}

	/**
	 * [機能]
	 * [説明]
	 * @return
	 * @throws OrangeException
	 */
	public int insert(ItemCategoryVO ItemCategory) throws OrangeException{
		return 0;

	}

	/**
	 * [機能]
	 * [説明]
	 * @return
	 * @throws OrangeException
	 */
	public int check(ItemCategoryVO ItemCategory) throws OrangeException{
		return 0;

	}
}
