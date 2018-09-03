package mx.com.bsmexico.customertool.dispersion.plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javafx.beans.property.SimpleStringProperty;
import mx.com.bsmexico.customertool.api.layouts.modell.LayoutField;
import mx.com.bsmexico.customertool.api.layouts.modell.LayoutModel;
import mx.com.bsmexico.customertool.api.layouts.modell.LayoutModelType;
import mx.com.bsmexico.customertool.api.layouts.modell.RestrictionLayoutField;

/**
 * 
 * Beneficiario Model
 * 
 * @author jchr
 *
 */
@LayoutModel(type = LayoutModelType.PROPERTY_JAVABEANS)
public class Dispersion {

	public static final String FIELD_TIPO_MOVIMIENTO = "TIPO_MOVIMIENTO";
	public static final String FIELD_APLICACION = "APLICACION";
	public static final String FIELD_FECHA = "FECHA";
	public static final String FIELD_TIPO_TRANSACCION = "TIPO_TRANSACCION";
	public static final String FIELD_CUENTA_CARGO = "CUENTA_CARGO";
	public static final String FIELD_TIPO_CUENTA_BENEFICIARIO = "TIPO_CUENTA_BENEFICIARIO";
	public static final String FIELD_CUENTA_ABONO = "CUENTA_ABONO";
	public static final String FIELD_TIPO_PERSONA = "TIPO_PERSONA";
	public static final String FIELD_NOMBRE_BENEFICIARIO = "NOMBRE_BENEFICIARIO";
	public static final String FIELD_RFC = "RFC";
	public static final String FIELD_CURP = "CURP";
	public static final String FIELD_DIVISA = "DIVISA";
	public static final String FIELD_IMPORTE = "IMPORTE";
	public static final String FIELD_IVA = "IVA";
	public static final String FIELD_CONCEPTO = "CONCEPTO";
	public static final String FIELD_REFERENCIA = "REFERENCIA";
	public static final String FIELD_CORREO_ELECTRONICO = "CORREO_ELECTRONICO";
	public static final String FIELD_NUMERO_CELULAR = "NUMERO_CELULAR";

	private static final Double MAX_IMPORTE = 999999999999999.99D;

	@LayoutField(name = FIELD_TIPO_MOVIMIENTO, title = "Tipo de Movimiento", length = 1)
	private SimpleStringProperty tipoMovimiento;

	@LayoutField(name = FIELD_APLICACION, title = "Aplicación", length = 1)
	private SimpleStringProperty aplicacion;

	@LayoutField(name = FIELD_FECHA, title = "Fecha", length = 10)
	private SimpleStringProperty fecha;

	@LayoutField(name = FIELD_TIPO_TRANSACCION, title = "Tipo de transacción", length = 2)
	private SimpleStringProperty tipoTransaccion;

	@LayoutField(name = FIELD_CUENTA_CARGO, title = "Cuenta de Cargo", length = 11)
	private SimpleStringProperty cuentaCargo;

	@LayoutField(name = FIELD_TIPO_CUENTA_BENEFICIARIO, title = "Tipo Cuenta Beneficiario", length = 2)
	private SimpleStringProperty tipoCuentaBeneficiario;

	@LayoutField(name = FIELD_CUENTA_ABONO, title = "Cuenta abono", length = 18)
	private SimpleStringProperty cuentaAbono;

	@LayoutField(name = FIELD_TIPO_PERSONA, title = "Tipo Persona", length = 2)
	private SimpleStringProperty tipoPersona;

	@LayoutField(name = FIELD_NOMBRE_BENEFICIARIO, title = "Nombre Beneficiario", length = 40)
	private SimpleStringProperty nombre;

	@LayoutField(name = FIELD_RFC, title = "Rfc", length = 13)
	private SimpleStringProperty rfc;

	@LayoutField(name = FIELD_CURP, title = "Curp", length = 18)
	private SimpleStringProperty curp;

	@LayoutField(name = FIELD_DIVISA, title = "Divisa", length = 3)
	private SimpleStringProperty divisa;

	@LayoutField(name = FIELD_IMPORTE, title = "Importe", length = 15)
	private SimpleStringProperty importe;

	@LayoutField(name = FIELD_IVA, title = "Iva", length = 15)
	private SimpleStringProperty iva;

	@LayoutField(name = FIELD_CONCEPTO, title = "Concepto", length = 40)
	private SimpleStringProperty concepto;

	@LayoutField(name = FIELD_REFERENCIA, title = "Referencia", length = 20)
	private SimpleStringProperty referencia;

	@LayoutField(name = FIELD_CORREO_ELECTRONICO, title = "Correo Electronico", length = 60)
	private SimpleStringProperty correoElectronico;

