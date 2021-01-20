package tools;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Fasst Operationen auf Bildern zusammen
 */

public class PictureTools {

    /**
     * erschafft ein Bitmap aus den übergebenen Parametern
     * @param image das QuellBild
     * @param width die gewünschte Breite
     * @param height die gewünschte Höhe
     * @return das Bitmap
     */
    public static Image createBitmap(Image image, int width, int height){
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        //TODO Abändern, so dass die Proportionen des übergebenen Bildes eingehalten werden
    }

    public static Image loadImage(String filepath) throws IOException{
        File dat = new File(filepath);
        BufferedImage img = ImageIO.read(dat);
        return (Image) img;
    }

    /**
     *  Speichert ein Bild als JPG in der angegebenen Datei mit der entsprechenden Qualität
     * @param imageToSave das Bild
     * @param outputFile der Dateipfad
     * @param quality die Qualität. Bsp : 1f = 100% 0.7f = 70%
     * @throws IOException speichern fehlgeschlagen
     */

    public static void saveImage(Image imageToSave, File outputFile, float quality) throws IOException {

        BufferedImage image = imageToBufferedImage(imageToSave);
        ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
        ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
        jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpgWriteParam.setCompressionQuality(quality);

        jpgWriter.setOutput(ImageIO.createImageOutputStream(outputFile));
        IIOImage outputImage = new IIOImage(image, null, null);
        jpgWriter.write(null, outputImage, jpgWriteParam);
        jpgWriter.dispose();

    }


    /**
     * Gibt das Standardbild zurück, welches genutzt wird, falls noch kein Bild zugewiesen wurde
     * @return das STandardbild
     */
    public static Image defaultEmptyImage() {
        return createEmptyImage();
    }

    static BufferedImage imageToBufferedImage(Image im) {
        BufferedImage bi = new BufferedImage
                (im.getWidth(null),im.getHeight(null),BufferedImage.TYPE_INT_RGB);
        Graphics bg = bi.getGraphics();
        bg.drawImage(im, 0, 0, null);
        bg.dispose();
        return bi;
    }

    private static Image createEmptyImage(){
        BufferedImage image = new BufferedImage(150,150, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.drawRect(0,0,150,150);

        return image;
    }
    
}
