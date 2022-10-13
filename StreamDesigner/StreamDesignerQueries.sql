CREATE STREAM SENSOR_DATA_RAW(
    sensorId STRING KEY,
    value ARRAY<STRUCT<type STRING, value DOUBLE, unit STRING>>,
    timestamp STRING
) WITH (
    KAFKA_TOPIC = 'sensor-data-raw',
    VALUE_FORMAT = 'AVRO',
    VALUE_AVRO_SCHEMA_FULL_NAME = 'com.kafkaStreamsExample.SensorData'
);


-- We want to have data per value
CREATE STREAM SENSOR_DATA_PER_VALUE
WITH (
    KAFKA_TOPIC = 'sensor-data-per-value',
    VALUE_FORMAT = 'AVRO',
    VALUE_AVRO_SCHEMA_FULL_NAME = 'com.kafkaStreamsExample.SensorDataPerValue'
) AS SELECT
    sensorId,
    EXPLODE(value)->type AS type,
    EXPLODE(value)->value AS value,
    EXPLODE(value)->unit AS unit,
    timestamp
    FROM SENSOR_DATA_RAW
    PARTITION BY sensorId;


-- We finally aggregate
set 'ksql.suppress.enabled'='true';
CREATE TABLE SENSOR_DATA_AGGREGATION
WITH (
    KAFKA_TOPIC = 'sensor-data-aggregation-ksql',
    KEY_FORMAT = 'AVRO',
    VALUE_FORMAT = 'AVRO',
    VALUE_AVRO_SCHEMA_FULL_NAME = 'com.kafkaStreamsExample.SensorDataAggregation'
) AS SELECT
    sensorId,
    type,
    ROUND(AVG(value), 1) AS valueAvg,
    COUNT(value) AS count,
    LATEST_BY_OFFSET(unit) AS unit,
    LATEST_BY_OFFSET(timestamp) AS timestamp
    FROM SENSOR_DATA_PER_VALUE
    WINDOW TUMBLING (SIZE 10 SECONDS)
    GROUP BY sensorId, type
    EMIT FINAL;
