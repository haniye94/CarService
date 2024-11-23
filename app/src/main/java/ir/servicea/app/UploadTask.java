package ir.servicea.app;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadTask extends AsyncTask<String, String, Void> {

    private SweetAlertDialog pDialog;

    protected Void doInBackground(String... uls) {
        String fileName = uls[0];
        File sourceFile = new File(uls[0]);
        if (!sourceFile.isFile()) {
            Log.i("UploadTask", "sourceFile");
            return null;
        } else {
            ToastRun(true, false, "");
            final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("uploaded_file", fileName, RequestBody.create(MEDIA_TYPE_PNG, sourceFile))
                    .build();
            String serverURL = G.api_upload + G.preference.getString("upload_pushe", "upload");
            Request request = new Request.Builder().url(serverURL).post(requestBody).build();
            try {
                Response response = client.newCall(request).execute();
                String strResponse = response.body().string();
                G.Log(response + "");
                if (!response.isSuccessful()) {
                    ToastRun(false, false, "مشکل در آپلود تصویر دوباره تلاش کنید");
                } else {
                    if (strResponse.contains("success")) {
                        ToastRun(false, true, "تصویر با موفقیت آپلود شد.");
                    } else {
                        ToastRun(false, false, "مشکل در آپلود تصویر دوباره تلاش کنید");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                ToastRun(false, false, "عدم اتصال به سرور دوباره سعی کنید!!");
            } catch (Exception e) {
                e.printStackTrace();
                ToastRun(false, false, "عدم اتصال به سرور دوباره سعی کنید!!");
            }

//        HttpURLConnection conn;
//        DataOutputStream dos;
//        String lineEnd = "\r\n";
//        String twoHyphens = "--";
//        String boundary = "*****";
//        int bytesRead, bytesAvailable, bufferSize;
//        byte[] buffer;
//        int maxBufferSize = 1024 * 1024;
//                ToastRun(true, false, "");
//                Log.e("tagggggggg", "در حال آپلود تصویر لطفا صبر کنید...");
//                // open a URL connection to the Servlet
//                FileInputStream fileInputStream = new FileInputStream(sourceFile);
//
//                URL url = new URL(G.api_upload + G.preference.getString("upload_pushe", "upload"));
//                Log.e("url",url.toString());
//                // Open a HTTP connection to the URL
//                conn = (HttpURLConnection) url.openConnection();
//                conn.setDoInput(true); // Allow Inputs
//                conn.setDoOutput(true); // Allow Outputs
//                conn.setUseCaches(false); // Don't use a Cached Copy
//                conn.setRequestMethod("POST");
//                conn.setRequestProperty("Connection", "Keep-Alive");
//                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
//                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
//
//                byte[] bytes = IOUtils.toByteArray(fileInputStream);
//
//                conn.setRequestProperty( "Content-Length", Integer.toString( 146  ) );
//                conn.setRequestProperty("uploaded_file", fileName);
//
//                dos = new DataOutputStream(conn.getOutputStream());
//
//                dos.writeBytes(twoHyphens + boundary + lineEnd);
//                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + fileName + "\""
//                        + lineEnd);
//
//                dos.writeBytes(lineEnd);
//
//                // create a buffer of maximum size
//
//                bytesAvailable = fileInputStream.available();
//                bufferSize = Math.min(bytesAvailable, maxBufferSize);
//                buffer = new byte[bufferSize];
//
//                // read file and write it into form...
//                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//
//                while (bytesRead > 0) {
//
//                    dos.write(buffer, 0, bufferSize);
//                    bytesAvailable = fileInputStream.available();
//                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
//                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//
//                }
//
//                // send multipart form data necesssary after file data...
//                dos.writeBytes(lineEnd);
//                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
//
//                // Responses from the server (code and message)
//                int serverResponseCode = conn.getResponseCode();
//                String serverResponseMessage = conn.getResponseMessage();
//
//                Log.i("uploadFile", "HTTP Response is : " + serverResponseMessage + ": " + serverResponseCode);
//
//                if (serverResponseCode == 200) {
//                    ToastRun(false, true, "تصویر با موفقیت آپلود شد.");
//                } else {
//                    ToastRun(false, false, "مشکل در آپلود تصویر دوباره تلاش کنید");
//                }
//
//                fileInputStream.close();
//                dos.flush();
//                dos.close();
//                conn.disconnect();
//            } catch (MalformedURLException ex) {
//                ex.printStackTrace();
//
//                ToastRun(false, false, "عدم اتصال به سرور دوباره سعی کنید!!");
//            } catch (Exception e) {
//
//                e.printStackTrace();
//
//                ToastRun(false, false, "عدم اتصال به سرور دوباره سعی کنید!!");
//
//            }

            return null;

        }
    }

    public void ToastRun(boolean access, boolean success, String txt) {
        if (G.Activity != null && !G.Activity.isFinishing())
            G.Activity.runOnUiThread(new Runnable() {
                public void run() {
                    if (access) {
                        if (pDialog != null && pDialog.isShowing()) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        pDialog = new SweetAlertDialog(G.Activity, SweetAlertDialog.PROGRESS_TYPE);
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#2489ed"));
                        pDialog.setTitleText("در حال آپلود تصویر ... ");
                        pDialog.setContentText("لطفا صبر کنید");
                        pDialog.setCancelable(false);
                        pDialog.show();
                    } else {
                        if (pDialog != null && pDialog.isShowing()) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        int typesweet = SweetAlertDialog.ERROR_TYPE;
                        String typestring = "خطا!!";
                        if (success) {
                            typesweet = SweetAlertDialog.SUCCESS_TYPE;
                            typestring = "موفق";
                        } else {
                            typesweet = SweetAlertDialog.ERROR_TYPE;
                            typestring = "خطا!!";
                        }
                        pDialog = new SweetAlertDialog(G.Activity, typesweet);
                        pDialog.setTitleText(typestring)
                                .setContentText(txt)
                                .setConfirmText("تایید")
                                .showCancelButton(true)
                                .setConfirmClickListener(sDialog -> {
                                    sDialog.dismiss();
                                }).show();
                        if (success) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (G.Activity != null && !G.Activity.isFinishing()) {
                                        if (pDialog != null && pDialog.isShowing()) {
                                            pDialog.dismiss();
                                            pDialog = null;
                                        }
                                    }
                                }
                            }, 2500);
                        }
                    }
                }
            });
    }

    @Override
    protected void onProgressUpdate(String... item) {
    }

    @Override
    protected void onPostExecute(Void unused) {
    }

}