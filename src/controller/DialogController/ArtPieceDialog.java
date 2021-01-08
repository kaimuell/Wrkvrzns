package controller.DialogController;

import adressbook.model.PersonEntry;
import controller.Controller;
import gui.ArtworkTypeChoice;
import model.ArtPieceEntry;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


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
        JLabel nameLabel = new JLabel("Titel");
        nameField = new JTextField(artPiece.getName(), 15);
        entryPanel.add(nameLabel);
        entryPanel.add(nameField);

        JLabel techniqueLabel = new JLabel("Technik");
        techniqueField = new JTextField(artPiece.getTechnique(),25);
        entryPanel.add(techniqueLabel);
        entryPanel.add(techniqueField);

        JLabel heightLabel = new JLabel("Höhe (in cm)");
        heightField = new JTextField(((Integer)artPiece.getHeight()).toString(), 6);
        entryPanel.add(heightLabel);
        entryPanel.add(heightField);

        JLabel widthLabel = new JLabel("Breite (in cm)");
        widthField = new JTextField(((Integer) artPiece.getWidth()).toString(), 6);
        entryPanel.add(widthLabel);
        entryPanel.add(widthField);

        JLabel depthLabel = new JLabel("Tiefe (in cm)");
        depthField = new JTextField(((Integer) artPiece.getDepth()).toString(), 6);
        entryPanel.add(depthLabel);
        entryPanel.add(depthField);

        JLabel lengthLabel = new JLabel("Länge (in min)");
        lengthField = new JTextField(((Integer) artPiece.getLength()).toString(), 6);
        entryPanel.add(lengthLabel);
        entryPanel.add(lengthField);

        JLabel yearLabel = new JLabel("Entstehungsjahr");
        yearField = new JTextField(((Integer) artPiece.getYear()).toString(), 6);
        entryPanel.add(yearLabel);
        entryPanel.add(yearField);

        JLabel priceLabel = new JLabel("Preis (€)");
        priceField = new JTextField(((Integer) artPiece.getPrice()).toString(), 9);
        entryPanel.add(priceLabel);
        entryPanel.add(priceField);
    }

    private void initPictureSelection(JPanel entryPanel) {
        JLabel pictureLabel = new JLabel ("Adresse der Abbildung");
        pictureField = new JTextField(artPiece.getPicturePath());
        JButton loadPictureButton = new JButton("Bild laden");
        loadPictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File f = null;
                OpenSingleJPEGDialog openDialog = new OpenSingleJPEGDialog();
                int returnVal = openDialog.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    pictureField.setText(f.getPath());
                    //TODO Vorschau Bild erstellen?
                }
            }
        });
        entryPanel.add(pictureLabel);
        entryPanel.add(pictureField);
        entryPanel.add(loadPictureButton);
        leaveEmptySpace(entryPanel);
    }

    private void setArtPieceInfoToTextFields() throws NumberFormatException{
        artPiece.setType(typeChoice.getSelectedArtworkType());
        //TODO wie Bilder setzen ?????????????
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

    private void leaveEmptySpace(JPanel panel) {
        panel.add(new JLabel());
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