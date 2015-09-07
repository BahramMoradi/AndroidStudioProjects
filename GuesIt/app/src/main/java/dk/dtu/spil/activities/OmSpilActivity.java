package dk.dtu.spil.activities;


import dk.dtu.spil.helper.*;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class OmSpilActivity extends ActionBarActivity {

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.omspil);
	}

	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spil_menu, menu);
		return true;
	}

	
	public boolean onOptionsItemSelected(MenuItem item) {
		return Helper.goToOptions(this, item);
	}

}
