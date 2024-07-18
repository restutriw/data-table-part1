package com.project.simple_crud_bulk_api.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.simple_crud_bulk_api.Models.Karyawan;
import com.project.simple_crud_bulk_api.Repositories.KaryawanRepo;

import java.util.List;
import java.util.Optional;

@Service
public class KaryawanService {

    @Autowired
    private KaryawanRepo karyawanRepo;

    public List<Karyawan> getAllKaryawan() {
        return karyawanRepo.findAll();
    }

    public Optional<Karyawan> getKaryawanById(Long id) {
        return karyawanRepo.findById(id);
    }

    public Karyawan createKaryawan(Karyawan karyawan) {
        return karyawanRepo.save(karyawan);
    }

    public Karyawan updateKaryawan(Long id, Karyawan karyawanDetails) {
        Karyawan karyawan = karyawanRepo.findById(id).orElseThrow();
        karyawan.setNama(karyawanDetails.getNama());
        karyawan.setRole(karyawanDetails.getRole());
        karyawan.setStatus_pekerjaan(karyawanDetails.getStatus_pekerjaan());
        return karyawanRepo.save(karyawan);
    }

    public void deleteKaryawan(Long id) {
        karyawanRepo.deleteById(id);
    }
}
