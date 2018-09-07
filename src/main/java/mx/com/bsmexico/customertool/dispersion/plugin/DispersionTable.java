package mx.com.bsmexico.customertool.dispersion.plugin;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import mx.com.bsmexico.customertool.api.exporter.ExportSource;
import mx.com.bsmexico.customertool.api.exporter.ImportTarget;
import mx.com.bsmexico.customertool.api.layouts.control.LayoutTable;

public class DispersionTable extends LayoutTable<Dispersion>
		implements ImportTarget<Dispersion>, ExportSource<Dispersion> {

	private final int INITIAL_CAPACITY = 50;

	public DispersionTable() throws IllegalArgumentException, InstantiationError {
		super(Dispersion.class, new ColumnDispersionFactory());

	}

	/**
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void setColumns() throws Exception {
		String[] ids = getFieldOrder();
		if (!ArrayUtils.isEmpty(ids)) {
			TableColumn ct = null;
			for (String id : ids) {
				ct = columnFactory.<String>getEditableColumn(id, 100);
				ct.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
				table.getColumns().add(ct);
			}
		}
	}

	protected void polulate() {
		for (int idx = 0; idx <= INITIAL_CAPACITY; idx++) {
			this.data.add(new Dispersion());
		}
	}

	@Override
	protected void addRow() {
		table.getItems().add(new Dispersion());

	}

	@Override
	public void setData(List<Dispersion> data) {
		ObservableList<Dispersion> observableList = FXCollections.observableList(data);
		table.setItems(observableList);
	}

	@Override
	public List<Dispersion> getData() {
		return table.getItems();
	}

	@Override
	protected String[] getFieldOrder() {
		return new String[] { Dispersion.FIELD_TIPO_MOVIMIENTO, Dispersion.FIELD_APLICACION, Dispersion.FIELD_FECHA,
				Dispersion.FIELD_TIPO_TRANSACCION, Dispersion.FIELD_CUENTA_CARGO,
				Dispersion.FIELD_TIPO_CUENTA_BENEFICIARIO, Dispersion.FIELD_CUENTA_ABONO, Dispersion.FIELD_TIPO_PERSONA,
				Dispersion.FIELD_NOMBRE_BENEFICIARIO, Dispersion.FIELD_RFC, Dispersion.FIELD_CURP,
				Dispersion.FIELD_DIVISA, Dispersion.FIELD_IMPORTE, Dispersion.FIELD_IVA, Dispersion.FIELD_CONCEPTO,
				Dispersion.FIELD_REFERENCIA, Dispersion.FIELD_CORREO_ELECTRONICO, Dispersion.FIELD_NUMERO_CELULAR };
	}

}
