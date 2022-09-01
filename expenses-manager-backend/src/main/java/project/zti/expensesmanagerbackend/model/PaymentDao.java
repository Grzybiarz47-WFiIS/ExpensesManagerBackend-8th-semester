package project.zti.expensesmanagerbackend.model;

import java.util.List;

public interface PaymentDao {
    public List<Payment> getAllPayments();

    void insertPayment(Payment payment);

    void updatePayment(Payment payment, Integer paymentId);

    void deletePayment(Integer paymentId);
}
