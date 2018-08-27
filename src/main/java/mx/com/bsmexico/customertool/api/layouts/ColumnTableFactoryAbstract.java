package mx.com.bsmexico.customertool.api.layouts;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import mx.com.bsmexico.customertool.api.layouts.model.Field;
import mx.com.bsmexico.customertool.beneficiarios.Beneficiario;


/**
 * @author jchr
 *
 * @param <T>
 */
public abstract class ColumnTableFactoryAbstract<T extends LayoutModel> {
	private Class<T> type;
	private java.lang.reflect.Field[] classFields;

	/**
	 * @param type
	 * @throws IllegalArgumentException
	 */
	public ColumnTableFactoryAbstract(Class<T> type) throws IllegalArgumentException {
		if (type == null) {
			throw new IllegalArgumentException("Type can not be null");
		}
		this.type = type;
	}

	/**
	 * @param field
	 * @param width
	 * @return
	 * @throws Exception
	 */
	
	public TableColumn<T, ?> getInstance(final Field field, final int width) throws Exception {

		
		final TableColumn column = new TableColumn(field.getTitle());
		column.setMinWidth(width);
		final java.lang.reflect.Field property = this.getProperty(field.getId());
		
		column.setCellValueFactory(new PropertyValueFactory<T, String>(property.getName()));
		column.setCellFactory(TextFieldTableCell.forTableColumn());
		
		column.setOnEditCommit(
			    new EventHandler<CellEditEvent<Beneficiario, String>>() {
			        @Override
			        public void handle(CellEditEvent<Beneficiario, String> t) {
			        	T row = ((T) t.getTableView().getItems().get(t.getTablePosition().getRow()));
			        	try {
							Method method = type.getDeclaredMethod("set" + StringUtils.capitalize(property.getName()),
									String.class);
							method.invoke(row, t.getNewValue());
						} catch (Exception exception) {
							exception.printStackTrace();
						}
			        }
			    }
			);

		
		
		
//		column.setOnEditCommit(new EventHandler<CellEditEvent<T, String>>() {
//			@Override
//			public void handle(CellEditEvent<T, String> t) {
//				System.out.println("Se edito la celda");
//				T row = ((T) t.getTableView().getItems().get(t.getTablePosition().getRow()));
//				System.out.println(row);
//				try {
//					Method method = type.getDeclaredMethod("set" + StringUtils.capitalize(property.getName()),
//							property.getType());
//					System.out.println(method);
//					method.invoke(row, t.getNewValue());
//				} catch (Exception exception) {
//					exception.printStackTrace();
//				}
//			}
//
//		});
		return column;
	}

	/**
	 * @param field
	 * @return
	 */
	/**
	 * @param field
	 * @return
	 */
	private java.lang.reflect.Field getProperty(String field) {
		java.lang.reflect.Field property = null;
		if (classFields == null) {
			classFields = type.getDeclaredFields();
		}
		
		for (java.lang.reflect.Field cf : classFields) {
			LayoutFieldModel annotation = cf.getDeclaredAnnotation(LayoutFieldModel.class);			
			if (annotation != null && annotation.field().equals(field)) {
				property = cf;
				break;
			}
		}
		return property;
	}
}
