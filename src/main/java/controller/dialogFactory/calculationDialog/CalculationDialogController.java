package controller.dialogFactory.calculationDialog;

import controller.dialogFactory.OkCancelOption;

import java.util.List;

public interface CalculationDialogController {
    void edit(OkCancelOption okCancelOption, List<ArtPieceWithNewPrice> artPieceWithNewPriceList);
}
