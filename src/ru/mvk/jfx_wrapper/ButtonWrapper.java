/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.jetbrains.annotations.NotNull;

public interface ButtonWrapper {
  void setHandler(@NotNull EventHandler<ActionEvent> handler);

  void setDefault();

  void setCancel();
}
