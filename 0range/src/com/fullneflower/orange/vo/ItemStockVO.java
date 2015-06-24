package com.fullneflower.orange.vo;

/**
 * [説明]商品在庫のプロパティを扱うクラス
 * @author Kawamori
 *
 */
public class ItemStockVO {
	/** 商品番号 */
	private String itemNo;
	/** 在庫数 */
	private int itemStock;

	/**
	 * プロパティのアクセッサー
	 */
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo2) {
		this.itemNo = itemNo2;
	}
	public int getItemStock() {
		return itemStock;
	}
	public void setItemStock(int itemStock) {
		this.itemStock = itemStock;
	}
}
