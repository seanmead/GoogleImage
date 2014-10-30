package com.googleimage;

import java.util.List;

import com.example.wolfram.R;
import com.mead.request.Request;
import com.mead.request.Response;
import com.mead.request.content.Content;
import com.mead.request.html.Div;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainFragment extends Fragment implements Response, View.OnClickListener{
	
	private static final String SERVER = "https://www.google.com/search?&tbm=isch&q=";
	
	private ImageView mImageView;
	private EditText mEditText;
	private Button mButton;
	private RelativeLayout rootView;
	private Request mRequest;

	public MainFragment() {}
	
	private View $(int id){
		return rootView.findViewById(id);		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = (RelativeLayout) inflater.inflate(R.layout.fragment_main, container, false);
		mImageView = (ImageView) $(R.id.imageView);
		mEditText = (EditText) $(R.id.editText);
		mButton = (Button) $(R.id.button);
		mButton.setOnClickListener(this);
		mRequest = new Request(getActivity(), this);
		return rootView;
	}

	@Override
	public void onDecode(Content arg0) {
		mImageView.setImageBitmap(arg0.Bitmap);
	}

	@Override
	public void onGet(Content arg0) {
		try{
			List<String> images = arg0.parser.getImgs();
			if(images.size() > 0){
				String img = images.get(0);
				String src = img.substring(img.indexOf("src=") + 5);
				mRequest.decode(src.substring(0, src.indexOf("\"")));
			}
		}catch(Exception e){}
		
		
	}

	@Override
	public void onLogin(Content arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPost(Content arg0) {
		
	}

	@Override
	public void onClick(View v) {
		mRequest.get(SERVER + mEditText.getText().toString());
	}
}
