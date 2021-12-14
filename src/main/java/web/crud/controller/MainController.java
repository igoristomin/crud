package web.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.crud.model.Customer;
import web.crud.service.CombineService;
import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    private CombineService combineService;

//    ------------------------------
//    /
//    ------------------------------
    @GetMapping("/")
    public String showIndex(Model model) {
        return combineService.combineShowIndex(model);
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
        return combineService.combinePostCreate(customer, result, model);
    }

//    ------------------------------
//    /update/{id}
//    ------------------------------
    @GetMapping("/update/{id}")
    public String showUpdate(@PathVariable Long id, Model model) {
        return combineService.combineShowUpdate(id, model);
    }

    @PostMapping("/update/{id}")
    public String postUpdate(@PathVariable Long id, @Valid Customer customer, BindingResult result, Model model) {
        return combineService.combinePostUpdate(id, customer, result, model);
    }

//    ------------------------------
//    /delete/{id}
//    ------------------------------
    @GetMapping("/delete/{id}")
    public String runDelete(@PathVariable Long id, Model model) {
        return combineService.combineRunDelete(id, model);
    }

}
