package view;

import adressbook.model.PersonEntry;
import model.Model;
import model.elements.ArtPieceEntry;

public class DebugView implements Viewer {


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
            System.out.println(entry.getSizeRepresentation());
            System.out.println(entry.getYear());
            System.out.println(entry.getPrice());
            System.out.println();
        }
        System.out.println();
        for (PersonEntry person: model.getAdressbook().getPersonList()) {
            System.out.println(person.getFirstName());
            System.out.println(person.getFamilyName());
            System.out.println(person.geteMail());
            System.out.println(person.getTel());
            System.out.println(person.getAdress().getStreet() + person.getAdress().getHouseNo());
        }
    }

    @Override
    public void changeBackgroundOfSelectedElements() {

    }

    @Override
    public void setModelTo(Model model) {
        this.model = model;
    }
}
