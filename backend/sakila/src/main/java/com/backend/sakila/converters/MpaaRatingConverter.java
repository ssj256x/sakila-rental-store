package com.backend.sakila.converters;

import com.backend.sakila.model.entity.MpaaRating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MpaaRatingConverter implements AttributeConverter<MpaaRating, String> {
    @Override
    public String convertToDatabaseColumn(MpaaRating attribute) {
        return attribute == null ? null : attribute.name().replace("_", "-");
    }

    @Override
    public MpaaRating convertToEntityAttribute(String dbData) {
        return dbData == null ? null : MpaaRating.valueOf(dbData.replace("-", "_"));
    }
}
