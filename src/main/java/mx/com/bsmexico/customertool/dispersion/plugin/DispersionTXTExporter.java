package mx.com.bsmexico.customertool.dispersion.plugin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.QuoteMode;
import org.apache.commons.lang3.StringUtils;

import mx.com.bsmexico.customertool.api.exporter.CSVExporter;
import mx.com.bsmexico.customertool.api.exporter.ExportSource;

/**
 * @author jchr
 *
 */
public class DispersionTXTExporter extends CSVExporter<Dispersion> {

	public DispersionTXTExporter(ExportSource<Dispersion> source) {
		super(new DispersionGroupDataAdapter(source));
	}

	@Override
	protected Object[] getRecord(final Dispersion dispersion) {
		final List<Object> record = new ArrayList<>();
		record.add(this.builRecord(dispersion));
		return record.toArray();
	}

	@Override
	protected String[] getHeader() {
		return null;
	}

	@Override
	protected Character getCustomDelimiter() {
		return " ".charAt(0);
	}
	@Override
	protected QuoteMode getQuoteMode() {
		return null;
	}
	
	private String builRecord(final Dispersion dispersion) {
		StringBuffer record = new StringBuffer();
		final String dop = StringUtils.isBlank(dispersion.getDetalleOperacion()) ? "DE"
				: dispersion.getDetalleOperacion();
		record.append(normalize(dop, 2));
		record.append(normalize(dispersion.getTipoMovimiento(), 1));
		record.append(normalize(dispersion.getAplicacion(), 1));
		if (!dop.equals("HB")) {
			record.append(normalize(dispersion.getFecha(), 8));
			record.append(normalize(dispersion.getTipoTransaccion(), 2));
			record.append(normalize(dispersion.getCuentaCargo(), 11));
			record.append(normalize(dispersion.getTipoCuentaBeneficiario(), 2));
			if (dop.equals("DE")) {
				record.append(normalize(dispersion.getCuentaAbono(), 18));
				record.append(normalize(dispersion.getTipoPersona(), 2));
				record.append(normalize(dispersion.getNombre(), 40));
				record.append(normalize(dispersion.getRfc(), 13));
				record.append(normalize(dispersion.getCurp(), 18));
				record.append(normalize(dispersion.getDivisa(), 3));
				record.append(normalize(dispersion.getImporte(), 15));
				record.append(normalize(dispersion.getIva(), 15));
			}
		}

		return record.toString();
	}

	/**
	 * @param data
	 * @param size
	 * @return
	 */
	private String normalize(final String data, final int size) {
		return StringUtils.rightPad(data, size);
	}

}
