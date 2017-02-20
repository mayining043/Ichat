package com.ichat.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.example.ichat.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MsgAdapter extends ArrayAdapter<Msg>{
	//xml样式
	private int XMLId;
	private Bitmap chat_icon;
	public MsgAdapter(Context context,int XML, List<Msg>data){
		super(context,XML,data);
		this.XMLId=XML;
	}
	class ViewHolder{
		RelativeLayout left_layout;
		RelativeLayout right_layout;
		TextView left_msg;
		TextView right_msg;
		ImageView left_icon;
		ImageView right_icon;
	}
	private Bitmap getDiskBitmap(File file)  
	{  
	    Bitmap bitmap = null;  
	    try  
	    {  
	        if(file.exists())  
	        {  
	            bitmap = BitmapFactory.decodeFile(file.getPath());  
	        }  
	    } catch (Exception e)  
	    {  
	        // TODO: handle exception  
	    }        
	    return bitmap;  
	}  
	
	
	
	
	private void init(){
		File f = new File("/sdcard/" + "uesr_icon" + ".png");
		chat_icon=getDiskBitmap(f);
	}
	
	
	
	
	public View getView(int position,View convertView, ViewGroup parent){
		init();
		View view;
		ViewHolder viewholder;
		//若存在缓存
		if(convertView!=null){
			view=convertView;
			//读取缓存
			viewholder=(ViewHolder)view.getTag();
		}
		//创建view视图与控件
		else{
			viewholder=new ViewHolder();
			//创建view
			view=LayoutInflater.from(getContext()).inflate(XMLId, null);
			//绑定控件
			viewholder.left_layout=(RelativeLayout)view.findViewById(R.id.left_chat_layout);
			viewholder.right_layout=(RelativeLayout)view.findViewById(R.id.right_chat_layout);
			viewholder.left_msg=(TextView)view.findViewById(R.id.left_msg);
			viewholder.right_msg=(TextView)view.findViewById(R.id.right_msg);
			viewholder.left_icon=(ImageView)view.findViewById(R.id.left_icon);
			viewholder.right_icon=(ImageView)view.findViewById(R.id.right_icon);
			//设置缓存
			view.setTag(viewholder);
		}
		Msg msg=getItem(position);
		if(msg.getType()==Msg.is_received){
			viewholder.left_layout.setVisibility(View.VISIBLE);
			viewholder.right_layout.setVisibility(View.GONE);
			viewholder.left_msg.setText(msg.getMsg());
		}
		else if(msg.getType()==Msg.is_sent){
			viewholder.right_layout.setVisibility(View.VISIBLE);
			viewholder.left_layout.setVisibility(View.GONE);
			viewholder.right_msg.setText(msg.getMsg());
			if(chat_icon!=null)
			viewholder.right_icon.setImageBitmap(chat_icon);
		}
		return view;
	}
}
