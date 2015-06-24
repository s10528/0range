package com.fullneflower.orange.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * [説明]入力チェック用共通クラス
 * @author Sanpei,Kawamori
 */
public class InputChecker {
	/**
	 * [説明]半角数字が入力されているかどうかのチェックを行う。
	 * @param param String 判定を行う文字列
	 * @return boolean 判定
	 * @author Sanpei
	 */
	public static boolean isNum(String param) {
		Pattern pattern = Pattern.compile("^[0-9]+$");
		Matcher matcher = pattern.matcher(param);
		if(matcher.matches()){
			return true;
		}
		return false;
	}

	/**
	 * [説明]メールアドレスのチェックを行う。
	 * メールアドレスのフォーマットに合っていればtrue,それ以外はfalse
	 * @param param String 判定を行う文字列
	 * @return boolean 判定
	 * @author Kawamori
	 */
	public static boolean isMail(String param){
		String chkStr = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$";
		Pattern pattern = Pattern.compile(chkStr);
		Matcher matcher = pattern.matcher(param);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * [説明]半角英数のみのチェック
	 * 半角英数のみだとtrue,それ以外はfalse
	 * @param param String 判定する文字列
	 * @return boolean 判定
	 * @author Kawamori
	 */
	public static boolean isAlphaNumeric(String param) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
		Matcher matcher = pattern.matcher(param);
		if(matcher.matches()){
			return true;
		}
		return false;
	}

	/**
	 * [説明]ひらがなのみのチェック
	 * ひらがなのみだとtrue,それ以外はfalse
	 * @param param String 判定する文字列
	 * @return boolean 判定
	 * @author Kawamori
	 */
	public static boolean isHiragana(String param) {
		Pattern pattern = Pattern.compile("^[ぁ-ゞ]+$");
		Matcher matcher = pattern.matcher(param);
		if(matcher.matches()){
			return true;
		}
		return false;
	}

	/**
	 * [説明]空文字のチェック
	 * 空文字以外だとtrue,空文字はfalse
	 * @param param String 判定する文字列
	 * @return boolean 判定
	 * @author Kawamori
	 */
	public static boolean isEmpty(String param) {
		Pattern pattern = Pattern.compile("");
		Matcher matcher = pattern.matcher(param);
		if(matcher.matches()){
			return true;
		}
		return false;
	}

	/**
	 * [説明]担当者IDの桁数チェック
	 * 桁数が4の場合true,それ以外はfalse
	 * @param param String 判定する文字列
	 * @return boolean 判定
	 * @author Kawamori
	 */
	public static boolean idStaffLength(String param) {
		int idLength = param.length() ;
		if(idLength == 4){
			return true;
		}
		return false;
	}

	/**
	 * [説明]担当者IDの1000以上チェック
	 * 1000以上の場合true,それ以外はfalse
	 * 入力値が半角数字であることを確認した後に用いる
	 * @param param String 判定する文字列
	 * @return boolean 判定
	 * @author Sanpei
	 */
	public static boolean idUnderThousand(String param) {
		int empId = Integer.parseInt(param);
		if(empId >= 1000){
			return true;
		}
		return false;
	}

	/**
	 * [説明]商品番号の桁数チェック
	 * 桁数が4桁以下の場合true,それ以外はfalse
	 * @param param String 判定する文字列
	 * @return boolean 判定
	 * @author Sanpei
	 */
	public static boolean itemIdLength(String param) {
		int idLength = param.length() ;
		System.out.println("入力桁数：" + idLength);
		if(idLength <= 4){
			return true;
		}
		return false;
	}

	/**
	 * [説明]商品名の桁数チェック
	 * 桁数が20桁以下の場合true,それ以外はfalse
	 * @param param String 判定する文字列
	 * @return boolean 判定
	 * @author Sanpei
	 */
	public static boolean itemNameLength(String param) {
		int nameLength = param.length() ;
		System.out.println("入力桁数：" + nameLength);
		if(nameLength <= 20){
			return true;
		}
		return false;
	}

	/**
	 * [説明]商品画像urlの桁数チェック
	 * 桁数が50桁以下の場合true,それ以外はfalse
	 * @param param String 判定する文字列
	 * @return boolean 判定
	 * @author Sanpei
	 */
	public static boolean itemUrlLength(String param) {
		int urlLength = param.length() ;
		System.out.println("入力桁数：" + urlLength);
		if(urlLength <= 50){
			return true;
		}
		return false;
	}

