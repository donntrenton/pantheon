/*
 * Copyright 2019 ConsenSys AG.
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
package tech.pegasys.pantheon.consensus.common.jsonrpc;

import tech.pegasys.pantheon.consensus.common.VoteProposer;
import tech.pegasys.pantheon.consensus.common.VoteType;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.JsonRpcRequest;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.response.JsonRpcResponse;
import tech.pegasys.pantheon.ethereum.jsonrpc.internal.response.JsonRpcSuccessResponse;

import java.util.Map;
import java.util.stream.Collectors;

public class AbstractVoteProposerMethod {

  private final VoteProposer voteProposer;

  public AbstractVoteProposerMethod(final VoteProposer voteProposer) {
    this.voteProposer = voteProposer;
  }

  public JsonRpcResponse response(final JsonRpcRequest request) {
    final Map<String, Boolean> proposals =
        voteProposer
            .getProposals()
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    proposal -> proposal.getKey().toString(),
                    proposal -> proposal.getValue() == VoteType.ADD));

    return new JsonRpcSuccessResponse(request.getId(), proposals);
  }
}
