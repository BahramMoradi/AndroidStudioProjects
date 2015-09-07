package dk.dtu.spil.activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import dk.dtu.spil.helper.Helper;
import dk.dtu.spil.logik.Galgelogik;

public class GameActivity extends ActionBarActivity implements OnClickListener {

	private Galgelogik logik = null;
	private Button button = null;
	private Button status_bt = null;
	private ImageView imgView = null;
	private TextView ord_tv = null;
	private TextView synligOrd = null;
	private TextView antalForkert = null;
	private TextView brugteBog = null;
	private EditText bog_et = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		imgView = (ImageView) findViewById(R.id.imgView);

		ord_tv = (TextView) findViewById(R.id.ord_tv);
		synligOrd = (TextView) findViewById(R.id.synligord_tv);
		antalForkert = (TextView) findViewById(R.id.antalForkert_tv);
		brugteBog = (TextView) findViewById(R.id.brugteBogstave_tv);
		bog_et = (EditText) findViewById(R.id.charEditText);

		status_bt = (Button) findViewById(R.id.status_bt);
		status_bt.setVisibility(View.INVISIBLE);
		status_bt.setOnClickListener(this);
		button = (Button) findViewById(R.id.ok);
		button.setOnClickListener(this);

		usePreference();
		logik = new Galgelogik();
		synligOrd.setText("Synligt ord      : " + logik.getSynligtOrd());

	}

	protected void onStart() {
		super.onStart();

	}

	protected void onResume() {
		super.onResume();
		usePreference();
	}

	protected void onPause() {
		super.onPause();
		usePreference();
	}

	protected void onStop() {
		super.onStop();
	}

	public void onDestroy() {
		super.onDestroy();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.spil_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		return Helper.goToOptions(this, item);

	}

	public void onClick(View v) {

		if (v.getId() == R.id.ok) {
			String ch = bog_et.getText().toString().trim();
			bog_et.setText("");

			logik.gætBogstav(ch);

			String brugt = logik.getBrugteBogstaverAsString();
			String ord = logik.getOrdet();
			String synlig = logik.getSynligtOrd();
			int forkert = logik.getAntalForkerteBogstaver();

			synligOrd.setText("Synligt ord      : " + synlig);
			brugteBog.setText("Brugte bogstaver : " + brugt);
			antalForkert.setText("Antal forkert    : "
					+ Integer.toString(forkert));

			switch (forkert) {
			case 0:
				imgView.setImageResource(R.drawable.galge);
				break;
			case 1:
				imgView.setImageResource(R.drawable.forkert1);
				break;
			case 2:
				imgView.setImageResource(R.drawable.forkert2);
				break;
			case 3:
				imgView.setImageResource(R.drawable.forkert3);
				break;
			case 4:
				imgView.setImageResource(R.drawable.forkert4);
				break;
			case 5:
				imgView.setImageResource(R.drawable.forkert5);
				break;
			case 6:
				imgView.setImageResource(R.drawable.forkert6);
				break;
			}

			if (logik.erSpilletTabt()) {
				status_bt.setText("Tabt : klik på mig for ny spil");
				status_bt.setVisibility(View.VISIBLE);
				status_bt.setBackgroundColor(Color.RED);
				ord_tv.setText("Rigtigt Ord : " + ord);

			}

			if (logik.erSpilletVundet()) {
				status_bt.setText("Vundet : klik på mig for ny spil");
				status_bt.setVisibility(View.VISIBLE);
				status_bt.setBackgroundColor(Color.GREEN);
				ord_tv.setText("Rigtigt Ord : " + ord);

			}
		} else {
			logik.nulstil();
			imgView.setImageResource(R.drawable.galge);
			status_bt.setVisibility(View.INVISIBLE);
			ord_tv.setText("");
			synligOrd.setText("Synligt ord     : " + logik.getSynligtOrd());
			brugteBog.setText("Brugte bogstaver: "
					+ logik.getBrugteBogstaverAsString());
			antalForkert.setText("Antal forkert   : "
					+ Integer.toString(logik.getAntalForkerteBogstaver()));
		}

	}

	public void usePreference() {
		SharedPreferences indst = PreferenceManager
				.getDefaultSharedPreferences(this);
		
		if (indst.getBoolean("gempoint", true)) {
			Helper.showTosta(this, "gempoint =true ");
			
			setProgressBarIndeterminateVisibility(true);
			new HentOrdFraURL().execute(logik);
			setProgressBarIndeterminateVisibility(false);
		}
		if (indst.getBoolean("lyd", true)) {
			Helper.showTosta(this, "lyd =true ");
		}
		if (indst.getBoolean("hentOrdFraDr", true)) {
			Helper.showTosta(this, "hentOrdFraDr =true ");
		}

	}

}
