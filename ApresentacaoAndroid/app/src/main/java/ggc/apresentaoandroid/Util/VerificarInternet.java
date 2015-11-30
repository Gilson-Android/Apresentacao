package ggc.apresentaoandroid.Util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import ggc.apresentaoandroid.PrincipalActivity;
import ggc.apresentaoandroid.ServicosActivity;

/**
 * Created by Gilson on 29/11/2015.
 */
public class VerificarInternet extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            if(Utilidades.estaAberto(context) != null){
                if(Utilidades.estaAberto(context).equals(PrincipalActivity.class.getName())){
                    PrincipalActivity.statusConexao(context);
                }else if (Utilidades.estaAberto(context).equals(ServicosActivity.class.getName())){
                    ServicosActivity.statusConexao(context);
                }
            }
        }
    }

}
