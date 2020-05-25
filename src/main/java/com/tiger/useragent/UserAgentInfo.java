/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.tiger.useragent;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class UserAgentInfo extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"UserAgentInfo\",\"namespace\":\"com.tiger.useragent\",\"fields\":[{\"name\":\"os_type\",\"type\":\"int\",\"default\":0},{\"name\":\"os_name\",\"type\":\"string\",\"default\":\"-\"},{\"name\":\"os_detail\",\"type\":\"string\",\"default\":\"-\"},{\"name\":\"os_version\",\"type\":\"string\",\"default\":\"-\"},{\"name\":\"browser_name\",\"type\":\"string\",\"default\":\"-\"},{\"name\":\"browser_detail\",\"type\":\"string\",\"default\":\"-\"},{\"name\":\"browser_version\",\"type\":\"string\",\"default\":\"-\"},{\"name\":\"device_brand\",\"type\":\"string\",\"default\":\"-\"},{\"name\":\"device_name\",\"type\":\"string\",\"default\":\"-\"},{\"name\":\"device_type\",\"type\":\"string\",\"default\":\"-\"},{\"name\":\"int_device_type\",\"type\":\"int\",\"default\":0},{\"name\":\"is_mobile\",\"type\":\"boolean\",\"default\":false},{\"name\":\"net_type\",\"type\":\"string\",\"default\":\"-\"},{\"name\":\"int_net_type\",\"type\":\"int\",\"default\":0}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public int os_type;
  @Deprecated public java.lang.CharSequence os_name;
  @Deprecated public java.lang.CharSequence os_detail;
  @Deprecated public java.lang.CharSequence os_version;
  @Deprecated public java.lang.CharSequence browser_name;
  @Deprecated public java.lang.CharSequence browser_detail;
  @Deprecated public java.lang.CharSequence browser_version;
  @Deprecated public java.lang.CharSequence device_brand;
  @Deprecated public java.lang.CharSequence device_name;
  @Deprecated public java.lang.CharSequence device_type;
  @Deprecated public int int_device_type;
  @Deprecated public boolean is_mobile;
  @Deprecated public java.lang.CharSequence net_type;
  @Deprecated public int int_net_type;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public UserAgentInfo() {}

  /**
   * All-args constructor.
   */
  public UserAgentInfo(java.lang.Integer os_type, java.lang.CharSequence os_name, java.lang.CharSequence os_detail, java.lang.CharSequence os_version, java.lang.CharSequence browser_name, java.lang.CharSequence browser_detail, java.lang.CharSequence browser_version, java.lang.CharSequence device_brand, java.lang.CharSequence device_name, java.lang.CharSequence device_type, java.lang.Integer int_device_type, java.lang.Boolean is_mobile, java.lang.CharSequence net_type, java.lang.Integer int_net_type) {
    this.os_type = os_type;
    this.os_name = os_name;
    this.os_detail = os_detail;
    this.os_version = os_version;
    this.browser_name = browser_name;
    this.browser_detail = browser_detail;
    this.browser_version = browser_version;
    this.device_brand = device_brand;
    this.device_name = device_name;
    this.device_type = device_type;
    this.int_device_type = int_device_type;
    this.is_mobile = is_mobile;
    this.net_type = net_type;
    this.int_net_type = int_net_type;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return os_type;
    case 1: return os_name;
    case 2: return os_detail;
    case 3: return os_version;
    case 4: return browser_name;
    case 5: return browser_detail;
    case 6: return browser_version;
    case 7: return device_brand;
    case 8: return device_name;
    case 9: return device_type;
    case 10: return int_device_type;
    case 11: return is_mobile;
    case 12: return net_type;
    case 13: return int_net_type;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: os_type = (java.lang.Integer)value$; break;
    case 1: os_name = (java.lang.CharSequence)value$; break;
    case 2: os_detail = (java.lang.CharSequence)value$; break;
    case 3: os_version = (java.lang.CharSequence)value$; break;
    case 4: browser_name = (java.lang.CharSequence)value$; break;
    case 5: browser_detail = (java.lang.CharSequence)value$; break;
    case 6: browser_version = (java.lang.CharSequence)value$; break;
    case 7: device_brand = (java.lang.CharSequence)value$; break;
    case 8: device_name = (java.lang.CharSequence)value$; break;
    case 9: device_type = (java.lang.CharSequence)value$; break;
    case 10: int_device_type = (java.lang.Integer)value$; break;
    case 11: is_mobile = (java.lang.Boolean)value$; break;
    case 12: net_type = (java.lang.CharSequence)value$; break;
    case 13: int_net_type = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'os_type' field.
   */
  public java.lang.Integer getOsType() {
    return os_type;
  }

  /**
   * Sets the value of the 'os_type' field.
   * @param value the value to set.
   */
  public void setOsType(java.lang.Integer value) {
    this.os_type = value;
  }

  /**
   * Gets the value of the 'os_name' field.
   */
  public java.lang.CharSequence getOsName() {
    return os_name;
  }

  /**
   * Sets the value of the 'os_name' field.
   * @param value the value to set.
   */
  public void setOsName(java.lang.CharSequence value) {
    this.os_name = value;
  }

  /**
   * Gets the value of the 'os_detail' field.
   */
  public java.lang.CharSequence getOsDetail() {
    return os_detail;
  }

  /**
   * Sets the value of the 'os_detail' field.
   * @param value the value to set.
   */
  public void setOsDetail(java.lang.CharSequence value) {
    this.os_detail = value;
  }

  /**
   * Gets the value of the 'os_version' field.
   */
  public java.lang.CharSequence getOsVersion() {
    return os_version;
  }

  /**
   * Sets the value of the 'os_version' field.
   * @param value the value to set.
   */
  public void setOsVersion(java.lang.CharSequence value) {
    this.os_version = value;
  }

  /**
   * Gets the value of the 'browser_name' field.
   */
  public java.lang.CharSequence getBrowserName() {
    return browser_name;
  }

  /**
   * Sets the value of the 'browser_name' field.
   * @param value the value to set.
   */
  public void setBrowserName(java.lang.CharSequence value) {
    this.browser_name = value;
  }

  /**
   * Gets the value of the 'browser_detail' field.
   */
  public java.lang.CharSequence getBrowserDetail() {
    return browser_detail;
  }

  /**
   * Sets the value of the 'browser_detail' field.
   * @param value the value to set.
   */
  public void setBrowserDetail(java.lang.CharSequence value) {
    this.browser_detail = value;
  }

  /**
   * Gets the value of the 'browser_version' field.
   */
  public java.lang.CharSequence getBrowserVersion() {
    return browser_version;
  }

  /**
   * Sets the value of the 'browser_version' field.
   * @param value the value to set.
   */
  public void setBrowserVersion(java.lang.CharSequence value) {
    this.browser_version = value;
  }

  /**
   * Gets the value of the 'device_brand' field.
   */
  public java.lang.CharSequence getDeviceBrand() {
    return device_brand;
  }

  /**
   * Sets the value of the 'device_brand' field.
   * @param value the value to set.
   */
  public void setDeviceBrand(java.lang.CharSequence value) {
    this.device_brand = value;
  }

  /**
   * Gets the value of the 'device_name' field.
   */
  public java.lang.CharSequence getDeviceName() {
    return device_name;
  }

  /**
   * Sets the value of the 'device_name' field.
   * @param value the value to set.
   */
  public void setDeviceName(java.lang.CharSequence value) {
    this.device_name = value;
  }

  /**
   * Gets the value of the 'device_type' field.
   */
  public java.lang.CharSequence getDeviceType() {
    return device_type;
  }

  /**
   * Sets the value of the 'device_type' field.
   * @param value the value to set.
   */
  public void setDeviceType(java.lang.CharSequence value) {
    this.device_type = value;
  }

  /**
   * Gets the value of the 'int_device_type' field.
   */
  public java.lang.Integer getIntDeviceType() {
    return int_device_type;
  }

  /**
   * Sets the value of the 'int_device_type' field.
   * @param value the value to set.
   */
  public void setIntDeviceType(java.lang.Integer value) {
    this.int_device_type = value;
  }

  /**
   * Gets the value of the 'is_mobile' field.
   */
  public java.lang.Boolean getIsMobile() {
    return is_mobile;
  }

  /**
   * Sets the value of the 'is_mobile' field.
   * @param value the value to set.
   */
  public void setIsMobile(java.lang.Boolean value) {
    this.is_mobile = value;
  }

  /**
   * Gets the value of the 'net_type' field.
   */
  public java.lang.CharSequence getNetType() {
    return net_type;
  }

  /**
   * Sets the value of the 'net_type' field.
   * @param value the value to set.
   */
  public void setNetType(java.lang.CharSequence value) {
    this.net_type = value;
  }

  /**
   * Gets the value of the 'int_net_type' field.
   */
  public java.lang.Integer getIntNetType() {
    return int_net_type;
  }

  /**
   * Sets the value of the 'int_net_type' field.
   * @param value the value to set.
   */
  public void setIntNetType(java.lang.Integer value) {
    this.int_net_type = value;
  }

  /**
   * Creates a new UserAgentInfo RecordBuilder.
   * @return A new UserAgentInfo RecordBuilder
   */
  public static com.tiger.useragent.UserAgentInfo.Builder newBuilder() {
    return new com.tiger.useragent.UserAgentInfo.Builder();
  }
  
  /**
   * Creates a new UserAgentInfo RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new UserAgentInfo RecordBuilder
   */
  public static com.tiger.useragent.UserAgentInfo.Builder newBuilder(com.tiger.useragent.UserAgentInfo.Builder other) {
    return new com.tiger.useragent.UserAgentInfo.Builder(other);
  }
  
  /**
   * Creates a new UserAgentInfo RecordBuilder by copying an existing UserAgentInfo instance.
   * @param other The existing instance to copy.
   * @return A new UserAgentInfo RecordBuilder
   */
  public static com.tiger.useragent.UserAgentInfo.Builder newBuilder(com.tiger.useragent.UserAgentInfo other) {
    return new com.tiger.useragent.UserAgentInfo.Builder(other);
  }
  
  /**
   * RecordBuilder for UserAgentInfo instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<UserAgentInfo>
    implements org.apache.avro.data.RecordBuilder<UserAgentInfo> {

    private int os_type;
    private java.lang.CharSequence os_name;
    private java.lang.CharSequence os_detail;
    private java.lang.CharSequence os_version;
    private java.lang.CharSequence browser_name;
    private java.lang.CharSequence browser_detail;
    private java.lang.CharSequence browser_version;
    private java.lang.CharSequence device_brand;
    private java.lang.CharSequence device_name;
    private java.lang.CharSequence device_type;
    private int int_device_type;
    private boolean is_mobile;
    private java.lang.CharSequence net_type;
    private int int_net_type;

    /** Creates a new Builder */
    private Builder() {
      super(com.tiger.useragent.UserAgentInfo.SCHEMA$);
    }
    
    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.tiger.useragent.UserAgentInfo.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.os_type)) {
        this.os_type = data().deepCopy(fields()[0].schema(), other.os_type);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.os_name)) {
        this.os_name = data().deepCopy(fields()[1].schema(), other.os_name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.os_detail)) {
        this.os_detail = data().deepCopy(fields()[2].schema(), other.os_detail);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.os_version)) {
        this.os_version = data().deepCopy(fields()[3].schema(), other.os_version);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.browser_name)) {
        this.browser_name = data().deepCopy(fields()[4].schema(), other.browser_name);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.browser_detail)) {
        this.browser_detail = data().deepCopy(fields()[5].schema(), other.browser_detail);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.browser_version)) {
        this.browser_version = data().deepCopy(fields()[6].schema(), other.browser_version);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.device_brand)) {
        this.device_brand = data().deepCopy(fields()[7].schema(), other.device_brand);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.device_name)) {
        this.device_name = data().deepCopy(fields()[8].schema(), other.device_name);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.device_type)) {
        this.device_type = data().deepCopy(fields()[9].schema(), other.device_type);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.int_device_type)) {
        this.int_device_type = data().deepCopy(fields()[10].schema(), other.int_device_type);
        fieldSetFlags()[10] = true;
      }
      if (isValidValue(fields()[11], other.is_mobile)) {
        this.is_mobile = data().deepCopy(fields()[11].schema(), other.is_mobile);
        fieldSetFlags()[11] = true;
      }
      if (isValidValue(fields()[12], other.net_type)) {
        this.net_type = data().deepCopy(fields()[12].schema(), other.net_type);
        fieldSetFlags()[12] = true;
      }
      if (isValidValue(fields()[13], other.int_net_type)) {
        this.int_net_type = data().deepCopy(fields()[13].schema(), other.int_net_type);
        fieldSetFlags()[13] = true;
      }
    }
    
    /**
     * Creates a Builder by copying an existing UserAgentInfo instance
     * @param other The existing instance to copy.
     */
    private Builder(com.tiger.useragent.UserAgentInfo other) {
            super(com.tiger.useragent.UserAgentInfo.SCHEMA$);
      if (isValidValue(fields()[0], other.os_type)) {
        this.os_type = data().deepCopy(fields()[0].schema(), other.os_type);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.os_name)) {
        this.os_name = data().deepCopy(fields()[1].schema(), other.os_name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.os_detail)) {
        this.os_detail = data().deepCopy(fields()[2].schema(), other.os_detail);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.os_version)) {
        this.os_version = data().deepCopy(fields()[3].schema(), other.os_version);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.browser_name)) {
        this.browser_name = data().deepCopy(fields()[4].schema(), other.browser_name);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.browser_detail)) {
        this.browser_detail = data().deepCopy(fields()[5].schema(), other.browser_detail);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.browser_version)) {
        this.browser_version = data().deepCopy(fields()[6].schema(), other.browser_version);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.device_brand)) {
        this.device_brand = data().deepCopy(fields()[7].schema(), other.device_brand);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.device_name)) {
        this.device_name = data().deepCopy(fields()[8].schema(), other.device_name);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.device_type)) {
        this.device_type = data().deepCopy(fields()[9].schema(), other.device_type);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.int_device_type)) {
        this.int_device_type = data().deepCopy(fields()[10].schema(), other.int_device_type);
        fieldSetFlags()[10] = true;
      }
      if (isValidValue(fields()[11], other.is_mobile)) {
        this.is_mobile = data().deepCopy(fields()[11].schema(), other.is_mobile);
        fieldSetFlags()[11] = true;
      }
      if (isValidValue(fields()[12], other.net_type)) {
        this.net_type = data().deepCopy(fields()[12].schema(), other.net_type);
        fieldSetFlags()[12] = true;
      }
      if (isValidValue(fields()[13], other.int_net_type)) {
        this.int_net_type = data().deepCopy(fields()[13].schema(), other.int_net_type);
        fieldSetFlags()[13] = true;
      }
    }

    /**
      * Gets the value of the 'os_type' field.
      * @return The value.
      */
    public java.lang.Integer getOsType() {
      return os_type;
    }

    /**
      * Sets the value of the 'os_type' field.
      * @param value The value of 'os_type'.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder setOsType(int value) {
      validate(fields()[0], value);
      this.os_type = value;
      fieldSetFlags()[0] = true;
      return this; 
    }

    /**
      * Checks whether the 'os_type' field has been set.
      * @return True if the 'os_type' field has been set, false otherwise.
      */
    public boolean hasOsType() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'os_type' field.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder clearOsType() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'os_name' field.
      * @return The value.
      */
    public java.lang.CharSequence getOsName() {
      return os_name;
    }

    /**
      * Sets the value of the 'os_name' field.
      * @param value The value of 'os_name'.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder setOsName(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.os_name = value;
      fieldSetFlags()[1] = true;
      return this; 
    }

    /**
      * Checks whether the 'os_name' field has been set.
      * @return True if the 'os_name' field has been set, false otherwise.
      */
    public boolean hasOsName() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'os_name' field.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder clearOsName() {
      os_name = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'os_detail' field.
      * @return The value.
      */
    public java.lang.CharSequence getOsDetail() {
      return os_detail;
    }

    /**
      * Sets the value of the 'os_detail' field.
      * @param value The value of 'os_detail'.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder setOsDetail(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.os_detail = value;
      fieldSetFlags()[2] = true;
      return this; 
    }

    /**
      * Checks whether the 'os_detail' field has been set.
      * @return True if the 'os_detail' field has been set, false otherwise.
      */
    public boolean hasOsDetail() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'os_detail' field.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder clearOsDetail() {
      os_detail = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'os_version' field.
      * @return The value.
      */
    public java.lang.CharSequence getOsVersion() {
      return os_version;
    }

    /**
      * Sets the value of the 'os_version' field.
      * @param value The value of 'os_version'.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder setOsVersion(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.os_version = value;
      fieldSetFlags()[3] = true;
      return this; 
    }

    /**
      * Checks whether the 'os_version' field has been set.
      * @return True if the 'os_version' field has been set, false otherwise.
      */
    public boolean hasOsVersion() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'os_version' field.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder clearOsVersion() {
      os_version = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'browser_name' field.
      * @return The value.
      */
    public java.lang.CharSequence getBrowserName() {
      return browser_name;
    }

    /**
      * Sets the value of the 'browser_name' field.
      * @param value The value of 'browser_name'.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder setBrowserName(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.browser_name = value;
      fieldSetFlags()[4] = true;
      return this; 
    }

    /**
      * Checks whether the 'browser_name' field has been set.
      * @return True if the 'browser_name' field has been set, false otherwise.
      */
    public boolean hasBrowserName() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'browser_name' field.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder clearBrowserName() {
      browser_name = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'browser_detail' field.
      * @return The value.
      */
    public java.lang.CharSequence getBrowserDetail() {
      return browser_detail;
    }

    /**
      * Sets the value of the 'browser_detail' field.
      * @param value The value of 'browser_detail'.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder setBrowserDetail(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.browser_detail = value;
      fieldSetFlags()[5] = true;
      return this; 
    }

    /**
      * Checks whether the 'browser_detail' field has been set.
      * @return True if the 'browser_detail' field has been set, false otherwise.
      */
    public boolean hasBrowserDetail() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'browser_detail' field.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder clearBrowserDetail() {
      browser_detail = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'browser_version' field.
      * @return The value.
      */
    public java.lang.CharSequence getBrowserVersion() {
      return browser_version;
    }

    /**
      * Sets the value of the 'browser_version' field.
      * @param value The value of 'browser_version'.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder setBrowserVersion(java.lang.CharSequence value) {
      validate(fields()[6], value);
      this.browser_version = value;
      fieldSetFlags()[6] = true;
      return this; 
    }

    /**
      * Checks whether the 'browser_version' field has been set.
      * @return True if the 'browser_version' field has been set, false otherwise.
      */
    public boolean hasBrowserVersion() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'browser_version' field.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder clearBrowserVersion() {
      browser_version = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'device_brand' field.
      * @return The value.
      */
    public java.lang.CharSequence getDeviceBrand() {
      return device_brand;
    }

    /**
      * Sets the value of the 'device_brand' field.
      * @param value The value of 'device_brand'.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder setDeviceBrand(java.lang.CharSequence value) {
      validate(fields()[7], value);
      this.device_brand = value;
      fieldSetFlags()[7] = true;
      return this; 
    }

    /**
      * Checks whether the 'device_brand' field has been set.
      * @return True if the 'device_brand' field has been set, false otherwise.
      */
    public boolean hasDeviceBrand() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'device_brand' field.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder clearDeviceBrand() {
      device_brand = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the 'device_name' field.
      * @return The value.
      */
    public java.lang.CharSequence getDeviceName() {
      return device_name;
    }

    /**
      * Sets the value of the 'device_name' field.
      * @param value The value of 'device_name'.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder setDeviceName(java.lang.CharSequence value) {
      validate(fields()[8], value);
      this.device_name = value;
      fieldSetFlags()[8] = true;
      return this; 
    }

    /**
      * Checks whether the 'device_name' field has been set.
      * @return True if the 'device_name' field has been set, false otherwise.
      */
    public boolean hasDeviceName() {
      return fieldSetFlags()[8];
    }


    /**
      * Clears the value of the 'device_name' field.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder clearDeviceName() {
      device_name = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /**
      * Gets the value of the 'device_type' field.
      * @return The value.
      */
    public java.lang.CharSequence getDeviceType() {
      return device_type;
    }

    /**
      * Sets the value of the 'device_type' field.
      * @param value The value of 'device_type'.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder setDeviceType(java.lang.CharSequence value) {
      validate(fields()[9], value);
      this.device_type = value;
      fieldSetFlags()[9] = true;
      return this; 
    }

    /**
      * Checks whether the 'device_type' field has been set.
      * @return True if the 'device_type' field has been set, false otherwise.
      */
    public boolean hasDeviceType() {
      return fieldSetFlags()[9];
    }


    /**
      * Clears the value of the 'device_type' field.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder clearDeviceType() {
      device_type = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    /**
      * Gets the value of the 'int_device_type' field.
      * @return The value.
      */
    public java.lang.Integer getIntDeviceType() {
      return int_device_type;
    }

    /**
      * Sets the value of the 'int_device_type' field.
      * @param value The value of 'int_device_type'.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder setIntDeviceType(int value) {
      validate(fields()[10], value);
      this.int_device_type = value;
      fieldSetFlags()[10] = true;
      return this; 
    }

    /**
      * Checks whether the 'int_device_type' field has been set.
      * @return True if the 'int_device_type' field has been set, false otherwise.
      */
    public boolean hasIntDeviceType() {
      return fieldSetFlags()[10];
    }


    /**
      * Clears the value of the 'int_device_type' field.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder clearIntDeviceType() {
      fieldSetFlags()[10] = false;
      return this;
    }

    /**
      * Gets the value of the 'is_mobile' field.
      * @return The value.
      */
    public java.lang.Boolean getIsMobile() {
      return is_mobile;
    }

    /**
      * Sets the value of the 'is_mobile' field.
      * @param value The value of 'is_mobile'.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder setIsMobile(boolean value) {
      validate(fields()[11], value);
      this.is_mobile = value;
      fieldSetFlags()[11] = true;
      return this; 
    }

    /**
      * Checks whether the 'is_mobile' field has been set.
      * @return True if the 'is_mobile' field has been set, false otherwise.
      */
    public boolean hasIsMobile() {
      return fieldSetFlags()[11];
    }


    /**
      * Clears the value of the 'is_mobile' field.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder clearIsMobile() {
      fieldSetFlags()[11] = false;
      return this;
    }

    /**
      * Gets the value of the 'net_type' field.
      * @return The value.
      */
    public java.lang.CharSequence getNetType() {
      return net_type;
    }

    /**
      * Sets the value of the 'net_type' field.
      * @param value The value of 'net_type'.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder setNetType(java.lang.CharSequence value) {
      validate(fields()[12], value);
      this.net_type = value;
      fieldSetFlags()[12] = true;
      return this; 
    }

    /**
      * Checks whether the 'net_type' field has been set.
      * @return True if the 'net_type' field has been set, false otherwise.
      */
    public boolean hasNetType() {
      return fieldSetFlags()[12];
    }


    /**
      * Clears the value of the 'net_type' field.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder clearNetType() {
      net_type = null;
      fieldSetFlags()[12] = false;
      return this;
    }

    /**
      * Gets the value of the 'int_net_type' field.
      * @return The value.
      */
    public java.lang.Integer getIntNetType() {
      return int_net_type;
    }

    /**
      * Sets the value of the 'int_net_type' field.
      * @param value The value of 'int_net_type'.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder setIntNetType(int value) {
      validate(fields()[13], value);
      this.int_net_type = value;
      fieldSetFlags()[13] = true;
      return this; 
    }

    /**
      * Checks whether the 'int_net_type' field has been set.
      * @return True if the 'int_net_type' field has been set, false otherwise.
      */
    public boolean hasIntNetType() {
      return fieldSetFlags()[13];
    }


    /**
      * Clears the value of the 'int_net_type' field.
      * @return This builder.
      */
    public com.tiger.useragent.UserAgentInfo.Builder clearIntNetType() {
      fieldSetFlags()[13] = false;
      return this;
    }

    @Override
    public UserAgentInfo build() {
      try {
        UserAgentInfo record = new UserAgentInfo();
        record.os_type = fieldSetFlags()[0] ? this.os_type : (java.lang.Integer) defaultValue(fields()[0]);
        record.os_name = fieldSetFlags()[1] ? this.os_name : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.os_detail = fieldSetFlags()[2] ? this.os_detail : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.os_version = fieldSetFlags()[3] ? this.os_version : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.browser_name = fieldSetFlags()[4] ? this.browser_name : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.browser_detail = fieldSetFlags()[5] ? this.browser_detail : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.browser_version = fieldSetFlags()[6] ? this.browser_version : (java.lang.CharSequence) defaultValue(fields()[6]);
        record.device_brand = fieldSetFlags()[7] ? this.device_brand : (java.lang.CharSequence) defaultValue(fields()[7]);
        record.device_name = fieldSetFlags()[8] ? this.device_name : (java.lang.CharSequence) defaultValue(fields()[8]);
        record.device_type = fieldSetFlags()[9] ? this.device_type : (java.lang.CharSequence) defaultValue(fields()[9]);
        record.int_device_type = fieldSetFlags()[10] ? this.int_device_type : (java.lang.Integer) defaultValue(fields()[10]);
        record.is_mobile = fieldSetFlags()[11] ? this.is_mobile : (java.lang.Boolean) defaultValue(fields()[11]);
        record.net_type = fieldSetFlags()[12] ? this.net_type : (java.lang.CharSequence) defaultValue(fields()[12]);
        record.int_net_type = fieldSetFlags()[13] ? this.int_net_type : (java.lang.Integer) defaultValue(fields()[13]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
