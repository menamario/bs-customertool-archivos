package mx.com.bsmexico.customertool.beneficiarios.plugin;

import java.util.ArrayList;
import java.util.List;

import mx.com.bsmexico.customertool.api.process.CSVExporter;
import mx.com.bsmexico.customertool.api.process.ExportSource;

public class BeneficiariosExporter extends CSVExporter<Beneficiario> {

	/**
	 * @param source
	 */
	public BeneficiariosExporter(ExportSource<Beneficiario> source) {
		super(source);
	}

	@Override
	protected Object[] getRecord(final Beneficiario beneficiario) {
		final List<Object> record = new ArrayList<>();
		record.add(beneficiario.getCuenta());
		record.add(beneficiario.getNumLinea());
		record.add(beneficiario.getBancoParticipante());
		record.add(beneficiario.getTipoCuenta());
		record.add(beneficiario.getMoneda());
		record.add(beneficiario.getImporteMaximo());
		record.add(beneficiario.getTipoPersona());
		record.add(beneficiario.getRazonSocial());
		record.add(beneficiario.getNombre());
		record.add(beneficiario.getApellidoPaterno());
		record.add(beneficiario.getApellidoMaterno());
		return record.toArray();
	}

	@Override
	protected String[] getHeader() {
		return new String[] { "CuentaBeneficiario", "NumeroLinea", "BancoParticipante", "TipoCuenta", "Moneda",
				"ImporteMaximo", "TipoPersona", "RazonSocial", "Nombre", "ApellidoPaterno", "ApellidoMaterno" };
	}

}
