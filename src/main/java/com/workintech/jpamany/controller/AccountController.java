package com.workintech.jpamany.controller;

import com.workintech.jpamany.entity.Account;
import com.workintech.jpamany.entity.Customer;
import com.workintech.jpamany.service.AccountService;
import com.workintech.jpamany.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private AccountService accountService;
    private CustomerService customerService;
    @Autowired
    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @PostMapping("/{customerId}")
    public Account save(@RequestBody Account account, @PathVariable int customerId){
        Customer customer =customerService.find(customerId);
        customer.add(account);
        account.setCustomer(customer);
        return accountService.save(account);

    }
}
