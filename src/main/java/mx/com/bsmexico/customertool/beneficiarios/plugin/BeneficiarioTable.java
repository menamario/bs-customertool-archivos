package mx.com.bsmexico.customertool.beneficiarios.plugin;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mx.com.bsmexico.customertool.api.exporter.ExportSource;
import mx.com.bsmexico.customertool.api.exporter.ImportTarget;
import mx.com.bsmexico.customertool.api.layouts.control.EditableLayoutTable;
import mx.com.bsmexico.customertool.api.layouts.model.validation.LayoutModelValidator;

public class BeneficiarioTable extends EditableLayoutTable<Beneficiario>
		implements ImportTarget<Beneficiario>, ExportSource<Beneficiario> {

	private final int INITIAL_CAPACITY = 50;

	public BeneficiarioTable() throws IllegalArgumentException, InstantiationError {
		super(Beneficiario.class);

	}

	protected void polulate() {
		for (int idx = 0; idx <= INITIAL_CAPACITY; idx++) {
			this.data.add(new Beneficiario());
		}
	}

	@Override
	protected void addRow() {
		getItems().add(new Beneficiario());

	}

	@Override
	public void setData(List<Beneficiario> data) {
		ObservableList<Beneficiario> observableList = FXCollections.observableList(data);
		setItems(observableList);

	}

	@Override
	public List<Beneficiario> getData() {
		List<Beneficiario> exportList = new ArrayList<Beneficiario>();
		try{
			for(Beneficiario r:getItems()){
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
		return new String[] { Beneficiario.FIELD_CUENTA_BENEFICIARIO, Beneficiario.FIELD_NUMERO_LINEA_BENEFICIARIO,
				Beneficiario.FIELD_BANCO_PARTICIPANTE, Beneficiario.FIELD_TIPO_CUENTA, Beneficiario.FIELD_MONEDA,
				Beneficiario.FIELD_IMPORTE_MAXIMO_PAGAR, Beneficiario.FIELD_TIPO_PERSONA,
				Beneficiario.FIELD_RAZON_SOCIAL, Beneficiario.FIELD_NOMBRE, Beneficiario.FIELD_APELLIDO_PATERNO,
				Beneficiario.FIELD_APELLIDO_MATERNO };
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean validateTable() throws Exception {
		boolean isValid = true;
		if (this.metamodel.getValidator() != null) {
			this.validated = true;
			isValid = ((LayoutModelValidator<Beneficiario>) this.metamodel.getValidator()).isValid(getData());
			if (!isValid) {
				refresh();
			}
		}
		return isValid;
	}

	@Override
	public boolean validateModel(Beneficiario model) throws Exception {
		boolean isValid = true;
		if (this.metamodel.getValidator() != null) {
			isValid = ((LayoutModelValidator<Beneficiario>) this.metamodel.getValidator()).isValid(model);
			if (!isValid) {
				refresh();
			}
		}
		return isValid;
	}

	@Override
	public boolean isActiveModel(Beneficiario model) throws Exception {
		boolean isValid = true;
		if (this.metamodel.getValidator() != null) {
			isValid = ((LayoutModelValidator<Beneficiario>) this.metamodel.getValidator()).isActive(model);
			if (!isValid) {
				refresh();
			}
		}
		return isValid;
	}

}
