package exhibitions.controller;

import exhibitions.ExhibitionView;
import exhibitions.model.Exhibition;
import exhibitions.model.ExhibitionsModel;

import java.util.ArrayList;
import java.util.List;

public class ExhibitionsController {
    private ExhibitionsModel exhibitionsModel;
    private List<ExhibitionView> views;

    public ExhibitionsController(ExhibitionsModel exhibitionsModel) {
        this.exhibitionsModel = exhibitionsModel == null ? new ExhibitionsModel(null) : exhibitionsModel;
        this.views = new ArrayList<>();
    }

    public void addView (ExhibitionView view){
        this.views.add(view);
    }

    public void addExhibition(Exhibition exhibition){
        if (exhibition.getId() == -1 && exhibitionsModel.containsId(exhibition.getId())){
            exhibition.setId(createID());
        }
        exhibitionsModel.getExhibitions().add(exhibition);
        updateViews();
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
            exhibitionsModel.getExhibitions().remove(exhibition);
            exhibitionsModel.setSelectedExhibition(null);
            updateViews();
        }
    }
}
