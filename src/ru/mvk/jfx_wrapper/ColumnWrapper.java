/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.scene.control.TableColumn;
import org.jetbrains.annotations.NotNull;

public interface ColumnWrapper<Entity> {
  @NotNull
  TableColumn<Entity,? > getColumn();
}
