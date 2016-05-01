package models;


public class School {

    private int id;
    private String name;
    private String country;
    private String city;
    private String address;

    public School(int id, String name, String country, String city, String address) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }
}
