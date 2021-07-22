package pdf;


import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Line;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.AreaBreakType;
import model.elements.ArtPiece;
import model.elements.ArtPieceEntry;


import java.io.IOException;
import java.util.List;

public class Portfolio {

    List<ArtPieceEntry> artpieces;

    public Portfolio(List<ArtPieceEntry> artpieces) {
        this.artpieces = artpieces;
    }

    public void create_PDF(String destination) throws IOException {
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(destination));
        for (int i = 0; i < artpieces.size(); i++) {
            pdfDocument.addNewPage();
        }
        Document doc = new Document(pdfDocument);
        for (ArtPieceEntry artpiece : artpieces) {
            doc.add( new Image(ImageDataFactory.create(artpiece.getBitmap(), null))); // TODO ändern zu tatsächlichem Bild, Position und Größe definieren
            String description = artpiece.getName() + ", " + artpiece.getTechnique() + ", " + artpiece.getYear() + ", "+  artpiece.getPrice() + " €";
            doc.add(new Paragraph(description));
            doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        }

    }
}
