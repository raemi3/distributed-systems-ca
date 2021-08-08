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
		
//		//jmDNS calling Service2Registration
//		String service_type = "_grpc2._tcp.local.";
//		String service_name = "GrpcServer";
//		Service2Registration s2r = new Service2Registration();
//		s2r.run(port, service_type, service_name);
		
		server = ServerBuilder.forPort(port).addService(new TemperatureServerImpl()).build().start();
		
		System.out.println("Server Running on Port: " + port);
		
		server.awaitTermination();
	}
	
	//extend base class for our implementation
	static class TemperatureServerImpl extends Service2ImplBase {
		
		//SERVER streaming
		@Override
		public void heatingControl(HeatingRequest request, StreamObserver<HeatingResponse> responseObserver) {
			
			System.out.println("Server Streaming Server");
			
			//find out what the client sent
			String tooHot = request.getTooHot();
			System.out.println("Heating : " + tooHot);
			
			//build our response
			HeatingResponse.Builder response = HeatingResponse.newBuilder();
			
				if(tooHot.equalsIgnoreCase("on")) {
					//send out messages
					response.setTooCold("Activating Heating");
					responseObserver.onNext(response.build());
					
					response.setTooCold("Heating has been turned on at 18 degrees celsius");
					responseObserver.onNext(response.build());
					
					response.setTooCold("Heating will turn off once room temperature hits 21 degrees celsius");
					responseObserver.onNext(response.build());
				}
				else if(tooHot.equalsIgnoreCase("off")) {
	
					response.setTooCold("De-activating Heating");
					responseObserver.onNext(response.build());
					
					response.setTooCold("Heating has been turned off");
					responseObserver.onNext(response.build());
					
					response.setTooCold("Heating will turn on once room temperature hits 17 degrees celsius");
					responseObserver.onNext(response.build());
				}
				else {
					
					response.setTooCold("Invalid Input - Try Again");
					responseObserver.onNext(response.build());
				}
			
			responseObserver.onCompleted();
			
		}//server streaming end

		
		//CLIENT streaming
		@Override
		public StreamObserver<AirConRequest> airConControl(StreamObserver<AirConResponse> responseObserver) {
			
			System.out.println("Client Streaming Server");
			return new StreamObserver<AirConRequest>() {
				
				@Override
				public void onNext(AirConRequest request) {
					
					//find out what the client sent
					String coolingAC = request.getCoolingAC();
					System.out.println("Heating : " + coolingAC);
					
					if(coolingAC.equalsIgnoreCase("on")) {
						//Server Response
						AirConResponse reply = AirConResponse.newBuilder().setWarmingAC("AirCon Activated").build();
						
						responseObserver.onNext(reply);
						
						responseObserver.onCompleted();
					}
					else if(coolingAC.equalsIgnoreCase("off")) {
						
						//Server Response
						AirConResponse reply = AirConResponse.newBuilder().setWarmingAC("AirCon Deactivated").build();
						
						responseObserver.onNext(reply);
						
						responseObserver.onCompleted();
					}
					else if(coolingAC.equalsIgnoreCase("temp")) {
						
						//Server Response
						AirConResponse reply = AirConResponse.newBuilder().setWarmingAC("AirCon Temperature 16C").build();
						
						responseObserver.onNext(reply);
						
						responseObserver.onCompleted();
					}
					else if(coolingAC.equalsIgnoreCase("higher")) {
						
						//Server Response
						AirConResponse reply = AirConResponse.newBuilder().setWarmingAC("AirCon Temperature 20C").build();
						
						responseObserver.onNext(reply);
						
						responseObserver.onCompleted();
					}
					else if(coolingAC.equalsIgnoreCase("lower")) {
						
						//Server Response
						AirConResponse reply = AirConResponse.newBuilder().setWarmingAC("AirCon Temperature 12C").build();
						
						responseObserver.onNext(reply);
						
						responseObserver.onCompleted();
					}
					else {
						
						//Server Response
						AirConResponse reply = AirConResponse.newBuilder().setWarmingAC("Invalid Input - Try Again").build();
						
						responseObserver.onNext(reply);
						
						responseObserver.onCompleted();
					}
				}

				@Override
				public void onError(Throwable t) {
					
				}

				@Override
				public void onCompleted() {
					
				}
			};//return statement
		}//client streaming end
	
		//BISTREAMING
		@Override
		public StreamObserver<WindowsRequest> windowControl(StreamObserver<WindowsResponse> responseObserver) {
			
			System.out.println("Bi-Streaming Server");
			return new StreamObserver<WindowsRequest>() {

				@Override
				public void onNext(WindowsRequest value) {
					
					//find out what the client sent
					String openWindow = value.getOpenWindow();
					System.out.println(value.getOpenWindow());
					
					//String text = openWindow;
				    //int code = Integer.parseInt(text);
					//parsed String into an int to validate codes entered as JTextField doesn't accept int for GUI
					//this allowed the client to enter the room temperature to decide window settings
					//however this caused errors when words were entered as they could not be parsed to ints so stuck with 
					//String open & close
					
					if(openWindow.equalsIgnoreCase("open")) {
						
						WindowsResponse reply = WindowsResponse.newBuilder().setCloseWindow("Windows Opening - Auto-Close at 17C").build();
						
						responseObserver.onNext(reply);
					}
					else if(openWindow.equalsIgnoreCase("close")) {
		
						WindowsResponse reply = WindowsResponse.newBuilder().setCloseWindow("Windows Closing - Auto-Open at 24C").build();
			
						responseObserver.onNext(reply);
					}
					else {
						
						WindowsResponse reply = WindowsResponse.newBuilder().setCloseWindow("Invalid Input - Try Again").build();
						
						responseObserver.onNext(reply);
					}
					 
				}

				@Override
				public void onError(Throwable t) {
					t.printStackTrace();	
				}

				@Override
				public void onCompleted() {
						responseObserver.onCompleted();
				}
			};//return statement
	    }//bistreaming end

	}//static class
	
}//class