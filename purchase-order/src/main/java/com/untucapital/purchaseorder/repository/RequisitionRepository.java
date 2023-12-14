package com.untucapital.purchaseorder.repository;

import com.untucapital.purchaseorder.model.Requisitions;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RequisitionRepository extends JpaRepository<Requisitions, String> {

    Optional<Requisitions> getRequisitionsByPoNumber(String poNumber);

    // Example query method using the "Requisitions.approvers" entity graph
    @EntityGraph(value = "Requisitions.approvers", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Requisitions r WHERE r.poStatus = :poStatus")
    List<Requisitions> findByPoStatusWithApprovers(@Param("poStatus") String poStatus);

    // Example query method using the "Requisitions.attachments" entity graph
    @EntityGraph(value = "Requisitions.attachments", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Requisitions r WHERE r.userId = :userId")
    List<Requisitions> findByUserIdWithAttachments(@Param("userId") String userId);

    @EntityGraph(value = "Requisitions.withApproversAndAttachments", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Requisitions r")
    List<Requisitions> findAllWithApproversAndAttachments();

    @EntityGraph(attributePaths = {"approvers", "attachments"})
    Optional<Requisitions> findById(String id);

}
