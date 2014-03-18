package za.co.endrasenn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PriceTagGeneratorTest {

	private static final String[] TEST_DATA = new String[]{
	"15,1101,\"Apples 1kg Golden Delicious. The sweetest Apples! Always a favourite. Love, Mrs. Hollingberry\",\"2012/02/15\",1505,5",
	"15,1102,\"Apples 1kg Green. They are very crunchy! Once my teeth got stuck in one of these apples and I didn't notice until a little girl pointed at the counter where I left the apple. She started crying, shame... Love, Mrs. Hollingberry\",\"2012/02/16\",1423,2",
	"32,1103,\"Apples 1kg Red. Less crunchy than the green ones, that's for sure. I prefer these myself nowadays. Love, Mrs. Hollingberry\",\"2012/02/16\",1431,3",
	"32,1201,\"Bananas 1kg. Just the other day I heard that some tribes in Africa cook green bananas like potatoes! Our bananas are yellow, so don't worry about that. Love, Mrs. Hollingberry\",\"2012/02/27\",984,4",
	"23,1328,\"Gooseberries 500g. Mr Hollingberry once ate so much Gooseberries, his tummy rumbled for 3 days! My, was he grumpy then... Love, Mrs. Hollingberry\",\"2012/02/29\",1922,7",
	"54,1342,\"Strawberries 500g. You really should taste this, it has been imported from the Paarl region. So lovely! Love, Mrs. Hollingberry\",\"2012/02/23\",2232,4",
	"22,1357,\"Blueberries 500g. I have so many blueberry recipes, come ask me anytime. Also, I know exactly how to get those stains out of your clothes. Love, Mrs. Hollingberry\",\"2012/02/24\",2503,5",
	"999,1399,\"Jaboca Berries. My grandson keeps asking me to get these, but we really have difficulty getting stock. Do you know where I can order some? Love, Mrs. Hollingberry\",\"2012/02/23\",0,0",
	"101,1320,\"Raspberries - Red. 500g. I still remember the days when I climbed Raspberry trees. But deary me, I won't try it now! Love, Mrs. Hollingberry\",\"2012/02/29\",1615,2",
	"101,1208,\"Bananas 500g. Please take one for free! Togetherness was kind enough to share some extras he had. Love, Mrs. Hollingberry\",\"2012/02/29\",0,2",
	"219,1106,\"Apples 1kg Pink Lady. Perfect for a warm summer day. Love, Mrs. Hollingberry\",\"2012/02/15\",1609,4",
	"204,1381,\"Miracle Berry 500g. This is simply amazing! If you eat one of these berries before eating a lemon, the lemon tastes sweet! Try it if you don't believe me! Love, Mrs. Hollingberry\",\"2012/02/25\",2894,3"};

	private static final String[] TEST_RESULT = new String[]{
		"R   21.072012/02/29Apples 1kg Golden Delicious. Th",
		"R   21.072012/02/29Apples 1kg Golden Delicious. Th",
		"R   21.072012/02/29Apples 1kg Golden Delicious. Th",
		"R   21.072012/02/29Apples 1kg Golden Delicious. Th",
		"R   21.072012/02/29Apples 1kg Golden Delicious. Th",
		"R   19.922012/03/01Apples 1kg Green. They are very",
		"R   19.922012/03/01Apples 1kg Green. They are very",
		"R   18.032012/02/27Apples 1kg Red. Less crunchy th",
		"R   18.032012/02/27Apples 1kg Red. Less crunchy th",
		"R   18.032012/02/27Apples 1kg Red. Less crunchy th",
		"R   11.282012/02/29Bananas 1kg. Just the other day",
		"R   11.282012/02/29Bananas 1kg. Just the other day",
		"R   11.282012/02/29Bananas 1kg. Just the other day",
		"R   11.282012/02/29Bananas 1kg. Just the other day",
		"R   29.792012/03/07Gooseberries 500g. Mr Hollingbe",
		"R   29.792012/03/07Gooseberries 500g. Mr Hollingbe",
		"R   29.792012/03/07Gooseberries 500g. Mr Hollingbe",
		"R   29.792012/03/07Gooseberries 500g. Mr Hollingbe",
		"R   29.792012/03/07Gooseberries 500g. Mr Hollingbe",
		"R   29.792012/03/07Gooseberries 500g. Mr Hollingbe",
		"R   29.792012/03/07Gooseberries 500g. Mr Hollingbe",
		"R   34.602012/03/01Strawberries 500g. You really s",
		"R   34.602012/03/01Strawberries 500g. You really s",
		"R   34.602012/03/01Strawberries 500g. You really s",
		"R   34.602012/03/01Strawberries 500g. You really s",
		"R   38.802012/03/02Blueberries 500g. I have so man",
		"R   38.802012/03/02Blueberries 500g. I have so man",
		"R   38.802012/03/02Blueberries 500g. I have so man",
		"R   38.802012/03/02Blueberries 500g. I have so man",
		"R   38.802012/03/02Blueberries 500g. I have so man",
		"R   23.032012/03/04Raspberries - Red. 500g. I stil",
		"R   23.032012/03/04Raspberries - Red. 500g. I stil",
		"R    0.002012/03/02Bananas 500g. Please take one f",
		"R    0.002012/03/02Bananas 500g. Please take one f",
		"R   25.002012/02/29Apples 1kg Pink Lady. Perfect f",
		"R   25.002012/02/29Apples 1kg Pink Lady. Perfect f",
		"R   25.002012/02/29Apples 1kg Pink Lady. Perfect f",
		"R   25.002012/02/29Apples 1kg Pink Lady. Perfect f",
		"R   48.002012/03/03Miracle Berry 500g. This is sim",
		"R   48.002012/03/03Miracle Berry 500g. This is sim",
		"R   48.002012/03/03Miracle Berry 500g. This is sim"
	};
	
	private FruitBuilder fruitBuilder;
	@Before
	public void setup(){
		fruitBuilder = new FruitBuilder();
	}
	
	@Test
	public void testGeneratePriceTag(){
		try {
			List<String> ret = new ArrayList<String>();
			int counter = 0;
			for ( int i = 0; i < TEST_DATA.length; i++){
				IFruit fruit =  fruitBuilder.buildFruit( TEST_DATA[i]);
				for (String priceTag : fruit.generatePriceTag()) {
					assertEquals( TEST_RESULT[counter++], priceTag);
				}	
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void testGenerateApplePriceTagGeneralSupplier(){
		IFruit apple;
		ISupplier supplier = new GeneralSupplier();
		try {
			apple = new Apple(15, 1101, "Apples 1kg Golden Delicious. The sweetest Apples! Always a favourite. Love, Mrs. Hollingberry", 1505, 5, FruitBuilder.sdf.parse("2012/02/15"), supplier, FruitBuilder.sdf);
			testFruit(apple, 14, 2107);
		} catch (ParseException e) {
			fail("invalid delivery date format specified - expecing yyyy/MM/dd");
		}
	}
	
	@Test
	public void testGenerateApplePriceTagBadSupplier(){
		IFruit apple;
		ISupplier supplier = new BadSupplier();
		try {
			apple = new Apple(15, 1101, "Apples 1kg Golden Delicious. The sweetest Apples! Always a favourite. Love, Mrs. Hollingberry", 1505, 5, FruitBuilder.sdf.parse("2012/02/15"), supplier, FruitBuilder.sdf);
			testFruit(apple, 11, 1907);
		} catch (ParseException e) {
			fail("invalid delivery date format specified - expecing yyyy/MM/dd");
		}
	}
	
	
	@Test
	public void testGenerateApplePriceTagGoodSupplier(){
		IFruit apple;
		ISupplier supplier = new PremiumSupplier();
		try {
			apple = new Apple(15, 1101, "Apples 1kg Golden Delicious. The sweetest Apples! Always a favourite. Love, Mrs. Hollingberry", 1505, 5, FruitBuilder.sdf.parse("2012/02/15"), supplier, FruitBuilder.sdf);
			testFruit(apple, 14, 2300);
		} catch (ParseException e) {
			fail("invalid delivery date format specified - expecing yyyy/MM/dd");
		}
	}
	
	@Test
	public void testGenerateBananaPriceTagGoodSupplier(){
		IFruit banana;
		ISupplier supplier = new PremiumSupplier();
		try {
			banana = new Banana(32, 1201, "Bananas 1kg. Just the other day I heard that some tribes in Africa cook green bananas like potatoes! Our bananas are yellow, so don't worry about that. Love, Mrs. Hollingberry", 984, 4, FruitBuilder.sdf.parse("2012/02/27"), supplier, FruitBuilder.sdf);
			testFruit(banana, 5, 1500);
		} catch (ParseException e) {
			fail("invalid delivery date format specified - expecing yyyy/MM/dd");
		}
	}
	
	public void testFruit(IFruit fruit, int sellByDays, int expectedPrice){
			assertEquals( expectedPrice, fruit.getMarkedUpUnitPrice());
			Calendar sellByDate = Calendar.getInstance();
			sellByDate.setTime(fruit.getDeliveryDate());
			sellByDate.add(Calendar.DATE, sellByDays);
			assertEquals(sellByDate.getTime(), fruit.getSellByDate());
	}	
	
	
	@Test
	public void testGenerateBananaPriceTagGeneralSupplier(){
		IFruit banana;
		ISupplier supplier = new GeneralSupplier();
		try {
			banana = new Banana(32, 1201, "Bananas 1kg. Just the other day I heard that some tribes in Africa cook green bananas like potatoes! Our bananas are yellow, so don't worry about that. Love, Mrs. Hollingberry", 984, 4, FruitBuilder.sdf.parse("2012/02/27"), supplier, FruitBuilder.sdf);
			testFruit(banana, 5, 1328);
		} catch (ParseException e) {
			fail("invalid delivery date format specified - expecing yyyy/MM/dd");
		}
	}	
	
	@Test
	public void testGenerateBananaPriceTagBadSupplier(){
		IFruit banana;
		ISupplier supplier = new BadSupplier();
		try {
			banana = new Banana(32, 1201, "Bananas 1kg. Just the other day I heard that some tribes in Africa cook green bananas like potatoes! Our bananas are yellow, so don't worry about that. Love, Mrs. Hollingberry", 984, 4, FruitBuilder.sdf.parse("2012/02/27"), supplier, FruitBuilder.sdf);
			testFruit(banana, 2, 1128);
		} catch (ParseException e) {
			fail("invalid delivery date format specified - expecing yyyy/MM/dd");
		}
	}	
	
	@Test
	public void testGenerateBerryPriceTagBadSupplier(){
		IFruit berry;
		ISupplier supplier = new BadSupplier();
		try {
			berry = new Berry(23, 1922, "Gooseberries 500g. Mr Hollingberry once ate so much Gooseberries, his tummy rumbled for 3 days! My, was he grumpy then... Love, Mrs. Hollingberry", 1922, 7, FruitBuilder.sdf.parse("2012/02/29"), supplier, FruitBuilder.sdf);
			testFruit(berry, 4, 2779);
		} catch (ParseException e) {
			fail("invalid delivery date format specified - expecing yyyy/MM/dd");
		}
	}	
	
	@Test
	public void testGenerateBerryPriceTagGeneralSupplier(){
		IFruit berry;
		ISupplier supplier = new GeneralSupplier();
		try {
			berry = new Berry(23, 1922, "Gooseberries 500g. Mr Hollingberry once ate so much Gooseberries, his tummy rumbled for 3 days! My, was he grumpy then... Love, Mrs. Hollingberry", 1922, 7, FruitBuilder.sdf.parse("2012/02/29"), supplier, FruitBuilder.sdf);
			testFruit(berry, 7, 2979);
		} catch (ParseException e) {
			fail("invalid delivery date format specified - expecing yyyy/MM/dd");
		}
	}	
	
	@Test
	public void testGenerateBerryPriceTagGoodSupplier(){
		IFruit berry;
		ISupplier supplier = new PremiumSupplier();
		try {
			berry = new Berry(23, 1922, "Gooseberries 500g. Mr Hollingberry once ate so much Gooseberries, his tummy rumbled for 3 days! My, was he grumpy then... Love, Mrs. Hollingberry", 1922, 7, FruitBuilder.sdf.parse("2012/02/29"), supplier, FruitBuilder.sdf);
			testFruit(berry, 7, 3200);
		} catch (ParseException e) {
			fail("invalid delivery date format specified - expecing yyyy/MM/dd");
		}
	}	
	
	@After
	public void tearDown(){
		
	}
	
}


