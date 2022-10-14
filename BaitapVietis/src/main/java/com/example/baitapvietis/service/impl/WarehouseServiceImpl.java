package com.example.baitapvietis.service.impl;

import com.example.baitapvietis.model.entity.WarehouseEntity;
import com.example.baitapvietis.repository.WarehouseRepository;
import com.example.baitapvietis.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository){
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<WarehouseEntity> getAll(){
        return warehouseRepository.findAll();
    }

    @Override
    public Optional<WarehouseEntity> findById(Long id){
        return warehouseRepository.findById(id);
    }

    @Override
    public WarehouseEntity createWasehouse(WarehouseEntity warehouseEntity){
        return warehouseRepository.save(warehouseEntity);
    }


    @Override
    public WarehouseEntity updateWasehouse(Long id, WarehouseEntity warehouseEntity){
        Optional<WarehouseEntity> findById = warehouseRepository.findById(id);
        if(!findById.isPresent()) return null;

        return warehouseRepository.save(warehouseEntity);
    }

    @Override
    public boolean deleteWasehouse(Long id){
        Optional<WarehouseEntity> findById = warehouseRepository.findById(id);
        if(!findById.isPresent()) return false;

        WarehouseEntity wasehouse = findById.get();
        warehouseRepository.deleteById(wasehouse.getId());

        return true;
    }





}
