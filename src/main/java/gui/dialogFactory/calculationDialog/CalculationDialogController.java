package gui.dialogFactory.calculationDialog;

import gui.dialogFactory.OkCancelOption;

import java.util.List;

public interface CalculationDialogController {
    void edit(OkCancelOption okCancelOption, List<ArtPieceWithNewPrice> artPieceWithNewPriceList);
}
