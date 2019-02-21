package application;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Bairstow;
import model.Graeffe;

public class IndexController {
	
	@FXML
	private TextField coefficients;

	@FXML
	private TextField polynomialGenerated;

	@FXML
	private TextField realSolution;

	@FXML
	private TextField complexSolution;

	@FXML
	private ChoiceBox<Integer> grades;
	
	
	public void initialize() {
		
		Integer [] gradesoptions = {1,2,3,4,5,6,7,8,9,10};
		grades.setItems(FXCollections.observableArrayList(gradesoptions));
		
	}
	
	
	@FXML
	void generatePolynomial(ActionEvent event) {
		int random = (int)(Math.random() * 9 + 1);
		String coef = "";
		grades.setValue(random);
		for(int i= 0;  i <= random + 1; i++ ) {
			
			int random1 = (int)(Math.random() * 9 + 1);
			coef += random1 +" ";
		}
		coefficients.setText(coef);  
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
					realSolution.setText(Main.getGraeffeMethod().realRoots[0] + "");
					complexSolution.setText(Main.getGraeffeMethod().complexRoots[0] + "");
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setContentText("Solved By Graeffe's Method");
					alert.setTitle("INFO");
					alert.showAndWait();
					
				}else if(random == 2) {
					Main.setBairstowMethod(new Bairstow(parts,grades.getSelectionModel().getSelectedIndex()));
					System.out.println("ENTRO EN BAIRSTOW");
					Main.getBairstowMethod().solve();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setContentText("Solved By Baristow's Method");
					alert.setTitle("INFO");
					alert.showAndWait();
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
