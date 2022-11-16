package org.epam.learn.converter;

import org.epam.learn.model.Signal;
import org.epam.learn.model.SignalRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppMapperr {
    Signal convert(SignalRequest request);
}
