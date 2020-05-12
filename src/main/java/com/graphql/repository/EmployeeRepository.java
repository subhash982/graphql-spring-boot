/**
 * 
 */
package com.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graphql.modal.Employee;

/**
 * @author subha
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
