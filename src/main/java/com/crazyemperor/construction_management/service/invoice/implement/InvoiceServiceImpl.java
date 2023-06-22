package com.crazyemperor.construction_management.service.invoice.implement;

import com.crazyemperor.construction_management.entity.Invoice;
import com.crazyemperor.construction_management.repository.InvoiceRepository;
import com.crazyemperor.construction_management.service.invoice.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;


    @Override
    @Transactional
    public List<Invoice> getUnpaid(long id) {
        return invoiceRepository.findAllUnpaid(id);
    }
}
