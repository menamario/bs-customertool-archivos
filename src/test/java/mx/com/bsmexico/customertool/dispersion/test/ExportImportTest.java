package mx.com.bsmexico.customertool.dispersion.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import mx.com.bsmexico.customertool.api.process.ExportSource;
import mx.com.bsmexico.customertool.api.process.ImportTarget;
import mx.com.bsmexico.customertool.dispersion.plugin.Dispersion;
import mx.com.bsmexico.customertool.dispersion.plugin.DispersionCSVImporter;
import mx.com.bsmexico.customertool.dispersion.plugin.DispersionTXTExporter;
import mx.com.bsmexico.customertool.dispersion.plugin.DispersionTXTImporter;

public class ExportImportTest {

	@Test
	public void dispersionSimpleImporterCSV() {
		final ClassLoader classLoader = getClass().getClassLoader();
		final File dispersiones = new File(classLoader.getResource("layout/dispersion_entrada_simple.csv").getFile());
		final DispersionCSVImporter importer = new DispersionCSVImporter(new ImportTarget<Dispersion>(){
			@Override
			public void setData(List<Dispersion> data) {				
				Assert.assertNotNull(data);
				Assert.assertTrue(data.size() == 8);
				Assert.assertTrue("0".equals(data.get(0).getTipoMovimiento()));
				Assert.assertTrue("H".equals(data.get(0).getAplicacion()));
				Assert.assertTrue("20180903".equals(data.get(0).getFecha()));
				Assert.assertTrue("00".equals(data.get(0).getTipoTransaccion()));
				Assert.assertTrue("00000211804".equals(data.get(0).getCuentaCargo()));
				Assert.assertTrue("01".equals(data.get(0).getTipoCuentaBeneficiario()));
				Assert.assertTrue("00000132801".equals(data.get(0).getCuentaAbono()));
				Assert.assertTrue("PF".equals(data.get(0).getTipoPersona()));
				Assert.assertTrue("PASCUAL PEREZ MIRALLES 1".equals(data.get(0).getNombre()));
				Assert.assertTrue(StringUtils.isBlank((data.get(0).getRfc())));
				Assert.assertTrue(StringUtils.isBlank((data.get(0).getCurp())));
				Assert.assertTrue("MXN".equals(data.get(0).getDivisa()));
				Assert.assertTrue("1502.00".equals(data.get(0).getImporte()));
				Assert.assertTrue(StringUtils.isBlank((data.get(0).getIva())));				
				Assert.assertTrue("RESTAMO123".equals(data.get(0).getConcepto()));
				Assert.assertTrue("7777777".equals(data.get(0).getReferencia()));				
				Assert.assertTrue(StringUtils.isBlank((data.get(0).getCorreoElectronico())));
				Assert.assertTrue(StringUtils.isBlank((data.get(0).getNumeroCelular())));
				
				Assert.assertTrue("0".equals(data.get(7).getTipoMovimiento()));
				Assert.assertTrue("H".equals(data.get(7).getAplicacion()));
				Assert.assertTrue("20180903".equals(data.get(7).getFecha()));
				Assert.assertTrue("00".equals(data.get(7).getTipoTransaccion()));
				Assert.assertTrue("00000211804".equals(data.get(7).getCuentaCargo()));
				Assert.assertTrue("01".equals(data.get(7).getTipoCuentaBeneficiario()));
				Assert.assertTrue("012180001415045253".equals(data.get(7).getCuentaAbono()));
				Assert.assertTrue("PF".equals(data.get(7).getTipoPersona()));
				Assert.assertTrue("EMPRESA NUMERO SEIS, SA DE CV".equals(data.get(7).getNombre()));
				Assert.assertTrue("POVR800101D45".equals((data.get(7).getRfc())));
				Assert.assertTrue(StringUtils.isBlank((data.get(7).getCurp())));
				Assert.assertTrue("MXN".equals(data.get(7).getDivisa()));
				Assert.assertTrue("00001.00".equals(data.get(7).getImporte()));
				Assert.assertTrue("1.16".equals((data.get(7).getIva())));				
				Assert.assertTrue("PAGO CUENTA TERCERO CUARENTA123".equals(data.get(7).getConcepto()));
				Assert.assertTrue("6668586".equals(data.get(7).getReferencia()));				
				Assert.assertTrue(StringUtils.isBlank((data.get(7).getCorreoElectronico())));
				Assert.assertTrue("5574859901".equals((data.get(7).getNumeroCelular())));
			}			
		});
		
		try {
			importer.importFile(dispersiones);
		} catch (Exception e) {			
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void dispersionBloqueImporterCSV() {
		final ClassLoader classLoader = getClass().getClassLoader();
		final File dispersiones = new File(classLoader.getResource("layout/dispersion_entrada_bloque.csv").getFile());
		final DispersionCSVImporter importer = new DispersionCSVImporter(new ImportTarget<Dispersion>(){
			@Override
			public void setData(List<Dispersion> data) {				
				Assert.assertNotNull(data);
				Assert.assertTrue(data.size() == 30);
				Assert.assertTrue("0".equals(data.get(0).getTipoMovimiento()));
				Assert.assertTrue("H".equals(data.get(0).getAplicacion()));
				Assert.assertTrue("20180801".equals(data.get(0).getFecha()));
				Assert.assertTrue("00".equals(data.get(0).getTipoTransaccion()));
				Assert.assertTrue("00000212006".equals(data.get(0).getCuentaCargo()));
				Assert.assertTrue("01".equals(data.get(0).getTipoCuentaBeneficiario()));
				Assert.assertTrue("00000239001".equals(data.get(0).getCuentaAbono()));
				Assert.assertTrue("PF".equals(data.get(0).getTipoPersona()));
				Assert.assertTrue("OSCAR YAIR SANCHEZ SALAZAR".equals(data.get(0).getNombre()));
				Assert.assertTrue("MAEY760113T85".equals((data.get(0).getRfc())));
				Assert.assertTrue(StringUtils.isBlank((data.get(0).getCurp())));
				Assert.assertTrue("MXN".equals(data.get(0).getDivisa()));
				Assert.assertTrue("2222.00".equals(data.get(0).getImporte()));
				Assert.assertTrue(StringUtils.isBlank((data.get(0).getIva())));				
				Assert.assertTrue("PAGO, CUENTA, TERCERO, CUARENTA777".equals(data.get(0).getConcepto()));
				Assert.assertTrue("7777778".equals(data.get(0).getReferencia()));				
				Assert.assertTrue("MXTEST@BANCOSABADELL.MX".equals((data.get(0).getCorreoElectronico())));
				Assert.assertTrue(StringUtils.isBlank((data.get(0).getNumeroCelular())));
								
				Assert.assertTrue("0".equals(data.get(29).getTipoMovimiento()));
				Assert.assertTrue("H".equals(data.get(29).getAplicacion()));
				Assert.assertTrue("20180801".equals(data.get(29).getFecha()));
				Assert.assertTrue("00".equals(data.get(29).getTipoTransaccion()));
				Assert.assertTrue("00000212001".equals(data.get(29).getCuentaCargo()));
				Assert.assertTrue("03".equals(data.get(29).getTipoCuentaBeneficiario()));
				Assert.assertTrue("6210000000235077".equals(data.get(29).getCuentaAbono()));
				Assert.assertTrue("PM".equals(data.get(29).getTipoPersona()));
				Assert.assertTrue("EMPRESA DE PASTELES SA, DE CV,".equals(data.get(29).getNombre()));
				Assert.assertTrue("EMP8808301S5".equals((data.get(29).getRfc())));
				Assert.assertTrue(StringUtils.isBlank((data.get(29).getCurp())));
				Assert.assertTrue("MXN".equals(data.get(29).getDivisa()));
				Assert.assertTrue("1025.00".equals(data.get(29).getImporte()));
				Assert.assertTrue("1.16".equals((data.get(29).getIva())));				
				Assert.assertTrue("PAGO CUENTA TERCERO CUARENTA411".equals(data.get(29).getConcepto()));
				Assert.assertTrue("6667766".equals(data.get(29).getReferencia()));				
				Assert.assertTrue("MXTEST01@BANCOSABADELL.MX".equals((data.get(29).getCorreoElectronico())));
				Assert.assertTrue(StringUtils.isBlank((data.get(0).getNumeroCelular())));
			}			
		});
		
		try {
			importer.importFile(dispersiones);
		} catch (Exception e) {			
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void dispersionSimpleImporterTXT() {
		final ClassLoader classLoader = getClass().getClassLoader();
		final File dispersiones = new File(classLoader.getResource("layout/dispersion_entrada_simple_utf8.txt").getFile());
		final DispersionTXTImporter importer = new DispersionTXTImporter(new ImportTarget<Dispersion>(){
			@Override
			public void setData(List<Dispersion> data) {				
				Assert.assertNotNull(data);
				Assert.assertTrue(data.size() == 8);
				Assert.assertTrue("0".equals(data.get(0).getTipoMovimiento()));
				Assert.assertTrue("H".equals(data.get(0).getAplicacion()));
				Assert.assertTrue("20180903".equals(data.get(0).getFecha()));
				Assert.assertTrue("00".equals(data.get(0).getTipoTransaccion()));
				Assert.assertTrue("00000211802".equals(data.get(0).getCuentaCargo()));
				Assert.assertTrue("01".equals(data.get(0).getTipoCuentaBeneficiario()));
				Assert.assertTrue("00000233902".equals(data.get(0).getCuentaAbono()));
				Assert.assertTrue("PM".equals(data.get(0).getTipoPersona()));
				Assert.assertTrue("ABC SORPRESAS SA DE CV".equals(data.get(0).getNombre()));
				Assert.assertTrue("GFT456LOK124".equals((data.get(0).getRfc())));
				Assert.assertTrue(StringUtils.isBlank((data.get(0).getCurp())));
				Assert.assertTrue("USD".equals(data.get(0).getDivisa()));
				Assert.assertTrue("25000.10".equals(data.get(0).getImporte()));
				Assert.assertTrue("1.16".equals((data.get(0).getIva())));				
				Assert.assertTrue("PAGO A CUENTA TERCERO CUARENTA CARACTERE".equals(data.get(0).getConcepto()));
				Assert.assertTrue("7654321".equals(data.get(0).getReferencia()));				
				Assert.assertTrue("MXTEST01@BANCOSABADELL.MX".equals((data.get(0).getCorreoElectronico())));
				Assert.assertTrue("5545748551".equals((data.get(0).getNumeroCelular())));
			}			
		});
		
		try {
			importer.importFile(dispersiones);
		} catch (Exception e) {			
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void dispersionBloqueImporterTXT() {
		final ClassLoader classLoader = getClass().getClassLoader();
		final File dispersiones = new File(classLoader.getResource("layout/dispersion_entrada_bloque_utf8.txt").getFile());
		final DispersionTXTImporter importer = new DispersionTXTImporter(new ImportTarget<Dispersion>(){
			@Override
			public void setData(List<Dispersion> data) {				
				Assert.assertNotNull(data);
				Assert.assertTrue(data.size() == 24);
				Assert.assertTrue("0".equals(data.get(0).getTipoMovimiento()));
				Assert.assertTrue("H".equals(data.get(0).getAplicacion()));
				Assert.assertTrue("20180723".equals(data.get(0).getFecha()));
				Assert.assertTrue("00".equals(data.get(0).getTipoTransaccion()));
				Assert.assertTrue("00000212004".equals(data.get(0).getCuentaCargo()));
				Assert.assertTrue("01".equals(data.get(0).getTipoCuentaBeneficiario()));
				Assert.assertTrue("00000129001".equals(data.get(0).getCuentaAbono()));
				Assert.assertTrue("PF".equals(data.get(0).getTipoPersona()));
				Assert.assertTrue("OSCAR YAIR SANCHEZ SALAZAR".equals(data.get(0).getNombre()));
				Assert.assertTrue("MAJO760113T85".equals((data.get(0).getRfc())));
				Assert.assertTrue("MAJO760114MNERSL01".equals((data.get(0).getCurp())));
				Assert.assertTrue("MXN".equals(data.get(0).getDivisa()));
				Assert.assertTrue("25000.10".equals(data.get(0).getImporte()));
				Assert.assertTrue("1.16".equals((data.get(0).getIva())));				
				Assert.assertTrue("DEBO A CUENTA CUARENTA CARACTERE".equals(data.get(0).getConcepto()));
				Assert.assertTrue("7654321".equals(data.get(0).getReferencia()));				
				Assert.assertTrue("MXTEST01@BANCOSABADELL.MX".equals((data.get(0).getCorreoElectronico())));
				Assert.assertTrue("5545748551".equals((data.get(0).getNumeroCelular())));
			}			
		});
		
		try {
			importer.importFile(dispersiones);
		} catch (Exception e) {			
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	
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
