/*
 * Copyright 2018 ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package tech.pegasys.pantheon.ethereum.jsonrpc.internal.methods;

import tech.pegasys.pantheon.ethereum.core.Hash;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.JsonRpcRequest;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.parameters.JsonRpcParameter;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.parameters.UnsignedIntParameter;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.queries.BlockchainQueries;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.response.JsonRpcResponse;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.response.JsonRpcSuccessResponse;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.results.BlockResult;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.results.UncleBlockResult;

public class EthGetUncleByBlockHashAndIndex implements JsonRpcMethod {

  private final BlockchainQueries blockchain;
  private final JsonRpcParameter parameters;

  public EthGetUncleByBlockHashAndIndex(
      final BlockchainQueries blockchain, final JsonRpcParameter parameters) {
    this.blockchain = blockchain;
    this.parameters = parameters;
  }

  @Override
  public String getName() {
    return "eth_getUncleByBlockHashAndIndex";
  }

  @Override
  public JsonRpcResponse response(final JsonRpcRequest request) {
    return new JsonRpcSuccessResponse(request.getId(), blockResult(request));
  }

  private BlockResult blockResult(final JsonRpcRequest request) {
    final Hash hash = parameters.required(request.getParams(), 0, Hash.class);
    final int index =
        parameters.required(request.getParams(), 1, UnsignedIntParameter.class).getValue();

    return blockchain.getOmmer(hash, index).map(UncleBlockResult::build).orElse(null);
  }
}
