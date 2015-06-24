package com.fullneflower.orange.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fullneflower.orange.servlet.OrangeException;
import com.fullneflower.orange.vo.ItemStockVO;

/**
 * [説明]DBの商品在庫を呼び出すクラス
 * @author Kawamori,Nakajima
 */
public class ItemStockDAO {

	/**
	 * [説明]コネクション
	 * @author Kawamori
	 */
	private Connection connection;
	/**
	 * [説明]コンストラクタ
	 * @author Kawamori
	 */
	public ItemStockDAO(Connection connection){
		this.connection = connection;
	}

	/**
	 * [説明]DBの商品在庫テーブルから商品番号と商品在庫を検索するメソッド
	 *       検索条件は商品番号のみ
	 * @return itemstockVO
	 * @throws OrangeException
	 * @author Nakajima
	 */
	public ItemStockVO search(ItemStockVO itemstockVO) throws OrangeException{
		// ステートメントの定義
		PreparedStatement preparedStatement = null;
		try {
			// SQLの定義
			String sql = "SELECT item_no, item_stock from item_stock WHERE item_no=? ";
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(sql);
			// SQLバインド変数への値設定
			preparedStatement.setInt(1, Integer.valueOf(itemstockVO.getItemNo()));
			// SQLの実行
			ResultSet result = preparedStatement.executeQuery();

			while(result.next()){
				String itemNo = String.valueOf(result.getInt("item_no"));
				int itemStock =result.getInt("item_stock");
				itemstockVO.setItemNo(itemNo);
				itemstockVO.setItemStock(itemStock);
			}
			return itemstockVO;
		} catch (SQLException e) {
			throw new OrangeException("ItemStockテーブルのSELECTに失敗しました", e);
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
	public int allSearch(ItemStockVO itemStock) throws OrangeException{
		return 0;

	}

	/**
	 * [機能]
	 * [説明]
	 * @return
	 * @throws OrangeException
	 */
	public int update(ItemStockVO itemStock) throws OrangeException{
		return 0;

	}

	/**
	 * [機能]
	 * [説明]
	 * @return
	 * @throws OrangeException
	 */
	public int delete(ItemStockVO itemStock) throws OrangeException{
		return 0;

	}

	/**
	 * [説明]DBの商品在庫テーブルに商品番号を登録するメソッド
	 * @return result
	 * @throws OrangeException
	 * @author Kawamori
	 */
	public int insert(ItemStockVO itemStockVO) throws OrangeException{
		// ステートメントの定義
		PreparedStatement preparedStatement = null;
		try{
			// SQLの定義
			String sql = "INSERT INTO item_stock (item_no) VALUES (?)";
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(sql);
			// SQLバインド変数への値設定
			preparedStatement.setInt(1, Integer.parseInt(itemStockVO.getItemNo()));
			// SQLの実行
			int result = preparedStatement.executeUpdate();
			System.out.println("登録結果:" + result);
			return result;
		}catch(SQLException e) {
			throw new OrangeException("在庫テーブルのINSERTに失敗しました", e);
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
	 * [機能]
	 * [説明]
	 * @return
	 * @throws OrangeException
	 */
	public int check(ItemStockVO itemStock) throws OrangeException{
		return 0;

	}
}