package mx.com.bsmexico.customertool.dispersion.plugin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import mx.com.bsmexico.customertool.api.exporter.CSVExporter;
import mx.com.bsmexico.customertool.api.exporter.ExportSource;

/**
 * @author jchr
 *
 */
public class DispersionCSVExporter extends CSVExporter<Dispersion> {
	
	public DispersionCSVExporter(ExportSource<Dispersion> source) {
		super(new DispersionGroupDataAdapter(source));
	}

	@Override
	protected Object[] getRecord(final Dispersion dispersion) {
		final List<Object> record = new ArrayList<>();
		record.add(StringUtils.isBlank(dispersion.getDetalleOperacion())?"DE":dispersion.getDetalleOperacion());
		record.add(dispersion.getTipoMovimiento());
		record.add(dispersion.getAplicacion());
		record.add(dispersion.getFecha());
		record.add(dispersion.getTipoTransaccion());
		record.add(dispersion.getCuentaCargo());
		record.add(dispersion.getTipoCuentaBeneficiario());
		record.add(dispersion.getCuentaAbono());
		record.add(dispersion.getTipoPersona());
		record.add(dispersion.getNombre());
		record.add(dispersion.getRfc());
		record.add(dispersion.getCurp());
		record.add(dispersion.getDivisa());
		record.add(dispersion.getImporte());
		record.add(dispersion.getIva());		
		return record.toArray();
	}

	@Override
	protected String[] getHeader() {
		return null;
	}

	
}
