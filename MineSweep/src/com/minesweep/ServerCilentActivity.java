package com.minesweep;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class ServerCilentActivity extends Activity {
	//联机游戏选择按钮
	private ImageButton server_bt1;
	private ImageButton server_bt2;
	private ImageButton server_bt3;
		
	public void onCreate(Bundle savedInstanceState) {
		//全屏显示
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servercilentactivity);
        server_bt1=(ImageButton)findViewById(R.id.server_bt1);
        server_bt2=(ImageButton)findViewById(R.id.server_bt2);
        server_bt3=(ImageButton)findViewById(R.id.server_bt3);
        server_bt1.getBackground().setColorFilter(0xFFEEEE00, PorterDuff.Mode.MULTIPLY);
        server_bt2.getBackground().setColorFilter(0xFFEEEE00, PorterDuff.Mode.MULTIPLY);
        server_bt3.getBackground().setColorFilter(0xFFEEEE00, PorterDuff.Mode.MULTIPLY);
        //建立游戏按钮监听
        server_bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(ServerCilentActivity.this, ServerActivity.class);
				startActivity(intent);
			}
		});
        //加入游戏按钮监听
        server_bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(ServerCilentActivity.this, CilentActivity.class);
				startActivity(intent);
			}
		});
        //返回主界面按钮监听
        server_bt3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ServerCilentActivity.this.finish();				
			}
		});
	}
}