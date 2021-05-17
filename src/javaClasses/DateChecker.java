package javaClasses;

public class DateChecker {

    private String date;
    private boolean valid;

    public void setDate(String date) {
        this.date = date;
        this.valid = check();
    }

    public boolean check() {
        if ( this.date.length() != 10 ) {
            return false;
        }
        if ( this.date.charAt(2) != '-' || this.date.charAt(5) != '-' ) {
            return false;
        }
        boolean leapYear = false;
        int dateYear = ((this.date.charAt(6) - '0') * 1000) + ((this.date.charAt(7) - '0') + 100) + ((this.date.charAt(8) - '0') * 10) + (this.date.charAt(9) - '0');
        if ( dateYear % 100 == 0 && dateYear % 400 == 0 ) {
            leapYear = true;
        }
        if ( dateYear % 100 != 0 && dateYear % 4 == 0 ) {
            leapYear = true;
        }
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int dateMonth = ((this.date.charAt(3) - '0') * 10) + ((this.date.charAt(4)) - '0');
        if ( dateMonth < 1 || dateMonth > 12 ) {
            return false;
        }
        int dateDate = ((this.date.charAt(0) - '0') * 10) + (this.date.charAt(1) - '0');
        if ( leapYear && dateMonth == 2 ) {
            return dateDate >= 1 && dateDate <= 29;
        } else {
            return dateDate >= 1 && dateDate <= monthDays[dateMonth - 1];
        }
    }

    public boolean isValid() {
        return this.valid;
    }

}
