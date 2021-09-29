package exhibitions;

import controller.dialogFactory.OkCancelOption;
import exhibitions.entities.Exhibition;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog zum Eingeben und Bearbeiten von Ausstellungen
 */

class ExhibitionDialog extends JDialog {

    private final Exhibition exhibition;
    private JTextField nameField;
    private JTextField placeField;
    private JTextField cityField;
    private JTextField countryField;
    private JTextField yearField;
    public OkCancelOption okCancelOption = OkCancelOption.UNDECIDED;
    private ExhibitionTypeChoice exhibitionTypeChoice;
    private JTextField withField;
    private JLabel errorLabel;

    ExhibitionDialog(Frame owner, Exhibition exhibition, boolean isEditable) {
        super(owner);
        this.exhibition = exhibition;
        this.setLayout(new BorderLayout());

        errorLabel = new JLabel("");
        this.add(errorLabel, BorderLayout.NORTH);

        this.add(initialiseInputPanel(), BorderLayout.CENTER);

        JPanel buttonPanel = initialiseButtonPanel();
        this.add(buttonPanel,BorderLayout.SOUTH);
        if (!isEditable){
            deactivateTextFields();
        }
        this.pack();
    }

    private JPanel initialiseButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(action -> {
            setExhibitionToTextFields();
            this.okCancelOption = OkCancelOption.OK;
        });
        JButton cancelButton = new JButton("ABBRECHEN");
        cancelButton.addActionListener(action -> {
            this.okCancelOption = OkCancelOption.CANCEL;
        });
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        return buttonPanel;
    }

    private JPanel initialiseInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7,2));

        JLabel typeLabel = new JLabel("Art der Ausstellung : ");
        JLabel withLabel = new JLabel("Zusammen mit :");
        JLabel nameLabel = new JLabel("Titel : ");
        JLabel placeLabel = new JLabel("Ausstellungsort : ");
        JLabel cityLabel = new JLabel("Stadt : ");
        JLabel countryLabel = new JLabel("Land :");
        JLabel yearLabel = new JLabel("Jahr : ");

        exhibitionTypeChoice = new ExhibitionTypeChoice(exhibition.getType());
        withField = new JTextField(exhibition.getWith(), 30);
        nameField = new JTextField(exhibition.getName());
        placeField = new JTextField(exhibition.getPlace());
        cityField = new JTextField(exhibition.getCity());
        countryField = new JTextField(exhibition.getCountry());
        yearField = new JTextField(String.valueOf(exhibition.getYear()));

        inputPanel.add(typeLabel);
        inputPanel.add(exhibitionTypeChoice);
        inputPanel.add(withLabel);
        inputPanel.add(withField);

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(placeLabel);
        inputPanel.add(placeField);
        inputPanel.add(cityLabel);
        inputPanel.add(cityField);
        inputPanel.add(countryLabel);
        inputPanel.add(countryField);
        inputPanel.add(yearLabel);
        inputPanel.add(yearField);

        return inputPanel;
    }

    /**
     * Setzt die Informationen einer Ausstellung auf die der Textfelder
     */
    public void setExhibitionToTextFields(){
        exhibition.setType(exhibitionTypeChoice.getSelectedExhibitionType());
        exhibition.setWith(withField.getText());
        exhibition.setName(nameField.getText());
        exhibition.setPlace(placeField.getText());
        exhibition.setCity(cityField.getText());
        exhibition.setCountry(countryField.getText());
        try {
            exhibition.setYear(Integer.parseInt(yearField.getText()));
            errorLabel.setText("");
        } catch (NumberFormatException e){
            errorLabel.setText("Ungültige Jahreseingabe");
        }
    }


    private void deactivateTextFields() {
        exhibitionTypeChoice.setEnabled(false);
        withField.setEditable(false);
        nameField.setEditable(false);
        placeField.setEditable(false);
        cityField.setEditable(false);
        countryField.setEditable(false);
        yearField.setEditable(false);
    }
}
