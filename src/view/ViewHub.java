package view;

import controller.Controller;
import gui.MainFrame;
import model.Model;
import view.pictureView.PictureView;
import view.select_view.SelectViewPanel;

import javax.swing.*;

/**
 * DIe Klasse {@link ViewHub} fasst eine Auswahl an {@link Viewer} zusammen und ermöglicht das Umschalten zwischen ihnen.
 */
public class ViewHub extends JPanel implements Viewer {
    PictureView pictureView;
    SelectViewPanel selectView;
    ViewOption viewOption;
    Controller controller;
    MainFrame mainFrame;

    public ViewHub(MainFrame mainFrame, PictureView pictureView, SelectViewPanel selectView, ViewOption viewOption, Controller controller) {
        this.mainFrame = mainFrame;
        this.pictureView = pictureView;
        this.selectView = selectView;
        this.controller = controller;
        this.setViewModeTo(viewOption);
    }


    public void setViewModeTo(ViewOption viewOption){
        this.removeAll();
        this.viewOption = viewOption;
        if (viewOption == ViewOption.PICTURE_VIEW){
            this.add(pictureView);
            pictureView.refreshView();
        } else if (viewOption == ViewOption.SELECT_VIEW){
            this.add(selectView);
            selectView.refreshView();
        }
        this.revalidate();
        mainFrame.refreshView();

    }

    @Override
    public void refreshView() {
        if(viewOption == ViewOption.PICTURE_VIEW){
            pictureView.refreshView();
        } else if(viewOption == ViewOption.SELECT_VIEW){
            selectView.refreshView();
        }
    }

    @Override
    public void changeBackgroundOfSelectedElements() {
        if (viewOption == ViewOption.PICTURE_VIEW) {
            pictureView.changeBackgroundOfSelectedElements();
        } else if (viewOption == ViewOption.SELECT_VIEW) {
            selectView.changeBackgroundOfSelectedElements();
        }
    }

    @Override
    public void setModelTo(Model model) {
        pictureView.setModelTo(model);
        selectView.setModelTo(model);
    }
}
