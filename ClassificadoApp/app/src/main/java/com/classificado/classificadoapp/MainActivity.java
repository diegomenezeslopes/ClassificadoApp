package com.classificado.classificadoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.classificado.classificadoapp.adapter.ListaCategoriaAdapter;
import com.classificado.classificadoapp.bean.Categoria;
import com.classificado.classificadoapp.dao.CategoriaDAO;
import com.classificado.classificadoapp.task.EnviarCategoriaTask;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "CADASTRO_ALUNO";

    private ListView lvListagem;
    private List<Categoria> listaCategorias;
    private ListaCategoriaAdapter adapter;
    private Categoria categoriaSelecionada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvListagem = (ListView) findViewById(R.id.lvListagem);
        registerForContextMenu(lvListagem);

        lvListagem.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                categoriaSelecionada = (Categoria) adapter.getItemAtPosition(position);
                Log.i(TAG, "Categoria selecionada ListView.longClick()" + categoriaSelecionada.getNome());
                return false;
            }
        });

        lvListagem.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent form = new Intent(MainActivity.this, FormularioCategoriaActivity.class);

                categoriaSelecionada = (Categoria) lvListagem.getItemAtPosition(position);

                form.putExtra("CATEGORIA_SELECIONADA", categoriaSelecionada);

                startActivity(form);
            }
        });

        Log.i(TAG, "Execucao do metodo OnCreate()");
    }

    private void carregarLista() {
        // Criacao do objeto DAO - inicio da conexao com BD
        CategoriaDAO dao = new CategoriaDAO(this);
        // chamada ao metodo listar
        this.listaCategorias = dao.listar();
        // Fim da conexao com BD
        dao.close();

        // O objeto ListaAlunoAdapter sabe converter listas de alunos em View
        this.adapter = new ListaCategoriaAdapter(this, listaCategorias);

        // Associacao do Adapter aa ListView
        this.lvListagem.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.carregarLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Definicao do objeto Inflater
        MenuInflater inflater = this.getMenuInflater();

        // Inflar um XML em um Menu vazio
        inflater.inflate(R.menu.menu_main, menu);

        // Exibir o menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = null;

        switch (item.getItemId()){
            case R.id.menu_novo:
                intent = new Intent(MainActivity.this, FormularioCategoriaActivity.class);
                startActivity(intent);

                return false;

            case R.id.menu_enviar_categorias:
                new EnviarCategoriaTask(this).execute();

                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
