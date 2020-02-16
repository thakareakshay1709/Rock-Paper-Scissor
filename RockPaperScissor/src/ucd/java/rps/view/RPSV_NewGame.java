package ucd.java.rps.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * This is view created for main project and which is required to run the project
 * @author akshaythakare
 *
 */
public class RPSV_NewGame extends Application {
	
	@Override
	public void start(Stage primaryStage)  {
		/**
		 * This is the first view java class to load NewGame.fxml file
		 * Rest of the files will be loaded on particular action event.
		 */
		Parent root;
		try {
			/**
			 * {@link} https://www.youtube.com/watch?v=mokD1I7hl-o&list=PLS1QulWo1RIaUGP446_pWLgTZPiFizEMq&index=10
			 * 
			 * The reference of the pattern to load FXML files in java was taken from above link from youtube
			 */
			
			root = FXMLLoader.load(getClass().getResource("/ucd/java/rps/view/NewGame.fxml"));
			Scene scene = new Scene(root,700,500);
			primaryStage.setScene(scene);
			primaryStage.setTitle("ROCK PAPER SCISSOR");
			primaryStage.setFullScreen(false);
			primaryStage.setResizable(false);
			primaryStage.show();
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// AnchorPane root = new AnchorPane(); 
		 
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
