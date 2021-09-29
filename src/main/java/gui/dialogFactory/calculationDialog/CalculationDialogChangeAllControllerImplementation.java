package gui.dialogFactory.calculationDialog;

import controller.Controller;
import gui.dialogFactory.OkCancelOption;

import java.util.List;

public class CalculationDialogChangeAllControllerImplementation implements CalculationDialogController{

    Controller controller;


    public CalculationDialogChangeAllControllerImplementation(Controller controller){
        this.controller = controller;
    }

    @Override
    public void edit(OkCancelOption okCancelOption, List<ArtPieceWithNewPrice> artPieceWithNewPriceList) {
        if (okCancelOption != OkCancelOption.OK){ return;}
        if (artPieceWithNewPriceList == null){return;}
        for (ArtPieceWithNewPrice entryAndPrice : artPieceWithNewPriceList){
            controller.setPriceOfArtpiece(entryAndPrice.getArtPiece(), entryAndPrice.getNewPrice());
        }
        controller.refreshViews();
    }
}
