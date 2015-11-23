package ggc.apresentaoandroid.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ggc.apresentaoandroid.R;

public class DrawerHeaderHolder {


    public ImageView mHeaderCover;

    public ImageView mUserAvatar;

    public TextView mUserName;


    public DrawerHeaderHolder(View view)
    {

        mHeaderCover = (ImageView)view.findViewById(R.id.user_cover);

        mUserAvatar = (ImageView) view.findViewById(R.id.user_avatar);

        mUserName = (TextView) view.findViewById(R.id.user_name);
    }
}
