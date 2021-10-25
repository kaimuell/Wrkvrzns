package exhibitions;

import exhibitions.entities.Exhibition;
import gui.dialogFactory.OkCancelOption;
import exhibitions.model.ExhibitionsModel;
import model.ModelContainer;
import model.elements.ArtPieceEntry;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Fenster zum Anzeigen einer Liste von Ausstellungen
 */

class ExhibitionViewFrame extends JFrame implements ExhibitionView {

    private final ArtPieceEntry relatedArtpiece;
    private final ExhibitionWindowController windowController;
    private ExhibitionsModel model;

    /**
     *  @param withAddingOption  können Ausstellungen hinzugefügt werden?
     * @param withCancelButton  hat das Fenster einen Button zum Abbrechen?
     * @param isEditable        sind die Ausstellungen editierbar?
     * @param relatedArtpiece  das Kunstwerk für welches die Ausstellungen gelten.
     * @param windowController der Controller der das Verhalten der Buttons festlegt.
     */
    ExhibitionViewFrame(boolean withAddingOption, boolean withCancelButton, boolean isEditable, ArtPieceEntry relatedArtpiece, ExhibitionWindowController windowController) {
        super("Ausstellungen");
        this.relatedArtpiece = relatedArtpiece;
        this.model = this.relatedArtpiece == null ? ModelContainer.getModel().getExhibitions() : filterExhibitions(relatedArtpiece);
        ExhibitionsController controller = new ExhibitionsController(model, relatedArtpiece);
        this.windowController = windowController;
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(500, 500));

        ExhibitionToolbar toolbar = new ExhibitionToolbar(this, controller, withAddingOption, model);
        this.add(toolbar, BorderLayout.NORTH);
        ExhibitionPanelList epl = new ExhibitionPanelList(this, controller, isEditable, model);
        this.add(epl, BorderLayout.CENTER);
        controller.addView(epl);
        controller.addView(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        JButton okButton =  new JButton("OK");
        okButton.addActionListener(action -> windowController.release(this, model, OkCancelOption.OK));
        buttonPanel.add(okButton);

        if (withCancelButton){
            JButton cancelButton = new JButton("ABBRECHEN");
            cancelButton.addActionListener(action -> windowController.release(this, model, OkCancelOption.CANCEL));
            buttonPanel.add(cancelButton);
        }
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private ExhibitionsModel filterExhibitions(ArtPieceEntry relatedArtpiece) {
        List<Exhibition> relations = ModelContainer.getModel().getArtpieceExhibitionRelations().getExhibitionsOfArtpiece(relatedArtpiece);
        return new ExhibitionsModel(relations);
    }

    @Override
    public void refreshView() {
        revalidate();
        repaint();
    }

}
