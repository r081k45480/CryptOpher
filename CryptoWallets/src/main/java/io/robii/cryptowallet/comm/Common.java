package io.robii.cryptowallet.comm;

import java.text.DecimalFormat;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Common {



    public Common(){
		try {
			currencySymbol = "�";
			percentage = "%";
			datePattern = "yyyy-MM-dd";
			timePattern = "HH:mm";
			dateTimePattern = datePattern+" "+timePattern;
		}catch (Exception e){
			;
		}
	}
	public static String datePattern;
	public static String timePattern;
	public static String dateTimePattern;
	public static String currencySymbol = "";
	public static String percentage = "";

	public static final String COIN_MARKET_CUP_LINK_BASE= "https://coinmarketcap.com/currencies/";

	private static ExecutorService executor = Executors.newFixedThreadPool(8);
	
    public static<T> Future<T> getFuture(Callable<T> task){
    	Future<T> futurePrice = executor.submit(task);
    	return futurePrice;
    }

    public static Double twoDecimals(Double v) {
    	Double shifted = (v * 100.00);
    	long round=Math.round(shifted);
    	return (round*1.0)/100.00;
    }

    public static String twoDecimalsStr(Double v){
    	Double d = twoDecimals(v);
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(d);
	}

    public static<T> T getResult(Future<T> t){
		try {
			return t.get();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}
}
