package id.tech.kalbe;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import id.tech.util.CustomAdapter_Img;
import id.tech.util.GPSTracker;
import id.tech.util.Parameter_Collections;
import id.tech.util.Public_Functions;
import id.tech.util.RecyclerAdapter_Promo;
import id.tech.util.RowData_Promo;
import id.tech.util.ServiceHandlerJSON;

/**
 * Created by RebelCreative-A1 on 16/12/2015.
 */
public class Activity_Promo_Add extends AppCompatActivity {
    Button btn;
    String mUrl_Img_00, mUrl_Img_01, mUrl_Img_02, mUrl_Img_03, mUrl_Img_04, mUrl_Img_05, mUrl_Img_06;
    ImageView imgview_00,imgview_01,imgview_02, imgview_03,imgview_04,imgview_05, imgview_06;
    public static int CODE_UPLOAD = 111;
    HorizontalScrollView horizontalScroll;
    CustomAdapter_Img adapter;
    EditText ed_note;
    Spinner spinner;
    SharedPreferences spf;
    private String id_pegawai,id_toko;
    String id_promo;
    List<RowData_Promo> data_spinner;

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        setContentView(R.layout.activity_promo_add);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Promo Activity");

        spf = getSharedPreferences(Parameter_Collections.SH_NAME, Context.MODE_PRIVATE);
        id_toko = spf.getString(Parameter_Collections.SH_ID_TOKO, "");
        id_pegawai = spf.getString(Parameter_Collections.SH_ID_PEGAWAI, "");

        mUrl_Img_00 = null;
        mUrl_Img_01 = null;
        mUrl_Img_02 = null;
        mUrl_Img_03 = null;
        mUrl_Img_04 = null;
        mUrl_Img_05 = null;
        mUrl_Img_06 = null;

