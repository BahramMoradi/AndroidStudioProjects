package dk.dtu.spil.helper;

import dk.dtu.spil.activities.*;
import dk.dtu.spil.activities.GameActivity;
import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;




public class Helper {
	public static void showTosta(Activity activ,String msg){
		Toast.makeText(activ, msg, Toast.LENGTH_SHORT).show();
	}
	public static boolean goToOptions(Activity activ,MenuItem item){
		int id = item.getItemId();
		switch(id){
		
			
		case R.id.start_act:
			Toast.makeText(activ, "start blev valgt", Toast.LENGTH_SHORT).show();
			Intent mpa= new Intent(activ,MainPageActivity.class);
			activ.startActivity(mpa);
			return true;
			
		case R.id.spil_act:
			Toast.makeText(activ, "spil blev valgt", Toast.LENGTH_SHORT).show();
			Intent ga= new Intent(activ,GameActivity.class);
			activ.startActivity(ga);
			return true;
		case R.id.result_act:
			Toast.makeText(activ, "result blev valgt", Toast.LENGTH_SHORT).show();
			Intent ra= new Intent(activ,ResultActivity.class);
			activ.startActivity(ra);
			return true;
		case R.id.indstilling_act:
			Toast.makeText(activ, "Indstilling blev valgt", Toast.LENGTH_SHORT).show();
			Intent ia= new Intent(activ,IndstillingActivity.class);
			activ.startActivity(ia);
			return true;
		case R.id.help_act:
			Toast.makeText(activ, "help blev valgt", Toast.LENGTH_SHORT).show();
			Intent ha= new Intent(activ,HelpActivity.class);
			activ.startActivity(ha);
			return true;
		case R.id.omspil_act:
			Toast.makeText(activ, "om spil blev valgt", Toast.LENGTH_SHORT).show();
			Intent oma= new Intent(activ,OmSpilActivity.class);
			activ.startActivity(oma);
			return true;
		case R.id.exit_act:
			Toast.makeText(activ, "exit blev valgt", Toast.LENGTH_SHORT).show();
			activ.finish();
			return true;
		
			
		default:
			  
	          return activ.onOptionsItemSelected(item);
	          
		
		}
		
		
	}

}
