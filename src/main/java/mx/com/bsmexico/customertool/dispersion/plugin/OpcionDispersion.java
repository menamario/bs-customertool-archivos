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
import mx.com.bsmexico.customertool.api.process.Exporter;
import mx.com.bsmexico.customertool.api.process.Importer;
import mx.com.bsmexico.customertool.beneficiarios.plugin.InstruccionLayoutTable;

public class OpcionDispersion extends Feature {

	DispersionTable t = null;
	private SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	int hashCodeGuardado;
	RadioButton rbTxt = null;
	RadioButton rbCsv = null;
	Button bCerrar = new Button();
	ImageView error = new ImageView();
	ImageView check = new ImageView();
	double xOffset=0;
	double yOffset=0;

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
					.addNode("Generación de archivos de entrada", "Generación de archivos de entrada", 0, false,
							getImageInput("/img/archivos.png"))
					.addNode("Dispersión de pagos", "Dispersión de pagos", 0, false,
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
		bImportarArchivo.setText("Importar archivo");
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
				mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
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
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("csv and txt files", "*.csv", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		headerBox1.getChildren().add(bAtras);
		headerBox1.setSpacing(40);
		Label l = new Label("    Dispersión de pagos    ");
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
		lFormato.setAlignment(Pos.BOTTOM_LEFT);
		lFormato.setTextFill(Color.WHITE);
		lFormato.setStyle("-fx-font-family: FranklinGothicLT;-fx-font-size: 18px");
		
		HBox txthb = new HBox();
		txthb.setAlignment(Pos.CENTER_LEFT);
		rbTxt = new RadioButton();
		rbTxt.setAlignment(Pos.CENTER_LEFT);
		txthb.setSpacing(10);
		Label ltxt= new Label(".txt");
		ltxt.setAlignment(Pos.CENTER_LEFT);
		ltxt.setTextFill(Color.WHITE);
		ltxt.setStyle("-fx-font-family: FranklinGothicLT;-fx-font-weight:bold;-fx-font-size: 18px");
		txthb.getChildren().addAll(rbTxt,ltxt);
		
		HBox csvhb = new HBox();
		csvhb.setAlignment(Pos.CENTER_LEFT);
		csvhb.setSpacing(10);
		rbCsv = new RadioButton();
		rbCsv.setSelected(true);
		rbCsv.setAlignment(Pos.CENTER_LEFT);
		
		Label lcsv= new Label(".csv");
		lcsv.setTextFill(Color.WHITE);
		lcsv.setStyle("-fx-font-family: FranklinGothicLT;-fx-font-weight:bold;-fx-font-size: 18px");
		csvhb.getChildren().addAll(rbCsv,lcsv);
		lcsv.setAlignment(Pos.CENTER_LEFT);
		
		ToggleGroup tgFormato = new ToggleGroup();
		rbCsv.setToggleGroup(tgFormato);
		rbTxt.setToggleGroup(tgFormato);

		HBox hb = new HBox();

		hb.setSpacing(50);
		hb.getChildren().addAll(lFormato, txthb,csvhb);
		hb.setAlignment(Pos.CENTER_RIGHT);
		hb.setPadding(new Insets(0, 100, 0, 0));

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
						"Banco Sabadell agradece su preferencia, a continuación se detallan los pasos que debes seguir para\ngenerar los layouts de dispersión de pagos. (CSV y TXT)");
				instruccionesLabel.setWrapText(true);
				instruccionesLabel.setTextAlignment(TextAlignment.CENTER);
				instruccionesLabel
						.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 14px;-fx-font-weight: bold");
				instruccionesLabel.setTextFill(Color.web("#828488"));
				instruccionesLabel.setMinHeight(40);
				StackPane p = new StackPane();
				p.setPadding(new Insets(20, 0, 20, 0));
				p.setStyle("-fx-background-color: #white");
				p.getChildren().add(instruccionesLabel);

				stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
				stage.setTitle("Archivos Bantotal - Dispersion - Instrucciones");

				

				ImageView lc1 = null;
				ImageView lc2 = null;
				ImageView lc3 = null;
				ImageView lc4 = null;
				ImageView lc5 = null;
				ImageView lc6 = null;
				ImageView lc7 = null;
				ImageView lc8 = null;
				ImageView lc9 = null;
				ImageView lc11 = null;
				ImageView lc10 = null;
				ImageView insIv = null;
				ImageView insIv2 = null;

				try {
					lc1 = new ImageView(new Image(getImageInput("/img/littleCheck.png")));
					lc2 = new ImageView(new Image(getImageInput("/img/littleCheck.png")));
					lc3 = new ImageView(new Image(getImageInput("/img/littleCheck.png")));
					lc4 = new ImageView(new Image(getImageInput("/img/littleCheck.png")));
					lc5 = new ImageView(new Image(getImageInput("/img/littleCheck.png")));
					lc6 = new ImageView(new Image(getImageInput("/img/littleCheck.png")));
					lc7 = new ImageView(new Image(getImageInput("/img/littleCheck.png")));
					lc8 = new ImageView(new Image(getImageInput("/img/littleCheck.png")));
					lc9 = new ImageView(new Image(getImageInput("/img/littleCheck.png")));
					lc10 = new ImageView(new Image(getImageInput("/img/littleCheck.png")));
					lc11 = new ImageView(new Image(getImageInput("/img/littleCheck.png")));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
                TextFlow flow = new TextFlow();
				
				
				
				Text t0 = new Text("Instrucciones generales\n\n    ");
				t0.setStyle("-fx-fill: black;-fx-font-weight: bold");
				

				Text t1 = new Text(" Podrás generar tus archivos para Dispersión de Pagos capturando registro por registro o por medio de la\n         importación de archivos.\n    ");
				t1.setStyle("-fx-fill: #black");
				Text t2 = new Text(" En caso de capturar algún dato erróneo, la aplicación marcará en color amarillo la celda correspondiente al error.\n    ");
				t2.setStyle("-fx-fill: black");
				Text t3 = new Text(" Las instrucciones para el llenado de cada uno de los campos que integran el layout están disponibles en la hoja\n         llamada \"Descripción de campos\"\n    ");
				t3.setStyle("-fx-fill: black");
				Text t4 = new Text(" La aplicación te permitirá capturar datos en mayúsculas y minúsculas.\n    ");
				t4.setStyle("-fx-fill: black");
				Text t5 = new Text(" Al guardar el archivo, la aplicación te solicitará que indiques la carpeta de tu preferencia.\n    ");
				t5.setStyle("-fx-fill: black");
				Text t6 = new Text(" La aplicación te indicará la nomenclatura con la que debes asignarle el nombre al archivo. Comenzando por la\n         fecha en formato AAAAMMDD, tu número de cliente a 9 dígitos XXXXXXXXX y un consecutivo a 3 dígitos XXX,\n         separados por guiones. Solo deberás sustituir las X por tu número de cliente a 9 dígitos (anteponer ceros a la\n         izquierda en caso necesario) y las N por el consecutivo de tu archivo anteponiendo ceros a la izquierda hasta\n         completar los 3 dígitos.\n    ");
				t6.setStyle("-fx-fill: black");
				Text t7 = new Text(" Los archivos que generes no podrán tener combinación monedas, por lo que deberán generarse por separado.\n    ");
				t7.setStyle("-fx-fill: black");
				Text t8 = new Text(" Solo podrán hacerse dispersiones en dólares entre cuentas Sabadell, tanto propias como terceros.\n    ");
				t8.setStyle("-fx-fill: black");
				Text t9 = new Text(" No se podrá operar SPID masivo.\n\n");
				t9.setStyle("-fx-fill: black");
				
				Text t10 = new Text("Captura\n\n    ");
				t10.setStyle("-fx-fill: black;-fx-font-weight: bold");

				
				
				Text t11 = new Text("1.");Text t111 = new Text(" Para la captura registro por registro, deberás ingresar los datos de tus pagos comenzando por el \"Tipo de\n        Movimiento\" con base en lo indicado en la hoja llamada \"Descripción de campos\".\n    ");
				t11.setStyle("-fx-fill: black;-fx-font-weight:bold");
				Text t12 = new Text("2.");Text t121 = new Text(" Para la importación de archivos, deberás oprimir el botón \"Importar archivo\", la aplicación te mostrará el explorador\n        de archivos, a través del cual deberás elegir el archivo a importar.\n    ");
				t12.setStyle("-fx-fill: black;-fx-font-weight:bold");
				Text t13 = new Text("3.");Text t131 = new Text(" Indica por medio del radiobottom \"Formato\" el tipo de archivo que deseas generar (CSV o TXT).\n    ");
				t13.setStyle("-fx-fill: black;-fx-font-weight:bold");
				Text t14 = new Text("4.");Text t141 = new Text(" Ya habiendo capturado los datos registro por registro y una vez que la aplicación no detecte ningún error en los\n        campos, deberás oprimir el botón \"Guardar\".\n    ");
				t14.setStyle("-fx-fill: black;-fx-font-weight:bold");
				Text t99 = new Text("    Nota: ");
				t99.setStyle("-fx-fill: black;-fx-font-weight: bold");
				Text t15 = new Text("En caso de que intentes guardar el archivo sin haber validado que los datos estén correctos la aplicación te\n        mostrará una ventana emergente que indique \"Error en los datos proporcionados\".\n    ");
				t15.setStyle("-fx-fill: black;");
				Text t16 = new Text("5.");Text t161 = new Text(" Deberás indicar la carpeta en la cual deseas que el archivo quede alojado.\n    ");
				t16.setStyle("-fx-fill: black;-fx-font-weight:bold");
			    Text t17 = new Text("6.");Text t171 = new Text(" Deberás también indicar el nombre (nomenclatura) del archivo con base en la indicada por la misma aplicación.\n        (AAAAMMDD_XXXXXXXXX_NNN)\n    ");
			    t17.setStyle("-fx-fill: black;-fx-font-weight:bold");
	            Text t18 = new Text("7.");Text t181 = new Text(" Oprime el botón \"Guardar\" en la ventana del explorador de archivos y la aplicación depositará el archivo en la\n        carpeta indicada.\n\n");
	            t18.setStyle("-fx-fill: black;-fx-font-weight:bold");
		 
	            Text t19 = new Text("Notas:\n\n    ");
				t19.setStyle("-fx-fill: black;-fx-font-weight: bold");

				Text t20 = new Text(" La aplicación generará el Header Alto en el que se muestran las cifras control por cada una de las cuentas de cargo\n         contenidas en el archivo en forma de bloques.\n    ");
				t20.setStyle("-fx-fill: black");
				Text t21 = new Text(" De la misma forma será generado un solo Header Bajo en el que se muestran las cifras control totales del archivo,\n         considerando la suma de cada uno de los headers altos.    \n\n");
				t21.setStyle("-fx-fill: black");
				
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
				flow.getChildren().add(lc6);
				flow.getChildren().add(t6);
				flow.getChildren().add(lc7);
				flow.getChildren().add(t7);
				flow.getChildren().add(lc8);
				flow.getChildren().add(t8);
				flow.getChildren().add(lc9);
				flow.getChildren().add(t9);
				flow.getChildren().addAll(t10,t11,t111,t12,t121,t13,t131,t14,t141,t99,t15,t16,t161,t17,t171,t18,t181,t19,lc10,t20,lc11,t21);
				flow.setStyle("-fx-background-color:white;-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 14px;-fx-fill:black");
				flow.setMinWidth(746);
				

				ScrollPane scrollPaneGenerales = new ScrollPane();
				scrollPaneGenerales.setPrefSize(800, 600);
				scrollPaneGenerales.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
				scrollPaneGenerales.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
				scrollPaneGenerales.setContent(flow);
				scrollPaneGenerales.setPadding(new Insets(35,30,0,30));
				scrollPaneGenerales.setStyle("-fx-background-color:white;");
				
				ScrollPane scrollPane = new ScrollPane();
				scrollPane.setPrefSize(800, 600);
				scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
				scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
				scrollPane.setContent(insIv);

				
				ScrollPane scrollPane2 = new ScrollPane();
				scrollPane2.setPrefSize(800, 600);
				scrollPane2.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
				scrollPane2.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
				scrollPane2.setContent(insIv2);
				
				LayoutDispersionCsv tvcsv  = new LayoutDispersionCsv();
				LayoutDispersionTxt tvtxt  = new LayoutDispersionTxt();
				
				TabPane tabPane = new TabPane();
				Tab tabInstrucciones = new Tab("    Instrucciones    ");
				Tab tabCampos = new Tab("    Descripción de campos csv    ");
				Tab tabCamposTxt = new Tab("    Descripción de campos txt    ");
				tabInstrucciones.setContent(scrollPaneGenerales);
				tabInstrucciones.setClosable(false);
				tabCampos.setContent(tvcsv);
				tabCampos.setClosable(false);
				tabCamposTxt.setContent(tvtxt);
				tabCamposTxt.setClosable(false);
				tabPane.getTabs().addAll(tabInstrucciones, tabCampos,tabCamposTxt);

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
				if (file != null) {

					Importer ddImporter = null;
					if (file.getName().toUpperCase().endsWith("CSV")) {
						ddImporter = new DispersionCSVImporter(t);
					} else {
						ddImporter = new DispersionTXTImporter(t);
					}

					try {
						ddImporter.importFile(file);
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
						stage.setTitle("Archivos Bantotal - Dispersion - Formato de Archivo Incorrecto");

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
						mensaje.setPadding(new Insets(0, 0, 35, 0));
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
						stage.setTitle("Archivos Bantotal - Dispersion - Archivo Guardado");

						Label mensaje = new Label("El archivo fue guardado exitosamente");
						mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
						mensaje.setTextFill(Color.web("#777777"));

						Button bContinuar = new Button("Continuar");
						bContinuar.setStyle(
								"-fx-background-color: #006dff;  -fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 15px;");
						bContinuar.setPrefSize(140, 40);
						bContinuar.setTextFill(Color.WHITE);

						bContinuar.setOnMouseClicked(evt -> {
							stage.hide();
						});

						VBox vbox = new VBox();
						vbox.setSpacing(25);
						vbox.setAlignment(Pos.TOP_CENTER);
						vbox.setPrefSize(512, 345);
						vbox.getChildren().add(canvas);
						vbox.getChildren().add(check);
						mensaje.setPadding(new Insets(0, 0, 35, 0));
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
						return false;
					}
				}
				return false;

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

				stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoSabadellCircle.png")));
				stage.setTitle("Archivos Bantotal - Dispersion - Datos Incorrectos");

				Label mensaje = new Label("Error en los datos proporcionados");
				mensaje.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 20px;");
				mensaje.setTextFill(Color.web("#777777"));

				Button bContinuar = new Button("Continuar");
				bContinuar.setStyle(
						"-fx-background-color: #006dff;  -fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 15px;");
				bContinuar.setPrefSize(140, 40);
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
				mensaje.setPadding(new Insets(0, 0, 35, 0));
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
		// TODO Auto-generated method stub
		return 0;
	}
}
