package com.graphql.service.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphql.modal.Employee;
import com.graphql.repository.EmployeeRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllEmployeesDataFetcher implements DataFetcher<List<Employee>>{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> get(DataFetchingEnvironment environment) {
		
		return employeeRepository.findAll();
	}

}
