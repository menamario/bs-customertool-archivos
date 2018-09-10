package mx.com.bsmexico.customertool.dispersion.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import mx.com.bsmexico.customertool.api.report.ContextReport;
import mx.com.bsmexico.customertool.api.report.ReportDataSourceFactory;
import mx.com.bsmexico.customertool.api.report.ReportGenerator;
import mx.com.bsmexico.customertool.dispersion.plugin.DispersionPago;

public class ReportTest {

	@Test
	public void reportTest() {
		final DispersionPago pago1 = new DispersionPago();
		pago1.setBanco("Test Banco");
		pago1.setBeneficiario("Test Beneficiario");
		pago1.setClaveRastreo("Test Clave operacion");
		pago1.setCliente("Test Cliente");
		pago1.setComision(0.01);
		pago1.setCuentaDeposito("D1234");
		pago1.setCuentaRetiro("R4321");
		pago1.setEstadoOperacion("Test Estado ");
		pago1.setFechaHoraOperacion("2018/09/20");
		pago1.setFolioOperacion("Test Folio");
		pago1.setImporte(1000.00);
		pago1.setIva(40.00);
		pago1.setIvaComision(0.01);
		pago1.setMoneda("MXN");
		pago1.setMotivoPago("Tesr Motivo");
		pago1.setReferencia("123456");
		pago1.setRfc("Test RFC");
		pago1.setUsuario("Test Usuario");
		final DispersionPago pago2 = new DispersionPago();
		pago2.setBanco("Test Banco");
		pago2.setBeneficiario("Test Beneficiario");
		pago2.setClaveRastreo("Test Clave operacion");
		pago2.setCliente("Test Cliente");
		pago2.setComision(0.01);
		pago2.setCuentaDeposito("D1234");
		pago2.setCuentaRetiro("R4321");
		pago2.setEstadoOperacion("Test Estado ");
		pago2.setFechaHoraOperacion("2018/09/20");
		pago2.setFolioOperacion("Test Folio");
		pago2.setImporte(1000.00);
		pago2.setIva(40.00);
		pago2.setIvaComision(0.01);
		pago2.setMoneda("MXN");
		pago2.setMotivoPago("Tesr Motivo");
		pago2.setReferencia("123456");
		pago2.setRfc("Test RFC");
		pago2.setUsuario("Test Usuario");
		final List<DispersionPago> data = new ArrayList<>();
		data.add(pago1);
		data.add(pago2);
		final File file = new File("testDispersionPago.pdf");
		try {
			final FileOutputStream fout = new FileOutputStream(file);
			file.createNewFile();
			final ContextReport context = new ContextReport();
			final ClassLoader classLoader = getClass().getClassLoader();
			final File logo = new File(classLoader.getResource("logoSabadell.png").getFile());
			final FileInputStream imgStream = new FileInputStream(logo);
			context.addImageParameter("logo", imgStream);
			ReportGenerator.generateFromCompiledReport("reports/ComprobanteDispersionPago.jasper", context,
					ReportDataSourceFactory.getBeanDataSource(data), fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

}
