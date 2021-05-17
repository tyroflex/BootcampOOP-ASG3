package javaClasses;

import java.util.Random;

public class Shoe {

    private final String shoeName;
    private final String category;
    private final String releaseDate;
    private final String productCode;
    private final int price;

    public Shoe(String shoeName, String category, String releaseDate, int price) {
        this.shoeName = shoeName;
        this.category = category;
        this.releaseDate = releaseDate;
        this.price = price;
        this.productCode = randomNumber();
    }

    public static String randomNumber() {
        Random randomizer = new Random();
        int code = randomizer.nextInt(1000);
        String stringCode = "SH";
        for ( int i = 0 ; i < 3-Math.ceil(Math.log10(code)) ; i++ ) {
            stringCode = stringCode.concat("0");
        }
        stringCode = stringCode.concat(toString(code));
        return stringCode;
    }

    public static String toString( int number ) {
        String result = "";
        int denominator = (int) Math.pow(10, (int) Math.floor(Math.log10(number)));
        while ( denominator > 0 ) {
            int digit = number/denominator;
            result = result + (char)(digit + '0');
            number %= denominator;
            denominator /= 10;
        }
        return result;
    }

    public void print(int roll) {
        System.out.printf("%d %s-%s\n", roll, this.shoeName, this.productCode);
        System.out.println("======================");
        System.out.printf("Category: %s\n", this.category);
        System.out.printf("Release Date: %s\n", this.releaseDate);
        System.out.printf("Price: %d\n\n", this.price);
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
