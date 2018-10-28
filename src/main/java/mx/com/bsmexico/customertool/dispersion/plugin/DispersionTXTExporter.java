package mx.com.bsmexico.customertool.dispersion.plugin;

import java.math.BigDecimal;
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
			record.add(new RecordPosition(27, 45, StringUtils.leftPad(dispersion.getCuentaAbono(),18)));
			record.add(new RecordPosition(45, 47, dispersion.getTipoPersona()));
			record.add(new RecordPosition(47, 87, dispersion.getNombre()!=null?dispersion.getNombre().trim():null));
			record.add(new RecordPosition(87, 100, dispersion.getRfc()!=null?dispersion.getRfc().trim():null));
			record.add(new RecordPosition(100, 118, dispersion.getCurp()!=null?dispersion.getCurp().trim():null));
			record.add(new RecordPosition(118, 121, dispersion.getDivisa()));
			record.add(new RecordPosition(121, 136, (String.format("%015.2f", new BigDecimal(dispersion.getImporte()))).trim()));
			record.add(new RecordPosition(136, 151, StringUtils.isEmpty(dispersion.getIva())?String.format("%015.2f", new BigDecimal(0)):(String.format("%015.2f", new BigDecimal(dispersion.getIva()))).trim()));
			record.add(new RecordPosition(151, 191, dispersion.getConcepto()!=null?dispersion.getConcepto().trim():null));
			record.add(new RecordPosition(191, 211, StringUtils.leftPad(dispersion.getReferencia(),20)));
			record.add(new RecordPosition(211, 271, dispersion.getCorreoElectronico()!=null?dispersion.getCorreoElectronico().trim():null));
			record.add(new RecordPosition(271, 281, dispersion.getNumeroCelular()));
		} else {
			if (dop.equals("HA")) {				
				final String ha = dop + dispersion.getTipoMovimiento() + dispersion.getAplicacion()
						+ String.format("%015.2f", new BigDecimal(dispersion.getFecha())).trim() 
						+ String.format("%06d", new Long(dispersion.getTipoTransaccion())).trim();
				record.add(new RecordPosition(0, ha.length(), ha));
			} else {
				final String hb = dop +
						String.format("%015.2f", new BigDecimal(dispersion.getTipoMovimiento())).trim()+
						String.format("%06d", new Long(dispersion.getAplicacion())).trim(); 
				record.add(new RecordPosition(0, hb.length(), hb));
			}
		}
		return record;
	}
}
