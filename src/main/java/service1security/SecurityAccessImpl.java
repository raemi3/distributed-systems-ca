// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service1security.proto

package service1security;

public final class SecurityAccessImpl {
  private SecurityAccessImpl() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CodeRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CodeRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CodeResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CodeResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026service1security.proto\" \n\013CodeRequest\022" +
      "\021\n\tenterCode\030\001 \001(\t\"!\n\014CodeResponse\022\021\n\tch" +
      "eckCode\030\001 \001(\t2<\n\010Service1\0220\n\017getSecurity" +
      "Code\022\014.CodeRequest\032\r.CodeResponse\"\000B(\n\020s" +
      "ervice1securityB\022SecurityAccessImplP\001b\006p" +
      "roto3"
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
    internal_static_CodeRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_CodeRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CodeRequest_descriptor,
        new java.lang.String[] { "EnterCode", });
    internal_static_CodeResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_CodeResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CodeResponse_descriptor,
        new java.lang.String[] { "CheckCode", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
