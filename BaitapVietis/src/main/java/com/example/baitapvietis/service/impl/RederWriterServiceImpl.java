package com.example.baitapvietis.service.impl;

import com.example.baitapvietis.model.entity.ReaderWriterEntity;
import com.example.baitapvietis.repository.ReaderWriterRepository;
import com.example.baitapvietis.service.ReaderWriterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RederWriterServiceImpl implements ReaderWriterService {

    private final ReaderWriterRepository readerWriterRepository;

    public RederWriterServiceImpl(
            ReaderWriterRepository readerWriterRepository
    ){
        this.readerWriterRepository = readerWriterRepository;
    }

    @Override
    public List<ReaderWriterEntity> getAll(){
        return readerWriterRepository.findAll();
    }


}
