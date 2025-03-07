package com.xadmin.foodmanagement.bean;

public class Food 
{
	 
			private int id;
			private String name;
			private String price;
			
			public Food(String name, String price, String image, String rating) {
				super();
				this.name = name;
				this.price = price;
				this.image = image;
				this.rating = rating;
			}
			public Food(int id, String name, String price, String image, String rating) {
				super();
				this.id = id;
				this.name = name;
				this.price = price;
				this.image = image;
				this.rating = rating;
			}
			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getPrice() {
				return price;
			}
			public void setPrice(String price) {
				this.price = price;
			}
			public String getImage() {
				return image;
			}
			public void setImage(String image) {
				this.image = image;
			}
			public String getRating() {
				return rating;
			}
			public void setRating(String rating) {
				this.rating = rating;
			}
			private String image;
			private String rating;
			
		}
