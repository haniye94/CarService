package ir.tehranOil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.servicea.R;
import ir.tehranOil.activity.CustomerActivity;
import ir.tehranOil.activity.LastServiseDoneActivity;
import ir.tehranOil.activity.ProductGroupActivity;
import ir.tehranOil.activity.ReportCustomerActivity;
import ir.tehranOil.activity.ReportSalesActivity;
import ir.tehranOil.activity.SendMessageActivity;
import ir.tehranOil.activity.SettingActivity;
import ir.tehranOil.activity.WebViewActivity;
import ir.tehranOil.app.G;
import ir.tehranOil.model.ModelItemMain;

public class AdapterListGridMain extends RecyclerView.Adapter<AdapterListGridMain.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<ModelItemMain> models;
    int selectPosition;

    public AdapterListGridMain(Context context, List<ModelItemMain> models) {
        this.context = context;
        this.models = models;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_list_grid_main, parent, false));
    }

    public static View listCustView;
    public static View listServiceView;

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txt_name_item.setTypeface(G.ExtraBold);
        holder.txt_name_item.setText(models.get(position).getName());
        int id = models.get(position).getImage();
        holder.img_level.setImageResource(id);
        holder.ly_main.setBackgroundResource(models.get(position).getImg_bg());
        if ((models.get(position).getId()) == 1) {
            listServiceView = holder.itemView;
        }
        if ((models.get(position).getId()) == 5) {
            listCustView = holder.itemView;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (models.get(position).getId()) {
                    case 1:
                        context.startActivity(new Intent(context, LastServiseDoneActivity.class).putExtra("idCustomer", 0));
                        break;
                    case 2:
                        context.startActivity(new Intent(context, ReportCustomerActivity.class));
                        break;
                    case 3:
                        Intent intent = new Intent(context, SendMessageActivity.class);
                        intent.putExtra("firstName", "null");
                        context.startActivity(intent);
                        break;
                    case 4:
                        context.startActivity(new Intent(context, WebViewActivity.class)
                                .putExtra("LINK", G.LINK_CAR_INFO)
                                .putExtra("TITLE", "اطلاعات خودرو"));

                        break;
                    case 5:
                        context.startActivity(new Intent(context, CustomerActivity.class));

                        break;
                    case 6:
                        context.startActivity(new Intent(context, ReportSalesActivity.class));

                        break;
                    case 7:
                        context.startActivity(new Intent(context, WebViewActivity.class)
                                .putExtra("LINK", G.LINK_SHOP)
                                .putExtra("TITLE", "فروشگاه"));

                        // context.startActivity(new Intent(context, ShopActivity.class));

                        //  context.startActivity(new Intent(context, ProductGroupActivity.class));

                        break;
                    case 8:
                        context.startActivity(new Intent(context, SettingActivity.class));
                        break;
                    case 10:
                        context.startActivity(new Intent(context, ProductGroupActivity.class));
                        break;

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name_item;
        ImageView img_level;
        LinearLayout ly_main;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name_item = itemView.findViewById(R.id.txt_name_item);
            img_level = itemView.findViewById(R.id.img_level);
            ly_main = itemView.findViewById(R.id.ly_main);
        }
    }

}
