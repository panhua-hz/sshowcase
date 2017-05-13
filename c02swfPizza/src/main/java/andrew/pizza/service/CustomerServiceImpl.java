package andrew.pizza.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import andrew.pizza.domain.Customer;
import andrew.pizza.domain.CustomerNotFoundException;

@Repository
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private JdbcOperations jdbc;
	

	@Override
	public Customer lookupByPhoneNum(String phoneNumber) throws CustomerNotFoundException {
		List<Customer> list = jdbc.query("select * from CUSTOMER where phoneNumber=?", new CustomerRowMapper(), phoneNumber);
        if (list==null || list.isEmpty()){
            throw new CustomerNotFoundException("Unregistered phoneNumber: "+phoneNumber);
        }
        return list.get(0);
	}

	@Override
	public Customer addCustomer(Customer customer) {
		jdbc.update("insert into CUSTOMER(name, address, city, state, zipCode, phoneNumber) values(?,?,?,?,?,?)", 
				customer.getName(), customer.getAddress(), customer.getCity(), 
				customer.getState(), customer.getZipCode(), customer.getPhoneNumber());
		return customer;
	}
	
	private static class CustomerRowMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Customer(
            		new Integer(rs.getInt("id")), 
            		rs.getString("name"), 
            		rs.getString("address"),
            		rs.getString("city"),
            		rs.getString("state"),
            		rs.getString("zipCode"),
            		rs.getString("phoneNumber"));
        }

    }

}
