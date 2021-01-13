package view;

import model.Model;

public interface Views {
    void refreshView();
    void changeSelectedElements();

    void setModelTo(Model model);
}
