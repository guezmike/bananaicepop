package com.zipper.zipcloset;

import java.util.Arrays;
import java.util.List;

import com.kinvey.persistence.mapping.MappedEntity;
import com.kinvey.persistence.mapping.MappedField;

public class clothingEntity implements MappedEntity {

	private String id;
	private String brand;
	private String imageUrl;
	private String purchaseUrl;
	private String price;
	private String favorite;
	
	public clothingEntity(){}
	
	public clothingEntity(String id) {
		this.id = id;
	}
	
	public clothingEntity(String id, 
			String brand, 
			String imageUrl,
			String purchaseUrl,
			String price,
			String favorite){
		this.id = id;
		this.brand= brand;
		this.imageUrl = imageUrl;
		this.purchaseUrl= purchaseUrl;
		this.price = price;
		this.favorite = favorite;
	}

	@Override
	public List<MappedField> getMapping() {
		return Arrays.asList(new MappedField[] { 
				new MappedField("id", "_id"), 
				new MappedField("brand", "brand"),
				new MappedField("imageUrl", "imageUrl"),
				new MappedField("purchaseUrl", "purchaseUrl"),
				new MappedField("favorite", "favorite"),
				new MappedField("price", "price")});
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String url) {
		this.imageUrl = url;
	}
	public void setPurchaseUrl(String purchase) {
		this.purchaseUrl = purchase;
	}
	public String getPurchaseUrl() {
		return purchaseUrl;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPrice() {
		return price;
	}
	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}
	public String getFavorite() {
		return favorite;
	}
}
