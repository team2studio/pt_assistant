package com.example.pt_assistant;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;





// import com.example.listview1.CreateChart;
import com.example.pt_assistant.TrendChartActivity;
// import com.example.pt_assistant.R;





import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.os.Build;

// imports from Bill's GUI code


import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;
import android.os.Build;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;

public class TrendChartGUI extends ActionBarActivity {
	
	TextView tvCumulativeChangeValue, tvProgressiveChangeVaue, tvHeader;
	ImageButton btnCreateCharts;
	EditText dtCustomStartDate, dtCustomEndDate, dtPatID;
	String MetricType, CurrentDate, CustomStartDate, CustomEndDate, InjuryType;
	ListView lvStandardDate, lvSelected;
	Button btSave, btPrint, btDone, btPatID;
	RadioButton rbIndexscore, rbROM, rbPain, rbStrength;
	RadioGroup rgMetric, rgInjuryType;
	String patID;
	Patient p;
	Patient_Notes pn;
	Metrics m;

	DecimalFormat formatter = new DecimalFormat("#0.0");
	
	//jamels onCreate method
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trend_chart_gui);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		//display header information
	    tvHeader = (TextView)this.findViewById(R.id.tvHeader);
	    tvHeader.setText("             TREND CHART MAIN MENU"); 
	}
	
	//added by Jamel
	public void getTrendReport(View view) {
		p = new Patient();
		pn = new Patient_Notes();
		m = new Metrics();
		EditText eText;
		
		//grab patient id entered by user
		eText = (EditText)this.findViewById(R.id.dtpatid);
		p.setPatientID(Integer.parseInt(eText.getText().toString()));
		//patID = eText.getText().toString();
		
		//grab injury selected by user
		rgInjuryType = (RadioGroup)this.findViewById(R.id.rgInjuryType);
		
		//determine what the user selected
		switch (rgInjuryType.getCheckedRadioButtonId()) {
		case R.id.rbLowerBack:
			InjuryType = "LUMBAR STRAIN";
			pn.setInjury(InjuryType);
			break;
		case R.id.rbArm:
			InjuryType = "ROTATOR CUFF TEAR";
			pn.setInjury(InjuryType);
			break;
		case R.id.rbACL:
			InjuryType = "ACL TEAR";
			pn.setInjury(InjuryType);
			break;
		}
		
		//grab specific metric selected by user
		rgMetric = (RadioGroup)this.findViewById(R.id.rgMetric);
		switch (rgMetric.getCheckedRadioButtonId()) {
		case R.id.rbStrength:
			MetricType = "STRENGTH";
			m.setSpecificMetric(MetricType);
			break;
		case R.id.rbPain:
			MetricType = "PAIN";
			m.setSpecificMetric(MetricType);
			break;
		case R.id.rbROM:
			MetricType = "ROM";
			m.setSpecificMetric(MetricType);
			break;
		}
		
		//Serialize, start next activity and send intent
		Intent intent = new Intent(this, TrendChartActivity.class);
		intent.putExtra("PatientObject", p);
		intent.putExtra("PatientNotesObject", pn);
		intent.putExtra("SpecificMetricObject", m);
		startActivity(intent);
	}
	
	
	/*
	@Override
	protected void 
	onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trend_chart_gui);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
        // code for trend chart main gui
		// display header with patient, current date, injury, therapist info 
        
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
 	   //get current date time with Date()
 	   Date date = new Date();
 	   CurrentDate = dateFormat.format(date);
        
      tvHeader = (TextView)this.findViewById(R.id.tvHeader);
      tvHeader.setText("             TREND CHART MAIN MENU"); 
      
      tvCumulativeChangeValue = (TextView)this.findViewById(R.id.tvCumualtiveChangeValue);
	  tvProgressiveChangeVaue = (TextView)this.findViewById(R.id.tvProgressiveChangeValue);
	  
	  //btPatID = (Button)this.findViewById(R.id.btpatid);
	  
	  //find the edit text
	  dtPatID = (EditText)this.findViewById(R.id.dtpatid);
	  
	  btnCreateCharts = (ImageButton)this.findViewById(R.id.btnCreateChart);
	  
	//  rbROM = (RadioButton)this.findViewById(R.id.rbROM);
	  rgMetric = (RadioGroup)this.findViewById(R.id.rgMetric);
	  rgInjuryType = (RadioGroup)this.findViewById(R.id.rgInjuryType);
	  
	  btnCreateCharts.setOnClickListener(new OnClickListener() {
		//display graph for appropriate metric in chart area 

		@Override
		//display values for cumulative and progressive change below chart 
		public void onClick(View v) {
			// CreateChart cc = new CreateChart();
			
		//	MainActivity.this.startActivity(cc.execute(MainActivity.this));
			
		//	tvCumulativeChangeValue.setText(" "+ String.valueOf(formatter.format(CreateChart.getCumulativeChangeValue()))+"%");
		//	tvProgressiveChangeVaue.setText(" " + String.valueOf(formatter.format(CreateChart.getProgressiveChangeValue()))+"%");
		//	android.app.Dialog dlg = new android.app.Dialog(MainActivity.this); // XXX Testing purposes only
			
		// get patient database query criteria for Metric and Injury Type
			
			switch (rgMetric.getCheckedRadioButtonId()) {
			case R.id.rbStrength:
			//	dlg.setTitle("0");
				MetricType = "STRENGTH";
				break;
			case R.id.rbPain:
			//	dlg.setTitle("1");
				MetricType = "PAIN";
				break;
			case R.id.rbROM:
			//	dlg.setTitle("2");
				MetricType = "ROM";
				break;
			//	default:
			//		dlg.setTitle(rgMetric.getCheckedRadioButtonId());
			//		int num = rgMetric.getCheckedRadioButtonId();
			//		break;
			}
			
			switch (rgInjuryType.getCheckedRadioButtonId()) {
			case R.id.rbLowerBack:
			//	dlg.setTitle("0");
				InjuryType = "Lower_Back";
				break;
			case R.id.rbArm:
			//	dlg.setTitle("1");
				InjuryType = "Arm";
				break;
						//	default:
			//		dlg.setTitle(rgMetric.getCheckedRadioButtonId());
			//		int num = rgMetric.getCheckedRadioButtonId();
			//		break;
			}
			//	dlg.show(); // XXX
			
			// call report method to retrieve data from back-end system
			
			// call create chart method to display trend-chart using patient data
			
		}

	  });
	  
	  btPatID.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			patID = dtPatID.getText().toString();
		}
		  
	  });
		
	}
*/
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trend_chart_gui, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_trend_chart_gui,
					container, false);
			return rootView;
		}
	}

}
