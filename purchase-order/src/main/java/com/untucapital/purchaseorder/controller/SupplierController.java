package com.untucapital.purchaseorder.controller;


import com.untucapital.purchaseorder.dto.POSSupplierDto;
import com.untucapital.purchaseorder.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@RestController
@RequestMapping(value = "pos")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    //Save Supplier
    @PostMapping("/supplier/save")
    @ResponseStatus(HttpStatus.CREATED)
    public POSSupplierDto saveSupplier(@RequestBody POSSupplierDto posSupplier) {
       return supplierService.saveSupplier(posSupplier);
    }

    //Get Supplier By Id
    @GetMapping("/supplier/{id}")
    @ResponseStatus(HttpStatus.OK)
    public POSSupplierDto getSupplierById(@PathVariable Integer id) {
        return supplierService.getSupplierById(id);
    }

    //Get all Suppliers
    @GetMapping("/supplier/all")
    @ResponseStatus(HttpStatus.OK)
    public List<POSSupplierDto> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    //Update Supplier
    @PutMapping("/supplier/update")
    @ResponseStatus(HttpStatus.OK)
    public POSSupplierDto updateSupplier(@RequestBody POSSupplierDto posSupplier) {
        System.out.println(posSupplier.toString());
        return supplierService.updateSupplier(posSupplier);
    }


    //Delete Supplier
    @DeleteMapping("/supplier/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public POSSupplierDto deleteSupplier(@PathVariable Integer id) {

        return supplierService.deleteSupplier(id);
    }
}
