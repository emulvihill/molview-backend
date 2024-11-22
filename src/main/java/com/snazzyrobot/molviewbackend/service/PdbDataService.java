package com.snazzyrobot.molviewbackend.service;

import com.snazzyrobot.molviewbackend.entity.PdbData;
import com.snazzyrobot.molviewbackend.repository.PdbDataRepository;
import com.snazzyrobot.molviewbackend.utility.HashUtil;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PdbDataService {

    @Autowired
    private PdbDataRepository pdbDataRepository;

    public List<PdbData> getAllPdbData() {
        return pdbDataRepository.findAll();
    }

    public Optional<PdbData> getPdbDataById(Long id) {
        return pdbDataRepository.findById(id);
    }

    public PdbData createPdbData(@NonNull String name, @NonNull String data, String compound) {

        PdbData pdbData = new PdbData();
        pdbData.setName(name);
        pdbData.setData(data);
        pdbData.setCompound(compound);
        pdbData.setSha256(HashUtil.calculateSHA256(data));
        return pdbDataRepository.save(pdbData);
    }

    public Optional<PdbData> deletePdbData(long id) {
        if (!pdbDataRepository.existsById(id)) {
            return Optional.empty();
        }

        return pdbDataRepository.findById(id)
                .map(pdbData -> {
                    pdbDataRepository.deleteById(id);
                    return pdbData;
                });
    }
}