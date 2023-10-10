package se.kaiserbirch.views;

import javax.swing.*;
import java.awt.event.ActionListener;

class TimeAndDateView extends JPanel {
    private final JLabel timeOrDate = new JLabel();
    private final JLabel currentState = new JLabel();
    private final JButton changeModeButton;
    private final JButton readyToSetButton;
    private final JTextField textPrompt = new JTextField();

    private TimeAndDateView(Builder builder) {
        this.changeModeButton = builder.changeModeButton;
        this.readyToSetButton = builder.readyToSetButton;
        this.add(currentState);
        this.add(timeOrDate);
        this.add(changeModeButton);
        this.add(readyToSetButton);
    }

    protected static class Builder {
        private final JButton changeModeButton = new JButton("Change Mode");
        private final JButton readyToSetButton = new JButton("Ready to Set");

        protected Builder setChangeModeFunction(ActionListener changeModeFunction) {
            this.changeModeButton.addActionListener(changeModeFunction);
            return this;
        }

        protected Builder setReadyToSetFunction(ActionListener readyToSetFunction) {
            this.readyToSetButton.addActionListener(readyToSetFunction);
            return this;
        }

        TimeAndDateView build() {
            return new TimeAndDateView(this);
        }
    }

    protected void setTextToDisplay(String timeOrDate) {
        this.timeOrDate.setText(timeOrDate);
    }

    protected void setCurrentState(String currentState) {
        this.currentState.setText(currentState);
    }

    protected void disableChangeModeButton() {
        this.changeModeButton.setEnabled(false);
    }

    protected void enableChangeModeButton() {
        this.changeModeButton.setEnabled(true);
    }

    protected void setReadyToSetButtonText(String readyToSetButtonText) {
        readyToSetButton.setText(readyToSetButtonText);
    }

    protected void addTextPrompt(String textPromptText) {
        this.add(textPrompt);
        textPrompt.setText(textPromptText);
    }

    protected void removeTextPrompt() {
        this.remove(textPrompt);
    }

    protected String getText() {
        return textPrompt.getText();
    }
}
