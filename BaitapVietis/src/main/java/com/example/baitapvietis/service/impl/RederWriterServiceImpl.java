package com.example.baitapvietis.service.impl;

import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.entity.ReaderWriterEntity;
import com.example.baitapvietis.repository.ReaderWriterRepository;
import com.example.baitapvietis.service.ReaderWriterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<ReaderWriterEntity> findById(Long id){
        return readerWriterRepository.findById(id);
    }

    @Override
    public ReaderWriterEntity create(ReaderWriterEntity readerWriterEntity){
        return readerWriterRepository.save(readerWriterEntity);
    }

    @Override
    public ReaderWriterEntity update(Long id, ReaderWriterEntity readerWriterEntity){
        Optional<ReaderWriterEntity> readerWriter = Optional.ofNullable(readerWriterRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Reader id not found" + id)));

        return readerWriterRepository.save(readerWriterEntity);
    }

    @Override
    public void delete(Long id){
        Optional<ReaderWriterEntity> findById = Optional.ofNullable(readerWriterRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Reader id not found" + id)));

        ReaderWriterEntity readerWriter = findById.get();
        readerWriterRepository.deleteById(readerWriter.getId());
    }


}
