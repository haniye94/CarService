package ir.servicea.dialog;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ir.servicea.R;
import ir.servicea.app.G;
import ir.servicea.app.PreferenceUtil;
import ir.servicea.model.ModelProductGroup;
import ir.servicea.model.ModelSpinner;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author haniye94 .
 * @since on 12/30/2024.
 */
public class AddNewProductDialogBottomSheet extends BottomSheetDialogFragment {

    Context context;
    EditText edt_product_name;
    Spinner spBrand, spGroup, spGrade, spQuality;

    private final List<ModelSpinner> listGroup = new ArrayList<>();
    List<String> spinnerBrandTitle = new ArrayList<>();
    List<String> spinnerBrandId = new ArrayList<>();
    List<String> spinnerGroupTitle = new ArrayList<>();
    List<String> spinnerGroupId = new ArrayList<>();
    List<String> spinnerGradeTitle = new ArrayList<>();
    List<String> spinnerGradeId = new ArrayList<>();
    List<String> spinnerQualityTitle = new ArrayList<>();
    List<String> spinnerQualityId = new ArrayList<>();
    Button btnCancel, btnSubmit;
    List<ModelProductGroup.ProductGroup> productGroups = new ArrayList<>();
    ModelProductGroup.ProductAdd productAdd = new ModelProductGroup.ProductAdd();
    List<ModelProductGroup.Brand> productBrands = new ArrayList<>();

