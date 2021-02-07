package adressbook.controller;

import adressbook.model.ABModel;

/**
 * Klasse Um Sortierfunktionen auf dem Adressbuch auszuführen.
 */

class PersonSorter {

    /**
     * Sortiert das übergebene {@link ABModel} nach Nachnamen
     * @param model
     */
    static void SortByLastName(ABModel model) {
        model.getPersonList().sort((p1,p2) -> p1.getFamilyName().compareTo(p2.getFamilyName()));
    }
}
