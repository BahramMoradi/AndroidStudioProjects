package statsborgerskab.dk.dip;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Test extends AppCompatActivity implements View.OnClickListener {
    private static final String QNR="qnr";
    private static final String QLIST="qList";
    private static final String ACTUAL_QUESTION="actualQuestion";
    private static final String CORRECT_OPTION="correctOption";
    private static final String NR_CORRECT="nrCorrect";
    private static final String NR_INCORRECT="nrIncorrect";
    private static final String DATABASE="database";
    private static final String Q = "q";
    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final String D = "D";
    private static final String CORRECT = "Rigtig";
    private int qnr = 1;
    private TextView statusTV = null;
    private TextView correctTV = null;
    private TextView wrongTV = null;
    private TextView timerTV = null;
    private TextView questionTV = null;
    private Button a = null;
    private Button b = null;
    private Button c = null;
    private Button d = null;
    private String testFile = null;
    private JSONObject qList = null;
    private JSONObject actualQuestion=null;
    private String correctOption =null;
    private  int nrCorrect=0;
    private int nrIncorrect=0;
    private String testTitle=null;
    private String correctText="Rigtig : \n%d";
    private String wrongText="Wrong : \n%d";

    private long startTime = 0L;
    private Handler customHandler = new Handler();
    private long timeInMilliseconds = 0L;
    private long elapsedTime = 0L;
    private long updatedTime = 0L;
    private int mins=0;
    private int secs=0;
    private String timeFormat="%02d:%02d";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_test);
        statusTV = (TextView) findViewById(R.id.statusTV);

        wrongTV=(TextView) findViewById(R.id.wrongStatusTV);
        timerTV= (TextView) findViewById(R.id.timerStatusTV);
        correctTV=(TextView) findViewById(R.id.correctStatusTV);
        questionTV = (TextView) findViewById(R.id.questionTV);
        a = (Button) findViewById(R.id.A);
        b = (Button) findViewById(R.id.B);
        c = (Button) findViewById(R.id.C);
        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);


        if (savedInstanceState != null) {
            try{
                testTitle=savedInstanceState.getString(Database.TEST_TITLE_KEY);
                qnr=savedInstanceState.getInt(QNR);
                nrCorrect=savedInstanceState.getInt(NR_CORRECT);
                nrIncorrect=savedInstanceState.getInt(NR_INCORRECT);
                testFile =savedInstanceState.getString(Database.TEST_KEY);
                elapsedTime =savedInstanceState.getLong(Database.TEST_ELAPSED_TIME);
            }catch(Exception e){
                showToast(e.toString());
            }

        }else{
            Bundle bundle = getIntent().getExtras();
            testFile = bundle.getString(Database.TEST_KEY);
            nrCorrect=0;
            nrIncorrect=0;
            qnr=1;
        }
        readJsonFile();
        setQuestion();

        //*** timer
        startTimer();


    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt(QNR, qnr);
        savedInstanceState.putInt(NR_CORRECT, nrCorrect);
        savedInstanceState.putInt(NR_INCORRECT, nrIncorrect);
        savedInstanceState.putString(Database.TEST_KEY, testFile);
        savedInstanceState.putString(Database.TEST_TITLE_KEY, testTitle);
        savedInstanceState.putLong(Database.TEST_ELAPSED_TIME, updatedTime);



        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        qnr = savedInstanceState.getInt(QNR);
        nrCorrect= savedInstanceState.getInt(NR_CORRECT);
        nrIncorrect=savedInstanceState.getInt(NR_INCORRECT);
        testFile =savedInstanceState.getString(Database.TEST_KEY);
        testTitle=savedInstanceState.getString(Database.TEST_TITLE_KEY);
        elapsedTime =savedInstanceState.getLong(Database.TEST_ELAPSED_TIME);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int option=item.getItemId();
        Intent intent=null;
        switch(option){
            case R.id.MenuStartPage:
                intent=new Intent(this,StartActvity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.MenuExercisePage:
                intent=new Intent(this,ChooseExerciseActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.MenuTestPage:
                intent=new Intent(this,ChooseTestActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


    public void onClick(View v) {
        String idTag=getResources().getResourceName(v.getId());
        String []idParts=idTag.split("/");
        String choosenOption=idParts[1];
        if (choosenOption.equalsIgnoreCase(correctOption)){
            nrCorrect++;

        }else{
            nrIncorrect++;
            showToast("Forkert");
        }
        qnr++;
        setQuestion();

    }

    public void readJsonFile() {
        try {
            AssetManager manager = getAssets();
            InputStream in = manager.open(testFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder content = new StringBuilder();
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                content.append(nextLine);
            }
            //showToast(content.toString());
            reader.close();
             qList = (JSONObject) new JSONTokener(content.toString()).nextValue();
             testTitle=qList.getString(Database.TEST_TITLE_KEY);


        } catch (Exception e) {
            Log.d("Exception", e.getMessage());
            showToast("Exception : " + e.toString());
        }
    }

    public void setQuestion() {
        //statusTV.setText(String.format(status,nrCorrect,nrIncorrect));
        correctTV.setText(String.format(correctText,nrCorrect));
        wrongTV.setText(String.format(wrongText,nrIncorrect));
        // way qList.length()-1  because i have add title to question list..
        if (qnr > (qList.length()-1)) {
            JSONObject info=new JSONObject();
            try {
                info.put(Database.NUMBER_OF_WRONG_ANSWER,nrIncorrect);
                info.put(Database.NUMBER_OF_CORRECT_ANSWER,nrCorrect);
            } catch (JSONException e) {
                e.printStackTrace();
            }




             SharedPreferences sharedPref =this.getPreferences(Context.MODE_PRIVATE);
             SharedPreferences.Editor editor = sharedPref.edit();
             editor.putString(testTitle,info.toString());
             editor.commit();
            Intent intent=new Intent(this,ShowResultActivity.class);
            try {
                testTitle=qList.getString(Database.TEST_TITLE_KEY);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            intent.putExtra(Database.TEST_TITLE_KEY,testTitle);
            intent.putExtra(Database.NUMBER_OF_CORRECT_ANSWER,nrCorrect);
            intent.putExtra(Database.NUMBER_OF_WRONG_ANSWER,nrIncorrect);
            intent.putExtra(Database.TEST_ELAPSED_TIME, String.format(timeFormat, mins, secs));
            startActivity(intent);
            finish();

            qnr = 1;
            nrCorrect=0;
            nrIncorrect=0;
            stopTimer();
        } else {
            try {
                actualQuestion = qList.getJSONObject(Integer.toString(qnr));
                questionTV.setText(actualQuestion.getString(Q));
                questionTV.startAnimation(AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left));
                correctOption =actualQuestion.getString(CORRECT);
                setOptions(a, A);
                setOptions(b,B);
                setOptions(c,C);




            } catch (Exception e) {
                showToast(e.toString());
            }
        }
    }

    public void setOptions(Button button, String option) {
        try {
            if (actualQuestion.has(option)) {
                button.setText(actualQuestion.getString(option));
                button.setVisibility(View.VISIBLE);
                button.startAnimation(AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left));
            } else {
                button.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            showToast(e.toString());
        }

    }


    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void startTimer(){
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);

    }
    private void stopTimer(){
        elapsedTime += timeInMilliseconds;
        customHandler.removeCallbacks(updateTimerThread);
    }
    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = elapsedTime + timeInMilliseconds;
            secs = (int) (updatedTime / 1000);
            mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timerTV.setText(String.format(timeFormat, mins,secs) );
                    customHandler.postDelayed(this, 1000);
        }

    };


}

