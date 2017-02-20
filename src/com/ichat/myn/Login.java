package com.ichat.myn;

import com.example.ichat.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity{
	private Button btn_login;
	private TextView forget;
	private TextView register;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_a);
		//��¼��ť
		btn_login=(Button)findViewById(R.id.btn_login);
		btn_login.setOnClickListener(new OnClickListener(){
			public void onClick(View E){
				EditText t1=(EditText)findViewById(R.id.idnum);
				EditText t2=(EditText)findViewById(R.id.psw);
				final String usn=t1.getText().toString();
				final String psw=t2.getText().toString();
				Toast.makeText(Login.this,"��½�ɹ���",Toast.LENGTH_SHORT).show();
				login(usn,psw);
			}
			public void login(String usn,String psw){
//				Intent login=new Intent(Login.this,ChatMain.class);
//				startActivity(login);
				Intent i1 = new Intent();
				 
				// POI����
				 
				i1.setData(Uri.parse("baidumap://map/place/search?query=��ʳ&region=beijing&location=39.915168,116.403875&radius=1000&bounds=37.8608310000,112.5963090000,42.1942670000,118.9491260000"));
				 
				startActivity(i1);
			}
		});
		//��������
		forget=(TextView)findViewById(R.id.forgetpassword);
		forget.setOnClickListener(new OnClickListener(){
			public void onClick(View E){
				AlertDialog.Builder f=new AlertDialog.Builder(Login.this);
				f.setTitle("�����Ҳ�����T....T");
				f.setMessage("�������룬����ϵһ����(020)18102679254");
				f.setCancelable(true);
				f.show();
			}
		});
		//���û�ע��
		register=(TextView)findViewById(R.id.regist);
		register.setOnClickListener(new OnClickListener(){
			public void onClick(View E){
				Intent reg=new Intent(Login.this,Register.class);
				startActivity(reg);
			}
		});
	}

}
