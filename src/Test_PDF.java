import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;

public class Test_PDF {

    public static final String DEST = "hello.pdf";

    public static void main (String[] args) {
        try {
            PdfDocument pdf = new PdfDocument(new PdfWriter(DEST));
            Document document = new Document(pdf);

            String line = "TEST TEST TEST TEST";
            document.add(new Paragraph(line));
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
