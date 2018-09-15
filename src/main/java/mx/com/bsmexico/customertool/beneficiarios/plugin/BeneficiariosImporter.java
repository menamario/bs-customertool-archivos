package mx.com.bsmexico.customertool.beneficiarios.plugin;

import java.util.List;

import mx.com.bsmexico.customertool.api.process.CSVImporter;
import mx.com.bsmexico.customertool.api.process.ImportTarget;

/**
 * @author jchr
 *
 */
public class BeneficiariosImporter extends CSVImporter<Beneficiario> {
	
	public BeneficiariosImporter(ImportTarget<Beneficiario> target) throws IllegalArgumentException {
		super(target);	
	}

	@Override
	protected Beneficiario getInstance(final List<String> record) {
		final Beneficiario beneficiario = new Beneficiario();
		
		
			for(int i=0;i<=10;i++){
				try{
					record.get(i);
				}catch(IndexOutOfBoundsException ex){
					record.add(i, "");
				}
			}
		
		beneficiario.setCuenta(record.get(0));

		beneficiario.setNumLinea(record.get(1));

		beneficiario.setBancoParticipante(record.get(2));
		beneficiario.setTipoCuenta(record.get(3));

		beneficiario.setMoneda(record.get(4));

		beneficiario.setImporteMaximo(record.get(5));

		beneficiario.setTipoPersona(record.get(6));

		beneficiario.setRazonSocial(record.get(7));

		beneficiario.setNombre(record.get(8));
		
		beneficiario.setApellidoPaterno(record.get(9));

		beneficiario.setApellidoMaterno(record.get(10));

		

		return beneficiario;
	}

	@Override
	protected String[] getHeader() {
		return null;/*new String[] { "CuentaBeneficiario", "NumeroLinea", "BancoParticipante", "TipoCuenta", "Moneda",
				"ImporteMaximo", "TipoPersona", "RazonSocial", "Nombre", "ApellidoPaterno", "ApellidoMaterno" };*/
	}

}
