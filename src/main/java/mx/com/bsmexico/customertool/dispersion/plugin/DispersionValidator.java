package mx.com.bsmexico.customertool.dispersion.plugin;

import java.util.List;

import mx.com.bsmexico.customertool.api.layouts.model.validation.LayoutModelValidator;

public class DispersionValidator extends LayoutModelValidator<Dispersion> {

	@Override
	public boolean isValidField(String fieldName, Dispersion model) {
		return true;
	}

	@Override
	public boolean isValid(Dispersion model) {
		return true;
	}

	@Override
	public boolean isValid(List<Dispersion> models) {
		return true;
	}

}
