package se.kaiserbirch;

import se.kaiserbirch.controller.Controller;
import se.kaiserbirch.views.ViewController;

public class Main {
    public static void main(String[] args) {
        ViewController viewController = new ViewController();
        Controller controller = new Controller();
        viewController.mainView(controller);


    }
}