package mx.com.bsmexico.customertool.dispersion.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mx.com.bsmexico.customertool.api.Feature;
import mx.com.bsmexico.customertool.api.Layout;
import mx.com.bsmexico.customertool.api.NavRoute;
import mx.com.bsmexico.customertool.beneficiarios.plugin.Beneficiario;
import mx.com.bsmexico.customertool.beneficiarios.plugin.BeneficiarioTable;

public class OpcionDispersion extends Feature {

	DispersionTable t = null;

	public String getNombreMenu() {
		// TODO Auto-generated method stub
		return "Alta de Beneficiarios";
	}

	public void ejecutar() {
		System.out.println("Se ejecuto el alta de Beneficiarios");

	}

	public String getImagenMenu() {
		return "/img/beneficiarios.svg";

	}

	@SuppressWarnings("unused")
	private InputStream getImageInput(final String file) throws FileNotFoundException {
		final InputStream input = getClass().getResourceAsStream(file);
		return input;

	}

	@Override
	public Layout getLayout() {

		final NavRoute.BuilderNavRoute navRuoteBuilder = new NavRoute.BuilderNavRoute("TEST");
		NavRoute route = null;
		try {
			route = navRuoteBuilder
					.addNode("Generacion de Archivos de Entrada", "Generacion de Archivos de Entrada", 0, false,
							getImageInput("/img/archivos.png"))
					.addNode("Dispersion de Pagos", "Dispersion de Pagos", 0, false,
							getImageInput("/img/dispersion.png"))
					.build();

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Layout.LayoutBuilder("l1").route(route).build();
	}

	@Override
	public void launch() {

		getMenuNavigator().hide();

		Pane mainPane = new BorderPane();

		mainPane.setPadding(new Insets(0, 20, 0, 20));

		HBox headerBox1 = new HBox();
		HBox headerBox2 = new HBox();

		ImageView atras = null;
		ImageView importarArchivo = null;
		ImageView instrucciones = null;

		try {
			atras = new ImageView(new Image(this.getImageInput("/img/atras.png")));
			importarArchivo = new ImageView(new Image(this.getImageInput("/img/importarArchivo.png")));
			importarArchivo.setPreserveRatio(true);
			importarArchivo.setFitWidth(70);
			instrucciones = new ImageView(new Image(this.getImageInput("/img/instrucciones.png")));
			instrucciones.setPreserveRatio(true);
			instrucciones.setFitWidth(70);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Button bAtras = new Button();
		Button bInstrucciones = new Button();
		Button bImportarArchivo = new Button();

		bAtras.setGraphic(atras);
		bAtras.setStyle("-fx-background-color: transparent;");
		bAtras.setTooltip(new Tooltip("Regresar"));
		bInstrucciones.setGraphic(instrucciones);
		bInstrucciones.setText("Instrucciones");
		bInstrucciones.setTextFill(Color.WHITE);
		bInstrucciones.setStyle(
				"-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 13px;-fx-background-color: transparent;");
		bInstrucciones.setContentDisplay(ContentDisplay.TOP);

		bImportarArchivo.setGraphic(importarArchivo);
		bImportarArchivo.setText("Importar Archivo");
		bImportarArchivo.setTextFill(Color.WHITE);
		bImportarArchivo.setStyle(
				"-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 13px;-fx-background-color: transparent;");
		bImportarArchivo.setContentDisplay(ContentDisplay.TOP);

		bAtras.setOnMouseClicked(evt -> {
			getMenuNavigator().show();
		});

		final FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xls)", "*.xls");
		fileChooser.getExtensionFilters().add(extFilter);

		headerBox1.getChildren().add(bAtras);
		headerBox2.getChildren().add(bInstrucciones);
		headerBox2.getChildren().add(bImportarArchivo);
		headerBox2.setSpacing(10);
		HBox.setHgrow(headerBox2, Priority.ALWAYS);
		headerBox2.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		headerBox1.getChildren().add(headerBox2);

		BorderPane borderpane = new BorderPane();
		borderpane.setPadding(new Insets(0, 20, 0, 20));
		Label lFormato = new Label("Formato");
		lFormato.setTextFill(Color.WHITE);
		RadioButton rbTxt = new RadioButton("txt");
		rbTxt.setTextFill(Color.WHITE);
		RadioButton rbCsv = new RadioButton("csv");
		rbCsv.setSelected(true);
		rbCsv.setTextFill(Color.WHITE);
		ToggleGroup tgFormato = new ToggleGroup();
		rbCsv.setToggleGroup(tgFormato);
		rbTxt.setToggleGroup(tgFormato);

		HBox hb = new HBox();
		hb.setSpacing(10);
		hb.getChildren().addAll(lFormato, rbTxt, rbCsv);
		hb.setAlignment(Pos.CENTER);

		borderpane.setCenter(hb);

		Button bGuardar = new Button("Guardar");
		bGuardar.setStyle(
				"-fx-background-color: #006dff;  -fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 15px;");
		bGuardar.setPrefWidth(140);
		bGuardar.setTextFill(Color.WHITE);
		borderpane.setRight(bGuardar);

		bGuardar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {

				int numError = 0;
				int numRegistros = 0;
				for (Dispersion b : t.getTable().getItems()) {
					if (b.isActive()) {
						numRegistros++;
						if (!b.validar())
							numError++;
					}
				}
				t.getTable().refresh();

				if (numError > 0) {
					Stage stage = new Stage();

					StackPane canvas = new StackPane();
					canvas.setPadding(new Insets(10));
					canvas.setStyle("-fx-background-color: #e90e5c;");
					canvas.setPrefSize(512, 40);

					stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
					stage.setTitle("Archivos Bantotal - Dispersion - Datos Incorrectos");

					Label mensaje = new Label("Error en los datos proporcionados");

					Button bContinuar = new Button("Continuar");
					bContinuar.setStyle(
							"-fx-background-color: #006dff;  -fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 15px;");
					bContinuar.setPrefWidth(140);
					bContinuar.setTextFill(Color.WHITE);

					bContinuar.setOnMouseClicked(evt -> {
						stage.hide();
					});

					VBox vbox = new VBox();
					vbox.setPrefSize(512, 345);
					VBox.setVgrow(vbox, Priority.ALWAYS);
					vbox.getChildren().add(canvas);
					vbox.getChildren().add(mensaje);
					vbox.getChildren().add(bContinuar);

					stage.setScene(new Scene(vbox, 512, 345));
					stage.setResizable(false);
					stage.show();

				} else if(numRegistros>0){

					FileChooser saveFile = new FileChooser();

					// Set extension filter
					FileChooser.ExtensionFilter sfFilter = new FileChooser.ExtensionFilter("csv files (*.csv)",
							"*.csv");
					saveFile.getExtensionFilters().add(sfFilter);

					// Show save file dialog
					File file = saveFile.showSaveDialog(getDesktop().getStage());

					// if (file != null) {
					// BeneficiariosExport exporter = new BeneficiariosExport();
					// try {
					// exporter.export(t.getTable().getItems(), file);
					// } catch (Exception e1) {
					// // TODO Auto-generated catch block
					// e1.printStackTrace();
					// }
					// }

				}
			}
		});

