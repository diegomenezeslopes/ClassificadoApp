package com.classificado.classificadoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.classificado.classificadoapp.bean.Categoria;
import com.classificado.classificadoapp.dao.CategoriaDAO;
import com.classificado.classificadoapp.helper.FormularioCategoriaHelper;

/**
 * Created by Diego on 05/10/2015.
 */
public class FormularioCategoriaActivity extends Activity {
    private Button botao;
    private FormularioCategoriaHelper helper;

    private String localArquivo;
    private Categoria categoriaParaSerAlterada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_categoria);
        // Criacao do objeto Helper
        helper = new FormularioCategoriaHelper(this);
        botao = (Button) findViewById(R.id.sbSalvar);

        if (savedInstanceState != null) {
            localArquivo = (String) savedInstanceState
                    .getSerializable("localArquivo");
        }

        categoriaParaSerAlterada = (Categoria) getIntent().getSerializableExtra(
                "ALUNO_SELECIONADO");

        if (categoriaParaSerAlterada != null) {
            // Atualiza a tela com dados do Aluno
            helper.setCategoria(categoriaParaSerAlterada);
        }

        botao.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // Utilizacao do Helper para recuperar dados do Aluno
                Categoria categoria = helper.getCategoria();
                // Criacao do objeto DAO - inicio da conexao com o BD
                CategoriaDAO dao = new CategoriaDAO(FormularioCategoriaActivity.this);

                // Verificacao para salvar ou cadastrar o aluno
                if (categoria.getId() == null) {
                    dao.cadastrar(categoria);
                } else {
                    dao.alterar(categoria);
                }

                // Encerramento da conexao com o Banco de Dados
                dao.close();
                // Encerrando a Activity
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.formulario, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("localArquivo", localArquivo);
    }

}
