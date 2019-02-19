package application;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Graeffe;

public class IndexController {
	
	@FXML
	private TextField coefficients;

	@FXML
	private TextField polynomialGenerated;

	@FXML
	private TextField solutionX;

	@FXML
	private TextField solutionPolynomial;

	@FXML
	private ChoiceBox<Integer> grades;
	
	
	public void initialize() {
		
		Integer [] gradesoptions = {1,2,3,4,5,6,7,8,9,10};
		grades.setItems(FXCollections.observableArrayList(gradesoptions));
		
	}
	
	
	@FXML
	void generatePolynomial(ActionEvent event) {
		
	}

	@FXML
	void solvePolynomial(ActionEvent event) {
		
		
		if(coefficients.getText()!="" && !grades.getSelectionModel().isEmpty()) {
			try {
				String cof = coefficients.getText();
				String[] parts = cof.split(" ");
				
				int random = (int)(Math.random() * 1) + 1;
				if(random == 1) {
					Main.setGraeffeMethod(new Graeffe(convert(parts)));
					Main.getGraeffeMethod().showRoots();					
				}else if(random == 2) {
					Main.setGraeffeMethod(new Graeffe(convert(parts)));
				}
			}catch(Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setContentText("Please write the coefficients");
				alert.setTitle("WARNING");
				alert.showAndWait();
			}	
			
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Please write the coefficients");
			alert.setTitle("WARNING");
			alert.showAndWait();
		}
	}
	
	
	private double[] convert(String[] parts) {
		double[] aux;
		aux = new double[parts.length];
		for(int i= 0;i < parts.length;i++) {
			aux[i] = Double.parseDouble(parts[i]);
		}
		
		return aux;
	}
	
	
	
}
