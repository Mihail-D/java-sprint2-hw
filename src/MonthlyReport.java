public class MonthlyReport {
    String itemName;
    boolean isExpense;
    int quantity;
    int sumOfOne;

    public MonthlyReport(String itemName, boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}
