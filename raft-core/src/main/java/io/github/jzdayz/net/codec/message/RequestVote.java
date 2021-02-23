package io.github.jzdayz.net.codec.message;

import io.github.jzdayz.core.NodeId;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestVote {

  private int term;

  private NodeId nodeId;

  private int lastLogIndex = 0;

  private int lastLogTerm = 0;
}
