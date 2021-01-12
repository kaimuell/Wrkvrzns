package tests;

import adressbook.model.ABModel;
import controller.Controller;
import controller.ControllerImplementation;
import controller.DialogController.DialogController;
import controller.FileHandler.FileHandler;

import static java.lang.Thread.sleep;


public class ArtpieceDialogTest {

    public static void main(String[] args){
        FileHandler fileHandler = new FileHandler();
        try{
            sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        Controller controller = new ControllerImplementation(fileHandler);
        new DialogController(controller).createNewArtPieceDialogThread().start();


    }
}