    public AddNewProductDialogBottomSheet(@NonNull Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.add_new_product_dialog, null);
        FindViews(view);
        FetchProductsGroup();
        OnClick();
        return view;
    }

    private void OnClick() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product_name = edt_product_name.getText().toString().trim(); // Trim whitespace


                if (TextUtils.isEmpty(product_name)) {
                    edt_product_name.setError("لطفا نام محصول را وارد کنید");
                } else if (productAdd.getProductGroupId().equals("0")) {
                    G.toast("لطفا گروه کالا را انتخاب کنید");
                } else {
                    if (productAdd.getBrandId().equals("0")) {
                        G.toast("لطفا  برند را انتخاب کنید");
                    } else if (productAdd.getGradeId().equals("0")) {
                        G.toast("لطفا گرید را انتخاب کنید");

                    } else if (productAdd.getQualityTypeId() != null) {
                        if (productAdd.getQualityTypeId().equals("0"))
                            G.toast("لطفا سطح کیفی را انتخاب کنید");
                    } else addNewProduct();
                }
            }

        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void FindViews(View view) {

        edt_product_name = view.findViewById(R.id.edt_product_name);
        spGroup = view.findViewById(R.id.spinner_group);
        spBrand = view.findViewById(R.id.spinner_brand);
        spGrade = view.findViewById(R.id.spinner_grade);
        spQuality = view.findViewById(R.id.spinner_quality);
        btnCancel = view.findViewById(R.id.btn_cancel);
        btnSubmit = view.findViewById(R.id.btn_submit);
    }

    private void FetchProductsGroup() {
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        int service_center_id = Integer.parseInt(PreferenceUtil.getD_id());

        Call<ResponseBody> request = api.getProductsGroupAndBrand(service_center_id);
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                String jsonString = G.getResult(response);
                parseJson(jsonString);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط با سرور");
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent); // Set transparent for Border
        }
    }

    public void parseJson(String jsonResponse) {

        List<ModelProductGroup.ProductGroup> productGroups = new ArrayList<>();
        List<ModelProductGroup.Brand> brands = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);

            // Parse Product Groups
            JSONArray productGroupsArray = jsonObject.getJSONArray("product_groups");
            for (int i = 0; i < productGroupsArray.length(); i++) {
                ModelProductGroup.ProductGroup productGroup = new ModelProductGroup.ProductGroup();
                JSONObject groupObject = productGroupsArray.getJSONObject(i);
                productGroup.setProductGroupId(groupObject.getString("product_group_id"));
                productGroup.setProductGroupTitle(groupObject.getString("product_group_title"));
                // Parse details
                List<ModelProductGroup.Detail> detailsList = new ArrayList<>();
                JSONArray detailsArray = groupObject.getJSONArray("details");
                for (int j = 0; j < 2; j++) {
                    JSONObject detailObject = detailsArray.getJSONObject(j);
                    ModelProductGroup.Detail detail = new ModelProductGroup.Detail();
                    detail.setGradeId(detailObject.getString("grade_id"));
                    detail.setGradeName(detailObject.getString("grade_name"));
                    if (detailObject.has("quality_type_id")) {
                        detail.setQualityTypeId(detailObject.getString("quality_type_id"));
                    } else
                        detail.setQualityTypeId(jsonObject.optString("quality_type_id", "Not Available"));


                    if (detailObject.has("quality_type_name")) {
                        detail.setQualityTypeName(detailObject.getString("quality_type_name"));
                    } else
                        detail.setQualityTypeName(jsonObject.optString("quality_type_name", "Not Available"));

                    detailsList.add(detail);
                }


                productGroup.setDetails(detailsList);
                productGroups.add(productGroup);

            }

            // Parse Brands
            JSONArray brandsArray = jsonObject.getJSONArray("brands");
            for (int i = 0; i < brandsArray.length(); i++) {
                JSONObject brandObject = brandsArray.getJSONObject(i);
                ModelProductGroup.Brand brand = new ModelProductGroup.Brand();
                brand.setBrandId(brandObject.getString("brand_id"));
                brand.setBrandName(brandObject.getString("brand_name"));
                brands.add(brand);
            }
            getSpinners(productGroups, brands);

        } catch (Exception e) {
//            e.printStackTrace();
        }

        // Now productGroups and brands lists are populated and can be used as needed
    }

    public void getSpinners(List<ModelProductGroup.ProductGroup> productGroups, List<ModelProductGroup.Brand> productBrands) {
        spinnerGroupTitle.clear();
        spinnerGroupId.clear();
        spinnerGroupTitle.add(0, "انتخاب کنید");
        spinnerGroupId.add(0, "0");
        for (int i = 0; i < productGroups.size(); i++) {
            spinnerGroupTitle.add(i + 1, productGroups.get(i).getProductGroupTitle());
            spinnerGroupId.add(i + 1, productGroups.get(i).getProductGroupId());
        }
        spGroup.setAdapter(G.setFontToSpinner(spinnerGroupTitle));
        spGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                productAdd.setProductGroupTitle(spinnerGroupTitle.get(position));
                productAdd.setProductGroupId(spinnerGroupId.get(position));
                spinnerGradeTitle.clear();
                spinnerGradeId.clear();
                spinnerGradeTitle.add(0, "انتخاب کنید");
                spinnerGradeId.add(0, "0");
                spinnerQualityTitle.clear();
                spinnerQualityId.clear();
                spinnerQualityTitle.add(0, "انتخاب کنید");
                spinnerQualityId.add(0, "0");
                for (int i = 0; i < productGroups.size(); i++) {
                    if (selectedItem.equals(productGroups.get(i).getProductGroupTitle())) {
                        List<ModelProductGroup.Detail> details = productGroups.get(i).getDetails();
                        for (int j = 0; j < details.size(); j++) {
                            spinnerGradeTitle.add(j + 1, details.get(j).getGradeName());
                            spinnerGradeId.add(j + 1, details.get(j).getGradeId());
                            if (!details.get(j).getQualityTypeName().equals("Not Available")) {
                                spinnerQualityTitle.add(j + 1, details.get(j).getQualityTypeName());
                                spinnerQualityId.add(j + 1, details.get(j).getQualityTypeId());
                            } else {
                                spinnerQualityTitle.clear();
                                spQuality.setEnabled(false);
                            }
                        }

                    }
                    spGrade.setAdapter(G.setFontToSpinner(spinnerGradeTitle));
                    spGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            productAdd.setGradeName(spinnerGradeTitle.get(position));
                            productAdd.setGradeId(spinnerGradeId.get(position));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    spQuality.setAdapter(G.setFontToSpinner(spinnerQualityTitle));
                    spQuality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            productAdd.setGradeName(spinnerQualityTitle.get(position));
                            productAdd.setGradeId(spinnerQualityId.get(position));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerBrandTitle.clear();
        spinnerBrandId.clear();
        spinnerBrandTitle.add(0, "انتخاب کنید");
        spinnerBrandId.add(0, "0");
        for (int i = 0; i < productBrands.size(); i++) {
            spinnerBrandTitle.add(i + 1, productBrands.get(i).getBrandName());
            spinnerBrandId.add(i + 1, productBrands.get(i).getBrandId());
        }
        spBrand.setAdapter(G.setFontToSpinner(spinnerBrandTitle));
        spBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                productAdd.setBrandName(spinnerBrandTitle.get(position));
                productAdd.setBrandId(spinnerBrandId.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void addNewProduct() {

        JSONObject object = new JSONObject();
        try {
            object.put("product_group_id", productAdd.getProductGroupId());
            object.put("brand_id", productAdd.getBrandId());
            object.put("grade_id", productAdd.getGradeId());
            object.put("quality_type_id", "1"/*productAdd.getQualityTypeId()*/);
            object.put("name", edt_product_name.getText().toString());
            object.put("description", "");
            object.put("status", 0);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        G.Log(object + "");
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.addNewProducts(G.returnBody(object.toString()));
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                int code = response.code();
                if (response.isSuccessful()) {
                    String result = G.getResult(response);
                    if (code == 200) {
                        G.toast("ثبت کالای پیشنهادی با موفقیت صورت گرفت.");
                    } else {
                        G.toast("خطا درثبت کالای پیشنهادی");

                    }
                }
                G.stop_loading();
                dismiss();

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط با سرور");
            }
        });
    }


}
