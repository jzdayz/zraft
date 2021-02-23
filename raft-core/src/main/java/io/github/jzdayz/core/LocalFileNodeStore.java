package io.github.jzdayz.core;

import io.github.jzdayz.ex.Trys;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class LocalFileNodeStore implements NodeStore {

  private File file;

  private RandomAccessFile randomAccessFile;

  private int term;

  private NodeId votedFor;

  private final static int _VOTED_OFFSET = 4;

  private final static int _TERM_OFFSET = 0;

  public LocalFileNodeStore(String filePath) {
    this.file = new File(filePath);
    Trys.doWOE(() -> {
      if (!this.file.exists() && this.file.createNewFile()) {
      }
      randomAccessFile = new RandomAccessFile(file, "rw");
      if (randomAccessFile.length() == 0) {
        randomAccessFile.writeInt(0);// term
        randomAccessFile.writeInt(0);// votedFor length
      }
      randomAccessFile.seek(0);
      term = randomAccessFile.readInt();
      int length = randomAccessFile.readInt();
      if (length == 0) {
        return;
      }
      byte[] votedForBytes = new byte[length];
      randomAccessFile.read(votedForBytes);
      votedFor = new NodeId(new String(votedForBytes, StandardCharsets.UTF_8));
    });

  }

  @Override
  public int getTerm() {
    return term;
  }

  @Override
  public void setTerm(int term) {
    Trys.doWOE(() -> {
      randomAccessFile.seek(_TERM_OFFSET);
      randomAccessFile.writeInt(term);
      this.term = term;
    });
  }

  @Override
  public NodeId getVotedFor() {
    return votedFor;
  }

  @Override
  public void setVotedFor(NodeId nodeId) {
    Trys.doWOE(() -> {
      randomAccessFile.seek(_VOTED_OFFSET);
      randomAccessFile.write(nodeId.getId().getBytes(StandardCharsets.UTF_8));
      this.votedFor = nodeId;
    });
  }

  @Override
  public void close() {
    Trys.doWOE(randomAccessFile::close);
  }
}
