/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.scene.Node;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loadui.testfx.GuiTest;

abstract class UITest extends GuiTest {
  @NotNull
  final <NodeType extends Node> NodeType findById(@NotNull String id) {
    @Nullable NodeType result = find ('#' + id);
    if (result == null) {
      throw new AssertionError("Node #" + id + " was not found");
    }
    return result;
  }

  @NotNull
  final UITest clickById(@NotNull String id) {
    return (UITest) click('#' + id);
  }
}
