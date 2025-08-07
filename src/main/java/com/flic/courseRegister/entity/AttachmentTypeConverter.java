package com.flic.courseRegister.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AttachmentTypeConverter implements AttributeConverter<AttachmentType, String> {

    @Override
    public String convertToDatabaseColumn(AttachmentType attribute) {
        if (attribute == null) return null;

        return switch (attribute) {
            case _4x6_photo -> "4x6_photo";
            case _3x4_photo -> "3x4_photo";
            case birth_certificate -> "birth_certificate";
        };
    }

    @Override
    public AttachmentType convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        return switch (dbData) {
            case "4x6_photo" -> AttachmentType._4x6_photo;
            case "3x4_photo" -> AttachmentType._3x4_photo;
            case "birth_certificate" -> AttachmentType.birth_certificate;
            default -> throw new IllegalArgumentException("Unknown value for AttachmentType: " + dbData);
        };
    }
}
