package mx.com.bsmexico.customertool.beneficiarios.plugin;

import java.io.File;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;
import mx.com.bsmexico.customertool.api.process.CSVImporter;
import mx.com.bsmexico.customertool.api.process.ImportTarget;
import mx.com.bsmexico.customertool.beneficiarios.plugin.InstruccionLayoutTable.DescriptionLayout;

public class InstruccionLayoutTable extends TableView<DescriptionLayout> implements ImportTarget<DescriptionLayout> {

	private final ObservableList<DescriptionLayout> data = FXCollections.observableArrayList();

	public InstruccionLayoutTable() {
		super();
		configColumns();
		this.setEditable(true);
		final ClassLoader classLoader = getClass().getClassLoader();
		final File instrucciones = new File(
				classLoader.getResource("instrucciones/instrucciones-layout-beneficiario.csv").getFile());
		final CSVImporter<DescriptionLayout> importCsv = new CSVImporter<DescriptionLayout>(this) {

			@Override
			protected DescriptionLayout getInstance(List<String> records) {
				final DescriptionLayout desc = new DescriptionLayout();
				desc.setCampo(records.get(0));
				desc.setNombre(records.get(1));
				desc.setTipo(records.get(2));
				desc.setLongitud(records.get(3));
				desc.setObligatorio(records.get(4));
				desc.setObservaciones(records.get(5));
				return desc;
			}

			@Override
			protected String[] getHeader() {
				return null;
			}

			@Override
			protected Character getCustomDelimiter() {
				return '|';
			}

		};

		try {
			importCsv.importFile(instrucciones);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void configColumns() {
		TableColumn<DescriptionLayout, String> title = new TableColumn<>("DESCRIPCIÓN DE CONTENIDO DE CAMPOS");
		title.getColumns().addAll(getSimpleColumn("Campo", "campo", 20),
				getSimpleColumn("Nombre de campo", "nombre", 200), getSimpleColumn("Tipo", "tipo", 50),
				getSimpleColumn("Longitud", "longitud", 50), getSimpleColumn("Obligatorio", "obligatorio", 100),
				getSimpleColumn("Observaciones", "observaciones", 200));
		this.getColumns().add(title);
	}

	/**
	 * @param title
	 * @param property
	 * @param width
	 * @return
	 */
	private TableColumn<DescriptionLayout, String> getSimpleColumn(final String title, final String property,
			final int width) {
		TableColumn<DescriptionLayout, String> column = new TableColumn<>(title);
		column.setMinWidth(width);
		column.setCellValueFactory(new PropertyValueFactory<DescriptionLayout, String>(property));
		column.setCellFactory(new Callback<TableColumn<DescriptionLayout, String>, TableCell<DescriptionLayout, String>>() {
	        @Override
	        public TableCell<DescriptionLayout, String> call(
	                TableColumn<DescriptionLayout, String> param) {
	            TableCell<DescriptionLayout, String> cell = new TableCell<>();
	            Text text = new Text();
	            cell.setGraphic(text);
	            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
	            text.wrappingWidthProperty().bind(cell.widthProperty());
	            text.textProperty().bind(cell.itemProperty());
	            return cell ;
	        }
	    });
		return column;
	}

	public static class DescriptionLayout {
		private final SimpleStringProperty campo;
		private final SimpleStringProperty nombre;
		private final SimpleStringProperty tipo;
		private final SimpleStringProperty longitud;
		private final SimpleStringProperty obligatorio;
		private final SimpleStringProperty observaciones;

		/**
		 * 
		 */
		DescriptionLayout() {
			this.campo = new SimpleStringProperty();
			this.nombre = new SimpleStringProperty();
			this.tipo = new SimpleStringProperty();
			this.longitud = new SimpleStringProperty();
			this.obligatorio = new SimpleStringProperty();
			this.observaciones = new SimpleStringProperty();
		}

		/**
		 * @return the campo
		 */
		public String getCampo() {
			return campo.get();
		}

		/**
		 * @param campo the campo to set
		 */
		public void setCampo(String campo) {
			this.campo.set(campo);
		}

		/**
		 * @return the nombre
		 */
		public String getNombre() {
			return nombre.get();
		}

		/**
		 * @param nombre the nombre to set
		 */
		public void setNombre(String nombre) {
			this.nombre.set(nombre);
		}

		/**
		 * @return the tipo
		 */
		public String getTipo() {
			return tipo.get();
		}

		/**
		 * @param tipo the tipo to set
		 */
		public void setTipo(String tipo) {
			this.tipo.set(tipo);
		}

		/**
		 * @return the longitud
		 */
		public String getLongitud() {
			return longitud.get();
		}

		/**
		 * @param longitud the longitud to set
		 */
		public void setLongitud(String longitud) {
			this.longitud.set(longitud);
		}

		/**
		 * @return the obligatorio
		 */
		public String getObligatorio() {
			return obligatorio.get();
		}

		/**
		 * @param obligatorio the obligatorio to set
		 */
		public void setObligatorio(String obligatorio) {
			this.obligatorio.set(obligatorio);
		}

		/**
		 * @return the observaciones
		 */
		public String getObservaciones() {
			return observaciones.get();
		}

		/**
		 * @param observaciones the observaciones to set
		 */
		public void setObservaciones(String observaciones) {
			this.observaciones.set(observaciones);
		}

	}

	@Override
	public void setData(List<DescriptionLayout> data) {
		if (data != null) {
			this.data.addAll(data);
			this.getItems().addAll(this.data);
		}
	}
}
