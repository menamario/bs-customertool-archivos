package mx.com.bsmexico.customertool.beneficiarios.plugin;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mx.com.bsmexico.customertool.api.layouts.model.LayoutField;
import mx.com.bsmexico.customertool.api.layouts.model.LayoutModel;
import mx.com.bsmexico.customertool.api.layouts.model.LayoutModelType;

/**
 * 
 * Beneficiario Model
 * 
 * @author jchr
 *
 */
@LayoutModel(type = LayoutModelType.PROPERTY_JAVABEANS, validatorClass = BeneficiarioValidator.class)
public class Beneficiario {

	String pattern = "###############0.00";
	DecimalFormat decimalFormat = new DecimalFormat(pattern);

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
	public static final String FIELD_ALIAS = "ALIAS";
	public static final String FIELD_RFC = "RFC";
	public static final String FIELD_CURP = "CURP";
	public static final String FIELD_CORREO_ELECTRONICO = "CORREO_ELECTRONICO";

	@LayoutField(name = FIELD_CUENTA_BENEFICIARIO, title = "Cuenta beneficiario", length = 18)
	private SimpleStringProperty cuenta;

	@LayoutField(name = FIELD_NUMERO_LINEA_BENEFICIARIO, title = "Número de línea de telefono móvil del beneficiario", length = 0, disable = true, required = false, editable=false)
	private SimpleStringProperty numLinea;

	@LayoutField(name = FIELD_BANCO_PARTICIPANTE, title = "Banco participante", length = 3, required = false)
	private SimpleStringProperty bancoParticipante;

	@LayoutField(name = FIELD_TIPO_CUENTA, title = "Tipo de cuenta", length = 2)
	private SimpleStringProperty tipoCuenta;

	@LayoutField(name = FIELD_MONEDA, title = "Moneda", length = 3)
	private SimpleStringProperty moneda;

	@LayoutField(name = FIELD_IMPORTE_MAXIMO_PAGAR, title = "Importe máximo a pagar", length = 19)
	private SimpleStringProperty importeMaximo;

	@LayoutField(name = FIELD_TIPO_PERSONA, title = "Tipo persona", length = 3)
	private SimpleStringProperty tipoPersona;

	@LayoutField(name = FIELD_RAZON_SOCIAL, title = "Razón social", length = 70)
	private SimpleStringProperty razonSocial;

	@LayoutField(name = FIELD_NOMBRE, title = "Nombre", length = 25)
	private SimpleStringProperty nombre;

	@LayoutField(name = FIELD_APELLIDO_PATERNO, title = "Apellido paterno", length = 30)
	private SimpleStringProperty apellidoPaterno;

	@LayoutField(name = FIELD_APELLIDO_MATERNO, title = "Apellido materno", length = 30)
	private SimpleStringProperty apellidoMaterno;

	@LayoutField(name = FIELD_ALIAS, title = "Alias", length = 30)
	private SimpleStringProperty alias;

	@LayoutField(name = FIELD_RFC, title = "RFC", length = 13)
	private SimpleStringProperty rfc;

	@LayoutField(name = FIELD_CURP, title = "CURP", length = 18)
	private SimpleStringProperty curp;

	@LayoutField(name = FIELD_CORREO_ELECTRONICO, title = "Correo electrónico", length = 50)
	private SimpleStringProperty email;

	public Beneficiario() {
		cuenta = new SimpleStringProperty();
		numLinea = new SimpleStringProperty();
		bancoParticipante = new SimpleStringProperty();
		tipoCuenta = new SimpleStringProperty();
		moneda = new SimpleStringProperty();
		importeMaximo = new SimpleStringProperty();
		tipoPersona = new SimpleStringProperty();
		razonSocial = new SimpleStringProperty();
		nombre = new SimpleStringProperty();
		apellidoPaterno = new SimpleStringProperty();
		apellidoMaterno = new SimpleStringProperty();
		alias = new SimpleStringProperty();
		rfc = new SimpleStringProperty();
		curp = new SimpleStringProperty();
		email = new SimpleStringProperty();
	}

	/**
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta.get();
	}

	/**
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta.set(cuenta);
	}

	/**
	 * @return the numLinea
	 */
	public String getNumLinea() {
		return numLinea.get();
	}

	/**
	 * @param numLinea
	 *            the numLinea to set
	 */
	public void setNumLinea(String numLinea) {
		this.numLinea.set("");
	}

	/**
	 * @return the bancoParticipante
	 */
	public String getBancoParticipante() {
		return bancoParticipante.get();
	}

	/**
	 * @param bancoParticipante
	 *            the bancoParticipante to set
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
	 * @param tipoCuenta
	 *            the tipoCuenta to set
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
	 * @param moneda
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		if (moneda != null)
			this.moneda.set(moneda.toUpperCase());
	}

	/**
	 * @return the importeMaximo
	 */
	public String getImporteMaximo() {
		return importeMaximo.get();
	}

