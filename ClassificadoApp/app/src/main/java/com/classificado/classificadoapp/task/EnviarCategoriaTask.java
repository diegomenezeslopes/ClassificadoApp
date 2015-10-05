package com.classificado.classificadoapp.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.classificado.classificadoapp.bean.Categoria;
import com.classificado.classificadoapp.converter.CategoriaConverter;
import com.classificado.classificadoapp.dao.CategoriaDAO;
import com.classificado.classificadoapp.suport.WebClient;

import java.util.List;

/**
 * Created by Diego on 05/10/2015.
 */
public class EnviarCategoriaTask extends AsyncTask<Object, Object, String> {
    private final String url = "http://rankingfuncionarios.esy.es/process.php";

    private Context context;

    private ProgressDialog progressDialog;

    public EnviarCategoriaTask(Context context){
        this.context = context;
    }

    protected void onPreExecute(){
        progressDialog = ProgressDialog.show(context, "Aguarde...", "Enviando Dados para o servidor web", true, true);
    }

    protected String doInBackground(Object... params){
        CategoriaDAO dao = new CategoriaDAO(context);
        List<Categoria> lista = dao.listar();
        dao.close();

        String json = new CategoriaConverter().toJSON(lista);
        String jsonResposta = new WebClient(url).post(json);

        return jsonResposta;
    }

    protected void onPostExecute(String result){
        progressDialog.dismiss();

        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
