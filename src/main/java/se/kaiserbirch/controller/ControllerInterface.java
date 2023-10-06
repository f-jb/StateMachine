package se.kaiserbirch.controller;

public interface ControllerInterface {
    void onSetTime(String time);
    void onSetDate(String date);
    void changeMode();
    void readyToSet();
}
