/**
 * 
 */
package com.reignite.messaging.proxy;

import java.util.Collection;
import java.util.Map;

/**
 * Delegates conversion to type specific editors.
 * 
 * @author Surrey
 * 
 */
public class DelegatingTypeEditor implements TypeEditor {

	private TypeEditor numberEditor = new NumberAwareEditor();
	private TypeEditor collectionEditor = new CollectionAwareEditor();
	private TypeEditor mapEditor = new MapAwareEditor();

	/**
	 * @see com.reignite.messaging.proxy.TypeEditor#convert(java.lang.Object,
	 *      java.lang.Class)
	 */
	@Override
	public Object convert(Object value, Class<?> requiredType) {
		if (value != null) {
			if (value instanceof Number) {
				return numberEditor.convert(value, requiredType);
			}
			if (Collection.class.isAssignableFrom(requiredType)) {
				return collectionEditor.convert(value, requiredType);
			}
			if (Map.class.isAssignableFrom(requiredType)){
				return mapEditor.convert(value, requiredType);
			}
		}
		return value;
	}

}
