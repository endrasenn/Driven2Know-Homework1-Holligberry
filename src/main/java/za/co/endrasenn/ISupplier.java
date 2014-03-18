package za.co.endrasenn;


public interface ISupplier {

	double getSupplierAdjustedMarkedUpUnitPrice(double unitPrice, int markupPercent);
	
	int getSupplierAdjustedSellByDays();
	
}
