/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JFXColumn<Entity> implements ColumnWrapper<Entity> {
  @NotNull
  private final TableColumn<Entity, Object> column;

  public JFXColumn(@NotNull String caption,
                   @NotNull
                   Callback<TableColumn.CellDataFeatures<Entity, Object>,
                               ObservableValue<Object>> cellValueFactory) {
    column = new TableColumn<>(caption);
    column.setCellValueFactory(cellValueFactory);
    column.setCellFactory(param -> new TableCell<Entity, Object>() {
      @Override
      public void updateItem(@Nullable Object item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
          setText(null);
        } else {
          setText(item.toString());
        }
      }
    });
    column.setSortable(true);
  }

  @NotNull
  @Override
  public TableColumn<Entity, ?> getColumn() {
    return column;
  }
}

