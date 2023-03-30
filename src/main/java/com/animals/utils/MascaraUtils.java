package com.animals.utils;

public class MascaraUtils {

    public static String removeMascaraCpf(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

}
