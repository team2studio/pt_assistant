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
	private static final String JSON_PAT_NAME = "name";
	private static final String JSON_ROM = "range_of_motion";
	private static final String JSON_EVAL_DATE = "evaldate";
	private static final String JSON_INJURY_NAME = "injury_name";
	private static final String JSON_THERAPIST_NAME = "therapist_name";
	private static final String JSON_STRENGTH = "strength";
	private static final String JSON_PAIN = "pain";
	String KEY_PATNAME = "1";
	String KEY_ROM = "2";
	String KEY_EVAL_DATE = "3";
	String KEY_INURY_NAME = "4";
	String KEY_THERAPIST = "5";
	String KEY_STRENT_LVL = "6";
	String KEY_PAIN_LVL = "7";

	private static final String TAG_MESSAGE = "message";
	private static final String GET_INDEX_URL = "http://199.255.250.71/get_patient_trend_data.php";
	List<Map> patientTrendList;
	Map<String, String> patTrendmap;
	int pat_data[];

	// added by Jamel
	Patient p;
	Patient_Notes pn;
	Metrics m;
	private ProgressDialog pDialog;

	// First Create a Graphical View object called mChart.

	private GraphicalView mChart;

	private String[] mMonth = new String[] { "1/1/14", "2/3/14", "2/17/14",
			"3/2/14", "5/4/14", "6/12/14",

			"7/4/14", "7/21/14" };

	private String testMonths[];

	// Get report data from database using the query parameters

	public void get_patient_trend_data() { // For remote DB
		new doReportTrendAsyncTask().execute();

		// for (int i = 0; .)
		// Iterator it = patientTrendList.get(i)
	}

	// Async tasks are necessary for retrieving data from the remote DB. 
	// You cannot do this in the  activity window
	private class doReportTrendAsyncTask extends
			AsyncTask<String, Void, String> {

		JSONParser jsonParser = new JSONParser();
		String TAG_SUCCESS = "success";

		@Override
		protected String doInBackground(String... args) {

			report_async_get_trend_data();
			return "Executed";
		}

		@Override
		protected void onPostExecute(String result) {

			/*
			 * Iterator<Map> it = patientTrendList.iterator();
			 * 
			 * Map map = new HashMap();
			 * 
			 * while(it.hasNext()){
			 * 
			 * map = it.next();
			 * 
			 * Log.d("patient name: ", (String )map.get(KEY_PATNAME));
			 * Log.d("eval date: ", (String )map.get(KEY_EVAL_DATE));
			 * Log.d("rom: ", (String )map.get(KEY_ROM));
			 * Log.d("therapist name: ", (String )map.get(KEY_THERAPIST));
			 * Log.d("strength: ", (String )map.get(KEY_STRENT_LVL));
			 * Log.d("pain level: ", (String )map.get(KEY_PAIN_LVL));
			 * Log.d("injury name: ", (String )map.get(KEY_INURY_NAME));
			 * Log.d("record ", "next record"); }
			 */

			// call Bill's OpenChart method
			// OpenChart();
			// dismiss the dialog once product deleted

			/*
			 * if (pDialog != null) { if (pDialog.isShowing()) {
			 * pDialog.dismiss(); // creates the toast Context context =
			 * getApplicationContext(); CharSequence text =
			 * "Found the patients data!!"; int duration = Toast.LENGTH_SHORT;
			 * 
			 * Toast toast = Toast.makeText(context, text, duration);
			 * toast.show(); finish(); } } if (result != null) {
			 * Toast.makeText(TrendChartActivity.this, result,
			 * Toast.LENGTH_LONG).show(); }
			 */
			OpenChart();
		}

		@Override
		protected void onPreExecute() {
			/*
			 * super.onPreExecute(); pDialog = new
			 * ProgressDialog(TrendChartActivity.this);
			 * pDialog.setMessage("Attempting to find the patients data...");
			 * pDialog.setIndeterminate(false); pDialog.setCancelable(true);
			 * pDialog.show();
			 */
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}

		private String report_async_get_trend_data() {

			// get objects passed in
			// receive serialized patient and notes objects from previous
			// activity
			p = (Patient) getIntent().getSerializableExtra("PatientObject");
			pn = (Patient_Notes) getIntent().getSerializableExtra(
					"PatientNotesObject");

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("patient_id", Integer.toString(p
					.getPatientID())));
			params.add(new BasicNameValuePair("injuryName", pn.getInjury()));
			try {
				String temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8;
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(GET_INDEX_URL,
						"GET", params);
				// json success tag
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					patientTrendList = new ArrayList<Map>();
					JSONArray jsonPatTrendList = new JSONArray();
					jsonPatTrendList = json.getJSONArray("trend_data");

					for (int i = 0; i < jsonPatTrendList.length(); i++) {

						JSONObject c = jsonPatTrendList.getJSONObject(i);
						patTrendmap = new HashMap<String, String>();
						patTrendmap
								.put(KEY_PATNAME, c.getString(JSON_PAT_NAME));
						patTrendmap.put(KEY_ROM, c.getString(JSON_ROM));
						patTrendmap.put(KEY_EVAL_DATE,
								c.getString(JSON_EVAL_DATE));
						patTrendmap.put(KEY_INURY_NAME,
								c.getString(JSON_INJURY_NAME));
						patTrendmap.put(KEY_THERAPIST,
								c.getString(JSON_THERAPIST_NAME));
						patTrendmap.put(KEY_STRENT_LVL,
								c.getString(JSON_STRENGTH));
						patTrendmap.put(KEY_PAIN_LVL, c.getString(JSON_PAIN));
						patientTrendList.add(patTrendmap);
					}

					Log.d("Found the patient information!", json.toString());

					return json.getString(TAG_MESSAGE);
				} else {
					Log.d("Did not find the patient information!",
							json.getString(TAG_MESSAGE));
					return json.getString(TAG_MESSAGE);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;

		}

	}

	private void OpenChart() {
		Iterator<Map> it = patientTrendList.iterator();

		Map map = new HashMap();

		// Define the number of elements you want in the chart.
		int z[] = { 0, 1, 2, 3, 4, 5, 6, 7 };

		ArrayList<Integer> totalElements = new ArrayList<Integer>();

		// determine how many objects are in the patient trend list
		int anArray[];
		String anArray2[];
		// String testMonths[];
		int size = patientTrendList.size();
		anArray = new int[size];
		anArray2 = new String[size];
		testMonths = new String[size];
		ArrayList<String> storePainVals = new ArrayList<String>();
		ArrayList<String> storeMonthVals = new ArrayList<String>();
		ArrayList<String> storeROMVals = new ArrayList<String>();
		ArrayList<String> storeStrengthVals = new ArrayList<String>();

		while (it.hasNext()) {

			map = it.next();

			Log.d("patient name: ", (String) map.get(KEY_PATNAME));

			patient_name = (String) map.get(KEY_PATNAME);

			Log.d("eval date: ", (String) map.get(KEY_EVAL_DATE));
			storeMonthVals.add((String) map.get(KEY_EVAL_DATE));

			Log.d("rom: ", (String) map.get(KEY_ROM));
			storeROMVals.add((String) map.get(KEY_ROM));

			Log.d("therapist name: ", (String) map.get(KEY_THERAPIST));
			Log.d("strength: ", (String) map.get(KEY_STRENT_LVL));
			storeStrengthVals.add((String) map.get(KEY_STRENT_LVL));

			Log.d("pain level: ", (String) map.get(KEY_PAIN_LVL));
			storePainVals.add((String) map.get(KEY_PAIN_LVL)); // added by Jamel

			Log.d("injury name: ", (String) map.get(KEY_INURY_NAME));
			Log.d("record ", "next record");
		}

		String x[] = { "0", "0", "0", "0", "0", "0", "0", "0" };

		int xx[] = { 0, 0, 0, 0, 0, 0, 0, 0 };

		double cumulative_change;
		double prog_change;

		DecimalFormat formatter = new DecimalFormat("#0.0");

		cumulative_change = 0.1;
		prog_change = 0.1;

		// get metrics
		m = (Metrics) getIntent().getSerializableExtra("SpecificMetricObject");
		charttype = m.getSpecificMetric();
		if (charttype != null) {
			Log.d("Metrics object is not null!", "Chart type equals: "
					+ charttype);
		} else {
			Log.d("Metrics object is ", "null!!");
		}

		if (charttype.equalsIgnoreCase("PAIN")) {
			Log.d("WE MADE IT HERE!!", " WE ARE IN THE PAIN STATEMENT");
			for (int i = 0; i < anArray.length; i++) {
				anArray[i] = i;
			}
			/*
			 * z[0]=0; z[1]=1; z[2]=2; z[3]=3; z[4]=4; z[5]=5; z[6]=6; z[7]=7;
			 */

			for (int i = 0; i < anArray2.length; i++) {
				anArray2[i] = storePainVals.get(i).toString();
			}



			double Y1 = Double.parseDouble(anArray2[0]);
			double Y2 = Double.parseDouble(anArray2[anArray2.length - 1]);
			double Y3 = Double.parseDouble(anArray2[anArray2.length - 2]);

			cumulative_change = ((Y2 - Y1) / Y1) * 100;
			prog_change = ((Y2 - Y3) / Y3) * 100;

		} else if (charttype.equalsIgnoreCase("ROM")) {
			Log.d("WE MADE IT HERE!!", " WE ARE IN THE ROM STATEMENT");
			for (int i = 0; i < anArray.length; i++) {
				anArray[i] = i;
			}
			/*
			 * z[0]=0; z[1]=1; z[2]=2; z[3]=3; z[4]=4; z[5]=5; z[6]=6; z[7]=7;
			 */

			for (int i = 0; i < anArray2.length; i++) {
				anArray2[i] = storeROMVals.get(i).toString();
			}

			double Y1 = Double.parseDouble(anArray2[0]);
			double Y2 = Double.parseDouble(anArray2[anArray2.length - 1]);
			double Y3 = Double.parseDouble(anArray2[anArray2.length - 2]);

			cumulative_change = ((Y2 - Y1) / Y1) * 100;
			prog_change = ((Y2 - Y3) / Y3) * 100;
			// prog_change = 20.0;

		} else if (charttype.equalsIgnoreCase("STRENGTH")) {
			// int z2[]={0,1,2,3,4,5,6,7};
			Log.d("WE MADE IT HERE!!", " WE ARE IN THE STRENGTH STATEMENT");
			for (int i = 0; i < anArray.length; i++) {
				anArray[i] = i;
			}

			for (int i = 0; i < anArray2.length; i++) {
				anArray2[i] = storeStrengthVals.get(i).toString();
			}

			double Y1 = Double.parseDouble(anArray2[0]);
			double Y2 = Double.parseDouble(anArray2[anArray2.length - 1]);
			double Y3 = Double.parseDouble(anArray2[anArray2.length - 2]);

			cumulative_change = ((Y2 - Y1) / Y1) * 100;
			prog_change = ((Y2 - Y3) / Y3) * 100;

		}

		else {
			// do nothing
			Log.d("WE MADE IT HERE!!", " WE ARE IN THE DO NOTHING STATEMENT");
			cumulative_change = 0.1;
			prog_change = 0.1;
		}

		// Create XY Series for X Series.
		XYSeries xSeries = new XYSeries(charttype + " Index");


		// Adding data to the X Series.
		for (int i = 0; i < anArray.length; i++) {
			xSeries.add(anArray[i], Integer.parseInt(anArray2[i]));

		}

		// Create a Dataset to hold the XSeries.

		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

		// Add X series to the Dataset.
		dataset.addSeries(xSeries);

		// Create XYSeriesRenderer to customize XSeries

		XYSeriesRenderer Xrenderer = new XYSeriesRenderer();
		Xrenderer.setColor(Color.GREEN);
		Xrenderer.setPointStyle(PointStyle.DIAMOND);

		// added by Jamel
		// Xrenderer.setPointStrokeWidth(3);

		Xrenderer.setDisplayChartValues(true);
		Xrenderer.setLineWidth(2);
		Xrenderer.setFillPoints(true);

		// Create XYMultipleSeriesRenderer to customize the whole chart

		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		// Adding the XSeriesRenderer to the MultipleRenderer.
		mRenderer.addSeriesRenderer(Xrenderer);

		mRenderer.setChartTitle(charttype + " Trend Chart" + ":" + " "
				+ patient_name + "    Total: "
				+ formatter.format(cumulative_change) + "%" + "  Prog: "
				+ formatter.format(prog_change) + "%");
		mRenderer.setXTitle("Eval Date");
		mRenderer.setYTitle("Measurement");
		mRenderer.setZoomButtonsVisible(true);
		mRenderer.setXLabels(0);
		mRenderer.setPanEnabled(false);

		if (charttype.equalsIgnoreCase("STRENGTH")
				|| (charttype.equalsIgnoreCase("PAIN"))) {
			// jamel test
			mRenderer.setYAxisMin(0.0);
			mRenderer.setYAxisMax(10.0);
		} else if (charttype.equalsIgnoreCase("ROM")) {
			// jamel test
			mRenderer.setYAxisMin(0.0);
			mRenderer.setYAxisMax(180.0);
		}

		mRenderer.setShowGrid(true);

		mRenderer.setClickEnabled(true);

		// add months from arraylist object
		for (int i = 0; i < testMonths.length; i++) {
			testMonths[i] = storeMonthVals.get(i).toString();
		}

		/*
		 * for(int i=0;i<z.length;i++) { mRenderer.addXTextLabel(i, mMonth[i]);
		 * }
		 */

		for (int i = 0; i < anArray.length; i++) {
			mRenderer.addXTextLabel(i, testMonths[i]);
		}

		// Adding the XSeriesRenderer to the MultipleRenderer.
		// mRenderer.addSeriesRenderer(Xrenderer);

		LinearLayout chart_container = (LinearLayout) findViewById(R.id.Chart_layout);

		if (chart_container != null) {
			Log.d("chart_container is ", "not null!!");
		} else {
			Log.d("chart_container is ", "null!!");
		}

		// Creating an intent to plot line chart using dataset and
		// multipleRenderer

		mChart = (GraphicalView) ChartFactory.getLineChartView(this, dataset,
				mRenderer);

		if (mChart != null) {
			Log.d("mChart is ", "not null!!");
		} else {
			Log.d("mChart is ", "null!!");
		}

		// Adding click event to the Line Chart.

		mChart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				SeriesSelection series_selection = mChart
						.getCurrentSeriesAndPoint();

				if (series_selection != null) {
					int series_index = series_selection.getSeriesIndex();

					String select_series = charttype + " Index";
					if (series_index == 0) {
						select_series = charttype + " Index";
					} else {
						select_series = "Y Series";
					}

					// String month=mMonth[(int)series_selection.getXValue()];
					String month = testMonths[(int) series_selection
							.getXValue()];

					int amount = (int) series_selection.getValue();

					Toast.makeText(getBaseContext(),
							select_series + " in " + month + ": " + amount,
							Toast.LENGTH_LONG).show();
				}
			}
		});

		// Add the graphical view mChart object into the Linear layout .
		chart_container.addView(mChart);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_trend_chart);

		// test
		setContentView(R.layout.fragment_trend_chart);

		/*
		 * if (savedInstanceState == null) {
		 * getSupportFragmentManager().beginTransaction() .add(R.id.container,
		 * new PlaceholderFragment()).commit(); }
		 */

		// this works
		get_patient_trend_data();

		// charttype = "PAIN";
		// patient_name = "Mays, Bill";
		// OpenChart();
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
