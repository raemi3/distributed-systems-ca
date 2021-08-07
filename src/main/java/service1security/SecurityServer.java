package service1security;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import service1security.Service1Grpc.Service1ImplBase;

public class SecurityServer {
	
	private Server server;

	public static void main(String[] args) throws IOException, InterruptedException {

		final SecurityServer ourServer = new SecurityServer();
		ourServer.start();
		
	}

	private void start() throws IOException, InterruptedException {

		System.out.println("Starting gRPC Security Server");
		
		int port = 50051;
		
//		//jmDNS calling Service1Registration
//		String service_type = "_grpc1._tcp.local.";
//		String service_name = "GrpcServer";
//		Service1Registration s1r = new Service1Registration();
//		s1r.run(port, service_type, service_name);
		
		server = ServerBuilder.forPort(port).addService(new SecurityServerImpl()).build().start();
		
		System.out.println("Server Running on Port: " + port);
		
		server.awaitTermination();
	}
	
	static class SecurityServerImpl extends Service1ImplBase {
		
		@Override
		public void getSecurityCode(CodeRequest request, StreamObserver<CodeResponse> responseObserver) {
			
			//find out what the client sent
			String enterCode = request.getEnterCode();
			System.out.println("Code Entered : " + enterCode);
			
			//String text = enterCode;
		    //int code = Integer.parseInt(text);
			//orginally parsed String into an int to validate codes entered as JTextField doesn't accept int for GUI
			//however this caused errors when words were entered as they could not be parsed to ints so stuck with String
			
			//only 9 codes allow access
			if (enterCode.equals("11111111") || enterCode.equals("22222222") || enterCode.equals("33333333") 
				|| enterCode.equals("44444444") || enterCode.equals("55555555") || enterCode.equals("66666666")
					|| enterCode.equals("77777777") || enterCode.equals("88888888") || enterCode.equals("99999999")) {
			
				//build our response
				CodeResponse.Builder response = CodeResponse.newBuilder();
				
				response.setCheckCode("Access Granted");
				
				//send out message
				responseObserver.onNext(response.build());
				
				responseObserver.onCompleted();
			}
			else {
				
				//build our response
				CodeResponse.Builder response = CodeResponse.newBuilder();
				
				response.setCheckCode("Access Denied");
				
				//send out message
				responseObserver.onNext(response.build());
				
				responseObserver.onCompleted();
			}
			
		}
		
	}

}//class
