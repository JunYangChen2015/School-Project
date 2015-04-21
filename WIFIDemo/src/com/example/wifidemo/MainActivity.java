package com.example.wifidemo;

import java.util.List;

import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	private Button open_bt,close_bt,search_bt,check_bt;
	private TextView textView;
	private WifiManager wifiManager;
	private WifiInfo wifiInfo;
	private ScrollView scrollView;
	private List WifiConfiguration;
	private android.net.wifi.ScanResult scanResult;
	private List<android.net.wifi.ScanResult> WifiList;
	private StringBuffer stringBuffer=new StringBuffer();
	@Override			
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		scrollView=(ScrollView)findViewById(R.id.mScrollView);
		//加按键
		open_bt=(Button)findViewById(R.id.open_bt);
		close_bt=(Button)findViewById(R.id.close_bt);
		check_bt=(Button)findViewById(R.id.check_bt);
		search_bt=(Button)findViewById(R.id.search_bt);
		textView=(TextView)findViewById(R.id.text);
		
		open_bt.setOnClickListener(new open_btListener());
		close_bt.setOnClickListener(new close_btListener());
		check_bt.setOnClickListener(new check_btListener());
		search_bt.setOnClickListener(new search_btListener());
	}
	//search按键实现
	class search_btListener implements OnClickListener{
		public void onClick(View v){
			wifiManager.startScan();
			WifiList=wifiManager.getScanResults();
			wifiInfo=wifiManager.getConnectionInfo();
			
			if(stringBuffer !=null){
				stringBuffer =new StringBuffer();
			}
			
			stringBuffer=stringBuffer.append("Wifi 名").append("  ")
					.append("Wifi 地址").append("         ")
					.append("Wifi 频率").append("")
					.append("Wifi 信号").append("\n");
			
			if(WifiList!=null){
				for(int i=0;i<WifiList.size();i++){
					scanResult=WifiList.get(i);
					stringBuffer=stringBuffer.append(scanResult.SSID).append("   ")
							.append(scanResult.BSSID).append("                   ")
							.append(scanResult.frequency).append("               ")
							.append(scanResult.level).append("\n");
					
					textView.setText(stringBuffer.toString());
				}
				stringBuffer=stringBuffer.append("------------------------").append("\n");
				textView.setText(stringBuffer.toString());
				stringBuffer=stringBuffer.append("当前Wifi——BSSID").append(":   ").append(wifiInfo.getBSSID()).append("\n")
										 .append("当前Wifi——HiddenSSID").append(":   ").append(wifiInfo.getHiddenSSID()).append("\n")
										 .append("当前Wifi——IpAddress").append(":   ").append(wifiInfo.getIpAddress()).append("\n")
										 .append("当前Wifi——LinkSpeed").append(":   ").append(wifiInfo.getLinkSpeed()).append("\n")
										 .append("当前Wifi——MacAddress").append(":   ").append(wifiInfo.getMacAddress()).append("\n")
										 .append("当前Wifi——Network ID").append(":   ").append(wifiInfo.getNetworkId()).append("\n")
										 .append("当前Wifi——RSSI").append(":   ").append(wifiInfo.getRssi()).append("\n")
										 .append("当前Wifi——SSID").append(":   ").append(wifiInfo.getSSID()).append("\n")
										 .append("——————————————————————————————————————————————————").append("\n")
										 .append("全部打印出关于本机wifi信息").append(":    ").append(wifiInfo.toString());
				
									textView.setText(stringBuffer.toString());
			}
			//stringBuffer=stringBuffer.apper("----------------").append("\n");
			//textView.setText()
		}
	}
	class check_btListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			wifiManager=(WifiManager)MainActivity.this.getSystemService(Context.WIFI_SERVICE);
			System.out.println("wifi state --"+wifiManager.getWifiState());
			Toast.makeText(MainActivity.this,"当前网卡状态为："+change(),Toast.LENGTH_SHORT).show();
		}
	}
	class close_btListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			wifiManager=(WifiManager)MainActivity.this.getSystemService(Context.WIFI_SERVICE);
			wifiManager.setWifiEnabled(false);
			System.out.println("wifi state---->"+wifiManager.getWifiState());
			Toast.makeText(MainActivity.this,"当前网卡状态为："+change(),Toast.LENGTH_SHORT).show();
		}
		
	}
	class open_btListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			wifiManager=(WifiManager)MainActivity.this.getSystemService(Context.WIFI_SERVICE);
			wifiManager.setWifiEnabled(true);
			System.out.println("wifi state---->"+wifiManager.getWifiState());
			Toast.makeText(MainActivity.this, "当前网卡状态为："+change(), Toast.LENGTH_SHORT).show();
		}
		
	}

	public String change(){
		String temp=null;
		switch(wifiManager.getWifiState()){
		case 0:
				temp="Wifi 正在关闭中";
				break;
		case 1:
				temp="Wifi已经关闭";
				break;
		case 2:
				temp="Wifi正在打开中";
				break;
		case 3:
				temp="Wifi已经打开";
				break;
		default:
			break;
		}
		return temp;
	}
}
