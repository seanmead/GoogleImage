package com.googleimage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.wolfram.R;
import com.mead.request.Request;
import com.mead.request.Response;
import com.mead.request.content.Content;

public class MainFragment extends Fragment implements Response, View.OnClickListener{
	
	private static final String SERVER = "https://www.google.com/search?&tbm=isch&q=";
	
	private ImageView imageView;
	private EditText editText;
	private RelativeLayout rootView;
	private Request request;

	public MainFragment() {}
	
	private View $(int id){
		return rootView.findViewById(id);		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = (RelativeLayout) inflater.inflate(R.layout.fragment_main, container, false);
		imageView = (ImageView) $(R.id.imageView);
		editText = (EditText) $(R.id.editText);
		((Button) $(R.id.button)).setOnClickListener(this);
		request = new Request(getActivity(), this);
		return rootView;
	}

	@Override
	public void onDecode(Content content) {
		imageView.setImageBitmap(content.Bitmap);
	}

	@Override
	public void onGet(Content content) {
		System.out.println(content.Type);
		content.html();
	}

	@Override
	public void onPost(Content content) {}

	@Override
	public void onClick(View v) {
		request.get(SERVER + editText.getText().toString());
	}

}