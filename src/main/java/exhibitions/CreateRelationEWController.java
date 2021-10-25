package exhibitions;

import exhibitions.model.ExhibitionsModel;
import gui.dialogFactory.OkCancelOption;
import model.ArtpieceExhibitionRelation;
import model.ModelContainer;
import model.elements.ArtPieceEntry;

public class CreateRelationEWController implements ExhibitionWindowController{

    private final ArtPieceEntry artPiece;

    public CreateRelationEWController(ArtPieceEntry artPieceEntry) {
        this.artPiece = artPieceEntry;
    }

    @Override
    public void release(ExhibitionViewFrame evf, ExhibitionsModel model, OkCancelOption okCancelOption) {
        if(okCancelOption == OkCancelOption.OK){
            ModelContainer.getModel().getArtpieceExhibitionRelations().addRelation(new ArtpieceExhibitionRelation(artPiece, model.getSelectedExhibition()));
        }
        evf.dispose();
    }
}
