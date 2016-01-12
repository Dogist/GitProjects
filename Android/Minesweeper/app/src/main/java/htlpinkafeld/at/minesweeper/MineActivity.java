package htlpinkafeld.at.minesweeper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import htlpinkafeld.at.minesweeper.pojo.MineField;
import htlpinkafeld.at.minesweeper.service.FieldServiceClass;
import htlpinkafeld.at.minesweeper.service.GridBoardAdapter;

public class MineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        FieldServiceClass.forceRecreate();

        GridView grid=(GridView)findViewById(R.id.game_grid);
        grid.setNumColumns(FieldServiceClass.getWidth());
        GridBoardAdapter gba=new GridBoardAdapter(this);
        grid.setAdapter(gba);

        //Listener to uncover fields, which have no flag. Also handles winning and loosing
        grid.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MineField f = (MineField) parent.getItemAtPosition(position);
                if (!f.isFlagged()) {
                    FieldServiceClass.getFieldServiceClass().clearAround(position % FieldServiceClass.getWidth(), position / FieldServiceClass.getWidth());
                    ((BaseAdapter) parent.getAdapter()).notifyDataSetChanged();

                    if (f.isMine()) {
                        FieldServiceClass.getFieldServiceClass().setMinesCovered(false);
                        AlertDialog alertDialog = createGameEndDialog("You Lost");
                        alertDialog.show();
                    } else if (FieldServiceClass.getWidth() * FieldServiceClass.getHeight() - FieldServiceClass.getNumMines() == FieldServiceClass.getFieldServiceClass().getFieldsUncovered()) {
                        AlertDialog alertDialog = createGameEndDialog("You Won");
                        alertDialog.show();

                    }
                }
            }
        });
        //used to set or unset flags
        grid.setOnItemLongClickListener(new GridView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MineField f = (MineField) parent.getItemAtPosition(position);
                if (f.isCovered()) {
                    f.setFlagged(!f.isFlagged());
                    ((BaseAdapter) parent.getAdapter()).notifyDataSetChanged();
                    return true;
                }
                return false;
            }
        });

    }

    //creates the standard dialog at the end of the game
    private AlertDialog createGameEndDialog(String message){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(message);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "play again?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FieldServiceClass.getFieldServiceClass().reset();
                GridView grid = (GridView) findViewById(R.id.game_grid);
                ((BaseAdapter) grid.getAdapter()).notifyDataSetChanged();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "different settings?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((GridView)findViewById(R.id.game_grid)).setEnabled(false);
            }
        });
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                //((GridView)findViewById(R.id.game_grid)).setEnabled(false);
            }
        });
        return alertDialog;
    }
}
