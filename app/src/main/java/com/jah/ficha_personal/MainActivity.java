package com.jah.ficha_personal;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnNewReg, btnName, btnCourse, btnLanguages;
    TextView lblName, lblCourse, lblLanguages;
    AlertDialog.Builder dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnNewReg = findViewById(R.id.btnNewReg);
        btnName = findViewById(R.id.btnName);
        btnCourse = findViewById(R.id.btnCourse);
        btnLanguages = findViewById(R.id.btnLanguages);
        lblName = findViewById(R.id.lblName);
        lblCourse = findViewById(R.id.lblCourse);
        lblLanguages = findViewById(R.id.lblLanguages);

        btnNewReg.setOnClickListener(view -> createDialogReg());
        btnName.setOnClickListener(view -> createDialogName());
        btnCourse.setOnClickListener(view -> createDialogCourse());
        btnLanguages.setOnClickListener(view -> createDialogLanguages());

    }

    private void createDialogLanguages() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        CheckBox cbJava = new CheckBox(this);
        cbJava.setText(R.string.btnLanguageDialogcb1);
        CheckBox cbJS = new CheckBox(this);
        cbJS.setText(R.string.btnLanguageDialogcb2);
        CheckBox cbCSharp = new CheckBox(this);
        cbCSharp.setText(R.string.btnLanguageDialogcb3);
        CheckBox cbKotlin = new CheckBox(this);
        cbKotlin.setText(R.string.btnLanguageDialogcb4);
        CheckBox cbPhyton = new CheckBox(this);
        cbPhyton.setText(R.string.btnLanguageDialogcb5);


        layout.addView(cbJava);
        layout.addView(cbJS);
        layout.addView(cbCSharp);
        layout.addView(cbKotlin);
        layout.addView(cbPhyton);
        dialog = new AlertDialog.Builder(this);

        dialog.setCancelable(false)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle(R.string.btnLanguage)
                .setView(layout)
                .setNeutralButton(R.string.btnDialogNeutral, (dialogInterface, i) -> {
                    cbJava.setChecked(false);
                    cbJS.setChecked(false);
                    cbCSharp.setChecked(false);
                    cbKotlin.setChecked(false);
                    cbPhyton.setChecked(false);
                })
                .setNegativeButton(R.string.btnDialogNegative, null)
                .setPositiveButton(R.string.btnDialogPositive, (dialogInterface, i) -> {
                    String str = "";
                    if(cbJava.isChecked())
                        str += cbJava.getText().toString() + "\n";
                    if(cbJS.isChecked())
                        str += cbJS.getText().toString() + "\n";
                    if(cbCSharp.isChecked())
                        str += cbCSharp.getText().toString() + "\n";
                    if(cbKotlin.isChecked())
                        str += cbKotlin.getText().toString() + "\n";
                    if(cbPhyton.isChecked())
                        str += cbPhyton.getText().toString() + "\n";

                    lblLanguages.setText(str);
                })
                .create()
                .show();
    }

    private void createDialogCourse() {
        RadioGroup rgCourse = new RadioGroup(this);
        RadioButton rbDAM = new RadioButton(this);
        rbDAM.setText(R.string.btnCourseDialogButtons1);
        RadioButton rbDAW= new RadioButton(this);
        rbDAW.setText(R.string.btnCourseDialogButtons2);
        RadioButton rbASIR = new RadioButton(this);
        rbASIR.setText(R.string.btnCourseDialogButtons3);

        rgCourse.addView(rbDAM);
        rgCourse.addView(rbDAW);
        rgCourse.addView(rbASIR);

        dialog = new AlertDialog.Builder(this);

        dialog.setCancelable(false)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle(R.string.btnCourseDialog)
                .setView(rgCourse)
                .setNegativeButton(R.string.btnDialogNegative, null)
                .setPositiveButton(R.string.btnDialogPositive, (dialogInterface, i) -> {
                    int selectedId = rgCourse.getCheckedRadioButtonId();
                    RadioButton selected = rgCourse.findViewById(selectedId);

                    String selectedText = selected.getText().toString();
                    lblCourse.setText(selectedText);
                })
                .create()
                .show();
    }



    private void createDialogName() {
        EditText txtName = new EditText(this);
        txtName.setHint(R.string.btnName);
        txtName.setTextSize(18);

        dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle(R.string.btnNombreDialog)
                .setView(txtName)
                .setNegativeButton(R.string.btnDialogNegative, null)
                .setPositiveButton(R.string.btnDialogPositive, (dialogInterface, i) -> setLblName(txtName))
                .create()
                .show();

    }

    private void setLblName(View txtName) {
        txtName = (EditText) txtName;
        lblName.setText(((EditText) txtName).getText().toString());
    }

    private void createDialogReg() {
        dialog = new AlertDialog.Builder(this);

        dialog.setCancelable(false)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle(R.string.btnRegistration)
                .setMessage(R.string.btnInscriptionText)
                .setNegativeButton(R.string.btnInscriptionNo, null)
                .setPositiveButton(R.string.btnInscriptionYes, (dialogInterface, i) -> newReg())
                .create()
                .show();
    }

    private void newReg() {
        lblName.setText("");
        lblCourse.setText("");
        lblLanguages.setText("");
    }
}