/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

public class JFXButtonTest extends UITest {
  @NotNull
  private final String caption = "Test Button";
  @NotNull
  private final String id = "test_button";
  @NotNull
  private JFXButton jfxButton;
  private boolean buttonState;
  @NotNull
  private final EventHandler<ActionEvent> buttonHandler =
      (event) -> buttonState = !buttonState;

  @Test
  public void getNode_shouldReturnButton() {
    Assert.assertTrue("getNode() should return instance of Button",
                         findById(id) instanceof Button);
  }

  @Test
  public void constructor_shouldSetButtonCaption() {
    @NotNull Button button = findById(id);
    Assert.assertEquals("constructor should set correct caption for Label",
                           caption, button.getText());
  }

  @Test
  public void setDefault_shouldSetButtonAsDefault() {
    @NotNull Button button = findById(id);
    jfxButton.setDefault();
    Assert.assertTrue("setDefault() should set Button as default button",
                         button.isDefaultButton());
  }

  @Test
  public void setCancel_shouldSetButtonAsCancel() {
    @NotNull Button button = findById(id);
    jfxButton.setCancel();
    Assert.assertTrue("setCancel() should set Button as cancel button",
                         button.isCancelButton());
  }

  @Test
  public void setHandler_shouldSetButtonActionHandler() {
    buttonState = false;
    jfxButton.setHandler(buttonHandler);
    clickById(id);
    Assert.assertTrue("setHandler() should set Button handler", buttonState);
  }

  @Override
  @NotNull
  protected Parent getRootNode() {
    jfxButton = new JFXButton(caption);
    @NotNull Node node = jfxButton.getNode();
    node.setId(id);
    return (Parent) node;
  }
}
