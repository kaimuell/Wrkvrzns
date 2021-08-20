package adressbook.controller;

import adressbook.view.ABViewer;
import adressbook.model.Person;
import adressbook.model.PersonEntry;

/**
 * Das Interface des Controllers eines Adressbuchs
 */

public interface ABController {

    /**
     * Selektiert einen PersonEntry
     * @param p der Eintrag
     */
    void selectPerson(PersonEntry p);

    /**
     * entfernt den aktuell selektierten Eintrag
     */
    void deleteSelectedPerson();

    /**
     * fügt eine Person dem Model hinzu
     * @param person die Person
     */
    void addPerson(Person person);

    /**
     * Stellt die Werte des {@link PersonEntry} mit der ID auf die Werte der übergebenen {@link Person}
     * @param id die ID
     * @param person die Person
     */
    void modifyPerson(int id, Person person);

    /**
     * Meldet ein View als Listener an
     * @param view das View
     */
    void addViewAsListener(ABViewer view);

    /**
     * Getter  Methode für den Ausgewählten Eintrag
     * @return der Eintrag
     */
    PersonEntry getSelectedPerson();

    /**
     * Filtert das {@link adressbook.model.ABModel} danach ob Name, Nachname oder Email
     * einen Teilstring des übergebenen Wortes enthalten.
     * @param input das Wort
     */
    void filter(String input);
}
