package com.project.simple_crud_bulk_api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.simple_crud_bulk_api.Models.Karyawan;
import com.project.simple_crud_bulk_api.Services.KaryawanService;

import lombok.Data;

import java.util.List;

@RestController
@RequestMapping("/api/karyawan")
public class BulkingController {

    @Autowired
    private KaryawanService karyawanService;

    @PostMapping("/bulk-crud")
    public ResponseEntity<?> batchOperation(@RequestBody BulkCRUDRequest bulkCRUDRequest) {
        // Create Data Karyawan
        if (bulkCRUDRequest.getCreates() != null) {
            bulkCRUDRequest.getCreates().forEach(karyawanService::createKaryawan);
        }

        // Update 
        if (bulkCRUDRequest.getUpdates() != null) {
            bulkCRUDRequest.getUpdates().forEach(updateRequest -> 
                karyawanService.updateKaryawan(updateRequest.getId(), updateRequest.getKaryawan())
            );
        }

        // Delete 
        if (bulkCRUDRequest.getDeletes() != null) {
            bulkCRUDRequest.getDeletes().forEach(karyawanService::deleteKaryawan);
        }

        return ResponseEntity.status(HttpStatus.OK).body("Create, update, dan delete data karyawan berhasil dilakukan.");
    }

    @Data
    public static class BulkCRUDRequest {
        private List<Karyawan> creates;
        private List<UpdateRequest> updates;
        private List<Long> deletes;

        @Data
        public static class UpdateRequest {
            private Long id;
            private Karyawan karyawan;
        }
    }
}

