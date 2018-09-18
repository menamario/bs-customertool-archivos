package mx.com.bsmexico.customertool.dispersion.plugin;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import mx.com.bsmexico.customertool.api.process.FixPositionImporter;
import mx.com.bsmexico.customertool.api.process.ImportTarget;
import mx.com.bsmexico.customertool.api.process.RecordPosition;

public class DispersionTXTImporter extends FixPositionImporter<Dispersion> {
	
	private DecimalFormat decimalFormat = new DecimalFormat("############.00");
	
	public DispersionTXTImporter(ImportTarget<Dispersion> target) throws IllegalArgumentException {
		super(target);
		withTrim(true);
	}

	@Override
	protected Dispersion getInstance(List<String> record) {
		Dispersion dispersion = null;
		if (record != null && record.size() > 0) {
			if (record.get(0).startsWith("DE")) {
				dispersion = new Dispersion();
				dispersion.setTipoMovimiento(record.get(1));
				dispersion.setAplicacion(record.get(2));
				dispersion.setFecha(record.get(3));
				dispersion.setTipoTransaccion(record.get(4));
				dispersion.setCuentaCargo(record.get(5));
				dispersion.setTipoCuentaBeneficiario(record.get(6));
				dispersion.setCuentaAbono(record.get(7));
				dispersion.setTipoPersona(record.get(8));
				dispersion.setNombre(record.get(9));
				dispersion.setRfc(record.get(10));
				dispersion.setCurp(record.get(11));
				dispersion.setDivisa(record.get(12));
				dispersion.setImporte(StringUtils.isNotEmpty(record.get(13))?decimalFormat.format(Double.parseDouble(record.get(13))):"");
				dispersion.setIva(StringUtils.isNotEmpty(record.get(14))?decimalFormat.format(Double.parseDouble(record.get(14))):"");				
				dispersion.setConcepto(record.get(15));
				dispersion.setReferencia(record.get(16));
				dispersion.setCorreoElectronico(record.get(17));
				dispersion.setNumeroCelular(record.get(18));
			}
		}
		return dispersion;
	}

	@Override
	protected List<RecordPosition> getFixPositions() {
		List<RecordPosition> positions = new ArrayList<>();
		positions.add(new RecordPosition(0, 2));
		positions.add(new RecordPosition(2, 3));
		positions.add(new RecordPosition(3, 4));
		positions.add(new RecordPosition(4, 12));
		positions.add(new RecordPosition(12, 14));
		positions.add(new RecordPosition(14, 25));
		positions.add(new RecordPosition(25, 27));
		positions.add(new RecordPosition(27, 45));
		positions.add(new RecordPosition(45, 47));
		positions.add(new RecordPosition(47, 87));
		positions.add(new RecordPosition(87, 100));
		positions.add(new RecordPosition(100, 118));
		positions.add(new RecordPosition(118, 121));
		positions.add(new RecordPosition(121, 136));
		positions.add(new RecordPosition(136, 151));
		positions.add(new RecordPosition(151, 191));
		positions.add(new RecordPosition(191, 211));
		positions.add(new RecordPosition(211, 271));
		positions.add(new RecordPosition(271, 281));
		return positions;
	}

}
