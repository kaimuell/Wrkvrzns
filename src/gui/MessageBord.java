package gui;

/**
 * Interface zur Anzeige von Textnachrichten. Wird von dem {@link controller.Controller} genutzt um Statusmeldungen anzuzeigen.
 */

public interface MessageBord {
    void pushMessage(String message);
}
