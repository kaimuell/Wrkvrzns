package pdf;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import model.elements.ArtPieceEntry;
import java.io.IOException;
import java.util.List;

public class DeliveryNote{
    private List<ArtPieceEntry> artPieceEntries;

     public DeliveryNote(List<ArtPieceEntry> artPieceEntries){
         this.artPieceEntries = artPieceEntries;
     }


     public void create_PDF(String destination) throws IOException {
         PdfDocument pdfDocument = new PdfDocument(new PdfWriter(destination));
         Document doc = new Document(pdfDocument);
         for (ArtPieceEntry entry : artPieceEntries) {
            doc.add(new Image(ImageDataFactory.create(entry.getBitmap(), null)));
            doc.add(new Paragraph("Name : " + entry.getName()));
            doc.add(new Paragraph("Technick : " + entry.getTechnique()));
            doc.add(new Paragraph("Jahr : " + entry.getYear()));
            doc.add(new Paragraph());
         }
     }
}
