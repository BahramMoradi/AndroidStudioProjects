package dk.dtu.spil.activities;



import dk.dtu.spil.*;
import dk.dtu.spil.activities.GameActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainPageActivity extends ActionBarActivity implements OnClickListener {

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		Button button=(Button)findViewById(R.id.start);
		button.setOnClickListener(this);
	}

	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spil_menu, menu);
		return true;
	}

	
	public boolean onOptionsItemSelected(MenuItem item) {
		return Helper.goToOptions(this, item);
	}

	
	public void onClick(View v) {
		Intent intent= new Intent(this,GameActivity.class);
		this.startActivity(intent);
		
	}
}
