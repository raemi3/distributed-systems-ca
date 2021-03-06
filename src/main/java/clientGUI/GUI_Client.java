package clientGUI;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jmdns.ServiceInfo;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import service1security.Service1Grpc;
import service1security.Service1Grpc.Service1BlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class GUI_Client implements ActionListener {
	
	private JTextField entry1, reply1;
	private JTextField entry2, reply2;
	private JTextField entry3, reply3;
	private JTextField entry4, reply4;

	//Create a stub for service 2
	private service2temperature.Service2Grpc.Service2Stub service2Stub;
	
	public GUI_Client() {
		//pass the channel for service 2 to the stub
		service2Stub = service2temperature.Service2Grpc.newStub(
				ManagedChannelBuilder.forAddress("localhost", 50052).usePlaintext().build());;
	}
	
	private JPanel getService1JPanel() {

		JPanel panel = new JPanel();

		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);

		JLabel label = new JLabel("Enter Security Code: ")	;
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		entry1 = new JTextField("",10);
		panel.add(entry1);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		JButton button = new JButton("Enter");
		button.addActionListener(this);
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		reply1 = new JTextField("", 50);
		reply1 .setEditable(false);
		panel.add(reply1 );

		panel.setLayout(boxlayout);

		return panel;

	}

	private JPanel getService2JPanel() {

		JPanel panel = new JPanel();

		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);

		JLabel label = new JLabel("Heating - ON / OFF: ")	;
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		entry2 = new JTextField("",10);
		panel.add(entry2);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		JButton button = new JButton("Heating");
		button.addActionListener(this);
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		reply2 = new JTextField("", 50);
		reply2 .setEditable(false);
		panel.add(reply2);


		panel.setLayout(boxlayout);

		return panel;

	}
	
	private JPanel getService3JPanel() {

		JPanel panel = new JPanel();

		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);

		JLabel label = new JLabel("AirCon - ON / OFF / TEMP / HIGHER / LOWER")	;
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		entry3 = new JTextField("",10);
		panel.add(entry3);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		JButton button = new JButton("AirCon");
		button.addActionListener(this);
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		reply3 = new JTextField("", 50);
		reply3 .setEditable(false);
		panel.add(reply3 );

		panel.setLayout(boxlayout);

		return panel;

	}

	private JPanel getService4JPanel() {

		JPanel panel = new JPanel();

		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);

		JLabel label = new JLabel("Windows - OPEN / CLOSE ");
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		entry4 = new JTextField("",10);
		panel.add(entry4);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		JButton button = new JButton("Windows");
		button.addActionListener(this);
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		reply4 = new JTextField("", 50);
		reply4 .setEditable(false);
		panel.add(reply4 );

		panel.setLayout(boxlayout);

		return panel;

	}

	public static void main(String[] args) {

		GUI_Client gui = new GUI_Client();
		
		gui.build();
	}
	
	private void build() { 

		JFrame frame = new JFrame("Service Controller Sample");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the panel to add buttons
		JPanel panel = new JPanel();

		// Set the BoxLayout to be X_AXIS: from left to right
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

		panel.setLayout(boxlayout);

		// Set border for the panel
		panel.setBorder(new EmptyBorder(new Insets(100, 100, 100, 100)));
	
		panel.add( getService1JPanel() );
		panel.add( getService2JPanel() );
		panel.add( getService3JPanel() );
		panel.add( getService4JPanel() );

		// Set size for the frame
		frame.setSize(300, 300);

		// Set the window to be visible as the default to be false
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String label = button.getActionCommand();  

		//Service 1 Unary RPC
		if (label.equals("Enter")) {
			System.out.println("Security Access loading...");

			//comment out when running jmDNS
			int port = 50051;
			String host = "localhost";
			
//			//jmDNS calling Service1Discovery
//			ServiceInfo serviceInfo;
//			String service_type = "_grpc._tcp.local.";
//			//Now retrieve the service info - all we are supplying is the service type
//			serviceInfo = service1security.Service1Discovery.run(service_type);
//			//Use the serviceInfo to retrieve the port
//			int port = serviceInfo.getPort();
//			String host = "localhost";
			
			ManagedChannel securityChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
			//Create a stub, pass the channel to the stub
			Service1BlockingStub bstub = Service1Grpc.newBlockingStub(securityChannel);
			
			//preparing message to send
			service1security.CodeRequest request = service1security.CodeRequest.newBuilder().setEnterCode(entry1.getText()).build();
			
			//receive reply from service
			service1security.CodeResponse response = bstub.getSecurityCode(request);
			
			reply1.setText(String.valueOf(response.getCheckCode()));
		
		}
		//Service 2 Server Streaming RPC
		else if (label.equals("Heating")) {
			System.out.println("Heating System loading...");
			
//			//jmDNS calling Service2Discovery
//			ServiceInfo serviceInfo;
//			String service_type = "_grpc2._tcp.local.";
//			//Now retrieve the service info - all we are supplying is the service type
//			serviceInfo = service2temperature.Service2Discovery.run(service_type);
//			//Use the serviceInfo to retrieve the port
//			int port = serviceInfo.getPort();
//			String host = "localhost";
			
			service2temperature.HeatingRequest request = service2temperature.HeatingRequest.newBuilder().setTooHot(entry2.getText()).build();

			StreamObserver<service2temperature.HeatingResponse> responseObserver = new StreamObserver<service2temperature.HeatingResponse>() {

				@Override
				public void onNext(service2temperature.HeatingResponse response) {
					reply2.setText(String.valueOf(response.getTooCold()) );
				}

				@Override
				public void onError(Throwable t) {
					t.printStackTrace();

				}

				@Override
				public void onCompleted() {
				}

			};

			service2Stub.heatingControl(request, responseObserver);
			
		}
		//Service 3 Client Streaming RPC
		else if (label.equals("AirCon")) {
			System.out.println("AirCon System loading...");
			
//			//jmDNS calling Service2Discovery
//			ServiceInfo serviceInfo;
//			String service_type = "_grpc2._tcp.local.";
//			//Now retrieve the service info - all we are supplying is the service type
//			serviceInfo = service2temperature.Service2Discovery.run(service_type);
//			//Use the serviceInfo to retrieve the port
//			int port = serviceInfo.getPort();
//			String host = "localhost";

				StreamObserver<service2temperature.AirConResponse> responseObserver = new StreamObserver<service2temperature.AirConResponse>() {

					@Override
					public void onNext(service2temperature.AirConResponse response) {
						reply3.setText(String.valueOf(response.getWarmingAC()) );
					}

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();
					}

					@Override
					public void onCompleted() {
					}

				};

		
			StreamObserver<service2temperature.AirConRequest> requestObserver = service2Stub.airConControl(responseObserver);
				
			requestObserver.onNext(service2temperature.AirConRequest.newBuilder().setCoolingAC(entry3.getText()).build());
			
			requestObserver.onCompleted();
			
		}
		//Service 4 Bi-streaming RPC
		else if (label.equals("Windows")) {
			System.out.println("Window System loading...");
			
//			//jmDNS calling Service2Discovery
//			ServiceInfo serviceInfo;
//			String service_type = "_grpc2._tcp.local.";
//			//Now retrieve the service info - all we are supplying is the service type
//			serviceInfo = service2temperature.Service2Discovery.run(service_type);
//			//Use the serviceInfo to retrieve the port
//			int port = serviceInfo.getPort();
//			String host = "localhost";

				StreamObserver<service2temperature.WindowsResponse> responseObserver = new StreamObserver<service2temperature.WindowsResponse>() {

					@Override
					public void onNext(service2temperature.WindowsResponse response) {
					reply4.setText(String.valueOf(response.getCloseWindow()) );
					}

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();
					} 

					@Override
						public void onCompleted() {
					}

				};


			StreamObserver<service2temperature.WindowsRequest> requestObserver = service2Stub.windowControl(responseObserver);

				try {
					requestObserver.onNext(service2temperature.WindowsRequest.newBuilder().setOpenWindow(entry4.getText()).build());
							
					//Mark the end of requests
					requestObserver.onCompleted();
				} 
				
				catch (RuntimeException err) {
					err.printStackTrace();
						System.out.println(err);
				}
			
		}
		else {
		}
			
	}//actionPerformed end
		
}//class