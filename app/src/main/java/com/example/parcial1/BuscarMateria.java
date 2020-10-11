package com.example.parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcial1.Repositories.SubjectRepository;

public class BuscarMateria extends AppCompatActivity {

    EditText txtIdSubject, txtSubjectName;
    Button btnSearch;
    SubjectRepository subjectRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_materia);
        txtIdSubject=(EditText)findViewById(R.id.txtIdSubject);
        txtSubjectName=(EditText)findViewById(R.id.txtSubjectName);
        //txtSubjectName.setEnabled(false);
        subjectRepository=new SubjectRepository(this);
        btnSearch=(Button)findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=subjectRepository.getSubjectById(txtIdSubject.getText().toString());
                if(res.getCount()<=0){
                    showToast("Materia no encontrada");
                    return;
                }else
                {
                    String[] subject=new String[2];
                    while(res.moveToNext()){
                        subject[0]=res.getString(0);
                        subject[1]=res.getString(1);
                    }
                    Intent intent= new Intent(BuscarMateria.this, ReportCard.class);
                    intent.putExtra("subject",subject);
                    startActivity(intent);
                }

            }
        });
    }
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
