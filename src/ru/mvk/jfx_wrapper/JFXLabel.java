/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.scene.Node;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;

public class JFXLabel implements NodeWrapper {
  @NotNull
  private final Label label;

  public JFXLabel(@NotNull String caption) {
    label = new Label(caption);
  }

  @Override
  @NotNull
  public final Node getNode() {
    return label;
  }
}
