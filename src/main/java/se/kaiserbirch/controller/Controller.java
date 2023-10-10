package se.kaiserbirch.controller;

import se.kaiserbirch.model.Date;
import se.kaiserbirch.model.Time;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

import static java.util.concurrent.Flow.Subscriber;

public class Controller implements ControllerInterface, Flow.Publisher<UiState> {
    private final SubmissionPublisher<UiState> submissionPublisher = new SubmissionPublisher<>();
    private UiState uiState;

    public UiState getUiState() {
        return uiState;
    }

    public void subscribe(Subscriber<? super UiState> subscriber) {
        submissionPublisher.subscribe(subscriber);
    }

    public Controller() {
        // Set the uiState to hold current time and date
        this.uiState = new UiState.Builder()
                .setTime(new Time())
                .setDate(new Date())
                .setCurrentState(UiState.State.S1)
                .build();
    }


    @Override
    public void setTime(String time) {
        if (uiState.getCurrentState() == UiState.State.S3) {
            LocalTime localTime = LocalTime.parse(time);
            uiState = new UiState.Builder()
                    .copyUiState(uiState)
                    .setTime(new Time(localTime))
                    .build();
        }
    }

    @Override
    public void changeMode() {
        this.uiState = new UiState.Builder()
                .copyUiState(uiState)
                .setCurrentState(
                        switch (uiState.getCurrentState()) {
                            case S1 -> UiState.State.S2;
                            case S2 -> UiState.State.S1;
                            default ->
                                    throw new IllegalStateException("Unexpected value: " + uiState.getCurrentState());
                        }
                )
                .build();
        submissionPublisher.submit(this.uiState);

    }

    @Override
    public void setDate(String date) {
        if (uiState.getCurrentState() == UiState.State.S4) {
            LocalDate localDate = LocalDate.parse(date);
            uiState = new UiState.Builder()
                    .copyUiState(uiState)
                    .setDate(new Date(localDate))
                    .build();
        }
    }

    @Override
    public void readyToSet() {
        this.uiState = new UiState.Builder()
                .copyUiState(uiState)
                .setCurrentState(
                        switch (uiState.getCurrentState()) {
                            case S1 -> UiState.State.S3;
                            case S2 -> UiState.State.S4;

                            default ->
                                    throw new IllegalStateException("Unexpected value: " + uiState.getCurrentState());
                        }
                )
                .build();
        submissionPublisher.submit(this.uiState);
    }

    @Override
    public void setDateOrTime(String userInput) {
        switch (uiState.getCurrentState()) {
            case S3 -> setTime(userInput);
            case S4 -> setDate(userInput);
        }
        this.uiState = new UiState.Builder()
                .copyUiState(uiState)
                .setCurrentState(
                        switch (uiState.getCurrentState()) {
                            case S3 -> UiState.State.S1;
                            case S4 -> UiState.State.S2;

                            default ->
                                    throw new IllegalStateException("Unexpected value: " + uiState.getCurrentState());
                        }
                )
                .build();
        submissionPublisher.submit(this.uiState);
    }
}