package io.github.jzdayz.core;

public class MemoryNodeStore implements NodeStore{

  private int term;

  private NodeId votedFor;

  @Override
  public int getTerm() {
    return term;
  }

  @Override
  public void setTerm(int term) {
    this.term = term;
  }

  @Override
  public NodeId getVotedFor() {
    return votedFor;
  }

  @Override
  public void setVotedFor(NodeId nodeId) {
    this.votedFor = nodeId;
  }

  @Override
  public void close() {

  }
}
