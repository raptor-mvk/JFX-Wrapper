/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class JFXTable<Entity> implements TableWrapper<Entity>, NodeWrapper {
  @NotNull
  private final TableView<Entity> tableView;

  public JFXTable() {
    tableView = new TableView<>();
  }

  @NotNull
  @Override
  public final List<TableColumn<Entity, ?>> getColumns() {
    return tableView.getColumns();
  }

  @Override
  public final void setItems(@NotNull List<Entity> items) {
    @NotNull
    ArrayList<Entity> itemsCopy = new ArrayList<>(items);
    @NotNull
    ObservableList<Entity> itemsObservableList =
        FXCollections.observableList(itemsCopy);
    tableView.setItems(itemsObservableList);
  }

  @NotNull
  @Override
  public final ObservableList<Entity> getItems() {
    return tableView.getItems();
  }

  @Nullable
  @Override
  public Entity getSelectedEntity() {
    return tableView.getSelectionModel().getSelectedItem();
  }

  @Override
  public void refresh() {
    @Nullable TableColumn<Entity, ?> column = tableView.getColumns().get(0);
    if (column != null) {
      column.setVisible(false);
      column.setVisible(true);
    }
  }

  @NotNull
  @Override
  public final Node getNode() {
    return tableView;
  }
}

