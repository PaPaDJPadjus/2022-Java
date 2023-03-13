package inheritance.analyser;

import java.util.List;

public sealed abstract class AbstractSalesAnalyser permits DifferentiatedTaxSalesAnalyser, FlatTaxSalesAnalyser, TaxFreeSalesAnalyser {
    protected double tax = 0.0;
    protected List<SalesRecord> records;
    protected boolean differentiatedTaxSwitcher = false;

    protected abstract void calculatorPrivileges();

    public double calculator(double tax, List<SalesRecord> records) {
        double sumOfContents = 0.0;
        for (SalesRecord record : records) {
            if (differentiatedTaxSwitcher && record.hasReducedRate()) {
                sumOfContents += (record.getProductPrice()  * record.getItemsSold()) / 1.1;
            } else if (differentiatedTaxSwitcher) {
                sumOfContents += (record.getProductPrice()  * record.getItemsSold()) / (1 + tax);
            }
            if (!differentiatedTaxSwitcher) {
                sumOfContents += (record.getProductPrice()  * record.getItemsSold()) / (1 + tax);
            }
        }
        return sumOfContents;
    }
    public double getTotalSales() {
        return calculator(tax, records);
    }

    public double getTotalSalesByProductId(String id) {
        double totalSales = 0;
        for (SalesRecord record : records) {
            if (record.getProductId().equals(id)) {
                totalSales += record.getProductPrice() * record.getItemsSold();
            }
        }
        return totalSales / (1 + tax);
    }

    public String getIdOfMostPopularItem() {
        int mostSales = 0;
        String mostSalesId = "";
        for (SalesRecord record : records) {
            if (record.getItemsSold() > mostSales) {
                mostSales = record.getItemsSold();
                mostSalesId = record.getProductId();
            }
        }
        return mostSalesId;
    }

    public String getIdOfItemWithLargestTotalSales() {
        double leaderAmountOfSales = 0.0;
        String idOfLeader = "";

        for (SalesRecord record : records) {
            double newRecordToCheck = countTotalSalesForId(record.getProductId());
            if (newRecordToCheck > leaderAmountOfSales) {
                leaderAmountOfSales = newRecordToCheck;
                idOfLeader = record.getProductId();
            }
        }
        return idOfLeader;
    }

    public double countTotalSalesForId (String id) {
        double totalSalesForId = 0.0;
        for (SalesRecord record : records) {
            if (record.getProductId().equals(id)) {
                totalSalesForId += record.getItemsSold() * record.getProductPrice();
            }
        }
        return totalSalesForId;
    }
}
