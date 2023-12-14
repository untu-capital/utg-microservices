package com.untucapital.purchaseorder.repository;



import com.untucapital.purchaseorder.model.PurchaseOrderTransactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderTransactionsRepository extends JpaRepository<PurchaseOrderTransactions, String> {
    
    Optional<List<PurchaseOrderTransactions>> getPurchaseOrderTransactionsByPoRequisitionId(String id);

    boolean existsByPoItemAndPoSupplierAndPoCategoryAndPoQuantityAndPoAmountAndPoRequisitionId(
            String poItem, String poSupplier, String poCategory, String poQuantity, String poAmount, String poRequisitionId);
}
