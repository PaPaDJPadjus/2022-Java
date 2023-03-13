package inheritance.analyser;

import java.util.List;

public final class DifferentiatedTaxSalesAnalyser extends AbstractSalesAnalyser {

    protected DifferentiatedTaxSalesAnalyser(List<SalesRecord> records) {
        this.records = records;
        tax = 0.2;
        differentiatedTaxSwitcher = true;
    }

    protected double differentiatedTaxSalesAnalyser() {
        return calculator(tax, records);
    }

    @Override
    protected void calculatorPrivileges() {

    }
}
