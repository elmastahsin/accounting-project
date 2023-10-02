package com.company.accounting.service;

        import com.company.accounting.dto.ClientVendorDTO;
        import com.company.accounting.dto.InvoiceDTO;
        import com.company.accounting.enums.InvoiceType;
        import com.company.accounting.service.common.CrudService;

        import java.util.List;

public interface InvoiceService extends CrudService<InvoiceDTO, Long> {
    List<InvoiceDTO> findPurchaseInvoices();
    List<InvoiceDTO> findSaleInvoices();
    List<ClientVendorDTO> findVendors();
    List<ClientVendorDTO> findClients();
    String generateInvoiceNo(InvoiceType invoiceType);
    InvoiceDTO getNewInvoice(InvoiceType invoiceType);
    void save(InvoiceDTO InvoiceDTO, InvoiceType invoiceType);
    void approve(Long invoiceId);

}
