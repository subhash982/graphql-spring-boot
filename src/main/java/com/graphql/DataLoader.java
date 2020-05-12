/**
 * 
 */
package com.graphql;

import java.io.FileReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.graphql.modal.Employee;
import com.graphql.repository.EmployeeRepository;
import com.opencsv.CSVReader;

/**
 * @author subha
 *
 */
@Component
public class DataLoader implements CommandLineRunner{
	
	private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Value("classpath:employee.csv")
	Resource resource;
	
	@Override
	public void run(String... args) throws Exception {
		
		//Load Employee Data
		 try(CSVReader reader= new CSVReader(new FileReader(resource.getFile()))) {
			 	String[] line;
			 	Employee employee=null;
	            while ((line = reader.readNext()) != null) {
	            	employee = new Employee(line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7], line[8]);
	            	employeeRepository.save(employee);
	            	logger.info("Saving the emolpye "+ employee);
	            }
		} catch (Exception e) {
			logger.error("Error in pasrsing and saving employee" , e);
		}
		
	}

}
