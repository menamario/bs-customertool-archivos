package mx.com.bsmexico.customertool.dispersion.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Platform;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mx.com.bsmexico.customertool.api.Feature;
import mx.com.bsmexico.customertool.api.Layout;
import mx.com.bsmexico.customertool.api.NavRoute;
import mx.com.bsmexico.customertool.api.process.Exporter;
import mx.com.bsmexico.customertool.api.process.Importer;

public class OpcionDispersion extends Feature {

	DispersionTable t = null;
	private SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	int hashCodeGuardado;
	RadioButton rbTxt = null;
	RadioButton rbCsv = null;

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
		getDesktop().updatePleca("#b50055", null);

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
			if (t.getItems().hashCode() == hashCodeGuardado) {
				salir();
			} else {
				Stage stage = new Stage(StageStyle.UNDECORATED);

				StackPane canvas = new StackPane();
				canvas.setPadding(new Insets(10));
				canvas.setStyle("-fx-background-color: #e90e5c;");
				canvas.setPrefSize(512, 54);

				Label mensaje = new Label(
						"¿Quieres guardar los cambios realizados en el archivo?");
				mensaje.setWrapText(true);
				mensaje.setTextAlignment(TextAlignment.CENTER);
				mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
				mensaje.setTextFill(Color.web("#777777"));
				mensaje.setPrefWidth(400);

				Button bGuardar = new Button("Guardar");
				bGuardar.setStyle("-fx-font-family: FranklinGothicLT;	-fx-font-size: 12.0px;	-fx-border-radius: 8.0px;	-fx-background-color: #006dff;	-fx-border-width: 1.0px;	-fx-border-color: #979797;	-fx-font-weight:bold;	-fx-background-radius: 8.0px;");
				bGuardar.setPrefSize(140,40);
				
				bGuardar.setTextFill(Color.WHITE);

				bGuardar.setOnMouseClicked(ev -> {
					stage.hide();

					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							if (guardar())
								salir();
						}
					});
				});

				Button bSalir = new Button("No guardar");
				bSalir.setStyle(
						"-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 12px;  -fx-border-radius: 8px;-fx-background-color: rgba(255, 255, 255, 0.8);-fx-border-width: 1px;-fx-border-color: #006dff;-fx-font-weight:bold;-fx-background-radius: 8px");
				bSalir.setPrefSize(140,40);
				bSalir.setTextFill(Color.web("#006dff"));

				bSalir.setOnMouseClicked(ev -> {
					stage.hide();
					getMenuNavigator().show();
					getDesktop().setWorkArea(null);
					getDesktop().updatePleca("black", null);
				});

				HBox opciones = new HBox();
				opciones.getChildren().addAll(bSalir,bGuardar);
				opciones.setAlignment(Pos.CENTER);
				opciones.setSpacing(35);

				VBox vbox = new VBox();
				vbox.setSpacing(50);
				vbox.setAlignment(Pos.TOP_CENTER);
				vbox.setPrefSize(512, 345);
				// VBox.setVgrow(vbox, Priority.ALWAYS);
				vbox.getChildren().add(canvas);
				vbox.getChildren().add(mensaje);
				vbox.getChildren().add(opciones);

				stage.setScene(new Scene(vbox, 512, 345));
				stage.setResizable(false);
				stage.initOwner(getDesktop().getStage());
				stage.initModality(Modality.WINDOW_MODAL);
				stage.showAndWait();

			}
		});

		final FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("csv and txt files", "*.csv",
				"*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		headerBox1.getChildren().add(bAtras);
		headerBox1.setSpacing(40);
		Label l = new Label("    Dispersion de Pagos    ");
		l.setTextFill(Color.WHITE);
		l.setStyle(
				"-fx-background-color: #b50055;-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 14px;-fx-border-radius: 0 0 5 5; -fx-background-radius: 0 0 5 5;");
		headerBox1.getChildren().add(l);
		headerBox2.getChildren().add(bInstrucciones);
		headerBox2.getChildren().add(bImportarArchivo);
		headerBox2.setSpacing(100);
		HBox.setHgrow(headerBox2, Priority.ALWAYS);
		headerBox2.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		headerBox1.getChildren().add(headerBox2);
		headerBox1.setPadding(new Insets(0, 30, 0, 0));

		BorderPane borderpane = new BorderPane();
		borderpane.setPadding(new Insets(0, 20, 0, 20));
		Label lFormato = new Label("Formato");
		lFormato.setTextFill(Color.WHITE);
		lFormato.setStyle("-fx-font-family: FranklinGothicLT;-fx-font-weight:bold;-fx-font-size: 18px");
		rbTxt = new RadioButton("txt");
		rbTxt.setTextFill(Color.WHITE);
		rbTxt.setStyle("-fx-font-family: FranklinGothicLT;-fx-font-weight:bold;-fx-font-size: 18px");
		rbCsv = new RadioButton("csv");
		rbCsv.setSelected(true);
		rbCsv.setTextFill(Color.WHITE);
		rbCsv.setStyle("-fx-font-family: FranklinGothicLT;-fx-font-weight:bold;-fx-font-size: 18px");
		ToggleGroup tgFormato = new ToggleGroup();
		rbCsv.setToggleGroup(tgFormato);
		rbTxt.setToggleGroup(tgFormato);

		HBox hb = new HBox();
		
		hb.setSpacing(50);
		hb.getChildren().addAll(lFormato, rbTxt, rbCsv);
		hb.setAlignment(Pos.CENTER_RIGHT);
		hb.setPadding(new Insets(0,100,0,0));

		borderpane.setCenter(hb);

		Button bGuardar = new Button("Guardar");
		bGuardar.setStyle(
				"-fx-background-color: #006dff;  -fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 15px;-fx-font-weight:bold");
		bGuardar.setPrefWidth(140);
		bGuardar.setTextFill(Color.WHITE);
		borderpane.setRight(bGuardar);
		BorderPane.setMargin(bGuardar, new Insets(0, 15, 0, 0));

		bGuardar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(final ActionEvent e) {
				guardar();

			}
		});

		bInstrucciones.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				Stage stage = new Stage();

				StackPane canvas = new StackPane();
				canvas.setPadding(new Insets(10));
				canvas.setStyle("-fx-background-color: #239d45;");
				canvas.setPrefSize(800, 60);

				Label instruccionesLabel = new Label(
						"Banco Sabadell agradece su preferencia, a continuacion detallamos los pasos que debe seguir para capturar los datos de dispersion de pagos.");
				instruccionesLabel.setWrapText(true);
				instruccionesLabel.setTextAlignment(TextAlignment.JUSTIFY);
				instruccionesLabel
						.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 14px;-fx-font-weight: bold");
				instruccionesLabel.setTextFill(Color.WHITE);
				canvas.getChildren().add(instruccionesLabel);

				stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
				stage.setTitle("Archivos Bantotal - Dispersion - Instrucciones");

				TextArea textArea = new TextArea();
				textArea.setText("\n"

						+ "1) Revise que la configuración regional del sistema operativo esté en Español(México)."
						+ "\n\n3) Los datos que se capturan deben estar en mayúsculas y sin caracteres especiales."
						+ "\n\n4) Al concluir la captura de transferencias para apgos, dar un click en el boton de Guardar, en seguida se abrira una ventana donde usted podrá guardar el archivo en la ruta que indique y con el nombre que desee."
						+ "\n\n6) Las transferencias que se pueden aplicar son:	Transferencias Sabadell en pesos mexicanos, dólares americanos y euros; sin compra-venta de divisas. Transferencias Nacionales Internbancarias en pesos mexicanos."
						+ "\n\n7) Las Transferencias Nacionales Interbancarias deberán llevar la cuenta CLABE del beneficiario; (el número de teléfono móvil y tarjeta de débito o crédito no están disponibles). "
						+ "\n\n8) Las transferencias se ejecutarán a las cuentas de los beneficiarios que previamente se hayan registrado."

				);
				textArea.setEditable(false);
				textArea.setWrapText(true);

				ImageView insIv = null;

				try {
					insIv = new ImageView(new Image(getImageInput("/img/instruccionesDispersion.png")));
					insIv.setPreserveRatio(true);
					insIv.setFitWidth(1000);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ScrollPane scrollPane = new ScrollPane();
				scrollPane.setPrefSize(1000, 600);
				scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
				scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
				scrollPane.setContent(insIv);

				TabPane tabPane = new TabPane();
				Tab tabInstrucciones = new Tab("Instrucciones");
				Tab tabCampos = new Tab("Descripcion de campos");
				tabInstrucciones.setContent(textArea);
				tabCampos.setContent(scrollPane);
				tabPane.getTabs().addAll(tabInstrucciones, tabCampos);

				VBox vbox = new VBox();
				textArea.prefHeightProperty().bind(vbox.prefHeightProperty().add(-60));
				vbox.setPrefSize(1020, 600);
				VBox.setVgrow(vbox, Priority.ALWAYS);
				vbox.getChildren().add(canvas);
				vbox.getChildren().add(tabPane);

				stage.setScene(new Scene(vbox, 1020, 600));
				stage.setResizable(false);
				stage.show();

			}
		});

		VBox vbox = new VBox(headerBox1, borderpane);
		vbox.setSpacing(20);

		((BorderPane) mainPane).setTop(vbox);

		t = new DispersionTable();
		hashCodeGuardado = t.getItems().hashCode();
		t.prefWidthProperty().bind(mainPane.widthProperty().add(-60));

		((BorderPane) mainPane).setCenter(t);
		BorderPane.setMargin(t, new Insets(25, 25, 50, 0));

		bImportarArchivo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
				fileChooser.setInitialDirectory(new File(currentPath));
				File file = fileChooser.showOpenDialog(getDesktop().getStage());
				if (file != null){
					
					Importer ddImporter = null;
					if (file.getName().toUpperCase().endsWith("CSV")){
						ddImporter = new DispersionCSVImporter(t);
					}else{
						ddImporter = new DispersionTXTImporter(t);
					}
					
					
					try {
						ddImporter.importFile(file);
					} catch (Exception e1) {
						e1.printStackTrace();
						Stage stage = new Stage(StageStyle.UNDECORATED);

						Pane canvas = new Pane();
						canvas.setPadding(new Insets(10));
						canvas.setStyle("-fx-background-color:  #e90e5c;");
						canvas.setPrefSize(512, 50);

						stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
						stage.setTitle("Archivos Bantotal - Dispersion - Formato de Archivo Incorrecto");

						Label mensaje = new Label("El archivo no tiene el formato correcto");
						mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
						mensaje.setTextFill(Color.web("#777777"));

						Button bContinuar = new Button("Continuar");
						bContinuar.setStyle("-fx-font-family: FranklinGothicLT;	-fx-font-size: 12.0px;	-fx-border-radius: 8.0px;	-fx-background-color: #006dff;	-fx-border-width: 1.0px;	-fx-border-color: #979797;	-fx-font-weight:bold;	-fx-background-radius: 8.0px;");
						bContinuar.setPrefSize(140,40);
						bContinuar.setTextFill(Color.WHITE);

						bContinuar.setOnMouseClicked(evt -> {
							stage.hide();
						});

						VBox vbox = new VBox();
						vbox.setSpacing(50);
						vbox.setAlignment(Pos.TOP_CENTER);
						vbox.setPrefSize(512, 275);
						// VBox.setVgrow(vbox, Priority.ALWAYS);
						vbox.getChildren().add(canvas);
						vbox.getChildren().add(mensaje);
						vbox.getChildren().add(bContinuar);

						stage.setScene(new Scene(vbox, 512, 275));
						stage.setResizable(false);
						stage.initOwner(getDesktop().getStage());
						stage.initModality(Modality.WINDOW_MODAL);
						stage.showAndWait();
					}
				}
			}
		});

		getDesktop().setWorkArea(mainPane);
	}

	private boolean guardar() {
		boolean isValid;
		try {

			int numRegistros = 0;
			for (Dispersion d : t.getItems()) {
				if (t.isActiveModel(d)) {
					numRegistros++;
				}
			}

			isValid = t.validateTable();
			if (isValid && numRegistros > 0) {
				String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
				FileChooser saveFile = new FileChooser();
				saveFile.setInitialDirectory(new File(currentPath));

				// Set extension filter
				FileChooser.ExtensionFilter sfFilter = null;

				if (rbCsv.isSelected()) {
					sfFilter = new FileChooser.ExtensionFilter("csv files (*.csv)", "*.csv");
				}
				if (rbTxt.isSelected()) {
					sfFilter = new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");
				}

				saveFile.getExtensionFilters().add(sfFilter);

				// Show save file dialog
				saveFile.setInitialFileName(df.format(new Date()) + "_XXXXXXXXX_NNN");
				File file = saveFile.showSaveDialog(getDesktop().getStage());

				// TODO use a factoty here
				if (file != null) {
					Exporter<Dispersion> exporter = null;
					if (rbCsv.isSelected()) {
						exporter = new DispersionCSVExporter(t);
					} else if (rbTxt.isSelected()) {
						exporter = new DispersionTXTExporter(t);
					}

					try {
						exporter.export(file);
						hashCodeGuardado = t.getItems().hashCode();
						Stage stage = new Stage(StageStyle.UNDECORATED);

						StackPane canvas = new StackPane();
						canvas.setPadding(new Insets(10));
						canvas.setStyle("-fx-background-color:  #a9d42c;");
						canvas.setPrefSize(512, 50);

						stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
						stage.setTitle("Archivos Bantotal - Dispersion - Archivo Guardado");

						Label mensaje = new Label("El archivo fue guardado exitosamente");
						mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
						mensaje.setTextFill(Color.web("#777777"));

						Button bContinuar = new Button("Continuar");
						bContinuar.setStyle(
								"-fx-background-color: #006dff;  -fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 15px;");
						bContinuar.setPrefSize(140,40);
						bContinuar.setTextFill(Color.WHITE);

						bContinuar.setOnMouseClicked(evt -> {
							stage.hide();
						});

						VBox vbox = new VBox();
						vbox.setSpacing(50);
						vbox.setAlignment(Pos.TOP_CENTER);
						vbox.setPrefSize(512, 275);
						vbox.getChildren().add(canvas);
						vbox.getChildren().add(mensaje);
						vbox.getChildren().add(bContinuar);

						stage.setScene(new Scene(vbox, 512, 275));
						stage.setResizable(false);
						stage.initOwner(getDesktop().getStage());
						stage.initModality(Modality.WINDOW_MODAL);
						stage.showAndWait();
						return true;
					} catch (Exception e1) {
						e1.printStackTrace();
						return false;
					}
				}
				return false;

			} else if (numRegistros > 0) {
				Stage stage = new Stage(StageStyle.UNDECORATED);

				StackPane canvas = new StackPane();
				canvas.setPadding(new Insets(10));
				canvas.setStyle("-fx-background-color: #e90e5c;");
				canvas.setPrefSize(512, 50);

				stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
				stage.setTitle("Archivos Bantotal - Dispersion - Datos Incorrectos");

				Label mensaje = new Label("Error en los datos proporcionados");
				mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
				mensaje.setTextFill(Color.web("#777777"));

				Button bContinuar = new Button("Continuar");
				bContinuar.setStyle(
						"-fx-background-color: #006dff;  -fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 15px;");
				bContinuar.setPrefSize(140,40);
				bContinuar.setTextFill(Color.WHITE);

				bContinuar.setOnMouseClicked(evt -> {
					stage.hide();
				});

				VBox vbox = new VBox();
				vbox.setPrefSize(512, 275);
				vbox.setSpacing(50);
				vbox.setAlignment(Pos.TOP_CENTER);
				vbox.getChildren().add(canvas);
				vbox.getChildren().add(mensaje);
				vbox.getChildren().add(bContinuar);

				stage.setScene(new Scene(vbox, 512, 275));
				stage.setResizable(false);
				stage.initOwner(getDesktop().getStage());
				stage.initModality(Modality.WINDOW_MODAL);
				stage.showAndWait();
				return false;

			}
		} catch (Exception e2) {
			e2.printStackTrace();
			return false;
		}
		return false;

	}

	private void salir() {
		getMenuNavigator().show();
		getDesktop().setWorkArea(null);
		getDesktop().updatePleca("black", null);
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}
}
