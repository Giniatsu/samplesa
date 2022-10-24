package com.example.elective2_garcia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

        Picasso.get().load(dataModal.getImgUrl()).into(pdimage);

        /*listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductViewActivity pp = new ProductViewActivity();
                pp.openProductView(view);
            }
        });*/

        return listitemView;
    }
}
