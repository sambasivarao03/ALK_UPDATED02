package com.alk.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EncryptionConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String rawValue) {
        if (rawValue == null) return null;
        return encrypt(rawValue);
    }

    @Override
    public String convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;
        return decrypt(dbValue);
    }

    private String encrypt(String value) {
        // TODO: your encryption logic
        return value; // temp (no-op)
    }

    private String decrypt(String value) {
        // TODO: your decryption logic
        return value; // temp (no-op)
    }
}
