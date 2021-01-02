package controller;

import java.awt.*;

public interface PictureController {
    Image createBitmap(Image image);
    void loadPicture(String path);
    void savePicture (Image image, String path);
}
