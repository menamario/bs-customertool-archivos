package mx.com.bsmexico.customertool.dispersion.plugin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.validator.routines.EmailValidator;

import mx.com.bsmexico.customertool.api.layouts.model.validation.LayoutModelValidator;

public class DispersionValidator extends LayoutModelValidator<Dispersion> {

	private SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	String regex = "^([A-Z,Ñ,&]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[A-Z|\\d]{3})$";
	Pattern rfcPattern = Pattern.compile(regex);
	
	@Override
	public boolean isValidField(String fieldName, Dispersion model) {
		boolean isValid = false;
		if (StringUtils.isNotBlank(fieldName) && model != null) {
			switch (fieldName) {
			case Dispersion.FIELD_APLICACION:
				isValid = aplicacion().test(model);
				break;
			case Dispersion.FIELD_CONCEPTO:
				isValid = concepto().test(model);
				break;
			case Dispersion.FIELD_CORREO_ELECTRONICO:
				isValid = email().test(model);
				break;
			case Dispersion.FIELD_CUENTA_ABONO:
				isValid = cuentaAbono().test(model);
				break;
			case Dispersion.FIELD_CUENTA_CARGO:
				isValid = cuentaCargo().test(model);
				break;
			case Dispersion.FIELD_CURP:
				isValid = curp().test(model);
				break;
			case Dispersion.FIELD_FECHA:
				isValid = fecha().test(model);
				break;
			case Dispersion.FIELD_IMPORTE:
				isValid = importe().test(model);
				break;
			case Dispersion.FIELD_IVA:
				isValid = iva().test(model);
				break;
			case Dispersion.FIELD_NOMBRE_BENEFICIARIO:
				isValid = nombreBeneficiario().test(model);
				break;
			case Dispersion.FIELD_NUMERO_CELULAR:
				isValid = numeroTel().test(model);
				break;
			case Dispersion.FIELD_REFERENCIA:
				isValid = referencia().test(model);
				break;
			case Dispersion.FIELD_RFC:
				isValid = rfc().test(model);
				break;
			case Dispersion.FIELD_TIPO_CUENTA_BENEFICIARIO:
				isValid = tipoCuentaBeneficiario().test(model);
				break;
			case Dispersion.FIELD_TIPO_MOVIMIENTO:
				isValid = tipoMovimiento().test(model);
				break;
			case Dispersion.FIELD_TIPO_PERSONA:
				isValid = tipoPersona().test(model);
				break;
			case Dispersion.FIELD_TIPO_TRANSACCION:
				isValid = tipoTransaccion().test(model);
				break;
			case Dispersion.FIELD_DIVISA:
				isValid = divisa().test(model);
				break;
			default:
				break;
			}
		}
		return isValid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.bsmexico.customertool.api.layouts.model.validation.
	 * LayoutModelValidator#isValid(java.lang.Object)
	 */
	@Override
	public boolean isValid(Dispersion model) {
		boolean isValid = true;
		if (!isEmptyModel(model)) {
			isValid = model != null && aplicacion().test(model) && concepto().test(model) && email().test(model)
					&& cuentaAbono().test(model) && cuentaCargo().test(model) && curp().test(model)
					&& fecha().test(model) && importe().test(model) && iva().test(model)
					&& nombreBeneficiario().test(model) && numeroTel().test(model) && referencia().test(model)
					&& rfc().test(model) && tipoCuentaBeneficiario().test(model) && tipoMovimiento().test(model)
					&& tipoPersona().test(model) && tipoTransaccion().test(model) && divisa().test(model);
		}
		return isValid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.bsmexico.customertool.api.layouts.model.validation.
	 * LayoutModelValidator#isValid(java.util.List)
	 */
	@Override
	public boolean isValid(List<Dispersion> models) {
		boolean isValid = true;
		if (models != null) {
			for (Dispersion model : models) {
				if (!this.isValid(model)) {
					isValid = false;
					break;
				}
			}
		}
		return isValid;
	}

	@Override
	public String getValidationDescription(String fieldName) {
		String desc = StringUtils.EMPTY;
		if (StringUtils.isNotBlank(fieldName)) {
			switch (fieldName) {
			case Dispersion.FIELD_APLICACION:
				desc = "Dato Obligatorio\nH Mismo dia\nP Programado";
				break;
			case Dispersion.FIELD_CONCEPTO:
				desc = "Dato Obligatorio\nDescripción del Pago\nMáximo 30 caracteres";
				break;
			case Dispersion.FIELD_CORREO_ELECTRONICO:
				desc = "Dato Opcional\nDebe ser un correo electrónico válido\nMáximo 60 caracteres";
				break;
			case Dispersion.FIELD_CUENTA_ABONO:
				desc = "Dato Obligatorio\nClabe 18 posiciones\nSabadell 11 Posiciones";
				break;
			case Dispersion.FIELD_CUENTA_CARGO:
				desc = "Dato Obligatorio\nCuenta Sabadell 11 posiciones";
				break;
			case Dispersion.FIELD_CURP:
				desc = "Dato Opcional\nDebe estar vacío si tipo de persona es PM-Persona Moral";
				break;
			case Dispersion.FIELD_FECHA:
				desc = "Dato Obligatorio si la Aplicación es P-Programada\nEl formato debe ser aaaammdd";
				break;
			case Dispersion.FIELD_IMPORTE:
				desc = "Dato Obligatorio\nImporte de la Operación\nDebe ser un dato numérico válido";
				break;
			case Dispersion.FIELD_IVA:
				desc = "Dato Opcional\nDebe ser un dato numérico válido\nSi se captura el IVA el RFC no debe estar en blanco\nEl IVA no puede exceder el importe";
				break;
			case Dispersion.FIELD_NOMBRE_BENEFICIARIO:
				desc = "Dato Obligatorio\nNombre o Razón social del beneficiariode la transferencia\nMáximo 40 caracteres";
				break;
			case Dispersion.FIELD_NUMERO_CELULAR:
				desc = "Dato Opcional\nDebe ser un dato numérico";
				break;
			case Dispersion.FIELD_REFERENCIA:
				desc = "Dato Obligatorio\nDebe ser un dato numérico\nDebe ser al menos 7 dígitos\nMaximo 20 dígitos";
				break;
			case Dispersion.FIELD_RFC:
				desc = "Dato Opcional\nDebe Cumplir con el Formato de RFC\nDebe ser de 12 posiciones si el tipo de persona es PM-Persona Moral\nDebe ser de 13 posiciones si el tipo de persona es PF-Persona Física";
				break;
			case Dispersion.FIELD_TIPO_CUENTA_BENEFICIARIO:
				desc = "Dato Obligatorio\n01 Sabadell\n40 CLABE\n03 TDD/TDC\n10 LTM";
				break;
			case Dispersion.FIELD_TIPO_MOVIMIENTO:
				desc = "Dato Obligatorio\n0 Pago\n1 Cancelación";
				break;
			case Dispersion.FIELD_TIPO_PERSONA:
				desc = "Dato Obligatorio\nPF Persona Física\nPM Persona Moral";
				break;
			case Dispersion.FIELD_TIPO_TRANSACCION:
				desc = "Dato Obligatorio\n00 Genérico\n01 Nómina\n02 Pago a Proveedores\n03Pago de Viáticos";
				break;
			case Dispersion.FIELD_DIVISA:
				desc = "Dato Obligatorio\nMXP Pesos\nUSD Dólares\nEUR Euros";
				break;
			default:
				break;
			}
		}
		return desc;
	}

	/**
	 * @param model
	 * @return
	 */
	private boolean isEmptyModel(final Dispersion model) {
		return StringUtils.isBlank(model.getAplicacion()) && StringUtils.isBlank(model.getConcepto())
				&& StringUtils.isBlank(model.getCorreoElectronico()) && StringUtils.isBlank(model.getCuentaAbono())
				&& StringUtils.isBlank(model.getCuentaCargo()) && StringUtils.isBlank(model.getCurp())
				&& StringUtils.isBlank(model.getFecha()) && StringUtils.isBlank(model.getImporte())
				&& StringUtils.isBlank(model.getIva()) && StringUtils.isBlank(model.getNombre())
				&& StringUtils.isBlank(model.getNumeroCelular()) && StringUtils.isBlank(model.getReferencia())
				&& StringUtils.isBlank(model.getRfc()) && StringUtils.isBlank(model.getTipoCuentaBeneficiario())
				&& StringUtils.isBlank(model.getTipoMovimiento()) && StringUtils.isBlank(model.getTipoPersona())
				&& StringUtils.isBlank(model.getTipoTransaccion()) && StringUtils.isBlank(model.getDivisa());

	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> tipoMovimiento() {
		return v -> {
			return (StringUtils.isNotBlank(v.getTipoMovimiento()) && v.getTipoMovimiento().matches("[01]"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> aplicacion() {
		return v -> {
			return (StringUtils.isNotBlank(v.getAplicacion()) && v.getAplicacion().matches("[HP]"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> fecha() {
		return v -> {
			Date date = null;
			if (StringUtils.isNotBlank(v.getFecha())) {
				try {
					date = df.parse(v.getFecha());
				} catch (ParseException e) {
					// No match pattern
				}
			}
			return StringUtils.isBlank(v.getFecha()) || date != null;
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> tipoTransaccion() {
		return v -> {
			return (StringUtils.isNotBlank(v.getTipoTransaccion()) && v.getTipoTransaccion().matches("00|01|02|03"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> cuentaCargo() {
		return v -> {
			return (StringUtils.isNotBlank(v.getCuentaCargo()) && StringUtils.isNumeric(v.getCuentaCargo())
					&& v.getCuentaCargo().length() == 11);
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> tipoCuentaBeneficiario() {
		return v -> {
			return (StringUtils.isNotBlank(v.getTipoCuentaBeneficiario())
					&& v.getTipoCuentaBeneficiario().matches("01|40|03|10"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> cuentaAbono() {
		return v -> {
			return StringUtils.isNotBlank(v.getCuentaAbono()) && StringUtils.isNumeric(v.getCuentaAbono())
					&& (("01".equals(v.getTipoCuentaBeneficiario()) && v.getCuentaAbono().length()==11)
						|| ("40".equals(v.getTipoCuentaBeneficiario()) && v.getCuentaAbono().length()==18)
						|| ("03".equals(v.getTipoCuentaBeneficiario()) && v.getCuentaAbono().length()==16)
						|| ("10".equals(v.getTipoCuentaBeneficiario()) && v.getCuentaAbono().length()==10));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> tipoPersona() {
		return v -> {
			return (StringUtils.isBlank(v.getTipoPersona()) || v.getTipoPersona().matches("PF|PM"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> nombreBeneficiario() {
		return v -> {
			return (StringUtils.isNotBlank(v.getNombre()) && v.getNombre().length() <= 40);
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> rfc() {
		return v -> {
			boolean match = false;
			if(StringUtils.isNotBlank(v.getRfc())){
				Matcher m = rfcPattern.matcher(v.getRfc());
				match = m.find();
			}
			
			return StringUtils.isBlank(v.getRfc()) 
					|| (StringUtils.isNotBlank(v.getRfc()) && "PF".equals(v.getTipoPersona()) && v.getRfc().length() == 13 && match)
					|| (StringUtils.isNotBlank(v.getRfc()) && "PM".equals(v.getTipoPersona()) && v.getRfc().length() == 12 && match);
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> curp() {
		return v -> {
			return StringUtils.isBlank(v.getCurp())
					|| (StringUtils.isNotBlank(v.getCurp()) && v.getCurp().length() == 18 && "PF".equals(v.getTipoPersona()));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> divisa() {
		return v -> {
			return (StringUtils.isNotBlank(v.getDivisa()) && v.getDivisa().matches("USD|MXP|EUR"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> importe() {
		return v -> {
			return (StringUtils.isNotBlank(v.getImporte()) && NumberUtils.isCreatable(v.getImporte())
					&& Double.valueOf(v.getImporte()) <= 999999999999.99);
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> iva() {
		return v -> {
			return (StringUtils.isNotBlank(v.getRfc()) && StringUtils.isNotBlank(v.getIva())
					&& NumberUtils.isCreatable(v.getIva()) && Double.valueOf(v.getIva()) <= 999999999999.99 && Double.valueOf(v.getIva()) < Double.valueOf(v.getImporte()))
					|| StringUtils.isBlank(v.getIva());
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> concepto() {
		return v -> {
			return (StringUtils.isNotBlank(v.getConcepto()) && v.getConcepto().length() <= 40);
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> referencia() {
		return v -> {
			return StringUtils.isNotBlank(v.getReferencia()) && StringUtils.isNumeric(v.getReferencia()) && v.getReferencia().length()>=7;
		};
	}
	
	/**
	 * @return
	 */
	public Predicate<Dispersion> email() {
		return v -> {
			return (StringUtils.isBlank(v.getCorreoElectronico())
					|| (EmailValidator.getInstance().isValid(v.getCorreoElectronico())
							&& v.getCorreoElectronico().length() <= 60));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> numeroTel() {
		return v -> {
			return (StringUtils.isEmpty(v.getNumeroCelular()) || (StringUtils.isNumeric(v.getNumeroCelular())));
		};
	}

	@Override
	public boolean isActive(Dispersion model) {
		if (model != null) {
			if (model.getTipoMovimiento() != null && model.getTipoMovimiento().length() > 0)
				return true;
			if (model.getAplicacion() != null && model.getAplicacion().length() > 0)
				return true;
			if (model.getFecha() != null && model.getFecha().length() > 0)
				return true;
			if (model.getTipoTransaccion() != null && model.getTipoTransaccion().length() > 0)
				return true;
			if (model.getCuentaCargo() != null && model.getCuentaCargo().length() > 0)
				return true;
			if (model.getTipoCuentaBeneficiario() != null && model.getTipoCuentaBeneficiario().length() > 0)
				return true;
			if (model.getCuentaAbono() != null && model.getCuentaAbono().length() > 0)
				return true;
			if (model.getTipoPersona() != null && model.getTipoPersona().length() > 0)
				return true;
			if (model.getNombre() != null && model.getNombre().length() > 0)
				return true;
			if (model.getRfc() != null && model.getRfc().length() > 0)
				return true;
			if (model.getCurp() != null && model.getCurp().length() > 0)
				return true;
			if (model.getDivisa() != null && model.getDivisa().length() > 0)
				return true;
			if (model.getImporte() != null && model.getImporte().length() > 0)
				return true;
			if (model.getIva() != null && model.getIva().length() > 0)
				return true;
			if (model.getConcepto() != null && model.getConcepto().length() > 0)
				return true;
			if (model.getReferencia() != null && model.getReferencia().length() > 0)
				return true;
			if (model.getCorreoElectronico() != null && model.getCorreoElectronico().length() > 0)
				return true;
			if (model.getNumeroCelular() != null && model.getNumeroCelular().length() > 0)
				return true;
		}
		return false;
	}

}
