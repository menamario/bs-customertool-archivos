package mx.com.bsmexico.customertool.beneficiarios.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;

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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mx.com.bsmexico.customertool.api.Feature;
import mx.com.bsmexico.customertool.api.Layout;
import mx.com.bsmexico.customertool.api.NavRoute;

public class OpcionBeneficiarios extends Feature {

	BeneficiarioTable t = null;
	int hashCodeGuardado;
	Button bCerrar = new Button();
	ImageView error = new ImageView();
	ImageView check = new ImageView();
	double xOffset=0;
	double yOffset=0;

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
					.addNode("Generación de Archivos de Entrada", "Generación de Archivos de Entrada", 0, false,
							getImageInput("/img/archivos.png"))
					.addNode("Alta de Beneficiarios", "Alta de Beneficiarios", 0, false,
							getImageInput("/img/beneficiarios.png"))
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
		ImageView cerrar = null;

		try {
			error = new ImageView(new Image(this.getImageInput("/img/error.png")));
			error.setPreserveRatio(true);
			error.setFitWidth(66);
			check = new ImageView(new Image(this.getImageInput("/img/check.png")));
			check.setPreserveRatio(true);
			check.setFitWidth(66);
			atras = new ImageView(new Image(this.getImageInput("/img/atras.png")));
			atras.setPreserveRatio(true);
			atras.setFitWidth(40);
			cerrar = new ImageView(new Image(this.getImageInput("/img/close.png")));
			cerrar.setPreserveRatio(true);
			cerrar.setFitWidth(25);
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
		

		bCerrar.setGraphic(cerrar);
		bCerrar.setStyle("-fx-background-color: transparent;");
		StackPane.setAlignment(bCerrar, Pos.TOP_RIGHT);

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
				
				getDesktop().opacar();
				Stage stage = new Stage(StageStyle.UNDECORATED);

				StackPane canvas = new StackPane();
				canvas.setPadding(new Insets(5));
				canvas.setStyle("-fx-background-color: #e90e5c;");
				canvas.setPrefSize(512, 54);

				canvas.getChildren().add(bCerrar);
				StackPane.setAlignment(bCerrar, Pos.TOP_RIGHT);

				bCerrar.setOnMouseClicked(ev -> {
					stage.hide();
				});

				Label mensaje = new Label("¿Quieres guardar los cambios realizados en el archivo?");
				mensaje.setWrapText(true);
				mensaje.setTextAlignment(TextAlignment.CENTER);
				mensaje.setStyle("-fx-font-family: FranklinGothicLT;-fx-font-size: 20px;-fx-font-weight:bold;");
				mensaje.setTextFill(Color.web("#777777"));
				mensaje.setPrefWidth(400);

				Button bGuardar = new Button("Guardar");
				bGuardar.setStyle(
						"-fx-font-family: FranklinGothicLT;	-fx-font-size: 12.0px;	-fx-border-radius: 8.0px;	-fx-background-color: #006dff;	-fx-border-width: 1.0px;	-fx-border-color: #979797;	-fx-font-weight:bold;	-fx-background-radius: 8.0px;");
				bGuardar.setPrefSize(140, 40);

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
				bSalir.setPrefSize(140, 40);
				bSalir.setTextFill(Color.web("#006dff"));

				bSalir.setOnMouseClicked(ev -> {
					stage.hide();
					getMenuNavigator().show();
					getDesktop().setWorkArea(null);
					getDesktop().updatePleca("black", null);
				});

				HBox opciones = new HBox();
				opciones.getChildren().addAll(bSalir, bGuardar);
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
				
				stage.setX(getDesktop().getStage().getX()+((getDesktop().getStage().getWidth()-512)/2));
				stage.setY(getDesktop().getStage().getY()+((getDesktop().getStage().getHeight()-345)/2));
				stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
			        if (KeyCode.ESCAPE == event.getCode()) {
			            stage.close();
			        }
			    });
				stage.showAndWait();
				getDesktop().desOpacar();

			}
		});

		final FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel and Csv files (*.xls,*.csv)",
				"*.csv", "*.xls");
		fileChooser.getExtensionFilters().add(extFilter);

		headerBox1.getChildren().add(bAtras);
		headerBox1.setSpacing(40);
		Label l = new Label("    Alta de Beneficiarios    ");
		l.setTextFill(Color.WHITE);
		l.setStyle(
				"-fx-background-color: #b50055;-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 14px;-fx-border-radius: 0 0 5 5; -fx-background-radius: 0 0 4 4;");

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
		RadioButton rbTxt = new RadioButton("txt");
		rbTxt.setTextFill(Color.WHITE);
		RadioButton rbCsv = new RadioButton("csv");
		rbCsv.setSelected(true);
		rbCsv.setTextFill(Color.WHITE);
		ToggleGroup tgFormato = new ToggleGroup();
		rbCsv.setToggleGroup(tgFormato);
		rbTxt.setToggleGroup(tgFormato);
		rbTxt.setDisable(true);

		HBox hb = new HBox();
		hb.setSpacing(10);
		// hb.getChildren().addAll(lFormato, rbTxt, rbCsv);
		Label mensajeCsv = new Label("El archivo se guardara en formato .csv       ");
		mensajeCsv.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 24px;-fx-font-weight: bold");
		mensajeCsv.setTextFill(Color.WHITE);

		hb.getChildren().add(mensajeCsv);
		hb.setAlignment(Pos.CENTER_RIGHT);

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
				

				Stage stage = new Stage(StageStyle.UNDECORATED);

				StackPane canvas = new StackPane();
				canvas.setPadding(new Insets(10));
				canvas.setStyle("-fx-background-color: #239d45;");
				canvas.setPrefSize(800, 60);
				canvas.setMinHeight(54);
				canvas.setOnMousePressed(e -> {
					xOffset = e.getSceneX();
					yOffset = e.getSceneY();

		        });
				
				canvas.setOnMouseDragged(e -> {
					stage.setX(e.getScreenX() - xOffset);
					stage.setY(e.getScreenY() - yOffset - 20);

		        });
				
				canvas.getChildren().add(bCerrar);
				StackPane.setAlignment(bCerrar, Pos.TOP_RIGHT);

				bCerrar.setOnMouseClicked(ev -> {
					stage.hide();
				});		
				

				Label instruccionesLabel = new Label(
						"Banco Sabadell agradece su preferencia, a continuación se detallan los pasos que debes seguir para generar el layout de Beneficiarios.");
				instruccionesLabel.setWrapText(true);
				instruccionesLabel.setTextAlignment(TextAlignment.CENTER);
				instruccionesLabel.setMinHeight(40);
				instruccionesLabel
						.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 14px;-fx-font-weight: bold");
				instruccionesLabel.setTextFill(Color.web("#828488"));
				StackPane p = new StackPane();
				p.setPadding(new Insets(20,0,20,0));
				p.setStyle("-fx-background-color: white");
				p.getChildren().add(instruccionesLabel);

				stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
				stage.setTitle("Archivos Bantotal - Beneficiarios - Instrucciones");

				ImageView lc1 = null;
				ImageView lc2 = null;
				ImageView lc3 = null;
				ImageView lc4 = null;
				ImageView lc5 = null;
				ImageView insIv = null;

				try {
					lc1 = new ImageView(new Image(getImageInput("/img/littleCheck.png")));
					lc2 = new ImageView(new Image(getImageInput("/img/littleCheck.png")));
					lc3 = new ImageView(new Image(getImageInput("/img/littleCheck.png")));
					lc4 = new ImageView(new Image(getImageInput("/img/littleCheck.png")));
					lc5 = new ImageView(new Image(getImageInput("/img/littleCheck.png")));
					insIv = new ImageView(new Image(getImageInput("/img/instruccionesBeneficiarios.png")));
					insIv.setPreserveRatio(true);
					insIv.setFitWidth(800);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				TextFlow flow = new TextFlow();
								
				Text t0 = new Text("Instrucciones Generales\n\n   ");
				t0.setStyle("-fx-fill: #828488;-fx-font-weight: bold");
				
				Text t1 = new Text(" Podrás generar tus archivos para Beneficiarios capturando registro por registro o por medio de la importación\n        de archivos.\n   ");
				t1.setStyle("-fx-fill: #828488");
				Text t2 = new Text(" En caso de capturar algún dato erróneo, la aplicación marcará en color amarillo la celda correspondiente al error.\n   ");
				t2.setStyle("-fx-fill: #828488");
				Text t3 = new Text(" Las instrucciones para el llenado de cada uno de los campos que integran el layout están disponibles en la hoja\n        llamada \"Descripción de campos\"\n   ");
				t3.setStyle("-fx-fill: #828488");
				Text t4 = new Text(" La aplicación te permitirá capturar datos en mayúsculas y minúsculas.\n   ");
				t4.setStyle("-fx-fill: #828488");
				Text t5 = new Text(" Al guardar el archivo, la aplicación te solicitará que indiques la carpeta de tu preferencia, así como el nombre con\n        el que deseas guardarlo.\n\n");
				t5.setStyle("-fx-fill: #828488");

				Text t6 = new Text("Captura\n\n    ");
				t6.setStyle("-fx-fill: #828488;-fx-font-weight: bold");
				Text t7 = new Text("1. Para la captura registro por registro, deberás ingresar los datos de tus beneficiarios comenzando por el campo\n    ");
				Text t70 = new Text("1. ");
				t70.setStyle("-fx-fill: white");
				Text t71 = new Text("\"Cuenta beneficiario\" con base en lo indicado en la hoja llamada \"Descripción de campos\".\n    ");
				t71.setStyle("-fx-fill: #828488");
				t7.setStyle("-fx-fill: #828488");
				Text t8 = new Text("2. Para la importación de archivos, deberás oprimir el botón \"Importar Archivo\", la aplicación te mostrará el explorador\n    ");
				Text t80 = new Text("1. ");
				t80.setStyle("-fx-fill: white");
				Text t81 = new Text("de archivos, a través del cual deberás elegir el archivo a importar.\n    ");
				t81.setStyle("-fx-fill: #828488");
				t8.setStyle("-fx-fill: #828488");
				Text t9 = new Text("3. Ya habiendo capturado los datos registro por registro y una vez que la aplicación no detecte ningún error en los\n    ");
				Text t90 = new Text("1. ");
				t90.setStyle("-fx-fill: white");
				Text t91 = new Text("campos, deberás oprimir el botón \"Guardar\".\n    ");
				t91.setStyle("-fx-fill: #828488");
				t9.setStyle("-fx-fill: #828488");
				Text t10 = new Text("    Nota:  ");
				t10.setStyle("-fx-fill: #828488;-fx-font-weight: bold");
				Text t99 = new Text("En caso de que intentes guardar el archivo sin haber validado que los datos estén correctos la aplicación te\n    ");
				Text t990 = new Text("1. ");
				t990.setStyle("-fx-fill: white");
				Text t991 = new Text("mostrará una ventana emergente que indique \"Error en los datos proporcionados\".\n    ");
				t991.setStyle("-fx-fill: #828488");
				t99.setStyle("-fx-fill: #828488");
				Text t11 = new Text("4. Deberás indicar la carpeta en la cual deseas que el archivo quede alojado.\n    ");
				t11.setStyle("-fx-fill: #828488");
				Text t12 = new Text("5. Deberás también indicar el nombre con el que deseas que el archivo sea guardado.\n    ");
				t12.setStyle("-fx-fill: #828488");
				Text t13 = new Text("6. Oprime el botón \"Guardar\" en la ventana del explorador de archivos y la aplicación depositará el archivo en la\n    ");
				Text t130 = new Text("1. ");
				t130.setStyle("-fx-fill: white");
				Text t131 = new Text("carpeta indicada.\n");
				t131.setStyle("-fx-fill: #828488");
				t13.setStyle("-fx-fill: #828488");
				
				flow.getChildren().add(t0);
				
				flow.getChildren().add(lc1);
				flow.getChildren().add(t1);
				flow.getChildren().add(lc2);
				flow.getChildren().add(t2);
				flow.getChildren().add(lc3);
				flow.getChildren().add(t3);
				flow.getChildren().add(lc4);
				flow.getChildren().add(t4);
				flow.getChildren().add(lc5);
				flow.getChildren().add(t5);
				flow.getChildren().addAll(t6,t7,t70,t71,t8,t80,t81,t9,t90,t91,t10,t99,t990,t991,t11,t12,t13,t130,t131);
				flow.setStyle("-fx-background-color:white;-fx-font-family: FranklinGothicLT;-fx-font-size: 14px;-fx-fill: #828488");
				flow.setMaxWidth(747);



				ScrollPane scrollPaneGenerales = new ScrollPane();
				scrollPaneGenerales.setPrefSize(800, 600);
				scrollPaneGenerales.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
				scrollPaneGenerales.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
				scrollPaneGenerales.setContent(flow);
				scrollPaneGenerales.setStyle("-fx-background-color:white");
				scrollPaneGenerales.setPadding(new Insets(35,30,0,30));
				
				ScrollPane scrollPane = new ScrollPane();
				scrollPane.setPrefSize(800, 600);
				scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
				scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
				scrollPane.setContent(insIv);

				TabPane tabPane = new TabPane();
				Tab tabInstrucciones = new Tab("    Instrucciones    ");
				tabInstrucciones.setClosable(false);
				DropShadow ds = new DropShadow();
		        ds.setOffsetY(3.0);
		        ds.setOffsetX(3.0);
		        ds.setColor(Color.GRAY);
		        tabPane.setEffect(ds);
	
				
				Tab tabCampos = new Tab("    Descripcion de campos    ");
				tabInstrucciones.setContent(scrollPaneGenerales);
				
				tabCampos.setContent(scrollPane);
				tabCampos.setClosable(false);
				tabPane.getTabs().addAll(tabInstrucciones, tabCampos);
				tabPane.getSelectionModel().select(0);

				VBox vbox = new VBox();
				vbox.setPrefSize(820, 600);
				VBox.setVgrow(vbox, Priority.ALWAYS);
				vbox.getChildren().add(canvas);
				vbox.getChildren().add(p);
				vbox.getChildren().add(tabPane);
				vbox.setStyle("-fx-background-color:white;");
				

				Scene scene = new Scene(vbox,820,600);
				scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();

			}
		});

		VBox vbox = new VBox(headerBox1, borderpane);
		vbox.setSpacing(20);

		((BorderPane) mainPane).setTop(vbox);

		t = new BeneficiarioTable();
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
				if (file != null) {
					BeneficiariosImporter benImporter = new BeneficiariosImporter(t);
					try {
						benImporter.importFile(file);
					} catch (Exception e1) {
						e1.printStackTrace();
						getDesktop().opacar();
						Stage stage = new Stage(StageStyle.UNDECORATED);

						Pane canvas = new Pane();
						canvas.setPadding(new Insets(5));
						canvas.setStyle("-fx-background-color:  #e90e5c;");
						canvas.setPrefSize(512, 54);
						
						canvas.getChildren().add(bCerrar);
						StackPane.setAlignment(bCerrar, Pos.TOP_RIGHT);

						bCerrar.setOnMouseClicked(ev -> {
							stage.hide();
						});

						stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
						stage.setTitle("Alta de Beneficiarios - Formato de Archivo Incorrecto");

						Label mensaje = new Label("El archivo no tiene el formato correcto");
						mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
						mensaje.setTextFill(Color.web("#777777"));

						Button bContinuar = new Button("Continuar");
						bContinuar.setStyle(
								"-fx-font-family: FranklinGothicLT;	-fx-font-size: 12.0px;	-fx-border-radius: 8.0px;	-fx-background-color: #006dff;	-fx-border-width: 1.0px;	-fx-border-color: #979797;	-fx-font-weight:bold;	-fx-background-radius: 8.0px;");
						bContinuar.setPrefSize(140, 40);
						bContinuar.setTextFill(Color.WHITE);

						bContinuar.setOnMouseClicked(evt -> {
							stage.hide();
						});

						VBox vbox = new VBox();
						vbox.setSpacing(25);
						vbox.setAlignment(Pos.TOP_CENTER);
						vbox.setPrefSize(512, 345);
						// VBox.setVgrow(vbox, Priority.ALWAYS);
						vbox.getChildren().add(canvas);
						vbox.getChildren().add(error);
						mensaje.setPadding(new Insets(0,0,35,0));
						vbox.getChildren().add(mensaje);
						vbox.getChildren().add(bContinuar);

						stage.setScene(new Scene(vbox, 512, 345));
						stage.setResizable(false);
						stage.initOwner(getDesktop().getStage());
						stage.initModality(Modality.WINDOW_MODAL);
						stage.setX(getDesktop().getStage().getX()+((getDesktop().getStage().getWidth()-512)/2));
						stage.setY(getDesktop().getStage().getY()+((getDesktop().getStage().getHeight()-345)/2));
						stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
					        if (KeyCode.ESCAPE == event.getCode()) {
					            stage.close();
					        }
					    });
						stage.showAndWait();
						getDesktop().desOpacar();
					}
				}
			}
		});

		getDesktop().setWorkArea(mainPane);
	}

	private boolean guardar() {
		try {

			int numRegistros = 0;
			for (Beneficiario b : t.getItems()) {
				if (t.isActiveModel(b)) {
					numRegistros++;
				}
			}

			boolean isValid = t.validateTable();
			if (isValid && numRegistros > 0) {
				String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
				FileChooser saveFile = new FileChooser();
				saveFile.setInitialDirectory(new File(currentPath));

				// Set extension filter
				FileChooser.ExtensionFilter sfFilter = new FileChooser.ExtensionFilter("csv files (*.csv)", "*.csv");
				saveFile.getExtensionFilters().add(sfFilter);

				// Show save file dialog
				File file = saveFile.showSaveDialog(getDesktop().getStage());

				if (file != null) {
					BeneficiariosExporter exporter = new BeneficiariosExporter(t);
					try {
						exporter.export(file);
						hashCodeGuardado = t.getItems().hashCode();
						getDesktop().opacar();

						Stage stage = new Stage(StageStyle.UNDECORATED);

						StackPane canvas = new StackPane();
						canvas.setPadding(new Insets(5));
						canvas.setStyle("-fx-background-color:  #a9d42c;");
						canvas.setPrefSize(512, 54);
						
						canvas.getChildren().add(bCerrar);
						StackPane.setAlignment(bCerrar, Pos.TOP_RIGHT);

						bCerrar.setOnMouseClicked(ev -> {
							stage.hide();
						});

						stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
						stage.setTitle("Archivos Bantotal - Beneficiarios - Archivo Guardado");

						Label mensaje = new Label("El archivo fue guardado exitosamente");

						mensaje.setStyle("-fx-font-family: FranklinGothicLT;-fx-font-size: 20px;");
						mensaje.setTextFill(Color.web("#777777"));

						Button bContinuar = new Button("Continuar");
						bContinuar.setStyle(
								"-fx-font-family: FranklinGothicLT;	-fx-font-size: 12.0px;	-fx-border-radius: 8.0px;	-fx-background-color: #006dff;	-fx-border-width: 1.0px;	-fx-border-color: #979797;	-fx-font-weight:bold;	-fx-background-radius: 8.0px;");
						bContinuar.setPrefSize(140, 40);
						bContinuar.setTextFill(Color.WHITE);

						bContinuar.setOnMouseClicked(evt -> {
							stage.hide();
						});

						VBox vbox = new VBox();
						vbox.setSpacing(25);
						vbox.setAlignment(Pos.TOP_CENTER);
						vbox.setPrefSize(512, 345);
						// VBox.setVgrow(vbox, Priority.ALWAYS);
						vbox.getChildren().add(canvas);
						vbox.getChildren().add(check);
						mensaje.setPadding(new Insets(0,0,35,0));
						vbox.getChildren().add(mensaje);
						vbox.getChildren().add(bContinuar);

						stage.setScene(new Scene(vbox, 512, 345));
						stage.setResizable(false);
						stage.initOwner(getDesktop().getStage());
						stage.initModality(Modality.WINDOW_MODAL);
						stage.setX(getDesktop().getStage().getX()+((getDesktop().getStage().getWidth()-512)/2));
						stage.setY(getDesktop().getStage().getY()+((getDesktop().getStage().getHeight()-345)/2));
						stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
					        if (KeyCode.ESCAPE == event.getCode()) {
					            stage.close();
					        }
					    });
						stage.showAndWait();
						getDesktop().desOpacar();
						return true;
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					return false;
				}

			} else if (numRegistros > 0) {
				getDesktop().opacar();
				Stage stage = new Stage(StageStyle.UNDECORATED);
				StackPane canvas = new StackPane();
				canvas.setPadding(new Insets(5));
				canvas.setStyle("-fx-background-color: #e90e5c;");
				canvas.setPrefSize(512, 54);
				
				canvas.getChildren().add(bCerrar);
				StackPane.setAlignment(bCerrar, Pos.TOP_RIGHT);

				bCerrar.setOnMouseClicked(ev -> {
					stage.hide();
				});

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
				vbox.setPrefSize(512, 345);
				vbox.setSpacing(25);
				vbox.setAlignment(Pos.TOP_CENTER);
				vbox.getChildren().add(canvas);
				vbox.getChildren().add(error);
				mensaje.setPadding(new Insets(0,0,35,0));
				vbox.getChildren().add(mensaje);
				vbox.getChildren().add(bContinuar);

				stage.setScene(new Scene(vbox, 512, 345));
				stage.setResizable(false);
				stage.initOwner(getDesktop().getStage());
				stage.initModality(Modality.WINDOW_MODAL);
				stage.setX(getDesktop().getStage().getX()+((getDesktop().getStage().getWidth()-512)/2));
				stage.setY(getDesktop().getStage().getY()+((getDesktop().getStage().getHeight()-345)/2));
				stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
			        if (KeyCode.ESCAPE == event.getCode()) {
			            stage.close();
			        }
			    });
				stage.showAndWait();
				getDesktop().desOpacar();
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
		return 0;
	}

}
