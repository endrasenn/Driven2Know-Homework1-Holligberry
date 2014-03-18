package za.co.endrasenn;

public class BadSupplier implements ISupplier{



	public int getSupplierAdjustedSellByDays() {
		return -3;
	}

	public double getSupplierAdjustedMarkedUpUnitPrice(double unitPrice,
			int markupPercent) {
		return (unitPrice * (1 + (markupPercent / 100d))) - 200d;
	}
	
}
