package gui.dialogFactory.calculationDialog;

import controller.Controller;
import gui.dialogFactory.OkCancelOption;
import pdf.DeliveryNote;
import pdf.PriceList;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CalculationDialogCreatePriceListControllerImplementation implements CalculationDialogController {
    Controller controller;
    File file;

    public CalculationDialogCreatePriceListControllerImplementation(Controller controller, File file) {
        this.controller = controller;
        this.file = file;
    }

    @Override
    public void edit(OkCancelOption okCancelOption, List<ArtPieceWithNewPrice> artPieceWithNewPriceList) {
        if (okCancelOption == OkCancelOption.OK) {
            PriceList pl = new PriceList(artPieceWithNewPriceList);
            try {
                pl.create_PDF(file.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Die Datei konnte nicht erzeugt werden. "
                        + "Eventuell ist sie in einem anderen Programm geöffnet.");
            }
        }
    }
}
