/**
 * 
 */
package com.graphql.service.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphql.modal.Employee;
import com.graphql.repository.EmployeeRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * @author subha
 *
 */
@Component
public class EmployeeDataFetcher implements DataFetcher<Employee>{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee get(DataFetchingEnvironment dataFetchingEnvironment) {
		String isn = dataFetchingEnvironment.getArgument("id");
		return employeeRepository.findById(isn).get();
	}

}
