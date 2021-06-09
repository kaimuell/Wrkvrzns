package exhibitions.entities;

import exhibitions.ExhibitionType;

/**
 * Klasse zum speichern von informationen von Ausstellungen
 */

public class Exhibition {
    private int id;
    private ExhibitionType type;
    private String with;
    private String name;
    private String place;
    private String city;
    private String country;
    private int year;

    public Exhibition(int id, ExhibitionType type, String with, String name, String place, String city, String country, int year) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.with = with;
        this.place = place;
        this.city = city;
        this.country = country;
        this.year = year;
    }

    public String getWith() {
        return with;
    }

    public void setWith(String with) {
        this.with = with;
    }

    public Exhibition() {
        this.id = -1;
        this.type = ExhibitionType.SOLO;
        this.name ="";
        this.place="";
        this.city="";
        this.country="";
        this.year = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ExhibitionType getType() {
        return type;
    }

    public void setType(ExhibitionType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
