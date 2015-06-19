/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

public class JFXLabelTest extends UITest {
  @NotNull
  private final String caption = "Test Label";
  @NotNull
  private final String id = "test_label";

  @Test
  public void constructor_shouldSetLabelCaption() {
    @NotNull Label label = findById(id);
    Assert.assertEquals("constructor should set correct caption for Label",
                            caption, label.getText());
  }

  @Test
  public void getNode_shouldReturnLabel() {
    Assert.assertTrue("getNode() should return instance of Label",
                         findById(id) instanceof Label);
  }

  @Override
  @NotNull
  protected Parent getRootNode() {
    @NotNull Node node = new JFXLabel(caption).getNode();
    node.setId(id);
    return (Parent) node;
  }
}
