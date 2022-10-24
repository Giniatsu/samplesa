package com.example.elective2_garcia;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CoursesLVAdapter extends ArrayAdapter<DataModal> {
    public CoursesLVAdapter(@NonNull Context context, ArrayList<DataModal> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listitemView = convertView;
        if (listitemView == null){
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.items_cardview, parent, false);
        }

        DataModal dataModal = getItem(position);

        TextView pdname = listitemView.findViewById(R.id.pname);
        TextView pddesc = listitemView.findViewById(R.id.pdesc);
        TextView pdprice = listitemView.findViewById(R.id.pprice);
        ImageView pdimage = listitemView.findViewById(R.id.imgUrl);

        pdname.setText(dataModal.getName());
        pddesc.setText(dataModal.getDescription());
        pdprice.setText(dataModal.getPrice());

        Picasso.get().load(dataModal.getImgUrl().get(0)).into(pdimage);

        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Item clicked is : " + dataModal.getName(), Toast.LENGTH_SHORT).show();
                String productname = pdname.getText().toString();
                String productdesc = pddesc.getText().toString();
                String productprice = pdprice.getText().toString();
                String p1 = dataModal.getImgUrl().get(0);
                Log.d("T10000", p1);
                String p2 = dataModal.getImgUrl().get(1);
                Intent intent = new Intent(getContext(), ProductViewActivity2.class);
                intent.putExtra("name", productname);
                intent.putExtra("desc", productdesc);
                intent.putExtra("price", productprice);
                intent.putExtra("image1", p1);
                intent.putExtra("image2", p2);
                getContext().startActivity(intent);
            }
        });

        return listitemView;
    }
}
