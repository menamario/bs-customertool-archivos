package mx.com.bsmexico.customertool.dispersion.plugin;

import java.util.List;

import mx.com.bsmexico.customertool.api.process.CSVImporter;
import mx.com.bsmexico.customertool.api.process.ImportTarget;

/**
 * @author jchr
 *
 */
public class DispersionImporter extends CSVImporter<Dispersion> {
	
	public DispersionImporter(ImportTarget<Dispersion> target) throws IllegalArgumentException {
		super(target);	
	}

	@Override
	protected Dispersion getInstance(final List<String> record) {
		final Dispersion dispersion = new Dispersion();
		dispersion.setTipoMovimiento(record.get(0));
		dispersion.setAplicacion(record.get(1));
		dispersion.setFecha(record.get(2));
		dispersion.setTipoTransaccion(record.get(3));
		dispersion.setCuentaCargo(record.get(4));
		dispersion.setTipoCuentaBeneficiario(record.get(5));
		dispersion.setCuentaAbono(record.get(6));
		dispersion.setTipoPersona(record.get(7));
		dispersion.setNombre(record.get(8));
		dispersion.setRfc(record.get(9));
		dispersion.setCurp(record.get(10));
		dispersion.setDivisa(record.get(11));
		dispersion.setImporte(record.get(12));
		dispersion.setIva(record.get(13));
		dispersion.setConcepto(record.get(14));
		dispersion.setReferencia(record.get(15));
		dispersion.setCorreoElectronico(record.get(16));
		dispersion.setNumeroCelular(record.get(17));
		
		
		return dispersion;
	}

	@Override
	protected String[] getHeader() {
		return null;/*new String[] { "CuentaBeneficiario", "NumeroLinea", "BancoParticipante", "TipoCuenta", "Moneda",
				"ImporteMaximo", "TipoPersona", "RazonSocial", "Nombre", "ApellidoPaterno", "ApellidoMaterno" };*/
	}

}
