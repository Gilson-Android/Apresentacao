package ggc.apresentaoandroid;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gilson on 21/11/2015.
 */
public class HomeFragments extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Inflate the layout for this fragment

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SimpleDraweeView ivShot = (SimpleDraweeView)view.findViewById(R.id.iv_sobre);

        Uri uri = Uri.parse("https://scontent-mia1-1.xx.fbcdn.net/hphotos-xat1/v/t1.0-9/12038001_1644867635782668_91244613768593808_n.jpg?oh=c4aca595b5ee208c971bba071d2ea44d&oe=56EAE112");
        DraweeController dc = Fresco.newDraweeControllerBuilder()
                .setUri( uri )
                .setAutoPlayAnimations(true)
                .setOldController( ivShot.getController() )
                .build();
        ivShot.setController(dc);
    }
}


