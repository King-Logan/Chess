package Game;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
 
public class GUI extends Application {
 
    public static void main(String args[]){          
         launch(args);     
    } 
         
    @Override    
    public void start(Stage primaryStage) throws Exception { 
         
        StackPane layout = new StackPane();
        Scene scene = new Scene(layout, 400, 300);                      
     
        primaryStage.setTitle("CodersLegacy");
        primaryStage.setScene(scene);   
        primaryStage.show();
    }
}

