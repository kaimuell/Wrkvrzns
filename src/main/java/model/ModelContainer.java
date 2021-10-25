package model;

import adressbook.model.ABModel;
import exhibitions.model.ExhibitionsModel;

import java.util.ArrayList;

public class ModelContainer {
    private static ModelContainer singleInstance = new ModelContainer();
    private Model model;

    private ModelContainer() {
        this.model = new Model(new ABModel(), new ExhibitionsModel(new ArrayList<>()), new ArtPieceExhibitionRelationContainer());
    }

    public static Model getModel(){
        if (singleInstance == null){
            singleInstance = new ModelContainer();
        }
        return singleInstance.getModelInstance();
    }

    public static void setModel(Model newModel) {
        if (singleInstance == null){
            singleInstance = new ModelContainer();
        }
        singleInstance.model = newModel;
    }

    private Model getModelInstance() {
        return this.model;
    }

}
