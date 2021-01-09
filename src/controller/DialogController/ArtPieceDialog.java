package controller.DialogController;

import adressbook.model.PersonEntry;
import controller.Controller;
import controller.PictureController;
import gui.ArtworkTypeChoice;
import model.elements.ArtPieceEntry;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Eingabe Dialog für die Informationen eines {@link ArtPieceEntry}
 */

class ArtPieceDialog extends JDialog {

    private Controller controller;
    private JLabel buyerLabel;
    private JTextField priceField;
    private JTextField yearField;
    private JTextField lengthField;
    private JTextField depthField;
    private JTextField widthField;
    private JTextField heightField;
    private JTextField techniqueField;
    private JTextField nameField;
    private JTextField pictureField;
    private ArtPieceEntry artPiece;
    private boolean isApproved;
    private JPanel mainPanel;
    private JLabel errorInfoLabel;
    private ArtworkTypeChoice typeChoice;
    private JLabel imagePreviewIcon;


    public ArtPieceDialog(ArtPieceEntry artPiece, Controller controller) {
        this.artPiece = artPiece;
        this.controller = controller;
        this.isApproved = false;

        drawPanels();

        pack();
        setVisible(true);
    }

    public ArtPieceDialog(Controller controller) {

        this.isApproved = false;
        this.artPiece = ArtPieceEntry.createEmptyArtPieceEntry();
        this.controller = controller;

        drawPanels();

        pack();
        setVisible(true);
    }



    private void drawPanels() {

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(750,300));

        initTypeChoice();

        JPanel centralPanel =  new JPanel(new BorderLayout());

        JPanel pictureSelectionPanel = new JPanel(new GridLayout(1,4));
        addPictureSelectionTo(pictureSelectionPanel);
        centralPanel.add(pictureSelectionPanel, BorderLayout.NORTH);

        JPanel entryPanel = new JPanel(new GridLayout(5,4));

        initTextFields(entryPanel);
        initBuyerSelection(entryPanel);

        centralPanel.add(entryPanel, BorderLayout.CENTER);
        mainPanel.add(centralPanel);

        JPanel errorAndButtonPanel = new JPanel(new BorderLayout());
        errorAndButtonPanel.add(initErrorLabel(), BorderLayout.NORTH);
        errorAndButtonPanel.add(initOKButton(), BorderLayout.SOUTH);

        mainPanel.add(errorAndButtonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
    }

    private void initTypeChoice() {
        JPanel choicePanel =  new JPanel();
        typeChoice = new ArtworkTypeChoice();
        typeChoice.select(artPiece.getType().toString());
        choicePanel.add(typeChoice);
        mainPanel.add(choicePanel, BorderLayout.NORTH);
    }

    private JPanel initErrorLabel() {
        JPanel errorInfoPanel = new JPanel();
        errorInfoLabel = new JLabel();
        errorInfoPanel.add(errorInfoLabel);
        return errorInfoPanel;
    }

