/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.kafkaSummitEurope;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class SensorDataPerValue extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 2870747070120227186L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"SensorDataPerValue\",\"namespace\":\"com.kafkaSummitEurope\",\"fields\":[{\"name\":\"sensorId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"type\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"value\",\"type\":\"double\"},{\"name\":\"unit\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"timestamp\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<SensorDataPerValue> ENCODER =
      new BinaryMessageEncoder<SensorDataPerValue>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<SensorDataPerValue> DECODER =
      new BinaryMessageDecoder<SensorDataPerValue>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<SensorDataPerValue> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<SensorDataPerValue> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<SensorDataPerValue> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<SensorDataPerValue>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this SensorDataPerValue to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a SensorDataPerValue from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a SensorDataPerValue instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static SensorDataPerValue fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.String sensorId;
  @Deprecated public java.lang.String type;
  @Deprecated public double value;
  @Deprecated public java.lang.String unit;
  @Deprecated public java.lang.String timestamp;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public SensorDataPerValue() {}

  /**
   * All-args constructor.
   * @param sensorId The new value for sensorId
   * @param type The new value for type
   * @param value The new value for value
   * @param unit The new value for unit
   * @param timestamp The new value for timestamp
   */
  public SensorDataPerValue(java.lang.String sensorId, java.lang.String type, java.lang.Double value, java.lang.String unit, java.lang.String timestamp) {
    this.sensorId = sensorId;
    this.type = type;
    this.value = value;
    this.unit = unit;
    this.timestamp = timestamp;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return sensorId;
    case 1: return type;
    case 2: return value;
    case 3: return unit;
    case 4: return timestamp;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: sensorId = value$ != null ? value$.toString() : null; break;
    case 1: type = value$ != null ? value$.toString() : null; break;
    case 2: value = (java.lang.Double)value$; break;
    case 3: unit = value$ != null ? value$.toString() : null; break;
    case 4: timestamp = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'sensorId' field.
   * @return The value of the 'sensorId' field.
   */
  public java.lang.String getSensorId() {
    return sensorId;
  }


  /**
   * Sets the value of the 'sensorId' field.
   * @param value the value to set.
   */
  public void setSensorId(java.lang.String value) {
    this.sensorId = value;
  }

  /**
   * Gets the value of the 'type' field.
   * @return The value of the 'type' field.
   */
  public java.lang.String getType() {
    return type;
  }


  /**
   * Sets the value of the 'type' field.
   * @param value the value to set.
   */
  public void setType(java.lang.String value) {
    this.type = value;
  }

  /**
   * Gets the value of the 'value' field.
   * @return The value of the 'value' field.
   */
  public double getValue() {
    return value;
  }


  /**
   * Sets the value of the 'value' field.
   * @param value the value to set.
   */
  public void setValue(double value) {
    this.value = value;
  }

  /**
   * Gets the value of the 'unit' field.
   * @return The value of the 'unit' field.
   */
  public java.lang.String getUnit() {
    return unit;
  }


  /**
   * Sets the value of the 'unit' field.
   * @param value the value to set.
   */
  public void setUnit(java.lang.String value) {
    this.unit = value;
  }

  /**
   * Gets the value of the 'timestamp' field.
   * @return The value of the 'timestamp' field.
   */
  public java.lang.String getTimestamp() {
    return timestamp;
  }


  /**
   * Sets the value of the 'timestamp' field.
   * @param value the value to set.
   */
  public void setTimestamp(java.lang.String value) {
    this.timestamp = value;
  }

  /**
   * Creates a new SensorDataPerValue RecordBuilder.
   * @return A new SensorDataPerValue RecordBuilder
   */
  public static com.kafkaSummitEurope.SensorDataPerValue.Builder newBuilder() {
    return new com.kafkaSummitEurope.SensorDataPerValue.Builder();
  }

  /**
   * Creates a new SensorDataPerValue RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new SensorDataPerValue RecordBuilder
   */
  public static com.kafkaSummitEurope.SensorDataPerValue.Builder newBuilder(com.kafkaSummitEurope.SensorDataPerValue.Builder other) {
    if (other == null) {
      return new com.kafkaSummitEurope.SensorDataPerValue.Builder();
    } else {
      return new com.kafkaSummitEurope.SensorDataPerValue.Builder(other);
    }
  }

  /**
   * Creates a new SensorDataPerValue RecordBuilder by copying an existing SensorDataPerValue instance.
   * @param other The existing instance to copy.
   * @return A new SensorDataPerValue RecordBuilder
   */
  public static com.kafkaSummitEurope.SensorDataPerValue.Builder newBuilder(com.kafkaSummitEurope.SensorDataPerValue other) {
    if (other == null) {
      return new com.kafkaSummitEurope.SensorDataPerValue.Builder();
    } else {
      return new com.kafkaSummitEurope.SensorDataPerValue.Builder(other);
    }
  }

  /**
   * RecordBuilder for SensorDataPerValue instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<SensorDataPerValue>
    implements org.apache.avro.data.RecordBuilder<SensorDataPerValue> {

    private java.lang.String sensorId;
    private java.lang.String type;
    private double value;
    private java.lang.String unit;
    private java.lang.String timestamp;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.kafkaSummitEurope.SensorDataPerValue.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.sensorId)) {
        this.sensorId = data().deepCopy(fields()[0].schema(), other.sensorId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.type)) {
        this.type = data().deepCopy(fields()[1].schema(), other.type);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.value)) {
        this.value = data().deepCopy(fields()[2].schema(), other.value);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.unit)) {
        this.unit = data().deepCopy(fields()[3].schema(), other.unit);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[4].schema(), other.timestamp);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
    }

    /**
     * Creates a Builder by copying an existing SensorDataPerValue instance
     * @param other The existing instance to copy.
     */
    private Builder(com.kafkaSummitEurope.SensorDataPerValue other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.sensorId)) {
        this.sensorId = data().deepCopy(fields()[0].schema(), other.sensorId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.type)) {
        this.type = data().deepCopy(fields()[1].schema(), other.type);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.value)) {
        this.value = data().deepCopy(fields()[2].schema(), other.value);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.unit)) {
        this.unit = data().deepCopy(fields()[3].schema(), other.unit);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[4].schema(), other.timestamp);
        fieldSetFlags()[4] = true;
      }
    }

    /**
      * Gets the value of the 'sensorId' field.
      * @return The value.
      */
    public java.lang.String getSensorId() {
      return sensorId;
    }


    /**
      * Sets the value of the 'sensorId' field.
      * @param value The value of 'sensorId'.
      * @return This builder.
      */
    public com.kafkaSummitEurope.SensorDataPerValue.Builder setSensorId(java.lang.String value) {
      validate(fields()[0], value);
      this.sensorId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'sensorId' field has been set.
      * @return True if the 'sensorId' field has been set, false otherwise.
      */
    public boolean hasSensorId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'sensorId' field.
      * @return This builder.
      */
    public com.kafkaSummitEurope.SensorDataPerValue.Builder clearSensorId() {
      sensorId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'type' field.
      * @return The value.
      */
    public java.lang.String getType() {
      return type;
    }


    /**
      * Sets the value of the 'type' field.
      * @param value The value of 'type'.
      * @return This builder.
      */
    public com.kafkaSummitEurope.SensorDataPerValue.Builder setType(java.lang.String value) {
      validate(fields()[1], value);
      this.type = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'type' field has been set.
      * @return True if the 'type' field has been set, false otherwise.
      */
    public boolean hasType() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'type' field.
      * @return This builder.
      */
    public com.kafkaSummitEurope.SensorDataPerValue.Builder clearType() {
      type = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'value' field.
      * @return The value.
      */
    public double getValue() {
      return value;
    }


    /**
      * Sets the value of the 'value' field.
      * @param value The value of 'value'.
      * @return This builder.
      */
    public com.kafkaSummitEurope.SensorDataPerValue.Builder setValue(double value) {
      validate(fields()[2], value);
      this.value = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'value' field has been set.
      * @return True if the 'value' field has been set, false otherwise.
      */
    public boolean hasValue() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'value' field.
      * @return This builder.
      */
    public com.kafkaSummitEurope.SensorDataPerValue.Builder clearValue() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'unit' field.
      * @return The value.
      */
    public java.lang.String getUnit() {
      return unit;
    }


    /**
      * Sets the value of the 'unit' field.
      * @param value The value of 'unit'.
      * @return This builder.
      */
    public com.kafkaSummitEurope.SensorDataPerValue.Builder setUnit(java.lang.String value) {
      validate(fields()[3], value);
      this.unit = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'unit' field has been set.
      * @return True if the 'unit' field has been set, false otherwise.
      */
    public boolean hasUnit() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'unit' field.
      * @return This builder.
      */
    public com.kafkaSummitEurope.SensorDataPerValue.Builder clearUnit() {
      unit = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'timestamp' field.
      * @return The value.
      */
    public java.lang.String getTimestamp() {
      return timestamp;
    }


    /**
      * Sets the value of the 'timestamp' field.
      * @param value The value of 'timestamp'.
      * @return This builder.
      */
    public com.kafkaSummitEurope.SensorDataPerValue.Builder setTimestamp(java.lang.String value) {
      validate(fields()[4], value);
      this.timestamp = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'timestamp' field has been set.
      * @return True if the 'timestamp' field has been set, false otherwise.
      */
    public boolean hasTimestamp() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'timestamp' field.
      * @return This builder.
      */
    public com.kafkaSummitEurope.SensorDataPerValue.Builder clearTimestamp() {
      timestamp = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SensorDataPerValue build() {
      try {
        SensorDataPerValue record = new SensorDataPerValue();
        record.sensorId = fieldSetFlags()[0] ? this.sensorId : (java.lang.String) defaultValue(fields()[0]);
        record.type = fieldSetFlags()[1] ? this.type : (java.lang.String) defaultValue(fields()[1]);
        record.value = fieldSetFlags()[2] ? this.value : (java.lang.Double) defaultValue(fields()[2]);
        record.unit = fieldSetFlags()[3] ? this.unit : (java.lang.String) defaultValue(fields()[3]);
        record.timestamp = fieldSetFlags()[4] ? this.timestamp : (java.lang.String) defaultValue(fields()[4]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<SensorDataPerValue>
    WRITER$ = (org.apache.avro.io.DatumWriter<SensorDataPerValue>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<SensorDataPerValue>
    READER$ = (org.apache.avro.io.DatumReader<SensorDataPerValue>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.sensorId);

    out.writeString(this.type);

    out.writeDouble(this.value);

    out.writeString(this.unit);

    out.writeString(this.timestamp);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.sensorId = in.readString();

      this.type = in.readString();

      this.value = in.readDouble();

      this.unit = in.readString();

      this.timestamp = in.readString();

    } else {
      for (int i = 0; i < 5; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.sensorId = in.readString();
          break;

        case 1:
          this.type = in.readString();
          break;

        case 2:
          this.value = in.readDouble();
          break;

        case 3:
          this.unit = in.readString();
          break;

        case 4:
          this.timestamp = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










