package com.fullneflower.orange.servlet;

/**
 * [説明]独自例外クラス
 * @author Kawamori
 */
public class OrangeException extends Exception{
		/**
		 * [説明]独自例外メソッド
		 */
		public OrangeException(){
			super();
		}
		/**
		 * [説明]独自例外メソッド
		 * @param messeage
		 * @param e
		 */
		public OrangeException(String messeage, Throwable e){
			super (messeage , e);
		}
		/**
		 * [説明]独自例外メソッド
		 * @param message
		 */
		public OrangeException(String message){
			super(message);
		}
		/**
		 * [説明]独自例外メソッド
		 * @param e
		 */
		public OrangeException(Throwable e){
			super(e);
		}
}
