package io.github.jzdayz.core;

public interface NodeStore {

  int getTerm();

  void setTerm(int term);

  NodeId getVotedFor();

  void setVotedFor(NodeId nodeId);

  void close();

}
