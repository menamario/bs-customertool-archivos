package mx.com.bsmexico.customertool.dispersion.plugin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import mx.com.bsmexico.customertool.api.process.ExportSource;
import mx.com.bsmexico.customertool.api.process.FixPositionExporter;
import mx.com.bsmexico.customertool.api.process.RecordPosition;

/**
 * @author jchr
 *
 */
public class DispersionTXTExporter extends FixPositionExporter<Dispersion> {

	public DispersionTXTExporter(ExportSource<Dispersion> source) {
		super(new DispersionGroupDataAdapter(source));
	}

	@Override
	protected List<RecordPosition> getRecord(final Dispersion dispersion) {
		final List<RecordPosition> record = new ArrayList<>();
		final String dop = StringUtils.isBlank(dispersion.getDetalleOperacion()) ? "DE"
				: dispersion.getDetalleOperacion();
		if (dop.equals("DE")) {
			record.add(new RecordPosition(0, 2, dop));
			record.add(new RecordPosition(2, 3, dispersion.getTipoMovimiento()));
			record.add(new RecordPosition(3, 4, dispersion.getAplicacion()));
			record.add(new RecordPosition(4, 12, dispersion.getFecha()));
			record.add(new RecordPosition(12, 14, dispersion.getTipoTransaccion()));
			record.add(new RecordPosition(14, 25, dispersion.getCuentaCargo()));
			record.add(new RecordPosition(25, 27, dispersion.getTipoCuentaBeneficiario()));
			record.add(new RecordPosition(27, 45, dispersion.getCuentaAbono()));
			record.add(new RecordPosition(45, 47, dispersion.getTipoPersona()));
			record.add(new RecordPosition(47, 87, dispersion.getNombre()));
			record.add(new RecordPosition(87, 100, dispersion.getRfc()));
			record.add(new RecordPosition(100, 118, dispersion.getCurp()));
			record.add(new RecordPosition(118, 121, dispersion.getDivisa()));
			record.add(new RecordPosition(121, 136, dispersion.getImporte()));
			record.add(new RecordPosition(136, 151, dispersion.getIva()));
			record.add(new RecordPosition(151, 191, dispersion.getConcepto()));
			record.add(new RecordPosition(191, 211, dispersion.getReferencia()));
			record.add(new RecordPosition(211, 271, dispersion.getCorreoElectronico()));
			record.add(new RecordPosition(271, 281, dispersion.getNumeroCelular()));
		} else {
			if (dop.equals("HA")) {
				final String ha = dop + dispersion.getTipoMovimiento() + dispersion.getAplicacion()
						+ dispersion.getFecha() + dispersion.getTipoTransaccion();
				record.add(new RecordPosition(0, ha.length(), ha));
			} else {
				final String hb = dop + dispersion.getTipoMovimiento() + dispersion.getAplicacion();
				record.add(new RecordPosition(0, hb.length(), hb));
			}
		}
		return record;
	}
}
