package se.kaiserbirch.views;

import se.kaiserbirch.controller.Controller;
import se.kaiserbirch.controller.UiState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Flow;


public class ViewController implements Flow.Subscriber<UiState> {
    private UiState uiState;
    private Controller controller;
    private Flow.Subscription subscription;
    private TimeAndDateView timeAndDateView;

    private void onChangeModeClick(ActionEvent e) {
        controller.changeMode();
    }

    private void onReadyToSetClick(ActionEvent e) {
        switch (uiState.getCurrentState()) {
            case S1, S2 -> controller.readyToSet();
            case S3, S4 -> {
                String userInput = timeAndDateView.getText();
                if (validate(userInput)) {
                    controller.setDateOrTime(userInput);
                }
            }
        }
    }

    private boolean validate(String text) {
        switch (uiState.getCurrentState()) {
            case S3 -> {
                return true;
            }
            case S4 -> {
                return true;
            }
            default -> throw new IllegalStateException("Unexpected value: " + uiState.getCurrentState());
        }
    }

    private void timer() {
        ViewController viewController = this;
        TimerTask refresh = new TimerTask() {
            @Override
            public void run() {
                if (uiState.getCurrentState() == UiState.State.S1) {
                    viewController.update();
                }

            }
        };
        java.util.Timer timer = new Timer("refresh");
        timer.scheduleAtFixedRate(refresh, 0, 1000);
    }


    public void mainView(Controller controller) {
        this.controller = controller;
        this.uiState = controller.getUiState();
        controller.subscribe(this);
        timeAndDateView = new TimeAndDateView.Builder()
                .setChangeModeFunction(this::onChangeModeClick)
                .setReadyToSetFunction(this::onReadyToSetClick)
                .build();

        JPanel panel = timeAndDateView;
        JFrame frame = new JFrame("State Machine");
        frame.setSize(800, 600);
        frame.setLayout(new FlowLayout());
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
        frame.setVisible(true);
        timer();
    }

    private void update() {
        timeAndDateView.setCurrentState(uiState.getCurrentState().toString());
        switch (uiState.getCurrentState()) {
            case S1 -> {
                timeAndDateView.setTextToDisplay(uiState.getTime().toString());
                timeAndDateView.setReadyToSetButtonText("Ready to Set");
                timeAndDateView.enableChangeModeButton();
                timeAndDateView.removeTextPrompt();
            }
            case S2 -> {
                timeAndDateView.setTextToDisplay(uiState.getDate().toString());
                timeAndDateView.setReadyToSetButtonText("Ready to Set");
                timeAndDateView.enableChangeModeButton();
                timeAndDateView.removeTextPrompt();
            }
            case S3 -> {
                timeAndDateView.setTextToDisplay("Please enter time");
                timeAndDateView.setReadyToSetButtonText("Set");
                timeAndDateView.addTextPrompt("19:20:20");
                timeAndDateView.disableChangeModeButton();
            }
            case S4 -> {
                timeAndDateView.setTextToDisplay("Please enter a date");
                timeAndDateView.setReadyToSetButtonText("Set");
                timeAndDateView.addTextPrompt("2023-10-08");
                timeAndDateView.disableChangeModeButton();
            }
        }
    }


    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(UiState uiState) {
        this.uiState = uiState;
        update();
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}