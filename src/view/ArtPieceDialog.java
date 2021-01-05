package view;

import gui.OpenSingleJPEGDialog;
import model.ArtPieceEntry;
import model.elements.ArtworkType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class ArtPieceDialog extends JDialog {
    private JTextField pictureField;
    ArtPieceEntry artPiece;
    boolean isApproved;
    JPanel mainPanel;

    public ArtPieceDialog(ArtPieceEntry artPiece) {
        this.artPiece = artPiece;
        this.isApproved = false;
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
        JTextField nameField = new JTextField(artPiece.getName(), 15);
        entryPanel.add(nameLabel);
        entryPanel.add(nameField);





        //TODO weiterschreiben

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

    public ArtPieceDialog() {
        this.artPiece = null;
        this.isApproved = false;
        this.setPreferredSize(new Dimension(400,400));

        //TODO Felder darstellen
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