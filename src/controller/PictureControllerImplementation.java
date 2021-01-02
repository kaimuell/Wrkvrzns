package controller;

import java.awt.*;

public class PictureControllerImplementation implements PictureController {


    @Override
    public Image createBitmap(Image image) {
        return image.getScaledInstance(50,50,Image.SCALE_SMOOTH);
    }

    @Override
    public void loadPicture(String path) {
        //TODO
    }

    @Override
    public void savePicture(Image image, String path) {
        //TODO
    }
}
