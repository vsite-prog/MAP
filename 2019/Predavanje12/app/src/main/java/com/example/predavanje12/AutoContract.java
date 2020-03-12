package com.example.predavanje12;

import android.provider.BaseColumns;

public final class AutoContract {
    private AutoContract(){} // Ova klasa je spremnik
    public static  final class Auto implements BaseColumns {
        public static final String IME_TABLICE = "Automobil";
        public static final String STUPAC_MARKA = "marka";
        public static final String STUPAC_MODEL = "model";
    }
}