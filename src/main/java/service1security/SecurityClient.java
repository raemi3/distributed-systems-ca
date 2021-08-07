//package service1security;
//
//import java.util.concurrent.TimeUnit;
//
////import javax.jmdns.ServiceInfo;
//
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//import io.grpc.StatusRuntimeException;
//import service1security.Service1Grpc.Service1BlockingStub;
//
//public class SecurityClient {
//
//	public static void main(String[] args) throws InterruptedException {
//
//		//Build a channel
//		int port = 50051;
//		
//		//jmDNS calling Service1Discovery
//		//ServiceInfo serviceInfo;
//		//String service_type = "_grpc1._tcp.local.";
//		//Now retrieve the service info - all we are supplying is the service type
//		//serviceInfo = Service1Discovery.run(service_type);
//		//Use the serviceInfo to retrieve the port
//		//int port = serviceInfo.getPort();
//		String host = "localhost";
//		
//		
//		ManagedChannel securityChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
//		
//		//Create a stub, pass the channel to the stub
//		Service1BlockingStub bstub = Service1Grpc.newBlockingStub(securityChannel);
//		
//	try {
//		
//			//Now build our message
//			CodeRequest cString = CodeRequest.newBuilder().setEnterCode(
//					"Security Code - A1B2C3D4 - has been entered").build();
//			
//			CodeResponse response = bstub.getSecurityCode(cString);
//			
//			System.out.println(response.getCheckCode());
//			
//			securityChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
//		}
//		catch (StatusRuntimeException e) {
//				System.out.print(e.getMessage());
//				return;
//		} 
//		finally {
//				//shutdown channel
//				securityChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
//	    }
//	
//	}//main
//
//}//class