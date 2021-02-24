package exhibitions;

import controller.dialogController.OkCancelOption;

import javax.swing.*;
import java.awt.*;

public class ExhibitionDialog extends JDialog {

    private final Exhibition exhibition;
    private JTextField nameField;
    private JTextField placeField;
    private JTextField cityField;
    private JTextField countryField;
    private JTextField yearField;
    private OkCancelOption okCancelOption = OkCancelOption.UNDECIDED;

    public ExhibitionDialog(Frame owner, Exhibition exhibition) {
        super(owner);
        this.exhibition = exhibition;
        this.setLayout(new BorderLayout());
        this.add(initialiseInputPanel(), BorderLayout.CENTER);

        JPanel buttonPanel = initialiseButtonPanel();
        this.add(buttonPanel);

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
        inputPanel.setLayout(new GridLayout(5,2));
        JLabel nameLabel = new JLabel("Titel : ");
        JLabel placeLabel = new JLabel("Ausstellungsort : ");
        JLabel cityLabel = new JLabel("Stadt : ");
        JLabel countryLabel = new JLabel("Land :");
        JLabel yearLabel = new JLabel("Jahr : ");

        nameField = new JTextField(exhibition.getName());
        placeField = new JTextField(exhibition.getPlace());
        cityField = new JTextField(exhibition.getCity());
        countryField = new JTextField(exhibition.getCountry());
        yearField = new JTextField(exhibition.getYear());


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

    public void setExhibitionToTextFields(){
        exhibition.setName(nameField.getText());
        exhibition.setPlace(placeField.getText());
        exhibition.setCity(cityField.getText());
        exhibition.setCountry(countryField.getText());
        exhibition.setYear(Integer.parseInt(yearField.getText()));
    }
}
