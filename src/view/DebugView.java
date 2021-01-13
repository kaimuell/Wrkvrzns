package view;

import model.Model;
import model.elements.ArtPieceEntry;

public class DebugView implements Views {


    private Model model;

    public DebugView() {
    }

    @Override
    public void refreshView() {
        for (ArtPieceEntry entry: model.getPieces()
             ) {
            System.out.println(entry.getId());
            System.out.println(entry.getName());
            System.out.println(entry.getType().toString());
            System.out.println(entry.getTechnique());
            System.out.println(entry.getMeatDataRepresentation());
            System.out.println(entry.getYear());
            System.out.println(entry.getPrice());
            System.out.println(entry.isSold());
            System.out.println(entry.getBuyerID());
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void changeSelectedElements() {

    }

    @Override
    public void setModelTo(Model model) {
        this.model = model;
    }
}
