package ggc.apresentaoandroid.holder;

import android.view.View;
import android.widget.TextView;

import ggc.apresentaoandroid.R;

public class DrawerRegularRowHolder {

    public View mIcon;

    public TextView mRowText;

    public DrawerRegularRowHolder(View view)
    {
        mIcon = view.findViewById(R.id.row_icon);

        mRowText = (TextView) view.findViewById(R.id.drawer_row_line);
    }
}
