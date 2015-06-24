package com.fullneflower.orange.vo;

/**
 * [説明]種別テーブルのプロパティを扱うクラス
 * @author Kawamori
 *
 */
public class ItemAssortmentVO {
	/** 商品種別コード */
	private String itemAssortmentCode;
	/** 商品種別名 */
	private String itemAssortmentName;
	/** 説明 */
	private String description;
	/**
	 * プロパティのアクセッサー
	 */
	public String getItemAssortmentCode() {
		return itemAssortmentCode;
	}
	public void setItemAssortmentCode(String itemAssortmentCode) {
		this.itemAssortmentCode = itemAssortmentCode;
	}
	public String getItemAssortmentName() {
		return itemAssortmentName;
	}
	public void setItemAssortmentName(String itemAssortmentName) {
		this.itemAssortmentName = itemAssortmentName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
