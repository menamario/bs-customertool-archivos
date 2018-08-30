package mx.com.bsmexico.customertool.beneficiarios.plugin;

import javafx.beans.property.SimpleStringProperty;
import mx.com.bsmexico.customertool.api.layouts.LayoutFieldModel;
import mx.com.bsmexico.customertool.api.layouts.LayoutModel;

public class Beneficiario implements LayoutModel {

	@LayoutFieldModel(field = "cuenta")
	private SimpleStringProperty cuenta;
	@LayoutFieldModel(field = "numeroLinea")
	private SimpleStringProperty numLinea;
	@LayoutFieldModel(field = "bancoParticipante")
	private SimpleStringProperty bancoParticipante;
	@LayoutFieldModel(field = "tipoCuenta")
	private SimpleStringProperty tipoCuenta;
	@LayoutFieldModel(field = "moneda")
	private SimpleStringProperty moneda;
	@LayoutFieldModel(field = "importeMaximo")
	private SimpleStringProperty importeMaximo;
	@LayoutFieldModel(field = "tipoPersona")
	private SimpleStringProperty tipoPersona;
	@LayoutFieldModel(field = "razonSocial")
	private SimpleStringProperty razonSocial;
	@LayoutFieldModel(field = "nombre")
	private SimpleStringProperty nombre;
	@LayoutFieldModel(field = "apellidoPaterno")
	private SimpleStringProperty apellidoPaterno;
	@LayoutFieldModel(field = "apellidoMaterno")
	private SimpleStringProperty apellidoMaterno;

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
	}

	/**
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta.get();
	}

	/**
	 * @param cuenta the cuenta to set
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
	 * @param numLinea the numLinea to set
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
	public String getImporteMaximo() {
		return importeMaximo.get();
	}

	/**
	 * @param importeMaximo the importeMaximo to set
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
