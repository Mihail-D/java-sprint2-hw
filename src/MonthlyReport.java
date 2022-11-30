public class MonthlyReport {
    String itemName;
    boolean isExpense;
    int quantity;
    int sumOfOne;
    String keyName;

    public MonthlyReport(String itemName, boolean isExpense, int quantity, int sumOfOne, String keyName) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
        this.keyName = keyName;
    }
}
