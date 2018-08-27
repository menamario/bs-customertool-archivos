package mx.com.bsmexico.customertool.beneficiarios;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import mx.com.bsmexico.customertool.api.Feature;
import mx.com.bsmexico.customertool.api.Layout;
import mx.com.bsmexico.customertool.api.NavRoute;

public class OpcionBeneficiarios extends Feature {
	

	private InputStream getImageInput(final String file) throws FileNotFoundException {
		final InputStream input = getClass().getResourceAsStream(file);		
		return input;
		
	}

	@Override
	public Layout getLayout() {

		final NavRoute.BuilderNavRoute navRuoteBuilder = new NavRoute.BuilderNavRoute("TEST");
		NavRoute route = null;
		try {
			route = navRuoteBuilder.addNode("Generacion de Archivos de Entrada", "Generacion de Archivos de Entrada",0,false,getImageInput("/img/archivos.png")).addNode("Alta de Beneficiarios", "Alta de Beneficiarios",0,false,getImageInput("/img/beneficiarios.png")).build();
					
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
		
		Pane pane = new BorderPane();
		pane.setPadding(new Insets(0,20,0,20));
		
		
		HBox headerBox1 = new HBox();
		HBox headerBox2 = new HBox();
		
		ImageView atras = null;
		ImageView importarArchivo = null;
		ImageView instrucciones = null;
		
		try {
			atras = new ImageView (new Image(this.getImageInput("/img/atras.png")));
			importarArchivo = new ImageView (new Image(this.getImageInput("/img/importarArchivo.png")));
			instrucciones = new ImageView (new Image(this.getImageInput("/img/instrucciones.png")));
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
		bInstrucciones.setStyle("-fx-background-color: transparent;");
		bInstrucciones.setTooltip(new Tooltip("Instrucciones"));
		bImportarArchivo.setGraphic(importarArchivo);
		bImportarArchivo.setStyle("-fx-background-color: transparent;");
		Tooltip ttImportarArchivo = new Tooltip("Importar Archivo");
		ttImportarArchivo.setStyle("-fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 13px;");
		bImportarArchivo.setTooltip(ttImportarArchivo);
		
		bAtras.setOnMouseClicked(evt -> {
			getMenuNavigator().show();
		});

		
		headerBox1.getChildren().add(bAtras);
		headerBox2.getChildren().add(bInstrucciones);
		headerBox2.getChildren().add(bImportarArchivo);
		headerBox2.setSpacing(10);
		HBox.setHgrow(headerBox2, Priority.ALWAYS);
		headerBox2.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		headerBox1.getChildren().add(headerBox2);
		
		
		BorderPane borderpane = new BorderPane();
		pane.setPadding(new Insets(0,30,0,30));
		Label lFormato = new Label("Formato");
		lFormato.setTextFill(Color.WHITE);
		RadioButton rbTxt = new RadioButton("txt");
		rbTxt.setTextFill(Color.WHITE);
		RadioButton rbCsv = new RadioButton("csv");
		rbCsv.setTextFill(Color.WHITE);
		ToggleGroup tgFormato = new ToggleGroup();
		rbCsv.setToggleGroup(tgFormato);
		rbTxt.setToggleGroup(tgFormato);
				

		HBox hb = new HBox();
		//hb.setPadding(new Insets(0, 10, 10, 10));
		hb.setSpacing(10);
		hb.getChildren().addAll(lFormato, rbTxt, rbCsv);
		hb.setAlignment(Pos.CENTER);
		
		borderpane.setCenter(hb);
		
		
		Button bGuardar = new Button("Guardar");
		bGuardar.setStyle("-fx-width: 139px;-fx-height: 39px;-fx-background-color: #006dff;  -fx-font-family: FranklinGothicLT-Demi;-fx-font-size: 15px;");
		bGuardar.setTextFill(Color.WHITE);
		borderpane.setRight(bGuardar);
		//borderpane.setPadding(new Insets(0,10,0,10));



		VBox vbox = new VBox(headerBox1,borderpane);
		vbox.setSpacing(10);

		
		
		
		
		((BorderPane)pane).setTop(vbox);
		
		//BorderPane.setMargin(headerBox1, new Insets(0,10,0,10));
		
		
		//pane.setStyle("-fx-background-color: grey");
//		pane.setMinSize(500, getMenuNavigator().getDesktop().getMinHeight());
//		Label label = new Label("Estoy en la opcion de beneficiarios");
//		label.setTextFill(Color.WHITE);
//		Button hideMenu = new Button("Hide Menu");
//		hideMenu.setOnMouseClicked(evt -> {
//			getMenuNavigator().hide();
//		});
//		Button showMenu = new Button("Show Menu");
//		showMenu.setOnMouseClicked(evt -> {
//			getMenuNavigator().show();
//		});
//		HBox box = new HBox(label,hideMenu,showMenu);
//		pane.getChildren().add(box);
		getDesktop().setWorkArea(pane);
	}

}