	@LayoutField(name = FIELD_NUMERO_CELULAR, title = "Numero Celular", length = 10)
	private SimpleStringProperty numeroCelular;

	@RestrictionLayoutField(description = "0 Pago, 1 Cancelacion", fields = { FIELD_TIPO_MOVIMIENTO })
	private static Predicate<String> tipoMovimientoPredicate = t -> (t == null) ? false : t.matches("0|1");

	@RestrictionLayoutField(description = "H Hoy, P Programado", fields = { FIELD_APLICACION })
	private static Predicate<String> tipoAplicacionPredicate = t -> (t == null) ? false : t.matches("H|P");

	@RestrictionLayoutField(description = "Tipo de Transaccion : 00, 01, 02, 03", fields = { FIELD_TIPO_TRANSACCION })
	private static Predicate<String> tipoTransaccionPredicate = t -> (t == null) ? false : t.matches("00|01|02|03");

	@RestrictionLayoutField(description = "01 Sabadell, 40 CLABE, 03 TDD / TDC, 10 LTM", fields = {
			FIELD_TIPO_CUENTA_BENEFICIARIO })
	private static Predicate<String> tipoCuentaBeneficiarioPredicate = t -> (t == null) ? false
			: t.matches("01|40|10|03");

	@RestrictionLayoutField(description = "PF Persona Fisica, PM Persona Moral", fields = { FIELD_TIPO_PERSONA })
	private static Predicate<String> tipoPersonaPredicate = t -> (t == null) ? false : t.matches("PF|PM");

	@RestrictionLayoutField(description = "MXP Pesos Mexicanos, USD Dolares Americanos", fields = { FIELD_DIVISA })
	private static Predicate<String> divisaPredicate = t -> (t == null) ? false : t.matches("MXP|USD");

	@RestrictionLayoutField(description = "Importe máximo no mayor a 999999999999999.99", fields = { FIELD_IMPORTE })
	private static Predicate<String> importePredicate = v -> {
		return (StringUtils.isNotBlank(v) && NumberUtils.isCreatable(v) && Double.valueOf(v) <= MAX_IMPORTE);
	};

	@RestrictionLayoutField(description = "Cuenta Sabadell", fields = { FIELD_CUENTA_CARGO })
	private static Predicate<String> cuentaCargoPredicate = t -> (StringUtils.isNotBlank(t) && t.length() <= 11
			&& StringUtils.isNumeric(t));

	@RestrictionLayoutField(description = "Cuenta Abono", fields = { FIELD_CUENTA_ABONO })
	private static Predicate<String> cuentaAbonoPredicate = t -> (StringUtils.isNotBlank(t) && t.length() <= 18
			&& StringUtils.isNumeric(t));

	@RestrictionLayoutField(description = "Cuenta Abono", fields = { FIELD_REFERENCIA })
	private static Predicate<String> referenciaPredicate = t -> (StringUtils.isNotBlank(t) && t.length() <= 20
			&& StringUtils.isNumeric(t));

	private Map<String, Boolean> estatus = new HashMap<String, Boolean>();

	private String detalleOperacion;

	public Dispersion() {
		tipoMovimiento = new SimpleStringProperty();
		aplicacion = new SimpleStringProperty();
		fecha = new SimpleStringProperty();
		tipoTransaccion = new SimpleStringProperty();
		cuentaCargo = new SimpleStringProperty();
		tipoCuentaBeneficiario = new SimpleStringProperty();
		cuentaAbono = new SimpleStringProperty();
		tipoPersona = new SimpleStringProperty();
		nombre = new SimpleStringProperty();
		rfc = new SimpleStringProperty();
		curp = new SimpleStringProperty();
		divisa = new SimpleStringProperty();
		importe = new SimpleStringProperty();
		iva = new SimpleStringProperty();
		concepto = new SimpleStringProperty();
		referencia = new SimpleStringProperty();
		correoElectronico = new SimpleStringProperty();
		numeroCelular = new SimpleStringProperty();

		estatus.put("tipoMovimiento", true);
		estatus.put("aplicacion", true);
		estatus.put("fecha", true);
		estatus.put("tipoTransaccion", true);
		estatus.put("cuentaCargo", true);
		estatus.put("tipoCuentaBeneficiario", true);
		estatus.put("cuentaAbono", true);
		estatus.put("tipoPersona", true);
		estatus.put("nombre", true);
		estatus.put("rfc", true);
		estatus.put("curp", true);
		estatus.put("divisa", true);
		estatus.put("importe", true);
		estatus.put("iva", true);
		estatus.put("concepto", true);
		estatus.put("referencia", true);
		estatus.put("correoElectronico", true);
		estatus.put("numeroCelular", true);
	}

