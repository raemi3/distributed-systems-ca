package service2temperature;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: service2temperature.proto")
public final class Service2Grpc {

  private Service2Grpc() {}

  public static final String SERVICE_NAME = "Service2";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<service2temperature.HeatingRequest,
      service2temperature.HeatingResponse> getHeatingControlMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HeatingControl",
      requestType = service2temperature.HeatingRequest.class,
      responseType = service2temperature.HeatingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<service2temperature.HeatingRequest,
      service2temperature.HeatingResponse> getHeatingControlMethod() {
    io.grpc.MethodDescriptor<service2temperature.HeatingRequest, service2temperature.HeatingResponse> getHeatingControlMethod;
    if ((getHeatingControlMethod = Service2Grpc.getHeatingControlMethod) == null) {
      synchronized (Service2Grpc.class) {
        if ((getHeatingControlMethod = Service2Grpc.getHeatingControlMethod) == null) {
          Service2Grpc.getHeatingControlMethod = getHeatingControlMethod = 
              io.grpc.MethodDescriptor.<service2temperature.HeatingRequest, service2temperature.HeatingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "Service2", "HeatingControl"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service2temperature.HeatingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service2temperature.HeatingResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new Service2MethodDescriptorSupplier("HeatingControl"))
                  .build();
          }
        }
     }
     return getHeatingControlMethod;
  }

  private static volatile io.grpc.MethodDescriptor<service2temperature.AirConRequest,
      service2temperature.AirConResponse> getAirConControlMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AirConControl",
      requestType = service2temperature.AirConRequest.class,
      responseType = service2temperature.AirConResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<service2temperature.AirConRequest,
      service2temperature.AirConResponse> getAirConControlMethod() {
    io.grpc.MethodDescriptor<service2temperature.AirConRequest, service2temperature.AirConResponse> getAirConControlMethod;
    if ((getAirConControlMethod = Service2Grpc.getAirConControlMethod) == null) {
      synchronized (Service2Grpc.class) {
        if ((getAirConControlMethod = Service2Grpc.getAirConControlMethod) == null) {
          Service2Grpc.getAirConControlMethod = getAirConControlMethod = 
              io.grpc.MethodDescriptor.<service2temperature.AirConRequest, service2temperature.AirConResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "Service2", "AirConControl"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service2temperature.AirConRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service2temperature.AirConResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new Service2MethodDescriptorSupplier("AirConControl"))
                  .build();
          }
        }
     }
     return getAirConControlMethod;
  }

  private static volatile io.grpc.MethodDescriptor<service2temperature.WindowsRequest,
      service2temperature.WindowsResponse> getWindowControlMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WindowControl",
      requestType = service2temperature.WindowsRequest.class,
      responseType = service2temperature.WindowsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<service2temperature.WindowsRequest,
      service2temperature.WindowsResponse> getWindowControlMethod() {
    io.grpc.MethodDescriptor<service2temperature.WindowsRequest, service2temperature.WindowsResponse> getWindowControlMethod;
    if ((getWindowControlMethod = Service2Grpc.getWindowControlMethod) == null) {
      synchronized (Service2Grpc.class) {
        if ((getWindowControlMethod = Service2Grpc.getWindowControlMethod) == null) {
          Service2Grpc.getWindowControlMethod = getWindowControlMethod = 
              io.grpc.MethodDescriptor.<service2temperature.WindowsRequest, service2temperature.WindowsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "Service2", "WindowControl"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service2temperature.WindowsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service2temperature.WindowsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new Service2MethodDescriptorSupplier("WindowControl"))
                  .build();
          }
        }
     }
     return getWindowControlMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static Service2Stub newStub(io.grpc.Channel channel) {
    return new Service2Stub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static Service2BlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new Service2BlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static Service2FutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new Service2FutureStub(channel);
  }

  /**
   */
  public static abstract class Service2ImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *server streaming
     * </pre>
     */
    public void heatingControl(service2temperature.HeatingRequest request,
        io.grpc.stub.StreamObserver<service2temperature.HeatingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHeatingControlMethod(), responseObserver);
    }

    /**
     * <pre>
     *client streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<service2temperature.AirConRequest> airConControl(
        io.grpc.stub.StreamObserver<service2temperature.AirConResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getAirConControlMethod(), responseObserver);
    }

    /**
     * <pre>
     *bisteaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<service2temperature.WindowsRequest> windowControl(
        io.grpc.stub.StreamObserver<service2temperature.WindowsResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getWindowControlMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getHeatingControlMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                service2temperature.HeatingRequest,
                service2temperature.HeatingResponse>(
                  this, METHODID_HEATING_CONTROL)))
          .addMethod(
            getAirConControlMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                service2temperature.AirConRequest,
                service2temperature.AirConResponse>(
                  this, METHODID_AIR_CON_CONTROL)))
          .addMethod(
            getWindowControlMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                service2temperature.WindowsRequest,
                service2temperature.WindowsResponse>(
                  this, METHODID_WINDOW_CONTROL)))
          .build();
    }
  }

  /**
   */
  public static final class Service2Stub extends io.grpc.stub.AbstractStub<Service2Stub> {
    private Service2Stub(io.grpc.Channel channel) {
      super(channel);
    }

    private Service2Stub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Service2Stub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new Service2Stub(channel, callOptions);
    }

    /**
     * <pre>
     *server streaming
     * </pre>
     */
    public void heatingControl(service2temperature.HeatingRequest request,
        io.grpc.stub.StreamObserver<service2temperature.HeatingResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getHeatingControlMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *client streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<service2temperature.AirConRequest> airConControl(
        io.grpc.stub.StreamObserver<service2temperature.AirConResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getAirConControlMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *bisteaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<service2temperature.WindowsRequest> windowControl(
        io.grpc.stub.StreamObserver<service2temperature.WindowsResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getWindowControlMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class Service2BlockingStub extends io.grpc.stub.AbstractStub<Service2BlockingStub> {
    private Service2BlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private Service2BlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Service2BlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new Service2BlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *server streaming
     * </pre>
     */
    public java.util.Iterator<service2temperature.HeatingResponse> heatingControl(
        service2temperature.HeatingRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getHeatingControlMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class Service2FutureStub extends io.grpc.stub.AbstractStub<Service2FutureStub> {
    private Service2FutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private Service2FutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Service2FutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new Service2FutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_HEATING_CONTROL = 0;
  private static final int METHODID_AIR_CON_CONTROL = 1;
  private static final int METHODID_WINDOW_CONTROL = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final Service2ImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(Service2ImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HEATING_CONTROL:
          serviceImpl.heatingControl((service2temperature.HeatingRequest) request,
              (io.grpc.stub.StreamObserver<service2temperature.HeatingResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_AIR_CON_CONTROL:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.airConControl(
              (io.grpc.stub.StreamObserver<service2temperature.AirConResponse>) responseObserver);
        case METHODID_WINDOW_CONTROL:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.windowControl(
              (io.grpc.stub.StreamObserver<service2temperature.WindowsResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class Service2BaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    Service2BaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return service2temperature.TemperatureControlImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Service2");
    }
  }

  private static final class Service2FileDescriptorSupplier
      extends Service2BaseDescriptorSupplier {
    Service2FileDescriptorSupplier() {}
  }

  private static final class Service2MethodDescriptorSupplier
      extends Service2BaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    Service2MethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (Service2Grpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new Service2FileDescriptorSupplier())
              .addMethod(getHeatingControlMethod())
              .addMethod(getAirConControlMethod())
              .addMethod(getWindowControlMethod())
              .build();
        }
      }
    }
    return result;
  }
}
