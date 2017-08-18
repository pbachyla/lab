import java.util.Objects;

public enum Month {


    JANUARY,

    FEBRUARY,

    MARCH,

    APRIL,

    MAY,

    JUNE,

    JULY,

    AUGUST,

    SEPTEMBER,

    OCTOBER,

    NOVEMBER,

    DECEMBER;


    public static boolean contains(String element){
        Month[] values = Month.values();
        for (Month value : values) {
            if (Objects.equals(element.toUpperCase(), value.toString())) return true;
        }
        return false;
    }

}