	public Map<String, Boolean> getEstatus() {
		return estatus;
	}

	public void setEstatus(String property, Boolean value) {
		this.getEstatus().put(property, value);
	}

	public String getTipoMovimiento() {
		return tipoMovimiento.get();
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento.set(tipoMovimiento);
	}

	public String getAplicacion() {
		return aplicacion.get();
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion.set(aplicacion);
	}

	public String getFecha() {
		return fecha.get();
	}

	public void setFecha(String fecha) {
		this.fecha.set(fecha);
	}

	public String getTipoTransaccion() {
		return tipoTransaccion.get();
	}

	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion.set(tipoTransaccion);
	}

	public String getCuentaCargo() {
		return cuentaCargo.get();
	}

	public void setCuentaCargo(String cuentaCargo) {
		this.cuentaCargo.set(cuentaCargo);
	}

	public String getTipoCuentaBeneficiario() {
		return tipoCuentaBeneficiario.get();
	}

	public void setTipoCuentaBeneficiario(String tipoCuentaBeneficiario) {
		this.tipoCuentaBeneficiario.set(tipoCuentaBeneficiario);
	}

	public String getCuentaAbono() {
		return cuentaAbono.get();
	}

	public void setCuentaAbono(String cuentaAbono) {
		this.cuentaAbono.set(cuentaAbono);
	}

	public String getTipoPersona() {
		return tipoPersona.get();
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona.set(tipoPersona);
	}

	public String getNombre() {
		return nombre.get();
	}

	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}

	public String getRfc() {
		return rfc.get();
	}

	public void setRfc(String rfc) {
		this.rfc.set(rfc);
	}

	public String getCurp() {
		return curp.get();
	}

	public void setCurp(String curp) {
		this.curp.set(curp);
	}

	public String getDivisa() {
		return divisa.get();
	}

	public void setDivisa(String divisa) {
		this.divisa.set(divisa);
	}

	public String getImporte() {
		return importe.get();
	}

	public void setImporte(String importe) {
		this.importe.set(importe);
	}

	public String getIva() {
		return iva.get();
	}

	public void setIva(String iva) {
		this.iva.set(iva);
	}

	public String getConcepto() {
		return concepto.get();
	}

	public void setConcepto(String concepto) {
		this.concepto.set(concepto);
	}

	public String getReferencia() {
		return referencia.get();
	}

	public void setReferencia(String referencia) {
		this.referencia.set(referencia);
	}

	public String getCorreoElectronico() {
		return correoElectronico.get();
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico.set(correoElectronico);
	}

	public String getNumeroCelular() {
		return numeroCelular.get();
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular.set(numeroCelular);
	}

	public void setEstatus(Map<String, Boolean> estatus) {
		this.estatus = estatus;
	}

	public boolean isActive() {

		if (this.tipoMovimiento.get() != null && this.tipoMovimiento.get().length() > 0)
			return true;
		if (this.aplicacion.get() != null && this.aplicacion.get().length() > 0)
			return true;
		if (this.fecha.get() != null && this.fecha.get().length() > 0)
			return true;
		if (this.tipoTransaccion.get() != null && this.tipoTransaccion.get().length() > 0)
			return true;
		if (this.cuentaCargo.get() != null && this.cuentaCargo.get().length() > 0)
			return true;
		if (this.tipoCuentaBeneficiario.get() != null && this.tipoCuentaBeneficiario.get().length() > 0)
			return true;
		if (this.cuentaAbono.get() != null && this.cuentaAbono.get().length() > 0)
			return true;
		if (this.tipoPersona.get() != null && this.tipoPersona.get().length() > 0)
			return true;
		if (this.nombre.get() != null && this.nombre.get().length() > 0)
			return true;
		if (this.rfc.get() != null && this.rfc.get().length() > 0)
			return true;
		if (this.curp.get() != null && this.curp.get().length() > 0)
			return true;
		if (this.divisa.get() != null && this.divisa.get().length() > 0)
			return true;
		if (this.importe.get() != null && this.importe.get().length() > 0)
			return true;
		if (this.iva.get() != null && this.iva.get().length() > 0)
			return true;
		if (this.concepto.get() != null && this.concepto.get().length() > 0)
			return true;
		if (this.referencia.get() != null && this.referencia.get().length() > 0)
			return true;
		if (this.correoElectronico.get() != null && this.correoElectronico.get().length() > 0)
			return true;
		if (this.numeroCelular.get() != null && this.numeroCelular.get().length() > 0)
			return true;
		return false;
	}

	public boolean validar() {

		resetEstatus();

		// Tipo Movimiento
		if (!this.tipoMovimientoPredicate.test(this.tipoMovimiento.get())) {
			this.setEstatus("tipoMovimiento", false);
		}

		// Tipo Aplicacion
		if (!this.tipoAplicacionPredicate.test(this.aplicacion.get())) {
			this.setEstatus("aplicacion", false);
		}

		if (this.fecha.get() == null || this.fecha.get().length() != 10) {
			this.setEstatus("fecha", false);
		}

		if (!this.tipoTransaccionPredicate.test(this.tipoTransaccion.get())) {
			this.setEstatus("tipoTransaccion", false);
		}

		if (!this.cuentaCargoPredicate.test(this.cuentaCargo.get()) || this.cuentaCargo.get().length() != 11) {
			this.setEstatus("cuentaCargo", false);
		}

		if (!this.tipoCuentaBeneficiarioPredicate.test(this.tipoCuentaBeneficiario.get())) {
			this.setEstatus("tipoCuentaBeneficiario", false);
		}

		if (!this.cuentaAbonoPredicate.test(this.cuentaAbono.get()) || this.cuentaAbono.get().length() == 0) {
			this.setEstatus("cuentaAbono", false);
		}

		if (this.cuentaAbono.get() != null) {

			if (this.tipoCuentaBeneficiarioPredicate.test(this.tipoCuentaBeneficiario.get())
					&& this.tipoCuentaBeneficiario.get().equals("01") && this.cuentaAbono.get().length() != 11) {
				this.setEstatus("cuentaAbono", false);
			}

			if (this.tipoCuentaBeneficiarioPredicate.test(this.tipoCuentaBeneficiario.get())
					&& this.tipoCuentaBeneficiario.get().equals("40") && this.cuentaAbono.get().length() != 18) {
				this.setEstatus("cuentaAbono", false);
			}

			if (this.tipoCuentaBeneficiarioPredicate.test(this.tipoCuentaBeneficiario.get())
					&& this.tipoCuentaBeneficiario.get().equals("03") && this.cuentaAbono.get().length() != 16) {
				this.setEstatus("cuentaAbono", false);
			}

			if (this.tipoCuentaBeneficiarioPredicate.test(this.tipoCuentaBeneficiario.get())
					&& this.tipoCuentaBeneficiario.get().equals("10") && this.cuentaAbono.get().length() != 10) {
				this.setEstatus("cuentaAbono", false);
			}
		}

		if (!this.tipoPersonaPredicate.test(this.tipoPersona.get())) {
			this.setEstatus("tipoPersona", false);
		}

		if (this.nombre.get() == null || this.nombre.get().length() == 0) {
			this.setEstatus("nombre", false);
		}

		if (this.tipoPersonaPredicate.test(this.tipoPersona.get()) && this.tipoPersona.get().equals("PF")
				&& this.rfc.get().length() != 13) {
			this.setEstatus("rfc", false);
		}

		if (this.tipoPersonaPredicate.test(this.tipoPersona.get()) && this.tipoPersona.get().equals("PM")
				&& this.rfc.get().length() != 12) {
			this.setEstatus("rfc", false);
		}

		if (this.tipoPersonaPredicate.test(this.tipoPersona.get()) && this.tipoPersona.get().equals("PM")
				&& this.curp.get() != null || this.curp.get().length() > 0) {
			this.setEstatus("curp", false);
		}

		if (!this.divisaPredicate.test(this.divisa.get())) {
			this.setEstatus("divisa", false);
		}

		if (!this.importePredicate.test(this.importe.get())) {
			this.setEstatus("importe", false);
		}

		if ((this.rfc.get() == null || this.rfc.get().length() == 0) && this.iva.get() != null
				&& this.iva.get().length() > 0) {
			this.setEstatus("iva", false);
		}

		if (this.iva.get() != null && this.importe.get() != null
				&& Double.valueOf(this.iva.get()) > Double.valueOf(this.importe.get())) {
			this.setEstatus("iva", false);
		}

		if (this.concepto.get() == null || this.concepto.get().length() == 0) {
			this.setEstatus("concepto", false);
		}

		if (!this.referenciaPredicate.test(this.referencia.get())) {
			this.setEstatus("referencia", false);
		}

		for (boolean b : this.getEstatus().values()) {
			if (!b)
				return false;
		}
		return true;

	}

	private void resetEstatus() {
		this.getEstatus().replaceAll((k, v) -> true);
	}

	public String getDetalleOperacion() {
		return detalleOperacion;
	}

	public void setDetalleOperacion(String detalleOperacion) {
		this.detalleOperacion = detalleOperacion;
	}

}
