package com.classificado.classificadoapp.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;

import com.classificado.classificadoapp.FormularioCategoriaActivity;
import com.classificado.classificadoapp.MainActivity;
import com.classificado.classificadoapp.R;
import com.classificado.classificadoapp.bean.Categoria;

/**
 * Created by Diego on 05/10/2015.
 */
public class FormularioCategoriaHelper {
    private EditText nome;
    private EditText descricao;
    private EditText imageUrl;

    private Categoria categoria;


    public FormularioCategoriaHelper(FormularioCategoriaActivity activity) {
        nome = (EditText) activity.findViewById(R.id.edNome);
        descricao = (EditText) activity.findViewById(R.id.edDescricao);
        imageUrl = (EditText) activity.findViewById(R.id.edImageUrl);

        categoria = new Categoria();
    }

    public Categoria getCategoria() {

        categoria.setNome(nome.getText().toString());
        categoria.setDescricao(descricao.getText().toString());
        categoria.setImageUrl(imageUrl.getText().toString());


        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        nome.setText(categoria.getNome());
        descricao.setText(categoria.getDescricao());
        imageUrl.setText(categoria.getImageUrl());
    }
}
