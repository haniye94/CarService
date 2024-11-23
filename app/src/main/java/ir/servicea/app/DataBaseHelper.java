package ir.servicea.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import ir.servicea.model.ModelServicesCustomer;
import ir.servicea.model.ModelgetCount;
import ir.servicea.model.dbModel.ModelAddMessage;
import ir.servicea.model.dbModel.ModelCustomer;
import ir.servicea.model.dbModel.ModelKhadamat;
import ir.servicea.model.dbModel.ModelSaveKhadamat;
import ir.servicea.model.dbModel.ModelServices;

public class DataBaseHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "AutoAervice.db";
    private final static String DATABASE_PATH = "/databases/";
    private final static int DATABASE_VERSION = 6;
    private static Context context;
    private static final String TAG = "SeasonOpenHelper";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void copyDatabaseFromAsset() throws IOException {
        InputStream myInput = context.getAssets().open(DATABASE_NAME);
        String outFileName = getDatabasePath();
        File f = new File(context.getApplicationInfo().dataDir + DATABASE_PATH);
        if (!f.exists()) {
            f.mkdir();
        }
        OutputStream myoutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myoutput.write(buffer, 0, length);
        }
        myoutput.flush();
        myoutput.close();
        myInput.close();
        Log.d(TAG, "copyDatabaseFromAsset: ");
    }

    public SQLiteDatabase openDatabase() throws SQLException {
        File dbFile = new File(getDatabasePath());
        Log.d(TAG, "openDatabase:  " + dbFile.toString());
        if (!dbFile.exists()) {
            this.getWritableDatabase();
            try {
                copyDatabaseFromAsset();
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database");
            }
        }
        Log.d(TAG, "openDatabase: ");
        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    private static String getDatabasePath() {
        String path = context.getApplicationInfo().dataDir + DATABASE_PATH + DATABASE_NAME;
        Log.d(TAG, "getDatabasePath: " + path);
        return path;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void closeDatabase(SQLiteDatabase mDatabase) {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public List<ModelCustomer> getListCustomers(SQLiteDatabase mDatabase) {
        List<ModelCustomer> productList = new ArrayList<>();
        // openDatabase();
        try {
            Cursor cursor = mDatabase.rawQuery("SELECT * FROM customers", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelCustomer product = new ModelCustomer(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10));

                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //    closeDatabase(mDatabase);
        } catch (Exception m) {
            //Toast.makeText(context, m + " m00 ", Toast.LENGTH_SHORT).show();
        }
        return productList;

    }


    public int getCountCustomers(SQLiteDatabase mDatabase) {
        int count = 0;
        try {
            Cursor cursor = mDatabase.rawQuery("SELECT COUNT(id) AS count FROM customers", null);
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                //  count = cursor.getInt(cursor.getColumnIndex("count"));
                count = cursor.getInt(0);
                cursor.close();
            }
        } catch (Exception m) {
            //Toast.makeText(context, m + " m00 ", Toast.LENGTH_SHORT).show();
        }
        return count;

    }


    public int getLastIdService(SQLiteDatabase mDatabase) {
        int count = 0;
        try {
            Cursor cursor = mDatabase.rawQuery("SELECT id FROM services ORDER BY id DESC", null);
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                //  count = cursor.getInt(cursor.getColumnIndex("count"));
                count = cursor.getInt(0);
                cursor.close();
            }
        } catch (Exception m) {
            //Toast.makeText(context, m + " m00 ", Toast.LENGTH_SHORT).show();
        }
        return count;

    }


    public List<ModelCustomer> getCustomersInfo(SQLiteDatabase mDatabase, int id) {
        List<ModelCustomer> productList = new ArrayList<>();
        // openDatabase();
        try {
            Cursor cursor = mDatabase.rawQuery("SELECT * FROM customers WHERE id = " + id, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelCustomer product = new ModelCustomer(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10));

                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //    closeDatabase(mDatabase);
        } catch (Exception m) {
            //Toast.makeText(context, m + " m00 ", Toast.LENGTH_SHORT).show();
        }
        return productList;
    }

    public List<ModelCustomer> searchCustomersMain(SQLiteDatabase mDatabase, String plak, String phone) {
        List<ModelCustomer> productList = new ArrayList<>();
        //openDatabase();
        try {

            Cursor cursor = mDatabase.rawQuery("SELECT * FROM customers WHERE plak like '%" + plak + "%' OR phone like '%" + phone + "%'", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelCustomer product = new ModelCustomer(

                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10));

                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //   closeDatabase(mDatabase);
        } catch (Exception m) {
            Toast.makeText(context, m + " m ", Toast.LENGTH_SHORT).show();
        }
        return productList;

    }

    public List<ModelCustomer> searchCustomersNameFamily(SQLiteDatabase mDatabase, String first_name, String last_name) {
        List<ModelCustomer> productList = new ArrayList<>();
        //openDatabase();
        try {

            Cursor cursor = mDatabase.rawQuery("SELECT * FROM customers WHERE first_name like '%" + first_name + "%' OR last_name like '%" + last_name + "%'", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelCustomer product = new ModelCustomer(

                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10));

                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //   closeDatabase(mDatabase);
        } catch (Exception m) {
            Toast.makeText(context, m + " m ", Toast.LENGTH_SHORT).show();
        }
        return productList;

    }

    public List<ModelCustomer> searchCustomersPhone(SQLiteDatabase mDatabase, String phone) {
        List<ModelCustomer> productList = new ArrayList<>();
        //openDatabase();
        try {
            Cursor cursor = mDatabase.rawQuery("SELECT * FROM customers WHERE phone like '%" + phone + "%'", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelCustomer product = new ModelCustomer(

                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10));

                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //   closeDatabase(mDatabase);
        } catch (Exception m) {
            Toast.makeText(context, m + " m ", Toast.LENGTH_SHORT).show();
        }
        return productList;

    }

    public List<ModelServicesCustomer> searchServiseNameFamily(SQLiteDatabase mDatabase, String first_name, String last_name) {
        List<ModelServicesCustomer> productList = new ArrayList<>();
        //openDatabase();
        try {

            Cursor cursor = mDatabase.rawQuery("SELECT * FROM services WHERE first_name like '%" + first_name + "%' OR last_name like '%" + last_name + "%'", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelServicesCustomer product = new ModelServicesCustomer(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getInt(12),
                        cursor.getInt(13),
                        cursor.getInt(14),
                        cursor.getString(15),
                        cursor.getString(16),
                        cursor.getString(17),
                        cursor.getString(18),
                        cursor.getString(19),
                        cursor.getString(20),
                        cursor.getString(21),
                        cursor.getString(22),
                        cursor.getString(23),
                        cursor.getString(24));
                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //   closeDatabase(mDatabase);
        } catch (Exception m) {
            Toast.makeText(context, m + " m ", Toast.LENGTH_SHORT).show();
        }
        return productList;

    }

    public List<ModelServicesCustomer> searchServicesPhone(SQLiteDatabase mDatabase, String phone) {
        List<ModelServicesCustomer> productList = new ArrayList<>();
        //openDatabase();
        try {
            Cursor cursor = mDatabase.rawQuery("SELECT * FROM services WHERE phone like '%" + phone + "%'", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelServicesCustomer product = new ModelServicesCustomer(

                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getInt(12),
                        cursor.getInt(13),
                        cursor.getInt(14),
                        cursor.getString(15),
                        cursor.getString(16),
                        cursor.getString(17),
                        cursor.getString(18),
                        cursor.getString(19),
                        cursor.getString(20),
                        cursor.getString(21),
                        cursor.getString(22),
                        cursor.getString(23),
                        cursor.getString(24));
                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //   closeDatabase(mDatabase);
        } catch (Exception m) {
            Toast.makeText(context, m + " m ", Toast.LENGTH_SHORT).show();
        }
        return productList;

    }

    public List<ModelServicesCustomer> searchServisesPlak(SQLiteDatabase mDatabase, String plak) {
        List<ModelServicesCustomer> productList = new ArrayList<>();
        //openDatabase();
        try {

            Cursor cursor = mDatabase.rawQuery("SELECT * FROM services WHERE plak like '%" + plak + "%'", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelServicesCustomer product = new ModelServicesCustomer(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getInt(12),
                        cursor.getInt(13),
                        cursor.getInt(14),
                        cursor.getString(15),
                        cursor.getString(16),
                        cursor.getString(17),
                        cursor.getString(18),
                        cursor.getString(19),
                        cursor.getString(20),
                        cursor.getString(21),
                        cursor.getString(22),
                        cursor.getString(23),
                        cursor.getString(24));

                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //   closeDatabase(mDatabase);
        } catch (Exception m) {
            Toast.makeText(context, m + " m ", Toast.LENGTH_SHORT).show();
        }
        return productList;

    }

    public List<ModelServices> searchServiceForCustomers(SQLiteDatabase mDatabase, int id_cus) {
        List<ModelServices> productList = new ArrayList<>();
        //openDatabase();
        try {

            Cursor cursor = mDatabase.rawQuery("SELECT * FROM services WHERE id = " + id_cus, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelServices product = new ModelServices(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getInt(12));

                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //   closeDatabase(mDatabase);
        } catch (Exception m) {
            Toast.makeText(context, m + " m ", Toast.LENGTH_SHORT).show();
        }
        return productList;

    }

    public long insertCustomers(String first_name, String last_name, String gender, String date_birthday, String phone, String plak, String name_car, String type_car, String type_fuel, String date_save_customer) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("first_name", first_name);
        values.put("last_name", last_name);
        values.put("gender", gender);
        values.put("date_birthday", date_birthday);
        values.put("phone", phone);
        values.put("plak", plak);
        values.put("name_car", name_car);
        values.put("type_car", type_car);
        values.put("type_fuel", type_fuel);
        values.put("date_save_customer", date_save_customer);

        long id = sqLiteDatabase.insert("customers", null, values);
        return id;
    }

    public void updateServices(int id, String date_services, String km_now, String km_next, String avg_function, String all_services_price, String description) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date_services", date_services);
        values.put("km_now", km_now);
        values.put("km_nex", km_next);
        values.put("avg_function", avg_function);
        values.put("all_services_price", all_services_price);
        values.put("description", description);
        sqLiteDatabase.update("services", values, "id = " + id, null);
        sqLiteDatabase.close();
    }

    public void updateCustomers(int id, String first_name, String last_name, String gender, String date_birthday, String phone, String plak, String name_car, String type_car, String type_fuel, String date_save_customer) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("first_name", first_name);
        values.put("last_name", last_name);
        values.put("gender", gender);
        values.put("date_birthday", date_birthday);
        values.put("phone", phone);
        values.put("plak", plak);
        values.put("name_car", name_car);
        values.put("type_car", type_car);
        values.put("type_fuel", type_fuel);
        values.put("date_save_customer", date_save_customer);

        sqLiteDatabase.update("customers", values, "id = " + id, null);
        sqLiteDatabase.close();
    }

    public List<ModelServices> getListServises(SQLiteDatabase mDatabase) {
        List<ModelServices> productList = new ArrayList<>();
        //     openDatabase();
        try {
            Cursor cursor = mDatabase.rawQuery("SELECT * FROM services", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelServices product = new ModelServices(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getInt(12));

                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //   closeDatabase(mDatabase);
        } catch (Exception m) {
            
        }
        return productList;

    }

    public List<ModelServicesCustomer> getListServisesJoinCusromer(SQLiteDatabase mDatabase) {
        List<ModelServicesCustomer> productList = new ArrayList<>();
        //     openDatabase();
        try {
            Cursor cursor = mDatabase.rawQuery("SELECT * FROM services JOIN customers ON services.id_customer = customers.id ", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelServicesCustomer product = new ModelServicesCustomer(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getInt(12),
                        cursor.getInt(13),
                        cursor.getInt(14),
                        cursor.getString(15),
                        cursor.getString(16),
                        cursor.getString(17),
                        cursor.getString(18),
                        cursor.getString(19),
                        cursor.getString(20),
                        cursor.getString(21),
                        cursor.getString(22),
                        cursor.getString(23),
                        cursor.getString(24));

                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //   closeDatabase(mDatabase);
        } catch (Exception m) {
            
        }
        return productList;

    }

    public List<ModelServicesCustomer> getListServisesJoinCusromerss(SQLiteDatabase mDatabase, int id) {
        List<ModelServicesCustomer> productList = new ArrayList<>();
        //     openDatabase();
        try {
            Cursor cursor = mDatabase.rawQuery("SELECT * FROM services JOIN customers ON services.id_customer = customers.id WHERE services.id_customer= " + id, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelServicesCustomer product = new ModelServicesCustomer(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getInt(12),
                        cursor.getInt(13),
                        cursor.getInt(14),
                        cursor.getString(15),
                        cursor.getString(16),
                        cursor.getString(17),
                        cursor.getString(18),
                        cursor.getString(19),
                        cursor.getString(20),
                        cursor.getString(21),
                        cursor.getString(22),
                        cursor.getString(23),
                        cursor.getString(24));

                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //   closeDatabase(mDatabase);
        } catch (Exception m) {
            
        }
        return productList;

    }

    public ModelServices getServisGhabli(int id_customer, int id_servicer, SQLiteDatabase mDatabase) {
        ModelServices productList = null;
        //     openDatabase();
        try {
            Cursor cursor = mDatabase.rawQuery("SELECT * FROM services WHERE id_customer =" + id_customer + " AND id=" + id_servicer + " ORDER BY id DESC", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelServices product = new ModelServices(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getInt(12));
                productList = product;
                cursor.moveToNext();
            }
            cursor.close();
            //   closeDatabase(mDatabase);
        } catch (Exception m) {
            
        }
        return productList;

    }

    public List<ModelServices> getServishayeGhabli(String id_customer, SQLiteDatabase mDatabase) {
        List<ModelServices> productList = new ArrayList<>();
        //     openDatabase();
        try {
            Cursor cursor = mDatabase.rawQuery("SELECT * FROM services WHERE id_customer =" + id_customer + " ORDER BY id DESC", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelServices product = new ModelServices(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getInt(12));

                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //   closeDatabase(mDatabase);
        } catch (Exception m) {
            
        }
        return productList;

    }

    public void insertServices(String date_services, String km_now, String km_next, String avg_function, String all_services_price, String description, String first_name, String last_name, String name_car, String phone, String plak, String id_customer) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("date_services", date_services);
        values.put("km_now", km_now);
        values.put("km_nex", km_next);
        values.put("avg_function", avg_function);
        values.put("all_services_price", all_services_price);
        values.put("description", description);
        values.put("first_name", first_name);
        values.put("last_name", last_name);
        values.put("name_car", name_car);
        values.put("phone", phone);
        values.put("plak", plak);
        values.put("id_customer", id_customer);
        //  values.put("id_khadamat", id_khadamat);

        sqLiteDatabase.insert("services", null, values);
    }

    public void insertGProduct(String onvan) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("onvan", onvan);


        sqLiteDatabase.insert("onvan_product_group", null, values);
    }

    public boolean deleteServise(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete("services", "id" + "=" + id, null) > 0;
    }

    public boolean deleteCustomer(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete("customers", "id" + "=" + id, null) > 0;
    }

    public void updateKhadamat(int id, int status, int id_customer) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", status);
        values.put("id_customer", id_customer);

        sqLiteDatabase.update("khadamat", values, "id = " + id, null);
        sqLiteDatabase.close();
    }

    public void updateKhadamatType(String title, String type, String value) {
        G.Log("title: " + title);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("type", type+"Z_Z"+value);

//        values.put("id_customer", id_customer);

        sqLiteDatabase.update("save_khadamat", values, "title = '" + title+"'", null);
        sqLiteDatabase.close();
    }

    public List<ModelKhadamat> getListKhadamat(SQLiteDatabase mDatabase) {
        List<ModelKhadamat> productList = new ArrayList<>();
        try {
            Cursor cursor = mDatabase.rawQuery("SELECT * FROM khadamat", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelKhadamat product = new ModelKhadamat(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getInt(6),
                        cursor.getInt(7));

                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //   closeDatabase(mDatabase);
        } catch (Exception m) {
            
        }
        return productList;
    }

    public List<ModelKhadamat> getListKhadamatType(SQLiteDatabase mDatabase, int id) {
        List<ModelKhadamat> productList = new ArrayList<>();
        //     openDatabase();
        try {
            Cursor cursor = mDatabase.rawQuery("SELECT * FROM khadamat WHERE status =1 or status =2 AND id_service =" + id, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelKhadamat product = new ModelKhadamat(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getInt(6),
                        cursor.getInt(7));

                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //   closeDatabase(mDatabase);
        } catch (Exception m) {
            
        }
        return productList;
    }


    public void insertdetectProGroup(String title, int status, int id_service, String type, String value) {
        G.Log(title + " - " + status + " - " + id_service);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("title", title);
        values.put("status", status);
        values.put("id_service", id_service);
        values.put("type", (type+"").replace("null","")+"Z_Z"+value);
G.Log("insertdetectProGroup: "+values.toString());
        sqLiteDatabase.insert("save_khadamat", null, values);
    }

    public boolean deletedetectProGroup(String id, String title) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete("save_khadamat", "id_service=? and title=?" + " = " + title, new String[]{id, title}) > 0;
    }

    public void deleteRow(String id, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + "save_khadamat" + " WHERE " + "id_service" + "='" + id + "'" + " AND title='" + title + "'");
        db.close();
    }

    public void deleteHistoryKhadamt(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + "save_khadamat" + " WHERE " + "id_service" + "='" + id + "'");
        db.close();
    }

    public List<ModelSaveKhadamat> getListsave_khadamat(SQLiteDatabase mDatabase, int id) {
        List<ModelSaveKhadamat> productList = new ArrayList<>();
        try {
            Cursor cursor = mDatabase.rawQuery("SELECT * FROM save_khadamat WHERE id_service=" + id, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                G.Log("cursor");
                G.Log(cursor.getInt(0) + "");
                G.Log(cursor.getString(5));
                String type_value = cursor.getString(5);
                String type =  cursor.getString(5);
                String value =  "";
                if(type_value.length()>4 && type_value.contains("Z_Z")){
                    type = type_value.split("Z_Z")[0];
                    value = type_value.split("Z_Z")[1];
                }else{
                    type = "";
                    value =  "";
                }
                ModelSaveKhadamat product = new ModelSaveKhadamat(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        type, value);

                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //   closeDatabase(mDatabase);
        } catch (Exception m) {

        }
        return productList;
    }

    public void insertMessage(String title, String text, int status, String key) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("title", title);
        values.put("text", text);
        values.put("status", status);
        values.put("key", key);

        sqLiteDatabase.insert("create_msg", null, values);
    }

    public List<ModelAddMessage> getListMessage(SQLiteDatabase mDatabase) {
        List<ModelAddMessage> productList = new ArrayList<>();
        //     openDatabase();
        try {
            Cursor cursor = mDatabase.rawQuery("SELECT * FROM create_msg ", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelAddMessage product = new ModelAddMessage(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4));

                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //   closeDatabase(mDatabase);
        } catch (Exception m) {
            
        }
        return productList;
    }

    public int getTaskCount(SQLiteDatabase mDatabase, int tasklist_Id) {
        int count = 0;
        try {
            Cursor cursor = mDatabase.rawQuery(
                    "SELECT COUNT (*) FROM " + "save_khadamat" + " WHERE " + "id_service" + "=?",
                    new String[]{String.valueOf(tasklist_Id)}
            );
            //  if (null != cursor)
            // if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            count = cursor.getCount();
            //  }
            cursor.close();
            // mDatabase.close();
        } catch (Exception m) {
            
        }
        return count;
    }

    public int getCount(SQLiteDatabase mDatabase, int id) {
        String countQuery = "SELECT * FROM save_khadamat WHERE id_service = " + id;
//      SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    public List<ModelgetCount> getcountt(SQLiteDatabase mDatabase, int id) {
        List<ModelgetCount> productList = new ArrayList<>();
        //     openDatabase();
        try {
            Cursor cursor = mDatabase.rawQuery("SELECT id,COUNT(id) FROM save_khadamat WHERE id_service= " + id + " AND status = 1", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelgetCount product = new ModelgetCount(
                        cursor.getInt(0),
                        cursor.getInt(1));

                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //   closeDatabase(mDatabase);
        } catch (Exception m) {
            
        }
        return productList;
    }

    public List<ModelgetCount> getcountt2(SQLiteDatabase mDatabase, int id) {
        List<ModelgetCount> productList = new ArrayList<>();
        //     openDatabase();
        try {
            Cursor cursor = mDatabase.rawQuery("SELECT status,COUNT(id) FROM save_khadamat WHERE id_service= " + id + " AND status = 2", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ModelgetCount product = new ModelgetCount(
                        cursor.getInt(0),
                        cursor.getInt(1));

                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            //   closeDatabase(mDatabase);
        } catch (Exception m) {
            
        }
        return productList;
    }


    public void insertInfoGroup(String time_start, String time_end, String time_start2, String time_end2, int count_place, String enteza, String paziraee, String khedmat) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("time_start", time_start);
        values.put("time_end", time_end);
//        values.put("time_start2", time_start2);
//        values.put("time_end2", time_end2);
        values.put("count_place", count_place);
        values.put("enteza", enteza);
        values.put("paziraee", paziraee);
        values.put("khedmat", khedmat);

        sqLiteDatabase.insert("save_info", null, values);
    }

}

