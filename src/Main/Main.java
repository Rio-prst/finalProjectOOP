package Main;

import View.Login.ViewStart;
import Config.Connector;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Connector.Connect();
            
            new ViewStart().setVisible(true);
        });
    }
}