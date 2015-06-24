package com.fullneflower.orange.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fullneflower.orange.servlet.OrangeException;
import com.fullneflower.orange.util.ResourceProperty;


/**
 * [説 明] データベースの接続と切断を行うドライバークラス。
 * @author Kawamori
 */
public class ConnectionManager {
	/** コネクション */
	private Connection connection;

	/** 接続ドライバー名 */
	private String DRIVER_NAME;

	/** 接続URL */
	private String URL;

	/** 接続ユーザ */
	private String USER;

	/** 接続パスワード */
	private String PASSWORD;

	/**
	 * [説明]コンストラクタ
	 * @throws OrangeException
	 */
	public ConnectionManager() throws OrangeException{
		DRIVER_NAME = ResourceProperty.getJdbc("DRIVER_NAME");
		URL			= ResourceProperty.getJdbc("URL");
		USER		= ResourceProperty.getJdbc("USER");
		PASSWORD	= ResourceProperty.getJdbc("PASSWORD");
	}

	/**
	 * [説 明] コネクションを取得し、返却するメソッド。
	 *  ※例外取得時にはRuntimeExceptionにラップし上位に送出する。
	 * @return connection
	 * @throws OrangeException
	 */
	public Connection getConnection() throws OrangeException {
		try {
			Class.forName(DRIVER_NAME);
			System.out.println("ドライバーのロードに成功しました");
		} catch (ClassNotFoundException e) {
			throw new OrangeException("ドライバーのロードに失敗しました", e);
		}
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				//コミットモードの設定
				connection.setAutoCommit(false);
				System.out.println("データベースの接続に成功しました");
			} catch (SQLException e) {
				throw new OrangeException("データベースの接続に失敗しました", e);
			}
		}
		return connection;
	}

	/**
	 * [説 明] コネクションを切断するメソッド。<br>
	 *         ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * @throws OrangeException
	 */
	public void closeConnection() throws OrangeException {
		try {
			if (connection != null) {
				connection.close();
				System.out.println("データベースの切断に成功しました");
			}
		} catch (SQLException e) {
			throw new OrangeException("データベースの切断に失敗しました", e);
		}
	}

	/**
	 * [説 明] トランザクションをコミットするメソッド。<br>
	 *         ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * @throws OrangeException
	 */
	public void commit() throws OrangeException {
		try {
			if (connection != null) {
				connection.commit();
				System.out.println("トランザクションのコミットに成功しました");
			}
		} catch (SQLException e) {
			throw new OrangeException("トランザクションのコミットに失敗しました", e);
		}
	}

	/**
	 * [説 明] トランザクションをロールバックするメソッド。<br>
	 *         ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * @throws OrangeException
	 */
	public void rollback() throws OrangeException {
		try {
			if (connection != null) {
				connection.rollback();
				System.out.println("トランザクションのロールバックに成功しました");
			}
		} catch (SQLException e) {
			throw new OrangeException("トランザクションのロールバックに失敗しました", e);
		}
	}
}
