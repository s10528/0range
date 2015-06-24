package com.fullneflower.orange.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * [機能]サービス用抽象クラス
 * [説明]サービスクラスはこのクラスを継承する
 * @author Kawamori
 *
 */
public interface IFBean {
	 String service(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
