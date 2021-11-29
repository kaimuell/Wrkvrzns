package languagePack;

public class LanguagePackDE implements LanguagePack{

    @Override
    public String getToolbarFilterLabel() {return " Filtern nach : ";}

    @Override
    public String getToolbarSortLabel() {return " Sortieren nach : ";}

    @Override
    public String getToolbatSortButton() {return "sortieren";  }

    @Override
    public String getToolbarAddArtPieceButton() { return "Werk hinzufügen"; }

    @Override
    public String getToolbarDeleteEntryButtonButton() { return "Markierte Einträge löschen";  }

    @Override
    public String getConfimDeletionQuestion() { return "Einträge wirklich löschen?";  }

    @Override
    public String getConfim() {  return "Bestätigen";   }

    @Override
    public String getToolbarfilterTextFieldToolTipText() {
        return "Filtert nach dem im Feld zuvor ausgewählten Element." +
                " Ausgewählt werden alle Einträge, welche die Eingabe enthalten. " +
                "Bestätigen mit Enter";
    }

    @Override
    public String getTools() {return "Werkzeuge"; }

    @Override
    public String getToolsMenuAdjustAllPrices() { return "Alle Preise anpassen";  }

    @Override
    public String getToolsMenuAdjustAllPricesToolTipText() {
        return "Verändert die Preise aller Arbeiten um einen bestimmten Prozentsatz";
    }

    @Override
    public String getToolsMenuAdjustPrices() { return "Preise der Auswahl anpassen";  }

    @Override
    public String getToolsMenuAdjustPricesToolTipText() {
        return "Verändert die Preise der ausgewählten Arbeiten um einen bestimmten Prozentsatz";
    }

    @Override
    public String getToolsMenuOpenExhibitions() { return "Ausstellungen";  }

    @Override
    public String getToolsMenuOpenExhibitionsToolTipText() {
        return "Zeigt die Liste der Ausstellungen";
    }

    @Override
    public String getToolsMenuImportAdresses() {
        return "Kontakte importieren";
    }

    @Override
    public String getToolsMenuImportAdressesToolTipText() {
        return "Benötogt eine von Thunderbird exportierte .csv Datei, " +
                "bei der die Einträge durch Komma getrennt sind.";
    }

    @Override
    public String getToolsMenuImportAdressesConfimDialogText() {
        return "Nur Kontakte importieren, die einen gespeicherten Namen haben?";
    }

    @Override
    public String getToolsMenuOpenAddressBook() {
        return "Adressbuch öffnen";
    }

    @Override
    public String getViewsMenuHeader() {
        return "Ansicht";
    }

    @Override
    public String getViewsMenuSetSelectionView() {
        return "Auswahl-Ansicht";
    }

    @Override
    public String getViewsMenuSetSelectionViewToolTipText() {
        return "Die Standard Ansicht des Programms";
    }

    @Override
    public String getViewsMenuSetPictureView() {
        return "Bilder-Ansicht";
    }

    @Override
    public String getViewsMenuSetPictureViewToolTipText() {
        return "Ansicht aus Kacheln von Bildern";
    }

    @Override
    public String getViewsMenuSetTableView() {
        return "Tabellen-Ansicht";
    }

    @Override
    public String getViewsMenuSetTableViewToolTip() {
        return "Ansicht in Tabellenform";
    }

    @Override
    public String getOutputMenueHeader() {
        return "Ausgabe";
    }

    @Override
    public String getOutputMenueCreateDeliveryNode() {
        return "Lieferschein erstellen";
    }

    @Override
    public String getOutputMenueCreateDeliveryNodeToolTip() {
        return "Erstellt eine PDF mit einer Auflistung der ausgewählten Werke";
    }

    @Override
    public String getOutputMenueCreatePriceList() {
        return "Preisliste erstellen";
    }

    @Override
    public String getOutputMenueCreatePriceListToolTip() {
        return "Erstellt eine PDF mit einer Auflistung der ausgewählten Werke";
    }

    @Override
    public String getOutputMenueCreatePriceListAdjPrices() {
        return "Preisliste mit angepassten Preisen erstellen";
    }

    @Override
    public String getOutputMenueCreatePriceListAdjPricesToolTip() {
        return "Erstellt eine PDF mit einer Auflistung der ausgewählten Werke";
    }

    @Override
    public String getNoEntriesSelected() {
        return "Keine Einträge ausgewählt.";
    }

    @Override
    public String getOutputMenueCreatePortfolio() {
        return "Portfolio erstellen";
    }

    @Override
    public String getOutputMenueCreatePortfolioToolTip() {
        return "Erstellt eine PDF mit einem Portfolio der ausgewählten Werke";
    }

    @Override
    public String getPortfolioCreated() {
        return "Portfolio erstellt.";
    }

