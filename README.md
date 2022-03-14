# Think of ksqlDB before using Kafka Streams ...

Input data:

```json
{
  "sensorId": "sensor_1",
  "value": [
    {
      "type": "temperature",
      "value": 66.5,
      "unit": "Fahrenheit"
    },
    {
      "type": "humidity",
      "value": 71.7,
      "unit": "Percent"
    }
  ],
  "timestamp": "2021-11-25T10:55:47.070Z"
}
```

After Kafka Streams:

```json
{
  "key": {
    "sensorId": "sensor_1",
    "type": "temperature"
  },
  "value": {
    "valueAvg": 19.7,
    "count": 5,
    "unit": "Celsius",
    "timestamp": "2021-11-25T10:56:59.332Z"
  }
}
```

After ksqlDB:

```json
{
  "key": {
    "sensorId": "sensor_1",
    "type": "temperature"
  }
  "value": {
    "valueAvg": 19.5,
    "count": 5,
    "unit": "Celsius",
    "timestamp": "2021-11-25T11:00:39.519Z"
  }
}
```