package com.andrewogren.pottytalk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;


public class ProfileFragment extends Fragment implements Button.OnClickListener, AlertDialog.OnClickListener {

    private static final int NAME = 0;
    private static final int SCHOOL = 1;

    private EditText nameInput;
    private EditText schoolInput;

    private static AlertDialog nameDialog;
    private static AlertDialog schoolDialog;

    private TextView name;
    private TextView school;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference database;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_profile, container, false);
        setRetainInstance(true);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance().getReference();

        // set up buttons
        Button editName = ret.findViewById(R.id.editName);
        editName.setOnClickListener(this);
        Button editSchool = ret.findViewById(R.id.editSchool);
        editSchool.setOnClickListener(this);

        SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        // set up textviews
        name = ret.findViewById(R.id.fullName);
        String savedName = prefs.getString("name","No Name Saved");
        name.setText("Name: "+savedName);

        school = ret.findViewById(R.id.schoolName);
        String savedSchool = prefs.getString("school", "No School Saved");
        school.setText("School: "+savedSchool);

        return ret;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (dialog == null || which != -1) { return; }

        Dialog dialog2 = Dialog.class.cast(dialog);

        SharedPreferences.Editor prefs = getActivity().getPreferences(Context.MODE_PRIVATE).edit();
        String userId = firebaseUser.getUid();

        if (dialog2.equals(nameDialog)) {
            String name = nameInput.getText().toString();
            prefs.putString("name", name);
            this.name.setText("Name: "+name);
            database.child("users").child(userId).child("name").setValue(name);

        } else if (dialog2.equals(schoolDialog)) {
            String school = schoolInput.getText().toString();
            prefs.putString("school", school);
            this.school.setText("School: "+school);
            database.child("users").child(userId).child("school").setValue(school);
        }
        prefs.apply();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.editName) {
            nameDialog = displayAlertDialog("Full Name", "Please enter your full name.", NAME,
                    InputType
                    .TYPE_CLASS_TEXT);
            nameDialog.show();
        } else if (v.getId() == R.id.editSchool) {
            schoolDialog = displayAlertDialog("School Name","What school do you attend?", SCHOOL,
                    InputType
                    .TYPE_CLASS_TEXT);
            schoolDialog.show();
        }
    }


    private AlertDialog displayAlertDialog(String title, String hint, int id, int inputType) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(title);
        if (id == NAME) {
            nameInput = new EditText(getActivity());
            nameInput.setId(id);
            nameInput.setRawInputType(inputType);
            nameInput.setInputType(inputType);
            nameInput.setHint(hint);
            dialog.setView(nameInput);
        } else if (id == SCHOOL) {
            schoolInput = new EditText(getActivity());
            schoolInput.setId(id);
            schoolInput.setRawInputType(inputType);
            schoolInput.setInputType(inputType);
            schoolInput.setHint(hint);
            dialog.setView(schoolInput);
        }

        dialog.setPositiveButton("OK", this);
        dialog.setNegativeButton("CANCEL", this);
        return dialog.create();
    }
}
