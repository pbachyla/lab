
public class MonthEnumComparable implements MonthComparator {

    @Override
    public int compare(String s1, String s2) {
        return Integer.compare(Month.valueOf(s1.toUpperCase()).ordinal(),
                Month.valueOf(s2.toUpperCase()).ordinal());
    }
}
