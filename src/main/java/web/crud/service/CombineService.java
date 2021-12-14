package web.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import web.crud.model.Customer;
import web.crud.repository.CustomerRepository;

@Service
public class CombineService {

    @Autowired
    private CustomerRepository customerRepository;

//    ------------------------------
//    /
//    ------------------------------
    public String combineShowIndex(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "index";
    }

    public String combinePostCreate(Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) return "create";
        customerRepository.save(customer);
        return "redirect:/";
    }

//    ------------------------------
//    /update/{id}
//    ------------------------------
    public String combineShowUpdate(Long id, Model model) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Record does not exist!"));
        model.addAttribute("customer", customer);
        return "update";
    }

    public String combinePostUpdate(Long id, Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            customer.setId(id);
            return "update";
        }
        customerRepository.save(customer);
        return "redirect:/";
    }

//    ------------------------------
//    /delete/{id}
//    ------------------------------
    public String combineRunDelete(Long id, Model model) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Record does not exist!"));
        customerRepository.delete(customer);
        return "redirect:/";
    }

}
