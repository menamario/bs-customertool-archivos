package mx.com.bsmexico.customertool.dispersion.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import mx.com.bsmexico.customertool.api.process.ExportSource;
import mx.com.bsmexico.customertool.dispersion.plugin.Dispersion;
import mx.com.bsmexico.customertool.dispersion.plugin.DispersionTXTExporter;

public class ExportImportTest {

	@Test
	public void exportTXTTest () {
		DispersionTXTExporter exporter = new DispersionTXTExporter(new ExportSource<Dispersion>() {

			@Override
			public List<Dispersion> getData() {
				final List<Dispersion> dispersiones = new ArrayList<>();
				final Dispersion dispersion = new Dispersion();
				dispersion.setTipoMovimiento("1");
				dispersion.setAplicacion("H");
				dispersion.setFecha("20180913");
				dispersion.setTipoTransaccion("00");
				dispersion.setCuentaCargo("1111111111");
				dispersion.setTipoCuentaBeneficiario("40");
				dispersion.setCuentaAbono("11111111111111111");
				dispersion.setTipoPersona("PF");
				dispersion.setNombre("Test");
				dispersion.setRfc("CARJ8412225S2");
				dispersion.setCurp("CARJ841222HDFHYS06");
				dispersion.setDivisa("MXN");
				dispersion.setImporte("00.00");
				dispersion.setIva("00.00");				
				dispersion.setConcepto("PAGO DIRECTO");
				dispersion.setReferencia("1234567890");
				dispersion.setCorreoElectronico("jesus.chavez@isol.com.mx");
				dispersion.setNumeroCelular("5533354399");
				final Dispersion dispersion1 = new Dispersion();
				dispersion1.setTipoMovimiento("1");
				dispersion1.setAplicacion("H");
				dispersion1.setFecha("20180913");
				dispersion1.setTipoTransaccion("00");
				dispersion1.setCuentaCargo("1111111111");
				dispersion1.setTipoCuentaBeneficiario("40");
				dispersion1.setCuentaAbono("11111111111111111");
				dispersion1.setTipoPersona("PF");
				dispersion1.setNombre("Test");
				dispersion1.setRfc("CARJ8412225S2");
				dispersion1.setCurp("CARJ841222HDFHYS06");
				dispersion1.setDivisa("MXN");
				dispersion1.setImporte("100.00");
				dispersion1.setIva("10.00");				
				dispersion1.setConcepto("PAGO DIRECTO");
				dispersion1.setReferencia("1234567890");
				dispersion1.setCorreoElectronico("jesus.chavez@isol.com.mx");
				dispersion1.setNumeroCelular("5533354399");				
				dispersiones.add(dispersion);
				dispersiones.add(dispersion1);
				return dispersiones;
			}
			
		});
		final File file = new File("exportDispersion.txt");
		
		try {
			file.createNewFile();
			exporter.export(file);			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
	}
}
