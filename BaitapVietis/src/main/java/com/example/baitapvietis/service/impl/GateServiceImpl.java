package com.example.baitapvietis.service.impl;

import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.entity.GatesEntity;
import com.example.baitapvietis.model.entity.WarehouseEntity;
import com.example.baitapvietis.repository.GateRepository;
import com.example.baitapvietis.repository.WarehouseRepository;
import com.example.baitapvietis.service.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GateServiceImpl implements GateService {

    private final GateRepository gateRepository;
    private final WarehouseRepository warehouseRepository;

    @Autowired
    public GateServiceImpl(GateRepository gateRepository,
            WarehouseRepository warehouseRepository
    ){
        this.gateRepository = gateRepository;
        this.warehouseRepository = warehouseRepository;
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
        Optional<WarehouseEntity> wasehouse = Optional.ofNullable(warehouseRepository.findById(gateEntity.getWasehouseId()).
                orElseThrow(() -> new NotFoundException("Wasehouse id not found" + gateEntity.getWasehouseId())));

        WarehouseEntity warehouseEntity = wasehouse.get();
        gateEntity.setWasehouseId(warehouseEntity.getId());
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
