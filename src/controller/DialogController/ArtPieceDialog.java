package controller.DialogController;

import adressbook.model.PersonEntry;
import controller.Controller;
import controller.PictureController;
import gui.ArtworkTypeChoice;
import model.ArtPieceEntry;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


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
    }

    public ArtPieceDialog(Controller controller) {

        this.isApproved = false;
        this.artPiece = ArtPieceEntry.createEmptyArtpieceEntry();
        this.controller = controller;

        drawPanels();
    }



    private void drawPanels() {

        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(400,400));

        initTypeChoice();

        JPanel entryPanel = new JPanel(new GridLayout(6,4));

        initPictureSelection(entryPanel);
        initTextFields(entryPanel);
        initBuyerSelection(entryPanel);

        mainPanel.add(entryPanel);

        initErrorLabel();
        initOKButton();

        this.add(mainPanel);
    }

    private void initErrorLabel() {
        errorInfoLabel = new JLabel();
        mainPanel.add(errorInfoLabel);
    }

    private void initOKButton() {
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            try {
                setArtPieceInfoToTextFields();
                isApproved = true;
            }catch (NumberFormatException error){
                errorInfoLabel.setText("Eingabe in einem Textfeld, dass nur Zahlen annimmt nicht gültig.");
            }
        });
    }

    private void initBuyerSelection(JPanel entryPanel) {
        JLabel isSoldLabel = new JLabel("Verkauft an:");
        buyerLabel = new JLabel(
                createShortdescriptionOfPerson(
                    controller.getPersonWithIDFromAdressBook(artPiece.getBuyerID())));
        JButton selectBuyerButton = new JButton("Käufer hinzufügen");
        selectBuyerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PersonEntry buyer = selectPersonFromAddressbook();
                artPiece.setSold(true);
                artPiece.setBuyerID(buyer.getId());
                buyerLabel.setText(
                        createShortdescriptionOfPerson(
                            controller.getPersonWithIDFromAdressBook(artPiece.getBuyerID())));
                mainPanel.repaint();
            }
        });

        JButton deleteBuyerButton = new JButton("Käufer löschen");
        deleteBuyerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnval = JOptionPane.showConfirmDialog(null, "Wirklich löschen?");
                if (returnval == JOptionPane.YES_OPTION){
                    artPiece.setSold(false);
                    artPiece.setBuyerID(-1);
                }
            }
        });

        entryPanel.add(isSoldLabel);
        entryPanel.add(buyerLabel);
        entryPanel.add(selectBuyerButton);
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

    private void initPictureSelection(JPanel entryPanel) {
        imagePreviewIcon.setIcon(
                artPiece.getBitmap() == null ? (Icon) PictureController.defaultEmptyImage() : (Icon) artPiece.getBitmap());
        JLabel pictureLabel = new JLabel ("Adresse der Abbildung");
        pictureField = new JTextField(artPiece.getPicturePath());
        JButton loadPictureButton = new JButton("Bild laden");
        loadPictureButton.addActionListener(e -> {
            File pictureFile = null;
            OpenSingleJPEGDialog openDialog = new OpenSingleJPEGDialog();
            int returnVal = openDialog.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                try {
                    pictureField.setText(pictureFile.getPath());
                    Image image = PictureController.loadImage(pictureFile.getPath());
                    Image bitmap = PictureController.createBitmap(image);
                    imagePreviewIcon.setIcon((Icon) bitmap);
                    errorInfoLabel.setText("");
                    repaint();
                } catch (IOException ex) {
                    errorInfoLabel.setText("Bild konnte nicht geladen werden");
                    repaint();
                }
            }
        });

        entryPanel.add(imagePreviewIcon);
        entryPanel.add(pictureLabel);
        entryPanel.add(pictureField);
        entryPanel.add(loadPictureButton);
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

    }

    private String createShortdescriptionOfPerson(PersonEntry person){
        return (person.getFirstName() + " " + person.getFamilyName() + ", " + person.geteMail() + ",  " + person.getTel());
    }

    private int ParseIntegerFromTextField(JTextField textField) throws NumberFormatException{

        int info = Integer.parseInt(textField.getText());

        return info;
    }


    private void initTypeChoice() {
        JPanel choicePanel =  new JPanel();
        typeChoice = new ArtworkTypeChoice();
        typeChoice.select(artPiece.getType().toString());
        choicePanel.add(typeChoice);
        mainPanel.add(choicePanel);
    }

    public boolean isApproved() {
        return isApproved;
    }

    public ArtPieceEntry getArtPieceInfo() {
        return artPiece;
    }

}