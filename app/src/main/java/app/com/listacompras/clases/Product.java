package app.com.listacompras.clases;

/**
 * Created by daniel on 25/10/2016.
 */

public class Product {
    private String name , description;
    private float price, rating;
    private int stock;
    private  int thumbnail;
    public Product() {
    }

    public Product(String name, String description, float price, int stock, int thumbnail, float rating) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.thumbnail = thumbnail;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public float getRating() {return rating;}

    public void setRating(float rating) { this.rating = rating; }
}
