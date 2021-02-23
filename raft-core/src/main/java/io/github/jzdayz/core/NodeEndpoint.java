package io.github.jzdayz.core;

import io.github.jzdayz.net.Address;
import lombok.Data;

@Data
public class NodeEndpoint {

  private NodeId nodeId;

  private Address address;

}
