package application;

import javafx.scene.control.Button;
import  javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ClientGUI extends Application {
	
	Stage mainStage;
	Scene initialScene, connectedScene;
	ClientSocket csocket;
	
	
	@Override
    public void start(Stage primaryStage) {
		
		mainStage = primaryStage;		
        mainStage.setTitle("JavaFX Welcome");
        
    /*******************************************************************/
        // Unix command initial Scene 
        // The display shows two fields to enter server url and port no
        
        GridPane initialGrid = new GridPane();
        
        initialGrid.setAlignment(Pos.CENTER);
        initialGrid.setHgap(10);
        initialGrid.setVgap(10);
        initialGrid.setPadding(new Insets(25, 25, 25, 25));
        
        Text initialSceneTitle = new Text("Welcome To TCP Client Program");
        initialSceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        initialGrid.add(initialSceneTitle, 0, 0, 2, 1);
        
        
        Label addressLabel = new Label("Server Address:");
        initialGrid.add(addressLabel, 0, 1 );
        

        TextField address = new TextField();
        initialGrid.add(address, 1, 1, 2 , 1 );

        Label portLabel = new Label("Port:");
        initialGrid.add(portLabel, 0, 2);

        TextField port = new TextField();
        initialGrid.add(port, 1, 2 , 2, 1);
        
        Button connectBtn = new Button("Connect");
        HBox hbConnectBtn = new HBox(10);
        hbConnectBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbConnectBtn.getChildren().add(connectBtn);
        initialGrid.add(hbConnectBtn, 1, 4);

        final Text initialTarget = new Text();
        initialGrid.add(initialTarget, 1, 6);
     
        
     /******************************************************/
        
        GridPane executeGrid = new GridPane();
        
        executeGrid.setAlignment(Pos.CENTER);
        executeGrid.setHgap(10);
        executeGrid.setVgap(10);
        executeGrid.setPadding(new Insets(25, 25, 25, 25));
        
        Text executeSceneTitle = new Text("Welcome To Execute Program");
        executeSceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        executeGrid.add(executeSceneTitle, 0, 0, 2, 1);
        
        
        Label commandLabel = new Label("Unix Command:");
        executeGrid.add(commandLabel, 0, 1 );
        

        TextField command = new TextField();
        executeGrid.add(command, 1, 1, 2 , 1 );

        Label outputLabel = new Label("Output:");
        executeGrid.add(outputLabel, 0, 2);

        TextArea executeOutput = new TextArea();
        executeGrid.add(executeOutput, 1, 2 , 2, 1);
        
        Button executeBtn = new Button("Execute");
        HBox hbExecuteBtn = new HBox(10);
        hbExecuteBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbExecuteBtn.getChildren().add(executeBtn);
        executeGrid.add(hbExecuteBtn, 1, 4);
        
        Button exitBtn = new Button("Exit");
        HBox hbExitBtn = new HBox(10);
        hbExitBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbExitBtn.getChildren().add(exitBtn);
        executeGrid.add(hbExitBtn, 2, 4);
                
        initialScene	= new Scene(initialGrid, 500, 400);
        connectedScene	= new Scene(executeGrid, 500, 400);
        
        final Text executeTarget = new Text();
        executeGrid.add(executeTarget, 1, 6);
        
       //event handler to handle the initial button action
        connectBtn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
            	
            	String enteredAddress = address.getText();
            	int enteredPort = Integer.parseInt(port.getText());
            	initialTarget.setFill(Color.FIREBRICK);
            	initialTarget.setText("Address: "+enteredAddress+" Port: "+ enteredPort);
            	
            	csocket = new ClientSocket(enteredAddress, enteredPort);
            	try {
					
            		csocket.connect();
            		mainStage.setScene(connectedScene);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                              
            }
         });
        
        //event handler to handle the execute button action
        executeBtn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
            	String enteredCommand = command.getText();
            	executeTarget.setFill(Color.FIREBRICK);
            	executeTarget.setText("Last Command: "+enteredCommand);
                
                            	
            	try {
					String serverResponse = csocket.send(enteredCommand);
					
					executeOutput.setText(serverResponse);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            	
            	try {
					csocket.terminate();
					executeTarget.setFill(Color.FIREBRICK);
	            	executeTarget.setText("Connection Terminated. System exits in 2 seconds");
	            	/*Thread.sleep(3000);*/
	            	System.exit(1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
            }
        });
        
        
        mainStage.setScene(initialScene);        
        mainStage.show();
    }
	
	public static void main(String[] args) {
		launch();
	}
}
