package tests;

import adressbook.model.ABModel;
import controller.Controller;
import controller.ControllerImplementation;
import controller.DialogController.DialogController;
import model.Model;
import model.elements.ArtPieceEntry;

public class ArtpieceDialogTest {

    public static void main(String[] args){
        ABModel ab = new ABModel();
        Model model = new Model(ab);
        Controller controller = new ControllerImplementation(model, ab);
        new DialogController(controller).createNewArtPieceDialogThread().start();
        while (model.getPieces().size() == 0);
        for (ArtPieceEntry p : model.getPieces()) {
            System.out.println(p.getType().toString());
            System.out.println(p.getName());
            System.out.println(p.getTechnique());
            System.out.println(p.getMeatDataRepresentation());
            System.out.println(p.getYear());
        }
    }
}
