package mx.com.bsmexico.customertool.dispersion.plugin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import mx.com.bsmexico.customertool.api.exporter.CSVExporter;
import mx.com.bsmexico.customertool.api.exporter.ExportSource;

/**
 * @author jchr
 *
 */
public class DispersionCSVExporter extends CSVExporter<Dispersion> {

	public DispersionCSVExporter(ExportSource<Dispersion> source) {
		super(source);
		groupData();
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

	/**
	 * 
	 */
	private void groupData() {
		final List<Dispersion> data = this.source.getData();
		final List<Dispersion> groupData = new ArrayList<>();
		final Map<String, List<Dispersion>> map = new HashMap<>();
		long totalRegistros = 0L;
		BigDecimal totalImporte = BigDecimal.ZERO;
		String key = StringUtils.EMPTY;
		for (Dispersion dispersion : data) {
			key = this.getGroupKey(dispersion);
			if (map.get(key.toString()) == null) {
				map.put(key.toString(), new ArrayList<Dispersion>());
			}
			map.get(key.toString()).add(dispersion);
			totalRegistros++;
			totalImporte = totalImporte
					.add(NumberUtils.isCreatable(dispersion.getImporte()) ? new BigDecimal(dispersion.getImporte())
							: BigDecimal.ZERO);
		}

		final Set<String> keys = map.keySet();
		String[] splitKey = null;
		Dispersion ha = null;
		for (String k : keys) {
			splitKey = k.split("|");
			ha = new Dispersion();
			ha.setDetalleOperacion(splitKey[0]);
			ha.setTipoMovimiento(splitKey[1]);
			ha.setAplicacion(splitKey[2]);
			ha.setFecha(splitKey[3]);
			ha.setTipoTransaccion(splitKey[4]);
			groupData.add(ha);
			groupData.addAll(map.get(k));
		}
		final Dispersion hb = new Dispersion();
		ha.setDetalleOperacion("HB");
		ha.setTipoMovimiento(totalImporte.toEngineeringString());
		ha.setAplicacion(String.valueOf(totalRegistros));
		groupData.add(hb);
		// Adapter
		this.source = new ExportSource<Dispersion>() {
			@Override
			public List<Dispersion> getData() {
				return groupData;
			}
		};
	}

	private String getGroupKey(final Dispersion dispersion) {
		final StringBuffer key = new StringBuffer();
		key.append("HA").append("|").append("1").append("|").append(dispersion.getFecha()).append("|")
				.append(dispersion.getDivisa()).append("|").append(dispersion.getCuentaCargo());
		return key.toString();
	}
}
