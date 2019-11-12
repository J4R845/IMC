package com.delarue.imc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DadosImcDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public DadosImcDAO(Context context) {

        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(DadosImc dadosImc) {

        ContentValues values = new ContentValues();

        values.put("peso", dadosImc.getPeso());
        values.put("altura", dadosImc.getAltura());
        values.put("resultado", dadosImc.getResultado());
        values.put("diagnostico", dadosImc.getDiagnostico());

        return banco.insert("dadosImc", null, values);
    }

}
