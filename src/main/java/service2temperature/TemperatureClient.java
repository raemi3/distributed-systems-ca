//package service2temperature;
//
//import java.util.concurrent.TimeUnit;
//
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//import io.grpc.StatusRuntimeException;
//import io.grpc.stub.StreamObserver;
//import service2temperature.Service2Grpc.Service2BlockingStub;
//import service2temperature.Service2Grpc.Service2Stub;
//
//public class TemperatureClient {
//
//	public static void main(String[] args) throws Exception {
//		
//		//Build a channel
//		int port = 50052;
//		String host = "localhost";
//		
//		ManagedChannel temperatureChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
//		
//		//Create a stub, pass the channel to the stub
//		//Service2BlockingStub bstub = Service2Grpc.newBlockingStub(temperatureChannel);
//		Service2Stub cstub = Service2Grpc.newStub(temperatureChannel);
//		
//		
//	try{
//		
//		//SERVER STREAMING HEATING CONTROL
//		StreamObserver<HeatingResponse> responseObserverHC = new StreamObserver<HeatingResponse>() {
//			
//				@Override
//				public void onNext(HeatingResponse value) {
//					System.out.println("Response: " + value.getTooCold());
//					
//				}
//	
//				@Override
//				public void onError(Throwable t) {
//					System.out.println("Error In Client Streaming Server");
//					
//				}
//	
//				@Override
//				public void onCompleted() {
//					System.out.println("Completed");
//					
//				}
//				
//			};//return statement
//		
//		//use StreamObserver & requestObserver to send our outgoing messages
//		StreamObserver<HeatingRequest> requestObserverHC = cstub.heatingControl(responseObserverHC);
//
//		requestObserverHC.onNext(HeatingRequest.newBuilder().setTooHot("Test message").build());
//		
//		System.out.println("SENDING MESSAGES: " );
//		requestObserverHC.onCompleted();
//		
//		
//		//CLIENT STREAMING AIR CON CONTROL
//		// For incoming messages we need to implement a StreamObserver
//		StreamObserver<AirConRequest> responseObserverAC = new StreamObserver<AirConRequest>() {
//
//				@Override
//				public void onNext(AirConRequest value) {
//					System.out.println("Response: " +value.getCoolingAC());
//				}
//
//				@Override
//				public void onError(Throwable t) {
//
//				}
//
//				@Override
//				public void onCompleted() {
//					System.out.println("Completed");
//
//				}
//
//			};
//		
//		//use StreamObserver & requestObserver to send our outgoing messages
//		StreamObserver<AirConResponse> requestObserverAC = cstub.airConControl(responseObserverAC);
//
//		requestObserverAC.onNext(AirConResponse.newBuilder().setWarmingAC("First message").build());
//		requestObserverAC.onNext(AirConResponse.newBuilder().setWarmingAC("Second message").build());
//		
//		System.out.println("SENDING MESSAGES: " );
//		requestObserverAC.onCompleted();
//		
//		Thread.sleep(5000);
//		
//		//BISTREAMING WINDOW CONTROL
//		// For incoming messages we need to implement a StreamObserver
//		StreamObserver<WindowsRequest> responseObserverWC = new StreamObserver<WindowsRequest> () {
//
//				@Override
//				public void onNext(WindowsRequest value) {
//					System.out.println("Response: " + value.getOpenWindow());
//				}
//	
//				@Override
//				public void onError(Throwable t) {
//					
//				}
//	
//				@Override
//				public void onCompleted() {
//					System.out.println("Completed");
//					
//				}
//		
//			};
//			
//		//use StreamObserver & requestObserver to send our outgoing messages
//		StreamObserver<WindowsResponse> requestObserverWC = cstub.windowControl(responseObserverWC);
//
//		requestObserverWC.onNext(WindowsResponse.newBuilder().setCloseWindow("First message").build());
//		requestObserverWC.onNext(WindowsResponse.newBuilder().setCloseWindow("Second message").build());
//		requestObserverWC.onNext(WindowsResponse.newBuilder().setCloseWindow("Third message").build());
//			
//		System.out.println("SENDING MESSAGES: " );
//		requestObserverAC.onCompleted();
//			
//		Thread.sleep(5000);
//			
//	}//try end
//		
//	catch (StatusRuntimeException e) {
//		System.out.print(e.getMessage());
//		return;
//	} 
//	finally {
//		//shutdown channel
//		temperatureChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
//	}
//	
//
//	}//main
//
//}//class