		bInstrucciones.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				Stage stage = new Stage();
				stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
				stage.setTitle("Archivos Bantotal - Beneficiarios - Instrucciones");
				TextArea textArea = new TextArea();
				textArea.setText(
						"Aqui van las instrucciones\nPara Usar la Opcion de Captura de Beneficiarios\nPuede Contener texto e imagenes");

				VBox vbox = new VBox(textArea);
				textArea.prefHeightProperty().bind(vbox.prefHeightProperty());
				vbox.setPrefSize(800, 600);
				VBox.setVgrow(vbox, Priority.ALWAYS);

				stage.setScene(new Scene(vbox, 800, 600));
				stage.show();
				// Hide this current window (if this is what you want)
				// ((Node)(event.getSource())).getScene().getWindow().hide();

			}
		});

		VBox vbox = new VBox(headerBox1, borderpane);
		vbox.setSpacing(20);

		((BorderPane) mainPane).setTop(vbox);

		final ClassLoader classLoader = getClass().getClassLoader();
		InputStream layout = null;
		layout = getClass().getResourceAsStream("/xml/layouts/beneficiariosLayout.xml");

		t = new DispersionTable(new ColumnDispersionFactory());

		t.getTable().prefWidthProperty().bind(mainPane.widthProperty().add(-60));

		((BorderPane) mainPane).setCenter(t);
		BorderPane.setMargin(t, new Insets(25, 0, 0, 0));

		// bImportarArchivo.setOnAction(new EventHandler<ActionEvent>() {
		// @Override
		// public void handle(final ActionEvent e) {
		// File file = fileChooser.showOpenDialog(getDesktop().getStage());
		// if (file != null) {
		// t.getTable().setItems(loadXls(file));
		// }
		// }
		// });

		getDesktop().setWorkArea(mainPane);
	}
}
