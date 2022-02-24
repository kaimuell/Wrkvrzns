package tools;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

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
        return  createProportionalScaledImage(width, height, image);

    }

    /**
     * Erstellt eine proportional skalierte Version des Einagebebilds.
     * @param width die Zielbreite
     * @param height die Zielhöhe
     * @param image das EingabeBild
     * @return die proportional skalierte Version
     */
    private static BufferedImage createProportionalScaledImage(int width, int height, Image image){
        BufferedImage imageBuff = imageToBufferedImage(image);

            BufferedImage createdImage = new BufferedImage(width,height, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D g2d = createdImage.createGraphics();
            g2d.setColor(Color.WHITE);
            g2d.drawRect(0,0,width,height);
            if (imageBuff.getHeight() > imageBuff.getWidth()) {
                //Ist Hochformat
                int offset = (width - (width * imageBuff.getWidth() / imageBuff.getHeight()))/2;
                System.out.println("Hochformat mit Offset :" + offset);
                g2d.drawImage(imageBuff , 0+offset, 0,  width - (offset *2), height, null);
            } else {
                //Ist Querformat
                int offset = ((height - (height * imageBuff.getHeight() / imageBuff.getWidth())) /2);
                System.out.println("Querformat mit Offset : " + offset);
                g2d.drawImage(imageBuff, 0, 0+offset, width, (height-(offset *2)), null);
            }

            return createdImage;
    }

    /**
     * Versuch ein Bild aus dem übergebenen Filepath zu laden
     * @param filepath der Pfad der Datei
     * @return das Bild
     * @throws IOException laden fehlgeschlagen
     */
    public static Image loadImage(Path filepath) throws IOException{
        File dat = filepath.toFile();
        BufferedImage img = ImageIO.read(dat);
        return img;
    }

    /**
     * Versuch ein Bild aus dem übergebenen Filepath zu laden
     * @param filepath der Pfad der Datei
     * @return das Bild
     * @throws IOException laden fehlgeschlagen
     */
    public static Image loadImage(String filepath) throws IOException{
        File imageFile = new File(filepath);
        BufferedImage image = ImageIO.read(imageFile);
        return image;
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
     * @return das Standardbild
     */
    public static Image defaultEmptyImage() {
        return createEmptyImage();
    }

    /**
     * Konvertiert ein Bild der Klasse Image in die Klasse BufferedImage
     * @param image das Image
     * @return das BufferedImage
     */
    private static BufferedImage imageToBufferedImage(Image image) {
        BufferedImage bi = new BufferedImage
                (image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);
        Graphics bg = bi.getGraphics();
        bg.drawImage(image, 0, 0, null);
        bg.dispose();
        return bi;
    }

    /**
     * Erstellt ein leeres Bild mit der Größe 150x150 Pixel
     * @return das Bild
     */
    private static Image createEmptyImage(){
        BufferedImage image = new BufferedImage(150,150, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.drawRect(0,0,150,150);

        return image;
    }
}
