package com.untucapital.purchaseorder.service;

import com.untucapital.purchaseorder.dto.req.RequisitionsRequestDTO;
import com.untucapital.purchaseorder.dto.res.PurchaseOrderTransactionsResponseDTO;
import com.untucapital.purchaseorder.dto.res.RequisitionsResponseDTO;
import com.untucapital.purchaseorder.model.Expenditure;
import com.untucapital.purchaseorder.model.Requisitions;
import com.untucapital.purchaseorder.repository.ExpenditureRepository;
import com.untucapital.purchaseorder.repository.RequisitionRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@jakarta.transaction.Transactional
@Slf4j
public class RequisitionService {

    @Autowired
    private RequisitionRepository requisitionRepository;

    @Autowired
    private ExpenditureRepository expenditureRepository;

    @Autowired
    private PurchaseOrderTransactionsService purchaseOrderTransactionsService;

    @Transactional(value = "transactionManager")
    public List<RequisitionsResponseDTO> getAllRequistions() {

        List<RequisitionsResponseDTO> response = new ArrayList<>();
        List<Requisitions> requisitionsList= requisitionRepository.findAll();
        requisitionsList.forEach(requisition -> Hibernate.initialize(requisition.getAttachments()));

        for (Requisitions requisition : requisitionsList) {
            RequisitionsResponseDTO responseDTO = new RequisitionsResponseDTO();
            BeanUtils.copyProperties(requisition, responseDTO);
            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void saveRequisition(RequisitionsRequestDTO request) {

        Requisitions requisitions = new Requisitions();
        Expenditure expenditure = new Expenditure();

        BeanUtils.copyProperties(request, requisitions);

        requisitionRepository.save(requisitions);
    }

    @Transactional(value = "transactionManager")
    public RequisitionsResponseDTO getRequisitionById(String id) {

        RequisitionsResponseDTO requisitionsResponseDTO = new RequisitionsResponseDTO();
        log.info("ID: " + id);
        Optional<Requisitions> requisitions =  requisitionRepository.findById(id);

        log.info("Requisitions:{}", requisitions);

        if(requisitions.isPresent()) {
            Requisitions requisitions1 = requisitions.get();
            log.info("Requisitions1:{}", requisitions1);
            BeanUtils.copyProperties(requisitions1,requisitionsResponseDTO);
            log.info("RequisitionsResponseDTO:{}", requisitionsResponseDTO);

            return requisitionsResponseDTO;
        }else {
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public RequisitionsResponseDTO getRequisitionByPoNumber(String poNumber) {

        RequisitionsResponseDTO response = new RequisitionsResponseDTO();
        Optional<Requisitions> requisitions = requisitionRepository.getRequisitionsByPoNumber(poNumber);

        if(requisitions.isPresent()) {
            Requisitions requisitions1 = requisitions.get();
            BeanUtils.copyProperties(requisitions1,response);

            return response;
        }else {
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public RequisitionsResponseDTO updateRequisition(String poNumber) {

        RequisitionsResponseDTO response = new RequisitionsResponseDTO();
        Optional<Requisitions> requisitions = requisitionRepository.getRequisitionsByPoNumber(poNumber);

        if(requisitions.isPresent()) {
            Requisitions requisitions1 = requisitions.get();
            if(requisitions1.getPoStatus().equals("open")){

                Expenditure expenditure = new Expenditure();

                List<PurchaseOrderTransactionsResponseDTO> purchaseOrderTransactions = purchaseOrderTransactionsService.getPurchaseOrderTransactionsByRequisitionId(requisitions1.getId());

                for(PurchaseOrderTransactionsResponseDTO purchaseOrderTransactionsResponseDTO : purchaseOrderTransactions){

                    expenditure.setCategory(purchaseOrderTransactionsResponseDTO.getPoCategory());
                    expenditure.setAmount(BigDecimal.valueOf(Float.valueOf(purchaseOrderTransactionsResponseDTO.getPoAmount())));
                    expenditure.setMonth(LocalDateTime.now().getMonth().toString());
                    expenditure.setYear(LocalDateTime.now().getYear());
                    expenditureRepository.save(expenditure);
                }

                requisitions1.setPoStatus("closed");
                requisitionRepository.save(requisitions1);

                BeanUtils.copyProperties(requisitions1,response);

            }else{
                BeanUtils.copyProperties(requisitions1,response);
            }

            return response;
        }else {
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public void deleteRequisition(String id) {
        requisitionRepository.deleteById(id);
    }
}
