package data;

import java.time.LocalDateTime;
import javax.swing.JComboBox;

public class SetupDate {
    public static boolean load;
    
    public static String num = "0";
    
    public static void getYear(JComboBox<String> cbMonth, JComboBox<String> cbYear) {
        cbMonth.removeAllItems();
        cbYear.removeAllItems();
        for (int i = LocalDateTime.now().getYear(); i >= 1900; i--) {
            cbYear.addItem(i < 10 ? (num + i) : "" + i);
        }
        for (int i = 1; i <= 12; i++) {
            cbMonth.addItem(i < 10 ? (num + i) : "" + i);
        }
        load = true;
    }
    
    public static void getDay(JComboBox<String> cbDay, JComboBox<String> cbMonth, JComboBox<String> cbYear) {
        if (load) {
            cbDay.removeAllItems();
            int year = Integer.parseInt(cbYear.getItemAt(cbYear.getSelectedIndex()));
            int month = Integer.parseInt(cbMonth.getItemAt(cbMonth.getSelectedIndex()));
            int day;
            boolean isLeap;

            if (year % 4 == 0)
            {
                if ( year % 100 == 0)
                {
                    isLeap = (year % 400 == 0);
                }
                else {
                    isLeap = true;
                }
            }
            else {
                isLeap = false;
            }

            switch (month) {
                case 2 -> {
                    if (isLeap) {
                        day = 29;
                    }
                    else {
                        day = 28;
                    }
                }
                case 4, 6, 9, 11 -> day = 30;
                default -> day = 31;
            }

            for (int i = 1; i <= day; i++) {
                cbDay.addItem(i < 10 ? (num + i) : "" + i);
            }
        }
    }
    
    public static void selectDate(JComboBox<String> cbDay, JComboBox<String> cbMonth, JComboBox<String> cbYear, String date) {
        int day = Integer.parseInt(date.split("-")[2]);
        int month = Integer.parseInt(date.split("-")[1]);
        int year = Integer.parseInt(date.split("-")[0]);
        cbYear.setSelectedIndex(LocalDateTime.now().getYear() - year);
        cbMonth.setSelectedIndex(month - 1);
        cbDay.setSelectedIndex(day - 1);
    }
}