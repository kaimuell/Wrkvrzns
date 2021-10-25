package exhibitions;

import exhibitions.entities.Exhibition;
import exhibitions.model.ExhibitionsModel;
import model.ModelContainer;
import model.elements.ArtPieceEntry;

import java.util.ArrayList;
import java.util.List;


public class ExhibitionsController {
    private final ArtPieceEntry artpieceEntry;
    private ExhibitionsModel exhibitionsModel;
    private List<ExhibitionView> views;

    public ExhibitionsController(ExhibitionsModel exhibitionsModel, ArtPieceEntry entry) {
        this.exhibitionsModel = exhibitionsModel == null ? new ExhibitionsModel(null) : exhibitionsModel;
        this.artpieceEntry = entry;
        this.views = new ArrayList<>();
    }

    public void addView (ExhibitionView view){
        this.views.add(view);
    }

    public void addExhibition(Exhibition exhibition, boolean isChangedGlobal){
        if (exhibition.getId() == -1 || exhibitionsModel.containsId(exhibition.getId())){
            exhibition.setId(createID());
        }
        if (isChangedGlobal){
            addGlobal(exhibition);
        }else {
            exhibitionsModel.addExhibition(exhibition);
        }
        updateViews();
    }

    private void addGlobal(Exhibition exhibition) {
        ModelContainer.getModel().getExhibitions().addExhibition(exhibition);
    }

    private void updateViews() {
        for (ExhibitionView view : views) {
            view.refreshView();
        }
    }

    private int createID (){
        int id = 0;
        while (exhibitionsModel.containsId(id)){
            id++;
        }
        return id;
    }

    public void selectExhibition(Exhibition exhibition) {
        exhibitionsModel.setSelectedExhibition(exhibition);
        updateViews();
    }

    public void deleteSelectedExhibition() {
        Exhibition exhibition = exhibitionsModel.getSelectedExhibition();
        if (exhibition != null) {
            exhibitionsModel.removeExhibition(exhibition);
            if (this.artpieceEntry == null) {
                ModelContainer.getModel().getArtpieceExhibitionRelations().removeRelationsOfExhibition(exhibition);
            }else {
                ModelContainer.getModel().getArtpieceExhibitionRelations().removeRelation(this.artpieceEntry, exhibition);
            }
            exhibitionsModel.setSelectedExhibition(null);
            updateViews();
        }
    }
}
