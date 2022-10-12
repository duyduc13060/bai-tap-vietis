package com.example.baitapvietis.service.impl;

import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.entity.GatesEntity;
import com.example.baitapvietis.model.entity.WasehouseEntity;
import com.example.baitapvietis.repository.GateRepository;
import com.example.baitapvietis.repository.WasehouseRepository;
import com.example.baitapvietis.service.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GateServiceImpl implements GateService {

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
    public List<GatesEntity> getAll(){
        return gateRepository.findAll();
    }

    @Override
    public Optional<GatesEntity> findById(Long id){
        return gateRepository.findById(id);
    }

    @Override
    public GatesEntity create(GatesEntity gateEntity){
        Optional<WasehouseEntity> wasehouse = Optional.ofNullable(wasehouseRepository.findById(gateEntity.getWasehouseId()).
                orElseThrow(() -> new NotFoundException("Wasehouse id not found" + gateEntity.getWasehouseId())));

        WasehouseEntity wasehouseEntity = wasehouse.get();
        gateEntity.setWasehouseId(wasehouseEntity.getId());
        return gateRepository.save(gateEntity);
    }

    @Override
    public GatesEntity update(Long id, GatesEntity gateEntity){
        Optional<GatesEntity> findById = Optional.ofNullable(gateRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Gate id not found" + id)));
        gateEntity = findById.get();

        return gateRepository.save(gateEntity);
    }

    @Override
    public void delete(Long id){
        Optional<GatesEntity> findById = Optional.ofNullable(gateRepository.findById(id).
                orElseThrow(() -> new NotFoundException("gate id not found" + id)));
        GatesEntity gate = findById.get();

        gateRepository.deleteById(gate.getId());
    }


}
