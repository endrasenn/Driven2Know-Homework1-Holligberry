package za.co.endrasenn;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Apple extends Fruit{
	
	public Apple(int supplierId, int productCode,
			String productDescription, int unitPrice, int numberOfUnits, Date deliveryDate,
			ISupplier supplier, SimpleDateFormat sdf) {
		super(supplierId, productCode,
				productDescription, unitPrice, numberOfUnits, deliveryDate, supplier, sdf);
	}

	@Override
	public int getMarkUpPercent(){
		return 40;
	}
	
	@Override
	public int getSellByDays() {
		return 14;
	}
	
}
