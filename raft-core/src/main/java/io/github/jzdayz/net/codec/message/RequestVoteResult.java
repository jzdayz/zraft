package io.github.jzdayz.net.codec.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RequestVoteResult {

  private int term;

  private boolean voteGranted;
}
