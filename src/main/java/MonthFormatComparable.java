import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MonthFormatComparable implements MonthComparator  {

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("MMMM");

     public Date format(String value){
         try {
             return FORMATTER.parse(value);
         } catch (ParseException e) {
             throw new IllegalArgumentException( value + " is not a month");
         }
     }

    @Override
    public int compare(String s1, String s2) {
        return format(s1).compareTo(format(s2));
    }
}
