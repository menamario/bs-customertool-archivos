package mx.com.bsmexico.customertool.beneficiarios;

import mx.com.bsmexico.customertool.api.layouts.ColumnTableFactoryAbstract;

/**
 * @author jchr
 *
 */
public class ColumnBeneficiarioFactory extends ColumnTableFactoryAbstract<Beneficiario> {

	public ColumnBeneficiarioFactory() throws IllegalArgumentException {
		super(Beneficiario.class);
	}
}
