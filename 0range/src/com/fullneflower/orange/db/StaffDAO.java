package com.fullneflower.orange.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fullneflower.orange.servlet.OrangeException;
import com.fullneflower.orange.vo.StaffVO;


/**
 * [説明]DBと接続し照合を行うクラス
 * @author Kawamori Wada tanaka Sanpei Nakajima
 *
 */
public class StaffDAO {
	/**
	 * [説明]コンストラクタ
	 * @author Kawamori
	 */
	private Connection connection;
	public StaffDAO(Connection connection){
		this.connection = connection;
	}

	/**
	 * [機能]ログインチェック
	 * [説明]DBと照合しログインチェックを行う
	 * @return result
	 * @throws OrangeException
	 */
	public int staffCheck(StaffVO staff) throws OrangeException{
		// ステートメントの定義
		PreparedStatement preparedStatement = null;
		try {
			// SQLの定義
			String sql = "SELECT * FROM STAFF WHERE STAFF_ID=?";
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(sql);
			//SQLバインド変数への値設定
			preparedStatement.setString(1, staff.getStaffId());
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
			if (password.equals(staff.getPassword())) {
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