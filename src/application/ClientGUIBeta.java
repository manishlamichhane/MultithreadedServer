/*
 * This is a client GUI application, for the MultiThreadServer java program
 * It has two fields, one is the address of the server and other is the command that needs to be scand
 */

package application;
	


import javafx.scene.control.Button;
import  javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ClientGUIBeta extends Application {
	
	Stage mainStage;
	Scene scanScene, afterScanScene,afterPortSelectScene;
	
	
	@Override
    public void start(Stage primaryStage) {
		
		mainStage = primaryStage;		
        mainStage.setTitle("JavaFX Welcome");
        
        GridPane scanGrid = new GridPane();
        
        scanGrid.setAlignment(Pos.CENTER);
        scanGrid.setHgap(10);
        scanGrid.setVgap(10);
        scanGrid.setPadding(new Insets(25, 25, 25, 25));
        
        Text scanSceneTitle = new Text("Welcome To scan Program");
        scanSceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        scanGrid.add(scanSceneTitle, 0, 0, 2, 1);
        
                    
        Button scanBtn = new Button("Scan");
        HBox hbScanBtn = new HBox(10);
        hbScanBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbScanBtn.getChildren().add(scanBtn);
        scanGrid.add(hbScanBtn, 1, 4);
        
               
      /*******************************************************************/
        
        // Scene setting for After Scan Scene
        
        GridPane afterScanGrid = new GridPane();
        afterScanGrid.setAlignment(Pos.CENTER);
        afterScanGrid.setHgap(10);
        afterScanGrid.setVgap(10);
        afterScanGrid.setPadding(new Insets(25, 25, 25, 25));
        
       
        
        Text LoopSceneTitle = new Text("Scan results: ");
        LoopSceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        afterScanGrid.add(LoopSceneTitle, 0, 0, 2, 1);
        
        
              
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        
        Button portSelectBtn = new Button("Select");
        HBox hbportSelectBtn = new HBox(10);
        hbportSelectBtn.setAlignment(Pos.BOTTOM_LEFT);
        
        hbportSelectBtn.getChildren().add(portSelectBtn);
        afterScanGrid.add(hbportSelectBtn, 1, 4);
        
        /*Button scanSelectBtn = new Button("Scan >>");
        HBox hbscanSelectBtn = new HBox(10);
        hbscanSelectBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbscanSelectBtn.getChildren().add(scanSelectBtn);
        afterScanGrid.add(hbscanSelectBtn, 1, 4);*/
        
        
     /*******************************************************************/  
        
        final Text scanTarget = new Text();
        scanGrid.add(scanTarget, 1, 6);
        
        final Text loopTarget = new Text();
        afterScanGrid.add(loopTarget, 1, 6);
        
        scanScene	= new Scene(scanGrid, 500, 400);
        afterScanScene	= new Scene(afterScanGrid, 500, 400);
        
       //event handler to handle the scan button action
        scanBtn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
            	
            	String tcpPortScanCommand	=	"netstat -lt | grep tcp6";
            	Executor executor	=	new Executor();
            	String[] openPorts	=	executor.executeCommand(tcpPortScanCommand).split("\n");
            	
            	// a radio button is to be assigned to a toggle group
            	// as only one radio button in a toggle group can be selected at a time
            	// since the radio button object is generated dynamically, 
            	// to get access to individual object, they are all assigned to an ArrayList of radio buttons
            	// each radio button is added to this arraylist and then each member of arraylist is assigned
            	// to the ToggleGroup portsGroup.
            	// finally all the radio buttons in radioList are added to vbox in bulk using addAll() method
            	ToggleGroup portsGroup = new ToggleGroup();
            	
            	ArrayList radioList = new ArrayList<RadioButton>(); 
            	
            	int count = 0;
            	for(String s: openPorts){
            		
            			radioList.add(new RadioButton(s));
            			((ToggleButton) radioList.get(count++)).setToggleGroup(portsGroup);
            		  //vbox.getChildren().add(new RadioButton(s));
            		  
            	}
            	
            	vbox.getChildren().addAll(radioList);
            	
            	hbox.getChildren().add(vbox);
            	afterScanGrid.add(hbox,0, 1);
            	mainStage.setScene(afterScanScene); 
            }
        });
        
      //event handler to load scan scene
        /*scanSelectBtn.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            	
            	 // from the afterScanFrid, when scanSelect button is clicked to go back to scanScene
            	 // the open ports are already added to the scene,
            	 // so re-adding them will throw error
            	 // hence, the vbox that has all the ports added to it,
            	 // should be destroyed in the scanScene
            	 // only then a fresh scan can be done without error
            	
            	 vbox.getChildren().removeAll();
            	 hbox.getChildren().remove(vbox);
                 afterScanGrid.getChildren().removeAll(vbox,hbox);
                 
                 mainStage.setScene(scanScene);        
                 for(Node n: vbox.getChildren()){
             		
             		vbox.getChildren().remove(n);
             	}
            }
        });*/
        
        portSelectBtn.setOnAction(new EventHandler<ActionEvent>() {
          	 
            @Override
            public void handle(ActionEvent e) {
            	
            	// 
            	
            }
        });
        
        
        
             
        mainStage.setScene(scanScene);        
        mainStage.show();
    }
	
	public static void main(String[] args) {
		launch();
	}
}
