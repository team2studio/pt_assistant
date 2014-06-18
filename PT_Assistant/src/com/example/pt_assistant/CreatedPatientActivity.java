package com.example.pt_assistant;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CreatedPatientActivity extends ActionBarActivity     {
	/**
	 * 
	 */
	
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		  PT_SQLiteHelper pt_db =   new PT_SQLiteHelper(this);
		 super.onCreate(savedInstanceState);
		    Patient pat = new Patient();
		    // Get he message from the intent
		    Intent intent = getIntent();
		    pat = (Patient) intent.getSerializableExtra("Patient");
		    pt_db.addPatient(pat);
		    TextView textView = new TextView(this);
		    textView.setTextSize(20);
		    textView.setText(pat.getName());
		    textView.setText( "Name: " + pat.getName() + "\r\n"+
		    		          "IDD :" + pat.getPatientID() + "\r\n" +
		    		          "Injury ID: " + pat.getInjury());
		  
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.display_message, menu);
//		return true;
//	}

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
			View rootView = inflater.inflate(R.layout.fragment_display_message,
					container, false);
			return rootView;
		}
	}

}
