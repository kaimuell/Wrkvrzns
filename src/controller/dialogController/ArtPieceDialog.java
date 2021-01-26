package controller.dialogController;

import adressbook.model.Person;
import adressbook.model.PersonEntry;
import controller.Controller;
import tools.PictureTools;
import gui.elements.ArtworkTypeChoice;
import model.elements.ArtPieceEntry;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static controller.dialogController.OkCancelOption.*;

/**
 * Eingabe Dialog für die Informationen eines {@link ArtPieceEntry}
 */

class ArtPieceDialog extends JDialog {

    private final Controller controller;
    private final ArtPieceEntry artPiece;

    private JTextField priceField;
    private JTextField yearField;
    private JTextField lengthField;
    private JTextField depthField;
    private JTextField widthField;
    private JTextField heightField;
    private JTextField techniqueField;
    private JTextField nameField;
    private JPanel mainPanel;
    private JLabel errorInfoLabel;
    private ArtworkTypeChoice typeChoice;
    private JLabel imagePreviewIcon;
    private JLabel buyerLabel;

    private Image imageForEntry;

    private OkCancelOption okCancelOption;
    private String picturePath;
    private JTextField editionField;


    ArtPieceDialog(ArtPieceEntry artPiece, Controller controller) {
        this.artPiece = artPiece;
        this.controller = controller;
        this.okCancelOption = UNDECIDED;

        drawPanels();

        pack();
        setVisible(true);
    }

