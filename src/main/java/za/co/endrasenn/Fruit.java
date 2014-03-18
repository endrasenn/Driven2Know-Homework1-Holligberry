package za.co.endrasenn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Fruit implements IFruit {

	private static final String AMOUNT_MINOR_FORMATTER = "%02d";
	private static final String AMOUNT_MAJOR_FORMATTER = "% 5d";
	private static final String CURRENCY_SYMBOL = "R";
	private static final int ZERO_PRICE = 0;
	private int unitPrice;
	private int supplierId;
	private int productCode;
	private String productDescription;
	private int numberOfUnits;
	private Date deliveryDate;

	private ISupplier supplier;
	
	private SimpleDateFormat sdf;

	public Fruit( int supplierId, int productCode,
			String productDescription, int unitPrice, int numberOfUnits, Date deliveryDate,
			ISupplier supplier, SimpleDateFormat sdf) {
		super();
		this.unitPrice = unitPrice;
		this.supplierId = supplierId;
		this.productCode = productCode;
		this.productDescription = productDescription;
		this.numberOfUnits = numberOfUnits;
		this.deliveryDate = deliveryDate;
		this.supplier = supplier;
		this.sdf = sdf;
	}

	public int getMarkedUpUnitPrice(){
		return getUnitPrice() == ZERO_PRICE?ZERO_PRICE:new Double((getSupplier().getSupplierAdjustedMarkedUpUnitPrice( getUnitPrice(), getMarkUpPercent()))).intValue();
	}

	public Date getSellByDate() {
		Calendar sellByDate = Calendar.getInstance();
		sellByDate.setTime( getDeliveryDate());
		sellByDate.add(Calendar.DATE, getSellByDays() + getSupplier().getSupplierAdjustedSellByDays());
		return sellByDate.getTime();
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getNumberOfUnits() {
		return numberOfUnits;
	}

	public void setNumberOfUnits(int numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}

	public ISupplier getSupplier() {
		return supplier;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public int getMarkUpPercent() {
		return 50;
	}

	public int getSellByDays() {
		return 7;
	}

	public List<String> generatePriceTag() {
		List<String> ret = new ArrayList<String>();
		for (int i = 0; i < numberOfUnits; i++) {
			ret.add(CURRENCY_SYMBOL + getPaddedPrice(getMarkedUpUnitPrice()) + sdf.format( getSellByDate()) + productDescription.subSequence(0, 31));			
		}
		return ret;
	}

	private String getPaddedPrice(int price) {
		return String.format(AMOUNT_MAJOR_FORMATTER, price / 100) + "." + String.format(AMOUNT_MINOR_FORMATTER,price % 100);
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	
	
}
