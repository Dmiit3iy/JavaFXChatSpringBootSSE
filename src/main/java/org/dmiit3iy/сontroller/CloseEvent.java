package org.dmiit3iy.сontroller;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public interface CloseEvent {
   EventHandler<WindowEvent> getCloseEventHandler();

}
