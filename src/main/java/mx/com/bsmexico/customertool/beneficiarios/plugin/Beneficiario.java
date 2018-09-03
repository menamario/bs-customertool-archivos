package mx.com.bsmexico.customertool.beneficiarios.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	@LayoutField(name = FIELD_CUENTA_BENEFICIARIO, title = "Cuenta beneficiario", length = 18)
	private SimpleStringProperty cuenta;

	@LayoutField(name = FIELD_NUMERO_LINEA_BENEFICIARIO, title = "Número de línea de telefono Móvil del Beneficiario", length = 10, disable = true, required = false)
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

	@LayoutField(name = FIELD_RAZON_SOCIAL, title = "Razón Social", length = 70)
	private SimpleStringProperty razonSocial;

	@LayoutField(name = FIELD_NOMBRE, title = "Nombre", length = 25)
	private SimpleStringProperty nombre;

	@LayoutField(name = FIELD_APELLIDO_PATERNO, title = "Apellido paterno", length = 30)
	private SimpleStringProperty apellidoPaterno;

	@LayoutField(name = FIELD_APELLIDO_MATERNO, title = "Apellido materno", length = 30)
	private SimpleStringProperty apellidoMaterno;

	@RestrictionLayoutField(description = "00 Cuenta Banco Sabadell, 04 CLABE SPEI", fields = {
			FIELD_CUENTA_BENEFICIARIO })
	private static Predicate<String> cuentaPredicate = t -> (StringUtils.isNotBlank(t) && t.length() <= 18
			&& StringUtils.isNumeric(t));

	@RestrictionLayoutField(description = "00 Cuenta Banco Sabadell, 40 CLABE SPEI", fields = { FIELD_TIPO_CUENTA })
	private static Predicate<String> tipoCuentaPredicate = t -> (t == null) ? false : t.matches("00|40");

	@RestrictionLayoutField(description = "00 Persona Física, 01 Persona Moral", fields = { FIELD_TIPO_PERSONA })
	private static Predicate<String> tipoPersonaPredicate = t -> (t == null) ? false : t.matches("00|01");

	@RestrictionLayoutField(description = "Tipo de moneda : MXN, USD, EUR", fields = { FIELD_MONEDA })
	private static Predicate<String> monedaPredicate = t -> (t == null) ? false : t.matches("MXN|USD|EUR");

	@RestrictionLayoutField(description = "Importe máximo no mayor a 9999999999999999.99", fields = {
			FIELD_IMPORTE_MAXIMO_PAGAR })
	private static Predicate<String> importeMaximoPredicate = v -> {
		return (StringUtils.isNotBlank(v) && NumberUtils.isCreatable(v) && Double.valueOf(v) <= MAX_IMPORTE);
	};

	private Map<String, Boolean> estatus = new HashMap<String, Boolean>();

	public Beneficiario() {
		cuenta = new SimpleStringProperty();
		estatus.put("cuenta", true);
		numLinea = new SimpleStringProperty();
		estatus.put("numLinea", true);
		bancoParticipante = new SimpleStringProperty();
		estatus.put("bancoParticipante", true);
		tipoCuenta = new SimpleStringProperty();
		estatus.put("tipoCuenta", true);
		moneda = new SimpleStringProperty();
		estatus.put("moneda", true);
		importeMaximo = new SimpleStringProperty();
		estatus.put("importeMaximo", true);
		tipoPersona = new SimpleStringProperty();
		estatus.put("tipoPersona", true);
		razonSocial = new SimpleStringProperty();
		estatus.put("razonSocial", true);
		nombre = new SimpleStringProperty();
		estatus.put("nombre", true);
		apellidoPaterno = new SimpleStringProperty();
		estatus.put("apellidoPaterno", true);
		apellidoMaterno = new SimpleStringProperty();
		estatus.put("apellidoMaterno", true);
	}

	public Map<String, Boolean> getEstatus() {
		return estatus;
	}

	public void setEstatus(String property, Boolean value) {
		this.getEstatus().put(property, value);
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
		this.numLinea.set(numLinea);
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
		this.moneda.set(moneda);
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

	public List<String> getInvalids() {
		List<String> invalids = new ArrayList<String>();
		if (this.tipoCuenta.get() != null && this.tipoCuenta.get().equals("40")
				&& (this.bancoParticipante.get() == null || this.bancoParticipante.get().length() != 3)) {
			invalids.add("bancoParticipante");
		}
		if (this.tipoCuenta.get() != null && this.tipoCuenta.get().equals("40")
				&& (this.cuenta.get() == null || this.cuenta.get().length() != 18)) {
			invalids.add("cuenta");
		}
		if (this.tipoCuenta.get() != null && this.tipoCuenta.get().equals("00")
				&& (this.cuenta.get() == null || this.cuenta.get().length() != 11)) {
			invalids.add("cuenta");
		}
		if (this.tipoPersona.get() != null && this.tipoPersona.get().equals("00") && this.razonSocial.get() != null
				&& this.razonSocial.get().length() > 0) {
			invalids.add("razonSocial");
		}
		if (this.tipoPersona.get() != null && this.tipoPersona.get().equals("00")
				&& (this.nombre.get() == null || this.nombre.get().length() == 0)) {
			invalids.add("nombre");
		}
		if (this.tipoPersona.get() != null && this.tipoPersona.get().equals("00")
				&& (this.apellidoPaterno.get() == null || this.apellidoPaterno.get().length() == 0)) {
			invalids.add("apellidoPaterno");
		}
		if (this.tipoPersona.get() != null && this.tipoPersona.get().equals("00")
				&& (this.apellidoMaterno.get() == null || this.apellidoMaterno.get().length() == 0)) {
			invalids.add("apellidoMaterno");
		}

		if (this.tipoPersona.get() != null && this.tipoPersona.get().equals("01")
				&& (this.razonSocial.get() == null || this.razonSocial.get().length() == 0)) {
			invalids.add("razonSocial");
		}
		if (this.tipoPersona.get() != null && this.tipoPersona.get().equals("01") && this.nombre.get() != null
				&& this.nombre.get().length() > 0) {
			invalids.add("nombre");
		}
		if (this.tipoPersona.get() != null && this.tipoPersona.get().equals("01") && this.apellidoPaterno.get() != null
				&& this.apellidoPaterno.get().length() > 0) {
			invalids.add("apellidoPaterno");
		}
		if (this.tipoPersona.get() != null && this.tipoPersona.get().equals("01") && this.apellidoMaterno.get() != null
				&& this.apellidoMaterno.get().length() > 0) {
			invalids.add("apellidoMaterno");
		}

		return invalids;

	}

	public boolean validar() {
		
		resetEstatus();
		
		
		// Validar Cuenta

		if (this.tipoCuenta.get() != null && this.tipoCuenta.get().equals("40")
				&& (this.cuenta.get() == null || this.cuenta.get().length() != 18)) {
			this.setEstatus("cuenta", false);
		}
		if (this.tipoCuenta.get() != null && this.tipoCuenta.get().equals("00")
				&& (this.cuenta.get() == null || this.cuenta.get().length() != 11)) {
			this.setEstatus("cuenta", false);
		}
		
		if (this.cuenta.get() == null || this.cuenta.get().length()==0){
			this.setEstatus("cuenta", false);
		}

		// Validar Banco Participante
		if (this.tipoCuenta.get() != null && this.tipoCuenta.get().equals("40")
				&& (this.bancoParticipante.get() == null || this.bancoParticipante.get().length() != 3)) {
			this.setEstatus("bancoParticipante", false);
		}

		// Validar Tipo de Cuenta
		if (!this.tipoCuentaPredicate.test(this.tipoCuenta.get())){
			this.setEstatus("tipoCuenta", false);
		}

		// Validar Moneda
		if (!this.monedaPredicate.test(this.moneda.get())) {
			this.setEstatus("moneda", false);
		}

		// Validar Importe
		if (!this.importeMaximoPredicate.test(this.importeMaximo.get())) {
			this.setEstatus("importeMaximo", false);
		}

		// Validar tipoPersona
		if (!this.tipoPersonaPredicate.test(this.tipoPersona.get())) {
			this.setEstatus("tipoPersona", false);
		}
		
		//Validar Razon Social
		
		if (this.tipoPersona.get() != null && this.tipoPersona.get().equals("00") && this.razonSocial.get() != null
				&& this.razonSocial.get().length() > 0) {
			this.setEstatus("razonSocial", false);
		}
		
		if (this.tipoPersona.get() != null && this.tipoPersona.get().equals("01")
				&& (this.razonSocial.get() == null || this.razonSocial.get().length() == 0)) {
			this.setEstatus("razonSocial", false);
		}
		
		// Validar Nombre

		if (this.tipoPersona.get() != null && this.tipoPersona.get().equals("00")
				&& (this.nombre.get() == null || this.nombre.get().length() == 0)) {
			this.setEstatus("nombre", false);
		}
		
		if (this.tipoPersona.get() != null && this.tipoPersona.get().equals("01") && this.nombre.get() != null
				&& this.nombre.get().length() > 0) {
			this.setEstatus("nombre", false);
		}
		
		// Validar Nombre
		
		if (this.tipoPersona.get() != null && this.tipoPersona.get().equals("00")
				&& (this.apellidoPaterno.get() == null || this.apellidoPaterno.get().length() == 0)) {
			this.setEstatus("apellidoPaterno", false);
		}
		
		if (this.tipoPersona.get() != null && this.tipoPersona.get().equals("01") && this.apellidoPaterno.get() != null
				&& this.apellidoPaterno.get().length() > 0) {
			this.setEstatus("apellidoPaterno", false);
		}
		
		//Validar Apellido Materno
		if (this.tipoPersona.get() != null && this.tipoPersona.get().equals("00")
				&& (this.apellidoMaterno.get() == null || this.apellidoMaterno.get().length() == 0)) {
			this.setEstatus("apellidoMaterno", false);
		}

		if (this.tipoPersona.get() != null && this.tipoPersona.get().equals("01") && this.apellidoMaterno.get() != null
				&& this.apellidoMaterno.get().length() > 0) {
			this.setEstatus("apellidoMaterno", false);
		}
		
		for(boolean b: this.getEstatus().values()){
			if(!b) return false;
		}
		return true;

	}
	
	private void resetEstatus() {
		this.getEstatus().replaceAll((k, v) -> true);
	}

	public boolean isActive(){
		if(this.cuenta.get()!=null && this.cuenta.get().length()>0) return true;
		if(this.numLinea.get()!=null && this.numLinea.get().length()>0) return true;
		if(this.bancoParticipante.get()!=null && this.bancoParticipante.get().length()>0) return true;
		if(this.tipoCuenta.get()!=null && this.tipoCuenta.get().length()>0) return true;
		if(this.moneda.get()!=null && this.moneda.get().length()>0) return true;
		if(this.importeMaximo.get()!=null && this.importeMaximo.get().length()>0) return true;
		if(this.tipoPersona.get()!=null && this.tipoPersona.get().length()>0) return true;
		if(this.razonSocial.get()!=null && this.razonSocial.get().length()>0) return true;
		if(this.nombre.get()!=null && this.nombre.get().length()>0) return true;
		if(this.apellidoPaterno.get()!=null && this.apellidoPaterno.get().length()>0) return true;
		if(this.apellidoMaterno.get()!=null && this.apellidoMaterno.get().length()>0) return true;
		return false;
		
	}

}
