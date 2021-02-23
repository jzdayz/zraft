package io.github.jzdayz.net;

import io.github.jzdayz.core.NodeEndpoint;
import io.github.jzdayz.net.codec.message.AppendEntries;
import io.github.jzdayz.net.codec.message.AppendEntriesResult;
import io.github.jzdayz.net.codec.message.RequestVote;
import io.github.jzdayz.net.codec.message.RequestVoteResult;
import java.io.Closeable;
import java.util.Collection;

public interface Connector extends Closeable {

  void initialize();

  void sendRequestVote(RequestVote rpc, Collection<NodeEndpoint> nodeEndpoints);

  void replyRequestVote(RequestVoteResult rpc, NodeEndpoint nodeEndpoint);

  void sendAppendEntries(AppendEntries rpc, Collection<NodeEndpoint> nodeEndpoints);

  void replyAppendEntries(AppendEntriesResult rpc, NodeEndpoint nodeEndpoint);

}
