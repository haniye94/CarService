package ir.servicea;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.squareup.picasso.Picasso;

import ir.servicea.activity.AlarmsActivity;
import ir.servicea.activity.CommentsActivity;
import ir.servicea.activity.LoginInfoActivity;
import ir.servicea.activity.SplashActivity;
import ir.servicea.activity.SupportActivity;
import ir.servicea.activity.UserAccessActivity;
import ir.servicea.activity.WebViewActivity;
import ir.servicea.app.CircleTransform;
import ir.servicea.app.G;
import ir.servicea.activity.Config2Activity;
import ir.servicea.activity.ConfigActivity;
import ir.servicea.activity.passwordActivity;
import ir.servicea.app.PreferenceUtil;

public class Fragmentprofile extends Fragment {
    TextView txt_tile_action_bar;
    TextView txt_city_job,txt_useraxxess_warning,txt_useraccess, txt_name_family, txt_edit_pass, txt_faq, txt_notif, txt_language, txt_suport, txt_exit,txt_privacy,txt_score, txt_score_bazar;
    Button btn_edit_profile;
    PreferenceUtil preferenceUtil;
    private ImageView profile_iv;
    public View view;

    public void onclickAlamrs(View v) {
        startActivity(new Intent(getContext(), AlarmsActivity.class));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_profile, container, false);
        preferenceUtil = new PreferenceUtil(getContext());
        FindView(view);
        onClick();
        txt_tile_action_bar.setText("تنظیمات حساب کاربری");

        txt_city_job.setTypeface(G.Normal);
        txt_tile_action_bar.setTypeface(G.ExtraBold);
        txt_name_family.setTypeface(G.ExtraBold);
        txt_edit_pass.setTypeface(G.ExtraBold);
        txt_faq.setTypeface(G.ExtraBold);
        txt_notif.setTypeface(G.ExtraBold);
        txt_language.setTypeface(G.ExtraBold);
        txt_suport.setTypeface(G.ExtraBold);
        txt_exit.setTypeface(G.ExtraBold);
        txt_privacy.setTypeface(G.ExtraBold);
        txt_score.setTypeface(G.ExtraBold);
        txt_score_bazar.setTypeface(G.ExtraBold);
        txt_useraccess.setTypeface(G.ExtraBold);
        if(!G.preference.getBoolean("haveUserAccess",false)) {
            txt_useraxxess_warning.setText("");
            txt_name_family.setText((preferenceUtil.getName() + "").replace("null", "") + " " + (preferenceUtil.getFamily() + "").replace("null", ""));
        }else{
            txt_useraxxess_warning.setText(" (زیر مجموعه) ");
            txt_name_family.setText((preferenceUtil.getName() + "").replace("null", "") + " " + (preferenceUtil.getFamily() + "").replace("null", ""));
        }
        txt_city_job.setText((preferenceUtil.getCity() + "").replace("null", "") + "");
        if (txt_city_job.getText().toString().replace(" ", "").length() <= 3) {
            txt_city_job.setText("");
        }
        if(txt_name_family.getText().toString().length()<=1){
            txt_name_family.setText("کاربر مهمان");
        }
        view.findViewById(R.id.faq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), WebViewActivity.class)
                        .putExtra("LINK", G.LINK_Introduction)
                        .putExtra("TITLE", "معرفی و آموزش"));
            }
        });view.findViewById(R.id.elans).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), ConfigActivity.class));
            }
        });
        if(!G.preference.getBoolean("haveUserAccess",false)) {
            view.findViewById(R.id.useraccess).setVisibility(View.VISIBLE);
            view.findViewById(R.id.useraccess).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    startActivity(new Intent(getActivity(), UserAccessActivity.class));
                }
            });
        }else {
            view.findViewById(R.id.useraccess).setVisibility(View.GONE);
        }
        view.findViewById(R.id.privacy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Config2Activity.class));
            }
        });
        view.findViewById(R.id.score).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CommentsActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.score_bazar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setData(Uri.parse("bazaar://details?id="+ "ir.servicea"));
                intent.setPackage("com.farsitel.bazaar");
                startActivity(intent);
            }
        });
        view.findViewById(R.id.language).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              G.toast("بزودی ...");
            }
        });
        view.findViewById(R.id.support).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SupportActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), passwordActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("خروج")
                        .setContentText("آیا می\u200Cخواهید از برنامه خارج شوید؟")
                        .setCancelText("خیر")
                        .setConfirmText("بله")
                        .showCancelButton(true)
                        .setConfirmClickListener(sDialog -> {
                            sDialog.dismiss();
                            G.preference.edit().clear().apply();
                            PreferenceUtil.preferenceUtil.edit().clear().apply();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(getActivity(), SplashActivity.class));
                                    getActivity().finishAffinity();
                                }
                            },100);

                        })
                        .setCancelClickListener(sDialog -> {
                            sDialog.dismiss();
                        })
                        .show();

            }
        });
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        if (G.preference.getString("resultUri", "").length() > 3) {
            profile_iv = view.findViewById(R.id.profile_iv);
            Uri resultUri = Uri.parse(G.preference.getString("resultUri", ""));
            Picasso.get().load(resultUri).error(R.drawable.ic_user).placeholder(R.drawable.ic_user).transform(new CircleTransform()).into(profile_iv);
        }
        if (G.preference.getString("profile_photo", "").length() > 3) {
            profile_iv = view.findViewById(R.id.profile_iv);
            String profile_photo = G.preference.getString("profile_photo","");
            Picasso.get().load(G.PreImagesURL+"profiles/" +profile_photo).error(R.drawable.ic_user).placeholder(R.drawable.ic_user).transform(new CircleTransform()).into(profile_iv);
        }
    }

    void FindView(View view) {

        txt_tile_action_bar = view.findViewById(R.id.txt_tile_action_bar);
        txt_name_family = view.findViewById(R.id.txt_name_family);
        txt_city_job = view.findViewById(R.id.txt_city_job);
        txt_edit_pass = view.findViewById(R.id.txt_edit_pass);
        txt_faq = view.findViewById(R.id.txt_faq);
        txt_notif = view.findViewById(R.id.txt_notif);
        txt_language = view.findViewById(R.id.txt_language);
        txt_suport = view.findViewById(R.id.txt_suport);
        txt_exit = view.findViewById(R.id.txt_exit);
        txt_privacy = view.findViewById(R.id.txt_privacy);
        txt_score = view.findViewById(R.id.txt_score);
        txt_score_bazar = view.findViewById(R.id.txt_score_bazar);
        btn_edit_profile = view.findViewById(R.id.btn_edit_profile);
        txt_useraccess = view.findViewById(R.id.txt_useraccess);
        txt_useraxxess_warning = view.findViewById(R.id.txt_useraxxess_warning);
    }

    private void onClick() {
        btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginInfoActivity.class);
                intent.putExtra("editPro", "editPro");
                startActivity(intent);
            }
        });
    }


}