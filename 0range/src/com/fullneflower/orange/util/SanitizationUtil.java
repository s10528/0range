package com.fullneflower.orange.util;

/**
 * サニタイジングクラス
 * <>'"&の無害化
 * @author Kawamori
 *
 */
public class SanitizationUtil {
	/**
	 * [説明]文字列の置換を行う
	 *
	 * @param input 処理の対象の文字列
	 * @param pattern 置換前の文字列
	 * @param replacement 置換後の文字列
	 * @return 置換処理後の文字列
	 */
	private static String SubStitute(String input, String pattern, String replacement) {
		// 置換対象文字列が存在する場所を取得
		int index = input.indexOf(pattern);
		// 置換対象文字列が存在しなければ終了
		if(index == -1) {
			return input;
		}
		// 処理を行うための StringBuffer
		StringBuffer buffer = new StringBuffer();
		buffer.append(input.substring(0, index) + replacement);
		if(index + pattern.length() < input.length()) {
			// 残りの文字列を再帰的に置換
			String rest = input.substring(index + pattern.length(), input.length());
			buffer.append(SubStitute(rest, pattern, replacement));
		}
		return buffer.toString();
	}

	/**
	 * [説明]特殊文字の置換を行う。入力チェック後の->変換用
	 * & < > " ' に対応
	 * @param input String 置換を行う文字列
	 * @return input
	 */
	public static String HTMLEscape(String input) {
		input = SubStitute(input, "&",  "&amp;");
		input = SubStitute(input, "<",  "&lt;");
		input = SubStitute(input, ">",  "&gt;");
		input = SubStitute(input, "\"", "&quot;");
		input = SubStitute(input, "\'", "&#39;");

		return input;
	}
}
