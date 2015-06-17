/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

public class JFXNumFieldTest extends UITest {
  @NotNull
  private final String id = "num_field";
  private final int width = 5;
  @NotNull
  private HBox root;
  @NotNull
  private JFXNumField jfxNumField;

  @Test
  public void getNode_returnsTextField() {
    Assert.assertTrue("getNode() should return instance of TextField",
                         findById(id) instanceof TextField);
  }

  @Test
  public void setValue_setsCorrectValue() {
    @NotNull TextField textField = findById(id);
    @NotNull String expectedValue = "1234";
    jfxNumField.setValue(expectedValue);
    Assert.assertEquals("setValue(s) should set text to s for underlying " +
                            "TextField", expectedValue, textField.getText());
  }

  @Test
  public void getValue_returnsCorrectValue() {
    @NotNull TextField textField = findById(id);
    @NotNull String expectedValue = "4575";
    textField.setText(expectedValue);
    Assert.assertEquals("getValue() should return text from underlying " +
                            "TextField", expectedValue,
                           jfxNumField.getValue());
  }

  @Test
  public void requestFocus_setsFocus() {
    @NotNull TextField textField = findById(id);
    JFXUtils.runAndWait(root::requestFocus);
    JFXUtils.runAndWait(jfxNumField::requestFocus);
    Assert.assertTrue("requestFocus() should set focus on underlying " +
                          "TextField", textField.isFocused());
  }

  @Test
  public void shortNumericInput_setsWholeText() {
    @NotNull String inputText = "254";
    clickById(id).type(inputText);
    Assert.assertEquals("short numeric input should set value to whole text",
                           inputText, jfxNumField.getValue());
  }

  @Test
  public void shortMixedInput_setsFilteredText() {
    @NotNull String inputText = "photo2many5fill4";
    @NotNull String expectedValue = inputText.replaceAll("\\D", "");
    clickById(id).type(inputText);
    Assert.assertEquals("short mixed input should set value to filtered text",
                           expectedValue, jfxNumField.getValue());
  }

  @Test
  public void longNumericInput_setsTruncatedText() {
    @NotNull String inputText = "3473237327";
    @NotNull String expectedValue = inputText.substring(0, width);
    clickById(id).type(inputText);
    Assert.assertEquals("long numeric input should set value to truncated text",
                           expectedValue, jfxNumField.getValue());
  }

  @Test
  public void longMixedInput_setsFilteredAndTruncatedText() {
    @NotNull String inputText = "zeal23factory46triple346";
    @NotNull String expectedValue =
        inputText.replaceAll("\\D", "").substring(0, width);
    clickById(id).type(inputText);
    Assert.assertEquals("long mixed input should set value to filtered and " +
                            "truncated text", expectedValue,
                           jfxNumField.getValue());
  }

  @Test
  public void shortNumericPasteFromClipboard_setsWholeText() {
    @NotNull String inputText = "3463";
    putToClipboard(inputText);
    pasteFromClipboardById(id);
    Assert.assertEquals("short numeric paste from the clipboard should set " +
                            "value to whole text", inputText,
                           jfxNumField.getValue());
  }

  @Test
  public void longNumericPasteFromClipboard_setsEmptyText() {
    @NotNull String inputText = "Boring and pitiful";
    putToClipboard(inputText);
    pasteFromClipboardById(id);
    Assert.assertEquals("long numeric paste from the clipboard should set " +
                            "empty value", "", jfxNumField.getValue());
  }

  @Test
  public void inputWhenSelectedText_replacesSelectedText() {
    @NotNull TextField textField = findById(id);
    @NotNull String expectedValue = "7";
    textField.setText("3456");
    selectAllById(id);
    type(expectedValue);
    Assert.assertEquals("input when text is selected should replace " +
                            "selected text", expectedValue,
                           jfxNumField.getValue());
  }

  @Test
  public void longPasteWhenSelectedText_doesNotChangeText() {
    @NotNull TextField textField = findById(id);
    @NotNull String expectedValue = "2346";
    putToClipboard("568468");
    textField.setText(expectedValue);
    selectAllById(id);
    pasteFromClipboardById(id);
    Assert.assertEquals("long paste from the clipboard when text is " +
                            "selected should not change text", expectedValue,
                           jfxNumField.getValue());
  }

  @Test
  public void inputIntoBeginningOfFilledField_doesNotMoveCaret() {
    @NotNull TextField textField = findById(id);
    textField.setText("34564");
    type("2");
    Assert.assertEquals("input into the beginning of filled field should not " +
                            "move caret", 0, textField.getCaretPosition());
  }

  @Test
  public void inputIntoMiddleOfFilledField_doesNotMoveCaret() {
    @NotNull TextField textField = findById(id);
    int caretPosition = 3;
    textField.setText("54845");
    textField.positionCaret(caretPosition);
    type("2");
    Assert.assertEquals("input into the middle of filled field should not " +
                            "move caret", caretPosition,
                           textField.getCaretPosition());
  }

  @Test
  public void inputIntoEndOfFilledField_doesNotMoveCaret() {
    @NotNull TextField textField = findById(id);
    textField.setText("56895");
    textField.positionCaret(width);
    type("1");
    Assert.assertEquals("input into the end of filled field should not move " +
                            "caret", width, textField.getCaretPosition());
  }

  @Test
  public void pasteIntoBeginningOfFilledField_doesNotMoveCaret() {
    @NotNull TextField textField = findById(id);
    textField.setText("45678");
    putToClipboard("3456");
    pasteFromClipboardById(id);
    Assert.assertEquals("paste into the beginning of filled field should not " +
                            "move caret", 0, textField.getCaretPosition());
  }

  @Test
  public void pasteIntoMiddleOfFilledField_doesNotMoveCaret() {
    @NotNull TextField textField = findById(id);
    int caretPosition = 2;
    textField.setText("65866");
    textField.positionCaret(caretPosition);
    putToClipboard("9078");
    pasteFromClipboardById(id);
    Assert.assertEquals("paste into the middle of filled field should not " +
                            "move caret", caretPosition,
                           textField.getCaretPosition());
  }

  @Test
  public void pasteIntoEndOfFilledField_doesNotMoveCaret() {
    @NotNull TextField textField = findById(id);
    textField.setText("56745");
    textField.positionCaret(width);
    putToClipboard("546");
    pasteFromClipboardById(id);
    Assert.assertEquals("paste into the end of filled field should not move " +
                            "caret", width, textField.getCaretPosition());
  }

  @Test
  public void longPasteIntoFilledFieldWithSelection_doesNotMoveCaret() {
    @NotNull TextField textField = findById(id);
    int caretPosition = 4;
    textField.setText("95672");
    textField.selectRange(caretPosition - 3, caretPosition);
    putToClipboard("34555");
    pasteFromClipboardById(id);
    Assert.assertEquals("long paste into the filled field with selected text " +
                            "should not move caret", caretPosition,
                           textField.getCaretPosition());
  }

  @Override
  @NotNull
  protected Parent getRootNode() {
    root = new HBox();
    jfxNumField = new JFXNumField(width);
    @NotNull Node node = jfxNumField.getNode();
    node.setId(id);
    root.getChildren().add(node);
    return root;
  }
}
