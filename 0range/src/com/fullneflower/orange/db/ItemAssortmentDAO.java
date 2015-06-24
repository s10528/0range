package com.fullneflower.orange.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fullneflower.orange.servlet.OrangeException;
import com.fullneflower.orange.vo.ItemAssortmentVO;

/**
 * [説明]種別情報の取得を行う
 * @author Tanaka,Nakajima,Kawamori,Wada
 *
 */
public class ItemAssortmentDAO {
	/** コネクション */
	private Connection connection;
	/**
	 * [説明]コンストラクタ
	 * @author Kawamori
	 */
	public ItemAssortmentDAO(Connection connection){
		this.connection = connection;
	}

	/**
	 * [説明]種別名の取得を行うメソッド
	 * @param itemAssortment 種別名を検索する種別コード
	 * @return itemAssortment 種別名
	 * @throws OrangeException
	 * @author Tanaka
	 */
	public String search(String itemAssortment) throws OrangeException{
		// ステートメントの定義
		PreparedStatement preparedStatement = null;
		try {
			// SQLの定義
			String sql = "SELECT item_assortment_name FROM item_assortment WHERE item_assortment_code=?";
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(sql);
			// SQLバインド変数への値設定
			preparedStatement.setString(1, itemAssortment);
			// SQLの実行
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				String itemAssortmentName = result.getString("item_assortment_name");
				itemAssortment = itemAssortmentName;
			}
			return itemAssortment;
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
	 * [説明]種別コード・種別名を取得するメソッド
	 * @return assortList 種別情報
	 * @throws OrangeException
	 * @author Wada
	 */
	public ArrayList<ItemAssortmentVO> allSearch() throws OrangeException{
		PreparedStatement preparedStatement = null;
		try {
			// SQLの定義
			String sql = "SELECT ITEM_ASSORTMENT_CODE,ITEM_ASSORTMENT_NAME FROM ITEM_ASSORTMENT";
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(sql);
			// SQLの実行
			ResultSet result = preparedStatement.executeQuery();
			ArrayList<ItemAssortmentVO> assortList = new ArrayList<ItemAssortmentVO>();
			while(result.next()){
				String itemAssortmentCode =result.getString("item_assortment_code");
				String itemAssortmentName=result.getString("item_assortment_name");

				ItemAssortmentVO itemAssortVO = new ItemAssortmentVO();
				itemAssortVO.setItemAssortmentCode(itemAssortmentCode);
				itemAssortVO.setItemAssortmentName(itemAssortmentName);
				assortList.add(itemAssortVO);
			}
			return assortList;
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
	 * [説明]
	 * @return
	 * @throws OrangeException
	 */
	public int update(ItemAssortmentVO ItemAssortment) throws OrangeException{
		return 0;

	}

	/**
	 * [説明]
	 * @return
	 * @throws OrangeException
	 */
	public int delete(ItemAssortmentVO ItemAssortment) throws OrangeException{
		return 0;

	}

	/**
	 * [説明]
	 * @return
	 * @throws OrangeException
	 */
	public int insert(ItemAssortmentVO ItemAssortment) throws OrangeException{
		return 0;
	}

	/**
	 * [説明]
	 * @return
	 * @throws OrangeException
	 */
	public int check(ItemAssortmentVO ItemAssortment) throws OrangeException{
		return 0;
	}
}
