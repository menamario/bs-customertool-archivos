package mx.com.bsmexico.customertool.dispersion.plugin;

import mx.com.bsmexico.customertool.api.layouts.control.ColumnTableFactoryAbstract;

/**
 * @author jchr
 *
 */
public class ColumnDispersionFactory extends ColumnTableFactoryAbstract<Dispersion> {

	public ColumnDispersionFactory() throws IllegalArgumentException {
		super(Dispersion.class);
	}
}
