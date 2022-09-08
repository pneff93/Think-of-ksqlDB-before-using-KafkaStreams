# Schema Linking

![](SchemaLinking.png)

In order to link schemas, we follow the [quickstart documentation](https://docs.confluent.io/cloud/current/sr/schema-linking.html).
API Keys and endpoints are encrypted via blackbox. They need to be inserted into the [exporterConfig.txt](./exporterConfig.txt).

## Prerequisites
* We need the Stream Governance API of the destination and source environment
* We need an API Key and Secret for the destination and source environment

## Create Exporter

* In order to transfer the schemas, we need to set up an exporter, see [exporterConfig.txt](./exporterConfig.txt)
* Log in to Confluent Cloud and use the source environment
+ Create new exporter via:
```shell
confluent schema-registry exporter create patrick-sl-1 \
 --subjects "sensor-data-raw*" \
 --context-type CUSTOM \
 --context-name "staging" \
 --config-file ./exporterConfig.txt
```

* Check exporter status via:
```shell
confluent schema-registry exporter get-status patrick-sl-1
```

## Destination Environment
In the destination environment you should now see all schemas beginning with `sensor-data-raw`
with the same version and schema ID.
Moreover, we set the context to staging, so that the exact name in the destination environment is
`:.staging:sensor-data-raw-value` `:<context>:<subject>`.