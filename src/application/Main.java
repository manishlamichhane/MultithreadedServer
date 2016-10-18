package application;
	


import javafx.scene.control.Button;
import  javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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


public class Main extends Application {
	
	Stage mainStage;
	Scene executeScene, loopScene;
	
	
	@Override
    public void start(Stage primaryStage) {
		
		mainStage = primaryStage;		
        mainStage.setTitle("JavaFX Welcome");
        
    /*******************************************************************/
        //Unix command Execute Scene
        
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
        
        Button loopSelectBtn = new Button("Loop >>");
        HBox hbLoopSelectBtn = new HBox(10);
        hbLoopSelectBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbLoopSelectBtn.getChildren().add(loopSelectBtn);
        executeGrid.add(hbLoopSelectBtn, 2, 4);
        
     /*******************************************************************/     
        //loop scene
        
        GridPane loopGrid = new GridPane();
        loopGrid.setAlignment(Pos.CENTER);
        loopGrid.setHgap(10);
        loopGrid.setVgap(10);
        loopGrid.setPadding(new Insets(25, 25, 25, 25));
        
        // Scene setting for Loop program
        
        Text LoopSceneTitle = new Text("Welcome To Loop Program");
        LoopSceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        loopGrid.add(LoopSceneTitle, 0, 0, 2, 1);
        
        
        Label loopValueLabel = new Label("Iteration value:");
        loopGrid.add(loopValueLabel, 0, 1 );

        TextField loopValue = new TextField();
        loopGrid.add(loopValue, 1, 1, 2 , 1 );

        Label loopOutputLabel = new Label("Output:");
        loopGrid.add(loopOutputLabel, 0, 2);

        TextArea loopOutput = new TextArea();
        loopGrid.add(loopOutput, 1, 2 , 2, 1);
        
        Button loopBtn = new Button("Loop");
        HBox hbLoopBtn = new HBox(10);
        hbLoopBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbLoopBtn.getChildren().add(loopBtn);
        loopGrid.add(hbLoopBtn, 1, 4);
        
        Button executeSelectBtn = new Button("Execute >>");
        HBox hbExecuteSelectBtn = new HBox(10);
        hbExecuteSelectBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbExecuteSelectBtn.getChildren().add(executeSelectBtn);
        loopGrid.add(hbExecuteSelectBtn, 2, 4);
        
     /*******************************************************************/
        //loop scene ends
        
        final Text executeTarget = new Text();
        executeGrid.add(executeTarget, 1, 6);
        
        final Text loopTarget = new Text();
        loopGrid.add(loopTarget, 1, 6);
        
        executeScene	= new Scene(executeGrid, 500, 400);
        loopScene	= new Scene(loopGrid, 500, 400);
        
       //event handler to handle the execute button action
        executeBtn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
            	String enteredCommand = command.getText();
            	executeTarget.setFill(Color.FIREBRICK);
            	executeTarget.setText("Last Command: "+enteredCommand);
                
                Executor executor = new Executor();                
                executeOutput.setText(executor.executeCommand(enteredCommand));
            }
        });
        
        
        //event handler to load Loop scene
        loopSelectBtn.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
                        	
                mainStage.setScene(loopScene);        
                        	
            }
        });
        
      //event handler to load Execute scene
        executeSelectBtn.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            	
                 mainStage.setScene(executeScene);        
                
            }
        });
        
      //event handler to handle the execute button action
        loopBtn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
            	String enteredLoopValue = loopValue.getText();
            	loopTarget.setFill(Color.FIREBRICK);
            	loopTarget.setText("Loop value: "+enteredLoopValue);
                
                Looper looper = new Looper(Integer.parseInt(enteredLoopValue));                
                loopOutput.setText(looper.loop());
            }
        });

        
        mainStage.setScene(executeScene);        
        mainStage.show();
    }
	
	public static void main(String[] args) {
		launch();
	}
}
