package mx.com.bsmexico.customertool.dispersion.plugin;

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
@LayoutModel(type = LayoutModelType.PROPERTY_JAVABEANS, validatorClass = DispersionValidator.class)
public class Dispersion {
	
	String pattern = "###############0.00";
	DecimalFormat decimalFormat = new DecimalFormat(pattern);

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

	@LayoutField(name = FIELD_RFC, title = "RFC", length = 13)
	private SimpleStringProperty rfc;

	@LayoutField(name = FIELD_CURP, title = "CURP", length = 18)
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
		this.tipoPersona.set(tipoPersona.toUpperCase());
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
		this.divisa.set(divisa.toUpperCase());
	}

	public String getImporte() {
		return importe.get();
	}

	public void setImporte(String importe) {
		if (NumberUtils.isCreatable(StringUtils.stripStart(importe,"0")) && Double.valueOf(StringUtils.stripStart(importe,"0")) < 9999999999999999.99) {
			importe = decimalFormat.format(new BigDecimal(StringUtils.stripStart(importe,"0")));
		}
		this.importe.set(importe);
	}

	public String getIva() {
		return iva.get();
	}

	public void setIva(String iva) {
		if (NumberUtils.isCreatable(StringUtils.stripStart(iva,"0")) && Double.valueOf(StringUtils.stripStart(iva,"0")) < 9999999999999999.99) {
			iva = decimalFormat.format(new BigDecimal(StringUtils.stripStart(iva,"0")));
		}
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
	
	public String getDetalleOperacion() {
		return detalleOperacion;
	}

	public void setDetalleOperacion(String detalleOperacion) {
		this.detalleOperacion = detalleOperacion;
	}

	
	
	public StringProperty tipoMovimientoProperty(){return tipoMovimiento;}
	public StringProperty aplicacionProperty(){return aplicacion;}
	public StringProperty fechaProperty(){return fecha;}
	public StringProperty tipoTransaccionProperty(){return tipoTransaccion;}
	public StringProperty cuentaCargoProperty(){return cuentaCargo;}
	public StringProperty tipoCuentaBeneficiarioProperty(){return tipoCuentaBeneficiario;}
	public StringProperty cuentaAbonoProperty(){return cuentaAbono;}
	public StringProperty tipoPersonaProperty(){return tipoPersona;}
	public StringProperty nombreProperty(){return nombre;}
	public StringProperty rfcProperty(){return rfc;}
	public StringProperty curpProperty(){return curp;}
	public StringProperty divisaProperty(){return divisa;}
	public StringProperty importeProperty(){return importe;}
	public StringProperty ivaProperty(){return iva;}
	public StringProperty conceptoProperty(){return concepto;}
	public StringProperty referenciaProperty(){return referencia;}
	public StringProperty correoElectronicoProperty(){return correoElectronico;}
	public StringProperty numeroCelularProperty(){return numeroCelular;}
	
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aplicacion.get() == null) ? 0 : aplicacion.get().hashCode());
		result = prime * result + ((concepto.get() == null) ? 0 : concepto.get().hashCode());
		result = prime * result + ((correoElectronico.get() == null) ? 0 : correoElectronico.get().hashCode());
		result = prime * result + ((cuentaAbono.get() == null) ? 0 : cuentaAbono.get().hashCode());
		result = prime * result + ((cuentaCargo.get() == null) ? 0 : cuentaCargo.get().hashCode());
		result = prime * result + ((curp.get() == null) ? 0 : curp.get().hashCode());
		result = prime * result + ((divisa.get() == null) ? 0 : divisa.get().hashCode());
		result = prime * result + ((fecha.get() == null) ? 0 : fecha.get().hashCode());
		result = prime * result + ((importe.get() == null) ? 0 : importe.get().hashCode());
		result = prime * result + ((iva.get() == null) ? 0 : iva.get().hashCode());
		result = prime * result + ((nombre.get() == null) ? 0 : nombre.get().hashCode());
		result = prime * result + ((numeroCelular.get() == null) ? 0 : numeroCelular.get().hashCode());
		result = prime * result + ((referencia.get() == null) ? 0 : referencia.get().hashCode());
		result = prime * result + ((rfc.get() == null) ? 0 : rfc.get().hashCode());
		result = prime * result + ((tipoCuentaBeneficiario.get() == null) ? 0 : tipoCuentaBeneficiario.get().hashCode());
		result = prime * result + ((tipoMovimiento.get() == null) ? 0 : tipoMovimiento.get().hashCode());
		result = prime * result + ((tipoPersona.get() == null) ? 0 : tipoPersona.get().hashCode());
		result = prime * result + ((tipoTransaccion.get() == null) ? 0 : tipoTransaccion.get().hashCode());
		return result;
	}
	
	

}
