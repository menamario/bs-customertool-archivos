package mx.com.bsmexico.customertool.beneficiarios.plugin;

import java.util.function.Predicate;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import mx.com.bsmexico.customertool.api.layouts.modell.LayoutField;
import mx.com.bsmexico.customertool.api.layouts.modell.LayoutFieldConverter;
import mx.com.bsmexico.customertool.api.layouts.modell.LayoutFieldWrapper;
import mx.com.bsmexico.customertool.api.layouts.modell.LayoutModel;
import mx.com.bsmexico.customertool.api.layouts.modell.LayoutModelType;
import mx.com.bsmexico.customertool.api.layouts.modell.RestrictionLayoutField;
import mx.com.bsmexico.customertool.api.layouts.modell.converter.SecureDoubleStringConverter;
import mx.com.bsmexico.customertool.api.layouts.modell.converter.SecureLongStringConverter;

/**
 * 
 * Beneficiario Model
 * 
 * @author jchr
 *
 */
@LayoutModel(type = LayoutModelType.PROPERTY_JAVABEANS)
public class Beneficiario {

	public static final String FIELD_CUENTA_BENEFICIARIO = "CUENTA_BENEFICIARIO";
	public static final String FIELD_NUMERO_LINEA_BENEFICIARIO = "NUMERO_LINEA_BENEFICIARIO";
	public static final String FIELD_BANCO_PARTICIPANTE = "BANCO_PARTICIPANTE";
	public static final String FIELD_TIPO_CUENTA = "TIPO_CUENTA";
	public static final String FIELD_MONEDA = "MONEDA";
	public static final String FIELD_IMPORTE_MAXIMO_PAGAR = "IMPORTE_MAXIMO_PAGAR";
	public static final String FIELD_TIPO_PERSONA = "TIPO_PERSONA";
	public static final String FIELD_RAZON_SOCIAL = "RAZON_SOCIAL";
	public static final String FIELD_NOMBRE = "NOMBRE";
	public static final String FIELD_APELLIDO_PATERNO = "APELLIDO_PATERNO";
	public static final String FIELD_APELLIDO_MATERNO = "APELLIDO_MATERNO";

	private static final Double MAX_IMPORTE = 9999999999999999.99D;

	@LayoutFieldConverter(conversionClass = SecureLongStringConverter.class)
	@LayoutFieldWrapper(wrappedClass = Long.class)
	@LayoutField(name = FIELD_CUENTA_BENEFICIARIO, title = "Cuenta beneficiario", length = 18)
	private SimpleLongProperty cuenta;

	@LayoutFieldConverter(conversionClass = SecureLongStringConverter.class)
	@LayoutFieldWrapper(wrappedClass = Long.class)
	@LayoutField(name = FIELD_NUMERO_LINEA_BENEFICIARIO, title = "Número de línea de telefonía Móvil del Beneficiario", length = 10, disable = true, required = false)
	private SimpleLongProperty numLinea;

	@LayoutField(name = FIELD_BANCO_PARTICIPANTE, title = "Banco participante", length = 3, required = false)
	private SimpleStringProperty bancoParticipante;

	@LayoutField(name = FIELD_TIPO_CUENTA, title = "Tipo de cuenta", length = 2)
	private SimpleStringProperty tipoCuenta;

	@LayoutField(name = FIELD_MONEDA, title = "Moneda", length = 3)
	private SimpleStringProperty moneda;

	@LayoutFieldConverter(conversionClass = SecureDoubleStringConverter.class)
	@LayoutFieldWrapper(wrappedClass = Double.class)
	@LayoutField(name = FIELD_IMPORTE_MAXIMO_PAGAR, title = "Importe máximo a pagar", length = 19)
	private SimpleDoubleProperty importeMaximo;

	@LayoutField(name = FIELD_TIPO_PERSONA, title = "Tipo persona", length = 3)
	private SimpleStringProperty tipoPersona;

	@LayoutField(name = FIELD_RAZON_SOCIAL, title = "Razón Social", length = 70)
	private SimpleStringProperty razonSocial;

