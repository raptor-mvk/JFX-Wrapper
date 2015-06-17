/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.scene.Node;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loadui.testfx.GuiTest;

abstract class UITest extends GuiTest {
  @Nullable
  final <NodeType extends Node> NodeType findById(@NotNull String id) {
    return find('#' + id);
  }
}
