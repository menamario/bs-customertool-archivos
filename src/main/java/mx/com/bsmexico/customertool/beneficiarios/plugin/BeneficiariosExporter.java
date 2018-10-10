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
		record.add(beneficiario.getBancoParticipante()!=null?beneficiario.getBancoParticipante().trim():null);
		record.add(beneficiario.getTipoCuenta());
		record.add(beneficiario.getMoneda().trim());
		record.add(beneficiario.getImporteMaximo());
		record.add(beneficiario.getTipoPersona());
		record.add(beneficiario.getRazonSocial()!=null?beneficiario.getRazonSocial().trim():null);
		record.add(beneficiario.getNombre()!=null?beneficiario.getNombre().trim():null);
		record.add(beneficiario.getApellidoPaterno()!=null?beneficiario.getApellidoPaterno().trim():null);
		record.add(beneficiario.getApellidoMaterno()!=null?beneficiario.getApellidoMaterno().trim():null);
		record.add(beneficiario.getAlias()!=null?beneficiario.getAlias().trim():null);
		record.add(beneficiario.getRfc()!=null?beneficiario.getRfc().trim():null);
		record.add(beneficiario.getCurp()!=null?beneficiario.getCurp().trim():null);
		record.add(beneficiario.getEmail()!=null?beneficiario.getEmail().trim():null);
		return record.toArray();
	}

	@Override
	protected String[] getHeader() {
		return null;
	}

}
