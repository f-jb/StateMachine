package se.kaiserbirch.controller;

import se.kaiserbirch.model.Date;
import se.kaiserbirch.model.Time;

import java.time.LocalDate;
import java.time.LocalTime;


public class UiState {
    public enum State {
        // S1 - Show time
        // S2 - Show date
        // S3 - Change time
        // S4 - Change date

        S1,
        S2,
        S3,
        S4;
    }
    private final Date date;
    private final Time time;
    private final State currentState;
    private UiState(UiStateBuilder uiStateBuilder){
        this.date = uiStateBuilder.date;
        this.time =  uiStateBuilder.time;
        this.currentState = uiStateBuilder.currentState;
    }


    public LocalDate getDate() {
        return date.getDate();
    }

    public LocalTime getTime() {
        return time.getTime();
    }

    public State getCurrentState() {
        return currentState;
    }

    public static class UiStateBuilder {
       private Date date;
       private Time time;
       private State currentState;

        public UiStateBuilder setDate(Date date){
           this.date = date;
           return this;
       }

        public UiStateBuilder setTime(Time time) {
            this.time = time;
            return this;
        }

        public UiStateBuilder setCurrentState(State currentState) {
            this.currentState = currentState;
            return this;
        }
        public UiStateBuilder copyUiState(UiState uiState){
           this.date = new Date(uiState.getDate());
           this.time = new Time(uiState.getTime());
           this.currentState = uiState.getCurrentState();
           return this;
        }
        UiState build(){
           return new UiState(this);
        }
    }

}
