package project.zti.expensesmanagerbackend.controller;

import project.zti.expensesmanagerbackend.model.Payment;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    List<Payment> getAll();

    Payment getById(Integer paymentId);

    List<Payment> getAllFromRange(String startStr, String endStr, String category, String tag);

    List<Payment> getAllBetweenDates(LocalDate start, LocalDate end, List<Payment> arr);

    List<Payment> getAllFromCategory(String category, List<Payment> arr);

    List<Payment> getAllWithTag(String tag, List<Payment> arr);

    void insert(Payment payment);

    void update(Payment payment, Integer paymentId);

    void delete(Integer paymentId);
}