    @Override
    public String getErrorMessageFileNotMade() {
        return "Die Datei konnte nicht erzeugt werden. " +
                "Eventuell ist sie in einem anderen Programm geöffnet.";
    }

    @Override
    public String getPriceListMade() {
        return "Preisliste erstellt.";
    }

    @Override
    public String getDataMenuHeader() {
        return "Datei";
    }

    @Override
    public String getNew() {
        return "Neu";
    }

    @Override
    public String getDataMenuNewProfile() {
        return "Name des neuen Profils : ";
    }

    @Override
    public String getDataMenuLoad() {
        return "Laden";
    }

    @Override
    public String getDataMenuSave() {
        return "Speichern";
    }

    @Override
    public String getDataMenuSaveAs() {
        return "Speichern als";
    }

    @Override
    public String getDataMenuSaveAsDialog() {
        return "Name des neuen Profils : ";
    }

    @Override
    public String getQuit() {
        return "Beenden";
    }

    @Override
    public String getQuitConfimQuestion() {
        return "Wirklich beenden?";
    }

    @Override
    public String[] getTableAdapterColumnNames() {
        return new String[] {"Name", "Typ", "Technik", "Höhe", "Breite", "Tiefe", "Länge", "Jahr", "Preis", "Auflage", "Käufer"};
    }

    @Override
    public String getChooseSingleProfileDialogDescription() {
        return "Werkverzeichnis Profile";
    }

    @Override
    public String getOk() {
        return "OK";
    }

    @Override
    public String getCancel() {
        return "Abbrechen";
    }

    @Override
    public String getArtPieceDialogStorageLocation() {
        return "Lagerort : ";
    }

    @Override
    public String getArtPieceDialogLoadPicture() {
        return "Bild laden";
    }

    @Override
    public String getErrorMessageCouldntLoadPicture() {
        return "Bild konnte nicht geladen werden";
    }

    @Override
    public String getArtPieceDialogTitleLabel() {
        return "Titel";
    }

    @Override
    public String getArtPieceDialogTechniqueLabel() {
        return "Technik";
    }

    @Override
    public String getArtPieceDialogHeightLabel() {
        return "Höhe (in cm)";
    }

    @Override
    public String getArtPieceDialogWidthLabel() {
        return "Breite (in cm)";
    }

    @Override
    public String getArtPieceDialogDepthLabel() {
        return "Tiefe (in cm)";
    }

    @Override
    public String getArtPieceDialogLengthLabel() {
        return "Länge (in min)";
    }

    @Override
    public String getArtPieceDialogYearLabel() {
        return "Entstehungsjahr";
    }

    @Override
    public String getArtPieceDialogPriceLabel() {
        return "Preis (€)";
    }

    @Override
    public String getArtPieceDialogEditionLabel() {
        return "Auflage";
    }

    @Override
    public String getArtPieceDialogSoldTo() {
        return "Verkauft an:";
    }

    @Override
    public String getArtPieceDialogSelectBuyer() {
        return "Käufer hinzufügen";
    }

    @Override
    public String getArtPieceDialogEditBuyer() {
        return "Käufer bearbeiten";
    }

    @Override
    public String getArtPieceDialogLastBuyer() {
        return "Letze Ausstellung";
    }

    @Override
    public String getAdd() {
        return "Hinzufügen";
    }

    @Override
    public String getEdit() {
        return "Bearbeiten";
    }

    @Override
    public String getArtPieceDialogNotInAnyExhibitonYet() {
        return "noch nicht ausgestellt";
    }

    @Override
    public String getErrorExhibitionNotFound() {
        return "Ausstellung nicht gefunden ";
    }

    @Override
    public String getRoundingChoiceDoNotRound() {
        return "Nicht Runden";
    }

    @Override
    public String getNumbersHundred() {
        return "Hundert";
    }

    @Override
    public String getNumbesFifty() {
        return "Fünfzig";
    }

    @Override
    public String getNumbersTen() {
        return "Zehn";
    }

    @Override
    public String getCalculationDialogPercentLabel() {
        return "Prozente aufschlagen : ";
    }

    @Override
    public String getCalculationDialogInfoLabel() {
        return " - für abziehen";
    }

    @Override
    public String getCalculationDialogRoundingLabel() {
        return "Runden auf : ";
    }

    @Override
    public String getErrorMessageCouldntParseInput() {
        return "Konnte Eingaben nicht parsen";
    }


    @Override
    public String[] getSorterChoiceOptions() {
        return new String[]{"Name <", "Name >", "Typ <", "Typ >", "Jahr <", "Jahr >", "Preis <", "Preis >"};
    }

    @Override
    public String[] getFilterChoiceOtions() {
        return new String[]{"Name", "Technik", "Jahr", "Typ"};
    }
}