	/**
	 * [説明]単価の桁数チェック
	 * 桁数が5桁以下の場合true,それ以外はfalse
	 * @param param String 判定する文字列
	 * @return boolean 判定
	 * @author Sanpei
	 */
	public static boolean unitPriceLength(String param) {
		int unitPriceLength = param.length() ;
		System.out.println("入力桁数：" + unitPriceLength);
		if(unitPriceLength <= 5){
			return true;
		}
		return false;
	}


	/**
	 * [説明]寸法のチェックを行う。
	 * 寸法のフォーマットに合っていればtrue,それ以外はfalse
	 * @param param String 判定する文字列
	 * @return boolean 判定
	 * @author Sanpei
	 */
	public static boolean isSize(String param){
		String chkStr = "^\\d{1,3}x\\d{1,3}x\\d{1,3}$";
		Pattern pattern = Pattern.compile(chkStr);
		Matcher matcher = pattern.matcher(param);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * [説明]半角英数記号のみのチェック
	 * 半角英数記号のみだとtrue,それ以外はfalse
	 * @param param String
	 * @return boolean 判定
	 * @author Sanpei
	 */
	public static boolean isAlphaNumericSymbol(String param) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9 @!#$%&'_`/.=~-]*$");
		Matcher matcher = pattern.matcher(param);
		if(matcher.matches()){
			return true;
		}
		return false;
	}

	/**
	 * [説明]住所の桁数チェック
	 * 桁数が100桁以下の場合true,それ以外はfalse
	 * @param param String 判定する文字列
	 * @return boolean 判定
	 * @author Sanpei
	 */
	public static boolean adressLength(String param) {
		int addressLength = param.length() ;
		System.out.println("入力桁数：" + addressLength);
		if(addressLength <= 100){
			return true;
		}
		return false;
	}

	/**
	 * [説明]電話番号の桁数チェック
	 * 桁数が14桁以下の場合true,それ以外はfalse
	 * @param param String 判定する文字列
	 * @return boolean 判定
	 * @author Sanpei
	 */
	public static boolean telnoLength(String param) {
		int telnoLength = param.length() ;
		System.out.println("入力桁数：" + telnoLength);
		if(telnoLength <= 14){
			return true;
		}
		return false;
	}

	/**
	 * [説明]顧客担当者名（かな）の桁数チェック
	 * 桁数が40桁以下の場合true,それ以外はfalse
	 * @param param String 判定する文字列
	 * @return boolean 判定
	 * @author Sanpei
	 */
	public static boolean custNameKanaLength(String param) {
		int kanaLength = param.length() ;
		System.out.println("入力桁数：" + kanaLength);
		if(kanaLength <= 40){
			return true;
		}
		return false;
	}

	/**
	 * [説明]顧客担当者メールアドレスの桁数チェック
	 * 桁数が100桁以下の場合true,それ以外はfalse
	 * @param param String 判定する文字列
	 * @return boolean 判定
	 * @author Sanpei
	 */
	public static boolean custMailLength(String param) {
		int mailLength = param.length() ;
		System.out.println("入力桁数：" + mailLength);
		if(mailLength <= 100){
			return true;
		}
		return false;
	}

	/**
	 * [説明]パスワードの桁数チェック
	 * 桁数が8桁以下の場合true,それ以外はfalse
	 * @param param String 判定する文字列
	 * @return boolean 判定
	 * @author Sanpei
	 */
	public static boolean passwordLength(String param) {
		int passwordLength = param.length() ;
		System.out.println("入力桁数：" + passwordLength);
		if(passwordLength <= 8){
			return true;
		}
		return false;
	}

	/**
	 * [説明]電話番号のチェックを行う。
	 * 電話番号のフォーマットに合っていればtrue,それ以外はfalse
	 * @param param String 判定する文字列
	 * @return boolean 判定
	 * @author Sanpei
	 */
	public static boolean isTelno(String param){
		String chkStr = "^\\d{1,4}-\\d{1,4}-\\d{1,4}$";
		Pattern pattern = Pattern.compile(chkStr);
		Matcher matcher = pattern.matcher(param);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * [説明]顧客IDの桁数チェック
	 * 桁数が6の場合true,それ以外はfalse
	 * @param param String 判定する文字列
	 * @return boolean 判定
	 * @author Sanpei
	 */
	public static boolean idCustomerLength(String param) {
		int idLength = param.length() ;
		if(idLength <= 6){
			return true;
		}
		return false;
	}


}