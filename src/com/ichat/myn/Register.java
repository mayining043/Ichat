package com.ichat.myn;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.ichat.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Register extends Activity{
	private Button btn_choose;
	private Button btn_register;
	private ImageView display;
	private Uri imageUri;
	private Bitmap photo;
	private static final int choosephoto=0;
	private static final int CROP_REQUEST_CODE = 4;
	private static final int ALBUM_REQUEST_CODE = 1;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		btn_choose = (Button) findViewById(R.id.btn_choose);
        display = (ImageView) findViewById(R.id.usrhead);
		//Ñ¡ÔñÍ·Ïñ
		btn_choose=(Button)findViewById(R.id.btn_choose);
		btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, ALBUM_REQUEST_CODE);
            }
            
        });
		//×¢²á°´Å¥
		btn_register=(Button)findViewById(R.id.btn_register);
		btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(photo==null)
            	Toast.makeText(Register.this,"Î´Ñ¡ÔñÍ·Ïñ", Toast.LENGTH_SHORT).show();
            else if(saveBitmap(photo)){
            		Intent i=new Intent(Register.this,ChatMain.class);
            		startActivity(i);
            	}
            	else
            		Toast.makeText(Register.this,"×¢²áÊ§°Ü", Toast.LENGTH_SHORT).show();
            }
            
        });
	}
	public boolean saveBitmap(Bitmap bm) {
		 File f = new File("/sdcard/" + "uesr_icon" + ".png");
		  try {
		   f.createNewFile();
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
			  return false;
		  }
		  FileOutputStream fOut = null;
		  try {
		   fOut = new FileOutputStream(f);
		  } catch (FileNotFoundException e) {
		   e.printStackTrace();return false;
		  }
		 bm.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		  try {
		   fOut.flush();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		  try {
		   fOut.close();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		 
		  return true;
		 }
	private void startCrop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
       
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
       
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    private boolean isSDCardCanUser() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case ALBUM_REQUEST_CODE:
                
                if (data == null) {
                    return;
                }
                startCrop(data.getData());
                break;
            
            case CROP_REQUEST_CODE:
               
                if (data == null) {
                    
                    return;
                }
                Bundle extras = data.getExtras();
                if (extras != null) {
                    photo = extras.getParcelable("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);
                    display.setImageBitmap(photo);
                    
                }
                break;
            default:
                break;
        }
    }

}