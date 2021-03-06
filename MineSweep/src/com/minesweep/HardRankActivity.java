package com.minesweep;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.listAdapter.RankListAdapter;
import com.xmlparse.HttpClientConnector;
import com.xmlparse.RankInfo;
import com.xmlparse.RankInfoParse;

public class HardRankActivity extends ListActivity{
	private List<RankInfo> rankInfos= new  ArrayList<RankInfo>();
	private ListView rankeasy_list;
	private ProgressDialog dialog;
	public void onCreate(Bundle savedInstanceState)
	{
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.hard_rank);
		 
		 dialog = new ProgressDialog(this);		 
		 rankeasy_list=(ListView)findViewById(android.R.id.list);
		 
		 showEasyRankList();
	}
	
	public void showEasyRankList(){
		new AsyncTask<Void, Void, Boolean>(){

			@Override
			protected Boolean doInBackground(Void... arg0) {
				// TODO Auto-generated method stub
				try{
					final String rankInfoUrl = "http://10.0.2.2:8080/Test?level=hard&which_use=4";
					String rankInfoMessage = HttpClientConnector.getStringByUrl(rankInfoUrl);
					
					if(rankInfoMessage != ""&&rankInfoMessage!=null)
					{
						rankInfos = RankInfoParse.parse(rankInfoMessage);
						Log.v("rankInfos", rankInfos.toString());
						return true;
					}
					
				}catch (Exception e){
					e.printStackTrace();
				}
				return false;
			}
			
			protected void onPostExecute(Boolean result){
				super.onPostExecute(result);
				dialog.hide();
				
				if(result)
				{
					setListAdapter(new RankListAdapter(HardRankActivity.this,
							getListView(),rankInfos));
					
					for(int i=0;i<rankInfos.size();i++)
						Log.v("rankInfo", rankInfos.get(i).getUsername());
					
				}else{
					Toast.makeText(HardRankActivity.this, "网络数据加载失败", Toast.LENGTH_LONG).show();
				}
			}
			
			protected void onPreExecute(){
				super.onPreExecute();
				dialog.setMessage("正在加载排行数据...");
				dialog.show();
			}
			
		}.execute();
	}
}

