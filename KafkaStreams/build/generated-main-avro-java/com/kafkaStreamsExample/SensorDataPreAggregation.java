/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.kafkaStreamsExample;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class SensorDataPreAggregation extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -443765712240548118L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"SensorDataPreAggregation\",\"namespace\":\"com.kafkaStreamsExample\",\"fields\":[{\"name\":\"sum\",\"type\":\"double\"},{\"name\":\"count\",\"type\":\"int\"},{\"name\":\"unit\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"timestamp\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<SensorDataPreAggregation> ENCODER =
      new BinaryMessageEncoder<SensorDataPreAggregation>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<SensorDataPreAggregation> DECODER =
      new BinaryMessageDecoder<SensorDataPreAggregation>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<SensorDataPreAggregation> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<SensorDataPreAggregation> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<SensorDataPreAggregation> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<SensorDataPreAggregation>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this SensorDataPreAggregation to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a SensorDataPreAggregation from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a SensorDataPreAggregation instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static SensorDataPreAggregation fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public double sum;
  @Deprecated public int count;
  @Deprecated public java.lang.String unit;
  @Deprecated public java.lang.String timestamp;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public SensorDataPreAggregation() {}

  /**
   * All-args constructor.
   * @param sum The new value for sum
   * @param count The new value for count
   * @param unit The new value for unit
   * @param timestamp The new value for timestamp
   */
  public SensorDataPreAggregation(java.lang.Double sum, java.lang.Integer count, java.lang.String unit, java.lang.String timestamp) {
    this.sum = sum;
    this.count = count;
    this.unit = unit;
    this.timestamp = timestamp;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return sum;
    case 1: return count;
    case 2: return unit;
    case 3: return timestamp;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: sum = (java.lang.Double)value$; break;
    case 1: count = (java.lang.Integer)value$; break;
    case 2: unit = value$ != null ? value$.toString() : null; break;
    case 3: timestamp = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'sum' field.
   * @return The value of the 'sum' field.
   */
  public double getSum() {
    return sum;
  }


  /**
   * Sets the value of the 'sum' field.
   * @param value the value to set.
   */
  public void setSum(double value) {
    this.sum = value;
  }

  /**
   * Gets the value of the 'count' field.
   * @return The value of the 'count' field.
   */
  public int getCount() {
    return count;
  }


  /**
   * Sets the value of the 'count' field.
   * @param value the value to set.
   */
  public void setCount(int value) {
    this.count = value;
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
   * Creates a new SensorDataPreAggregation RecordBuilder.
   * @return A new SensorDataPreAggregation RecordBuilder
   */
  public static com.kafkaStreamsExample.SensorDataPreAggregation.Builder newBuilder() {
    return new com.kafkaStreamsExample.SensorDataPreAggregation.Builder();
  }

  /**
   * Creates a new SensorDataPreAggregation RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new SensorDataPreAggregation RecordBuilder
   */
  public static com.kafkaStreamsExample.SensorDataPreAggregation.Builder newBuilder(com.kafkaStreamsExample.SensorDataPreAggregation.Builder other) {
    if (other == null) {
      return new com.kafkaStreamsExample.SensorDataPreAggregation.Builder();
    } else {
      return new com.kafkaStreamsExample.SensorDataPreAggregation.Builder(other);
    }
  }

  /**
   * Creates a new SensorDataPreAggregation RecordBuilder by copying an existing SensorDataPreAggregation instance.
   * @param other The existing instance to copy.
   * @return A new SensorDataPreAggregation RecordBuilder
   */
  public static com.kafkaStreamsExample.SensorDataPreAggregation.Builder newBuilder(com.kafkaStreamsExample.SensorDataPreAggregation other) {
    if (other == null) {
      return new com.kafkaStreamsExample.SensorDataPreAggregation.Builder();
    } else {
      return new com.kafkaStreamsExample.SensorDataPreAggregation.Builder(other);
    }
  }

  /**
   * RecordBuilder for SensorDataPreAggregation instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<SensorDataPreAggregation>
    implements org.apache.avro.data.RecordBuilder<SensorDataPreAggregation> {

    private double sum;
    private int count;
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
    private Builder(com.kafkaStreamsExample.SensorDataPreAggregation.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.sum)) {
        this.sum = data().deepCopy(fields()[0].schema(), other.sum);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.count)) {
        this.count = data().deepCopy(fields()[1].schema(), other.count);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.unit)) {
        this.unit = data().deepCopy(fields()[2].schema(), other.unit);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[3].schema(), other.timestamp);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing SensorDataPreAggregation instance
     * @param other The existing instance to copy.
     */
    private Builder(com.kafkaStreamsExample.SensorDataPreAggregation other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.sum)) {
        this.sum = data().deepCopy(fields()[0].schema(), other.sum);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.count)) {
        this.count = data().deepCopy(fields()[1].schema(), other.count);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.unit)) {
        this.unit = data().deepCopy(fields()[2].schema(), other.unit);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[3].schema(), other.timestamp);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'sum' field.
      * @return The value.
      */
    public double getSum() {
      return sum;
    }


    /**
      * Sets the value of the 'sum' field.
      * @param value The value of 'sum'.
      * @return This builder.
      */
    public com.kafkaStreamsExample.SensorDataPreAggregation.Builder setSum(double value) {
      validate(fields()[0], value);
      this.sum = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'sum' field has been set.
      * @return True if the 'sum' field has been set, false otherwise.
      */
    public boolean hasSum() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'sum' field.
      * @return This builder.
      */
    public com.kafkaStreamsExample.SensorDataPreAggregation.Builder clearSum() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'count' field.
      * @return The value.
      */
    public int getCount() {
      return count;
    }


    /**
      * Sets the value of the 'count' field.
      * @param value The value of 'count'.
      * @return This builder.
      */
    public com.kafkaStreamsExample.SensorDataPreAggregation.Builder setCount(int value) {
      validate(fields()[1], value);
      this.count = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'count' field has been set.
      * @return True if the 'count' field has been set, false otherwise.
      */
    public boolean hasCount() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'count' field.
      * @return This builder.
      */
    public com.kafkaStreamsExample.SensorDataPreAggregation.Builder clearCount() {
      fieldSetFlags()[1] = false;
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
    public com.kafkaStreamsExample.SensorDataPreAggregation.Builder setUnit(java.lang.String value) {
      validate(fields()[2], value);
      this.unit = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'unit' field has been set.
      * @return True if the 'unit' field has been set, false otherwise.
      */
    public boolean hasUnit() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'unit' field.
      * @return This builder.
      */
    public com.kafkaStreamsExample.SensorDataPreAggregation.Builder clearUnit() {
      unit = null;
      fieldSetFlags()[2] = false;
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
    public com.kafkaStreamsExample.SensorDataPreAggregation.Builder setTimestamp(java.lang.String value) {
      validate(fields()[3], value);
      this.timestamp = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'timestamp' field has been set.
      * @return True if the 'timestamp' field has been set, false otherwise.
      */
    public boolean hasTimestamp() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'timestamp' field.
      * @return This builder.
      */
    public com.kafkaStreamsExample.SensorDataPreAggregation.Builder clearTimestamp() {
      timestamp = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SensorDataPreAggregation build() {
      try {
        SensorDataPreAggregation record = new SensorDataPreAggregation();
        record.sum = fieldSetFlags()[0] ? this.sum : (java.lang.Double) defaultValue(fields()[0]);
        record.count = fieldSetFlags()[1] ? this.count : (java.lang.Integer) defaultValue(fields()[1]);
        record.unit = fieldSetFlags()[2] ? this.unit : (java.lang.String) defaultValue(fields()[2]);
        record.timestamp = fieldSetFlags()[3] ? this.timestamp : (java.lang.String) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<SensorDataPreAggregation>
    WRITER$ = (org.apache.avro.io.DatumWriter<SensorDataPreAggregation>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<SensorDataPreAggregation>
    READER$ = (org.apache.avro.io.DatumReader<SensorDataPreAggregation>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeDouble(this.sum);

    out.writeInt(this.count);

    out.writeString(this.unit);

    out.writeString(this.timestamp);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.sum = in.readDouble();

      this.count = in.readInt();

      this.unit = in.readString();

      this.timestamp = in.readString();

    } else {
      for (int i = 0; i < 4; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.sum = in.readDouble();
          break;

        case 1:
          this.count = in.readInt();
          break;

        case 2:
          this.unit = in.readString();
          break;

        case 3:
          this.timestamp = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










