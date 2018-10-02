package mx.com.bsmexico.customertool.beneficiarios.plugin;

import java.util.List;

import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mx.com.bsmexico.customertool.api.layouts.control.LayoutSpreadsheet;
import mx.com.bsmexico.customertool.api.process.ImportTarget;

public class BeneficiarioSpreadsheet extends LayoutSpreadsheet<Beneficiario> implements ImportTarget<Beneficiario> {

	public BeneficiarioSpreadsheet() throws IllegalArgumentException {
		super(Beneficiario.class);
	}

	@Override
	public void setData(List<Beneficiario> data) {
		if (data != null && data.size() > 0) {
			polulate(data);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.bsmexico.customertool.api.layouts.control.LayoutSpreadsheet#getRow(
	 * java.lang.Object)
	 */
	@Override
	protected ObservableList<SpreadsheetCell> getRow(final Beneficiario data, final int row) {
		final ObservableList<SpreadsheetCell> result = FXCollections.observableArrayList();
		int column = 0;
		SpreadsheetCell cell = SpreadsheetCellType.STRING.createCell(row, column++, 1, 1, data.getCuenta());		
		result.add(cell);
		cell = SpreadsheetCellType.STRING.createCell(row, column++, 1, 1, data.getNumLinea());
		result.add(cell);		
		cell = SpreadsheetCellType.STRING.createCell(row, column++, 1, 1, data.getBancoParticipante());		
		result.add(cell);
		cell = SpreadsheetCellType.STRING.createCell(row, column++, 1, 1, data.getTipoCuenta());
		result.add(cell);
		cell = SpreadsheetCellType.STRING.createCell(row, column++, 1, 1, data.getMoneda());
		result.add(cell);
		cell = SpreadsheetCellType.STRING.createCell(row, column++, 1, 1, data.getImporteMaximo());
		result.add(cell);
		cell = SpreadsheetCellType.STRING.createCell(row, column++, 1, 1, data.getTipoPersona());
		result.add(cell);
		cell = SpreadsheetCellType.STRING.createCell(row, column++, 1, 1, data.getRazonSocial());
		result.add(cell);
		cell = SpreadsheetCellType.STRING.createCell(row, column++, 1, 1, data.getNombre());
		result.add(cell);
		cell = SpreadsheetCellType.STRING.createCell(row, column++, 1, 1, data.getApellidoPaterno());
		result.add(cell);
		cell = SpreadsheetCellType.STRING.createCell(row, column++, 1, 1, data.getApellidoMaterno());
		result.add(cell);
		cell = SpreadsheetCellType.STRING.createCell(row, column++, 1, 1, data.getAlias());
		result.add(cell);
		cell = SpreadsheetCellType.STRING.createCell(row, column++, 1, 1, data.getRfc());
		result.add(cell);
		cell = SpreadsheetCellType.STRING.createCell(row, column++, 1, 1, data.getCurp());
		result.add(cell);
		cell = SpreadsheetCellType.STRING.createCell(row, column++, 1, 1, data.getEmail());
		result.add(cell);
		setRowEditable(result,true);
		return result;
	}
	
	@Override
	protected String[] getFieldOrder() {
		return new String[] { Beneficiario.FIELD_CUENTA_BENEFICIARIO, Beneficiario.FIELD_NUMERO_LINEA_BENEFICIARIO,
				Beneficiario.FIELD_BANCO_PARTICIPANTE, Beneficiario.FIELD_TIPO_CUENTA, Beneficiario.FIELD_MONEDA,
				Beneficiario.FIELD_IMPORTE_MAXIMO_PAGAR, Beneficiario.FIELD_TIPO_PERSONA,
				Beneficiario.FIELD_RAZON_SOCIAL, Beneficiario.FIELD_NOMBRE, Beneficiario.FIELD_APELLIDO_PATERNO,
				Beneficiario.FIELD_APELLIDO_MATERNO, Beneficiario.FIELD_ALIAS, Beneficiario.FIELD_RFC, 
				Beneficiario.FIELD_CURP, Beneficiario.FIELD_CORREO_ELECTRONICO };
	}

}
