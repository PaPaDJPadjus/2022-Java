package inheritance.analyser;

import java.util.List;

public final class TaxFreeSalesAnalyser extends AbstractSalesAnalyser{

    protected TaxFreeSalesAnalyser(List<SalesRecord> records) {
        this.records = records;
        tax = 0.0;
    }

    protected double calculateTaxFreeSales() {
        return calculator(tax, records);
    }
}
