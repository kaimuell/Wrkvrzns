package pdf;


import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.AreaBreakType;
import model.elements.ArtPieceEntry;


import java.io.IOException;
import java.util.List;

public class Portfolio {

    private List<ArtPieceEntry> artpieces;
    private List<java.awt.Image> images;

    public Portfolio(List<ArtPieceEntry> artpieces, List<java.awt.Image> images) {

        this.artpieces = artpieces;
        this.images = images;
    }

    public void create_PDF(String destination) throws IOException {
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(destination));
        for (int i = 0; i < artpieces.size(); i++) {
            pdfDocument.addNewPage();
        }
        Document doc = new Document(pdfDocument);
        for (int i = 0; i< artpieces.size(); i++) {
            ArtPieceEntry artpiece = artpieces.get(i);
            doc.add( new Image(ImageDataFactory.create(images.get(i), null))); // TODO  Position und Größe definieren
            String description = artpiece.getName() + ", " + artpiece.getTechnique() + ", " + artpiece.getYear() + ", "+  artpiece.getPrice() + " €";
            doc.add(new Paragraph(description));
            doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        }
        pdfDocument.close();
        doc.close();

    }
}
