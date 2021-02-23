package io.github.jzdayz.net.codec;

import io.github.jzdayz.net.codec.message.AppendEntries;
import io.github.jzdayz.net.codec.message.AppendEntriesResult;
import io.github.jzdayz.net.codec.message.RequestVote;
import io.github.jzdayz.net.codec.message.RequestVoteResult;
import io.github.jzdayz.util.JsonUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

@ChannelHandler.Sharable
public class Decode extends LengthFieldBasedFrameDecoder {

  private static final int _10M = 10 * 1024 * 1024;

  public Decode() {
    super(_10M, 0, 4, 0, 0);
  }

  @Override
  protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
    ByteBuf frame = (ByteBuf) super.decode(ctx, in);
    if (frame == null) {
      return null;
    }
    int length = frame.readInt();
    byte magic = frame.readByte();
    byte[] msg = new byte[length];
    frame.readBytes(msg);
    frame.release();
    switch (magic) {
      case Magic.SendRequestVote: {
        return JsonUtil.toObj(msg, RequestVote.class);
      }
      case Magic.ReceiveRequestVote: {
        return JsonUtil.toObj(msg, RequestVoteResult.class);
      }
      case Magic.SendAppendEntries: {
        return JsonUtil.toObj(msg, AppendEntries.class);
      }
      case Magic.ReceiveAppendEntries: {
        return JsonUtil.toObj(msg, AppendEntriesResult.class);
      }
      default: {
        return null;
      }
    }
  }
}
