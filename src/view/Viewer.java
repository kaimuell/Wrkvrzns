package view;

import model.Model;

public interface Viewer {

    /**
     * veranlasst ein vollständiges Neuzeichnen
     */
    void refreshView();

    /**
     * Ändere die Anzeige der ausgewählten Einträge
     */
    void changeBackgroundOfSelectedElements();

    /**
     * Setzt das betrachtete Model auf das übergebene Model
     * @param model das Model
     */
    void setModelTo(Model model);
}