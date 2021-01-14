package tests;

import controller.Controller;
import controller.ControllerImplementation;
import controller.FileHandler.FileHandler;
import model.elements.ArtPieceEntry;

import view.DebugView;

public class controllerTest {
    public static void main(String[] args){
        FileHandler fileHandler = new FileHandler();
        Controller controller = new ControllerImplementation(fileHandler);
        controller.addView(new DebugView());
        ArtPieceEntry entry = ArtPieceEntry.createEmptyArtPieceEntry();
        controller.addEntry(entry, null);
        controller.setSelectedElementTo(entry);
        controller.addSelectedElement(entry);

    }
}