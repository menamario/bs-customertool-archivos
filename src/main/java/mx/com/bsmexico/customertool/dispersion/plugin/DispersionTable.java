package mx.com.bsmexico.customertool.dispersion.plugin;

import mx.com.bsmexico.customertool.api.layouts.control.ColumnTableFactoryAbstract;
import mx.com.bsmexico.customertool.api.layouts.control.LayoutTable;

public class DispersionTable extends LayoutTable<Dispersion> {

	private final int INITIAL_CAPACITY = 50;

	public DispersionTable(final ColumnTableFactoryAbstract<Dispersion> columnFactory)
			throws IllegalArgumentException, InstantiationError {
		super(columnFactory);

	}

	protected void polulate() {
		for (int idx = 0; idx <= INITIAL_CAPACITY; idx++) {
			this.data.add(new Dispersion());
		}
	}

	@Override
	protected void addRow() {
		System.out.println("adding rows");
		table.getItems().add(new Dispersion());

	}

}