        getAllView();
    }

    private void getAllView() {
        horizontalScroll = (HorizontalScrollView)findViewById(R.id.wrapper_horizontalview);
        imgview_00 = (ImageView)findViewById(R.id.img_00);
        imgview_01 = (ImageView)findViewById(R.id.img_01);
        imgview_02 = (ImageView)findViewById(R.id.img_02);
        imgview_03 = (ImageView)findViewById(R.id.img_03);
        imgview_04 = (ImageView)findViewById(R.id.img_04);
        imgview_05 = (ImageView)findViewById(R.id.img_05);
        imgview_06 = (ImageView)findViewById(R.id.img_06);

        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent upload = new Intent(getApplicationContext(),
                        UploadImageDialog.class);
                startActivityForResult(upload, CODE_UPLOAD);
                // adapter = new CustomAdapter_Img(getApplicationContext(), 0,
                // 0, data);

            }
        });

        spinner = (Spinner)findViewById(R.id.spinner);
        ed_note = (EditText)findViewById(R.id.ed_note);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_promo = data_spinner.get(position).id_promo;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                id_promo="";
            }
        });

        new AsyncTask_GetPromo().execute();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (resultCode == RESULT_OK) {

            if (requestCode == CODE_UPLOAD) {

                if (mUrl_Img_00 == null) {
                    horizontalScroll.setVisibility(View.VISIBLE);

                    mUrl_Img_00 = data.getStringExtra("mUrl_Img");
                    Bitmap b = BitmapFactory.decodeFile(mUrl_Img_00);
                    imgview_00.setVisibility(View.VISIBLE);
                    imgview_00.setImageBitmap(b);
                } else if (mUrl_Img_01 == null) {
                    mUrl_Img_01 = data.getStringExtra("mUrl_Img");
                    Bitmap b = BitmapFactory.decodeFile(mUrl_Img_01);
                    imgview_01.setVisibility(View.VISIBLE);
                    imgview_01.setImageBitmap(b);
                } else if (mUrl_Img_02 == null) {
                    mUrl_Img_02 = data.getStringExtra("mUrl_Img");
                    Bitmap b = BitmapFactory.decodeFile(mUrl_Img_02);
                    imgview_02.setVisibility(View.VISIBLE);
                    imgview_02.setImageBitmap(b);
                } else if (mUrl_Img_03 == null) {
                    mUrl_Img_03 = data.getStringExtra("mUrl_Img");
                    Bitmap b = BitmapFactory.decodeFile(mUrl_Img_03);
                    imgview_03.setVisibility(View.VISIBLE);
                    imgview_03.setImageBitmap(b);
                }else if (mUrl_Img_04 == null) {
                    mUrl_Img_04 = data.getStringExtra("mUrl_Img");
                    Bitmap b = BitmapFactory.decodeFile(mUrl_Img_04);
                    imgview_04.setVisibility(View.VISIBLE);
                    imgview_04.setImageBitmap(b);
                } else if (mUrl_Img_05 == null) {
                    mUrl_Img_05 = data.getStringExtra("mUrl_Img");
                    Bitmap b = BitmapFactory.decodeFile(mUrl_Img_05);
                    imgview_05.setVisibility(View.VISIBLE);
                    imgview_05.setImageBitmap(b);
                } else if (mUrl_Img_06 == null) {
                    mUrl_Img_06 = data.getStringExtra("mUrl_Img");
                    Bitmap b = BitmapFactory.decodeFile(mUrl_Img_06);
                    imgview_06.setVisibility(View.VISIBLE);
                    imgview_06.setImageBitmap(b);
                }

            }

        }

    }


    private class AsyncTask_GetPromo extends AsyncTask<Void,Void,Void>{
        private String total_data;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            data_spinner = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ServiceHandlerJSON sh = new ServiceHandlerJSON();

            JSONObject jObj = sh.json_get_promo_system();
            try{
                total_data = jObj.getString(Parameter_Collections.TAG_TOTAL_VISIT_MAX_TOKO);
                if(Integer.parseInt(total_data) > 0){
                    JSONArray jArray = jObj.getJSONArray(Parameter_Collections.TAG_DATA);
                    for(int i=0; i < jArray.length();i++){
                        JSONObject c = jArray.getJSONObject(i);

                        String id_promo = c.getString(Parameter_Collections.TAG_ID_PROMO);
                        String promo_name = c.getString(Parameter_Collections.TAG_NAME_PROMO);

                        data_spinner.add(new RowData_Promo(id_promo,promo_name));
                    }
                }
            }catch (JSONException e){

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(!total_data.equals("0")){
                String[] values = new String[data_spinner.size()];

                for(int i=0; i<data_spinner.size(); i++){
                    values[i] = data_spinner.get(i).promo_name;
                }

                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item,
                        values );
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
                spinner.setAdapter(spinnerAdapter);

            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.updatebrand, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case android.R.id.home:
                Public_Functions.delete_IssuePhoto();
                Toast.makeText(getApplicationContext(), "Canceled. Images deleted", Toast.LENGTH_LONG).show();
                finish();
                break;

            case R.id.action_send_updatebranding:

                GPSTracker gps = new GPSTracker(getApplicationContext());
                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    spf.edit().putString(Parameter_Collections.TAG_LONGITUDE_NOW, String.valueOf(longitude)).commit();
                    spf.edit().putString(Parameter_Collections.TAG_LATITUDE_NOW, String.valueOf(latitude)).commit();


                    if (Public_Functions.isNetworkAvailable(getApplicationContext())) {
//				boolean b = true;
//				if (b) {
                        new AsyncTask_Submit().execute();
                    }else {
                        Toast.makeText(getApplicationContext(),
                                "No Internet Connection, Cek Your Network",
                                Toast.LENGTH_LONG).show();
                    }


                } else {

                    if(Public_Functions.isNetworkAvailable(getApplicationContext())){
                        new AsyncTask_Submit().execute();
                    }else {
                        Toast.makeText(getApplicationContext(),
                                "No Internet Connection, Cek Your Network",
                                Toast.LENGTH_LONG).show();
                    }

                    Toast.makeText(
                            getApplicationContext(),
                            "Can not get your location now, Sent your last locations",
                            Toast.LENGTH_LONG).show();
                }

                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        Public_Functions.delete_IssuePhoto();
        Toast.makeText(getApplicationContext(), "Canceled. Images deleted", Toast.LENGTH_LONG).show();
        finish();
    }

    private class AsyncTask_Submit extends AsyncTask<Void, Void, String>{
        ProgressDialog pdialog;
        String respondMessage;
        JSONObject jsonResult;
        DialogFragmentProgress dialogProgress;
        String cDesc ,cNamaToko, cKet;
        int serverRespondCode = 0;

        String url_file00, url_file01, url_file02, url_file03, url_file04, url_file05, url_file06;
        File sourceFile00,sourceFile01, sourceFile02, sourceFile03,sourceFile04, sourceFile05, sourceFile06;
        FileInputStream fileInputStream00,fileInputStream01, fileInputStream02, fileInputStream03
                ,fileInputStream04, fileInputStream05, fileInputStream06;
        private String row_count, note;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            dialogProgress = new DialogFragmentProgress();
            dialogProgress.show(getSupportFragmentManager(), "");

            note= ed_note.getText().toString();
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            return uploadDataForm(mUrl_Img_00, mUrl_Img_01, mUrl_Img_02, mUrl_Img_03
                    , mUrl_Img_04, mUrl_Img_05, mUrl_Img_06);
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            JSONObject jObj = jsonResult;
            try{
                row_count = jObj.getString(Parameter_Collections.TAG_ROWCOUNT);

            }catch(JSONException e){
                row_count = "0";

            }

            if(row_count.equals("1")){
                dialogProgress.dismiss();

                DialogLocationConfirmation dialog = new DialogLocationConfirmation();
                dialog.setContext(getApplicationContext());
                dialog.setText("Input Promo Activity Success");
                dialog.setFrom(9);
                dialog.setCancelable(false);
                dialog.show(getSupportFragmentManager(), "");
//				Toast.makeText(getApplicationContext(), "Update Branding Success", Toast.LENGTH_LONG).show();
//				finish();
            }else{
                dialogProgress.dismiss();

                DialogLocationConfirmation dialog = new DialogLocationConfirmation();
                dialog.setContext(getApplicationContext());
                dialog.setText(result);
                dialog.setFrom(9);
                dialog.setCancelable(false);
                dialog.show(getSupportFragmentManager(), "");
//				Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
//				finish();
            }


        }

        private String uploadDataForm(String url_gambar00, String url_gambar01,
                                      String url_gambar02, String url_gambar03, String url_gambar04,
                                      String url_gambar05, String url_gambar06){
            HttpURLConnection conn = null;
            DataOutputStream dos = null;
            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";
            int bytesRead, bytesAvailable, bufferSize;
            byte[] buffer;
            int maxBufferSize = 1 * 1024 * 1024;



            if(url_gambar00 != null){
                url_file00 = url_gambar00;
                sourceFile00 = new File(url_file00);
            }
            if(url_gambar01 != null){
                url_file01 = url_gambar01;
                sourceFile01 = new File(url_file01);
            }
            if(url_gambar02 != null){
                url_file02 = url_gambar02;
                sourceFile02 = new File(url_file02);
            }if(url_gambar03 != null){
                url_file03 = url_gambar03;
                sourceFile03 = new File(url_file03);
            }if(url_gambar04 != null){
                url_file04 = url_gambar04;
                sourceFile04 = new File(url_file04);
            }
            if(url_gambar05 != null){
                url_file05 = url_gambar05;
                sourceFile05 = new File(url_file05);
            }if(url_gambar06 != null){
                url_file06 = url_gambar06;
                sourceFile06 = new File(url_file06);
            }

            try {
                URL url = new URL(Parameter_Collections.URL_INSERT);

                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setRequestMethod("POST");

                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type",
                        "multipart/form-data;boundary=" + boundary);
                if(url_gambar00 != null){
                    conn.setRequestProperty("img0", url_file00);
                }
                if(url_gambar01 != null){
                    conn.setRequestProperty("img1", url_file01);
                }
                if(url_gambar02 != null){
                    conn.setRequestProperty("img2", url_file02);
                }
                if(url_gambar03 != null){
                    conn.setRequestProperty("img3", url_file03);
                }
                if(url_gambar04 != null){
                    conn.setRequestProperty("img4", url_file04);
                }
                if(url_gambar05 != null){
                    conn.setRequestProperty("img5", url_file05);
                }
                if(url_gambar06 != null){
                    conn.setRequestProperty("img6", url_file06);
                }

                dos = new DataOutputStream(conn.getOutputStream());

                if(url_gambar00 != null){
                    fileInputStream00 = new FileInputStream(
                            sourceFile00);
                    //img 00
                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=\"img0\";filename=\""
                            + url_file00 + "\"" + lineEnd);
                    dos.writeBytes(lineEnd);

                    bytesAvailable = fileInputStream00.available();

                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];

                    bytesRead = fileInputStream00.read(buffer, 0, bufferSize);
                    while (bytesRead > 0) {
                        dos.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream00.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream00.read(buffer, 0, bufferSize);
                    }

                    dos.writeBytes(lineEnd);
                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                }if(url_gambar01 != null){
                    fileInputStream01 = new FileInputStream(
                            sourceFile01);
                    //img 01
                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=\"img1\";filename=\""
                            + url_file01 + "\"" + lineEnd);
                    dos.writeBytes(lineEnd);

                    bytesAvailable = fileInputStream01.available();

                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];

                    bytesRead = fileInputStream01.read(buffer, 0, bufferSize);
                    while (bytesRead > 0) {
                        dos.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream01.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream01.read(buffer, 0, bufferSize);
                    }

                    dos.writeBytes(lineEnd);
                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                }if(url_gambar02 != null){
                    fileInputStream02 = new FileInputStream(
                            sourceFile02);
                    //img 02
                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=\"img2\";filename=\""
                            + url_file02 + "\"" + lineEnd);
                    dos.writeBytes(lineEnd);

                    bytesAvailable = fileInputStream02.available();

                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];

                    bytesRead = fileInputStream02.read(buffer, 0, bufferSize);
                    while (bytesRead > 0) {
                        dos.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream02.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream02.read(buffer, 0, bufferSize);
                    }

                    dos.writeBytes(lineEnd);
                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                }if(url_gambar03 != null){
                    fileInputStream03 = new FileInputStream(
                            sourceFile03);
                    //img 03
                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=\"img3\";filename=\""
                            + url_file03 + "\"" + lineEnd);
                    dos.writeBytes(lineEnd);

                    bytesAvailable = fileInputStream03.available();

                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];

                    bytesRead = fileInputStream03.read(buffer, 0, bufferSize);
                    while (bytesRead > 0) {
                        dos.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream03.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream03.read(buffer, 0, bufferSize);
                    }

                    dos.writeBytes(lineEnd);
                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                }if(url_gambar04 != null){
                    fileInputStream04 = new FileInputStream(
                            sourceFile04);
                    //img 01
                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=\"img4\";filename=\""
                            + url_file04 + "\"" + lineEnd);
                    dos.writeBytes(lineEnd);

                    bytesAvailable = fileInputStream04.available();

                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];

                    bytesRead = fileInputStream04.read(buffer, 0, bufferSize);
                    while (bytesRead > 0) {
                        dos.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream04.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream04.read(buffer, 0, bufferSize);
                    }

                    dos.writeBytes(lineEnd);
                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                }if(url_gambar05 != null){
                    fileInputStream05 = new FileInputStream(
                            sourceFile05);
                    //img 02
                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=\"img5\";filename=\""
                            + url_file05 + "\"" + lineEnd);
                    dos.writeBytes(lineEnd);

                    bytesAvailable = fileInputStream05.available();

                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];

                    bytesRead = fileInputStream05.read(buffer, 0, bufferSize);
                    while (bytesRead > 0) {
                        dos.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream05.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream05.read(buffer, 0, bufferSize);
                    }

                    dos.writeBytes(lineEnd);
                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                }if(url_gambar06 != null){
                    fileInputStream06 = new FileInputStream(
                            sourceFile06);
                    //img 03
                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=\"img6\";filename=\""
                            + url_file06 + "\"" + lineEnd);
                    dos.writeBytes(lineEnd);

                    bytesAvailable = fileInputStream06.available();

                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];

                    bytesRead = fileInputStream06.read(buffer, 0, bufferSize);
                    while (bytesRead > 0) {
                        dos.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream06.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream06.read(buffer, 0, bufferSize);
                    }

                    dos.writeBytes(lineEnd);
                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                }



                // param kind
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\""
                        + Parameter_Collections.KIND + "\"" + lineEnd);
                dos.writeBytes(lineEnd);
                dos.writeBytes(Parameter_Collections.KIND_PROMO_ACTIVITY+ lineEnd);

                // param mobile
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\""
                        + Parameter_Collections.KIND_MOBILE + "\"" + lineEnd);
                dos.writeBytes(lineEnd);
                dos.writeBytes("true" + lineEnd);



                // param kode toko
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\""
                        + Parameter_Collections.TAG_ID_TOKO + "\"" + lineEnd);
                dos.writeBytes(lineEnd);
                dos.writeBytes(id_toko + lineEnd);

                Log.e("Kode Toko", id_toko);

                // param id pegawai
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\""
                        + Parameter_Collections.TAG_ID_PEGAWAI + "\"" + lineEnd);
                dos.writeBytes(lineEnd);
                dos.writeBytes(id_pegawai + lineEnd);

                // param id pegawai
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\""
                        + Parameter_Collections.TAG_ID_PROMO + "\"" + lineEnd);
                dos.writeBytes(lineEnd);
                dos.writeBytes(id_promo + lineEnd);

                // param latitude
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\""
                        + Parameter_Collections.TAG_LAT_PROMO_ACTIVITY + "\"" + lineEnd);
                dos.writeBytes(lineEnd);
                dos.writeBytes(spf.getString(Parameter_Collections.TAG_LATITUDE_NOW, "") + lineEnd);

                // param longitude
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\""
                        + Parameter_Collections.TAG_LONG_PROMO_ACTIVITY + "\"" + lineEnd);
                dos.writeBytes(lineEnd);
                dos.writeBytes(spf.getString(Parameter_Collections.TAG_LONGITUDE_NOW, "")+ lineEnd);

                // param keterangan
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\""
                        + Parameter_Collections.TAG_NOTE + "\"" + lineEnd);
                dos.writeBytes(lineEnd);
                dos.writeBytes(note+ lineEnd);

                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);


                serverRespondCode = conn.getResponseCode();
                respondMessage = conn.getResponseMessage();

                Log.e("RESPOND", respondMessage);

                if (serverRespondCode == 200) {
                    Log.e("CODE ", "Success Upload");
                } else {
                    Log.e("CODE ", "Success failed");
                }


                if(url_gambar00 != null){
                    fileInputStream00.close();
                }if(url_gambar01 != null){
                    fileInputStream01.close();
                }if(url_gambar02 != null){
                    fileInputStream02.close();
                }if(url_gambar03 != null){
                    fileInputStream03.close();
                }if(url_gambar04 != null){
                    fileInputStream04.close();
                }if(url_gambar05 != null){
                    fileInputStream05.close();
                }if(url_gambar06 != null){
                    fileInputStream06.close();
                }


                dos.flush();

                InputStream is = conn.getInputStream();
                int ch;

                StringBuffer buff = new StringBuffer();
                while ((ch = is.read()) != -1) {
                    buff.append((char) ch);
                }
                Log.e("publish", buff.toString());

                jsonResult = new JSONObject(buff.toString());
                dos.close();

            }catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return respondMessage;
        }
    }
}
