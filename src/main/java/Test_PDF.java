import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import  org.slf4j.impl.JDK14LoggerFactory;
import tools.PictureTools;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import static tools.PictureTools.defaultEmptyImage;

public class Test_PDF {

    public static final String DEST = "./hello.pdf";

    public static void main (String[] args) {
        try {
            PdfDocument pdf = new PdfDocument(new PdfWriter(DEST));
            pdf.addNewPage();
            Document document = new Document(pdf);
            String line = "TEST TEST TEST TEST";
            document.add(new Paragraph(line));
            ImageData imageData = ImageDataFactory.create(defaultEmptyImage(), Color.RED);
            document.add(new Image(imageData));
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
