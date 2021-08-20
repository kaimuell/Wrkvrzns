package view;

import controller.Controller;
import gui.MainFrame;
import model.Model;
import view.pictureView.PictureView;
import view.select_view.SelectViewPanel;
import view.tableView.TableView;

import javax.swing.*;
import java.awt.*;

/**
 * DIe Klasse {@link ViewHub} fasst eine Auswahl an {@link Viewer} zusammen und ermöglicht das Umschalten zwischen ihnen.
 */
public class ViewHub extends JPanel implements Viewer {
    private static final Color STANDARD_BACKGROUND = new Color(144, 143, 143);
    private PictureView pictureView;
    private SelectViewPanel selectView;
    private TableView tableView;
    private ViewOption viewOption;
    private Controller controller;
    private MainFrame mainFrame;

    public ViewHub(MainFrame mainFrame, PictureView pictureView, SelectViewPanel selectView, TableView tableView, ViewOption viewOption, Controller controller) {
        this.mainFrame = mainFrame;
        this.pictureView = pictureView;
        this.selectView = selectView;
        this.tableView = tableView;
        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.setBackground(STANDARD_BACKGROUND);
        this.setViewModeTo(viewOption);
    }


    public void setViewModeTo(ViewOption viewOption){
        this.removeAll();
        this.viewOption = viewOption;
        if (viewOption == ViewOption.PICTURE_VIEW){

            this.add(pictureView, BorderLayout.CENTER);
            pictureView.refreshView();
        } else if (viewOption == ViewOption.SELECT_VIEW){
            this.add(selectView, BorderLayout.CENTER);
            selectView.refreshView();
        } else if (viewOption == ViewOption.TABLE_VIEW){
            this.add(tableView.getTableHeader(), BorderLayout.NORTH);
            this.add(tableView, BorderLayout.CENTER);
            this.revalidate();
            tableView.refreshView();
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
        } else if (viewOption == ViewOption.TABLE_VIEW){
            tableView.refreshView();
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
        tableView.setModelTo(model);
    }
}
