package com.example.elective2_garcia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth _auth;
    private FirebaseFirestore db;
    ListView coursesLV;
    ArrayList<DataModal> dataModalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coursesLV = findViewById(R.id.idLVCourses);
        dataModalArrayList = new ArrayList<>();

        _auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        loadDatainListview();
    }

    private void loadDatainListview(){
        db.collection("items").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d: list){
                                DataModal dataModal = d.toObject(DataModal.class);
                                dataModalArrayList.add(dataModal);
                            }
                            CoursesLVAdapter adapter = new CoursesLVAdapter(MainActivity.this, dataModalArrayList);
                            coursesLV.setAdapter(adapter);
                        }else{
                            Toast.makeText(MainActivity.this, "No Data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Failed to load Data.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void logout(View v){
        _auth.signOut();
        finish();
    }

}
