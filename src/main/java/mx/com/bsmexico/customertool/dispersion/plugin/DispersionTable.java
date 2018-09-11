package mx.com.bsmexico.customertool.dispersion.plugin;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mx.com.bsmexico.customertool.api.exporter.ExportSource;
import mx.com.bsmexico.customertool.api.exporter.ImportTarget;
import mx.com.bsmexico.customertool.api.layouts.control.EditableLayoutTable;
import mx.com.bsmexico.customertool.api.layouts.model.validation.LayoutModelValidator;
import mx.com.bsmexico.customertool.beneficiarios.plugin.Beneficiario;

public class DispersionTable extends EditableLayoutTable<Dispersion>
		implements ImportTarget<Dispersion>, ExportSource<Dispersion> {

	private final int INITIAL_CAPACITY = 50;

	public DispersionTable() throws IllegalArgumentException, InstantiationError {
		super(Dispersion.class);

	}

	protected void polulate() {
		for (int idx = 0; idx <= INITIAL_CAPACITY; idx++) {
			this.data.add(new Dispersion());
		}
	}

	@Override
	protected void addRow() {
		getItems().add(new Dispersion());

	}

	@Override
	public void setData(List<Dispersion> data) {
		ObservableList<Dispersion> observableList = FXCollections.observableList(data);
		setItems(observableList);
	}

	@Override
	public List<Dispersion> getData() {
		List<Dispersion> exportList = new ArrayList<Dispersion>();
		try{
			for(Dispersion r:getItems()){
				if(isActiveModel(r)){
					exportList.add(r);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return exportList;
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

	@SuppressWarnings("unchecked")
	@Override
	public boolean validateTable() throws Exception{
		boolean isValid = true;
		if (this.metamodel.getValidator() != null) {
			this.validated = true;
			isValid = ((LayoutModelValidator<Dispersion>) this.metamodel.getValidator()).isValid(getData());
			if (!isValid) {
				refresh();
			}
		}
		return isValid;
	}

	@Override
	public boolean validateModel(Dispersion model) throws Exception {
		boolean isValid = true;
		if (this.metamodel.getValidator() != null) {
			isValid = ((LayoutModelValidator<Dispersion>) this.metamodel.getValidator()).isValid(model);
			if (!isValid) {
				refresh();
			}
		}
		return isValid;
	}

	@Override
	public boolean isActiveModel(Dispersion model) throws Exception {
		boolean isValid = true;
		if (this.metamodel.getValidator() != null) {
			isValid = ((LayoutModelValidator<Dispersion>) this.metamodel.getValidator()).isActive(model);
			if (!isValid) {
				refresh();
			}
		}
		return isValid;
	}
}
