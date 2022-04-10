package web.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.crud.model.Customer;
import web.crud.repository.CustomerRepository;
import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    private CustomerRepository customerRepository;

//    ------------------------------
//    /
//    ------------------------------
    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "index";
    }

//    ------------------------------
//    /create
//    ------------------------------
    @GetMapping("/create")
    public String showCreate(Customer customer) {
        return "create";
    }

    @PostMapping("/create")
    public String postCreate(@Valid Customer customer, BindingResult result, Model model) {
        if(result.hasErrors()) return "create";
        customerRepository.save(customer);
        return "redirect:/";
    }

//    ------------------------------
//    /update/{id}
//    ------------------------------
    @GetMapping("/update/{id}")
    public String showUpdate(@PathVariable Long id, Model model) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Record does not exist!"));
        model.addAttribute("customer", customer);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String postUpdate(@PathVariable Long id, @Valid Customer customer, BindingResult result, Model model) {
        if(result.hasErrors()) {
            customer.setId(id);
            return "update";
        }
        customerRepository.save(customer);
        return "redirect:/";
    }

//    ------------------------------
//    /delete/{id}
//    ------------------------------
    @GetMapping("/delete/{id}")
    public String runDelete(@PathVariable Long id, Model model) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Record does not exist!"));
        customerRepository.delete(customer);
        return "redirect:/";
    }

}
