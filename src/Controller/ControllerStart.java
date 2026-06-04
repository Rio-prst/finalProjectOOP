package Controller;

import View.Login.ViewStart;
import View.Login.ViewLogin;
import View.Login.ViewRegister;

public class ControllerStart {

    ViewStart view;

    public ControllerStart(ViewStart view) {
        this.view = view;
    }

    public void openLogin() {
        new ViewLogin().setVisible(true);
        view.dispose();
    }

    public void openRegister() {
        new ViewRegister().setVisible(true);
        view.dispose();
    }
}