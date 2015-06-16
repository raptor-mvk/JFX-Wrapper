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
  private static final String CAPTION = "Test Label";

  @Test
  public void jfxLabel_getNode_returnsCorrectCaptionNode() {
    Assert.assertNotNull(find(CAPTION));
  }

  @Test
  public void jfxLabel_getNode_ReturnsLabel() {
    Assert.assertTrue(find(CAPTION) instanceof Label);
  }

  @Override
  @NotNull
  protected Parent getRootNode() {
    return (Parent)new JFXLabel(CAPTION).getNode();
  }
}
