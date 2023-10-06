package se.kaiserbirch.views;

import se.kaiserbirch.controller.Controller;
import se.kaiserbirch.controller.UiState;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Flow;

public class ViewController implements Flow.Processor {
    private UiState uiState;

    public void mainView(UiState _uiState, Controller controller) {
        this.uiState = _uiState;

        JPanel timeAndDatePanel = TimeAndDateView.timeAndDatePanel(controller,uiState);

    JFrame frame = new JFrame("State Machine");
        frame.setSize(800,600);
        frame.setContentPane(timeAndDatePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        /*
    JPanel jPanel = new Display(uiState);
    JLabel jLabel = new JLabel("Test");
    jPanel.add(jLabel);
        frame.setContentPane(jPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

         */
    }


    public void updateUiState(UiState uiState) {
        this.uiState = uiState;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {

    }

    @Override
    public void onNext(Object item) {

    }



    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void subscribe(Flow.Subscriber subscriber) {

    }
}
