package com.untucapital.purchaseorder.model;

import com.untucapital.purchaseorder.enums.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class POSSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "comment")
    private String comment;

    @Column(name = "status")
    @Enumerated
    private ApprovalStatus status;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<POSSuplierFileUploads> supplierFiles;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
