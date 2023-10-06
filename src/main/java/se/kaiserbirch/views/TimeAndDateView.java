package se.kaiserbirch.views;

import se.kaiserbirch.controller.Controller;
import se.kaiserbirch.controller.UiState;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Flow;

public class TimeAndDateView implements Flow.Subscriber<UiState> {

    public static JPanel timeAndDatePanel(Controller controller, UiState uiState)

    {
        JPanel jPanel = new JPanel();
        JButton changeModeButton = new JButton();
        changeModeButton.setText("Change Mode");
        changeModeButton.addActionListener(e -> controller.changeMode());
        JButton readyToSetButton = new JButton();
        readyToSetButton.setText("Ready to Set");
        readyToSetButton.addActionListener(e -> controller.readyToSet());
        JLabel timeOrDate = new JLabel(uiState.getTime().toString());
        jPanel.add(timeOrDate);
        jPanel.add(changeModeButton);
        jPanel.add(readyToSetButton);

        TimerTask refresh = new TimerTask() {
            @Override
            public void run() {
                switch (uiState.getCurrentState()){
                    case S1 -> timeOrDate.setText(uiState.getTime().toString());
                    case S2 -> timeOrDate.setText(uiState.getDate().toString());
                }

            }
        };

        java.util.Timer timer = new Timer("refresh");
        timer.scheduleAtFixedRate(refresh,0,1000);

        return jPanel;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {


    }

    @Override
    public void onNext(UiState item) {

    }


    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}