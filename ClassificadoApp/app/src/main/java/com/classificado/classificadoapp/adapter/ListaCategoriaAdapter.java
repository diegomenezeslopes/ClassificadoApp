package com.classificado.classificadoapp.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.classificado.classificadoapp.R;
import com.classificado.classificadoapp.bean.Categoria;

import java.util.List;

/**
 * Created by Diego on 05/10/2015.
 */
public class ListaCategoriaAdapter extends BaseAdapter {

    private final List<Categoria> listaCategorias;
    private Activity activity;

    public ListaCategoriaAdapter(Activity activity, List<Categoria> listaCategorias){
        this.listaCategorias = listaCategorias;
        this.activity = activity;
    }

    @Override
    public int getCount(){
        return listaCategorias.size();
    }

    @Override
    public long getItemId(int posicao){
        return listaCategorias.get(posicao).getId();
    }

    @Override
    public Object getItem(int posicao){
        return listaCategorias.get(posicao);
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent){
        View view = activity.getLayoutInflater().inflate(R.layout.item, null);

        Categoria categoria = listaCategorias.get(posicao);

        if (posicao % 2 == 0){
            view.setBackgroundColor(activity.getResources().getColor(R.color.linha_par));
        } else {
            view.setBackgroundColor(activity.getResources().getColor(R.color.linha_impar));
        }

        TextView nome = (TextView) view.findViewById(R.id.itemNome);
        nome.setText(categoria.getNome());

        Bitmap bmp;
        bmp = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);

        bmp = Bitmap.createScaledBitmap(bmp, 100, 100, true);
        ImageView foto  = (ImageView) view.findViewById(R.id.itemFoto);
        foto.setImageBitmap(bmp);

        return view;
    }
}
