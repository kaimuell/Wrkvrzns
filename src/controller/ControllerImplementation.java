package controller;

import adressbook.controller.ABController;
import adressbook.controller.ABControllerImplementation;
import adressbook.model.ABModel;
import model.Model;
import view.Views;

import java.util.ArrayList;
import java.util.List;

public class ControllerImplementation {
    ABController addressbookController;
    ABModel addressBook;
    Model model;
    List<Views> views;

    public ControllerImplementation(Model model, ABModel addressBook) {
        this.model = model;
        this.views = new ArrayList<>();
        this.addressBook = addressBook;
        this.addressbookController = new ABControllerImplementation(addressBook);
    }




}
