/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface TableWrapper<Entity> {
  @NotNull
  List<TableColumn<Entity, ?>> getColumns();

  void setItems(@NotNull List<Entity> items);

  @NotNull
  ObservableList<Entity> getItems();

  @Nullable
  Entity getSelectedEntity();

  void refresh();
}
