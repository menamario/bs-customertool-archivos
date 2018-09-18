package mx.com.bsmexico.customertool.beneficiarios.plugin;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.validator.routines.EmailValidator;

import mx.com.bsmexico.customertool.api.layouts.model.validation.LayoutModelValidator;

/**
 * @author jchr
 *
 */
public class BeneficiarioValidator extends LayoutModelValidator<Beneficiario> {

	String regex = "^([A-Z,Ñ,&]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[A-Z|\\d]{3})$";
	String regexCurp = "^([A-Z][AEIOUX][A-Z]{2}\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01])[HM](?:AS|B[CS]|C[CLMSH]|D[FG]|G[TR]|HG|JC|M[CNS]|N[ETL]|OC|PL|Q[TR]|S[PLR]|T[CSL]|VZ|YN|ZS)[B-DF-HJ-NP-TV-Z]{3}[A-Z\\d])(\\d)$";
	Pattern rfcPattern = Pattern.compile(regex);
	Pattern curpPattern = Pattern.compile(regexCurp);

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
			case Beneficiario.FIELD_ALIAS:
				isValid = alias().test(model);
				break;
			case Beneficiario.FIELD_RFC:
				isValid = rfc().test(model);
				break;
			case Beneficiario.FIELD_CURP:
				isValid = curp().test(model);
				break;
			case Beneficiario.FIELD_CORREO_ELECTRONICO:
				isValid = email().test(model);
				break;
			default:
				break;
			}
		}
		return isValid;
	}

	@Override
	public boolean isValid(Beneficiario model) {
		return !isActive(model) || (model != null && cuenta().test(model) && bancoParticipante().test(model)
				&& tipoCuenta().test(model) && moneda().test(model) && importeMaximo().test(model)
				&& tipoPersona().test(model) && razonSocial().test(model) && nombre().test(model)
				&& apellidoPaterno().test(model) && apellidoMaterno().test(model)) && alias().test(model)
				&& rfc().test(model) && curp().test(model) && email().test(model);
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

	/**
	 * @return
	 */
	public Predicate<Beneficiario> cuenta() {
		return v -> {
			return (StringUtils.isNotBlank(v.getCuenta()) && v.getCuenta().length() <= 18
					&& StringUtils.isNumeric(v.getCuenta()));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Beneficiario> bancoParticipante() {
		return v -> {
			return (StringUtils.isBlank(v.getBancoParticipante()) || v.getBancoParticipante().length() == 3);
		};
	}

	/**
	 * @return
	 */
	public Predicate<Beneficiario> tipoCuenta() {
		return v -> {
			return (StringUtils.isNotBlank(v.getTipoCuenta()) && v.getTipoCuenta().length() == 2
					&& v.getTipoCuenta().matches("00|40"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Beneficiario> moneda() {
		return v -> {
			return (StringUtils.isNotBlank(v.getMoneda()) && v.getMoneda().matches("USD|MXN|EUR"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Beneficiario> importeMaximo() {
		return v -> {
			return (StringUtils.isNotBlank(v.getImporteMaximo()) && NumberUtils.isCreatable(v.getImporteMaximo())
					&& Double.valueOf(v.getImporteMaximo()) <= 9999999999999999.99);
		};
	}

	/**
	 * @return
	 */
	public Predicate<Beneficiario> tipoPersona() {
		return v -> {
			return (StringUtils.isNotBlank(v.getTipoPersona()) && v.getTipoPersona().matches("00|01"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Beneficiario> razonSocial() {
		return v -> {
			return ("01".equals(v.getTipoPersona()) && StringUtils.isNotBlank(v.getRazonSocial())
					&& v.getRazonSocial().length() <= 70)
					|| ("00".equals(v.getTipoPersona()) && StringUtils.isBlank(v.getRazonSocial()));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Beneficiario> nombre() {
		return v -> {
			return ("00".equals(v.getTipoPersona()) && StringUtils.isNotBlank(v.getNombre())
					&& v.getNombre().length() <= 25)
					|| ("01".equals(v.getTipoPersona()) && StringUtils.isBlank(v.getNombre()));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Beneficiario> apellidoPaterno() {
		return v -> {
			return ("00".equals(v.getTipoPersona()) && StringUtils.isNotBlank(v.getApellidoPaterno())
					&& v.getApellidoPaterno().length() <= 30)
					|| ("01".equals(v.getTipoPersona()) && StringUtils.isBlank(v.getApellidoPaterno()));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Beneficiario> apellidoMaterno() {
		return v -> {
			return ("00".equals(v.getTipoPersona()) && StringUtils.isNotBlank(v.getApellidoMaterno())
					&& v.getApellidoMaterno().length() <= 30)
					|| ("01".equals(v.getTipoPersona()) && StringUtils.isBlank(v.getApellidoMaterno()));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Beneficiario> alias() {
		return v -> {
			return 1 == 1;
		};
	}

	/**
	 * @return
	 */
	public Predicate<Beneficiario> rfc() {
		return v -> {
			boolean match = false;
			if (StringUtils.isNotBlank(v.getRfc())) {
				Matcher m = rfcPattern.matcher(v.getRfc());
				match = m.find();
			}

			return StringUtils.isBlank(v.getRfc())
					|| (StringUtils.isNotBlank(v.getRfc()) && "00".equals(v.getTipoPersona())
							&& v.getRfc().length() == 13 && match)
					|| (StringUtils.isNotBlank(v.getRfc()) && "01".equals(v.getTipoPersona())
							&& v.getRfc().length() == 12 && match);
		};
	}

	/**
	 * @return
	 */
	public Predicate<Beneficiario> curp() {
		return v -> {
			boolean match = false;
			if (StringUtils.isNotBlank(v.getCurp())) {
				Matcher m = curpPattern.matcher(v.getCurp());
				match = m.find();
			}
			return StringUtils.isBlank(v.getCurp()) || (StringUtils.isNotBlank(v.getCurp())
					&& v.getCurp().length() == 18 && "00".equals(v.getTipoPersona()) && match);
		};
	}

	/**
	 * @return
	 */
	public Predicate<Beneficiario> email() {
		return v -> {
			return (StringUtils.isBlank(v.getEmail())
					|| (EmailValidator.getInstance().isValid(v.getEmail()) && v.getEmail().length() <= 60));
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.bsmexico.customertool.api.layouts.model.validation.
	 * LayoutModelValidator#getValidationDescription(java.lang.String)
	 */
	@Override
	public String getValidationDescription(String fieldName) {
		String desc = StringUtils.EMPTY;
		if (StringUtils.isNotBlank(fieldName)) {
			switch (fieldName) {
			case Beneficiario.FIELD_CUENTA_BENEFICIARIO:
				desc = "Dato Obligatorio\nClabe 18 posiciones\nSabadell 11 Posiciones";
				break;
			case Beneficiario.FIELD_NUMERO_LINEA_BENEFICIARIO:
				desc = "Campo sin uso";
				break;
			case Beneficiario.FIELD_BANCO_PARTICIPANTE:
				desc = "Vacio para cuenta Sabadell\n3 Primeros dígitos de la cuenta Clabe para otros bancos";
				break;
			case Beneficiario.FIELD_TIPO_CUENTA:
				desc = "Dato Obligatorio\n01 Cuentas Sabadell\n40 Cuenta Clabe";
				break;
			case Beneficiario.FIELD_MONEDA:
				desc = "Dato Obligatorio\nMXN Pesos\nUSD Dólares\nEUR Euros";
				break;
			case Beneficiario.FIELD_IMPORTE_MAXIMO_PAGAR:
				desc = "Dato Obligatorio\nImporte Máximo por Operación\nDebe ser un dato numérico válido";
				break;
			case Beneficiario.FIELD_TIPO_PERSONA:
				desc = "Dato Obligatorio\n00 Persona física\n01 Persona moral";
				break;
			case Beneficiario.FIELD_RAZON_SOCIAL:
				desc = "Dato obligatorio si Tipo de Persona es 01-Persona Moral\nDebe estar vacío si tipo de persona es 00-Persona Física";
				break;
			case Beneficiario.FIELD_NOMBRE:
				desc = "Dato obligatorio si Tipo de Persona es 00-Persona Física\nDebe estar vacio si tipo de persona es 01-Persona Moral";
				break;
			case Beneficiario.FIELD_APELLIDO_PATERNO:
				desc = "Dato obligatorio si Tipo de Persona es 00-Persona Física\nDebe estar vacio si tipo de persona es 01-Persona Moral";
				break;
			case Beneficiario.FIELD_APELLIDO_MATERNO:
				desc = "Dato obligatorio si Tipo de Persona es 00-Persona Física\nDebe estar vacio si tipo de persona es 01-Persona Moral";
				break;
			case Beneficiario.FIELD_ALIAS:
				desc = "Dato opcional\nDescripcion Corta del Beneficiario";
				break;
			case Beneficiario.FIELD_RFC:
				desc = "Dato Opcional\nDebe Cumplir con el Formato de RFC\nDebe ser de 12 posiciones si el tipo de persona es 01-Persona Moral\nDebe ser de 13 posiciones si el tipo de persona es 00-Persona Física";
				break;
			case Beneficiario.FIELD_CURP:
				desc = "Dato Opcional\nDebe estar vacío si tipo de persona es 01-Persona Moral\nDebe cumplir con el formato de un CURP";
				break;
			case Beneficiario.FIELD_CORREO_ELECTRONICO:
				desc = "Dato obligatorio si Tipo de Persona es 00-Persona Física\nDebe estar vacio si tipo de persona es 01-Persona Moral";
				break;
			default:
				break;
			}
		}
		return desc;
	}

	@Override
	public boolean isActive(Beneficiario model) {
		if (model != null) {
			if (model.getCuenta() != null && model.getCuenta().length() > 0)
				return true;
			if (model.getNumLinea() != null && model.getNumLinea().length() > 0)
				return true;
			if (model.getBancoParticipante() != null && model.getBancoParticipante().length() > 0)
				return true;
			if (model.getTipoCuenta() != null && model.getTipoCuenta().length() > 0)
				return true;
			if (model.getMoneda() != null && model.getMoneda().length() > 0)
				return true;
			if (model.getImporteMaximo() != null && model.getImporteMaximo().length() > 0)
				return true;
			if (model.getTipoPersona() != null && model.getTipoPersona().length() > 0)
				return true;
			if (model.getRazonSocial() != null && model.getRazonSocial().length() > 0)
				return true;
			if (model.getNombre() != null && model.getNombre().length() > 0)
				return true;
			if (model.getApellidoPaterno() != null && model.getApellidoPaterno().length() > 0)
				return true;
			if (model.getApellidoMaterno() != null && model.getApellidoMaterno().length() > 0)
				return true;
			if (model.getAlias() != null && model.getAlias().length() > 0)
				return true;
			if (model.getRfc() != null && model.getRfc().length() > 0)
				return true;
			if (model.getCurp() != null && model.getCurp().length() > 0)
				return true;
			if (model.getEmail() != null && model.getEmail().length() > 0)
				return true;
		}
		return false;
	}

}
