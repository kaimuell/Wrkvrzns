package adressbook.model;

import java.io.Serializable;

/**
 * Klasse zum Speichern von Adressinformationen
 */
public class Address implements Serializable {
    private final String street;
    private final String houseNo;
    private final String city;
    private final String postal;
    private final String country;

    public Address(String street, String houseNo, String city, String postal, String country){
        this.street = street;
        this.houseNo = houseNo;
        this.city = city;
        this.postal = postal;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public String getCity() {
        return city;
    }

    public String getPostal() {
        return postal;
    }

    public String getCountry() {
        return country;
    }

    public static Address emptyAddress(){
        return new Address("","","","","");
    }
}