    public ArtPieceDialog(Controller controller) {

        this.okCancelOption = UNDECIDED;
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

        JPanel entryPanel = new JPanel(new GridLayout(6,4));

        initTextFields(entryPanel);
        initBuyerSelection(entryPanel);

        centralPanel.add(entryPanel, BorderLayout.CENTER);
        mainPanel.add(centralPanel);

        JPanel errorAndButtonPanel = new JPanel(new BorderLayout());
        errorAndButtonPanel.add(initErrorLabel(), BorderLayout.NORTH);
        errorAndButtonPanel.add(initOkAndCancelButtons(), BorderLayout.SOUTH);

        mainPanel.add(errorAndButtonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
        revalidate();
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

    private JPanel initOkAndCancelButtons() {
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            try {
                setArtPieceInfoToTextFields();
                okCancelOption = OK;
            }catch (NumberFormatException error){
                errorInfoLabel.setText("Eingabe in einem Textfeld, dass nur Zahlen annimmt nicht gültig.");
                repaint();
            }
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener( e -> {
            this.okCancelOption = CANCEL;
        });

        buttonPanel.add(okButton);
        return buttonPanel;
    }

    private void initBuyerSelection(JPanel entryPanel) {

        JLabel isSoldLabel = new JLabel("Verkauft an:");

        buyerLabel = new JLabel(
                createShortDescriptionOfPerson(artPiece.getBuyer()));

        JButton selectBuyerButton = new JButton("Käufer hinzufügen");
        selectBuyerButton.addActionListener( action -> {
                    new Thread(() -> {
                        setBuyerToSelectionFromDialog();
                    }).start();
                }
        );

        JButton deleteBuyerButton = new JButton("Käufer löschen");
        deleteBuyerButton.addActionListener(e -> deleteBuyer());

        entryPanel.add(isSoldLabel);
        entryPanel.add(buyerLabel);
        entryPanel.add(selectBuyerButton);
        entryPanel.add(deleteBuyerButton);
    }

    private void setBuyerToSelectionFromDialog() {
        Person buyer = selectPersonFromAddressbook();
        if (buyer != null) {
            artPiece.setBuyer(buyer);
            buyerLabel.setText(
                    createShortDescriptionOfPerson(buyer));
            mainPanel.repaint();
        }
    }

    private void deleteBuyer() {
        if (artPiece.getBuyer() != null) {
            int returnval = JOptionPane.showConfirmDialog(null, "Wirklich löschen?",
                    "Bestätigen", JOptionPane.YES_NO_OPTION);
            if (returnval == JOptionPane.YES_OPTION) {
                artPiece.setBuyer(null);
            }
        }
    }

    private Person selectPersonFromAddressbook() {
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
        insertEmptyLabel(entryPanel);
        insertEmptyLabel(entryPanel);
        addEditionTextFieldTo(entryPanel);
    }

    private void insertEmptyLabel(JPanel entryPanel) {
        JLabel emptyLabel = new JLabel();
        entryPanel.add(emptyLabel);
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

    private void addEditionTextFieldTo(JPanel entryPanel) {
        JLabel editionLabel = new JLabel("Auflage");
        editionField =  new JTextField(((Integer)artPiece.getEdition()).toString(), 6);
        entryPanel.add(editionLabel);
        entryPanel.add(editionField);
    }

    private void addPictureSelectionTo(JPanel panel) {
        JPanel picturePanel = new JPanel(new BorderLayout());

        JPanel previewPanel =  new JPanel();
        JPanel inputPanel = new JPanel();
        imagePreviewIcon = new JLabel( );
        imagePreviewIcon.setSize(80,80);
        imagePreviewIcon.setIcon(new ImageIcon(artPiece.getBitmap() == null?
                PictureTools.defaultEmptyImage().getScaledInstance(80,80, Image.SCALE_FAST)
                : artPiece.getBitmap().getScaledInstance(80,80,Image.SCALE_SMOOTH)));
        JButton loadPictureButton = new JButton("Bild laden");
        loadPictureButton.addActionListener(e -> selectPictureFromDialog());
        previewPanel.add(imagePreviewIcon);
        inputPanel.add(loadPictureButton);
        picturePanel.add(previewPanel, BorderLayout.WEST);
        picturePanel.add(inputPanel, BorderLayout.EAST);
        panel.add(picturePanel);
    }

    private void selectPictureFromDialog() {
            //JFileChooser openDialog = new ChooseSingleJPEGDialog(FileSystemView.getFileSystemView().getHomeDirectory());
        JFileChooser openDialog = DialogController.createChooseSingleJPEGDialog();

        int returnVal = openDialog.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    this.picturePath = setPictureFieldToSelectionOf(openDialog);
                    repaint();
                } catch (IOException ex) {
                    errorInfoLabel.setText("Bild konnte nicht geladen werden");
                    repaint();
                }
            }
    }

    private String setPictureFieldToSelectionOf(JFileChooser openDialog) throws IOException {
        File pictureFile = openDialog.getSelectedFile();
        this.imageForEntry = PictureTools.loadImage(pictureFile.getPath());
        Image bitmap = PictureTools.createBitmap(imageForEntry, 80, 80);
        imagePreviewIcon.setIcon(new ImageIcon(bitmap));
        errorInfoLabel.setText("");
        return pictureFile.getPath();
    }

    private void setArtPieceInfoToTextFields() throws NumberFormatException{
        artPiece.setType(typeChoice.getSelectedArtworkType());
        artPiece.setName(nameField.getText());
        artPiece.setTechnique(techniqueField.getText());
        artPiece.setHeight(ParseIntegerFromTextField(heightField));
        artPiece.setWidth(ParseIntegerFromTextField(widthField));
        artPiece.setDepth(ParseIntegerFromTextField(depthField));
        artPiece.setLength(ParseIntegerFromTextField(lengthField));
        artPiece.setYear(ParseIntegerFromTextField(yearField));
        artPiece.setPrice(ParseIntegerFromTextField(priceField));
        artPiece.setEdition(ParseIntegerFromTextField(editionField));
    }

    private String createShortDescriptionOfPerson(Person person){
        if (person != null) {
            return (person.getFirstName() + " " + person.getFamilyName() + ", " + person.geteMail() + ",  " + person.getTel());
        }else {
            return "nicht verkauft";
        }
    }

    private int ParseIntegerFromTextField(JTextField textField) throws NumberFormatException{
        return Integer.parseInt(textField.getText());
    }

    /**
     * Gibt zurück ob Eingabe bestätigt wurde, oder abgebrochen
     * @return wurde ok oder cancel gedrückt?
     */
    OkCancelOption okCancelOption() {
        return okCancelOption;
    }


    ArtPieceEntry getEntryInfo() {
        return artPiece;
    }

    Image getUpdatedImage() {
        return imageForEntry;
    }
}