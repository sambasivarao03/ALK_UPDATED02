package com.alk.service.util;

import com.alk.dto.CommonPersonDto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public final class HashUtil {

    private HashUtil(){}

    // compute SHA-256 hex of forename|lastname|dob(ISO)|gender (normalizing to lowercase trimmed)
    public static String computePiiPk(CommonPersonDto p) {
        if (p == null) throw new IllegalArgumentException("person is required");
        if (p.getDob() == null) throw new IllegalArgumentException("person.dob is required");
        String forename = safe(p.getForename());
        String lastName = safe(p.getLastName());
        String dob = p.getDob().toString();
        String gender = safe(p.getGender());
        String raw = forename + "|" + lastName + "|" + dob + "|" + gender;
        return sha256Hex(raw);
    }

    private static String safe(String s) {
        return s == null ? "" : s.trim().toLowerCase();
    }

    private static String sha256Hex(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] d = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : d) sb.append(String.format("%02x", b & 0xff));
            return sb.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Failed to hash", ex);
        }
    }
}
