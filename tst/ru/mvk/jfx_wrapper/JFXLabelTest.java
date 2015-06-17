/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

public class JFXLabelTest extends GuiTest {
  @NotNull
  private final String caption = "Test Label";

  @Test
  public void constructor_setsCorrectCaption() {
    Assert.assertNotNull("constructor should set correct caption",
                            find(caption));
  }

  @Test
  public void getNode_returnsLabel() {
    Assert.assertTrue("getNode() should return instance of Label",
                         find(caption) instanceof Label);
  }

  @Override
  @NotNull
  protected Parent getRootNode() {
    return (Parent) new JFXLabel(caption).getNode();
  }
}
