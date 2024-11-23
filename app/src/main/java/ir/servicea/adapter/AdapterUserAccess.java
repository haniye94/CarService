package ir.servicea.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import ir.servicea.R;
import ir.servicea.activity.LastServiseDoneActivity;
import ir.servicea.activity.SplashActivity;
import ir.servicea.activity.UserAccessActivity;
import ir.servicea.app.G;
import ir.servicea.app.PreferenceUtil;
import ir.servicea.model.UserAccess;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class AdapterUserAccess extends RecyclerView.Adapter<AdapterUserAccess.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<UserAccess> models;

    public AdapterUserAccess(Context context, List<UserAccess> models) {
        this.context = context;
        this.models = models;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.adapter_user_access, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.name.setTypeface(G.Bold);
        holder.name.setText(models.get(position).getName() + "");

        holder.phone.setTypeface(G.Bold);
        holder.phone.setText(models.get(position).getPhone() + "");

        holder.create_at.setTypeface(G.Bold);
        holder.create_at.setText(G.toShamsi(models.get(position).getCreated_at()) + "");

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("حذف دسترسی")
                        .setContentText("آیا می\u200Cخواهید دسترسی کاربر حذف شود؟")
                        .setCancelText("خیر")
                        .setConfirmText("بله")
                        .showCancelButton(true)
                        .setConfirmClickListener(sDialog -> {
                            sDialog.dismiss();
                            deleteService(models.get(position).getId());

                        })
                        .setCancelClickListener(sDialog -> {
                            sDialog.dismiss();
                        })
                        .show();
            }
        });

    }

    public void deleteService(long user_access_id) {
        Log.e(G.TAG, user_access_id+"");
        G.loading(context);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        JSONObject object = new JSONObject();
        try {
            String deleted_at = G.converToEn(DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString());
            object.put("deleted_at", deleted_at);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ResponseBody> request = api.deleteUserAccess(user_access_id + "", G.returnBody(object.toString()));
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                G.stop_loading();
                assert response.body() != null;
                String result = G.getResult(response);
                if (result.length() > 0 && result.length() < 10) {
                    for(int i=0;i<models.size();i++){
                        if(models.get(i).getId()==user_access_id){
                            models.remove(models.get(i));
                            UserAccessActivity.swipeRefreshLayout.performClick();
                        }
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, create_at;

        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            create_at = itemView.findViewById(R.id.create_at);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}