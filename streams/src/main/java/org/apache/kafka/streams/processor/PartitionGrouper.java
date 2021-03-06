/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.kafka.streams.processor;

import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.TopicPartition;

import java.util.Map;
import java.util.Set;

/**
 * A partition grouper that generates partition groups given the list of topic-partitions.
 *
 * This grouper also acts as the stream task creation function along with partition distribution
 * such that each generated partition group is assigned with a distinct {@link TaskId};
 * the created task ids will then be assigned to Kafka Streams instances that host the stream
 * processing application.
 */
public interface PartitionGrouper {

    /**
     * Returns a map of task ids to groups of partitions. A partition group forms a task, thus, partitions that are
     * expected to be processed together must be in the same group. DefaultPartitionGrouper implements this
     * interface. See {@link DefaultPartitionGrouper} for more information.
     *
     * @param topicGroups The map from the {@link TopologyBuilder#topicGroups()} topic group} id to topics
     * @param metadata Metadata of the consuming cluster
     * @return a map of task ids to groups of partitions
     */
    Map<TaskId, Set<TopicPartition>> partitionGroups(Map<Integer, Set<String>> topicGroups, Cluster metadata);

}