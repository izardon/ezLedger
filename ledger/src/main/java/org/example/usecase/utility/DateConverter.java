package org.example.usecase.utility;

import java.time.LocalDate;
import java.time.chrono.MinguoChronology;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    public String rocToAd(String rocDate) {
        int adYear = Integer.parseInt(rocDate.substring(0, 3)) + 1911;
        return adYear + rocDate.substring(3);
    }

    public String adToRoc(String adDate) {
        int rocYear = Integer.parseInt(adDate.substring(0, 4)) - 1911;
        return rocYear + adDate.substring(4);
    }
}
