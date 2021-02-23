package io.github.jzdayz.net.codec;

public abstract class Magic {

  public static final byte UNKNOWN = 0;

  public static final byte SendRequestVote = 1;

  public static final byte ReceiveRequestVote = 2;

  public static final byte SendAppendEntries = 3;

  public static final byte ReceiveAppendEntries = 4;

}
