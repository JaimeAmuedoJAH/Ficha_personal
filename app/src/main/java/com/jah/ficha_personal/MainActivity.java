package com.jah.ficha_personal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button btnNewReg, btnName, btnCourse, btnLanguages;
    TextView lblName, lblCourse, lblLanguages;
    AlertDialog.Builder dialog;
    String java;
    String javaScript;
    String cSharp;
    String kotlin;
    String python;

    String[] listItems;
    boolean[] checkedItems;
    String[] listCourse;
    int[] checked;


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

        java = getString(R.string.btnLanguageDialogcb1);
        javaScript = getString(R.string.btnLanguageDialogcb2);
        cSharp = getString(R.string.btnLanguageDialogcb3);
        kotlin = getString(R.string.btnLanguageDialogcb4);
        python = getString(R.string.btnLanguageDialogcb5);
        listItems = new String[] {java, javaScript, cSharp, kotlin, python};
        checkedItems = new boolean[listItems.length];

        String DAM = getString(R.string.btnCourseDialogButtons1);
        String DAW = getString(R.string.btnCourseDialogButtons2);
        String ASIR = getString(R.string.btnCourseDialogButtons3);
        listCourse = new String[]{DAM, DAW, ASIR};
        checked = new int[]{-1};

        btnNewReg.setOnClickListener(view -> createDialogReg());
        btnName.setOnClickListener(view -> createDialogName());
        btnCourse.setOnClickListener(view -> createDialogCourse());
        btnLanguages.setOnClickListener(view -> createDialogLanguages());

        if (savedInstanceState != null) {
            String name = savedInstanceState.getString(getString(R.string.btnName));
            String course = savedInstanceState.getString(getString(R.string.btnCourse));
            String languages = savedInstanceState.getString(getString(R.string.btnLanguage));

            lblName.setText(name);
            lblCourse.setText(course);
            lblLanguages.setText(languages);
            checkedItems = savedInstanceState.getBooleanArray("checkedItems");
            listCourse = savedInstanceState.getStringArray("listCourse");
            checked = savedInstanceState.getIntArray("checked");
        }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(getString(R.string.btnName), lblName.getText().toString());
        outState.putString(getString(R.string.btnCourse), lblCourse.getText().toString());
        outState.putString(getString(R.string.btnLanguage), lblLanguages.getText().toString());

        outState.putBooleanArray("checkedItems", checkedItems);
        outState.putStringArray("listCourse", listCourse);
        outState.putIntArray("checked", checked);
    }

    private void createDialogLanguages() {

        dialog = new AlertDialog.Builder(this);

        dialog.setCancelable(false)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle(R.string.btnLanguage)
                .setMultiChoiceItems(listItems, checkedItems, (dialog, which, isChecked) -> {
                    checkedItems[which] = isChecked;
                })
                .setNeutralButton(R.string.btnDialogNeutral, (dialogInterface, i) -> {
                    Arrays.fill(checkedItems, false);
                    lblLanguages.setText("");
                })
                .setNegativeButton(R.string.btnDialogNegative, (dialogInterface, i) -> {
                    for(int ind = 0;ind < checkedItems.length;ind++){
                        if(lblLanguages.getText().toString().contains(listItems[ind]))
                            checkedItems[ind] = true;
                        else
                            checkedItems[ind] = false;
                    }
                })
                .setPositiveButton(R.string.btnDialogPositive, (dialogInterface, i) -> {
                    String str = "";
                    for (int ind = 0; ind < checkedItems.length; ind++) {
                        if (checkedItems[ind]) {
                            str += listItems[ind] + "\n";
                        }
                    }
                    lblLanguages.setText(str);
                })
                .create()
                .show();
    }

    private void createDialogCourse() {
        dialog = new AlertDialog.Builder(this);

        dialog.setCancelable(false)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle(R.string.btnCourseDialog)
                .setSingleChoiceItems(listCourse, checked[0], (dialog, which) -> {
                    checked[0] = which;
                })
                .setNegativeButton(R.string.btnDialogNegative, null)
                .setPositiveButton(R.string.btnDialogPositive, (dialogInterface, i) -> {

                    String selectedText = listCourse[checked[0]];
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