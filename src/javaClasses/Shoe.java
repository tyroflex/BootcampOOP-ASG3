package javaClasses;

public class Shoe {

    private final String shoeName;
    private final String category;
    private final String releaseDate;
    private final String productCode;
    private final int price;

    public Shoe(String shoeName, String category, String releaseDate, String productCode, int price) {
        this.shoeName = shoeName;
        this.category = category;
        this.releaseDate = releaseDate;
        this.productCode = productCode;
        this.price = price;
    }

    public String getShoeName() {
        return this.shoeName;
    }

    public String getCategory() {
        return this.category;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public int getPrice() {
        return this.price;
    }
}
