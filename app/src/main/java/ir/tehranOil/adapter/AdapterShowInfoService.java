package ir.tehranOil.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import ir.tehranOil.app.G;

import java.util.List;

import ir.servicea.R;
import ir.tehranOil.model.ModelListCustomer;

public class AdapterShowInfoService extends RecyclerView.Adapter<AdapterShowInfoService.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<ModelListCustomer> models;

    public AdapterShowInfoService(Context context, List<ModelListCustomer> models) {
        this.context = context;
        this.models = models;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_list_customer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        holder.txt_name_customer.setText(models.get(position).getNameCustomer().toString());
        holder.txt_name_car.setText(models.get(position).getNameCar().toString());
        holder.txt_phone_customer.setText(models.get(position).getPhone().toString());
        String plak = (models.get(position).getPlak().toString() + "").replace(" ", "").replace("null", "");
        if (plak.length() > 3) {
            holder.plaks.setVisibility(View.VISIBLE);
            String c1 = plak.substring(0, 2);
            String c2 = plak.substring(2, plak.length() - 3);
            String c3 = plak.substring(plak.length() - 3, plak.length() - 1);
            String c4 = plak.substring(plak.length() - 1);
            holder.txt_plak_customer1.setText(c1);
            holder.txt_plak_customer2.setText(c4);
            holder.txt_plak_customer3.setText(c2);
            holder.txt_plak_customer4.setText(c3);

        } else {
            holder.plaks.setVisibility(View.GONE);
        }

        holder.icon_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(context)
                        .inflate(R.layout.layout_button_sheet_customer, (LinearLayout) holder.itemView.findViewById(R.id.ly_bottom_sheet_lang));
                bottomSheetDialog.setCancelable(true);
                Typeface font = Typeface.createFromAsset(context.getResources().getAssets(), "Fonts/IRANSans-Bold-web.ttf");
                bottomSheetDialog.setContentView(bottomSheetView);
                TextView txt_info_name, txt_info_plak;
                txt_info_name = bottomSheetDialog.findViewById(R.id.txt_info_name);
                txt_info_plak = bottomSheetDialog.findViewById(R.id.txt_info_plak);
                txt_info_name.setTypeface(font);
                txt_info_plak.setTypeface(font);

                bottomSheetDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name_customer, txt_name_car, txt_phone_customer;
        ImageView icon_menu;
        TextView txt_plak_customer1, txt_plak_customer2, txt_plak_customer3, txt_plak_customer4;
        ViewGroup plaks;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name_customer = itemView.findViewById(R.id.txt_name_customer);
            txt_name_car = itemView.findViewById(R.id.txt_name_car);
            txt_phone_customer = itemView.findViewById(R.id.txt_phone_customer);

            icon_menu = itemView.findViewById(R.id.icon_menu);


            plaks = itemView.findViewById(R.id.plaks);
            txt_plak_customer1 = itemView.findViewById(R.id.txt_plak_customer1);
            txt_plak_customer2 = itemView.findViewById(R.id.txt_plak_customer2);
            txt_plak_customer3 = itemView.findViewById(R.id.txt_plak_customer3);
            txt_plak_customer4 = itemView.findViewById(R.id.txt_plak_customer4);
            txt_plak_customer1.setTypeface(G.ExtraBold);
            txt_plak_customer2.setTypeface(G.ExtraBold);
            txt_plak_customer3.setTypeface(G.ExtraBold);
            txt_plak_customer4.setTypeface(G.ExtraBold);
            txt_name_customer.setTypeface(G.ExtraBold);
            txt_name_car.setTypeface(G.ExtraBold);
            txt_phone_customer.setTypeface(G.Normal);

        }
    }
}
