package com.delarue.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.format;


public class MainActivity extends AppCompatActivity {

    private EditText peso;
    private EditText altura;
    private TextView resultado;
    private TextView diagnostico;
    private DadosImcDAO dao;

    // Declaração de variaveis


    float imc;
    String mensagem = "";
    boolean dadosValidados;

    float kilos;
    float tamanho;


    // Declaração de Objetos

    EditText editPeso, editAltura;
    TextView txtResultado, txtDiagnostico;
    Button btnCalcular, btnSalvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Setar (Apontar) os Objetos
        peso =  findViewById(R.id.editPeso);
        altura =  findViewById(R.id.editAltura);
        resultado =  findViewById(R.id.txtResultado);
        diagnostico =  findViewById(R.id.txtDiagnostico);

        dao = new DadosImcDAO(this);

        btnCalcular =  findViewById(R.id.btnCalcular);
        btnSalvar =  findViewById(R.id.btnSalvar);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DadosImc d = new DadosImc();

                d.setPeso(peso.getText().toString());
                d.setAltura(altura.getText().toString());
                d.setResultado(resultado.getText().toString());
                d.setDiagnostico(diagnostico.getText().toString());

                long id = dao.inserir(d);

                Toast.makeText(getBaseContext(), "Dados Inseridos Com o ID: " + id, Toast.LENGTH_LONG).show();

            }

        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Setar (Apontar) os Objetos
                editPeso =  findViewById(R.id.editPeso);
                editAltura =  findViewById(R.id.editAltura);
                txtResultado =  findViewById(R.id.txtResultado);
                txtDiagnostico =  findViewById(R.id.txtDiagnostico);

                try {

                    txtResultado.setText("");
                    txtDiagnostico.setText("");

                    dadosValidados = validarCampos();

                    // Recuperar os valores digitados


                    if (dadosValidados) {

                        kilos = Float.parseFloat(editPeso.getText().toString());
                        tamanho = Float.parseFloat(editAltura.getText().toString());


                        imc = kilos / (tamanho * tamanho);

                        // Validação da IMC

                        if (imc < 17) {
                            mensagem = "Muito Abaixo Do Peso";

                        } else if ((imc >= 17) && (imc < 18.5)) {

                            mensagem = "Abaixo Do Peso";

                        } else if ((imc >= 18.5) && (imc < 25)) {

                            mensagem = "Peso Ideal";

                        } else if ((imc >= 25) && (imc < 30)) {

                            mensagem = "Acima Do Peso";

                        } else if ((imc >= 30) && (imc < 35)) {

                            mensagem = "Obesidade Grau I";

                        } else if ((imc >= 35) && (imc < 40)) {

                            mensagem = "Obesidade Grau II";

                        } else {

                            mensagem = "Obesidade Mórbida";

                        }

                        // Mostrar o resultado

                        txtResultado.setText(format("%.2f", imc));
                        txtDiagnostico.setText(mensagem);

                    }

                }catch (Exception e){

                }

            }
        });

    }


    private boolean validarCampos(){

        /* editPeso, editAltura; */

        boolean retorno = false;

        if(!TextUtils.isEmpty(editPeso.getText().toString())) {
            retorno = true;
        }else{
            editPeso.setError("Digite O Peso!");
            editPeso.requestFocus();
        }

            if (!TextUtils.isEmpty(editAltura.getText().toString())) {
                retorno = true;
            } else {
                editAltura.setError("Digite A Altura!");
                editAltura.requestFocus();
            }


        return retorno;
    }



}
