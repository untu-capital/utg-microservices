package com.untucapital.purchaseorder.dto;


import com.untucapital.purchaseorder.enums.ApprovalStatus;
import com.untucapital.purchaseorder.model.POSSuplierFileUploads;
import lombok.Data;
import lombok.RequiredArgsConstructor;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Data
public class POSSupplierDto implements Serializable {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private String contactPerson;
    private String comment;
    ApprovalStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    List<POSSuplierFileUploads> supplierFiles;
}