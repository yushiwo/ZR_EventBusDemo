package com.example.zr_eventbusdemo;

import android.os.Bundle;  
import android.support.v4.app.Fragment;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.View.OnClickListener;
import android.view.ViewGroup;  
import android.widget.Button;
import android.widget.TextView;  
import de.greenrobot.event.EventBus;  
  
public class ItemDetailFragment extends Fragment  
{  
  
    private TextView tvDetail;  
    private Button mAsyncBtn;  
  
    @Override  
    public void onCreate(Bundle savedInstanceState)  
    {  
        super.onCreate(savedInstanceState);  
        // register  
        EventBus.getDefault().register(this);  
    }  
  
    @Override  
    public void onDestroy()  
    {  
        super.onDestroy();  
        // Unregister  
        EventBus.getDefault().unregister(this);  
    }  
  
    /** List点击时会发送些事件，接收到事件后更新详情 */  
    public void onEventMainThread(Item item)  
    {  
        if (item != null)  
            tvDetail.setText(item.content);  
    }  
    
    public void onEventAsync(Item item)  {  
    	System.out.println("onEventAsync" + item.content);  
    } 
    
    public void onEvent(Item item)  {  
    	System.out.println("onEvent" + item.content);  
    } 
    
    public void onEventBackgroundThread(Item item)  {  
    	System.out.println("onEventBackgroundThread" + item.content);  
    } 
  
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState)  
    {  
        View rootView = inflater.inflate(R.layout.fragment_item_detail,  
                container, false);  
        tvDetail = (TextView) rootView.findViewById(R.id.item_detail);  
        mAsyncBtn = (Button) rootView.findViewById(R.id.async_btn);  
        mAsyncBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EventBus.getDefault().post(new String("Asyns test"));  
			}
		});
        return rootView;  
    }  
}
