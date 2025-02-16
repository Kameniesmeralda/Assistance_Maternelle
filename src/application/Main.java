package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane ;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = (GridPane )FXMLLoader.load(getClass().getResource("AssistanceMaternelle.fxml"));
			Scene scene = new Scene(root,420,360);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(true);
			primaryStage.setTitle("Assistance maternelle");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
		System.out.println("La liste des presences est:");
		for(Presence p : Presence.listePresence) {
			System.out.println(p.toString());
		}
	}
}
