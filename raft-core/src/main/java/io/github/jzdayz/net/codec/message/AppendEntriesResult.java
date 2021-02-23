package io.github.jzdayz.net.codec.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AppendEntriesResult {

  private int term;

  private boolean success;
}
