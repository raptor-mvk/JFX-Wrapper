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

public class JFXTextFieldTest extends UITest {
  @NotNull
  private final String id = "text_field";
  private final int width = 10;
  @NotNull
  private HBox root;
  @NotNull
  private JFXTextField jfxTextField;

  @Test
  public void getNode_shouldReturnTextField() {
    Assert.assertTrue("getNode() should return instance of TextField",
                         findById(id) instanceof TextField);
  }

  @Test
  public void setValue_shouldSetTextFieldText() {
    @NotNull TextField textField = findById(id);
    @NotNull String expectedValue = "Test it";
    jfxTextField.setValue(expectedValue);
    Assert.assertEquals("setValue(s) should set text to s for TextField",
                           expectedValue, textField.getText());
  }

  @Test
  public void getValue_shouldReturnTextFieldText() {
    @NotNull TextField textField = findById(id);
    @NotNull String expectedValue = "Some text";
    textField.setText(expectedValue);
    Assert.assertEquals("getValue() should return text from TextField",
                           expectedValue, jfxTextField.getValue());
  }

  @Test
  public void requestFocus_shouldRequestFocusForTextField() {
    @NotNull TextField textField = findById(id);
    JFXUtils.runAndWait(root::requestFocus);
    JFXUtils.runAndWait(jfxTextField::requestFocus);
    Assert.assertTrue("requestFocus() should set focus on TextField",
                         textField.isFocused());
  }

  @Test
  public void shortInput_shouldPutWholeTextIntoTextField() {
    @NotNull String inputText = "Test";
    clickById(id).type(inputText);
    Assert.assertEquals("short input should put whole text into TextField",
                           inputText, jfxTextField.getValue());
  }

  @Test
  public void longInput_shouldPutTruncatedTextIntoTextField() {
    @NotNull String inputText = "Very long text";
    @NotNull String expectedValue = inputText.substring(0, width);
    clickById(id).type(inputText);
    Assert.assertEquals("long input should put truncated text into TextField",
                           expectedValue, jfxTextField.getValue());
  }

  @Test
  public void shortPasteFromClipboard_shouldPutWholeTextIntoTextField() {
    @NotNull String inputText = "Clipboard";
    putToClipboard(inputText);
    pasteFromClipboardById(id);
    Assert.assertEquals("short paste from the clipboard should put whole " +
                            "text into TextField", inputText,
                           jfxTextField.getValue());
  }

  @Test
  public void longPasteFromClipboard_shouldMakeTextFieldEmpty() {
    @NotNull String inputText = "Boring and pitiful";
    putToClipboard(inputText);
    pasteFromClipboardById(id);
    Assert.assertEquals("long paste from the clipboard should make TextField " +
                            "empty", "", jfxTextField.getValue());
  }

  @Test
  public void inputWhenSelectedText_shouldReplaceSelectedTextInTextField() {
    @NotNull TextField textField = findById(id);
    @NotNull String expectedValue = "A";
    textField.setText("Selected");
    selectAllById(id);
    type(expectedValue);
    Assert.assertEquals("input when text is selected should replace " +
                            "selected text in TextField", expectedValue,
                           jfxTextField.getValue());
  }

  @Test
  public void longPasteWhenSelectedText_shouldNotChangeTextFieldText() {
    @NotNull TextField textField = findById(id);
    @NotNull String expectedValue = "Selected";
    putToClipboard("The longest one");
    textField.setText(expectedValue);
    selectAllById(id);
    pasteFromClipboardById(id);
    Assert.assertEquals("long paste from the clipboard when text is " +
                            "selected should not change text", expectedValue,
                           jfxTextField.getValue());
  }

  @Test
  public void inputIntoBeginningOfFilledField_shouldNotMoveTextFieldCaret() {
    @NotNull TextField textField = findById(id);
    textField.setText("Selections");
    type("B");
    Assert.assertEquals("input into the beginning of filled field should not " +
                            "move caret", 0, textField.getCaretPosition());
  }

  @Test
  public void inputIntoMiddleOfFilledField_shouldNotMoveTextFieldCaret() {
    @NotNull TextField textField = findById(id);
    int caretPosition = 3;
    textField.setText("Directions");
    textField.positionCaret(caretPosition);
    type("M");
    Assert.assertEquals("input into the middle of filled field should not " +
                            "move caret", caretPosition,
                           textField.getCaretPosition());
  }

  @Test
  public void inputIntoEndOfFilledField_shouldNotMoveTextFieldCaret() {
    @NotNull TextField textField = findById(id);
    textField.setText("Projection");
    textField.positionCaret(width);
    type("E");
    Assert.assertEquals("input into the end of filled field should not move " +
                            "caret", width, textField.getCaretPosition());
  }

  @Test
  public void pasteIntoBeginningOfFilledField_shouldNotMoveTextFieldCaret() {
    @NotNull TextField textField = findById(id);
    textField.setText("Hemisphere");
    putToClipboard("Star");
    pasteFromClipboardById(id);
    Assert.assertEquals("paste into the beginning of filled field should not " +
                            "move caret", 0, textField.getCaretPosition());
  }

  @Test
  public void pasteIntoMiddleOfFilledField_shouldNotMoveTextFieldCaret() {
    @NotNull TextField textField = findById(id);
    int caretPosition = 6;
    textField.setText("Atmosphere");
    textField.positionCaret(caretPosition);
    putToClipboard("Entity");
    pasteFromClipboardById(id);
    Assert.assertEquals("paste into the middle of filled field should not " +
                            "move caret", caretPosition,
                           textField.getCaretPosition());
  }

  @Test
  public void pasteIntoEndOfFilledField_shouldNotMoveTextFieldCaret() {
    @NotNull TextField textField = findById(id);
    textField.setText("Protection");
    textField.positionCaret(width);
    putToClipboard("Push");
    pasteFromClipboardById(id);
    Assert.assertEquals("paste into the end of filled field should not move " +
                            "caret", width, textField.getCaretPosition());
  }

  @Test
  public void longPasteIntoFilledSelectedField_shouldNotMoveTextFieldCaret() {
    @NotNull TextField textField = findById(id);
    int caretPosition = 5;
    textField.setText("Destructor");
    textField.selectRange(caretPosition - 2, caretPosition);
    putToClipboard("Testing");
    pasteFromClipboardById(id);
    Assert.assertEquals("long paste into the filled field with selected text " +
                            "should not move caret", caretPosition,
                           textField.getCaretPosition());
  }

  @Override
  @NotNull
  protected Parent getRootNode() {
    root = new HBox();
    jfxTextField = new JFXTextField(width);
    @NotNull Node node = jfxTextField.getNode();
    node.setId(id);
    root.getChildren().add(node);
    return root;
  }
}
