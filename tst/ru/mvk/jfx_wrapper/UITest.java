/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.scene.Node;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loadui.testfx.GuiTest;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

abstract class UITest extends GuiTest {
  @NotNull
  final <NodeType extends Node> NodeType findById(@NotNull String id) {
    @Nullable NodeType result = find('#' + id);
    if (result == null) {
      throw new AssertionError("Node #" + id + " was not found");
    }
    return result;
  }

  @NotNull
  final UITest clickById(@NotNull String id) {
    return (UITest) click('#' + id);
  }

  @NotNull
  final UITest rightClickById(@NotNull String id) {
    return (UITest) rightClick('#' + id);
  }

  @NotNull
  final UITest selectAllById(@NotNull String id) {
    return (UITest) rightClickById(id).click("Select All");
  }

  final void putToClipboard(@NotNull String text) {
    @NotNull StringSelection stringSelection = new StringSelection(text);
    @NotNull Clipboard clipboard =
        Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(stringSelection, null);
  }

  @NotNull
  final UITest pasteFromClipboardById(@NotNull String id) {
    return (UITest) rightClickById(id).click("Paste");
  }
}
