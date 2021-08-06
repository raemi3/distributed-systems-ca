//package clientGUI;
//
//import java.awt.Dimension;
//import java.awt.Insets;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.concurrent.TimeUnit;
//
//import javax.swing.Box;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//
//import service1security.CodeRequest;
//import service1security.CodeResponse;
//import service1security.Service1Grpc;
//import service1security.Service1Grpc.Service1BlockingStub;
//import service2temperature.AirConRequest;
//import service2temperature.AirConResponse;
//import service2temperature.HeatingRequest;
//import service2temperature.HeatingResponse;
//import service2temperature.Service2Grpc;
//import service2temperature.WindowsRequest;
//import service2temperature.WindowsResponse;
//import service2temperature.Service2Grpc.Service2Stub;
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//import io.grpc.stub.StreamObserver;
//
//public class GUI_Client implements ActionListener {
//	
//	private JTextField entry1, reply1;
//	private JTextField entry2, reply2;
//
//	private JPanel getService1JPanel() {
//
//		JPanel panel = new JPanel();
//
//		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
//
//		JLabel label = new JLabel("Enter Security Code: ")	;
//		panel.add(label);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
//		entry1 = new JTextField("",10);
//		panel.add(entry1);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
//
//		JButton button = new JButton("Invoke Service 1");
//		button.addActionListener(this);
//		panel.add(button);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
//
//		reply1 = new JTextField("", 10);
//		reply1 .setEditable(false);
//		panel.add(reply1 );
//
//		panel.setLayout(boxlayout);
//
//		return panel;
//
//	}
//
//	private JPanel getService2JPanel() {
//
//		JPanel panel = new JPanel();
//
//		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
//
//		JLabel label = new JLabel("Enter Service Wanted: ")	;
//		panel.add(label);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
//		entry2 = new JTextField("",10);
//		panel.add(entry2);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
//
//		JButton button = new JButton("Invoke Service 2");
//		button.addActionListener(this);
//		panel.add(button);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
//
//		reply2 = new JTextField("", 10);
//		reply2 .setEditable(false);
//		panel.add(reply2 );
//
//		panel.setLayout(boxlayout);
//
//		return panel;
//
//	}
//
//	public static void main(String[] args) {
//
//		GUI_Client gui = new GUI_Client();
//		
//		gui.build();
//	}
//	
//	private void build() { 
//
//		JFrame frame = new JFrame("Service Controller Sample");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		// Set the panel to add buttons
//		JPanel panel = new JPanel();
//
//		// Set the BoxLayout to be X_AXIS: from left to right
//		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
//
//		panel.setLayout(boxlayout);
//
//		// Set border for the panel
//		panel.setBorder(new EmptyBorder(new Insets(50, 100, 50, 100)));
//	
//		panel.add( getService1JPanel() );
//		panel.add( getService2JPanel() );
//
//		// Set size for the frame
//		frame.setSize(300, 300);
//
//		// Set the window to be visible as the default to be false
//		frame.add(panel);
//		frame.pack();
//		frame.setVisible(true);
//	}
//	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		JButton button = (JButton)e.getSource();
//		String label = button.getActionCommand();  
//
//		if (label.equals("Invoke Service 1")) {
//			System.out.println("service 1 to be invoked ...");
//
//			int port = 50051;
//			String host = "localhost";
//			
//			ManagedChannel securityChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
//			//Create a stub, pass the channel to the stub
//			Service1BlockingStub bstub = Service1Grpc.newBlockingStub(securityChannel);
//
//
//			//preparing message to send
//			service1security.CodeRequest request = service1security.CodeRequest.newBuilder().setEnterCode(entry1.getEnterCode()).build();
//
//			//retreaving reply from service
//			service1security.CodeResponse response = bstub.getSecurityCode(request);
//		
//		}else if (label.equals("Invoke Service 2")) {
//			System.out.println("service 2 to be invoked ...");
//			
//			//Build a channel
//			int port = 50052;
//			String host = "localhost";
//			
//			ManagedChannel temperatureChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
//			
//			//Create a stub, pass the channel to the stub
//			//Service2BlockingStub bstub = Service2Grpc.newBlockingStub(temperatureChannel);
//			Service2Stub cstub = Service2Grpc.newStub(temperatureChannel);
//
//			//SERVER STREAMING HEATING CONTROL
//			StreamObserver<HeatingResponse> responseObserverHC = new StreamObserver<HeatingResponse>() {
//				
//					@Override
//					public void onNext(HeatingResponse value) {
//						System.out.println("Response: " + value.getTooCold());
//						
//					}
//		
//					@Override
//					public void onError(Throwable t) {
//						System.out.println("Error In Client Streaming Server");
//						
//					}
//		
//					@Override
//					public void onCompleted() {
//						System.out.println("Completed");
//						
//					}
//					
//				};//return statement
//			
//			//use StreamObserver & requestObserver to send our outgoing messages
//			StreamObserver<HeatingRequest> requestObserverHC = cstub.heatingControl(responseObserverHC);
//
//			requestObserverHC.onNext(HeatingRequest.newBuilder().setTooHot("Test message").build());
//			
//			System.out.println("SENDING MESSAGES: " );
//			requestObserverHC.onCompleted();
//			
//			
//			//CLIENT STREAMING AIR CON CONTROL
//			// For incoming messages we need to implement a StreamObserver
//			StreamObserver<AirConRequest> responseObserverAC = new StreamObserver<AirConRequest>() {
//
//					@Override
//					public void onNext(AirConRequest value) {
//						System.out.println("Response: " +value.getCoolingAC());
//					}
//
//					@Override
//					public void onError(Throwable t) {
//
//					}
//
//					@Override
//					public void onCompleted() {
//						System.out.println("Completed");
//
//					}
//
//				};
//			
//			//use StreamObserver & requestObserver to send our outgoing messages
//			StreamObserver<AirConResponse> requestObserverAC = cstub.airConControl(responseObserverAC);
//
//			requestObserverAC.onNext(AirConResponse.newBuilder().setWarmingAC("First message").build());
//			requestObserverAC.onNext(AirConResponse.newBuilder().setWarmingAC("Second message").build());
//			
//			System.out.println("SENDING MESSAGES: " );
//			requestObserverAC.onCompleted();
//			
//			Thread.sleep(5000);
//			
//			//BISTREAMING WINDOW CONTROL
//			// For incoming messages we need to implement a StreamObserver
//			StreamObserver<WindowsRequest> responseObserverWC = new StreamObserver<WindowsRequest> () {
//
//					@Override
//					public void onNext(WindowsRequest value) {
//						System.out.println("Response: " + value.getOpenWindow());
//					}
//		
//					@Override
//					public void onError(Throwable t) {
//						
//					}
//		
//					@Override
//					public void onCompleted() {
//						System.out.println("Completed");
//						
//					}
//			
//				};
//				
//			//use StreamObserver & requestObserver to send our outgoing messages
//			StreamObserver<WindowsResponse> requestObserverWC = cstub.windowControl(responseObserverWC);
//
//			requestObserverWC.onNext(WindowsResponse.newBuilder().setCloseWindow("First message").build());
//			requestObserverWC.onNext(WindowsResponse.newBuilder().setCloseWindow("Second message").build());
//			requestObserverWC.onNext(WindowsResponse.newBuilder().setCloseWindow("Third message").build());
//				
//			System.out.println("SENDING MESSAGES: " );
//			requestObserverAC.onCompleted();
//				
//			Thread.sleep(5000);
//		
//			/*
//			 * 
//			 */
//
//			//preparing message to send
//			service2temperature.RequestMessage request = ds.service2.RequestMessage.newBuilder().setText(entry2.getText()).build();
//
//			//retreving reply from service
//			service2temperature.ResponseMessage response = blockingStub.service2Do(request);
//
//			reply2.setText( String.valueOf( response.getLength()) );
//			
//		}
//		else {
//			
//		}
//	}
//
//}//class
