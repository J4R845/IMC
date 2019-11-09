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

    // Declaração de variaveis

    float peso;
    float altura;
    float imc;
    String mensagem = "";

    boolean dadosValidados;


    // Declaração de Objetos

    EditText editPeso, editAltura;
    TextView txtResultado, txtDiagnostico;
    Button btnCalcular, btnSalvar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setar (Apontar) os Objetos

        editPeso =  findViewById(R.id.editPeso);
        editAltura =  findViewById(R.id.editAltura);
        txtResultado =  findViewById(R.id.txtResultado);
        txtDiagnostico =  findViewById(R.id.txtDiagnostico);
        btnCalcular =  findViewById(R.id.btnCalcular);
        btnSalvar =  findViewById(R.id.btnSalvar);



        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    txtResultado.setText("");
                    txtDiagnostico.setText("");

                    dadosValidados = validarCampos();

                    // Recuperar os valores digitados


                    if (dadosValidados) {

                        peso = Float.parseFloat(editPeso.getText().toString());
                        altura = Float.parseFloat(editAltura.getText().toString());

                        imc = peso / (altura * altura);

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


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getBaseContext(), "Nada Programado!!!", Toast.LENGTH_LONG).show();
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
