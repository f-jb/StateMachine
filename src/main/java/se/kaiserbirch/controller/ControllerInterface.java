package se.kaiserbirch.controller;

public interface ControllerInterface {
    void setTime(String time);

    void setDate(String date);
    UiState getUiState();

    void changeMode();

    void readyToSet();

    void setDateOrTime(String userInput);
}
