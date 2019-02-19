package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.Bairstow;
import model.Graeffe;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static Bairstow bairstowMethod;
	private static Graeffe graeffeMethod;
	
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/application/Index.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Polynomials");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		graeffeMethod = new Graeffe();
		bairstowMethod = new Bairstow();
		launch(args);
	}

	public static Bairstow getBairstowMethod() {
		return bairstowMethod;
	}

	public static void setBairstowMethod(Bairstow bairstowMethod) {
		Main.bairstowMethod = bairstowMethod;
	}

	public static Graeffe getGraeffeMethod() {
		return graeffeMethod;
	}

	public static void setGraeffeMethod(Graeffe graeffeMethod) {
		Main.graeffeMethod = graeffeMethod;
	}
	
	
	
	
}
