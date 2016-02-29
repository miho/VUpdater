// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package eu.mihosoft.vrl.vupdater.proto;

public final class Message {
  private Message() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface SearchRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:eu.mihosoft.vrl.vupdater.proto.SearchRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional string query = 1;</code>
     */
    java.lang.String getQuery();
    /**
     * <code>optional string query = 1;</code>
     */
    com.google.protobuf.ByteString
        getQueryBytes();

    /**
     * <code>optional int32 page_number = 2;</code>
     */
    int getPageNumber();

    /**
     * <code>optional int32 result_per_page = 3;</code>
     */
    int getResultPerPage();
  }
  /**
   * Protobuf type {@code eu.mihosoft.vrl.vupdater.proto.SearchRequest}
   */
  public  static final class SearchRequest extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:eu.mihosoft.vrl.vupdater.proto.SearchRequest)
      SearchRequestOrBuilder {
    // Use SearchRequest.newBuilder() to construct.
    private SearchRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
    }
    private SearchRequest() {
      query_ = "";
      pageNumber_ = 0;
      resultPerPage_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private SearchRequest(
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

              query_ = s;
              break;
            }
            case 16: {

              pageNumber_ = input.readInt32();
              break;
            }
            case 24: {

              resultPerPage_ = input.readInt32();
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
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return eu.mihosoft.vrl.vupdater.proto.Message.internal_static_eu_mihosoft_vrl_vupdater_proto_SearchRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return eu.mihosoft.vrl.vupdater.proto.Message.internal_static_eu_mihosoft_vrl_vupdater_proto_SearchRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest.class, eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest.Builder.class);
    }

    public static final int QUERY_FIELD_NUMBER = 1;
    private volatile java.lang.Object query_;
    /**
     * <code>optional string query = 1;</code>
     */
    public java.lang.String getQuery() {
      java.lang.Object ref = query_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        query_ = s;
        return s;
      }
    }
    /**
     * <code>optional string query = 1;</code>
     */
    public com.google.protobuf.ByteString
        getQueryBytes() {
      java.lang.Object ref = query_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        query_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int PAGE_NUMBER_FIELD_NUMBER = 2;
    private int pageNumber_;
    /**
     * <code>optional int32 page_number = 2;</code>
     */
    public int getPageNumber() {
      return pageNumber_;
    }

    public static final int RESULT_PER_PAGE_FIELD_NUMBER = 3;
    private int resultPerPage_;
    /**
     * <code>optional int32 result_per_page = 3;</code>
     */
    public int getResultPerPage() {
      return resultPerPage_;
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
      if (!getQueryBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessage.writeString(output, 1, query_);
      }
      if (pageNumber_ != 0) {
        output.writeInt32(2, pageNumber_);
      }
      if (resultPerPage_ != 0) {
        output.writeInt32(3, resultPerPage_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getQueryBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessage.computeStringSize(1, query_);
      }
      if (pageNumber_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, pageNumber_);
      }
      if (resultPerPage_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, resultPerPage_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    public static eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest prototype) {
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
     * Protobuf type {@code eu.mihosoft.vrl.vupdater.proto.SearchRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:eu.mihosoft.vrl.vupdater.proto.SearchRequest)
        eu.mihosoft.vrl.vupdater.proto.Message.SearchRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return eu.mihosoft.vrl.vupdater.proto.Message.internal_static_eu_mihosoft_vrl_vupdater_proto_SearchRequest_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return eu.mihosoft.vrl.vupdater.proto.Message.internal_static_eu_mihosoft_vrl_vupdater_proto_SearchRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest.class, eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest.Builder.class);
      }

      // Construct using eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest.newBuilder()
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
        }
      }
      public Builder clear() {
        super.clear();
        query_ = "";

        pageNumber_ = 0;

        resultPerPage_ = 0;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return eu.mihosoft.vrl.vupdater.proto.Message.internal_static_eu_mihosoft_vrl_vupdater_proto_SearchRequest_descriptor;
      }

      public eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest getDefaultInstanceForType() {
        return eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest.getDefaultInstance();
      }

      public eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest build() {
        eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest buildPartial() {
        eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest result = new eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest(this);
        result.query_ = query_;
        result.pageNumber_ = pageNumber_;
        result.resultPerPage_ = resultPerPage_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest) {
          return mergeFrom((eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest other) {
        if (other == eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest.getDefaultInstance()) return this;
        if (!other.getQuery().isEmpty()) {
          query_ = other.query_;
          onChanged();
        }
        if (other.getPageNumber() != 0) {
          setPageNumber(other.getPageNumber());
        }
        if (other.getResultPerPage() != 0) {
          setResultPerPage(other.getResultPerPage());
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
        eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object query_ = "";
      /**
       * <code>optional string query = 1;</code>
       */
      public java.lang.String getQuery() {
        java.lang.Object ref = query_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          query_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string query = 1;</code>
       */
      public com.google.protobuf.ByteString
          getQueryBytes() {
        java.lang.Object ref = query_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          query_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string query = 1;</code>
       */
      public Builder setQuery(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        query_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string query = 1;</code>
       */
      public Builder clearQuery() {
        
        query_ = getDefaultInstance().getQuery();
        onChanged();
        return this;
      }
      /**
       * <code>optional string query = 1;</code>
       */
      public Builder setQueryBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        query_ = value;
        onChanged();
        return this;
      }

      private int pageNumber_ ;
      /**
       * <code>optional int32 page_number = 2;</code>
       */
      public int getPageNumber() {
        return pageNumber_;
      }
      /**
       * <code>optional int32 page_number = 2;</code>
       */
      public Builder setPageNumber(int value) {
        
        pageNumber_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 page_number = 2;</code>
       */
      public Builder clearPageNumber() {
        
        pageNumber_ = 0;
        onChanged();
        return this;
      }

      private int resultPerPage_ ;
      /**
       * <code>optional int32 result_per_page = 3;</code>
       */
      public int getResultPerPage() {
        return resultPerPage_;
      }
      /**
       * <code>optional int32 result_per_page = 3;</code>
       */
      public Builder setResultPerPage(int value) {
        
        resultPerPage_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 result_per_page = 3;</code>
       */
      public Builder clearResultPerPage() {
        
        resultPerPage_ = 0;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:eu.mihosoft.vrl.vupdater.proto.SearchRequest)
    }

    // @@protoc_insertion_point(class_scope:eu.mihosoft.vrl.vupdater.proto.SearchRequest)
    private static final eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest();
    }

    public static eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<SearchRequest>
        PARSER = new com.google.protobuf.AbstractParser<SearchRequest>() {
      public SearchRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        try {
          return new SearchRequest(input, extensionRegistry);
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

    public static com.google.protobuf.Parser<SearchRequest> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<SearchRequest> getParserForType() {
      return PARSER;
    }

    public eu.mihosoft.vrl.vupdater.proto.Message.SearchRequest getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_eu_mihosoft_vrl_vupdater_proto_SearchRequest_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_eu_mihosoft_vrl_vupdater_proto_SearchRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rmessage.proto\022\036eu.mihosoft.vrl.vupdate" +
      "r.proto\"L\n\rSearchRequest\022\r\n\005query\030\001 \001(\t\022" +
      "\023\n\013page_number\030\002 \001(\005\022\027\n\017result_per_page\030" +
      "\003 \001(\005b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_eu_mihosoft_vrl_vupdater_proto_SearchRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_eu_mihosoft_vrl_vupdater_proto_SearchRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_eu_mihosoft_vrl_vupdater_proto_SearchRequest_descriptor,
        new java.lang.String[] { "Query", "PageNumber", "ResultPerPage", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}