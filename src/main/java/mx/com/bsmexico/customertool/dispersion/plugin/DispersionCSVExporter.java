package mx.com.bsmexico.customertool.dispersion.plugin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import mx.com.bsmexico.customertool.api.process.CSVExporter;
import mx.com.bsmexico.customertool.api.process.ExportSource;

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
		
		if (!("HB".equals(dispersion.getDetalleOperacion()))){
		record.add(dispersion.getFecha());
		record.add(dispersion.getTipoTransaccion());
		}
		if (StringUtils.isBlank(dispersion.getDetalleOperacion())){
			record.add(dispersion.getCuentaCargo());
			record.add(dispersion.getTipoCuentaBeneficiario());
			record.add(dispersion.getCuentaAbono());
			record.add(dispersion.getTipoPersona());
			record.add(dispersion.getNombre()!=null?dispersion.getNombre().trim():null);
			record.add(dispersion.getRfc()!=null?dispersion.getRfc().trim():null);
			record.add(dispersion.getCurp()!=null?dispersion.getCurp().trim():null);
			record.add(dispersion.getDivisa());
			record.add(dispersion.getImporte());
			record.add(dispersion.getIva());
			record.add(dispersion.getConcepto()!=null?dispersion.getConcepto().trim():null);
			record.add(dispersion.getReferencia());
			record.add(dispersion.getCorreoElectronico()!=null?dispersion.getCorreoElectronico().trim():null);
			record.add(dispersion.getNumeroCelular());
		}
		return record.toArray();
	}

	@Override
	protected String[] getHeader() {
		return null;
	}
	
	@Override
	protected Character getCustomDelimiter() {
		return '|';
	}

	
}
