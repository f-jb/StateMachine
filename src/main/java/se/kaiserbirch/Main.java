package se.kaiserbirch;

import se.kaiserbirch.controller.Controller;
import se.kaiserbirch.model.Model;
import se.kaiserbirch.views.ViewController;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new ViewController(), new Model());
        controller.run();

    }
}