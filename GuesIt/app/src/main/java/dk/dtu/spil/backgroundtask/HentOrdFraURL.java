package dk.dtu.spil.backgroundtask;

import dk.dtu.spil.logik.*;
import android.os.AsyncTask;

public class HentOrdFraURL extends AsyncTask<Galgelogik,Integer, Long> {

	
	protected Long doInBackground(Galgelogik... args) {
		Galgelogik logik=args[0];
		try {
			logik.hentOrdFraDr();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}

}
