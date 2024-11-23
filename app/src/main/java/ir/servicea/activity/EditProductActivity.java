package ir.servicea.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import ir.servicea.R;
import ir.servicea.app.G;
import ir.servicea.model.ReserveProduct;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProductActivity extends AppCompatActivity {

    private ImageView productImage;
    private EditText productName, productPrice, productBrand, productDescription;
    private Spinner productTypeSpinner;
    private Button updateProductButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);  // Replace with your layout file name

        // Initialize UI components
        productImage = findViewById(R.id.product_image);
        productName = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price);
        productBrand = findViewById(R.id.product_brand);
        productTypeSpinner = findViewById(R.id.product_type_spinner);
        productDescription = findViewById(R.id.product_description);
        updateProductButton = findViewById(R.id.update_product_button);

        // Set up the Update Product button click listener
        updateProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduct();
            }
        });
    }

    private void updateProduct() {
        // Get the input values
        String name = productName.getText().toString().trim();
        String price = productPrice.getText().toString().trim();
        String brand = productBrand.getText().toString().trim();
        String type = productTypeSpinner.getSelectedItem().toString();
        String description = productDescription.getText().toString().trim();

        // Validate inputs
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(price) || TextUtils.isEmpty(brand)) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a Product object
        ReserveProduct product = new ReserveProduct(name, price, brand, type, description,0);

        // Get Retrofit instance and create EditProductApi service
        Api apiService = RetrofitClient.createService(Api.class, G.api_username, G.api_password);

        // Make the API call
        Call<Void> call = apiService.editProduct(product);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EditProductActivity.this, "Product updated successfully", Toast.LENGTH_SHORT).show();
                    // Optionally finish the activity or navigate to another screen
                    finish();
                } else {
                    Toast.makeText(EditProductActivity.this, "Failed to update product: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(EditProductActivity.this, "Failed to update product: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
