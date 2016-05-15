package htlpinkafeld.at.minesweeper.gui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import htlpinkafeld.at.minesweeper.R;
import htlpinkafeld.at.minesweeper.util.DifficultyEnum;
import htlpinkafeld.at.minesweeper.service.FieldServiceClass;

public class MainActivity extends AppCompatActivity {
    private static Context context;

    private EditText mineNumberEText;
    private EditText heightEText;
    private EditText widthEText;
    private Button startButton;
    private Spinner diffSpinn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;

        mineNumberEText= (EditText) findViewById(R.id.mine_number_etext);
        heightEText=(EditText) findViewById(R.id.height_etext);
        widthEText=(EditText) findViewById(R.id.width_etext);
        startButton=(Button) findViewById(R.id.start_button);
        diffSpinn= (Spinner) findViewById(R.id.difficulty_spinn);

        DifficultyAdapter diffAdap=new DifficultyAdapter(this);
        diffSpinn.setAdapter(diffAdap);

        //Listener for settings spinner, for setting the textviews
        diffSpinn.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DifficultyEnum diff = DifficultyEnum.valueOf(String.valueOf(((TextView) view.findViewById(R.id.difficulty_entryitem)).getText()));
                int mines = diff.getNumMines();
                int height = diff.getHeight();
                int width = diff.getWidth();

                mineNumberEText.setText(Integer.toString(mines));
                heightEText.setText(Integer.toString(height));
                widthEText.setText(Integer.toString(width));

                if (diff != DifficultyEnum.SPECIAL) {
                    mineNumberEText.setEnabled(false);
                    heightEText.setEnabled(false);
                    widthEText.setEnabled(false);

                } else {
                    mineNumberEText.setEnabled(true);
                    heightEText.setEnabled(true);
                    widthEText.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //sets the action for the start button
        startButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                FieldServiceClass.setNumMines(Integer.parseInt(mineNumberEText.getText().toString()));
                FieldServiceClass.setHeight(Integer.parseInt(heightEText.getText().toString()));
                FieldServiceClass.setWidth(Integer.parseInt(widthEText.getText().toString()));
                if(FieldServiceClass.getNumMines()>0&&FieldServiceClass.getNumMines()<FieldServiceClass.getWidth()*FieldServiceClass.getHeight()){

                    Intent intent=new Intent(v.getContext(),MineActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public static Context getContext() {
        return context;
    }
}
