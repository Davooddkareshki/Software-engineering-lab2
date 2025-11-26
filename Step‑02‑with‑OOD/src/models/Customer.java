package models;

public class Customer {
    private String name;
    private String email;
    private String city;
    private String mobile;
    public Customer(String name, String email, String mobile, String city){
        this.name = name; this.email = email; this.city = city;this.mobile=mobile;
    }

    public String getName(){return this.name;}
    public String getEmail(){return this.email;}
    public String getMobile(){return this.mobile;}
    public String getCity(){return this.city;}
}