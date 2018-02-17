package controller;

import dao.CustomerDao;
import model.Customer;
import java.util.ArrayList;

public class CustomerController {

    CustomerDao dao = new CustomerDao();
    public void safe(Customer customer) {
        dao.safe(customer);
    }

    public void remove(long id) {
        dao.remove(id);
    }

    public Customer getById(long id) {
        return dao.getById(id);
    }

    public ArrayList<Customer> getAll() {
        return dao.getAll();
    }

    public ArrayList<Long> getIds(String path) {
        return dao.getIds(path);
    }
}