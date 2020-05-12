/**
 * 
 */
package com.graphql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graphql.service.GraphQLService;

import graphql.ExecutionResult;

/**
 * @author subha
 *
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private GraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> retrieveAllEmployees(@RequestBody String query) {
		ExecutionResult execute= graphQLService.executeQuery(query);
		return new ResponseEntity<Object>(execute , HttpStatus.OK);
	}


}
