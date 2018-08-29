package mx.com.bsmexico.customertool.beneficiarios;

import mx.com.bsmexico.customertool.api.layouts.LayoutFactoryAbstract;
import mx.com.bsmexico.customertool.api.layouts.control.ColumnTableFactoryAbstract;
import mx.com.bsmexico.customertool.api.layouts.control.LayoutTable;

public class BeneficiarioTable extends LayoutTable<Beneficiario> {

	private final int INITIAL_CAPACITY = 10;

	public BeneficiarioTable(final LayoutFactoryAbstract layoutFactory,
			final ColumnTableFactoryAbstract<Beneficiario> columnFactory)
			throws IllegalArgumentException, InstantiationError {
		super(layoutFactory, columnFactory);

	}

	protected void polulate() {
		for (int idx = 0; idx <= INITIAL_CAPACITY; idx++) {
			this.data.add(new Beneficiario());
		}
	}

}
