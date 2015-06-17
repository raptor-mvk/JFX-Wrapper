/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

abstract class AbstractTextField implements NodeWrapper, FieldWrapper<String> {
  @NotNull
  private final TextField textField;
  private boolean badValue = false;
  private boolean needCaretPos = false;
  private int prevCaretPos = -1;
  private final int width;

  public AbstractTextField(int width) {
    this.width = width;
    textField = new TextField();
    setWidth();
    setKeyHandlers();
    setChangeListener();
  }

  @Override
  @NotNull
  public final Node getNode() {
    return textField;
  }

  @Override
  @NotNull
  public final String getValue() {
    return textField.getText();
  }

  @Override
  public final void setValue(@NotNull String value) {
    textField.setText(value);
  }

  @Override
  public final void requestFocus() {
    textField.requestFocus();
  }

  abstract boolean check(@NotNull String value);

  int getWidth() {
    return width;
  }

  private void setWidth() {
    double fieldWidth = Font.getDefault().getSize() * (0.7 * width + 1.5);
    textField.setMaxWidth(fieldWidth);
    textField.setMinWidth(fieldWidth);
  }

  private void setKeyHandlers() {
    textField.setOnKeyPressed((event) ->
                                  prevCaretPos = textField.getCaretPosition());
    textField.setOnKeyReleased((event) -> {
      if (needCaretPos) {
        textField.positionCaret(prevCaretPos);
        needCaretPos = false;
      }
    });
  }

  private void setChangeListener() {
    textField.textProperty().addListener((obsValue, oldValue, newValue) -> {
      if (badValue) {
        if (needCaretPos) {
          textField.positionCaret(prevCaretPos);
          needCaretPos = false;
        }
        badValue = false;
      } else {
        if (!check(newValue)) {
          badValue = true;
          needCaretPos = true;
          textField.setText(oldValue);
        }
      }
    });
  }
}
