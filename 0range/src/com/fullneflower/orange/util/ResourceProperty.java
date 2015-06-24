package com.fullneflower.orange.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.fullneflower.orange.servlet.OrangeException;

/**
 * [説明]プロパティファイルから値を取得するクラス
 * @author Kawamori
 *
 */
public class ResourceProperty {
	/**
	 * [説明]Message.propertiesからメッセージを取得するメソッド
	 * @param msg
	 * @return strCode
	 * @throws OrangeException
	 * @author Kawamori
	 */
	public static String getMessage(String msg) throws OrangeException {
		try{
			ResourceBundle rBundle = ResourceBundle.getBundle("Message");
			String strCode = rBundle.getString(msg);
			return strCode;
		} catch(MissingResourceException e) {
			throw new OrangeException(e);
		}
	}
	/**
	 * [説明]Url.propertiesからメッセージを取得するメソッド
	 * @param url
	 * @return strCode
	 * @throws OrangeException
	 * @author Kawamori
	 */
	public static String getUrl(String url) throws OrangeException {
		try{
			ResourceBundle rBundle = ResourceBundle.getBundle("Url");
			String strCode = rBundle.getString(url);
			return strCode;
		} catch(MissingResourceException e) {
			throw new OrangeException(e);
		}
	}
	/**
	 * [説明]Jdbc.propertiesからメッセージを取得するメソッド
	 * @param jdbc
	 * @return strCode
	 * @throws OrangeException
	 * @author Kawamori
	 */
	public static String getJdbc(String jdbc) throws OrangeException {
		try{
			ResourceBundle rBundle = ResourceBundle.getBundle("Jdbc");
			String strCode = rBundle.getString(jdbc);
			return strCode;
		} catch(MissingResourceException e) {
			throw new OrangeException(e);
		}
	}
}
