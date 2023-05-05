package org.primefaces.test;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.filter.FilterConstraint;
import org.primefaces.util.LocaleUtils;

import jakarta.faces.context.FacesContext;

public class LazyCustomerDataModel extends LazyDataModel<Customer> {

    private static final long serialVersionUID = 1L;

    private List<Customer> datasource;

    public LazyCustomerDataModel(List<Customer> datasource) {
        this.datasource = datasource;
    }

    @Override
    public Customer getRowData(String rowKey) {
        for (Customer customer : datasource) {
            if (customer.getId() == Integer.parseInt(rowKey)) {
                return customer;
            }
        }

        return null;
    }

    @Override
    public String getRowKey(Customer customer) {
        return String.valueOf(customer.getId());
    }

    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        return (int) datasource.stream()
                .filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
                .count();
    }

    @Override
    public List<Customer> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        // apply offset & filters
        List<Customer> customers = datasource.stream()
                .skip(offset)
                .filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
                .limit(pageSize)
                .collect(Collectors.toList());

//        // sort
//        if (!sortBy.isEmpty()) {
//            List<Comparator<Customer>> comparators = sortBy.values().stream()
//                    .map(o -> new LazySorter(o.getField(), o.getOrder()))
//                    .collect(Collectors.toList());
//            Comparator<Customer> cp = ComparatorUtils.chainedComparator(comparators); // from apache
//            customers.sort(cp);
//        }
//
        return customers;
    }

    private boolean filter(FacesContext context, Collection<FilterMeta> filterBy, Object clazz) 
    {
        boolean matching = true;

        for (FilterMeta filter: filterBy) 
        {
            FilterConstraint constraint = filter.getConstraint();
            Object filterValue = filter.getFilterValue();

       	 	String fieldFilterBy = filter.getField();
       	 	
   	 		Object fieldValue = getPrivateField(fieldFilterBy, clazz);
   	 		if (fieldValue == null) {
   	 			fieldValue = getInheritedPrivateField(fieldFilterBy, clazz);
   	 			if (fieldValue == null) {
   	 				continue;
   	 			}
   	 		}
   	 	
            Object columnValue = String.valueOf(fieldValue);
            matching = constraint.isMatching(context, columnValue, filterValue, LocaleUtils.getCurrentLocale());
            if (!matching) {
                break;
            }
        }
        return matching;
    }

    private Object getPrivateField(String fieldFilterBy, Object clazz)
    {
   	 	try {
   	 		// Get && access private field
   	 		Field fieldProperty = clazz.getClass().getDeclaredField(fieldFilterBy);
   	 		fieldProperty.setAccessible(true);
   	 		Object fieldValue = fieldProperty.get(clazz);
   	 		return fieldValue;
        } catch (ReflectiveOperationException e) {
        	e.getStackTrace();
        }
   	 	return null;
    }

    private Object getInheritedPrivateField(String fieldFilterBy, Object clazz)
    {
   	 	try {
   	 		// Get && access to inherited private field from superclass
   	 		Field fieldProperty = clazz.getClass().getSuperclass().getDeclaredField(fieldFilterBy);
   	 		fieldProperty.setAccessible(true);
   	 		Object fieldValue = fieldProperty.get(clazz);
   	 		return fieldValue;
        } catch (ReflectiveOperationException e) {
        	e.getStackTrace();
        }
   	 	return null;
    }

}
