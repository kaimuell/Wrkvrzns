package gui.view;

import model.Model;

/**
 * Interface für die Views des Programms
 */
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
