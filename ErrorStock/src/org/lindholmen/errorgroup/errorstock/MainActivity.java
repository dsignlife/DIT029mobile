package org.lindholmen.errorgroup.errorstock;

import org.achartengine.model.CategorySeries;
import org.achartengine.model.TimeSeries;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;

public class MainActivity extends Activity {
	public static String[] date;
	public static int[] volume;
	public static double[] opening;
	public static double[] closing;
	public static double[] high;
	public static double[] low;
	public static String  dateFrom;
	public static String dateTo;
	public static boolean dailyData;	// true = daily data, false = weekly data
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		dateFrom = "0";	//0 = no date set
		dateTo = "0";
		dailyData = false;
		
		loadTestData1();
	}

	/**@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

	public void LineGraphhandler(View view) {

		//System.out.println(opening.length);
		//System.out.println(closing.length);
		TimeSeries[] series = {createTimeSeries(opening, "Opening Price"), createTimeSeries(closing, "Closing Price"), createTimeSeries(high, "Highest Price"), createTimeSeries(low, "Lowest Price")};
		LineChart line = new LineChart();
		int[] color = {Color.RED, Color.BLUE, Color.MAGENTA, Color.CYAN};
		Intent lineIntent = line.getIntent(this, "Line Chart", series, color, date);
		startActivity(lineIntent);
	}
	public void PieGraphhandler(View view) {
		//
		//int[] values = {1,2,3,4,5};
		int[] values = getPieVolume();
		CategorySeries series = new CategorySeries("Pie Chart");
		/**int k=0;
		for(int value:values){
			series.add("Section " + ++k, value);
		}*/
		String[] names = getPieDate();
		for(int i=0; i<values.length; i++){
			series.add(names[i], values[i]);
		}
		
		int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.GRAY, Color.RED, Color.CYAN, Color.BLACK, Color.parseColor("#FFA500") };
		//
		
		PieChart pie = new PieChart();
		Intent pieIntent = pie.getIntent(this, "Pie Chart", series, colors);
		startActivity(pieIntent);
	}
	public void ChandleGraphhandler(View view) {
		//double[] minValues = new double[] { 2, 4, 6, 8, 7, 12, 15, 14, 9, 1, 9, 7 };
		//double[] maxValues = new double[] { 7, 12, 24, 28, 33, 35, 37, 36, 28, 19, 13, 17 };
		//String[] testDates = {"2013-09-16","2013-09-23","2013-09-30","2013-10-07","2013-10-14","2013-10-21","2013-10-28","2013-11-04","2013-11-11","2013-11-18","2013-11-25","2013-12-02"};
		
		CandleChart candle = new CandleChart();
		//Intent candleIntent = candle.getIntent(this, "Vertical Range Chart", minValues, maxValues, testDates);
		Intent candleIntent = candle.getIntent(this, "Vertical Range Chart", low, high, date);
		startActivity(candleIntent);
		
		/**TemperatureChart candle = new TemperatureChart();
		Intent candleIntent = candle.execute(this);
		startActivity(candleIntent);*/
	}
	public void VerticalRangeBhanddler(View view) {
		CandleChart candle = new CandleChart();
		Intent candleIntent = candle.getIntent(this, "Vertical Range Chart", low, high, date);
		startActivity(candleIntent);
		
		/**TemperatureChart candle = new TemperatureChart();
		Intent candleIntent = candle.execute(this);
		startActivity(candleIntent);*/
	}
	public void CandlestickHandler(View view) {
		startActivity(new Intent(this, CandlestickChart.class));
	}
	public void Newshandler(View view) {

	}
	public void FindButtonhandler(View view) {

	}
	public void Timehandler(View view) {
		startActivity(new Intent(this, TimeSettings.class));
	}
	
	public void loadTestData1(){
		String[] datex = {"2013-09-16","2013-09-23","2013-09-30","2013-10-07","2013-10-14","2013-10-21","2013-10-28","2013-11-04","2013-11-11","2013-11-18"};
		int[] volumex = {2094700, 1486300, 1675500, 1910500, 4133200, 2524500, 1397500, 1240700, 1270600, 1279900};
		double[] openingx = {896.20, 896.15, 869.08, 867.45, 866.66, 1011.46, 1015.20, 1031.50, 1009.51, 1035.75};
		double[] closingx = {903.11, 876.39, 872.35, 871.99, 1011.41, 1015.20, 1027.04, 1016.03, 1033.56, 1022.31};
		double[] highx = {905.99, 901.59, 894.10, 873.99, 1015.46, 1040.57, 1041.52, 1032.37, 1039.75, 1048.74};
		double[] lowx = {881.00, 871.31, 868.31, 842.98, 865.39, 995.79, 1012.99, 1007.64, 1005.00, 1020.36};
		
		date = datex;
		volume = volumex;
		opening = openingx;
		closing = closingx;
		high = highx;
		low = lowx;
	}
	
	public TimeSeries createTimeSeries(double[] y, String title){
		//int[] x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // x values!
		//double[] y =  { 903.11, 876.39, 872.35, 871.99, 1011.41, 1015.20, 1027.04, 1016.03 ,1033.56 ,1022.31 }; // y values!
		TimeSeries series = new TimeSeries(title);
		//
		for(int i=0; i<y.length; i++){
			series.add(i, y[i]);
		}
		return series;
	}
	
	public int[] getPieVolume(){
		int[] temp = {volume[0],  volume[1],  volume[2],  volume[3],  volume[4],  volume[5],  volume[6]};
		//int[] temp = {volume[0],  volume[1],  volume[2],  volume[3],  volume[4]};
		return temp;
	}
	public String[] getPieDate(){
		String[] temp = {date[0], date[1], date[2], date[3], date[4], date[5], date[6]};
		return temp;
	}
}
