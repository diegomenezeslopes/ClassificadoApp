package com.classificado.classificadoapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.classificado.classificadoapp.bean.Categoria;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

/**
 * Created by Diego on 05/10/2015.
 */
public class CategoriaDAO extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String TABELA = "Categoria";
    private static final String DATABASE = "ClassificadoBD";

    private static final String TAG = "CADASTRO_CATEGORIA";

    public CategoriaDAO(Context context){
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        String sql = "CREATE TABLE " + TABELA + "("
                + "id INTEGER PRIMARY KEY, "
                + "nome TEXT, descricao TEXT, imageUrl TEXT)";

        database.execSQL(sql);

        Log.i(TAG, "Tabela criada: " + TABELA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int versaoAntiga, int novaVersao){
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        database.execSQL(sql);
        Log.i(TAG, "Tabela excluida: " + TABELA);

        onCreate(database);
    }

    @Override
    public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion){
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        database.execSQL(sql);
        onCreate(database);
    }

    public void cadastrar(Categoria categoria){
        ContentValues values = new ContentValues();

        values.put("id", categoria.getId());
        values.put("nome", categoria.getNome());
        values.put("descricao", categoria.getDescricao());
        values.put("imageUrl", categoria.getImageUrl());

        getWritableDatabase().insert(TABELA, null, values);
        Log.i(TAG, "Categoria cadastrada: " + categoria.getNome());
    }

    public void alterar(Categoria categoria){
        ContentValues values = new ContentValues();
        values.put("id", categoria.getId());
        values.put("nome", categoria.getNome());
        values.put("descricao", categoria.getDescricao());
        values.put("imageUrl", categoria.getImageUrl());

        String[] args = { categoria.getId().toString() };

        getWritableDatabase().update(TABELA, values, "id=?", args);
        Log.i(TAG, "Categoria alterada: " + categoria.getNome());
    }

    public List<Categoria> listar(){

        List<Categoria> lista = new ArrayList<Categoria>();
        String sql = "Select * from Categoria order by nome";

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        try{
            while (cursor.moveToNext()) {
                Categoria categoria = new Categoria();

                categoria.setId(cursor.getLong(0));
                categoria.setNome(cursor.getString(1));
                categoria.setDescricao(cursor.getString(2));
                categoria.setImageUrl(cursor.getString(3));

                lista.add(categoria);
            }
            //SQLException e
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        } finally {
            cursor.close();
        }
        return lista;
    }

    public void deletar(Categoria categoria){
        String[] args = { categoria.getId().toString() };

        getWritableDatabase().delete(TABELA, "id=?", args);

        Log.i(TAG, "Categoria deletada: " + categoria.getNome());
    }
}
