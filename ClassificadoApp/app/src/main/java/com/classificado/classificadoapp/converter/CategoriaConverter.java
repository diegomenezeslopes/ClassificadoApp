package com.classificado.classificadoapp.converter;

import android.util.Log;

import com.classificado.classificadoapp.bean.Categoria;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONStringer;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Diego on 04/10/2015.
 */
public class CategoriaConverter {
    public String toJSON(List<Categoria> listaCategorias){
        try{
            JSONStringer jsonStringer= new JSONStringer();
            jsonStringer.object().key("list").array().object().key("categoria").array();
            for (Categoria categoria : listaCategorias){
                jsonStringer.object()
                        .key("id").value(categoria.getId())
                        .key("nome").value(categoria.getNome())
                        .key("descricao").value(categoria.getDescricao())
                        .key("imageUrl").value(categoria.getImageUrl())
                        .endObject();
            }
            jsonStringer.endArray().endObject().endArray().endObject();
            return jsonStringer.toString();
        } catch (JSONException e){
            Log.i("CADASTRO_CATEGORIA", e.getMessage());
            return null;
        }
    }

    public Categoria toCategoria(String stringJSON){
        Type listType = new TypeToken<List<Categoria>>(){}.getType();

        Categoria categoria = new Gson().fromJson(stringJSON, listType);
        return categoria;
    }
}
