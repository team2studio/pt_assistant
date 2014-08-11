package com.example.pt_assistant;

import java.text.DecimalFormat;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

// import com.example.pt_assistant.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.os.Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.pt_assistant.GetPatientActivity.doPatient;
import com.example.pt_assistant.Report.doReportTrendAsyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class TrendChartActivity extends ActionBarActivity {

	private String charttype, patient_name;
	private static final String JSON_PAT_NAME =        "name";
	private static final String JSON_ROM =             "range_of_motion";
	private static final String JSON_EVAL_DATE =       "evaldate";
	private static final String JSON_INJURY_NAME =     "injury_name";
	private static final String JSON_THERAPIST_NAME =  "therapist_name";
	private static final String JSON_STRENGTH =        "strength";
	private static final String JSON_PAIN =            "pain";
	String KEY_PATNAME =   "1";
	String KEY_ROM =       "2";
	String KEY_EVAL_DATE = "3";
	String KEY_INURY_NAME = "4";
	String KEY_THERAPIST = "5";
	String KEY_STRENT_LVL = "6";
	String KEY_PAIN_LVL = "7";
	
	
	private static final String GET_INDEX_URL =   "http://199.255.250.71/get_patient_trend_data.php";
	List<Map> patientTrendList;
	Map<String, String> patTrendmap;
	int pat_data [];
	
	
	
	
	
	// First Create a Graphical View object called mChart.

	private GraphicalView mChart;


	private String[] mMonth = new String[] {
	          "1/1/14", "2/3/14" , "2/17/14", "3/2/14", "5/4/14", "6/12/14",

	          "7/4/14", "7/21/14" };
	
	
	// Get report data from database using the query parameters
	
	public void get_patient_trend_data() { // For remote DB
		new doReportTrendAsyncTask().execute();
		
		//for (int i = 0; .)
		//Iterator it = patientTrendList.get(i)
	}

	// NOTE when ever going to network you must run it in a task
	private class doReportTrendAsyncTask extends AsyncTask<String, Void, String> {
		
		JSONParser jsonParser  = new JSONParser();
		String TAG_SUCCESS = "success";
		
		
		
		@Override
		protected String doInBackground(String... args) {
		
			report_async_get_trend_data();
			return "Executed";
		}

		@Override
		protected void onPostExecute(String result) {
			
			Iterator<Map> it = patientTrendList.iterator();
			
			Map map = new HashMap();
			
			while(it.hasNext()){
				
				map = it.next();
				
				Log.d("patient name: ", (String )map.get(KEY_PATNAME));
				Log.d("eval date: ", (String )map.get(KEY_EVAL_DATE));
				Log.d("rom: ", (String )map.get(KEY_ROM));
				Log.d("therapist name: ", (String )map.get(KEY_THERAPIST));
				Log.d("strength: ", (String )map.get(KEY_STRENT_LVL));
				Log.d("pain level: ", (String )map.get(KEY_PAIN_LVL));
				Log.d("injury name: ", (String )map.get(KEY_INURY_NAME));
				Log.d("record ", "next record");
				
				
				
				
			}

		}

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
		
		private void report_async_get_trend_data()
		{
			
			
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("patient_id", "1007"));
			params.add(new BasicNameValuePair("injuryName", "LOWER BACK INJURY"));
			try {
				String temp1,temp2,temp3,temp4,temp5,temp6,temp7,temp8;
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(GET_INDEX_URL,
						"GET", params);
				// json success tag
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					patientTrendList =  new ArrayList<Map>();
					JSONArray jsonPatTrendList = new JSONArray();
					jsonPatTrendList = json.getJSONArray("trend_data");
					
					for (int i= 0;i<jsonPatTrendList.length();i++){
						
						JSONObject c = jsonPatTrendList.getJSONObject(i);
						patTrendmap = new HashMap<String, String>();
						patTrendmap.put(KEY_PATNAME, c.getString(JSON_PAT_NAME));
						patTrendmap.put(KEY_ROM, c.getString(JSON_ROM));
						patTrendmap.put(KEY_EVAL_DATE, c.getString(JSON_EVAL_DATE));
						patTrendmap.put(KEY_INURY_NAME, c.getString(JSON_INJURY_NAME));
						patTrendmap.put(KEY_THERAPIST, c.getString(JSON_THERAPIST_NAME));
						patTrendmap.put(KEY_STRENT_LVL, c.getString(JSON_STRENGTH));
						patTrendmap.put(KEY_PAIN_LVL, c.getString(JSON_PAIN));
						patientTrendList.add(patTrendmap);
						
						
						
//						 temp1 =   c.getString(PAT_NAME);
//						 temp2 =  c.getString(ROM);
//						 temp3 = c.getString(EVAL_DATE);
//						 temp4 = c.getString(INJURY_NAME);
//						 temp5 = c.getString(THERAPIST_NAME);
//						 temp6 = c.getString(STRENGTH);
//						 temp7 = c.getString(PAIN);
					  
					
					}
					

				}

			} catch (JSONException e) {
				Thread.interrupted();
			}
			
		}
		
		
		
	}
	   
	 private void OpenChart()
	    {
	     // Define the number of elements you want in the chart.
	    
	     
	  int z[]={0,1,2,3,4,5,6,7};
	     
	     
	  String x[]={"0","0","0","0","0","0","0","0"};
	  
	  int xx[]={0,0,0,0,0,0,0,0};
	  
	  double cumulative_change;
	  double prog_change;
	  
	  DecimalFormat formatter = new DecimalFormat("#0.0");
	  
	  cumulative_change = 0.1;
	  prog_change = 0.1;
		
		 if (charttype == "PAIN")
		 {
			 z[0]=0;
			 z[1]=1;
			 z[2]=2;
			 z[3]=3;
			 z[4]=4;
			 z[5]=5;
			 z[6]=6;
			 z[7]=7;
		     
		     x[0]="9";
		     x[1]="6";
		     x[2]="5";
		     x[3]="5";
		     x[4]="3";
		     x[5]="2";
		     x[6]="2";
		     x[7]="1";
		     
		     double Y1 =  Double.parseDouble(x[0]);
			 double Y2 =  Double.parseDouble(x[x.length-1]);
			 double Y3 = Double.parseDouble(x[x.length-2]);
					
			 cumulative_change = ((Y2 - Y1)/Y1)*100;
			 prog_change = ((Y2 - Y3)/Y3)*100;
		     
		//     cumulative_change = ((x[x.length-1] - x[0])/x[0])*100;
		//	 prog_change = ((x[x.length-1] - x[x.length - 2])/x[x.length - 2])*100;
		     
		//     cumulative_change =((Integer.parseInt(x[x.length-1]) - Integer.parseInt(x[0]))/Integer.parseInt(x[0]))*100;
		//	 prog_change = ((Integer.parseInt(x[x.length-1]) - Integer.parseInt(x[x.length-2]))/Integer.parseInt(x[x.length-2]))*100;
		
		 }   
		 else if (charttype == "ROM")
		 {
			
		     z[0]=0;
			 z[1]=1;
			 z[2]=2;
			 z[3]=3;
			 z[4]=4;
			 z[5]=5;
			 z[6]=6;
			 z[7]=7;
		     
		     
		     x[0]="5";
		     x[1]="22";
		     x[2]="25";
		     x[3]="38";
		     x[4]="60";
		     x[5]="70";
		     x[6]="80";
		     x[7]="90";
		     
		  //   cumulative_change = ((x[x.length-1] - x[0])/x[0])*100;
		  //	 prog_change = ((x[x.length-1] - x[x.length - 2])/x[x.length - 2])*100;
		     
		  //   for (int i=1; i<x.length; i++ ) {
		  //  	 pat_data[i] = Integer.parseInt(x[i]);
		  //   }
		     
		     // cumulative_change = ((double)(xx[xx.length-1] - xx[0])/(double)xx[0])*100;
			 // prog_change = ((double)(xx[xx.length-1] - xx[xx.length-2])/(double)xx[xx.length-2])*100;
			double Y1 =  Double.parseDouble(x[0]);
			double Y2 =  Double.parseDouble(x[x.length-1]);
			double Y3 = Double.parseDouble(x[x.length-2]);
		
		
		   cumulative_change = ((Y2 - Y1)/Y1)*100;
		   prog_change = ((Y2 - Y3)/Y3)*100;
			//	 prog_change = 20.0;
			 
		 }   
		 else if (charttype == "STRENGTH")
		 {
			// int z2[]={0,1,2,3,4,5,6,7};
		     
		     
		    // int x2[]={0,5,23,30,59,65,88,84};
		     
		     z[0]=0;
			 z[1]=1;
			 z[2]=2;
			 z[3]=3;
			 z[4]=4;
			 z[5]=5;
			 z[6]=6;
			 z[7]=7;
		     
		     x[0]="4";
		     x[1]="5";
		     x[2]="23";
		     x[3]="30";
		     x[4]="59";
		     x[5]="65";
		     x[6]="88";
		     x[7]="91";
		     
		     double Y1 =  Double.parseDouble(x[0]);
			 double Y2 =  Double.parseDouble(x[x.length-1]);
			 double Y3 = Double.parseDouble(x[x.length-2]);
			
			
			 cumulative_change = ((Y2 - Y1)/Y1)*100;
			 prog_change = ((Y2 - Y3)/Y3)*100;
		
		  //   cumulative_change = ((x[7] - x[0])/x[0])*100;
		  //   prog_change = ((x[7] - x[6])/x[6])*100;
		     
		 //  cumulative_change = ((x[x.length-1] - x[0])/x[0])*100;
		 //  prog_change = ((x[x.length-1] - x[x.length-2])/x[x.length-2])*100;
		     
		//     cumulative_change = ((Integer.parseInt(x[x.length-1]) - Integer.parseInt(x[0]))/Integer.parseInt(x[0]))*100;
		//	 prog_change = ((Integer.parseInt(x[x.length-1]) - Integer.parseInt(x[x.length-2]))/Integer.parseInt(x[x.length-2]))*100;
		 }
		 
		 else
		 {
			 // do nothing
			 
			 cumulative_change = 0.1;
			 prog_change = 0.1;
		 }
		 
		

	      // Create XY Series for X Series.
	     XYSeries xSeries=new XYSeries(charttype +" Index");
	     

	     //  Adding data to the X Series.
	     for(int i=0;i<z.length;i++)
	     {
	      xSeries.add(z[i],Integer.parseInt(x[i]));
	   
	     }

	        // Create a Dataset to hold the XSeries.
	     
	     XYMultipleSeriesDataset dataset=new XYMultipleSeriesDataset();
	     
	      // Add X series to the Dataset.   
	 dataset.addSeries(xSeries);
	     
	     
	      // Create XYSeriesRenderer to customize XSeries

	     XYSeriesRenderer Xrenderer=new XYSeriesRenderer();
	     Xrenderer.setColor(Color.GREEN);
	     Xrenderer.setPointStyle(PointStyle.DIAMOND);
	     Xrenderer.setDisplayChartValues(true);
	     Xrenderer.setLineWidth(2);
	     Xrenderer.setFillPoints(true);
	     
	     // Create XYMultipleSeriesRenderer to customize the whole chart

	     XYMultipleSeriesRenderer mRenderer=new XYMultipleSeriesRenderer();
	     
	     mRenderer.setChartTitle(charttype +" Trend Chart" + ":"+" "+ patient_name
	    		                 + "    Total: " + formatter.format(cumulative_change) + "%"
	    		                 + "  Prog: " + formatter.format(prog_change) + "%");
	     mRenderer.setXTitle("Eval Date");
	     mRenderer.setYTitle("Measurement");
	     mRenderer.setZoomButtonsVisible(true);
	     mRenderer.setXLabels(0);
	     mRenderer.setPanEnabled(false);
	   
	     mRenderer.setShowGrid(true);
	 
	     mRenderer.setClickEnabled(true);
	     
	     for(int i=0;i<z.length;i++)
	     {
	      mRenderer.addXTextLabel(i, mMonth[i]);
	     }
	     
	       // Adding the XSeriesRenderer to the MultipleRenderer. 
	     mRenderer.addSeriesRenderer(Xrenderer);
	  
	     
	     LinearLayout chart_container=(LinearLayout)findViewById(R.id.Chart_layout);

	   // Creating an intent to plot line chart using dataset and multipleRenderer
	     
	     mChart=(GraphicalView)ChartFactory.getLineChartView(this, dataset, mRenderer);
	     
	     //  Adding click event to the Line Chart.

	     mChart.setOnClickListener(new View.OnClickListener() {
	   
	   @Override
	   public void onClick(View arg0) {
	    // TODO Auto-generated method stub
	    
	    SeriesSelection series_selection=mChart.getCurrentSeriesAndPoint();
	    
	    if(series_selection!=null)
	    {
	     int series_index=series_selection.getSeriesIndex();
	     
	     String select_series=charttype +" Index";
	     if(series_index==0)
	     {
	      select_series=charttype + " Index";
	     }else
	     {
	      select_series="Y Series";
	     }
	     
	     String month=mMonth[(int)series_selection.getXValue()];
	     
	     int amount=(int)series_selection.getValue();
	     
	     Toast.makeText(getBaseContext(), select_series+"in" + month+":"+amount, Toast.LENGTH_LONG).show();
	    }
	   }
	  });
	     
	// Add the graphical view mChart object into the Linear layout .
	     chart_container.addView(mChart);
	     
	     
	    }

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trend_chart);
		charttype = "PAIN";
    	patient_name = "Mays, Bill";
        OpenChart();
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trend_chart, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_trend_chart,
					container, false);
			return rootView;
		}
	}

}

