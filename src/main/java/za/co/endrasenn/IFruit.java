package za.co.endrasenn;

import java.util.Date;
import java.util.List;

public interface IFruit {

	int getMarkedUpUnitPrice();

	Date getDeliveryDate();

	Date getSellByDate();

	int getMarkUpPercent();

	int getSellByDays();
	
	List<String> generatePriceTag();
	
}
