package se.kaiserbirch.controller;

import se.kaiserbirch.model.Date;
import se.kaiserbirch.model.Model;
import se.kaiserbirch.model.Time;
import se.kaiserbirch.views.ViewController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.Flow;

public class Controller implements ControllerInterface, Flow.Publisher {
    ViewController viewController;
    Model model;
    public Controller(ViewController viewController, Model model){
        // Set the uiState to hold current time and date
        this.uiState = new UiState.UiStateBuilder()
                .setTime(new Time())
                .setDate(new Date())
                .setCurrentState(UiState.State.S1)
                .build();
        this.viewController = viewController;
        this.model = model;
    }

    private UiState uiState;


    @Override
    public void onSetTime(String time) {
        if(uiState.getCurrentState() == UiState.State.S3) {
            LocalTime localTime = LocalTime.parse(time);
            uiState = new UiState.UiStateBuilder()
                    .copyUiState(uiState)
                    .setTime(new Time(localTime))
                    .build();
        }
    }

    @Override
    public void changeMode() {
        this.uiState = new UiState.UiStateBuilder()
                .copyUiState(uiState)
                .setCurrentState(
                        uiState.getCurrentState() == UiState.State.S1?
                                UiState.State.S2:
                                UiState.State.S1
                )
                .build();
        this.viewController.updateUiState(uiState);
    }

    @Override
    public void onSetDate(String date) {
        if (uiState.getCurrentState() == UiState.State.S4) {
            LocalDate localDate = LocalDate.parse(date);
            uiState = new UiState.UiStateBuilder()
                    .copyUiState(uiState)
                    .setDate(new Date(localDate))
                    .build();
        }
    }

    public void run() {
        viewController.mainView(uiState, this);
    }

    @Override
    public void readyToSet() {
        this.uiState = new UiState.UiStateBuilder()
                .copyUiState(uiState)
                .setCurrentState(
                        switch (uiState.getCurrentState()){
                            case S1 -> UiState.State.S3;
                            case S2 -> UiState.State.S4;

                            default ->
                                    throw new IllegalStateException("Unexpected value: " + uiState.getCurrentState());
                        }
                )
                .build();
        this.viewController.updateUiState(uiState);
        
    }

    @Override
    public void subscribe(Flow.Subscriber subscriber) {

    }
}


