import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


public class MonthLinkedListTest {

    @Test
    public void testIfListContainsSingleElementAfterAddingOneReturnsOneElement() throws Exception {
        MonthLinkedList expected = MonthLinkedList.of("May");
        MonthLinkedList actual = new MonthLinkedList();
        actual.add("May");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIfRemoveByIndexOneRemovesElement() throws Exception {
        MonthLinkedList expected = MonthLinkedList.of("January", "October");
        MonthLinkedList actual = MonthLinkedList.of("January", "February", "October");
        actual.remove(1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIfRemoveByFebruaryRemovesFirstOccurencyInList() throws Exception {
        MonthLinkedList expected = MonthLinkedList.of("January", "October", "February");
        MonthLinkedList actual = MonthLinkedList.of("January", "February", "October", "February");
        actual.remove("February");
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void testIfRemoveLastWithEmptyListThrowsException() throws Exception {
        MonthLinkedList list = new MonthLinkedList();
        list.removeLast();
    }

    @Test(expected = NullPointerException.class)
    public void testIfRemoveFirstWithEmptyListThrowsException() throws Exception {
        MonthLinkedList list = new MonthLinkedList();
        list.removeFirst();
    }

    @Test
    public void testIfAfterAddAndRemoveElementListIsEmpty() throws Exception {
        MonthLinkedList expected = new MonthLinkedList();
        MonthLinkedList acutal = new MonthLinkedList();
        acutal.add("May");
        acutal.removeFirst();
        Assert.assertEquals(expected, acutal);
    }

    @Test
    public void testIfRemoveLastRemovesLastElementWhenListHasTwoElements() throws Exception {
        MonthLinkedList expected = MonthLinkedList.of("January");
        MonthLinkedList actual = MonthLinkedList.of("January", "February");
        actual.removeLast();
        assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void testIfAddNullValueThrowsException() throws Exception {
        MonthLinkedList list = new MonthLinkedList();
        list.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfSetWithNegativeIndexAnExceptionIsThrown() throws Exception {
        MonthLinkedList list = new MonthLinkedList();
        list.add("January");
        list.set(-2, "May");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIfSetWithIndexMoreThanSizeAnExceptionIsThrown() throws Exception {
        MonthLinkedList list = new MonthLinkedList();
        list.add("January");
        list.set(5, "May");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIfGetWithIndexMoreThanSizeAnExceptionIsThrown() throws Exception {
        MonthLinkedList list = new MonthLinkedList();
        list.add("January");
        list.get(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfGetWithNegativeIndexAnExceptionIsThrown() throws Exception {
        MonthLinkedList list = new MonthLinkedList();
        list.add("January");
        list.get(-5);
    }

    @Test
    public void testIfSortUsingEnumComparatorWithRepeatedMothsWorks() throws Exception {
        MonthLinkedList expected = MonthLinkedList.of("January", "February", "June", "June", "December", "December");
        MonthLinkedList actual = getEpamLinkedList();
        actual.sort(new MonthEnumComparable());
        assertEquals(expected, actual);
    }

    @Test
    public void testIfSortUsingFormatComparatorWithRepeatedMothsWorks() throws Exception {
        MonthLinkedList expected = MonthLinkedList.of("January", "February", "June", "June", "December", "December");
        MonthLinkedList actual = getEpamLinkedList();
        actual.sort(new MonthFormatComparable());
        assertEquals(expected, actual);
    }

    private MonthLinkedList getEpamLinkedList() {
        MonthLinkedList actual = new MonthLinkedList();
        actual.add("December");
        actual.add("June");
        actual.add("January");
        actual.add("June");
        actual.add("February");
        actual.add("December");
        return actual;
    }


}