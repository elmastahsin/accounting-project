package com.company.accounting.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class MapperUtil {

    private final ModelMapper modelMapper;


    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public <T> T convert(Object source, Class<T> targetClass) {
       modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(source, (Type) targetClass.getClass());
    }
}
