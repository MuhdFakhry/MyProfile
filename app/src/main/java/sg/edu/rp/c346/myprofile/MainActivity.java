package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
    }

    @Override
    protected void onPause() {
        super.onPause();

        String strName = etName.getText().toString();
        float fltGPA = Float.parseFloat(etGPA.getText().toString());
        int gender = rgGender.getCheckedRadioButtonId();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("name", strName);
        prefEdit.putFloat("gpa", fltGPA);
        prefEdit.putInt("gender",gender);
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String displayName = prefs.getString("name", null);
        float displayGPA = prefs.getFloat("gpa", 0);
        int displayGender= prefs.getInt("gender", 0);
        etName.setText(displayName);
        etGPA.setText(Float.toString(displayGPA));
        rgGender.check(displayGender);
    }
}
