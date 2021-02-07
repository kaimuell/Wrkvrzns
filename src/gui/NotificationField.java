package gui;

import javax.swing.*;


/**
 * Ein textfeld zur anzeige von Statusmeldungen des {@link controller.Controller}
 */
public class NotificationField extends JTextField implements MessageBord{

    public NotificationField() {
        this.setEditable(false);
    }

    @Override
    public void pushMessage(String message) {
        this.setText(message);
        this.revalidate();
    }
}
