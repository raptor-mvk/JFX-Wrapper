/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.jfx_wrapper;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JFXNumField extends AbstractTextField {
  @NotNull
  private final Matcher matcher;

  public JFXNumField(int width) {
    super(width);
    matcher = Pattern.compile("(\\d){0," + width + "}").matcher("");
  }

  @Override
  boolean check(@NotNull String value) {
    return matcher.reset(value).matches();
  }
}
