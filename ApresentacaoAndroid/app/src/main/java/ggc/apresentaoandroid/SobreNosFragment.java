package ggc.apresentaoandroid;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class SobreNosFragment extends Fragment {

    public static SobreNosFragment newInstance() {

        SobreNosFragment fragment = new SobreNosFragment();

        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sobre_nos, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SimpleDraweeView ivShot = (SimpleDraweeView)view.findViewById(R.id.iv_sobre);

        Uri uri = Uri.parse("https://fbcdn-sphotos-e-a.akamaihd.net/hphotos-ak-xta1/t31.0-8/12014970_1645557615713670_6032228582527267159_o.jpg");
        DraweeController dc = Fresco.newDraweeControllerBuilder()
                .setUri( uri )
                .setAutoPlayAnimations(true)
                .setOldController( ivShot.getController() )
                .build();
        ivShot.setController(dc);
    }
}
