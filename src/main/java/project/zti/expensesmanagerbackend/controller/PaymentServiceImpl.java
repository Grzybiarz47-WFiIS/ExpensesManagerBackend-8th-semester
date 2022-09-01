package project.zti.expensesmanagerbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zti.expensesmanagerbackend.model.Payment;
import project.zti.expensesmanagerbackend.model.PaymentDaoImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    PaymentDaoImpl dao;

    @Override
    public List<Payment> getAll() {
        return dao.getAllPayments();
    }

    @Override
    public Payment getById(Integer paymentId) {
        List<Payment> arr = dao.getAllPayments();
        Payment payment = arr.stream().
                filter(p -> Objects.equals(p.getPaymentID(), paymentId)).findAny().orElse(null);
        return payment;
    }

    @Override
    public List<Payment> getAllFromRange(String startStr, String endStr, String category, String tag) {
        List<Payment> payments = dao.getAllPayments();
        LocalDate start, end;
        if(!startStr.isEmpty()) {
            start = LocalDate.parse(startStr);
        }
        else {
            start = LocalDate.parse("1900-01-01");
        }
        if(!endStr.isEmpty()) {
            end = LocalDate.parse(endStr);
        }
        else {
            end = LocalDate.parse("2200-01-01");
        }

        //take payments between dates if dates are correct
        if (end.isAfter(start)) {
            payments = getAllBetweenDates(start, end, payments);
        }

        //take payments from category if given
        if(!category.isEmpty()) {
            payments = getAllFromCategory(category, payments);
        }

        //take payments with tag if given
        if(!tag.isEmpty()) {
            payments = getAllWithTag(tag, payments);
        }

        return payments;
    }

    @Override
    public List<Payment> getAllBetweenDates(LocalDate start, LocalDate end, List<Payment> arr) {
        List<Payment> resultList = arr.stream().
                filter(p -> p.getDate().isAfter(start) && p.getDate().isBefore(end)).collect(Collectors.toList());
        return resultList;
    }

    @Override
    public List<Payment> getAllFromCategory(String category, List<Payment> arr) {
        List<Payment> resultList = arr.stream().
                filter(p -> Objects.equals(p.getCategory(), category)).collect(Collectors.toList());
        return resultList;
    }

    @Override
    public List<Payment> getAllWithTag(String tag, List<Payment> arr) {
        List<Payment> resultList = new ArrayList<>();
        for(Payment p : arr) {
            List<String> tags = p.getTags();
            if(tags.contains(tag)) {
                resultList.add(p);
            }
        }
        return resultList;
    }

    @Override
    public void insert(Payment payment) { dao.insertPayment(payment); }

    @Override
    public void update(Payment payment, Integer paymentId) { dao.updatePayment(payment, paymentId); }

    @Override
    public void delete(Integer paymentId){
        dao.deletePayment(paymentId);
    }
}
