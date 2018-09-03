package mx.com.bsmexico.customertool.dispersion.plugin;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mx.com.bsmexico.customertool.api.exporter.ImportTarget;
import mx.com.bsmexico.customertool.api.layouts.control.ColumnTableFactoryAbstract;
import mx.com.bsmexico.customertool.api.layouts.control.LayoutTable;


public class DispersionTable extends LayoutTable<Dispersion> implements ImportTarget<Dispersion> {

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

	@Override
	public void setData(List<Dispersion> data) {
		ObservableList<Dispersion> observableList = FXCollections.observableList(data);
		table.setItems(observableList);
	}

}
