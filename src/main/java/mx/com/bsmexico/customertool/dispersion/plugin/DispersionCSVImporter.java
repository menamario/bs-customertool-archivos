package mx.com.bsmexico.customertool.dispersion.plugin;

import java.util.List;

import mx.com.bsmexico.customertool.api.process.CSVImporter;
import mx.com.bsmexico.customertool.api.process.ImportTarget;

/**
 * @author jchr
 *
 */
public class DispersionCSVImporter extends CSVImporter<Dispersion> {
	
	public DispersionCSVImporter(ImportTarget<Dispersion> target) throws IllegalArgumentException {
		super(target);	
	}

	@Override
	protected Dispersion getInstance(final List<String> record) {
		Dispersion dispersion = null;
		if (record != null && record.size() > 0) {			
			if("DE".equals(record.get(0))) {
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
				dispersion.setImporte(record.get(13));
				dispersion.setIva(record.get(14));
				dispersion.setConcepto(record.get(15));
				dispersion.setReferencia(record.get(16));
				dispersion.setCorreoElectronico(record.get(17));
				dispersion.setNumeroCelular(record.get(18));
			}
		}		
		return dispersion;
	}

	@Override
	protected String[] getHeader() {
		return null;
	}
	
	@Override
	protected Character getCustomDelimiter() {
		return '|';
	}
}
