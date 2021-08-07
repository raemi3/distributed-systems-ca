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
//		}
//		catch (RuntimeException err1) {
//				err1.printStackTrace();
//					System.out.println("Server Stream: " + err1);
//		}
//		
////		//POSSIBLY NOT NEED WHEN USING GUI FOR INPUT
////		//use StreamObserver & requestObserver to send our outgoing messages
////		StreamObserver<HeatingRequest> requestObserverHC = cstub.heatingControl(responseObserverHC);
////
////		requestObserverHC.onNext(HeatingRequest.newBuilder().setTooHot("Test message").build());
////		
////		System.out.println("SENDING MESSAGES: " );
////		requestObserverHC.onCompleted();
//		
//	try {
//		//CLIENT STREAMING AIR CON CONTROL
//		// For incoming messages we need to implement a StreamObserver
//		StreamObserver<AirConResponse> responseObserverAC = new StreamObserver<AirConResponse>() {
//
//				@Override
//				public void onNext(AirConResponse value) {
//					System.out.println("Response: " + value.getWarmingAC());
//					//reply3.setWarmingAC(String.valueOf(value.getWarmingAC()) );
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
//		StreamObserver<AirConRequest> requestObserverAC = cstub.airConControl(responseObserverAC);
//
//		System.out.println("SENDING MESSAGES: " );
//
//		requestObserverAC.onNext(AirConRequest.newBuilder().setCoolingAC("First message").build());
//		requestObserverAC.onNext(AirConRequest.newBuilder().setCoolingAC("Second message").build());
//		
//		requestObserverAC.onCompleted();
//		
//		//Thread.sleep(5000);
//	}
//	catch (RuntimeException err2) {
//		err2.printStackTrace();
//		System.out.println("Client Stream: " + err2);
//	}
//	
//	try {
//		//BISTREAMING WINDOW CONTROL
//		// For incoming messages we need to implement a StreamObserver
//		StreamObserver<WindowsResponse> responseObserverWC = new StreamObserver<WindowsResponse> () {
//
//				@Override
//				public void onNext(WindowsResponse value) {
//					System.out.println("Response: " + value.getCloseWindow());
//					//reply4.setCloseWindow(String.valueOf(value.getCloseWindow()) );
//				}
//	
//				@Override
//				public void onError(Throwable t) {
//					t.printStackTrace();
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
//		StreamObserver<WindowsRequest> requestObserverWC = cstub.windowControl(responseObserverWC);
//
//		requestObserverWC.onNext(WindowsRequest.newBuilder().setOpenWindow("First message").build());
//		requestObserverWC.onNext(WindowsRequest.newBuilder().setOpenWindow("Second message").build());
//		requestObserverWC.onNext(WindowsRequest.newBuilder().setOpenWindow("Third message").build());
//			
//		System.out.println("SENDING MESSAGES: " );
//		requestObserverWC.onCompleted();
//
//		//Thread.sleep(5000);
//		
//	}
//	catch (RuntimeException err3) {
//				err3.printStackTrace();
//					System.out.println("Bi Stream: " + err3);
//	}
//
////	catch (StatusRuntimeException e) {
////		System.out.print(e.getMessage());
////		return;
////	} 
////	finally {
////		//shutdown channel
////		temperatureChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
////	}
//	
//
//	}//main
//
//}//class