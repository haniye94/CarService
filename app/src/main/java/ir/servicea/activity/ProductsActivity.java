package ir.servicea.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.R;
import ir.servicea.adapter.AdapterListProduct;
import ir.servicea.app.G;
import ir.servicea.app.PreferenceUtil;
import ir.servicea.dialog.AddInventoryDialogBottomSheet;
import ir.servicea.dialog.AddNewProductDialogBottomSheet;
import ir.servicea.dialog.FilterProductDialogBottomSheet;
import ir.servicea.model.ModelAddedProductCenter;
import ir.servicea.model.ModelSpinner;
import ir.servicea.model.ReserveProduct;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity implements FilterProductDialogBottomSheet.BottomSheetListener, AddInventoryDialogBottomSheet.BottomSheetListener {

    private AdapterListProduct adapterListReserveProduct;
    RecyclerView reserveProductRecycler;
    private TextView tv_add_product, tv_group, tv_brand, tv_title;
    private LinearLayout ll_filter, ll_group, ll_brand;
    private SwipeRefreshLayout swipeRefreshLayout;
    List<ReserveProduct> products = new ArrayList<>();

    String group_id = "0", brand_id = "0";
    private EditText search;
    private boolean fromRefresh = false;
    String last_key = "";
    private ImageView iv_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        G.Activity = this;
        G.context = this;
        reserveProductRecycler = findViewById(R.id.rv_products);
        reserveProductRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        findViews();
        onClick();
        loadDate(last_key);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fromRefresh = true;
                        refreshFilter(group_id, brand_id, last_key);

                    }
                }, 250);
            }
        });
        Handler handler = new Handler();
        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 && !fromRefresh) {

                    handler.removeCallbacksAndMessages(null);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            refreshFilter(group_id, brand_id, search.getText().toString());

                        }
                    }, 250);

                } else if (s.length() == 0) {
                    refreshFilter(group_id, brand_id, "");
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        G.Activity = this;
        G.context = this;
    }

    private void findViews() {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setTypeface(G.Bold);
        tv_add_product = findViewById(R.id.tv_add_product);
        tv_add_product.setTypeface(G.Bold);
        ll_filter = findViewById(R.id.ll_filter);
        ll_group = findViewById(R.id.ll_group);
        ll_brand = findViewById(R.id.ll_brand);
        tv_group = findViewById(R.id.tv_group);
        tv_brand = findViewById(R.id.tv_brand);
        iv_back = findViewById(R.id.iv_back);
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.button));
        search = findViewById(R.id.search);

    }

    private void onClick() {
        tv_add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialogAddNewProduct();
            }
        });
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
                refreshFilter(group_id, brand_id, last_key);
            }
        });
        ll_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_group.setVisibility(View.GONE);
                group_id = "0";
                refreshFilter(group_id, brand_id, last_key);
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void fetchProducts(String group_id, String brand_id, String key) {
        last_key = key;
        if (key.length() > 0) {
            products.clear();
            key = G.converToEn(key);
        }
        String finalKey = key;
        swipeRefreshLayout.setRefreshing(true);
//        G.loading(this);
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        int service_center_id = Integer.parseInt(PreferenceUtil.getD_id());

        Call<ResponseBody> request = api.getProducts(service_center_id, group_id, brand_id, "product_name,cs," + key);
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (finalKey.length() > 0) {
                        products.clear();
                    }
                    String result = G.getResult(response);
                    try {
                        JSONArray jsonArray = new JSONArray(result);
                        if (jsonArray.length() > 0) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                ReserveProduct product = new ReserveProduct();
                                JSONObject obj = jsonArray.getJSONObject(i);
                                product.setProduct_id(obj.getInt("product_id"));
                                product.setProduct_name(obj.getString("product_name"));
                                product.setBrandId(obj.getInt("brand_id"));
                                product.setProductGroupId(obj.getInt("product_group_id"));
                                product.setBrandName(obj.getString("product_group_title"));
                                product.setBrandName(obj.getString("brand_name"));
                                product.setGradeName(obj.getString("grade_name"));
                                product.setQualityTypeName(obj.getString("quality_type_name"));
                                product.setImageUrl(obj.getString("image"));
                                product.setExist(obj.getBoolean("exist"));
                                if (finalKey.length() >= 1) {
                                    if (obj.getString("product_name").contains(finalKey)) {
                                        products.add(product);
                                    }
                                } else {
                                    products.add(product);
                                }
                                G.stop_loading();
                                adapterListReserveProduct = new AdapterListProduct(products, ProductsActivity.this, new AdapterListProduct.OnAddProductClick() {
                                    @Override
                                    public void onAddProductClick(ReserveProduct reserveProduct) {
                                        if (reserveProduct.getExist())
                                            G.toast("این کالا قبلا به لیست انبار اضافه شده.");
                                        else showBottomSheetDialogInventory(reserveProduct);
                                    }
                                });
                                reserveProductRecycler.setAdapter(adapterListReserveProduct);

                                swipeRefreshLayout.setRefreshing(false);

                            }
                        } else {
                            G.stop_loading();
                            Toast.makeText(ProductsActivity.this, "Failed to retrieve products", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط با سرور");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    private void showBottomSheetDialogInventory(ReserveProduct reserveProduct) {

        AddInventoryDialogBottomSheet bottomSheetDialog = new AddInventoryDialogBottomSheet(this, reserveProduct);
        bottomSheetDialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomSheetDialogTheme);
        bottomSheetDialog.show(getSupportFragmentManager(), "bottomSheet");
    }

    private void showDialogAddNewProduct() {
        AddNewProductDialogBottomSheet bottomSheetDialog = new AddNewProductDialogBottomSheet(this);
        bottomSheetDialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomSheetDialogTheme);
        bottomSheetDialog.show(getSupportFragmentManager(), "bottomSheet");
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
            tv_group.setText(group.getName());
        } else {
            ll_group.setVisibility(View.GONE);
        }
        if (!brand.getName().equals("انتخاب کنید")) {
            ll_brand.setVisibility(View.VISIBLE);
            tv_brand.setText(brand.getName());
        } else {
            ll_brand.setVisibility(View.GONE);
        }
        group_id = group.getId();
        brand_id = brand.getId();
        if (change) refreshFilter(group_id, brand_id, last_key);
    }

    private void loadDate(String key) {
        fetchProducts(group_id, brand_id, key);
//        adapterListReserveProduct.updateData(products);

    }

    private void refreshFilter(String groupId, String brandId, String key) {
        // Start refreshing
        // Simulate data fetching (use your actual data fetching here)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fetchProducts(groupId, brandId, key);

                adapterListReserveProduct.updateData(products);
                // Stop refreshing
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000); // Simulate a delay of 2 seconds, for example
    }

    @Override
    public void onProductAdded(int product_id) {
        refreshItem(product_id);
    }

    @Override
    public void onProductEdited(ModelAddedProductCenter modelAddedProductCenter) {

    }

    public void refreshItem(int product_id) {
        adapterListReserveProduct.updateItemData(product_id);

    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }
}