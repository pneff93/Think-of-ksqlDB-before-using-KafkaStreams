# SASL

SASL authentication can be configured via:

* Plain - unencrypted username and password
* GSSAPI - with Kerberos
* SCRAM

We only use Plain in this example.

### Configure Kafka Brokers

```yaml
KAFKA_SASL_ENABLED_MECHANISMS: PLAIN
KAFKA_SASL_MECHANISM_INTER_BROKER_PROTOCOL: PLAIN
KAFKA_LISTENER_NAME_SASL_PLAIN_SASL_JAAS_CONFIG: org.apache.kafka.common.security.plain.PlainLoginModule required \
        username="broker-2" \
        password="broker-2-secret" \
        user_client="client-secret";
```
The `KAFKA_LISTENER_NAME_SASL_PLAIN_SASL_JAAS_CONFIG` needs
to be configured for every port using SASL. Its naming
consists of `KAFKA_LISTENER_NAME_<HOSTNAME>_<MECHANISM>_SASL_JAAS_CONFIG`.

Moreover, the username and password are the credentials used by
the broker to authenticate when it acts as a client (inter broker communication). 
As a server you can authenticate with client:client-secret to the broker.


### Configure Clients

```kotlin
settings.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL")
settings.setProperty(SaslConfigs.SASL_MECHANISM, "PLAIN")
settings.setProperty(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"client\" password=\"client-secret2\";")
```

If you do not configure your clients properly, you receive the 
following error:

```shell
ERROR org.apache.kafka.clients.NetworkClient - [AdminClient clientId=adminclient-1] Connection to node -1 (broker-1/127.0.0.1:19094)
failed authentication due to: Authentication failed: Invalid username or password
```