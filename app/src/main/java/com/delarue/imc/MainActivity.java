package com.delarue.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Declaração de vaiaveis

    float peso;
    float altura;
    float imc;
    String mensagem = "";



    // Declaração de Objetos

    EditText editPeso, editAltura;
    TextView txtResultado, txtDiagnostico;
    Button btnCalcular;


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


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                // Recuperar os valores digitados

                peso = Float.parseFloat(editPeso.getText().toString());
                altura = Float.parseFloat(editAltura.getText().toString());

                imc = peso / (altura * altura);

                // Validação da IMC

                if (imc < 17){
                    mensagem = "Muito Abaixo Do Peso";

                }else if ((imc >= 17) && (imc < 18.5)){

                    mensagem = "Abaixo Do Peso";

                }else if ((imc >= 18.5) && (imc < 25)){

                    mensagem = "Peso Ideal";

                }else if ((imc >= 25) && (imc < 30)) {

                    mensagem = "Acima Do Peso";

                }else if ((imc >= 30) && (imc < 35)) {

                    mensagem = "Obesidade Grau I";

                }else if ((imc >= 35) && (imc < 40)) {

                    mensagem = "Obesidade Grau II";

                }else {

                    mensagem = "Obesidade Mórbida";

                }

                // Mostrar o resultado

                txtResultado.setText(String.valueOf(imc));
                txtDiagnostico.setText(mensagem);

            }

                });
    }
}
