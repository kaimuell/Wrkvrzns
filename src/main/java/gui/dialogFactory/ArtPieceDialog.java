package gui.dialogFactory;

import adressbook.model.Person;
import controller.Controller;
import exhibitions.ExhibitionViewManager;
import exhibitions.entities.Exhibition;
import languagePack.LanguagePackContainer;
import model.ModelContainer;
import tools.PictureTools;
import gui.elements.ArtworkTypeChoice;
import model.elements.ArtPieceEntry;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static gui.dialogFactory.OkCancelOption.*;

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
    private JTextField storageLocationField;
    private JLabel exhibitionLabel;


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

        JPanel entryPanel = new JPanel(new GridLayout(7,4));

        initTextFields(entryPanel);
        initBuyerSelection(entryPanel);
        initExhibitionSelection(entryPanel);


        centralPanel.add(entryPanel, BorderLayout.CENTER);

        JPanel storageLocationPanel = createStorageLocationPanel();

        centralPanel.add(storageLocationPanel, BorderLayout.SOUTH);

        mainPanel.add(centralPanel);

        JPanel errorAndButtonPanel = new JPanel(new BorderLayout());
        errorAndButtonPanel.add(initErrorLabel(), BorderLayout.NORTH);
        errorAndButtonPanel.add(initOkAndCancelButtons(), BorderLayout.SOUTH);

        mainPanel.add(errorAndButtonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
        revalidate();
    }

    private JPanel createStorageLocationPanel() {
        JPanel storageLocationPanel = new JPanel(new GridLayout(1,2));
        JLabel storageLocationLabel = new JLabel(LanguagePackContainer.getLanguagePack().getArtPieceDialogStorageLocation());
        storageLocationField = new JTextField(artPiece.getStorageLocation());
        storageLocationPanel.add(storageLocationLabel);
        storageLocationPanel.add(storageLocationField);
        return storageLocationPanel;
    }

    private void initTypeChoice() {
        JPanel choicePanel =  new JPanel();
        typeChoice = new ArtworkTypeChoice();
        typeChoice.select(artPiece.getType().toString());
        choicePanel.add(typeChoice);
        mainPanel.add(choicePanel, BorderLayout.NORTH);
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
        JButton loadPictureButton = new JButton(LanguagePackContainer.getLanguagePack().getArtPieceDialogLoadPicture());
        loadPictureButton.addActionListener(e -> selectPictureFromDialog());
        previewPanel.add(imagePreviewIcon);
        inputPanel.add(loadPictureButton);
        picturePanel.add(previewPanel, BorderLayout.WEST);
        picturePanel.add(inputPanel, BorderLayout.EAST);
        panel.add(picturePanel);
    }

    private void selectPictureFromDialog() {
        JFileChooser openDialog = DialogFactory.createChooseSingleJPEGDialog();

        int returnVal = openDialog.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                this.picturePath = setPictureFieldToSelectionOf(openDialog);
                repaint();
            } catch (IOException ex) {
                errorInfoLabel.setText(LanguagePackContainer.getLanguagePack().getErrorMessageCouldntLoadPicture());
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

    private JPanel initErrorLabel() {
        JPanel errorInfoPanel = new JPanel();
        errorInfoLabel = new JLabel();
        errorInfoPanel.add(errorInfoLabel);
        return errorInfoPanel;
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
        JLabel nameLabel = new JLabel(
                LanguagePackContainer.getLanguagePack().getArtPieceDialogTitleLabel()
        );
        nameField = new JTextField(artPiece.getName(), 15);
        entryPanel.add(nameLabel);
        entryPanel.add(nameField);
    }

    private void addTechniqueTextFieldTo(JPanel entryPanel) {
        JLabel techniqueLabel = new JLabel(
                LanguagePackContainer.getLanguagePack().getArtPieceDialogTechniqueLabel()
        );
        techniqueField = new JTextField(artPiece.getTechnique(),25);
        entryPanel.add(techniqueLabel);
        entryPanel.add(techniqueField);
    }

    private void addHeightTextFieldTo(JPanel entryPanel) {
        JLabel heightLabel = new JLabel(
                LanguagePackContainer.getLanguagePack().getArtPieceDialogHeightLabel()
        );
        heightField = new JTextField(((Integer)artPiece.getHeight()).toString(), 6);
        entryPanel.add(heightLabel);
        entryPanel.add(heightField);
    }

    private void addWithTextFieldTo(JPanel entryPanel) {
        JLabel widthLabel = new JLabel(
                LanguagePackContainer.getLanguagePack().getArtPieceDialogWidthLabel()
        );
        widthField = new JTextField(((Integer) artPiece.getWidth()).toString(), 6);
        entryPanel.add(widthLabel);
        entryPanel.add(widthField);
    }

    private void addDepthTextFieldTo(JPanel entryPanel) {
        JLabel depthLabel = new JLabel(
                LanguagePackContainer.getLanguagePack().getArtPieceDialogDepthLabel()
        );
        depthField = new JTextField(((Integer) artPiece.getDepth()).toString(), 6);
        entryPanel.add(depthLabel);
        entryPanel.add(depthField);
    }

    private void addLengthTextFieldTo(JPanel entryPanel) {
        JLabel lengthLabel = new JLabel(
                LanguagePackContainer.getLanguagePack().getArtPieceDialogLengthLabel()
        );
        lengthField = new JTextField(((Integer) artPiece.getLength()).toString(), 6);
        entryPanel.add(lengthLabel);
        entryPanel.add(lengthField);
    }

    private void addYearTextFieldTo(JPanel entryPanel) {
        JLabel yearLabel = new JLabel(
                LanguagePackContainer.getLanguagePack().getArtPieceDialogYearLabel()
        );
        yearField = new JTextField(((Integer) artPiece.getYear()).toString(), 6);
        entryPanel.add(yearLabel);
        entryPanel.add(yearField);
    }

    private void addPriceTextFieldTo(JPanel entryPanel) {
        JLabel priceLabel = new JLabel(
                LanguagePackContainer.getLanguagePack().getArtPieceDialogPriceLabel()
        );
        priceField = new JTextField(((Integer) artPiece.getPrice()).toString(), 9);
        entryPanel.add(priceLabel);
        entryPanel.add(priceField);
    }

    private void addEditionTextFieldTo(JPanel entryPanel) {
        JLabel editionLabel = new JLabel(
                LanguagePackContainer.getLanguagePack().getArtPieceDialogEditionLabel()
        );
        editionField =  new JTextField(((Integer)artPiece.getEdition()).toString(), 6);
        entryPanel.add(editionLabel);
        entryPanel.add(editionField);
    }
    private void initBuyerSelection(JPanel entryPanel) {

        JLabel isSoldLabel = new JLabel(
                LanguagePackContainer.getLanguagePack().getArtPieceDialogSoldTo()
        );

        buyerLabel = new JLabel(artPiece.getBuyersRepresentation());

        JButton selectBuyerButton = new JButton(
                LanguagePackContainer.getLanguagePack().getArtPieceDialogSelectBuyer()
        );
        selectBuyerButton.addActionListener( action -> {
                    new Thread(() -> {
                        addBuyerFromSelectionDialog();
                    }).start();
                }
        );

        JButton editBuyerButton = new JButton(LanguagePackContainer.getLanguagePack().getArtPieceDialogEditBuyer());
        editBuyerButton.addActionListener(e ->
                new Thread(() ->
                        editBuyers()).start()
        );

        entryPanel.add(isSoldLabel);
        entryPanel.add(buyerLabel);
        entryPanel.add(selectBuyerButton);
        entryPanel.add(editBuyerButton);
    }

    private void initExhibitionSelection(JPanel entryPanel){
        JLabel infoLabel = new JLabel(
                LanguagePackContainer.getLanguagePack().getArtPieceDialogLastBuyer()
        );
        exhibitionLabel = new JLabel(lastExhibition());
        JButton addExhibitionButton = new JButton(
                LanguagePackContainer.getLanguagePack().getAdd()
        );
        addExhibitionButton.addActionListener(action -> {
            SwingUtilities.invokeLater(new Thread(() -> {
                    ExhibitionViewManager.setRelationBetweenArtpieceAndExhibitionDialog(this, this.artPiece);
                    exhibitionLabel.setText(lastExhibition());
                }));
        });

        JButton editExhibitionsButton = new JButton(LanguagePackContainer.getLanguagePack().getEdit());
        editExhibitionsButton.addActionListener(action -> {
            SwingUtilities.invokeLater(
            new Thread( () -> {
                ExhibitionViewManager.editExhibitionsOfArtpiece(this, this.artPiece);
                exhibitionLabel.setText(lastExhibition());
            }));
        });

        entryPanel.add(infoLabel);
        entryPanel.add(exhibitionLabel);
        entryPanel.add(addExhibitionButton);
        entryPanel.add(editExhibitionsButton);

    }

    private String lastExhibition(){
        List<Exhibition> exhibitions = ModelContainer.getModel().getArtpieceExhibitionRelations().getExhibitionsOfArtpiece(artPiece);
        int lastIndex = exhibitions.size() -1;
        if (lastIndex < 0){
            return LanguagePackContainer.getLanguagePack().getArtPieceDialogNotInAnyExhibitonYet();
        }else {
           Exhibition lastExhibition = exhibitions.get(lastIndex);
            return lastExhibition == null ?
            LanguagePackContainer.getLanguagePack().getErrorExhibitionNotFound(): lastExhibition.getName();
        }

    }

    private void addBuyerFromSelectionDialog() {
        Person buyer = selectPersonFromAddressbook();
        if (buyer != null) {
            artPiece.addBuyer(buyer);
            buyerLabel.setText(artPiece.getBuyersRepresentation());
            mainPanel.repaint();
        }
    }


    private void editBuyers() {
        DialogFactory dialogController = new DialogFactory(controller);
        artPiece.setBuyers(dialogController.editPeopleDialog(artPiece.getBuyers()));
        buyerLabel.setText(artPiece.getBuyersRepresentation());
        mainPanel.repaint();
    }

    private Person selectPersonFromAddressbook() {
        DialogFactory dialogController = new DialogFactory(controller);
        return dialogController.selectPersonFromAddressBookDialog();
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
        artPiece.setStorageLocation(storageLocationField.getText());
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