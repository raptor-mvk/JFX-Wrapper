/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

public class JFXTextFieldTest extends UITest {
  @NotNull
  private final String id = "text_field";
  private final int width = 10;
  @NotNull
  private JFXTextField jfxTextField;

  @Test
  public void getNode_returnsLabel() {
    Assert.assertTrue("getNode() should return instance of TextField",
                         findById(id) instanceof TextField);
  }

  @Override
  @NotNull
  protected Parent getRootNode() {
    jfxTextField = new JFXTextField(width);
    @NotNull Node node = jfxTextField.getNode();
    node.setId(id);
    return (Parent) jfxTextField.getNode();
  }
}
