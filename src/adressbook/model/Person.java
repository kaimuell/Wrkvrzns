package adressbook.model;

public class Person {
    private String familyName;
    private String firstName;
    private String eMail;
    private String tel;
    private Adress adress;

    public Person(String familyName, String firstName, String eMail, String tel, Adress adress) {
        this.familyName = familyName;
        this.firstName = firstName;
        this.eMail = eMail;
        this.tel = tel;
        this.adress = adress;
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

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
