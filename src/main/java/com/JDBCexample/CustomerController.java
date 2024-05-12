package com.JDBCexample;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    //requests
    //Get mapping
    @GetMapping("/all")
    public List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id){
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public void addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id){
        customerService.deleteCustomer(id);
    }

    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable int id,
                               @RequestBody Customer customer){
        customer.setId(id);
        customerService.updateCustomer(customer);
    }

}
