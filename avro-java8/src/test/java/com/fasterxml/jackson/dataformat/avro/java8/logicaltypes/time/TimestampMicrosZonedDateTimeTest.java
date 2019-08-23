package com.fasterxml.jackson.dataformat.avro.java8.logicaltypes.time;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.avro.AvroType;
import com.fasterxml.jackson.dataformat.avro.AvroMapper;
import com.fasterxml.jackson.dataformat.avro.java8.AvroJavaTimeModule;
import com.fasterxml.jackson.dataformat.avro.java8.logicaltypes.LogicalTypeTestCase;
import com.fasterxml.jackson.dataformat.avro.java8.logicaltypes.TestData;
import org.apache.avro.Schema;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimestampMicrosZonedDateTimeTest extends LogicalTypeTestCase<TimestampMicrosZonedDateTimeTest.TestCase> {
  static final ZonedDateTime VALUE = ZonedDateTime.ofInstant(
      Instant.ofEpochMilli(1526955327123L),
      ZoneId.of("UTC")
  );

  @Override
  protected Class<TestCase> dataClass() {
    return TestCase.class;
  }

  @Override
  protected Schema.Type schemaType() {
    return Schema.Type.LONG;
  }

  @Override
  protected String logicalType() {
    return "timestamp-micros";
  }

  @Override
  protected TestCase testData() {
    TestCase v = new TestCase();
    v.value = VALUE;
    return v;
  }

  @Override
  protected Object convertedValue() {
    return 1526955327123L * 1000L;
  }

  @Override
  protected void configure(AvroMapper mapper) {
    mapper.registerModule(new AvroJavaTimeModule());
  }

  static class TestCase extends TestData<ZonedDateTime> {
    @JsonProperty(required = true)
    @AvroType(schemaType = Schema.Type.LONG, logicalType = AvroType.LogicalType.TIMESTAMP_MICROSECOND)
    ZonedDateTime value;

    @Override
    public ZonedDateTime value() {
      return this.value;
    }
  }

}
