# Authorization via ACLs

Here, we only want to use ACLs for the producer and consumer CLI, not the entire
pipeline because then Schema Registry and Control Center also need
to have a user, which is out of scope here.

In order to configure ACLs, we first add to the broker config

```yaml
KAFKA_AUTHORIZER_CLASS_NAME: kafka.security.authorizer.AclAuthorizer
KAFKA_SUPER_USERS: User:broker1;User:broker2
```

Then every client needs to have specific ACLs to produce or consume data from the broker.
Super Users are not restricted by ACLs. So, the
inter broker communication is allowed by default.

## Add ACLs

The Super Users can add ACLs for other users.

### Producer
For the producer, we add `CREATE` and `WRITE` permissions to the topic `permission-topic`.
```shell
kafka-acls --bootstrap-server broker-1:19094 --command-config ACL/admin.properties --add --allow-principal "User:client"  --operation WRITE --operation CREATE --topic 'permission-topic'
```

Then produce data by:
```shell
kafka-console-producer  --broker-list broker-1:19094 --topic permission-topic  --producer.config ACL/client.properties
```

### Consumer
For the consumer, we add `READ` permissions to the topic `permission-topic` and
the group `test-group`
```shell
kafka-acls --bootstrap-server broker-1:19094 --command-config ACL/admin.properties --add --allow-principal "User:client"  --operation READ --topic permission-topic
kafka-acls --bootstrap-server broker-1:19094 --command-config ACL/admin.properties --add --allow-principal "User:client"  --operation READ --group test-group
```

We then consume data by:
```shell
kafka-console-consumer --bootstrap-server broker-1:19094 --group test-group --topic permission-topic --consumer.config ACL/client.properties --from-beginning
```

## Check ACLs

We can see all ACLs via:
```shell
kafka-acls --bootstrap-server broker-1:19094 --command-config ACL/admin.properties --list
```

```shell
Current ACLs for resource `ResourcePattern(resourceType=GROUP, name=test-group, patternType=LITERAL)`:
 	(principal=User:client, host=*, operation=READ, permissionType=ALLOW)

Current ACLs for resource `ResourcePattern(resourceType=TOPIC, name=permission-topic, patternType=LITERAL)`:
 	(principal=User:client, host=*, operation=CREATE, permissionType=ALLOW)
	(principal=User:client, host=*, operation=READ, permissionType=ALLOW)
	(principal=User:client, host=*, operation=WRITE, permissionType=ALLOW)
```