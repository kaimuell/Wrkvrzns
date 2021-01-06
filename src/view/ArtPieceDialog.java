package view;

import gui.OpenSingleJPEGDialog;
import model.ArtPieceEntry;
import model.Model;
import model.elements.ArtworkType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class ArtPieceDialog extends JDialog {
    private Model model;
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

    public ArtPieceDialog(ArtPieceEntry artPiece, Model model) {
        this.artPiece = artPiece;
        this.model = model;
        this.isApproved = false;

        drawPanels();
    }

    public ArtPieceDialog(Model model) {

        this.isApproved = false;
        this.artPiece = ArtPieceEntry.createEmptyArtpieceEntry();
        this.model = model;

        drawPanels();

    }

    private void drawPanels() {

        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(400,400));

        initTypeChoice();

        JPanel entryPanel = new JPanel(new GridLayout(6,4));

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


        JLabel isSoldLabel = new JLabel("Verkauft an:");
        buyerLabel = new JLabel(model.getPersonWithIDFromAdressBook(artPiece.getBuyerID()));
        JButton selectBuyerButton = new JButton("Käufer hinzufügen");
        selectBuyerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Dialog erzeugen in der das Adressbuch aufgerufen wird und eine Person ausgewählt werden kann.

                int id = 0; //id zurückgeben

                artPiece.setSold(true);
                artPiece.setBuyerID(id);
                buyerLabel.setText(model.getPersonWithIDFromAdressBook(artPiece.getBuyerID()));
            }
        });

        entryPanel.add(isSoldLabel);
        entryPanel.add(buyerLabel);
        entryPanel.add(selectBuyerButton);

        mainPanel.add(entryPanel);

        this.add(mainPanel);
    }

    private void leaveEmptySpace(JPanel panel) {
        panel.add(new JLabel());
    }

    private void initTypeChoice() {
        JPanel choicePanel =  new JPanel();
        Choice typeChoice = new Choice();
        typeChoice.add(ArtworkType.PAINTING.toString());
        typeChoice.add(ArtworkType.GRAFIK.toString());
        typeChoice.add(ArtworkType.SCULPTURE.toString());
        typeChoice.add(ArtworkType.INSTALLATION.toString());
        typeChoice.add(ArtworkType.VIDEO.toString());
        typeChoice.select(artPiece.getType().toString());
        choicePanel.add(typeChoice);
        mainPanel.add(choicePanel);
    }

    public boolean isApproved() {
        return isApproved;
    }

    public ArtPieceEntry getArtPieceInfo() {
        if (artPiece == null){
            //TODO erschaffe neues Artpiece aus Information
        }
        //TODO Informationen aus Feldern auslesen
        return artPiece;
    }
}