package controllers;

import javax.swing.*;

public abstract class Controller<V extends JComponent> {
    private V view;

    public void setView(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }
}
