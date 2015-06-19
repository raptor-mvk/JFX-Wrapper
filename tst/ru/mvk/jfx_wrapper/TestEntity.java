/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import org.jetbrains.annotations.NotNull;

class TestEntity {
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
