package project.zti.expensesmanagerbackend.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.*;

@Repository
public class PaymentDaoImpl extends JdbcDaoSupport implements PaymentDao{

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<Payment>();
        String query = "select paymentid, name, amount, currency, date, category, tags from Payments";
        List<Map<String, Object>> paymentsRowData = getJdbcTemplate().queryForList(query);

        for (Map<String, Object> paymentData : paymentsRowData) {
            Payment payment = new Payment();
            payment.setPaymentID(Integer.parseInt(String.valueOf(paymentData.get("PaymentID"))));
            payment.setName(String.valueOf(paymentData.get("Name")));
            payment.setAmount(Double.parseDouble(String.valueOf(paymentData.get("Amount"))));
            payment.setCurrency(String.valueOf(paymentData.get("Currency")));
            payment.setDate(LocalDate.parse(String.valueOf(paymentData.get("Date"))));
            payment.setCategory(String.valueOf(paymentData.get("Category")));

            String tags = String.valueOf(paymentData.get("Tags"));
            ArrayList<String> arr = new ArrayList<>();
            Collections.addAll(arr, tags.split(","));
            payment.setTags(arr);

            payments.add(payment);
        }
        return payments;
    }

    @Override
    public void insertPayment(Payment payment) {
        Integer index = getJdbcTemplate().queryForObject("select max(paymentid) + 1 from Payments", Integer.class);
        String query = "insert into Payments (paymentid, name, amount, currency, date, category, tags) "
                + "values (?, ?, ?, ?, ?, ?, ?)";
        List<String> arr = payment.getTags();
        String tags = String.join(",", arr);
        getJdbcTemplate().update(query,
                index,
                payment.getName(),
                payment.getAmount(),
                payment.getCurrency(),
                java.sql.Date.valueOf(payment.getDate()),
                payment.getCategory(),
                tags);
    }

    @Override
    public void updatePayment(Payment payment, Integer paymentId) {
        String query = "update Payments "
                + "set name=?, amount=?, currency=?, date=?, category=?, tags=? "
                + "where paymentid=?";
        List<String> arr = payment.getTags();
        String tags = String.join(",", arr);
        getJdbcTemplate().update(query,
                payment.getName(),
                payment.getAmount(),
                payment.getCurrency(),
                java.sql.Date.valueOf(payment.getDate()),
                payment.getCategory(),
                tags,
                paymentId
        );
    }

    @Override
    public void deletePayment(Integer paymentId) {
        String query = "delete from Payments where paymentid=?";
        getJdbcTemplate().update(
                query,
                paymentId
        );
    }
}
