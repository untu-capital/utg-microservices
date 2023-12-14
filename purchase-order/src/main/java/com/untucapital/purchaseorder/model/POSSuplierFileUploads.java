package com.untucapital.purchaseorder.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class POSSuplierFileUploads extends AbstractEntity {

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_description")
    private String fileDescription;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private POSSupplier supplier;
}
