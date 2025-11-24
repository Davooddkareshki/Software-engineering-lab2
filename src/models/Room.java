package models;

public class Room {
    private String number;
    private String type; // "standard" or "luxury"
    private double price;

    public Room(String number, String type, double price){
        this.number = number;
        this.type = type;
        this.price = price;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void discountPrice(){
        this.price = price * 0.9;
    }
}
