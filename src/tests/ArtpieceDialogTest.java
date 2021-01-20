package tests;

import controller.Controller;
import controller.ControllerImplementation;
import controller.dialogController.DialogController;
import controller.fileHandler.FileHandler;

import static java.lang.Thread.sleep;


public class ArtpieceDialogTest {

    public static void main(String[] args){
        FileHandler fileHandler = new FileHandler();
        Controller controller = new ControllerImplementation(fileHandler);
        new DialogController(controller).createNewArtPieceDialogThread().start();


    }
}
