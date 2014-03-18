package za.co.endrasenn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FruitBuilder {

	private static final String DATE_FORMAT = "yyyy/MM/dd";
	private static final String CSV_PARSE_REGEX = ",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))";
	public static final int[] PREMIUM_SUPPLIERS = new int[]{219, 204};
	public static final int[] BAD_SUPPLIERS = new int[]{101,32,219,204};
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	
	public IFruit buildFruit(String fruitString) throws ParseException{
		IFruit fruit;
		String columns[] = fruitString.split(CSV_PARSE_REGEX);
		int supplierId = Integer.parseInt( columns[0]);
		int productCode = Integer.parseInt( columns[1]);
		String productDescription = columns[2].replaceAll("\"", "");
		Date deliveryDate = sdf.parse( columns[3].replaceAll("\"", ""));
		int unitPrice = Integer.parseInt( columns[4]);
		int numberOfUnits = Integer.parseInt( columns[5]);
		
		ISupplier supplier = buildSupplier(supplierId);
		
		switch(getFruitType(Integer.parseInt(columns[1]))){
		
		case APPLES:
			fruit = new Apple(supplierId, productCode, productDescription, unitPrice, numberOfUnits, deliveryDate, supplier, sdf);
			break;
		case BANANAS:
			fruit = new Banana(supplierId, productCode, productDescription, unitPrice, numberOfUnits, deliveryDate, supplier, sdf);
			break;
		case BERRIES:
			fruit = new Berry(supplierId, productCode, productDescription, unitPrice, numberOfUnits, deliveryDate, supplier, sdf);
			break;
		default:
			fruit = new Fruit(supplierId, productCode, productDescription, unitPrice, numberOfUnits, deliveryDate, supplier, sdf);
			break;
		}
		
		return fruit;
		
	}

	private ISupplier buildSupplier(int supplierId) {
		switch (getSupplierType(supplierId)){
			case PREMIUM:
				return new PremiumSupplier();
			case BAD:
				return new BadSupplier();
			default:
				return new GeneralSupplier();
		}
	}
	
	private boolean containsInt(int id, int[] list){
		for (int i : list) {
			if (i == id){
				return true;
			}
		}
		return false;
	}
	
	private ESupplierType getSupplierType(int supplierId){
		if ( containsInt(supplierId, PREMIUM_SUPPLIERS)){
			return ESupplierType.PREMIUM;
		}else if ( containsInt(supplierId, BAD_SUPPLIERS)){
			return ESupplierType.BAD;
		}else {
			return ESupplierType.GENERAL;
		}
	}

	public boolean isBetween(int x, int lower, int upper) {
		return lower <= x && x <= upper;
	}

	public EFruitType getFruitType(int code){
		
		if ( isBetween(code, 1100, 1199)){
			return EFruitType.APPLES;
		}else if ( isBetween(code, 1200, 1299)){
			return EFruitType.BANANAS;
		}else if ( isBetween(code, 1300, 1399)){
			return EFruitType.BERRIES;
		}else {
			return EFruitType.ANYTHING;
		}
		
	}
}
