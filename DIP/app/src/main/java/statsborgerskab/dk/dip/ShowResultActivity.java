package statsborgerskab.dk.dip;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ShowResultActivity extends AppCompatActivity {
    private TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
    private LinearLayout mainLayout=null;
    private Bundle bundle=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_show_result);
        bundle = getIntent().getExtras();
        ScrollView scrollView=new ScrollView(this);
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        createTableLayout();
        scrollView.addView(mainLayout);
        setContentView(scrollView);


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
    public void createTableLayout(){
        if (bundle!=null) {
            String testTitle=bundle.getString(Database.TEST_TITLE_KEY);
            String elapsedTime=bundle.getString(Database.TEST_ELAPSED_TIME);
            int nrCorrect=bundle.getInt(Database.NUMBER_OF_CORRECT_ANSWER);
            int nrWrong= bundle.getInt(Database.NUMBER_OF_WRONG_ANSWER);

            TextView tv = new TextView(this);
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            tv.setText(testTitle);
            tv.setPadding(5, 5, 5, 5);
            tv.setGravity(Gravity.CENTER);
            mainLayout.addView(tv);
            TableLayout table = new TableLayout(this);
            TableRow header = new TableRow(this);

            TextView dateTime = new TextView(this);
            dateTime.setLayoutParams(params);
            dateTime.setText("Tid");
            TextView correct = new TextView(this);
            correct.setLayoutParams(params);
            correct.setText("Rigtig");
            TextView wrong = new TextView(this);
            wrong.setLayoutParams(params);
            wrong.setText("forkert");
            TextView procent = new TextView(this);
            procent.setLayoutParams(params);
            procent.setText("%");
            header.addView(dateTime);
            header.addView(correct);
            header.addView(wrong);
            header.addView(procent);
            table.addView(header);
            table.addView(createTableRow(elapsedTime,nrCorrect,nrWrong));
            table.setPadding(5, 0, 5, 0);
            mainLayout.addView(table);
        }


    }
    private  TableRow createTableRow(String time,int correct,int wrong ){
        TableRow row = new TableRow(this);
        TextView dateTime = new TextView(this);
        dateTime.setLayoutParams(params);

        TextView correctTV = new TextView(this);
        correctTV.setLayoutParams(params);
        correctTV.setText(Integer.toString(correct));
        TextView wrongTV = new TextView(this);
        wrongTV.setLayoutParams(params);
        wrongTV.setText(Integer.toString(wrong));
        TextView procentTV = new TextView(this);
        procentTV.setLayoutParams(params);

        if(time!=null){dateTime.setText(time);}
        if (correct!=0 || wrong !=0){
            int total=correct+wrong;
            float procent=((float)correct/(float)total)*100;
            procentTV.setText(String.format("%.2f",procent)+" %");

        }




        row.addView(dateTime);
        row.addView(correctTV);
        row.addView(wrongTV);
        row.addView(procentTV);

    return  row;
    }

}
