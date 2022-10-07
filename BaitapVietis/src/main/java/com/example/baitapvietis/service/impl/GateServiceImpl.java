package com.example.baitapvietis.service.impl;

import com.example.baitapvietis.model.entity.GateEntity;
import com.example.baitapvietis.repository.GateRepository;
import com.example.baitapvietis.service.GateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GateServiceImpl implements GateService {

    private final GateRepository gateRepository;

    public GateServiceImpl(GateRepository gateRepository){
        this.gateRepository = gateRepository;
    }

    @Override
    public List<GateEntity> getAll(){
        return gateRepository.findAll();
    }

    @Override
    public GateEntity create(GateEntity gateEntity){
        return gateRepository.save(gateEntity);
    }

    @Override
    public boolean delete(Long id){
        Optional<GateEntity> findByID = gateRepository.findById(id);
        if(!findByID.isPresent()) return false;

        GateEntity gateEntity = findByID.get();
        gateRepository.deleteById(gateEntity.getId());

        return true;
    }



}
