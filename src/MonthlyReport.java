public class MonthlyReport {
    String itemName;
    String keyName;
    boolean isExpense;
    int quantity;
    int sumOfOne;

    public MonthlyReport(String itemName, boolean isExpense, int quantity, int sumOfOne, String keyName) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
        this.keyName = keyName;
    }
}
