package exhibitions;

public class Exhibition {
    private int id;
    private String name;
    private String place;
    private String city;
    private String country;
    private int year;

    public Exhibition(int id, String name, String place, String city, String country, int year) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.city = city;
        this.country = country;
        this.year = year;
    }

    public Exhibition() {
        this.id = -1;
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
