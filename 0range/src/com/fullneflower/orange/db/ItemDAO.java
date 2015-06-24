package com.fullneflower.orange.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fullneflower.orange.servlet.OrangeException;
import com.fullneflower.orange.vo.ItemVO;

/**
 * [説明]商品テーブルの各種の更新を行うクラス
 * @author Wada,Tanaka,Nakajima,Kawamori
 *
 */
public class ItemDAO {

	/**コネクション*/
	private Connection connection;

	/**
	 * [説明]コンストラクタ
	 * @author Kawamori
	 */
	public ItemDAO(Connection connection){
		this.connection = connection;
	}

	/**
	 * [説明]商品番号を使って、商品情報を1件だけ取得するメソッド
	 * @param ItemVO itemVO
	 * @return itemVO
	 * @throws OrangeException
	 * @author Wada
	 */
	public ItemVO search(ItemVO itemVO) throws OrangeException{
		PreparedStatement preparedStatement = null;
		try {
			// SQLの定義
			String sql = "SELECT ITEM.ITEM_NO,ITEM.ITEM_NAME,ITEM.ITEM_URL,ITEM.UNIT_PRICE,ITEM.SIZE,ITEM.ITEM_ASSORTMENT_CODE,ITEM.ITEM_CATEGORY_CODE,ITEM_ASSORTMENT.ITEM_ASSORTMENT_NAME,ITEM_CATEGORY.ITEM_CATEGORY_NAME FROM ITEM INNER JOIN ITEM_ASSORTMENT ON ITEM.ITEM_ASSORTMENT_CODE = ITEM_ASSORTMENT.ITEM_ASSORTMENT_CODE INNER JOIN ITEM_CATEGORY ON ITEM.ITEM_CATEGORY_CODE = ITEM_CATEGORY.ITEM_CATEGORY_CODE  WHERE ITEM_NO= ?";
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(sql);
			// SQLバインド変数への値設定
			preparedStatement.setInt(1,Integer.parseInt(itemVO.getItemNo()));
			// SQLの実行
			ResultSet result = preparedStatement.executeQuery();

				while(result.next()){
					int itemNo =result.getInt("item_no");
					String itemName=result.getString("item_name");
					String itemUrl=result.getString("item_url");
					int unitPrice = result.getInt("unit_price");
					String size = result.getString("size");
					String itemAssortmentCode=result.getString("item_assortment_code");
					String itemCategoryCode=result.getString("item_category_code");
					String itemAssortmentName = result.getString("item_assortment_name");
					String itemCategoryName = result.getString("item_category_name");
					itemVO = new ItemVO();
					itemVO.setItemNo(String.valueOf(itemNo));
					itemVO.setItemName(itemName);
					itemVO.setItemUrl(itemUrl);
					itemVO.setUnitPrice(String.valueOf(unitPrice));
					itemVO.setSize(size);
					itemVO.setItemAssortmentCode(itemAssortmentCode);
					itemVO.setItemCategoryCode(itemCategoryCode);
					itemVO.setItemAssortmentName(itemAssortmentName);
					itemVO.setItemCategoryName(itemCategoryName);
				}
			return itemVO;
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
	 * [説明]商品一覧表示画面(P031)の商品一覧を表示するためにDB上の商品情報をリスト化するメソッド
	 * @return ItemVOList
	 * @throws OrangeException
	 * @author Wada
	 */
	public ArrayList<ItemVO> allSearch() throws OrangeException{
		PreparedStatement preparedStatement = null;
		try {
			// SQLの定義
			String sql = "SELECT ITEM.ITEM_NO,ITEM.ITEM_NAME,ITEM.ITEM_URL,ITEM.UNIT_PRICE,ITEM.SIZE,ITEM.ITEM_ASSORTMENT_CODE,ITEM.ITEM_CATEGORY_CODE,ITEM_ASSORTMENT.ITEM_ASSORTMENT_NAME,ITEM_CATEGORY.ITEM_CATEGORY_NAME FROM ITEM INNER JOIN ITEM_ASSORTMENT ON ITEM.ITEM_ASSORTMENT_CODE = ITEM_ASSORTMENT.ITEM_ASSORTMENT_CODE INNER JOIN ITEM_CATEGORY ON ITEM.ITEM_CATEGORY_CODE = ITEM_CATEGORY.ITEM_CATEGORY_CODE WHERE ITEM_DF = '0' ORDER BY ITEM_NO ASC";
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(sql);
			// SQLの実行
			ResultSet result = preparedStatement.executeQuery();
			ArrayList<ItemVO> ItemVOList = new ArrayList<ItemVO>();
			while(result.next()){
				int itemNo =result.getInt("item_no");
				String itemName=result.getString("item_name");
				String itemUrl=result.getString("item_url");
				int unitPrice = result.getInt("unit_price");
				String size = result.getString("size");
				String itemAssortmentCode=result.getString("item_assortment_code");
				String itemCategoryCode=result.getString("item_category_code");
				String itemAssortmentName = result.getString("item_assortment_name");
				String itemCategoryName = result.getString("item_category_name");
				ItemVO itemvo = new ItemVO();
				itemvo.setItemNo(String.valueOf(itemNo));
				itemvo.setItemName(itemName);
				itemvo.setItemUrl(itemUrl);
				itemvo.setUnitPrice(String.valueOf(unitPrice));
				itemvo.setSize(size);
				itemvo.setItemAssortmentCode(itemAssortmentCode);
				itemvo.setItemCategoryCode(itemCategoryCode);
				itemvo.setItemAssortmentName(itemAssortmentName);
				itemvo.setItemCategoryName(itemCategoryName);
				ItemVOList.add(itemvo);
			}
			return ItemVOList;
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
	 * [説明]商品情報の更新を行うメソッド
	 * @param ItemVO item
	 * @return result
	 * @throws OrangeException
	 * @author Tanaka
	 */
	public int update(ItemVO item) throws OrangeException{
		// ステートメントの定義
		PreparedStatement preparedStatement = null;
		try {
			// SQLの定義
			String sql = "UPDATE ITEM SET item_name=?, item_url=?, unit_price=?, size=?, item_assortment_code=?, item_category_code=? WHERE item_no=?";
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(sql);
			// SQLバインド変数への値設定
			preparedStatement.setString(1, item.getItemName());
			preparedStatement.setString(2, item.getItemUrl());
			preparedStatement.setInt(3, Integer.parseInt(item.getUnitPrice()));
			preparedStatement.setString(4, item.getSize());
			preparedStatement.setString(5, item.getItemAssortmentCode());
			preparedStatement.setString(6, item.getItemCategoryCode());
			preparedStatement.setInt(7, Integer.parseInt(item.getItemNo()));
			// SQLの実行
			int result = preparedStatement.executeUpdate();
			return result;
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
	 * [説明]商品情報の削除を行うメソッド
	 * @param ItemVO item
	 * @return result
	 * @throws OrangeException
	 * @author Tanaka
	 */
	public int delete(ItemVO item) throws OrangeException{
		// ステートメントの定義
		PreparedStatement preparedStatement = null;
		try {
			// SQLの定義
			String sql = "UPDATE ITEM SET item_df=? WHERE item_no=?";
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(sql);
			// SQLバインド変数への値設定
			preparedStatement.setString(1, "1");
			preparedStatement.setInt(2, Integer.parseInt(item.getItemNo()));
			// SQLの実行
			int result = preparedStatement.executeUpdate();
			return result;
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
	 * [説明]商品の追加を行うメソッド
	 * @param ItemVO itemVO
	 * @return result
	 * @throws OrangeException
	 * @author Nakajima
	 */
	public int insert(ItemVO itemVO) throws OrangeException{
		// ステートメントの定義
		PreparedStatement preparedStatement = null;
		try{
			// SQLの定義
			String sql =
					"INSERT INTO item ( item_no, item_name, item_url, unit_price, size, item_assortment_code, item_category_code) " +
							"VALUES (?,?,?,?,?,?,?)";
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(sql);
			// SQLバインド変数への値設定
			preparedStatement.setInt(1, Integer.parseInt(itemVO.getItemNo()));
			preparedStatement.setString(2, itemVO.getItemName());
			preparedStatement.setString(3, itemVO.getItemUrl());
			preparedStatement.setInt(4, Integer.parseInt(itemVO.getUnitPrice()));
			preparedStatement.setString(5, itemVO.getSize());
			preparedStatement.setString(6, itemVO.getItemAssortmentCode());
			preparedStatement.setString(7, itemVO.getItemCategoryCode());
			// SQLの実行
			int result = preparedStatement.executeUpdate();
			return result;
		}catch(SQLException e) {
			throw new OrangeException("商品テーブルのINSERTに失敗しました", e);
		}finally{
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
	 * [説明]商品名の重複チェックを行うメソッド
	 * @param ItemVO itemVO
	 * @return ItemVO
	 * @throws OrangeException
	 * @author Kawamori
	 */
	public ItemVO nameCheck(ItemVO itemVO) throws OrangeException{
		PreparedStatement preparedStatement = null;
		try {
			// SQLの定義
			String sql = "SELECT ITEM_NO,ITEM_NAME,Item_df  FROM ITEM WHERE ITEM_NAME= ?";
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(sql);
			// SQLバインド変数への値設定
			preparedStatement.setString(1, itemVO.getItemName());
			// SQLの実行
			ResultSet result = preparedStatement.executeQuery();
			if (result == null) {
				return itemVO;
			}else{
				while(result.next()){
					itemVO.setItemNo(String.valueOf(result.getInt("item_no")));
					itemVO.setItemName(result.getString("item_name"));
					itemVO.setItemDF(result.getString("item_df"));
					}
			}
			return itemVO;
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
}
