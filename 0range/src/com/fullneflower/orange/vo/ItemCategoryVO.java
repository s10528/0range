package com.fullneflower.orange.vo;
/**
 * [説明]カテゴリーのプロパティを扱うクラス
 * @author Kawamori
 *
 */
public class ItemCategoryVO {
	/** 商品カテゴリコード */
	private String itemCategoryCode;
	/** 商品カテゴリ名 */
	private String itemCategoryName;
	/** 説明 */
	private String description;

	/**
	 * プロパティのアクセッサー
	 */
	public String getItemCategoryCode() {
		return itemCategoryCode;
	}
	public void setItemCategoryCode(String itemCategoryCode) {
		this.itemCategoryCode = itemCategoryCode;
	}
	public String getItemCategoryName() {
		return itemCategoryName;
	}
	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


}
