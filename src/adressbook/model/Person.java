package adressbook.model;

import java.io.Serializable;

/**
 * Klasse mit den relevanten Daten einer Person
 */
public class Person implements Serializable {
    private String familyName;
    private String firstName;
    private String eMail;
    private String tel;
    private Address address;

    public Person(String familyName, String firstName, String eMail, String tel, Address address) {
        this.familyName = familyName;
        this.firstName = firstName;
        this.eMail = eMail;
        this.tel = tel;
        this.address = address;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Address getAdress() {
        return address;
    }

    public void setAdress(Address address) {
        this.address = address;
    }
}
