/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class JFXTableTest extends UITest {
  @NotNull
  private JFXTable<TestEntity> jfxTable;
  @NotNull
  private final String id = "test_table";
  @NotNull
  private final ArrayList<TableColumn<TestEntity, Object>> tableColumnList =
      new ArrayList<>();
  @NotNull
  private final ArrayList<TestEntity> entityList = new ArrayList<>();

  @Test
  public void getNode_shouldReturnTableView() {
    Assert.assertTrue("getNode() should return instance of TableView",
                         findById(id) instanceof TableView);
  }

  @Test
  public void getColumns_shouldReturnTableViewColumns() {
    @NotNull TableView<TestEntity> tableView = findById(id);
    JFXUtils.runAndWait(() -> tableView.getColumns().addAll(tableColumnList));
    Assert.assertEquals("getColumns() should return columns from underlying " +
                            "TableView", tableColumnList,
                           jfxTable.getColumns());
  }

  @Test
  public void setItems_shouldSetTableViewItems() {
    @NotNull TableView<TestEntity> tableView = findById(id);
    JFXUtils.runAndWait(() -> jfxTable.setItems(entityList));
    Assert.assertEquals("setItems() should set items for underlying TableView",
                           entityList, tableView.getItems());
  }

  @Test
  public void getItems_shouldReturnTableViewItems() {
    @NotNull TableView<TestEntity> tableView = findById(id);
    @NotNull ObservableList<TestEntity> entityObservableList =
        FXCollections.observableList(entityList);
    JFXUtils.runAndWait(() -> tableView.setItems(entityObservableList));
    Assert.assertEquals("getItems() should get items from underlying TableView",
                           entityList, jfxTable.getItems());
  }

  @Test
  public void getSelectedEntity_shouldReturnTableViewSelectedEntity() {
    @NotNull TableView<TestEntity> tableView = findById(id);
    @NotNull ObservableList<TestEntity> entityObservableList =
        FXCollections.observableList(entityList);
    int rowToSelect = 1;
    JFXUtils.runAndWait(() -> {
      tableView.setItems(entityObservableList);
      tableView.getSelectionModel().select(1);
    });
    Assert.assertEquals("getSelectedEntity() should return selected entity " +
                            "from underlying TableView",
                           entityList.get(rowToSelect),
                           jfxTable.getSelectedEntity());
  }

  @Override
  @NotNull
  protected Parent getRootNode() {
    jfxTable = new JFXTable<>();
    @NotNull Node node = jfxTable.getNode();
    node.setId(id);
    prepareTableColumnList();
    prepareEntityList();
    return (Parent) node;
  }

  private void prepareTableColumnList() {
    @NotNull TableColumn<TestEntity, Object> tableColumn =
        new TableColumn<>("id");
    tableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    tableColumnList.add(tableColumn);
    tableColumn = new TableColumn<>("name");
    tableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    tableColumnList.add(tableColumn);
  }

  private void prepareEntityList() {
    @NotNull TestEntity entity = new TestEntity(1, "Zealot");
    entityList.add(entity);
    entity = new TestEntity(2, "Bishop");
    entityList.add(entity);
  }

  private class TestEntity {
    private final int id;
    @NotNull
    private final String name;

    public TestEntity(int id, @NotNull String name) {
      this.id = id;
      this.name = name;
    }

    public int getId() {
      return id;
    }

    @NotNull
    public String getName() {
      return name;
    }

    @Override
    public boolean equals(Object o) {
      boolean result = false;
      if (this == o) {
        result = true;
      } else {
        if (o != null && getClass() == o.getClass()) {
          @NotNull TestEntity that = (TestEntity) o;
          result = id == that.id && name.equals(that.name);
        }
      }
      return result;
    }

    @Override
    public int hashCode() {
      int result = id;
      result = 31 * result + name.hashCode();
      return result;
    }
  }
}
