/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

public class JFXButtonTest extends GuiTest {
  @NotNull
  private final String caption = "Test Button";
  @NotNull
  private JFXButton jfxButton;
  private boolean buttonState;
  @NotNull
  private final EventHandler<ActionEvent> buttonHandler =
      (event) -> buttonState = !buttonState;

  @Test
  public void constructor_setsCorrectCaption() {
    Assert.assertNotNull("Constructor should set correct caption",
                            find(caption));
  }

  @Test
  public void getNode_returnsButton() {
    Assert.assertTrue("getNode() should return instance of Button",
                         find(caption) instanceof Button);
  }

  @Test
  public void setDefault_setsUnderlyingButtonAsDefault() {
    @NotNull Button button = find(caption);
    jfxButton.setDefault();
    Assert.assertTrue("setDefault() should set underlying Button as default " +
                          "button", button.isDefaultButton());
  }

  @Test
  public void setCancel_setsUnderlyingButtonAsCancel() {
    @NotNull Button button = find(caption);
    jfxButton.setCancel();
    Assert.assertTrue("setCancel() should set underlying Button as cancel " +
                          "button", button.isCancelButton());
  }

  @Test
  public void setHandler_setsUnderlyingButtonHandler() {
    buttonState = false;
    jfxButton.setHandler(buttonHandler);
    click(caption);
    Assert.assertTrue("setHandler() should set underlying Button handler",
                         buttonState);
  }

  @Override
  @NotNull
  protected Parent getRootNode() {
    jfxButton = new JFXButton(caption);
    return (Parent) jfxButton.getNode();
  }
}
