package com.fullneflower.orange.vo;

/**
 * [説明]商品テーブルのプロパティを扱うクラス
 * @author Kawamori
 *
 */
public class ItemVO {

	/** 商品番号 */
	private String itemNo;
	/** 商品名 */
	private String itemName;
	/** 商品画像URL */
	private String itemUrl;
	/** 単価 */
	private String unitPrice;
	/** 寸法 */
	private String size;
	/** 商品種別コード */
	private String itemAssortmentCode;
	/** 商品カテゴリコード */
	private String itemCategoryCode;
	/** 論理削除フラグ */
	private String itemDF;
	/** 商品カテゴリ名 */
	private String itemCategoryName;
	/** 商品種別名 */
	private String itemAssortmentName;

	/**
	 * [説明]プロパティのアクセッサー
	 */
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemUrl() {
		return itemUrl;
	}
	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getItemAssortmentCode() {
		return itemAssortmentCode;
	}
	public void setItemAssortmentCode(String itemAssortmentCode) {
		this.itemAssortmentCode = itemAssortmentCode;
	}
	public String getItemCategoryCode() {
		return itemCategoryCode;
	}
	public void setItemCategoryCode(String itemCategryCode) {
		this.itemCategoryCode = itemCategryCode;
	}
	public String getItemDF() {
		return itemDF;
	}
	public void setItemDF(String itemDF) {
		this.itemDF = itemDF;
	}
	public String getItemCategoryName() {
		return itemCategoryName;
	}
	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}
	public String getItemAssortmentName() {
		return itemAssortmentName;
	}
	public void setItemAssortmentName(String itemAssortmentName) {
		this.itemAssortmentName = itemAssortmentName;
	}
}
