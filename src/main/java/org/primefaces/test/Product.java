package org.primefaces.test;

import java.io.Serializable;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int id;
    private String uniqueId;
    private String name;
    private String desc;
    private String image;
    private int price;
    private String category;
    private int leftInStock;
    private InventoryStatus inventoryStatus;
    private int rating;

    public Product(int id, String uniqueId, String name, String desc, String image, int price, String category, int leftInStock, InventoryStatus inventoryStatus, int rating) 
    {
        this.id = id;
        this.uniqueId = uniqueId;
        this.name = name;
        this.desc = desc;
        this.image = image;
        this.price = price;
        this.category = category;
        this.leftInStock = leftInStock;
        this.inventoryStatus = inventoryStatus;
        this.rating = rating;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getLeftInStock() {
		return leftInStock;
	}

	public void setLeftInStock(int leftInStock) {
		this.leftInStock = leftInStock;
	}

	public InventoryStatus getInventoryStatus() {
		return inventoryStatus;
	}

	public void setInventoryStatus(InventoryStatus inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

    
}
