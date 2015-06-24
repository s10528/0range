package com.fullneflower.orange.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fullneflower.orange.servlet.OrangeException;
import com.fullneflower.orange.vo.CustomerVO;

/**
 * [説明]顧客テーブルの各種の更新を行うクラス
 * @author Wada
 *
 */

public class CustomerDAO {

	/**コネクション*/
	private Connection connection;

	/**
	 * [説明]コンストラクタ
	 * @author Wada
	  */

	public CustomerDAO(Connection connection){
		this.connection = connection;
	}

	/**
	 * [説明]顧客の追加を行うメソッド
	 * @param CustomerVO customerVO
	 * @return result
	 * @throws OrangeException
	 * @author Wada
	 */
	public int insert(CustomerVO customerVO) throws OrangeException{
		// ステートメントの定義
		PreparedStatement preparedStatement = null;
		try{
			// SQLの定義
			String sql =
					"INSERT INTO CUSTOMER (CUSTOMER_ID, COMPANY_NAME, ADDRESS, TELNO, DEPT, CUSTOMER_NAME, CUSTOMER_KANA, EMAIL, PASSWORD) " +
							"VALUES (to_char(NEXTVAL('SEQ_CUSTOMER_ID'),'00000'),?,?,?,?,?,?,?,?)";
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(sql);
			// SQLバインド変数への値設定
			//preparedStatement.setString(1, customerVO.getCustomerID());
			preparedStatement.setString(1, customerVO.getCompanyName());
			preparedStatement.setString(2, customerVO.getAddress());
			preparedStatement.setString(3, customerVO.getTelno());
			preparedStatement.setString(4, customerVO.getDept());
			preparedStatement.setString(5, customerVO.getCustomerName());
			preparedStatement.setString(6, customerVO.getCustomerKana());
			preparedStatement.setString(7, customerVO.getEmail());
			preparedStatement.setString(8, customerVO.getPassword());
			// SQLの実行
			int result = preparedStatement.executeUpdate();
			return result;
		}catch(SQLException e) {
			throw new OrangeException("顧客テーブルのINSERTに失敗しました", e);
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
	 * [説明]顧客登録したIDを取得
	 * @return customerID String
	 * @throws OrangeException
	 * @author Kawamori
	 */
	public String IDSearch() throws OrangeException{
		PreparedStatement preparedStatement = null;
		try {
			// SQLの定義
			String sql = "SELECT MAX(CUSTOMER_ID) AS MAXID  FROM CUSTOMER ";
			preparedStatement = connection.prepareStatement(sql);
			// SQLの実行
			ResultSet result = preparedStatement.executeQuery();
			String  customerID = "";
			while(result.next()){
				customerID= result.getString("MAXID");
			}
			return customerID;
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
	 * [機能]ログインチェック
	 * [説明]DBと照合しログインチェックを行う
	 * @return result
	 * @throws OrangeException
	 * @author Nakajima
	 */
	public int customerCheck(CustomerVO customer) throws OrangeException{
		// ステートメントの定義
		PreparedStatement preparedStatement = null;
		try {
			// SQLの定義
			String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=?";
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(sql);
			//SQLバインド変数への値設定
			preparedStatement.setString(1, customer.getCustomerID());
			// SQLの実行
			ResultSet resultSet = preparedStatement.executeQuery();
			// ID一致したデータのパスワードを詰める変数を宣言
			String password="";
			while(resultSet.next()){
				password = resultSet.getString("PASSWORD");
			}
			// 返り値を詰める変数の宣言(初期値０)
			int result = 0;
			// パスワードの照合
			if (password.equals(customer.getPassword())) {
				result = 1;
			}
			return result;

		} catch (SQLException e) {
			throw new OrangeException("一致するIDまたはパスワードが見つかりませんでした。", e);
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
