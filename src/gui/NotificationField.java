package gui;

import javax.swing.*;

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
