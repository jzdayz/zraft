package io.github.jzdayz.net.codec;

import io.github.jzdayz.net.codec.message.AppendEntries;
import io.github.jzdayz.net.codec.message.AppendEntriesResult;
import io.github.jzdayz.net.codec.message.RequestVote;
import io.github.jzdayz.net.codec.message.RequestVoteResult;
import io.github.jzdayz.util.JsonUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@Sharable
public class Encode extends MessageToByteEncoder<Object> {

  @Override
  protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
    byte magic;
    if (msg instanceof RequestVote) {
      magic = Magic.SendRequestVote;
    } else if (msg instanceof RequestVoteResult) {
      magic = Magic.ReceiveRequestVote;
    } else if (msg instanceof AppendEntries) {
      magic = Magic.SendAppendEntries;
    } else if (msg instanceof AppendEntriesResult) {
      magic = Magic.ReceiveAppendEntries;
    } else {
      throw new RuntimeException("not support");
    }
    byte[] msgBytes = JsonUtil.toBytes(msg);
    // 4字节长度+1字节魔数+报文
    // 报文长度
    out.writeInt(msgBytes.length + 1);
    // magic
    out.writeByte(magic);
    // 报文
    out.writeBytes(msgBytes);
  }
}
