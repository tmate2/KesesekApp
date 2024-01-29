package tm.kesesek;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    //TODO: activity_main-t megcsinálni!!!

    private RelativeLayout parent;
    private TravelsStack travelsStack;
    private TextView mrText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mrText = this.findViewById(R.id.mrTextField);
        mrText.setMovementMethod(new ScrollingMovementMethod());

        checkStack();

        parent = findViewById(R.id.parent_layout);
        Button mainGomb = findViewById(R.id.gomboc);

        mainGomb.setOnClickListener( x -> showAddNewItemWindow());
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private void showAddNewItemWindow(){
        View view = View.inflate(this, R.layout.add_new_item, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        PopupWindow popupWindow = new PopupWindow(view, width, height, false);
        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);

        LocalTime[] scheduledTime = new LocalTime[1];
        EditText scheduledTimeInput = view.findViewById(R.id.scheduledClockInput);
        scheduledTimeInput.setOnFocusChangeListener((x, hasFocus) -> {
            if (hasFocus) {
                timeDialog(view, scheduledTime, scheduledTimeInput);
                scheduledTimeInput.setOnClickListener(y -> timeDialog(view, scheduledTime, scheduledTimeInput));
            }
        });

        LocalTime[] actualTime = new LocalTime[1];
        EditText actualTimeInput = view.findViewById(R.id.actualClockInput);
        actualTimeInput.setOnFocusChangeListener((x, hasFocus) -> {
            if (hasFocus) {
                timeDialog(view, actualTime, actualTimeInput);
                actualTimeInput.setOnClickListener(y -> timeDialog(view, actualTime, actualTimeInput));
            }
        });

        LocalDate[] travelDate = new LocalDate[1];
        EditText dateTextField = view.findViewById(R.id.travelDateInput);
        dateTextField.setOnFocusChangeListener((x, hasFocus) -> {
            if (hasFocus) {
                dateDialog(view, travelDate, dateTextField);
                dateTextField.setOnClickListener(y -> dateDialog(view, travelDate, dateTextField));
            }
        });

        EditText flightText = view.findViewById(R.id.flightInfoInput);
        String[] flightInfo = new String[1];
        flightText.setOnFocusChangeListener((x, hasFocus) -> {
            if (hasFocus) {
                textDialog(view, flightInfo, flightText);
                flightText.setOnClickListener(y -> textDialog(view, flightInfo, flightText));
            }
        });

        Button add = view.findViewById(R.id.addBtn);
        add.setOnClickListener(x -> {
            if (scheduledTime[0] == null || actualTime[0] == null || travelDate[0] == null) {
                Toast.makeText(view.getContext(), R.string.toast_message, Toast.LENGTH_SHORT).show();
                return;
            }

            Switch sw = view.findViewById(R.id.nextDaySwitch);

            travelsStack.addNewTravel(new Travel(scheduledTime[0], actualTime[0], travelDate[0], sw.isChecked(), (flightInfo[0] != null ? flightInfo[0] : "") ));
            System.out.println(travelsStack);

            updateStack();
            popupWindow.dismiss();
        });

        Button close = view.findViewById(R.id.cancelBtn);
        close.setOnClickListener(x -> popupWindow.dismiss());

    }

    private void timeDialog(View view, LocalTime[] localTime, EditText editText) {
        boolean format24 = true;
        TimePickerDialog dialog = new TimePickerDialog(view.getContext(), android.R.style.Theme_Holo_Light_Dialog, (timePicker, hour, min) -> {
            localTime[0] = LocalTime.of(hour, min);
            String time = hour + ":" + (min < 10 ? ("0" + min) : min);
            editText.setText(time);
        }, LocalTime.now().getHour(), LocalTime.now().getMinute(), format24);

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    private void dateDialog(View view, LocalDate[] localDate, EditText editText) {
        DatePickerDialog dialog = new DatePickerDialog(view.getContext(), android.R.style.Theme_Holo_Light_Dialog, (datePicker, year, month, day) -> {
            localDate[0] = LocalDate.of(year, month + 1, day);
            String output = year + ". " + (month < 9 ? "0" + (month + 1) : (month + 1)) + ". " + (day < 9 ? "0" + day : day);
            editText.setText(output);
        }, LocalDate.now().getYear(), LocalDate.now().getMonth().getValue() - 1, LocalDate.now().getDayOfMonth());

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    //TODO: a dialog EditText-je kapjon egyből fókuszt + billentyűzetet
    private void textDialog(View view, String[] flight, EditText editText) {
        View viewDialog = View.inflate(view.getContext(), R.layout.text_dialog, null);
        EditText textInput = viewDialog.findViewById(R.id.textDialogInput);
        textInput.setText(editText.getText().toString());

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setView(viewDialog);
        builder.setPositiveButton(R.string.ok, (dialogInterface, i) -> {
            flight[0] = textInput.getText().toString();
            editText.setText(flight[0]);
            dialogInterface.dismiss();
        });
        builder.setNegativeButton(R.string.back, (dialogInterface, i) -> dialogInterface.dismiss());

        builder.show();
    }

    private void checkStack() {
        /*TODO: Megírni a fájlkezelést, amibe mentünk
         * Ha létezik már ilyen fájl, akkor beolvassunk belőle a vermet
         * Ha nem, akkor létrehozunk egyet.
         */
        if (2 == Math.random()*1) {
            // Feltöltés
            System.out.println("pass");
        } else {
            // Beolvasás

            //amíg nincs megírva rendesen:
            travelsStack = new TravelsStack();
        }
        updateStack();
    }

    private void updateStack() {
        /*TODO: Megírni az adat frissítéseket és megjeleníteni azokat
         * az activity_main-ben.
         */
        if (travelsStack.isEmpty())
            return;
        mrText.setText(travelsStack.toString());
    }

}
