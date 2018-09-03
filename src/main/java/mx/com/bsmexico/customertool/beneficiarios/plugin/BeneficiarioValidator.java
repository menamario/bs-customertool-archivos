package mx.com.bsmexico.customertool.beneficiarios.plugin;

import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author jchr
 *
 */
public class BeneficiarioValidator {

	public Predicate<String> cuenta() {
		return v -> {
			return (StringUtils.isNotBlank(v) && v.length() <= 18 && StringUtils.isNumeric(v));
		};
	}

	public Predicate<String> numLinea() {
		return v -> {
			return (StringUtils.isBlank(v) || v.length() == 10);
		};
	}

	public Predicate<String> bancoParticipante() {
		return v -> {
			return (StringUtils.isBlank(v) || v.length() == 3);
		};
	}

	public Predicate<String> tipoCuenta() {
		return v -> {
			return (StringUtils.isNotBlank(v) && v.length() == 2 && v.matches("00|40"));
		};
	}

	public Predicate<String> moneda() {
		return v -> {
			return (StringUtils.isNotBlank(v) && v.matches("USD|MXN|EUR"));
		};
	}

	public Predicate<String> importeMaximo() {
		return v -> {
			return (StringUtils.isNotBlank(v) && NumberUtils.isCreatable(v)
					&& Double.valueOf(v) <= 9999999999999999.99);
		};
	}

	public Predicate<String> tipoPersona() {
		return v -> {
			return (StringUtils.isNotBlank(v) && v.matches("00|01"));
		};
	}

	public Predicate<String> razonSocial() {
		return v -> {
			return (StringUtils.isNotBlank(v) && v.length() <= 70);
		};
	}

	public Predicate<String> nombre() {
		return v -> {
			return (StringUtils.isBlank(v) || v.length() <= 25);
		};
	}

	public Predicate<String> apellidoPaterno() {
		return v -> {
			return (StringUtils.isBlank(v) || v.length() <= 30);
		};
	}

	public Predicate<String> apellidoMaterno() {
		return v -> {
			return (StringUtils.isBlank(v) || v.length() <= 30);
		};
	}	

}
