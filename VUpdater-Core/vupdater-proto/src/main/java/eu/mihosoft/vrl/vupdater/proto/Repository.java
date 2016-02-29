// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package eu.mihosoft.vrl.vupdater.proto;

/**
 * Protobuf type {@code eu.mihosoft.vrl.vupdater.proto.Repository}
 */
public  final class Repository extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:eu.mihosoft.vrl.vupdater.proto.Repository)
    RepositoryOrBuilder {
  // Use Repository.newBuilder() to construct.
  private Repository(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private Repository() {
    desc_ = "";
    entry_ = java.util.Collections.emptyList();
    delta_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private Repository(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry) {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            desc_ = s;
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              entry_ = new java.util.ArrayList<eu.mihosoft.vrl.vupdater.proto.Entry>();
              mutable_bitField0_ |= 0x00000002;
            }
            entry_.add(input.readMessage(eu.mihosoft.vrl.vupdater.proto.Entry.parser(), extensionRegistry));
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              delta_ = new java.util.ArrayList<eu.mihosoft.vrl.vupdater.proto.Delta>();
              mutable_bitField0_ |= 0x00000004;
            }
            delta_.add(input.readMessage(eu.mihosoft.vrl.vupdater.proto.Delta.parser(), extensionRegistry));
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw new RuntimeException(e.setUnfinishedMessage(this));
    } catch (java.io.IOException e) {
      throw new RuntimeException(
          new com.google.protobuf.InvalidProtocolBufferException(
              e.getMessage()).setUnfinishedMessage(this));
    } finally {
      if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
        entry_ = java.util.Collections.unmodifiableList(entry_);
      }
      if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
        delta_ = java.util.Collections.unmodifiableList(delta_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return eu.mihosoft.vrl.vupdater.proto.Message.internal_static_eu_mihosoft_vrl_vupdater_proto_Repository_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return eu.mihosoft.vrl.vupdater.proto.Message.internal_static_eu_mihosoft_vrl_vupdater_proto_Repository_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            eu.mihosoft.vrl.vupdater.proto.Repository.class, eu.mihosoft.vrl.vupdater.proto.Repository.Builder.class);
  }

  private int bitField0_;
  public static final int DESC_FIELD_NUMBER = 1;
  private volatile java.lang.Object desc_;
  /**
   * <code>optional string desc = 1;</code>
   */
  public java.lang.String getDesc() {
    java.lang.Object ref = desc_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      desc_ = s;
      return s;
    }
  }
  /**
   * <code>optional string desc = 1;</code>
   */
  public com.google.protobuf.ByteString
      getDescBytes() {
    java.lang.Object ref = desc_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      desc_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ENTRY_FIELD_NUMBER = 2;
  private java.util.List<eu.mihosoft.vrl.vupdater.proto.Entry> entry_;
  /**
   * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
   */
  public java.util.List<eu.mihosoft.vrl.vupdater.proto.Entry> getEntryList() {
    return entry_;
  }
  /**
   * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
   */
  public java.util.List<? extends eu.mihosoft.vrl.vupdater.proto.EntryOrBuilder> 
      getEntryOrBuilderList() {
    return entry_;
  }
  /**
   * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
   */
  public int getEntryCount() {
    return entry_.size();
  }
  /**
   * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
   */
  public eu.mihosoft.vrl.vupdater.proto.Entry getEntry(int index) {
    return entry_.get(index);
  }
  /**
   * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
   */
  public eu.mihosoft.vrl.vupdater.proto.EntryOrBuilder getEntryOrBuilder(
      int index) {
    return entry_.get(index);
  }

  public static final int DELTA_FIELD_NUMBER = 3;
  private java.util.List<eu.mihosoft.vrl.vupdater.proto.Delta> delta_;
  /**
   * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
   */
  public java.util.List<eu.mihosoft.vrl.vupdater.proto.Delta> getDeltaList() {
    return delta_;
  }
  /**
   * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
   */
  public java.util.List<? extends eu.mihosoft.vrl.vupdater.proto.DeltaOrBuilder> 
      getDeltaOrBuilderList() {
    return delta_;
  }
  /**
   * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
   */
  public int getDeltaCount() {
    return delta_.size();
  }
  /**
   * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
   */
  public eu.mihosoft.vrl.vupdater.proto.Delta getDelta(int index) {
    return delta_.get(index);
  }
  /**
   * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
   */
  public eu.mihosoft.vrl.vupdater.proto.DeltaOrBuilder getDeltaOrBuilder(
      int index) {
    return delta_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getDescBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessage.writeString(output, 1, desc_);
    }
    for (int i = 0; i < entry_.size(); i++) {
      output.writeMessage(2, entry_.get(i));
    }
    for (int i = 0; i < delta_.size(); i++) {
      output.writeMessage(3, delta_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getDescBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(1, desc_);
    }
    for (int i = 0; i < entry_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, entry_.get(i));
    }
    for (int i = 0; i < delta_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, delta_.get(i));
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static eu.mihosoft.vrl.vupdater.proto.Repository parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static eu.mihosoft.vrl.vupdater.proto.Repository parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static eu.mihosoft.vrl.vupdater.proto.Repository parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static eu.mihosoft.vrl.vupdater.proto.Repository parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static eu.mihosoft.vrl.vupdater.proto.Repository parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static eu.mihosoft.vrl.vupdater.proto.Repository parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static eu.mihosoft.vrl.vupdater.proto.Repository parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static eu.mihosoft.vrl.vupdater.proto.Repository parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static eu.mihosoft.vrl.vupdater.proto.Repository parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static eu.mihosoft.vrl.vupdater.proto.Repository parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(eu.mihosoft.vrl.vupdater.proto.Repository prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code eu.mihosoft.vrl.vupdater.proto.Repository}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:eu.mihosoft.vrl.vupdater.proto.Repository)
      eu.mihosoft.vrl.vupdater.proto.RepositoryOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return eu.mihosoft.vrl.vupdater.proto.Message.internal_static_eu_mihosoft_vrl_vupdater_proto_Repository_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return eu.mihosoft.vrl.vupdater.proto.Message.internal_static_eu_mihosoft_vrl_vupdater_proto_Repository_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              eu.mihosoft.vrl.vupdater.proto.Repository.class, eu.mihosoft.vrl.vupdater.proto.Repository.Builder.class);
    }

    // Construct using eu.mihosoft.vrl.vupdater.proto.Repository.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        getEntryFieldBuilder();
        getDeltaFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      desc_ = "";

      if (entryBuilder_ == null) {
        entry_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        entryBuilder_.clear();
      }
      if (deltaBuilder_ == null) {
        delta_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
      } else {
        deltaBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return eu.mihosoft.vrl.vupdater.proto.Message.internal_static_eu_mihosoft_vrl_vupdater_proto_Repository_descriptor;
    }

    public eu.mihosoft.vrl.vupdater.proto.Repository getDefaultInstanceForType() {
      return eu.mihosoft.vrl.vupdater.proto.Repository.getDefaultInstance();
    }

    public eu.mihosoft.vrl.vupdater.proto.Repository build() {
      eu.mihosoft.vrl.vupdater.proto.Repository result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public eu.mihosoft.vrl.vupdater.proto.Repository buildPartial() {
      eu.mihosoft.vrl.vupdater.proto.Repository result = new eu.mihosoft.vrl.vupdater.proto.Repository(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.desc_ = desc_;
      if (entryBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          entry_ = java.util.Collections.unmodifiableList(entry_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.entry_ = entry_;
      } else {
        result.entry_ = entryBuilder_.build();
      }
      if (deltaBuilder_ == null) {
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          delta_ = java.util.Collections.unmodifiableList(delta_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.delta_ = delta_;
      } else {
        result.delta_ = deltaBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof eu.mihosoft.vrl.vupdater.proto.Repository) {
        return mergeFrom((eu.mihosoft.vrl.vupdater.proto.Repository)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(eu.mihosoft.vrl.vupdater.proto.Repository other) {
      if (other == eu.mihosoft.vrl.vupdater.proto.Repository.getDefaultInstance()) return this;
      if (!other.getDesc().isEmpty()) {
        desc_ = other.desc_;
        onChanged();
      }
      if (entryBuilder_ == null) {
        if (!other.entry_.isEmpty()) {
          if (entry_.isEmpty()) {
            entry_ = other.entry_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureEntryIsMutable();
            entry_.addAll(other.entry_);
          }
          onChanged();
        }
      } else {
        if (!other.entry_.isEmpty()) {
          if (entryBuilder_.isEmpty()) {
            entryBuilder_.dispose();
            entryBuilder_ = null;
            entry_ = other.entry_;
            bitField0_ = (bitField0_ & ~0x00000002);
            entryBuilder_ = 
              com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                 getEntryFieldBuilder() : null;
          } else {
            entryBuilder_.addAllMessages(other.entry_);
          }
        }
      }
      if (deltaBuilder_ == null) {
        if (!other.delta_.isEmpty()) {
          if (delta_.isEmpty()) {
            delta_ = other.delta_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureDeltaIsMutable();
            delta_.addAll(other.delta_);
          }
          onChanged();
        }
      } else {
        if (!other.delta_.isEmpty()) {
          if (deltaBuilder_.isEmpty()) {
            deltaBuilder_.dispose();
            deltaBuilder_ = null;
            delta_ = other.delta_;
            bitField0_ = (bitField0_ & ~0x00000004);
            deltaBuilder_ = 
              com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                 getDeltaFieldBuilder() : null;
          } else {
            deltaBuilder_.addAllMessages(other.delta_);
          }
        }
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      eu.mihosoft.vrl.vupdater.proto.Repository parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (eu.mihosoft.vrl.vupdater.proto.Repository) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object desc_ = "";
    /**
     * <code>optional string desc = 1;</code>
     */
    public java.lang.String getDesc() {
      java.lang.Object ref = desc_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        desc_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string desc = 1;</code>
     */
    public com.google.protobuf.ByteString
        getDescBytes() {
      java.lang.Object ref = desc_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        desc_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string desc = 1;</code>
     */
    public Builder setDesc(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      desc_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string desc = 1;</code>
     */
    public Builder clearDesc() {
      
      desc_ = getDefaultInstance().getDesc();
      onChanged();
      return this;
    }
    /**
     * <code>optional string desc = 1;</code>
     */
    public Builder setDescBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      desc_ = value;
      onChanged();
      return this;
    }

    private java.util.List<eu.mihosoft.vrl.vupdater.proto.Entry> entry_ =
      java.util.Collections.emptyList();
    private void ensureEntryIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        entry_ = new java.util.ArrayList<eu.mihosoft.vrl.vupdater.proto.Entry>(entry_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilder<
        eu.mihosoft.vrl.vupdater.proto.Entry, eu.mihosoft.vrl.vupdater.proto.Entry.Builder, eu.mihosoft.vrl.vupdater.proto.EntryOrBuilder> entryBuilder_;

    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
     */
    public java.util.List<eu.mihosoft.vrl.vupdater.proto.Entry> getEntryList() {
      if (entryBuilder_ == null) {
        return java.util.Collections.unmodifiableList(entry_);
      } else {
        return entryBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
     */
    public int getEntryCount() {
      if (entryBuilder_ == null) {
        return entry_.size();
      } else {
        return entryBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
     */
    public eu.mihosoft.vrl.vupdater.proto.Entry getEntry(int index) {
      if (entryBuilder_ == null) {
        return entry_.get(index);
      } else {
        return entryBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
     */
    public Builder setEntry(
        int index, eu.mihosoft.vrl.vupdater.proto.Entry value) {
      if (entryBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEntryIsMutable();
        entry_.set(index, value);
        onChanged();
      } else {
        entryBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
     */
    public Builder setEntry(
        int index, eu.mihosoft.vrl.vupdater.proto.Entry.Builder builderForValue) {
      if (entryBuilder_ == null) {
        ensureEntryIsMutable();
        entry_.set(index, builderForValue.build());
        onChanged();
      } else {
        entryBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
     */
    public Builder addEntry(eu.mihosoft.vrl.vupdater.proto.Entry value) {
      if (entryBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEntryIsMutable();
        entry_.add(value);
        onChanged();
      } else {
        entryBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
     */
    public Builder addEntry(
        int index, eu.mihosoft.vrl.vupdater.proto.Entry value) {
      if (entryBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEntryIsMutable();
        entry_.add(index, value);
        onChanged();
      } else {
        entryBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
     */
    public Builder addEntry(
        eu.mihosoft.vrl.vupdater.proto.Entry.Builder builderForValue) {
      if (entryBuilder_ == null) {
        ensureEntryIsMutable();
        entry_.add(builderForValue.build());
        onChanged();
      } else {
        entryBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
     */
    public Builder addEntry(
        int index, eu.mihosoft.vrl.vupdater.proto.Entry.Builder builderForValue) {
      if (entryBuilder_ == null) {
        ensureEntryIsMutable();
        entry_.add(index, builderForValue.build());
        onChanged();
      } else {
        entryBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
     */
    public Builder addAllEntry(
        java.lang.Iterable<? extends eu.mihosoft.vrl.vupdater.proto.Entry> values) {
      if (entryBuilder_ == null) {
        ensureEntryIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, entry_);
        onChanged();
      } else {
        entryBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
     */
    public Builder clearEntry() {
      if (entryBuilder_ == null) {
        entry_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        entryBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
     */
    public Builder removeEntry(int index) {
      if (entryBuilder_ == null) {
        ensureEntryIsMutable();
        entry_.remove(index);
        onChanged();
      } else {
        entryBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
     */
    public eu.mihosoft.vrl.vupdater.proto.Entry.Builder getEntryBuilder(
        int index) {
      return getEntryFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
     */
    public eu.mihosoft.vrl.vupdater.proto.EntryOrBuilder getEntryOrBuilder(
        int index) {
      if (entryBuilder_ == null) {
        return entry_.get(index);  } else {
        return entryBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
     */
    public java.util.List<? extends eu.mihosoft.vrl.vupdater.proto.EntryOrBuilder> 
         getEntryOrBuilderList() {
      if (entryBuilder_ != null) {
        return entryBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(entry_);
      }
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
     */
    public eu.mihosoft.vrl.vupdater.proto.Entry.Builder addEntryBuilder() {
      return getEntryFieldBuilder().addBuilder(
          eu.mihosoft.vrl.vupdater.proto.Entry.getDefaultInstance());
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
     */
    public eu.mihosoft.vrl.vupdater.proto.Entry.Builder addEntryBuilder(
        int index) {
      return getEntryFieldBuilder().addBuilder(
          index, eu.mihosoft.vrl.vupdater.proto.Entry.getDefaultInstance());
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Entry entry = 2;</code>
     */
    public java.util.List<eu.mihosoft.vrl.vupdater.proto.Entry.Builder> 
         getEntryBuilderList() {
      return getEntryFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilder<
        eu.mihosoft.vrl.vupdater.proto.Entry, eu.mihosoft.vrl.vupdater.proto.Entry.Builder, eu.mihosoft.vrl.vupdater.proto.EntryOrBuilder> 
        getEntryFieldBuilder() {
      if (entryBuilder_ == null) {
        entryBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
            eu.mihosoft.vrl.vupdater.proto.Entry, eu.mihosoft.vrl.vupdater.proto.Entry.Builder, eu.mihosoft.vrl.vupdater.proto.EntryOrBuilder>(
                entry_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        entry_ = null;
      }
      return entryBuilder_;
    }

    private java.util.List<eu.mihosoft.vrl.vupdater.proto.Delta> delta_ =
      java.util.Collections.emptyList();
    private void ensureDeltaIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        delta_ = new java.util.ArrayList<eu.mihosoft.vrl.vupdater.proto.Delta>(delta_);
        bitField0_ |= 0x00000004;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilder<
        eu.mihosoft.vrl.vupdater.proto.Delta, eu.mihosoft.vrl.vupdater.proto.Delta.Builder, eu.mihosoft.vrl.vupdater.proto.DeltaOrBuilder> deltaBuilder_;

    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
     */
    public java.util.List<eu.mihosoft.vrl.vupdater.proto.Delta> getDeltaList() {
      if (deltaBuilder_ == null) {
        return java.util.Collections.unmodifiableList(delta_);
      } else {
        return deltaBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
     */
    public int getDeltaCount() {
      if (deltaBuilder_ == null) {
        return delta_.size();
      } else {
        return deltaBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
     */
    public eu.mihosoft.vrl.vupdater.proto.Delta getDelta(int index) {
      if (deltaBuilder_ == null) {
        return delta_.get(index);
      } else {
        return deltaBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
     */
    public Builder setDelta(
        int index, eu.mihosoft.vrl.vupdater.proto.Delta value) {
      if (deltaBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDeltaIsMutable();
        delta_.set(index, value);
        onChanged();
      } else {
        deltaBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
     */
    public Builder setDelta(
        int index, eu.mihosoft.vrl.vupdater.proto.Delta.Builder builderForValue) {
      if (deltaBuilder_ == null) {
        ensureDeltaIsMutable();
        delta_.set(index, builderForValue.build());
        onChanged();
      } else {
        deltaBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
     */
    public Builder addDelta(eu.mihosoft.vrl.vupdater.proto.Delta value) {
      if (deltaBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDeltaIsMutable();
        delta_.add(value);
        onChanged();
      } else {
        deltaBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
     */
    public Builder addDelta(
        int index, eu.mihosoft.vrl.vupdater.proto.Delta value) {
      if (deltaBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDeltaIsMutable();
        delta_.add(index, value);
        onChanged();
      } else {
        deltaBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
     */
    public Builder addDelta(
        eu.mihosoft.vrl.vupdater.proto.Delta.Builder builderForValue) {
      if (deltaBuilder_ == null) {
        ensureDeltaIsMutable();
        delta_.add(builderForValue.build());
        onChanged();
      } else {
        deltaBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
     */
    public Builder addDelta(
        int index, eu.mihosoft.vrl.vupdater.proto.Delta.Builder builderForValue) {
      if (deltaBuilder_ == null) {
        ensureDeltaIsMutable();
        delta_.add(index, builderForValue.build());
        onChanged();
      } else {
        deltaBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
     */
    public Builder addAllDelta(
        java.lang.Iterable<? extends eu.mihosoft.vrl.vupdater.proto.Delta> values) {
      if (deltaBuilder_ == null) {
        ensureDeltaIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, delta_);
        onChanged();
      } else {
        deltaBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
     */
    public Builder clearDelta() {
      if (deltaBuilder_ == null) {
        delta_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
      } else {
        deltaBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
     */
    public Builder removeDelta(int index) {
      if (deltaBuilder_ == null) {
        ensureDeltaIsMutable();
        delta_.remove(index);
        onChanged();
      } else {
        deltaBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
     */
    public eu.mihosoft.vrl.vupdater.proto.Delta.Builder getDeltaBuilder(
        int index) {
      return getDeltaFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
     */
    public eu.mihosoft.vrl.vupdater.proto.DeltaOrBuilder getDeltaOrBuilder(
        int index) {
      if (deltaBuilder_ == null) {
        return delta_.get(index);  } else {
        return deltaBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
     */
    public java.util.List<? extends eu.mihosoft.vrl.vupdater.proto.DeltaOrBuilder> 
         getDeltaOrBuilderList() {
      if (deltaBuilder_ != null) {
        return deltaBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(delta_);
      }
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
     */
    public eu.mihosoft.vrl.vupdater.proto.Delta.Builder addDeltaBuilder() {
      return getDeltaFieldBuilder().addBuilder(
          eu.mihosoft.vrl.vupdater.proto.Delta.getDefaultInstance());
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
     */
    public eu.mihosoft.vrl.vupdater.proto.Delta.Builder addDeltaBuilder(
        int index) {
      return getDeltaFieldBuilder().addBuilder(
          index, eu.mihosoft.vrl.vupdater.proto.Delta.getDefaultInstance());
    }
    /**
     * <code>repeated .eu.mihosoft.vrl.vupdater.proto.Delta delta = 3;</code>
     */
    public java.util.List<eu.mihosoft.vrl.vupdater.proto.Delta.Builder> 
         getDeltaBuilderList() {
      return getDeltaFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilder<
        eu.mihosoft.vrl.vupdater.proto.Delta, eu.mihosoft.vrl.vupdater.proto.Delta.Builder, eu.mihosoft.vrl.vupdater.proto.DeltaOrBuilder> 
        getDeltaFieldBuilder() {
      if (deltaBuilder_ == null) {
        deltaBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
            eu.mihosoft.vrl.vupdater.proto.Delta, eu.mihosoft.vrl.vupdater.proto.Delta.Builder, eu.mihosoft.vrl.vupdater.proto.DeltaOrBuilder>(
                delta_,
                ((bitField0_ & 0x00000004) == 0x00000004),
                getParentForChildren(),
                isClean());
        delta_ = null;
      }
      return deltaBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:eu.mihosoft.vrl.vupdater.proto.Repository)
  }

  // @@protoc_insertion_point(class_scope:eu.mihosoft.vrl.vupdater.proto.Repository)
  private static final eu.mihosoft.vrl.vupdater.proto.Repository DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new eu.mihosoft.vrl.vupdater.proto.Repository();
  }

  public static eu.mihosoft.vrl.vupdater.proto.Repository getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Repository>
      PARSER = new com.google.protobuf.AbstractParser<Repository>() {
    public Repository parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new Repository(input, extensionRegistry);
      } catch (RuntimeException e) {
        if (e.getCause() instanceof
            com.google.protobuf.InvalidProtocolBufferException) {
          throw (com.google.protobuf.InvalidProtocolBufferException)
              e.getCause();
        }
        throw e;
      }
    }
  };

  public static com.google.protobuf.Parser<Repository> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Repository> getParserForType() {
    return PARSER;
  }

  public eu.mihosoft.vrl.vupdater.proto.Repository getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

