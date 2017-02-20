package com.ichat.myn;


import java.util.ArrayList;
import java.util.List;

import com.example.ichat.R;
import com.ichat.util.Msg;
import com.ichat.util.MsgAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ChatMain extends Activity{
	private ListView msgListView;
	private EditText inputhere;
	private Button send;
	private MsgAdapter adapter;
	private List<Msg>msglist=new ArrayList<Msg>();
	
	private void initMsg(){
		Msg to_sent=new Msg("???",Msg.is_sent);
		msglist.add(to_sent);
		Msg to_sent2=new Msg("!!!",Msg.is_received);
		msglist.add(to_sent2);
		Msg to_sent3=new Msg("。。。0000000000000000000000000",Msg.is_received);
		msglist.add(to_sent3);
	}
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chatmain);
		initMsg();
		//绑定数据
		send=(Button)findViewById(R.id.send);
		inputhere=(EditText)findViewById(R.id.input_text);
		msgListView=(ListView)findViewById(R.id.msg_list_view);
		//设置Adapter
		adapter=new MsgAdapter(ChatMain.this,R.layout.chatframe,msglist);
		msgListView.setAdapter(adapter);
		//响应sent按钮事件
		send.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				String txt=inputhere.getText().toString();
				if(!"".equals(txt)){
					Msg to_sent=new Msg(txt,Msg.is_sent);
					msglist.add(to_sent);
					adapter.notifyDataSetChanged();
					msgListView.setSelection(msglist.size());
					inputhere.setText("");
				}
				else{
					Toast.makeText(ChatMain.this,"消息不能为空！",Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
