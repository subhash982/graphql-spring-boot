/**
 * 
 */
package com.graphql.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.graphql.service.datafetcher.AllEmployeesDataFetcher;
import com.graphql.service.datafetcher.EmployeeDataFetcher;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

/**
 * @author subha
 *
 */
@Service
public class GraphQLService {

	@Value("classpath:employees.graphql")
	Resource resource;

	@Autowired
	private AllEmployeesDataFetcher allEmployeesDataFetcher;

	@Autowired
	private EmployeeDataFetcher employeeDataFetcher;

	private GraphQL graphql;

	@PostConstruct
	private void loadSchema() throws IOException {
		File schemaFile = resource.getFile();

		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRuntimeWiring();

		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);

		graphql = GraphQL.newGraphQL(schema).build();
	}

	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring().type("Query", typeWiring -> typeWiring
				.dataFetcher("allEmployees", allEmployeesDataFetcher).dataFetcher("employee", employeeDataFetcher)).build();
	}

	public ExecutionResult executeQuery(String requestString) {
		return graphql.execute(requestString);
	}
	

	
}
