package adressbook.view;

import adressbook.model.Adress;
import adressbook.model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonDialog extends JDialog{
    private JTextField nameField;
    private JTextField familyNameField;
    private JTextField telField;
    private  JTextField emailField;
    private  JTextField streetField;
    private JTextField houseNrField;
    private JTextField cityField;
    private JTextField zipField;
    private JTextField countryField;
    private Person person;
    private boolean isApproved;



    PersonDialog(JFrame f, Person person){
        super(f);
        this.setPreferredSize(new Dimension(550,300));
        this.person = person;
        this.isApproved = false;
        JPanel infoPanel = new JPanel();
        infoPanel.add(initialisePersonInfoPanel(), BorderLayout.CENTER);
        infoPanel.add(initialiseAdressInfoPanel(), BorderLayout.CENTER);
        infoPanel.add(initialiseOKButton(), BorderLayout.SOUTH);
        infoPanel.revalidate();
        this.add(infoPanel);

        this.setVisible(true);
        this.pack();
    }

    private JButton initialiseOKButton() {
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            person.setFirstName(nameField.getText());
            person.setFamilyName(familyNameField.getText());
            person.setTel(telField.getText());
            person.seteMail(emailField.getText());
            person.setAdress(new Adress(streetField.getText(), houseNrField.getText(), cityField.getText(), zipField.getText(), countryField.getText()));
            isApproved = true;
        });
        return okButton;
    }

    private JPanel initialisePersonInfoPanel(){
        JPanel personInfoPanel = new JPanel(new GridLayout(4,2));
        initNameField(personInfoPanel);
        initFamilyNameField(personInfoPanel);
        initTelField(personInfoPanel);
        initEMailField(personInfoPanel);
        personInfoPanel.revalidate();
        return personInfoPanel;
    }

    private void initEMailField(JPanel personInfoPanel) {
        JLabel emailLabel = new JLabel("E-Mail ");
        emailField = new JTextField(person.geteMail(),15);
        personInfoPanel.add(emailLabel);
        personInfoPanel.add(emailField);
    }

    private void initTelField(JPanel personInfoPanel) {
        JLabel telLabel = new JLabel("Telefon ");
        telField = new JTextField(person.getTel(), 15);
        personInfoPanel.add(telLabel);
        personInfoPanel.add(telField);
    }

    private void initFamilyNameField(JPanel personInfoPanel) {
        JLabel familyNameLabel = new JLabel("Nachname ");
        familyNameField = new JTextField (person.getFamilyName(),15);
        personInfoPanel.add(familyNameLabel);
        personInfoPanel.add (familyNameField);
    }

    private void initNameField(JPanel personInfoPanel) {
        JLabel nameLabel = new JLabel("Name ");
        nameField = new JTextField (person.getFirstName(),15);
        personInfoPanel.add(nameLabel);
        personInfoPanel.add (nameField);
    }



    private JPanel initialiseAdressInfoPanel() {
        JPanel adressPanel = new JPanel(new GridLayout(4,2));
        InitStreetAndZIPField(adressPanel);
        initZIPField(adressPanel);
        initCityField(adressPanel);
        initCountryField(adressPanel);
        adressPanel.revalidate();
        return adressPanel;
    }

    private void InitStreetAndZIPField(JPanel adressPanel) {
        JLabel streetLabel = new JLabel ("Strasse und Hausnummer ");
        JPanel fields = new JPanel();
        streetField = new JTextField(person.getAdress().getStreet(), 15);
        houseNrField = new JTextField(person.getAdress().getHouseNo(), 3);
        adressPanel.add(streetLabel);
        fields.add(streetField);
        fields.add(houseNrField);
        adressPanel.add(fields);
    }

    private void initZIPField(JPanel cityAndCountryPanel) {
        JLabel zipLabel = new JLabel("PLZ");
        zipField = new JTextField(person.getAdress().getPostal(), 6);
        cityAndCountryPanel.add(zipLabel);
        cityAndCountryPanel.add(zipField);
    }

    private void initCityField(JPanel cityAndCountryPanel) {
        JLabel cityLabel = new JLabel("Stadt");
        cityField = new JTextField(person.getAdress().getCity());
        cityAndCountryPanel.add(cityLabel);
        cityAndCountryPanel.add(cityField);
    }

    private void initCountryField(JPanel cityAndCountryPanel) {
        JLabel coutryLabel = new JLabel("Land");
        countryField = new JTextField(person.getAdress().getCountry(), 10);
        cityAndCountryPanel.add(coutryLabel);
        cityAndCountryPanel.add(countryField);
    }


    public Person getPerson(){
        return this.person;
    }

    public boolean isApproved() {
        return isApproved;
    }
}