	/**
	 * @param importeMaximo
	 *            the importeMaximo to set
	 */
	public void setImporteMaximo(String importeMaximo) {
		if (StringUtils.isNotBlank(importeMaximo)
				&& ((importeMaximo.lastIndexOf(".") > 0
						&& importeMaximo.substring(importeMaximo.lastIndexOf(".")).length() <= 3)
						|| importeMaximo.lastIndexOf(".") == -1)
				&& NumberUtils.isCreatable(StringUtils.stripStart(importeMaximo, "0"))
				&& Double.valueOf(StringUtils.stripStart(importeMaximo, "0")) < 9999999999999999.99) {
			importeMaximo = decimalFormat.format(new BigDecimal(StringUtils.stripStart(importeMaximo, "0")));
		}
		this.importeMaximo.set(importeMaximo);

	}

	/**
	 * @return the tipoPersona
	 */
	public String getTipoPersona() {
		return tipoPersona.get();
	}

	/**
	 * @param tipoPersona
	 *            the tipoPersona to set
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
	 * @param razonSocial
	 *            the razonSocial to set
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
	 * @param nombre
	 *            the nombre to set
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
	 * @param apellidoPaterno
	 *            the apellidoPaterno to set
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
	 * @param apellidoMaterno
	 *            the apellidoMaterno to set
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno.set(apellidoMaterno);
	}

	/**
	 * @return the apellidoMaterno
	 */
	public String getAlias() {
		return alias.get();
	}

	/**
	 * @param apellidoMaterno
	 *            the apellidoMaterno to set
	 */
	public void setAlias(String alias) {
		this.alias.set(alias);
	}

	/**
	 * @return the apellidoMaterno
	 */
	public String getRfc() {
		return rfc.get();
	}

	/**
	 * @param apellidoMaterno
	 *            the apellidoMaterno to set
	 */
	public void setRfc(String rfc) {
		if (rfc != null)
			this.rfc.set(rfc.toUpperCase());
	}

	/**
	 * @return the apellidoMaterno
	 */
	public String getCurp() {
		return curp.get();
	}

	/**
	 * @param apellidoMaterno
	 *            the apellidoMaterno to set
	 */
	public void setCurp(String curp) {
		if (curp != null)
			this.curp.set(curp.toUpperCase());
	}

	/**
	 * @return the apellidoMaterno
	 */
	public String getEmail() {
		return email.get();
	}

	/**
	 * @param apellidoMaterno
	 *            the apellidoMaterno to set
	 */
	public void setEmail(String email) {
		this.email.set(email);
	}

	public StringProperty cuentaProperty() {
		return cuenta;
	}

	public StringProperty numLineaProperty() {
		return numLinea;
	}

	public StringProperty bancoParticipanteProperty() {
		return bancoParticipante;
	}

	public StringProperty tipoCuentaProperty() {
		return tipoCuenta;
	}

	public StringProperty monedaProperty() {
		return moneda;
	}

	public StringProperty importeMaximoProperty() {
		return importeMaximo;
	}

	public StringProperty tipoPersonaProperty() {
		return tipoPersona;
	}

	public StringProperty razonSocialProperty() {
		return razonSocial;
	}

	public StringProperty nombreProperty() {
		return nombre;
	}

	public StringProperty apellidoPaternoProperty() {
		return apellidoPaterno;
	}

	public StringProperty apellidoMaternoProperty() {
		return apellidoMaterno;
	}

	public StringProperty aliasProperty() {
		return alias;
	}

	public StringProperty rfcProperty() {
		return rfc;
	}

	public StringProperty curpProperty() {
		return curp;
	}

	public StringProperty emailProperty() {
		return email;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidoMaterno.get() == null) ? 0 : apellidoMaterno.get().hashCode());
		result = prime * result + ((apellidoPaterno.get() == null) ? 0 : apellidoPaterno.get().hashCode());
		result = prime * result + ((bancoParticipante.get() == null) ? 0 : bancoParticipante.get().hashCode());
		result = prime * result + ((cuenta.get() == null) ? 0 : cuenta.get().hashCode());
		result = prime * result + ((importeMaximo.get() == null) ? 0 : importeMaximo.get().hashCode());
		result = prime * result + ((moneda.get() == null) ? 0 : moneda.get().hashCode());
		result = prime * result + ((nombre.get() == null) ? 0 : nombre.get().hashCode());
		result = prime * result + ((numLinea.get() == null) ? 0 : numLinea.get().hashCode());
		result = prime * result + ((razonSocial.get() == null) ? 0 : razonSocial.get().hashCode());
		result = prime * result + ((tipoCuenta.get() == null) ? 0 : tipoCuenta.get().hashCode());
		result = prime * result + ((tipoPersona.get() == null) ? 0 : tipoPersona.get().hashCode());
		result = prime * result + ((alias.get() == null) ? 0 : alias.get().hashCode());
		result = prime * result + ((rfc.get() == null) ? 0 : rfc.get().hashCode());
		result = prime * result + ((curp.get() == null) ? 0 : curp.get().hashCode());
		result = prime * result + ((email.get() == null) ? 0 : email.get().hashCode());
		return result;
	}

}
