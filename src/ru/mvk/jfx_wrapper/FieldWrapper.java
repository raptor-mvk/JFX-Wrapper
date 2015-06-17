/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import org.jetbrains.annotations.NotNull;

public interface FieldWrapper<Type> {
  @NotNull
  Type getValue();

  void setValue(@NotNull Type value);

  void requestFocus();
}