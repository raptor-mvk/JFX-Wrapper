/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import org.jetbrains.annotations.NotNull;

public class JFXButton implements NodeWrapper, ButtonWrapper {
  @NotNull
  private final Button button;

  public JFXButton(@NotNull String caption) {
    button = new Button(caption);
  }

  @Override
  public final void setHandler(@NotNull EventHandler<ActionEvent> handler) {
    button.setOnAction(handler);
  }

  @Override
  public final void setDefault() {
    button.setDefaultButton(true);
  }

  @Override
  public final void setCancel() {
    button.setCancelButton(true);
  }

  @Override
  @NotNull
  public final Node getNode() {
    return button;
  }
}

