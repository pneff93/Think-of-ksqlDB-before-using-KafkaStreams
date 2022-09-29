# Role-Based Access Control (RBAC)

In the following we go step by step to finally produce data with an API key from
a service account having a specific role.

Create service account

```shell
confluent iam service-account create PatrickSA --description "SA for RBAC testing"                                                                                                                                                                                                                1 ↵
+-------------+---------------------+
| ID          | sa-3rpz32           |
| Name        | PatrickSA           |
| Description | SA for RBAC testing |
+-------------+---------------------+
```

Create role binding for that service account
```shell
confluent iam rbac role-binding create --principal User:sa-3rpz32 \
--role DeveloperWrite \
--environment env-id \
--kafka-cluster-id lkc-id \
--resource Topic:sensor-data-raw                                                                                                                      1 ↵

+--------------+-----------------+
| Principal    | User:sa-3rpz32  |
| Role         | DeveloperWrite  |
| ResourceType | Topic           |
| Name         | sensor-data-raw |
| PatternType  | LITERAL         |
+--------------+-----------------+
```

Check role binding
```shell
confluent iam rbac role-binding list --principal User:sa-3rpz32

    Principal    | Email |      Role      | Environment | Cloud Cluster | Cluster Type | Logical Cluster | Resource Type |      Name       | Pattern Type
-----------------+-------+----------------+-------------+---------------+--------------+-----------------+---------------+-----------------+---------------
  User:sa-3rpz32 |       | DeveloperWrite | env-id       | lkc-id        | Kafka        | lkc-id          | Topic         | sensor-data-raw | LITERAL
```

Create API key
```shell
confluent api-key create --resource lkc-w7yx85 --service-account sa-3rpz32

+---------+------------------------------------------------------------------+
| API Key | GCSTPZR5UVB3I3DB                                                 |
| Secret  | <secret>                                                         |
+---------+------------------------------------------------------------------+
```

Check API key
```shell
confluent api-key list --service-account sa-3rpz32

         Key         | Description | Owner Resource ID |    Owner Email    | Resource Type | Resource ID |       Created
---------------------+-------------+-------------------+-------------------+---------------+-------------+-----------------------
    GCSTPZR5UVB3I3DB |             | sa-3rpz32         | <service account> | kafka         | lkc-id      | 2022-09-29T11:24:35Z
```

We successfully produce data into the topic using the create API key.

Now we change the topic to `sensor-data-raw-false` and should expect an error
because the service account has no permission for that topic.

```shell
ERROR org.apache.kafka.clients.Metadata - [Producer clientId=producer-1] Topic authorization failed for topics [sensor-data-raw-false]
```

We update the role binding to use a prefix
```shell
confluent iam rbac role-binding create --principal User:sa-3rpz32 \
--role DeveloperWrite \
--environment env-id \
--kafka-cluster-id lkc-id \
--resource Topic:sensor-data-raw --prefix

+--------------+-----------------+
| Principal    | User:sa-3rpz32  |
| Role         | DeveloperWrite  |
| ResourceType | Topic           |
| Name         | sensor-data-raw |
| PatternType  | PREFIXED        |
+--------------+-----------------+
```

And now we also can successfully produce data in the new topic.
Check out all the different roles in the [documentation](https://docs.confluent.io/cloud/current/access-management/access-control/cloud-rbac.html#manage-rbac-using-the-confluent-cli).