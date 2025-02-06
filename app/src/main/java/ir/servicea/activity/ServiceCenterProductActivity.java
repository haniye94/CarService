package ir.servicea.activity;

import static ir.servicea.app.G.context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.R;
import ir.servicea.adapter.AdapterListAddedProduct;
import ir.servicea.adapter.SliderAdapterExample;
import ir.servicea.app.G;
import ir.servicea.app.PreferenceUtil;
import ir.servicea.dialog.AddInventoryDialogBottomSheet;
import ir.servicea.dialog.FilterProductDialogBottomSheet;
import ir.servicea.model.ModelAddedProductCenter;
import ir.servicea.model.ModelSpinner;
import ir.servicea.model.SliderItem;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceCenterProductActivity extends AppCompatActivity implements FilterProductDialogBottomSheet.BottomSheetListener, AddInventoryDialogBottomSheet.BottomSheetListener {

    private AdapterListAddedProduct adapterListAddedProduct;
    RecyclerView reserveProductRecycler;
    TextView txt_tile_action_bar;
    ImageView iv_back;
    LinearLayout ll_no_data;
    private TextView txt_group, txt_brand;
    private SliderView sliderView;
    private SliderAdapterExample sliderAdapter;

    private LinearLayout ll_filter, ll_group, ll_brand;
    private SwipeRefreshLayout swipeRefreshLayout;
    List<ModelAddedProductCenter> products = new ArrayList<>();

    String group_id = "0", brand_id = "0";
    int status = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_center_product);

        G.Activity = this;
        G.context = this;
        reserveProductRecycler = findViewById(R.id.rv_products);
        reserveProductRecycler.setLayoutManager(new LinearLayoutManager(this));
        findViews();
        onClick();
        initSlider();
        txt_tile_action_bar.setText("لیست کالای من");
        txt_tile_action_bar.setTypeface(G.Bold);
        loadDate();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshFilter(group_id, brand_id);

                    }
                }, 250);
            }
        });
    }

    private void initSlider() {
        sliderAdapter = new SliderAdapterExample(context);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
        getSlider();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
                sliderView.setCurrentPagePosition(3);
            }
        });
    }

    private void findViews() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        iv_back = findViewById(R.id.img_back);
        ll_no_data = findViewById(R.id.ll_nodata);
        sliderView = findViewById(R.id.sliderView);
        ll_filter = findViewById(R.id.ll_filter);
        ll_group = findViewById(R.id.ll_group);
        ll_brand = findViewById(R.id.ll_brand);
        txt_group = findViewById(R.id.tv_group);
        txt_brand = findViewById(R.id.tv_brand);
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.button));

    }

    private void onClick() {
        ll_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogBrandAndGroup();
            }
        });
        ll_brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ll_brand.setVisibility(View.GONE);
                brand_id = "0";
                refreshFilter(group_id, brand_id);
            }
        });
        ll_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_group.setVisibility(View.GONE);
                group_id = "0";
                refreshFilter(group_id, brand_id);
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void fetchProducts(String group_id, String brand_id) {
        G.loading(this);
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        int service_center_id = Integer.parseInt(PreferenceUtil.getD_id());

        Call<ResponseBody> request = api.getProductsOfCenter(service_center_id, group_id, brand_id);
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    List<ModelAddedProductCenter> products = new ArrayList<>();
                    // Set up the adapter with the fetched products

                    try {
                        String result = G.getResult(response);
                        JSONArray jsonArray = new JSONArray(result);
                        if (jsonArray.length() > 0) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                ModelAddedProductCenter product = new ModelAddedProductCenter();
                                JSONObject obj = jsonArray.getJSONObject(i);
                                product.setPackaging(obj.getString("packaging"));
                                product.setAmount(obj.getString("amount"));
                                product.setReal_price(obj.getInt("real_price"));
                                product.setAmount_discount(obj.getInt("amount_discount"));
                                product.setCustomer_price(obj.getInt("customer_price"));
                                product.setInventory(obj.getInt("inventory"));
                                product.setName(obj.getString("name"));
                                product.setBrand_id(obj.getInt("brand_id"));
                                product.setGrade_id(obj.getInt("grade_id"));
                                product.setQuality_type_id(obj.getInt("quality_type_id"));
                                product.setProduct_group_id(obj.getInt("product_group_id"));
                                product.setProduct_name_id(obj.getInt("product_name_id"));
                                product.setId(obj.getInt("id"));
                                product.setImageUrl(obj.getString("image"));
                                product.setStatus(obj.getInt("status"));
                                products.add(product);
                                adapterListAddedProduct = new AdapterListAddedProduct(products, ServiceCenterProductActivity.this, new AdapterListAddedProduct.OnAddProductClick() {
                                    @Override
                                    public void onAddProductClick(ModelAddedProductCenter modelAddedProductCenter) {
                                        if (modelAddedProductCenter.getStatus() == 0)
                                            G.toast("محصول غیر فعال است");
                                        else
                                            showBottomSheetDialogInventory(modelAddedProductCenter);

                                    }
                                }, new AdapterListAddedProduct.OnHideProductClick() {
                                    @Override
                                    public void onHideProductClick(ModelAddedProductCenter modelAddedProductCenter, int position) {
                                        hideProduct(modelAddedProductCenter, position);
                                    }
                                });
                                G.stop_loading();
                                reserveProductRecycler.setAdapter(adapterListAddedProduct);
                                swipeRefreshLayout.setRefreshing(false);
                            }

                        } else {
                            G.stop_loading();
                            ll_no_data.setVisibility(View.VISIBLE);
                            reserveProductRecycler.setVisibility(View.GONE);

                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                G.stop_loading();
                swipeRefreshLayout.setRefreshing(false);
                G.toast("مشکل در برقراری ارتباط با سرور");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        G.Activity = this;
        G.context = this;
    }

    public void getSlider() {

//        G.loading(G.Activity);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.getSliderServiceCenterProduct();
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        String result = response.body().string();
                        JSONObject object = G.StringtoJSONObject(result);
                        JSONArray records = object.getJSONArray("records");
                        if (records.length() > 0) {
                            List<SliderItem> sliderItemList = new ArrayList<>();

                            for (int i = 0; i < records.length(); i++) {
                                JSONObject obj = records.getJSONObject(i);
                                SliderItem sliderItem = new SliderItem();
                                String id = obj.getString("id");
                                String title = obj.getString("title");
                                String image = obj.getString("image");
                                String url = obj.getString("url");
                                sliderItem.setDescription(title);
                                sliderItem.setUrl(url);
                                sliderItem.setImageUrl(G.PreImagesURL + "slides/" + image);
                                sliderItemList.add(sliderItem);
                            }
                            sliderAdapter.renewItems(sliderItemList);
                        } else {
                            G.toast("مشکل در دریافت اطلاعات");
                        }
                    } catch (JSONException | IOException e) {
                        G.toast("مشکل در تجزیه اطلاعات");
                        e.printStackTrace();
                    }
                }
                G.stop_loading();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    private void showDialogBrandAndGroup() {
        FilterProductDialogBottomSheet bottomSheetDialog = new FilterProductDialogBottomSheet(this);
        bottomSheetDialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomSheetDialogTheme);
        bottomSheetDialog.show(getSupportFragmentManager(), "bottomSheet");
    }

    @Override
    public void onValueReceived(boolean change, ModelSpinner group, ModelSpinner brand) {
        if (!group.getName().equals("انتخاب کنید")) {
            ll_group.setVisibility(View.VISIBLE);
            txt_group.setText(group.getName());
        } else {
            ll_group.setVisibility(View.GONE);
        }
        if (!brand.getName().equals("انتخاب کنید")) {
            ll_brand.setVisibility(View.VISIBLE);
            txt_brand.setText(brand.getName());
        } else {
            ll_brand.setVisibility(View.GONE);
        }
        group_id = group.getId();
        brand_id = brand.getId();
        if (change) refreshFilter(group_id, brand_id);
    }

    private void loadDate() {
        fetchProducts(group_id, brand_id);
    }

    private void refreshFilter(String groupId, String brandId) {
        // Start refreshing
        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fetchProducts(groupId, brandId);
                adapterListAddedProduct.updateData(products);
                // Stop refreshing
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000); // Simulate a delay of 2 seconds, for example
    }

    private void refreshHideItem(ModelAddedProductCenter modelAddedProductCenter, int position) {
        // Start refreshing
        swipeRefreshLayout.setRefreshing(true);

        // Simulate data fetching (use your actual data fetching here)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapterListAddedProduct.hideItem(modelAddedProductCenter, position);
                // Stop refreshing
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000); // Simulate a delay of 2 seconds, for example
    }


    private void showBottomSheetDialogInventory(ModelAddedProductCenter modelAddedProduct) {

        AddInventoryDialogBottomSheet bottomSheetDialog = new AddInventoryDialogBottomSheet(this, modelAddedProduct);
        bottomSheetDialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomSheetDialogTheme);
        bottomSheetDialog.show(getSupportFragmentManager(), "bottomSheet");
    }

    public void hideProduct(ModelAddedProductCenter modelAddedProductCenter, int position) {
        G.loading(context);
        status = modelAddedProductCenter.getStatus();
        status = status == 1 ? 0 : 1;
        JSONObject object = new JSONObject();
        try {
            object.put("id", modelAddedProductCenter.getId());
            object.put("product_name_id", String.valueOf(modelAddedProductCenter.getProduct_name_id()));
            object.put("product_group_id", String.valueOf(modelAddedProductCenter.getProduct_group_id()));
            object.put("amount", modelAddedProductCenter.getAmount());
            object.put("packaging", modelAddedProductCenter.getPackaging());
            object.put("inventory", modelAddedProductCenter.getInventory());
            object.put("customer_price", modelAddedProductCenter.getCustomer_price());
            object.put("real_price", modelAddedProductCenter.getReal_price());
            object.put("amount_discount", modelAddedProductCenter.getAmount_discount());
            object.put("status", status);
            object.put("service_center_id", Integer.parseInt(PreferenceUtil.getD_id()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        G.Log(object + "");
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.editServicesCenterProduct(modelAddedProductCenter.getId(), G.returnBody(object.toString()));
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                int code = response.code();
                if (response.isSuccessful()) {

                    if (code == 200) {
                        if (status == 0) G.toast("محصول با موفقیت غیر فعال شد.");
                        else G.toast("محصول با موفقیت  فعال شد.");

                        modelAddedProductCenter.setStatus(status);
                        refreshHideItem(modelAddedProductCenter, position);

                    } else {
                        G.toast("خطا در غیرفعال سازی محصول");

                    }
                }
                G.stop_loading();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط با سرور");
            }
        });
    }


    @Override
    public void onProductAdded(int product_id) {

    }

    @Override
    public void onProductEdited(ModelAddedProductCenter modelAddedProductCenter) {
        adapterListAddedProduct.updateItemData(modelAddedProductCenter);
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

}