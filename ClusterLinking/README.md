# Cluster Linking

We follow the approach [Share Data Across Clusters, Regions, and Clouds](https://docs.confluent.io/cloud/current/multi-cloud/cluster-linking/topic-data-sharing.html)
to transfer data from one CC cluster to another. Moreover, we use the UI to create the 
link as described [here](https://docs.confluent.io/cloud/current/multi-cloud/cluster-linking/quickstart.html#teardown).

Note that the destination cluster needs to be a dedicated one.

![](Overview.png)

## Create Cluster Link (UI)

We link:
* All topics
* All consumer offsets
* All ACLs

![](ClusterLink.png)

## Verification

Login to Confluent CLI and use the destination cluster.

### Cluster Link Config
```shell
confluent kafka link describe patrick-cl-test

                 Config Name                 |                                                               Config Value                                                               | Read Only | Sensitive |           Source            | Synonyms
----------------------------------------------+------------------------------------------------------------------------------------------------------------------------------------------+-----------+-----------+-----------------------------+-----------
  dest.cluster.id                             | lkc-abcde                                                                                                                                | true      | true      |                             | []
  acl.filters                                 | {"aclFilters":[{"resourceFilter":{"resourceType":"any","patternType":"any"},"accessFilter":{"operation":"any","permissionType":"any"}}]} | false     | false     | DYNAMIC_CLUSTER_LINK_CONFIG | []
  acl.sync.enable                             | true                                                                                                                                     | false     | false     | DYNAMIC_CLUSTER_LINK_CONFIG | []
  acl.sync.ms                                 |                                                                                                                                     5000 | false     | false     | DYNAMIC_CLUSTER_LINK_CONFIG | []
  auto.create.mirror.topics.enable            | true                                                                                                                                     | false     | false     | DYNAMIC_CLUSTER_LINK_CONFIG | []
  auto.create.mirror.topics.filters           | {"topicFilters":[{"name":"*","patternType":"LITERAL","filterType":"INCLUDE"}]}                                                           | false     | false     | DYNAMIC_CLUSTER_LINK_CONFIG | []
  bootstrap.servers                           | pkc-abcde.eu-central-1.aws.confluent.cloud:9092                                                                                          | false     | false     | DYNAMIC_CLUSTER_LINK_CONFIG | []
  client.dns.lookup                           | use_all_dns_ips                                                                                                                          | false     | false     | DYNAMIC_CLUSTER_LINK_CONFIG | []
  cluster.link.prefix                         |                                                                                                                                          | false     | false     | DYNAMIC_CLUSTER_LINK_CONFIG | []
  cluster.link.retry.timeout.ms               |                                                                                                                                   300000 | false     | false     | DYNAMIC_CLUSTER_LINK_CONFIG | []
  connection.mode                             |                                                                                                                                          | false     | false     | DYNAMIC_CLUSTER_LINK_CONFIG | []
  connections.max.idle.ms                     |                                                                                                                                   600000 | false     | false     | DYNAMIC_CLUSTER_LINK_CONFIG | []
  consumer.group.prefix.enable                | false                                                                                                                                    | false     | false     | DYNAMIC_CLUSTER_LINK_CONFIG | []
  consumer.offset.group.filters               | {"groupFilters":[{"name":"*","patternType":"LITERAL","filterType":"INCLUDE"}]}                                                           | false     | false     | DYNAMIC_CLUSTER_LINK_CONFIG | []
  consumer.offset.sync.enable                 | true                                                                                                                                     | false     | false     | DYNAMIC_CLUSTER_LINK_CONFIG | []
  consumer.offset.sync.ms                     |                                                                                                                                    30000 | false     | false     | DYNAMIC_CLUSTER_LINK_CONFIG | []
```

### Mirror topics
```shell
confluent kafka mirror list
     Link Name    |                       Mirror Topic Name                        | Num Partition | Max Per Partition Mirror Lag |                       Source Topic Name                        | Mirror Status | Status Time Ms
------------------+----------------------------------------------------------------+---------------+------------------------------+----------------------------------------------------------------+---------------+-----------------
  patrick-cl-test | rest-api                                                       |             2 |                            0 | rest-api                                                       | ACTIVE        |  1663148145470
  patrick-cl-test | streamsId-KSTREAM-AGGREGATE-STATE-STORE-0000000004-changelog   |             6 |                            0 | streamsId-KSTREAM-AGGREGATE-STATE-STORE-0000000004-changelog   | ACTIVE        |  1663148145559
  patrick-cl-test | streamsId-KTABLE-SUPPRESS-STATE-STORE-0000000010-changelog     |             6 |                            0 | streamsId-KTABLE-SUPPRESS-STATE-STORE-0000000010-changelog     | ACTIVE        |  1663148145515
  patrick-cl-test | sensor-data-raw                                                |             6 |                            0 | sensor-data-raw                                                | ACTIVE        |  1663148145414
  patrick-cl-test | streamsId-KSTREAM-AGGREGATE-STATE-STORE-0000000004-repartition |             6 |                            0 | streamsId-KSTREAM-AGGREGATE-STATE-STORE-0000000004-repartition | ACTIVE        |  1663148145432
  patrick-cl-test | sensor-data-aggregation-streams                                |             6 |                            0 | sensor-data-aggregation-streams                                | ACTIVE        |  1663148145453
```

Promote a topic so that producer can produce data on the destination cluster

```shell
 confluent kafka mirror promote rest-api --link patrick-cl-test                                                                                                                                                                                                              1 ↵
  Mirror Topic Name | Partition | Partition Mirror Lag | Error Message | Error Code | Last Source Fetch Offset
--------------------+-----------+----------------------+---------------+------------+---------------------------
  rest-api          |         1 |                    0 |               |            |                        0
  rest-api          |         0 |                    0 |               |            |                        0


confluent kafka mirror list
     Link Name    |                       Mirror Topic Name                        | Num Partition | Max Per Partition Mirror Lag |                       Source Topic Name                        | Mirror Status | Status Time Ms
------------------+----------------------------------------------------------------+---------------+------------------------------+----------------------------------------------------------------+---------------+-----------------
  patrick-cl-test | rest-api                                                       |             2 |                            0 | rest-api                                                       | STOPPED       |  1663148864210
  patrick-cl-test | streamsId-KSTREAM-AGGREGATE-STATE-STORE-0000000004-changelog   |             6 |                            0 | streamsId-KSTREAM-AGGREGATE-STATE-STORE-0000000004-changelog   | ACTIVE        |  1663148145559
  patrick-cl-test | streamsId-KTABLE-SUPPRESS-STATE-STORE-0000000010-changelog     |             6 |                            0 | streamsId-KTABLE-SUPPRESS-STATE-STORE-0000000010-changelog     | ACTIVE        |  1663148145515
  patrick-cl-test | sensor-data-raw                                                |             6 |                            0 | sensor-data-raw                                                | ACTIVE        |  1663148145414
  patrick-cl-test | streamsId-KSTREAM-AGGREGATE-STATE-STORE-0000000004-repartition |             6 |                            0 | streamsId-KSTREAM-AGGREGATE-STATE-STORE-0000000004-repartition | ACTIVE        |  1663148145432
  patrick-cl-test | sensor-data-aggregation-streams                                |             6 |                            0 | sensor-data-aggregation-streams                                | ACTIVE        |  1663148145453
```

Now you can produce data into the `rest-api` toic on the destination cluster

### ACLs
```shell
confluent kafka acl list

    Principal    | Permission | Operation | Resource Type | Resource Name | Pattern Type
-----------------+------------+-----------+---------------+---------------+---------------
  User:sa-8wonyq | ALLOW      | READ      | TOPIC         | *             | LITERAL
  User:sa-8wonyq | ALLOW      | DESCRIBE  | TOPIC         | *             | LITERAL
```