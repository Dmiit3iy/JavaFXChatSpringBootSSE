package org.dmiit3iy.сontroller;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public interface ControllerData<T> {
    void initData(T value);

    EventHandler<WindowEvent> getCloseEventHandler();
}
