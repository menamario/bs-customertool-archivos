package mx.com.bsmexico.customertool.api.layouts;

import java.io.File;
import java.io.InputStream;

import mx.com.bsmexico.customertool.api.layouts.model.Layout;

/**
 * @author jchr
 *
 */
public abstract class LayoutFactoryAbstract {

	protected InputStream layout;

	protected LayoutFactoryAbstract(final InputStream layout) throws IllegalArgumentException {
		if (layout == null ){//|| !layout.exists() || !layout.canRead() && !layout.isFile()) {
			throw new IllegalArgumentException("The file does not exist or can not be accessed");
		}
		this.layout = layout;
	}

	/**
	 * Get Layout instance
	 * 
	 * @return
	 */
	public abstract Layout getLayoutInstance() throws Exception;
}