	@LayoutField(name = FIELD_NOMBRE, title = "Nombre", length = 25)
	private SimpleStringProperty nombre;

	@LayoutField(name = FIELD_APELLIDO_PATERNO, title = "Apellido paterno", length = 30)
	private SimpleStringProperty apellidoPaterno;

	@LayoutField(name = FIELD_APELLIDO_MATERNO, title = "Apellido materno", length = 30)
	private SimpleStringProperty apellidoMaterno;

	@RestrictionLayoutField(description = "00 Cuenta Banco Sabadell, 04 CLABE SPEI", fields = { FIELD_TIPO_CUENTA })
	private static Predicate<String> tipoCuentaPredicate = t -> (t == null) ? false
			: t.matches("00|04");

	@RestrictionLayoutField(description = "00 Persona Física, 01 Persona Moral", fields = { FIELD_TIPO_PERSONA })
	private static Predicate<String> tipoPersonaPredicate = t -> (t == null) ? false
			: t.matches("00|01");

	@RestrictionLayoutField(description = "Tipo de moneda : MXN, USD, EUR", fields = { FIELD_MONEDA })
	private static Predicate<String> monedaPredicate = t -> (t == null) ? false
			: t.matches("MXN|USD|EUR");

	@RestrictionLayoutField(description = "Importe máximo no mayor a 9999999999999999.99", fields = {
			FIELD_IMPORTE_MAXIMO_PAGAR })
	private static Predicate<Double> importeMaximoPredicate = t -> (t != null && t <= MAX_IMPORTE);

	public Beneficiario() {
		cuenta = new SimpleLongProperty();
		numLinea = new SimpleLongProperty();
		bancoParticipante = new SimpleStringProperty();
		tipoCuenta = new SimpleStringProperty();
		moneda = new SimpleStringProperty();
		importeMaximo = new SimpleDoubleProperty();
		tipoPersona = new SimpleStringProperty();
		razonSocial = new SimpleStringProperty();
		nombre = new SimpleStringProperty();
		apellidoPaterno = new SimpleStringProperty();
		apellidoMaterno = new SimpleStringProperty();
	}

	/**
	 * @return the cuenta
	 */
	public Long getCuenta() {
		return cuenta.get();
	}

	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(Long cuenta) {		
		this.cuenta.set(cuenta);
	}

	/**
	 * @return the numLinea
	 */
	public Long getNumLinea() {
		return numLinea.get();
	}

	/**
	 * @param numLinea the numLinea to set
	 */
	public void setNumLinea(Long numLinea) {
		this.numLinea.set(numLinea);
	}

	/**
	 * @return the bancoParticipante
	 */
	public String getBancoParticipante() {
		return bancoParticipante.get();
	}

	/**
	 * @param bancoParticipante the bancoParticipante to set
	 */
	public void setBancoParticipante(String bancoParticipante) {
		this.bancoParticipante.set(bancoParticipante);
	}

	/**
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta.get();
	}

	/**
	 * @param tipoCuenta the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta.set(tipoCuenta);
	}

	/**
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda.get();
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda.set(moneda);
	}

	/**
	 * @return the importeMaximo
	 */
	public Double getImporteMaximo() {
		return importeMaximo.get();
	}

	/**
	 * @param importeMaximo the importeMaximo to set
	 */
	public void setImporteMaximo(Double importeMaximo) {
		this.importeMaximo.set(importeMaximo);
	}

	/**
	 * @return the tipoPersona
	 */
	public String getTipoPersona() {
		return tipoPersona.get();
	}

	/**
	 * @param tipoPersona the tipoPersona to set
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona.set(tipoPersona);
	}

	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial.get();
	}

	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial.set(razonSocial);
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre.get();
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}

	/**
	 * @return the apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno.get();
	}

	/**
	 * @param apellidoPaterno the apellidoPaterno to set
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno.set(apellidoPaterno);
	}

	/**
	 * @return the apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno.get();
	}

	/**
	 * @param apellidoMaterno the apellidoMaterno to set
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno.set(apellidoMaterno);
	}

}
