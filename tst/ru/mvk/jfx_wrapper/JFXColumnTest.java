/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

public class JFXColumnTest extends UITest {
  @NotNull
  private final String caption = "ID";
  @NotNull
  private JFXColumn<TestEntity> jfxColumn;
  @NotNull
  private final Callback<TableColumn.CellDataFeatures<TestEntity, Object>,
                            ObservableValue<Object>> cellValueFactory =
      new PropertyValueFactory<>("id");

  @Test
  public void constructor_shouldSetTableColumnCaption() {
    Assert.assertEquals("constructor should set correct text for TableColumn",
                           caption, jfxColumn.getColumn().getText());
  }

  @Test
  public void constructor_shouldSetTableColumnCellValueFactory() {
    Assert.assertEquals("constructor should set correct cellValueFactory for " +
                            "TableColumn", cellValueFactory,
                           jfxColumn.getColumn().getCellValueFactory());
  }

  @Test
  public void constructor_shouldMakeTableColumnSortable() {
    Assert.assertTrue("constructor should make TableColumn sortable",
                         jfxColumn.getColumn().isSortable());
  }

  @Override
  protected Parent getRootNode() {
    jfxColumn = new JFXColumn<>(caption, cellValueFactory);
    return new HBox();
  }
}
