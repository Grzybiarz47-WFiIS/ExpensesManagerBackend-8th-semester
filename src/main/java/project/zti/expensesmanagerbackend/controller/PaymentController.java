package project.zti.expensesmanagerbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.zti.expensesmanagerbackend.model.Payment;

import java.util.List;

@RestController(value = "PaymentController")
@CrossOrigin(origins = "https://expenses-manager-backend.herokuapp.com")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentService;

    @GetMapping(value = "payment/list")
    public @ResponseBody List<Payment> getAllPayments() { return paymentService.getAll(); }

    @GetMapping(value = "payment/{id}")
    public @ResponseBody Payment getOnePayment(@PathVariable int id) { return paymentService.getById(id); }

    @GetMapping(value = "payment/list/range")
    public @ResponseBody List<Payment> getAllPaymentsFromRange(@RequestParam("start") String start,
                                                               @RequestParam("end") String end,
                                                               @RequestParam("category") String category,
                                                               @RequestParam("tag") String tag) {
        return paymentService.getAllFromRange(start, end, category, tag);
    }

    @PostMapping(value = "payment/")
    public void addPayment(@RequestBody Payment newPayment) {
        paymentService.insert(newPayment);
    }

    @PutMapping(value = "payment/{id}")
    public void updatePayment(@RequestBody Payment updatedPayment, @PathVariable int id) { paymentService.update(updatedPayment, id); }

    @DeleteMapping(value= "payment/{id}")
    public void deletePayment(@PathVariable int id) { paymentService.delete(id); }
}
