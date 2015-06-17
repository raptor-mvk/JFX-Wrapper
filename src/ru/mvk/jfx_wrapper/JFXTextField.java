/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import org.jetbrains.annotations.NotNull;

public class JFXTextField extends AbstractTextField {
  public JFXTextField(int width) {
    super(width);
  }

  @Override
  boolean check(@NotNull String value) {
    return value.length() <= getWidth();
  }
}
