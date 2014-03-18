package za.co.endrasenn;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Banana extends Fruit{

	public Banana(int supplierId, int productCode, String productDescription,
			int unitPrice, int numberOfUnits, Date deliveryDate,
			ISupplier supplier, SimpleDateFormat sdf) {
		super(supplierId, productCode, productDescription, unitPrice, numberOfUnits,
				deliveryDate, supplier, sdf);
	}

	
	
	@Override
	public int getSellByDays() {
			return 5;
	}



	@Override
	public int getMarkUpPercent() {
		return 35;
	}

	
	
}
