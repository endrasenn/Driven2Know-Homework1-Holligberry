package za.co.endrasenn;

public class PremiumSupplier implements ISupplier{


	public int getSupplierAdjustedSellByDays() {
		return 0;
	}

	public double getSupplierAdjustedMarkedUpUnitPrice(double unitPrice,
			int markupPercent) {
		return Math.ceil((unitPrice * (1.1d + (markupPercent / 100d)))/100) * 100;
	}

	
	
}
