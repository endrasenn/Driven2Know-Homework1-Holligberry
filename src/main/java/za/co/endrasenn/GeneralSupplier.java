package za.co.endrasenn;

public class GeneralSupplier implements ISupplier{

	public int getSupplierAdjustedSellByDays() {
		return 0;
	}

	public double getSupplierAdjustedMarkedUpUnitPrice(double unitPrice,
			int markupPercent) {
		return  Math.round( unitPrice * (1 + (markupPercent / 100d)));
	}

	
	
}
