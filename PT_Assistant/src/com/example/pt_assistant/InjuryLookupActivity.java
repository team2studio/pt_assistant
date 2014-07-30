package com.example.pt_assistant;

import java.util.List;
import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class InjuryLookupActivity extends ActionBarActivity {
	SimpleAdapter simpleAdpt;
	Patient_Notes pn;
	Patient p;
	private static final String INJURY_NAME = "injuryName";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_injury_lookup);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.injury_lookup, menu);
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
	
	void fillInjuryList(List<Map> injuryList) {
		// We get the ListView component from the layout
		ListView lv = (ListView) findViewById(R.id.injuryList);

		// TODO should make a two column list view this is one column
		simpleAdpt = new SimpleAdapter(this,
				(List<? extends Map<String, ?>>) injuryList,
				android.R.layout.simple_list_item_1,
				new String[] { INJURY_NAME }, new int[] { android.R.id.text1 });
//not sure what goes here yet so I left text1 in, still figuring some of this out
		lv.setAdapter(simpleAdpt);
		// React to user clicks on item
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parentAdapter, View view,
					int position, long id) {

				// We know the View is a TextView so we can cast it
				TextView clickedView = (TextView) view;

				Toast.makeText(
						InjuryLookupActivity.this,
						"Item with id [" + id + "] - Position [" + position
								+ "] - Injury [" + clickedView.getText() + "]",
						Toast.LENGTH_SHORT).show();

			}
		});
		registerForContextMenu(lv);

	}
	
	public void backObjective(View view) {

		//receive serialized patient and notes objects from previous activity
		p = (Patient)getIntent().getSerializableExtra("PatientObject");
		pn = (Patient_Notes)getIntent().getSerializableExtra("PatientNotesObject");
				
		//Serialize, start next activity and send intent
		Intent intent = new Intent(this, ObjectiveNotesActivity.class);
		intent.putExtra("PatientObject", p);
		intent.putExtra("PatientNotesObject", pn);
		startActivity(intent);
		
	}
	
}
