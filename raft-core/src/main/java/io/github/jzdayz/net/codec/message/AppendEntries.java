package io.github.jzdayz.net.codec.message;

import io.github.jzdayz.core.Entry;
import io.github.jzdayz.core.NodeId;
import java.util.Collections;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppendEntries {

  private int term;

  private NodeId nodeId;

  private int prevLogIndex = 0;

  private int prevLogTerm = 0;

  private List<Entry> entries = Collections.emptyList();

  private int leaderCommit;
}
