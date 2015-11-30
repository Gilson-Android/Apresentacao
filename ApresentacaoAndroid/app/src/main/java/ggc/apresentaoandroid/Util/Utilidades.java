package ggc.apresentaoandroid.Util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

/**
 * Created by Gilson on 29/11/2015.
 */
public class Utilidades {
    // Verifica se o dispositivo tem acesso a internet
    public static boolean Conectado(Context _context) {
        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    // Verifica se o aplicativo esta aberto por uma Activity
    public static boolean estaAberto(Activity activity){
        String nomePacote = activity.getPackageName();
        ActivityManager gerenciadorActivity = (ActivityManager) activity.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tarefasRecentes = gerenciadorActivity.getRunningTasks(Integer.MAX_VALUE);
        if(tarefasRecentes != null && tarefasRecentes.size() > 0){
            ActivityManager.RunningTaskInfo informacaoTarefasRodando = tarefasRecentes.get(0);
            String pacote = informacaoTarefasRodando.baseActivity.getPackageName();
            if(pacote.equals(nomePacote)){
                return true;
            }
        }
        return false;
    }

    // Verifica se o aplicativo esta aberto por um Contexto
    public static String estaAberto(Context contexto){
        ActivityManager gerenciadorActivity = (ActivityManager) contexto.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tarefasRecentes = gerenciadorActivity.getRunningTasks(Integer.MAX_VALUE);
        if(tarefasRecentes != null && tarefasRecentes.size() > 0){
            ActivityManager.RunningTaskInfo informacaoTarefasRodando = tarefasRecentes.get(0);
            String topActivity = informacaoTarefasRodando.topActivity.getClassName();
            return topActivity;
        }
        return "";
    }
}
