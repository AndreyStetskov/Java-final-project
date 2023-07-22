package com.crazyemperor.construction_management.service.invoice.implement;

import com.crazyemperor.construction_management.auxillirary.exeption.NoDataFoundException;
import com.crazyemperor.construction_management.entity.Invoice;
import com.crazyemperor.construction_management.repository.InvoiceRepository;
import com.crazyemperor.construction_management.service.invoice.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;


    @Override
    @Transactional
    public List<Invoice> getUnpaid(long id) {
        Optional<List<Invoice>> invoices = Optional.ofNullable(invoiceRepository.findAllUnpaid(id));

        return invoices
                .orElseThrow(() -> {
                    new NoDataFoundException("No one unpaid invoice found");
                    return null;
                });
    }

    @Override
    @Transactional
    public List<Invoice> selectedForPay(long id) {
        List<Invoice> invoices = getUnpaid(id);

        return invoices;
    }

    @Override
    @Transactional
    public BigDecimal paymentAmount(long id) {
        List<Invoice> invoices = selectedForPay(id);

        BigDecimal sum = BigDecimal.ZERO;
        if (invoices.isEmpty()) return sum;

        for (Invoice invoice : invoices) {
            BigDecimal result = sum.add(invoice.getAmount());
            sum = result;
        }
        return sum;
    }
}
