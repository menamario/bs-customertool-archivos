package mx.com.bsmexico.customertool.dispersion.plugin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import mx.com.bsmexico.customertool.api.process.ExportSource;

/**
 * @author jchr
 *
 */
public class DispersionGroupDataAdapter implements ExportSource<Dispersion> {

	private List<Dispersion> data;

	public DispersionGroupDataAdapter(final ExportSource<Dispersion> source) {
		data = (source == null) ? new ArrayList<>() : this.groupData(source);
	}

	@Override
	public List<Dispersion> getData() {
		return this.data;
	}

	/**
	 * 
	 */
	private List<Dispersion> groupData(final ExportSource<Dispersion> source) {
		final List<Dispersion> data = source.getData();
		final List<Dispersion> groupData = new ArrayList<>();
		final Map<String, List<Dispersion>> map = new HashMap<>();
		final Set<String> keys = new HashSet<>();
		long totalRegistros = 0L;
		BigDecimal totalImporte = BigDecimal.ZERO;
		String key = StringUtils.EMPTY;
		for (Dispersion dispersion : data) {
			key = this.getGroupKey(dispersion);
			if (map.get(key.toString()) == null) {
				map.put(key.toString(), new ArrayList<Dispersion>());
			}
			map.get(key.toString()).add(dispersion);
			keys.add(key);
			totalRegistros++;
			totalImporte = totalImporte
					.add(NumberUtils.isCreatable(dispersion.getImporte()) ? new BigDecimal(dispersion.getImporte())
							: BigDecimal.ZERO);
		}

		//final Set<String> keys = map.keySet();
		String[] splitKey = null;
		Dispersion ha = null;
		for (String k : keys) {
			splitKey = StringUtils.split(k, "|");
			ha = new Dispersion();
			ha.setDetalleOperacion(splitKey[0]);
			ha.setTipoMovimiento(splitKey[1]);
			ha.setAplicacion(splitKey[2]);
			BigDecimal subtotal = BigDecimal.ZERO;
			for (Dispersion d : map.get(k)) {
				subtotal = subtotal.add(
						NumberUtils.isCreatable(d.getImporte()) ? new BigDecimal(d.getImporte()) : BigDecimal.ZERO);
			}
			
			ha.setFecha((String.format("%015.2f", subtotal)).trim());
			ha.setTipoTransaccion(String.format("%06d", map.get(k).size()).trim());
			groupData.add(ha);
			groupData.addAll(map.get(k));
		}
		final Dispersion hb = new Dispersion();
		hb.setDetalleOperacion("HB");
		hb.setTipoMovimiento((String.format("%015.2f", totalImporte)).trim());
		hb.setAplicacion((String.format("%06d", totalRegistros)).trim());
		groupData.add(hb);
		return groupData;
	}

	/**
	 * @param dispersion
	 * @return
	 */
	private String getGroupKey(final Dispersion dispersion) {
		final StringBuffer key = new StringBuffer();
		key.append("HA").append("|").append(dispersion.getDivisa()).append("|").append(dispersion.getCuentaCargo());
		return key.toString();
	}

}
