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
		
		//jmDNS calling Service1Registration
		String service_type = "_grpc1._tcp.local.";
		String service_name = "GrpcServer";
		Service1Registration s1r = new Service1Registration();
		s1r.run(port, service_type, service_name);
		
		
		server = ServerBuilder.forPort(port).addService(new SecurityServerImpl()).build().start();
		
		System.out.println("Server Running on Port: " + port);
		
		server.awaitTermination();
	}
	
	static class SecurityServerImpl extends Service1ImplBase {
		
		@Override
		public void getSecurityCode(CodeRequest request, StreamObserver<CodeResponse> responseObserver) {
			
			//find out what the client sent
			String enterCode = request.getEnterCode();
			System.out.println("Enter Code : " + enterCode);
			
			//build our response
			CodeResponse.Builder response = CodeResponse.newBuilder();
			
			response.setCheckCode("Test " + enterCode);
			
			//send out message
			responseObserver.onNext(response.build());
			
			responseObserver.onCompleted();
			
		}
		
	}

}
