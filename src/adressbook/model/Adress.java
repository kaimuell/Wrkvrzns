package adressbook.model;

/**
 * Klasse zum Speichern von Adressinformationen
 */
public class Adress {
    private String street;
    private String houseNo;
    private String city;
    private String postal;
    private String country;

    public Adress(String street, String houseNo, String city, String postal, String country){
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
}
