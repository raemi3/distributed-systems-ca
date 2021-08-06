package service2temperature;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import service2temperature.Service2Grpc.Service2ImplBase;

public class TemperatureServer {
	
	private Server server;

	public static void main(String[] args) throws IOException, InterruptedException {
		
		final TemperatureServer ourServer = new TemperatureServer();
		ourServer.start();

	}//main
	
	private void start() throws IOException, InterruptedException {

		System.out.println("Starting gRPC Temperature Server");
		
		int port = 50052;
		server = ServerBuilder.forPort(port).addService(new TemperatureServerImpl()).build().start();
		
		System.out.println("Server Running on Port: " + port);
		
		server.awaitTermination();
	}
	
	//extend base class for our implementation
	static class TemperatureServerImpl extends Service2ImplBase {
		
		//SERVER streaming
		@Override
		public void heatingControl(HeatingRequest request, StreamObserver<HeatingResponse> responseObserver) {
			
			//find out what the client sent
			String tooHot = request.getTooHot();
			System.out.println("Heating : " + tooHot);
			
			//build our response
			HeatingResponse.Builder response = HeatingResponse.newBuilder();
			
			//send out messages
			response.setTooCold("Activating Heating " + tooHot);
			responseObserver.onNext(response.build());
			
			response.setTooCold("Heating has been turned on at 18 degrees celsius");
			responseObserver.onNext(response.build());
			
			response.setTooCold("Heating will turn off once room temperature hits 21 degrees celsius");
			responseObserver.onNext(response.build());
			
			responseObserver.onCompleted();
			
		}//server streaming end

		
		//CLIENT streaming
		@Override
		public StreamObserver<AirConRequest> airConControl(StreamObserver<AirConResponse> responseObserver) {
		
			System.out.println("Inside Client Streaming Server");
			return new StreamObserver<AirConRequest>() {

				@Override
				public void onNext(AirConRequest value) {
					System.out.println("Message Received: " + value.getCoolingAC());
					
				}

				@Override
				public void onError(Throwable t) {
					
				}

				@Override
				public void onCompleted() {
					
					AirConResponse.Builder acBuilder = AirConResponse.newBuilder();
					
					//message from server back to client
					acBuilder.setWarmingAC("Air Conditioning has been set to 16 degrees celsius");
					
					responseObserver.onNext(acBuilder.build());
					
					responseObserver.onCompleted();
				}
			};//return statement
		}//client streaming end
	
//		//BISTREAMING
//		@Override
//		public StreamObserver<WindowsRequest> windowControl(StreamObserver<WindowsResponse> responseObserver) {
//			return new StreamObserver<WindowsRequest>() {
//
//				@Override
//				public void onNext(WindowsRequest value) {
//					
//					
//				}
//
//				@Override
//				public void onError(Throwable t) {
//					
//					
//				}
//
//				@Override
//				public void onCompleted() {
//					
//					
//				}
//			};
//	    }//bistreaming end

	}//static class
	
}//class