    private JPanel initOKButton() {
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            try {
                setArtPieceInfoToTextFields();
                isApproved = true;
            }catch (NumberFormatException error){
                errorInfoLabel.setText("Eingabe in einem Textfeld, dass nur Zahlen annimmt nicht gültig.");
                repaint();
            }
        });
        buttonPanel.add(okButton);
        return buttonPanel;
    }

    private void initBuyerSelection(JPanel entryPanel) {

        JLabel isSoldLabel = new JLabel("Verkauft an:");

        buyerLabel = new JLabel(
                createShortDescriptionOfPerson(
                    controller.getPersonWithIDFromAddressBook(artPiece.getBuyerID())));

        JButton selectBuyerButton = new JButton("Käufer hinzufügen");
        selectBuyerButton.addActionListener(e -> setBuyerToSelectionFromDialog());

        JButton deleteBuyerButton = new JButton("Käufer löschen");
        deleteBuyerButton.addActionListener(e -> deleteBuyer());

        entryPanel.add(isSoldLabel);
        entryPanel.add(buyerLabel);
        entryPanel.add(selectBuyerButton);
        entryPanel.add(deleteBuyerButton);
    }

    private void setBuyerToSelectionFromDialog() {
        PersonEntry buyer = selectPersonFromAddressbook();
        if (buyer != null) {
            artPiece.setSold(true);
            artPiece.setBuyerID(buyer.getId());
            buyerLabel.setText(
                    createShortDescriptionOfPerson(
                            controller.getPersonWithIDFromAddressBook(artPiece.getBuyerID())));
            mainPanel.repaint();
        }
    }

    private void deleteBuyer() {
        int returnval = JOptionPane.showConfirmDialog(null, "Wirklich löschen?");
        if (returnval == JOptionPane.YES_OPTION){
            artPiece.setSold(false);
            artPiece.setBuyerID(-1);
        }
    }

    private PersonEntry selectPersonFromAddressbook() {
        DialogController dialogController = new DialogController(controller);
        return dialogController.selectPersonFromAddressBookDialog();
    }

    private void initTextFields(JPanel entryPanel) {
        addNameTextFieldTo(entryPanel);
        addTechniqueTextFieldTo(entryPanel);
        addHeightTextFieldTo(entryPanel);
        addWithTextFieldTo(entryPanel);
        addDepthTextFieldTo(entryPanel);
        addLengthTextFieldTo(entryPanel);
        addYearTextFieldTo(entryPanel);
        addPriceTextFieldTo(entryPanel);
    }

    private void addNameTextFieldTo(JPanel entryPanel) {
        JLabel nameLabel = new JLabel("Titel");
        nameField = new JTextField(artPiece.getName(), 15);
        entryPanel.add(nameLabel);
        entryPanel.add(nameField);
    }

    private void addTechniqueTextFieldTo(JPanel entryPanel) {
        JLabel techniqueLabel = new JLabel("Technik");
        techniqueField = new JTextField(artPiece.getTechnique(),25);
        entryPanel.add(techniqueLabel);
        entryPanel.add(techniqueField);
    }

    private void addHeightTextFieldTo(JPanel entryPanel) {
        JLabel heightLabel = new JLabel("Höhe (in cm)");
        heightField = new JTextField(((Integer)artPiece.getHeight()).toString(), 6);
        entryPanel.add(heightLabel);
        entryPanel.add(heightField);
    }

    private void addWithTextFieldTo(JPanel entryPanel) {
        JLabel widthLabel = new JLabel("Breite (in cm)");
        widthField = new JTextField(((Integer) artPiece.getWidth()).toString(), 6);
        entryPanel.add(widthLabel);
        entryPanel.add(widthField);
    }

    private void addDepthTextFieldTo(JPanel entryPanel) {
        JLabel depthLabel = new JLabel("Tiefe (in cm)");
        depthField = new JTextField(((Integer) artPiece.getDepth()).toString(), 6);
        entryPanel.add(depthLabel);
        entryPanel.add(depthField);
    }

    private void addLengthTextFieldTo(JPanel entryPanel) {
        JLabel lengthLabel = new JLabel("Länge (in min)");
        lengthField = new JTextField(((Integer) artPiece.getLength()).toString(), 6);
        entryPanel.add(lengthLabel);
        entryPanel.add(lengthField);
    }

    private void addYearTextFieldTo(JPanel entryPanel) {
        JLabel yearLabel = new JLabel("Entstehungsjahr");
        yearField = new JTextField(((Integer) artPiece.getYear()).toString(), 6);
        entryPanel.add(yearLabel);
        entryPanel.add(yearField);
    }

    private void addPriceTextFieldTo(JPanel entryPanel) {
        JLabel priceLabel = new JLabel("Preis (€)");
        priceField = new JTextField(((Integer) artPiece.getPrice()).toString(), 9);
        entryPanel.add(priceLabel);
        entryPanel.add(priceField);
    }

    private void addPictureSelectionTo(JPanel panel) {
        JPanel picturePanel = new JPanel(new BorderLayout());

        JPanel previewPanel =  new JPanel();
        JPanel inputPanel = new JPanel(new GridLayout(1,3));

        imagePreviewIcon = new JLabel();
        imagePreviewIcon.setIcon(
                artPiece.getBitmap() == null ? (Icon) PictureController.defaultEmptyImage() : (Icon) artPiece.getBitmap());
        JLabel pictureLabel = new JLabel ("Adresse der Abbildung");
        pictureField = new JTextField(artPiece.getPicturePath());
        JButton loadPictureButton = new JButton("Bild laden");
        loadPictureButton.addActionListener(e -> selectPictureFromDialog());

        previewPanel.add(imagePreviewIcon);
        inputPanel.add(pictureLabel);
        inputPanel.add(pictureField);
        inputPanel.add(loadPictureButton);
        picturePanel.add(previewPanel, BorderLayout.WEST);
        picturePanel.add(inputPanel, BorderLayout.EAST);
        panel.add(picturePanel);
    }

    private void selectPictureFromDialog() {
        OpenSingleJPEGDialog openDialog = new OpenSingleJPEGDialog();
        int returnVal = openDialog.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            try {
                setTextAndPictureFieldToSelectionOf(openDialog);
                repaint();
            } catch (IOException ex) {
                errorInfoLabel.setText("Bild konnte nicht geladen werden");
                repaint();
            }
        }
    }

    private void setTextAndPictureFieldToSelectionOf(OpenSingleJPEGDialog openDialog) throws IOException {
        File pictureFile = openDialog.getSelectedFile();
        pictureField.setText(pictureFile.getPath());
        Image image = PictureController.loadImage(pictureFile.getPath());
        Image bitmap = PictureController.createBitmap(image);
        imagePreviewIcon.setIcon(new ImageIcon(bitmap));
        errorInfoLabel.setText("");
    }

    private void setArtPieceInfoToTextFields() throws NumberFormatException{
        artPiece.setType(typeChoice.getSelectedArtworkType());
        artPiece.setPicturePath(pictureField.getText());
        artPiece.setName(nameField.getText());
        artPiece.setTechnique(techniqueField.getText());
        artPiece.setHeight(ParseIntegerFromTextField(heightField));
        artPiece.setWidth(ParseIntegerFromTextField(widthField));
        artPiece.setDepth(ParseIntegerFromTextField(depthField));
        artPiece.setLength(ParseIntegerFromTextField(lengthField));
        artPiece.setYear(ParseIntegerFromTextField(yearField));
        artPiece.setPrice(ParseIntegerFromTextField(priceField));
    }

    private String createShortDescriptionOfPerson(PersonEntry person){
        if (person != null) {
            return (person.getFirstName() + " " + person.getFamilyName() + ", " + person.geteMail() + ",  " + person.getTel());
        }else {
            return "";
        }
    }

    private int ParseIntegerFromTextField(JTextField textField) throws NumberFormatException{
        return Integer.parseInt(textField.getText());
    }

    /**
     * Gibt zurück ob der Ok Button betätigt wurde
     * @return wurde ok gedrückt?
     */
    public boolean isApproved() {
        return isApproved;
    }

    public ArtPieceEntry getArtPieceInfo() {
        return artPiece;
    }

}