package com.example.baitapvietis.service.impl;

import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.entity.GateEntity;
import com.example.baitapvietis.model.entity.WasehouseEntity;
import com.example.baitapvietis.repository.GateRepository;
import com.example.baitapvietis.repository.WasehouseRepository;
import com.example.baitapvietis.service.Gateservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GateServiceImpl implements Gateservice{

    private final GateRepository gateRepository;
    private final WasehouseRepository wasehouseRepository;

    @Autowired
    public GateServiceImpl(GateRepository gateRepository,
            WasehouseRepository wasehouseRepository
    ){
        this.gateRepository = gateRepository;
        this.wasehouseRepository = wasehouseRepository;
    }


    @Override
    public List<GateEntity> getAll(){
        return gateRepository.findAll();
    }

    @Override
    public GateEntity create(GateEntity gateEntity){
        Optional<WasehouseEntity> wasehouse = Optional.ofNullable(wasehouseRepository.findById(gateEntity.getWasehouseId()).
                orElseThrow(() -> new NotFoundException("Wasehouse id not found" + gateEntity.getWasehouseId())));

        return gateRepository.save(gateEntity);
    }

    @Override
    public GateEntity update(Long id, GateEntity gateEntity){
        Optional<GateEntity> findById = Optional.ofNullable(gateRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Gate id not found" + id)));
        GateEntity gate = findById.get();

        return gateRepository.save(gate);
    }

    @Override
    public void delete(Long id){
        Optional<GateEntity> findById = Optional.ofNullable(gateRepository.findById(id).
                orElseThrow(() -> new NotFoundException("gate id not found" + id)));
        GateEntity gate = findById.get();

        gateRepository.deleteById(gate.getId());
    }


}
