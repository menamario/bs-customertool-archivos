package mx.com.bsmexico.customertool.beneficiarios.plugin;

import java.util.HashSet;
import java.util.Set;

import mx.com.bsmexico.customertool.api.layouts.control.ColumnTableFactoryAbstract;

/**
 * @author jchr
 *
 */
public class ColumnBeneficiarioFactory extends ColumnTableFactoryAbstract<Beneficiario> {

	public ColumnBeneficiarioFactory() throws IllegalArgumentException {
		super(Beneficiario.class);
	}
	@Override
	public Set<String> getFieldIds() {
		final Set<String> ids = new HashSet<>();
		ids.add(Beneficiario.FIELD_CUENTA_BENEFICIARIO);
		ids.add(Beneficiario.FIELD_NUMERO_LINEA_BENEFICIARIO);
		ids.add(Beneficiario.FIELD_BANCO_PARTICIPANTE);
		ids.add(Beneficiario.FIELD_TIPO_CUENTA);
		ids.add(Beneficiario.FIELD_MONEDA);
		ids.add(Beneficiario.FIELD_IMPORTE_MAXIMO_PAGAR);
		ids.add(Beneficiario.FIELD_TIPO_PERSONA);
		ids.add(Beneficiario.FIELD_RAZON_SOCIAL);
		ids.add(Beneficiario.FIELD_NOMBRE);
		ids.add(Beneficiario.FIELD_APELLIDO_PATERNO);
		ids.add(Beneficiario.FIELD_APELLIDO_MATERNO);
		return ids;
	}
}
