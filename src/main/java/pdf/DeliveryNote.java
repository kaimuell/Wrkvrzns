package pdf;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.BorderRadius;
import model.elements.ArtPieceEntry;
import java.io.IOException;
import java.util.List;

public class DeliveryNote{
    private List<ArtPieceEntry> artPieceEntries;
    private boolean withPrices;

     public DeliveryNote(List<ArtPieceEntry> artPieceEntries, boolean withPrices){
         this.artPieceEntries = artPieceEntries;
         this.withPrices = withPrices;
     }

     public void create_PDF(String destination) throws IOException {
         PdfDocument pdfDocument = new PdfDocument(new PdfWriter(destination));
         pdfDocument.addNewPage();
         Document doc = new Document(pdfDocument);
         float colWidth[] = {150f ,250f};
         Table table = new Table(colWidth);
         table.setHorizontalBorderSpacing(5);

         for (ArtPieceEntry entry : artPieceEntries) {
             table.addCell(new Cell(4,0).add(new Image(ImageDataFactory.create(entry.getBitmap(), null))
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
                     .add(new Paragraph(withPrices ? ("Preis : " + entry.getPrice()) : " "))
                     .setBorder(Border.NO_BORDER));
         }
         doc.add(table);
         doc.close();
         pdfDocument.close();
     }

}
