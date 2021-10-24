package CambioDivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application{
	
	private Stage primaryStage;
	BorderPane root;
	HBox hBox1, hBox2, hBox3;
	VBox contenedor;
	TextField textFrom, textTo;
	ComboBox<Divisa> comboUp, comboDown;
	Button buttonCambiar;
	
	Divisa euro = new Divisa("Euro", 1.0);
	Divisa libra = new Divisa("Libra",0.8873);
	Divisa dolar = new Divisa("Dolar", 1.2007);
	Divisa yen = new Divisa("Yen", 133.59);
	
	private Divisa[] arrayDivisa = {euro,libra,dolar,yen};
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		textFrom = new TextField();
		textFrom.setPrefWidth(60);
		
		textTo = new TextField();
		textTo.setPrefWidth(60);
		textTo.setEditable(false);
		
		comboUp = new ComboBox<Divisa>();
		comboUp.getItems().addAll(arrayDivisa);
		comboUp.getSelectionModel().selectFirst();
		
		comboDown = new ComboBox<Divisa>();
		comboDown.getItems().addAll(arrayDivisa);
		comboDown.getSelectionModel().selectLast();
			
		buttonCambiar = new Button("Cambiar");
		buttonCambiar.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				onCambiarAction(event);	
			}
		});
		
		hBox1 = new HBox();
		hBox2 = new HBox();
		hBox3 = new HBox();
		contenedor = new VBox();
		
		hBox1.getChildren().addAll(textFrom, comboUp);
		hBox1.setAlignment(Pos.CENTER);
		hBox1.setPadding(new Insets(5));
		hBox1.setSpacing(5);
		
		hBox2.getChildren().addAll(textTo, comboDown);
		hBox2.setAlignment(Pos.CENTER);
		hBox2.setPadding(new Insets(5));
		hBox2.setSpacing(5);
		
		hBox3.getChildren().add(buttonCambiar);
		hBox3.setAlignment(Pos.CENTER);
		hBox3.setPadding(new Insets(5));
		hBox3.setSpacing(5);
		
		contenedor.getChildren().addAll(hBox1,hBox2,hBox3);
		contenedor.setAlignment(Pos.CENTER);
		
		root = new BorderPane();
		root.setCenter(contenedor);
		
		Scene scene = new Scene(root,320,200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Cambio de Divisa");
		primaryStage.show();

	}

	protected void onCambiarAction(ActionEvent event) {

		Double divisa;
		Double divisaCambiada;
		
		try {
			divisa = Double.parseDouble(textFrom.getText());
			Divisa origen = comboUp.getSelectionModel().getSelectedItem();
			Divisa destino = comboDown.getSelectionModel().getSelectedItem();
			divisaCambiada = destino.fromEuro(origen.toEuro(divisa));
			
			textTo.setText(divisaCambiada.toString());
			
		}catch (NumberFormatException e) {
			Alert mostrarAlerta = new Alert(AlertType.ERROR);
			mostrarAlerta.initOwner(primaryStage);
			mostrarAlerta.setTitle("ERROR");
			mostrarAlerta.setHeaderText("DEBE INTRODUCIR UN NUMERO");
			mostrarAlerta.setContentText(e.getMessage());
			mostrarAlerta.showAndWait();
		}	
	}

	public static void main(String[] args) {
		launch(args);
	}
}
