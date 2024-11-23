package ir.servicea.adapter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import ir.servicea.app.G;

import java.util.ArrayList;

import ir.servicea.R;
import ir.servicea.model.StructTask;

public class AdapterNote extends ArrayAdapter<StructTask> {

    public AdapterNote(ArrayList<StructTask> array) {
        super(G.context, R.layout.adapter_notes, array);
    }



    private static class ViewHolder {
        ViewGroup layoutRoot;
        TextView name1;
        TextView name2;
        ImageView kindvehicle;
        TextView state;


        ViewHolder(View view) {
            name1 = view.findViewById(R.id.name1);
            name2 = view.findViewById(R.id.name2);
            kindvehicle = view.findViewById(R.id.img);
            state = view.findViewById(R.id.state);
            layoutRoot = view.findViewById(R.id.root);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                layoutRoot.setBackgroundColor(G.context.getResources().getColor(R.color.white));
            }
        }

        @SuppressLint("SetTextI18n")
        void fill(final StructTask item) {
            if (item.nam1.length() > 1)
                name1.setText(item.nam1);
            else
                name1.setText("  ...  ");
            if (item.nam2.length() > 1)
                name2.setText(item.nam2);
            else
                name2.setText("  ...  ");

            state.setText(item.id + "");


            layoutRoot.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

        }

    }


    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        StructTask item = getItem(position);
        if (convertView == null) {
            convertView = G.inflater.inflate(R.layout.adapter_notes, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.fill(item);

        return convertView;
    }


}
