package tm.kesesek;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {


    RelativeLayout parent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.parent_layout);
        Button mainGomb = findViewById(R.id.gomboc);
        mainGomb.setOnClickListener( x -> {
            showPopupWindow();
        });
    }


    private void showPopupWindow(){
        View view = View.inflate(this, R.layout.add_new_item, null);
        Button close = view.findViewById(R.id.cancelBtn);
        Button add = view.findViewById(R.id.addBtn);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        PopupWindow popupWindow = new PopupWindow(view, width, height, false);

        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);

        close.setOnClickListener(x -> {
            popupWindow.dismiss();
        });

        add.setOnClickListener(x -> {
            popupWindow.dismiss();
        });
    }

}