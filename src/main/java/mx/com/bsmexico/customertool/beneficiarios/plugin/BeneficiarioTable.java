package mx.com.bsmexico.customertool.beneficiarios.plugin;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mx.com.bsmexico.customertool.api.exporter.ExportSource;
import mx.com.bsmexico.customertool.api.exporter.ImportTarget;
import mx.com.bsmexico.customertool.api.layouts.control.ColumnTableFactoryAbstract;
import mx.com.bsmexico.customertool.api.layouts.control.LayoutTable;

public class BeneficiarioTable extends LayoutTable<Beneficiario> implements ImportTarget<Beneficiario>, ExportSource<Beneficiario> {

	private final int INITIAL_CAPACITY = 50;

	public BeneficiarioTable(final ColumnTableFactoryAbstract<Beneficiario> columnFactory)
			throws IllegalArgumentException, InstantiationError {
		super(columnFactory);

	}

	protected void polulate() {
		for (int idx = 0; idx <= INITIAL_CAPACITY; idx++) {
			this.data.add(new Beneficiario());
		}
	}

	@Override
	protected void addRow() {
		System.out.println("adding rows");
		table.getItems().add(new Beneficiario());

	}

	@Override
	protected String[] getFieldOrder() {
		return new String[] { Beneficiario.FIELD_CUENTA_BENEFICIARIO, Beneficiario.FIELD_NUMERO_LINEA_BENEFICIARIO,
				Beneficiario.FIELD_BANCO_PARTICIPANTE, Beneficiario.FIELD_TIPO_CUENTA, Beneficiario.FIELD_MONEDA,
				Beneficiario.FIELD_IMPORTE_MAXIMO_PAGAR, Beneficiario.FIELD_TIPO_PERSONA,
				Beneficiario.FIELD_RAZON_SOCIAL, Beneficiario.FIELD_NOMBRE, Beneficiario.FIELD_APELLIDO_PATERNO,
				Beneficiario.FIELD_APELLIDO_MATERNO };
	}

	@Override
	public void setData(List<Beneficiario> data) {
		ObservableList<Beneficiario> observableList = FXCollections.observableList(data);
		table.setItems(observableList);
		
	}

	@Override
	public List<Beneficiario> getData() {
		return table.getItems();
	}

}
