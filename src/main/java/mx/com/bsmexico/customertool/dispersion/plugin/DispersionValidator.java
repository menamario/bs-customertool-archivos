package mx.com.bsmexico.customertool.dispersion.plugin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.validator.routines.EmailValidator;

import mx.com.bsmexico.customertool.api.layouts.model.validation.LayoutModelValidator;

public class DispersionValidator extends LayoutModelValidator<Dispersion> {

	private SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

	@Override
	public boolean isValidField(String fieldName, Dispersion model) {
		boolean isValid = false;
		if (StringUtils.isNotBlank(fieldName) && model != null) {
			switch (fieldName) {
			case Dispersion.FIELD_APLICACION:
				isValid = aplicacion().test(model);
				break;
			case Dispersion.FIELD_CONCEPTO:
				isValid = true;
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
				isValid = true;
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

	@Override
	public boolean isValid(Dispersion model) {

		return model != null && divisa().test(model) && tipoTransaccion().test(model) && tipoMovimiento().test(model)
				&& tipoCuentaBeneficiario().test(model) && rfc().test(model) && nombreBeneficiario().test(model)
				&& numeroTel().test(model) && iva().test(model) && importe().test(model) && fecha().test(model)
				&& curp().test(model) && cuentaCargo().test(model) && cuentaAbono().test(model) && email().test(model)
				&& aplicacion().test(model);
	}

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
				desc = "";
				break;
			case Dispersion.FIELD_CONCEPTO:
				desc = "";
				break;
			case Dispersion.FIELD_CORREO_ELECTRONICO:
				desc = "";
				break;
			case Dispersion.FIELD_CUENTA_ABONO:
				desc = "";
				break;
			case Dispersion.FIELD_CUENTA_CARGO:
				desc = "";
				break;
			case Dispersion.FIELD_CURP:
				desc = "";
				break;
			case Dispersion.FIELD_FECHA:
				desc = "";
				break;
			case Dispersion.FIELD_IMPORTE:
				desc = "";
				break;
			case Dispersion.FIELD_IVA:
				desc = "";
				break;
			case Dispersion.FIELD_NOMBRE_BENEFICIARIO:
				desc = "";
				break;
			case Dispersion.FIELD_NUMERO_CELULAR:
				desc = "";
				break;
			case Dispersion.FIELD_REFERENCIA:
				desc = "";
				break;
			case Dispersion.FIELD_RFC:
				desc = "";
				break;
			case Dispersion.FIELD_TIPO_CUENTA_BENEFICIARIO:
				desc = "";
				break;
			case Dispersion.FIELD_TIPO_MOVIMIENTO:
				desc = "";
				break;
			case Dispersion.FIELD_TIPO_PERSONA:
				desc = "";
				break;
			case Dispersion.FIELD_TIPO_TRANSACCION:
				desc = "";
				break;
			case Dispersion.FIELD_DIVISA:
				desc = "";
				break;
			default:
				break;
			}
		}
		return desc;
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
			return (StringUtils.isNotBlank(v.getFecha()) && date != null);
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
					&& v.getCuentaCargo().length() <= 11);
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> tipoCuentaBeneficiario() {
		return v -> {
			return (StringUtils.isNotBlank(v.getTipoCuentaBeneficiario())
					&& v.getTipoCuentaBeneficiario().matches("00|40|03|04"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> cuentaAbono() {
		return v -> {
			return (StringUtils.isNotBlank(v.getCuentaAbono()) && StringUtils.isNumeric(v.getCuentaAbono())
					&& v.getCuentaAbono().length() >= 10 && v.getCuentaAbono().length() <= 18);
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> tipoPersona() {
		return v -> {
			return (StringUtils.isNotBlank(v.getTipoPersona()) && v.getTipoPersona().matches("PF|PM"));
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
			return StringUtils.isNotBlank(v.getRfc()) && (("PF".equals(v.getTipoPersona()) && v.getRfc().length() == 13)
					|| ("PM".equals(v.getTipoPersona()) && v.getRfc().length() == 12));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> curp() {
		return v -> {
			return (StringUtils.isNotBlank(v.getCurp()) && v.getCurp().length() == 18);
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> divisa() {
		return v -> {
			return (StringUtils.isNotBlank(v.getDivisa()) && v.getDivisa().matches("USD|MXN|EUR"));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> importe() {
		return v -> {
			return (StringUtils.isNotBlank(v.getImporte()) && NumberUtils.isCreatable(v.getImporte())
					&& Double.valueOf(v.getImporte()) <= 9999999999999.99);
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> iva() {
		return v -> {
			return (StringUtils.isNotBlank(v.getIva()) && NumberUtils.isCreatable(v.getIva())
					&& Double.valueOf(v.getIva()) <= 9999999999999.99);
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> email() {
		return v -> {
			return (StringUtils.isBlank(v.getCorreoElectronico())
					|| EmailValidator.getInstance().isValid(v.getCorreoElectronico()));
		};
	}

	/**
	 * @return
	 */
	public Predicate<Dispersion> numeroTel() {
		return v -> {
			return (StringUtils.isBlank(v.getNumeroCelular()) || StringUtils.isNumeric(v.getCuentaCargo()));
		};
	}

	@Override
	public boolean isActive(Dispersion model) {
		// TODO Auto-generated method stub
		return false;
	}

}
