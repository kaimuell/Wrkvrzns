package pdf;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import gui.dialogFactory.calculationDialog.ArtPieceWithNewPrice;
import model.elements.ArtPieceEntry;

import java.io.IOException;
import java.util.List;

public class PriceList {
    private List<ArtPieceWithNewPrice> artPieceEntries;

    public PriceList(List<ArtPieceWithNewPrice> artPieceEntries) {
        this.artPieceEntries = artPieceEntries;
    }

    public void create_PDF(String destination) throws IOException {
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(destination));
        pdfDocument.addNewPage();
        Document doc = new Document(pdfDocument);
        float colWidth[] = {150f, 250f};
        Table table = new Table(colWidth);
        table.setHorizontalBorderSpacing(5);

        for (ArtPieceWithNewPrice entryWithNewPrice : artPieceEntries) {
            ArtPieceEntry entry = entryWithNewPrice.getArtPiece();
            int price = entry.getPrice();

            table.addCell(new Cell(4, 0).add(new Image(ImageDataFactory.create(entry.getBitmap(), null))
                    .setWidth(100f)
                    .setHeight(100f)
            ).setBorder(Border.NO_BORDER));
            table.addCell(new Cell()
                    .add(new Paragraph(new StringBuilder().append("Name : ").append(entry.getName()).toString()))
                    .setBorder(Border.NO_BORDER));
            table.addCell(new Cell()
                    .add(new Paragraph("Technik : " + entry.getTechnique()))
                    .setBorder(Border.NO_BORDER));
            table.addCell(new Cell()
                    .add(new Paragraph("Jahr : " + entry.getYear()))
                    .setBorder(Border.NO_BORDER));
            table.addCell(new Cell()
                    .add(new Paragraph("Preis : " + price))
                            .setBorder(Border.NO_BORDER));
        }
        doc.add(table);
        doc.close();
        pdfDocument.close();
    }

}
