package ir.servicea.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import ir.servicea.app.G;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import ir.servicea.activity.CreateTextMessageActivity;
import ir.servicea.model.dbModel.ModelAddMessage;

import java.util.List;

import ir.servicea.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class AdapterMessageAmade extends RecyclerView.Adapter<AdapterMessageAmade.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<ModelAddMessage> models;

    public AdapterMessageAmade(Context context, List<ModelAddMessage> models) {
        this.context = context;
        this.models = models;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_list_message_amade, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txt_onvan.setTypeface(G.ExtraBold);
        holder.txt_message.setTypeface(G.ExtraBold);

        holder.txt_onvan.setText(models.get(position).getTitle().toString());
        holder.txt_message.setText(models.get(position).getText().toString());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(G.Activity, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("خروج")
                        .setContentText("آیا میخواهید پیام حذف شود؟")
                        .setCancelText("خیر")
                        .setConfirmText("بله")
                        .showCancelButton(true)
                        .setConfirmClickListener(sDialog -> {
                            sDialog.dismiss();
                            deleteSmsDraft(models.get(position).getId()+"");
                        })
                        .setCancelClickListener(sDialog -> {
                            sDialog.dismiss();
                        })
                        .show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_onvan, txt_message;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_onvan = itemView.findViewById(R.id.txt_onvan);
            txt_message = itemView.findViewById(R.id.txt_message);
            delete = itemView.findViewById(R.id.delete);

        }
    }



    public void deleteSmsDraft(String id) {
        G.loading(G.Activity);
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.deleteSmsDraft(id);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                G.stop_loading();
                String result = G.getResult(response);
                if (result.length() > 0 && result.length() < 10) {
                    G.toast("حذف شد");
                    G.Activity.startActivity(new Intent(G.Activity, CreateTextMessageActivity.class));
                    G.Activity.finish();

                }else {
                    G.toast("مشکل در ثبت اطلاعات");
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

}
