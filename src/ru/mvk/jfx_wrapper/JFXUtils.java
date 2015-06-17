/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import javafx.application.Platform;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CountDownLatch;

public class JFXUtils {
  public static void runAndWait(@NotNull Runnable action) {
    if (Platform.isFxApplicationThread()) {
      action.run();
    } else {
      @NotNull CountDownLatch doneLatch = new CountDownLatch(1);
      Platform.runLater(() -> {
        try {
          action.run();
        } finally {
          doneLatch.countDown();
        }
      });
      try {
        doneLatch.await();
      } catch (InterruptedException ignored) {
      }
    }
  }
}
