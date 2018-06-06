package holdem.controllers;

import javax.swing.*;

public abstract class Controller {
    private JPanel view;

    public Controller() {
        this.view = new JPanel();
    }

    public JPanel getView() {
        return view;
    }

    abstract void setupLayout(JPanel view);

    public void reloadData() {}
}
