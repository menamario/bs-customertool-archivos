package mx.com.bsmexico.customertool.beneficiarios.plugin;

import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import mx.com.bsmexico.customertool.api.layouts.model.validation.LayoutModelValidator;

/**
 * @author jchr
 *
 */
public class BeneficiarioValidator extends LayoutModelValidator<Beneficiario> {

	@Override
	public boolean isValidField(String fieldName, Beneficiario model) {
		boolean isValid = false;
		if (StringUtils.isNotBlank(fieldName) && model != null) {
			switch (fieldName) {
			case Beneficiario.FIELD_CUENTA_BENEFICIARIO:
				isValid = cuenta().test(model);
				break;
			case Beneficiario.FIELD_NUMERO_LINEA_BENEFICIARIO:
				isValid = true;
				break;
			case Beneficiario.FIELD_BANCO_PARTICIPANTE:
				isValid = bancoParticipante().test(model);
				break;
			case Beneficiario.FIELD_TIPO_CUENTA:
				isValid = tipoCuenta().test(model);
				break;
			case Beneficiario.FIELD_MONEDA:
				isValid = moneda().test(model);
				break;
			case Beneficiario.FIELD_IMPORTE_MAXIMO_PAGAR:
				isValid = importeMaximo().test(model);
				break;
			case Beneficiario.FIELD_TIPO_PERSONA:
				isValid = tipoPersona().test(model);
				break;
			case Beneficiario.FIELD_RAZON_SOCIAL:
				isValid = razonSocial().test(model);
				break;
			case Beneficiario.FIELD_NOMBRE:
				isValid = nombre().test(model);
				break;
			case Beneficiario.FIELD_APELLIDO_PATERNO:
				isValid = apellidoPaterno().test(model);
				break;
			case Beneficiario.FIELD_APELLIDO_MATERNO:
				isValid = apellidoMaterno().test(model);
				break;
			default:
				break;
			}
		}
		return isValid;
	}

	@Override
	public boolean isValid(Beneficiario model) {
		return model != null && cuenta().test(model) && bancoParticipante().test(model) && tipoCuenta().test(model)
				&& moneda().test(model) && importeMaximo().test(model) && tipoPersona().test(model)
				&& razonSocial().test(model) && nombre().test(model) && apellidoPaterno().test(model)
				&& apellidoMaterno().test(model);
	}

	@Override
	public boolean isValid(List<Beneficiario> models) {
		boolean isValid = true;
		if (models != null) {
			for (Beneficiario model : models) {
				if (!this.isValid(model)) {
					isValid = false;
					break;
				}
			}
		}
		return isValid;
	}

	public Predicate<Beneficiario> cuenta() {
		return v -> {
			return (StringUtils.isNotBlank(v.getCuenta()) && v.getCuenta().length() <= 18
					&& StringUtils.isNumeric(v.getCuenta()));
		};
	}

	public Predicate<Beneficiario> bancoParticipante() {
		return v -> {
			return (StringUtils.isBlank(v.getBancoParticipante()) || v.getBancoParticipante().length() == 3);
		};
	}

	public Predicate<Beneficiario> tipoCuenta() {
		return v -> {
			return (StringUtils.isNotBlank(v.getTipoCuenta()) && v.getTipoCuenta().length() == 2
					&& v.getTipoCuenta().matches("00|40"));
		};
	}

	public Predicate<Beneficiario> moneda() {
		return v -> {
			return (StringUtils.isNotBlank(v.getMoneda()) && v.getMoneda().matches("USD|MXN|EUR"));
		};
	}

	public Predicate<Beneficiario> importeMaximo() {
		return v -> {
			return (StringUtils.isNotBlank(v.getImporteMaximo()) && NumberUtils.isCreatable(v.getImporteMaximo())
					&& Double.valueOf(v.getImporteMaximo()) <= 9999999999999999.99);
		};
	}

	public Predicate<Beneficiario> tipoPersona() {
		return v -> {
			return (StringUtils.isNotBlank(v.getTipoPersona()) && v.getTipoPersona().matches("00|01"));
		};
	}

	public Predicate<Beneficiario> razonSocial() {
		return v -> {
			return ("01".equals(v.getTipoPersona()) && StringUtils.isNotBlank(v.getTipoPersona())
					&& v.getTipoPersona().length() <= 70)
					|| ("00".equals(v.getTipoPersona()) && StringUtils.isBlank(v.getTipoPersona()));
		};
	}

	public Predicate<Beneficiario> nombre() {
		return v -> {
			return ("00".equals(v.getTipoPersona()) && StringUtils.isNotBlank(v.getNombre())
					&& v.getNombre().length() <= 25)
					|| ("01".equals(v.getTipoPersona()) && StringUtils.isBlank(v.getNombre()));
		};
	}

	public Predicate<Beneficiario> apellidoPaterno() {
		return v -> {
			return ("00".equals(v.getTipoPersona()) && StringUtils.isNotBlank(v.getApellidoPaterno())
					&& v.getApellidoPaterno().length() <= 30)
					|| ("01".equals(v.getTipoPersona()) && StringUtils.isBlank(v.getApellidoPaterno()));
		};
	}

	public Predicate<Beneficiario> apellidoMaterno() {
		return v -> {
			return ("00".equals(v.getTipoPersona()) && StringUtils.isNotBlank(v.getApellidoMaterno())
					&& v.getApellidoMaterno().length() <= 30)
					|| ("01".equals(v.getTipoPersona()) && StringUtils.isBlank(v.getApellidoMaterno()));
		};
	}

}
