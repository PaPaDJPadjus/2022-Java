package inheritance.analyser;

import java.util.List;

public final class FlatTaxSalesAnalyser extends AbstractSalesAnalyser{

    protected FlatTaxSalesAnalyser(List<SalesRecord> records) {
            this.records = records;
            tax = 0.2;
        }
    protected double calculateFlatTaxSales() {
        return calculator(tax, records);
    }

    @Override
    protected void calculatorPrivileges() {

    }
}
