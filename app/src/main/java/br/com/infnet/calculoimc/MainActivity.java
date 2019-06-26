package br.com.infnet.calculoimc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    private TextView input_Altura;
    private TextView input_Peso;
    private TextView result_IMC;
    private Button   button_calc_IMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_Altura = findViewById(R.id.inputAltura);
        input_Peso = findViewById(R.id.inputPeso);
        result_IMC = findViewById(R.id.textViewImcResult);
        button_calc_IMC = findViewById(R.id.buttonCalcImc);

        input_Altura.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (input_Altura.getText().length() > 0 && input_Peso.getText().length() > 0){
                    button_calc_IMC.setEnabled(true);
                }
                else{
                    button_calc_IMC.setEnabled(false);
                }
            }
        });
        input_Peso.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (input_Altura.getText().length() > 0 && input_Peso.getText().length() > 0){
                    button_calc_IMC.setEnabled(true);
                }
                else{
                    button_calc_IMC.setEnabled(false);
                }
            }
        });
        button_calc_IMC.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                retornaAcaoBtnCalcular(new BigDecimal(input_Altura.getText().toString()), new BigDecimal(input_Peso.getText().toString()));
            }
        });

    }

    private void retornaAcaoBtnCalcular(BigDecimal altura, BigDecimal peso) {

        String textoValidacao = validacaoInputs(altura, peso);

        if (textoValidacao.length() > 0){
            Toast.makeText(MainActivity.this, textoValidacao, Toast.LENGTH_SHORT).show();
        }
        else{
            BigDecimal resultCalcIMC = calculaIMC(peso, altura);
            String grauObesidade = verificaGrauObesidade(resultCalcIMC);
            result_IMC.setText("IMC " + resultCalcIMC.toString() + "! " + grauObesidade);
        }
    }

    protected String validacaoInputs(BigDecimal altura, BigDecimal peso){
        String textoValidacao = "";

        if (altura.compareTo(BigDecimal.ZERO) == 0){
            textoValidacao += "Altura deve ser maior que 0!";
        }
        if (peso.compareTo(BigDecimal.ZERO) == 0) {
            textoValidacao += " Peso deve ser maior que 0!";
        }

        return textoValidacao;
    }

    protected String verificaGrauObesidade(BigDecimal resultCalcIMC) {

        if  (resultCalcIMC.compareTo(BigDecimal.valueOf(16)) < 0){
            return "Magreza grave";
        }
        else if (resultCalcIMC.compareTo(BigDecimal.valueOf(16)) >= 0 && resultCalcIMC.compareTo(BigDecimal.valueOf(17)) < 0){
            return "Magreza moderada";
        }
        else if (resultCalcIMC.compareTo(BigDecimal.valueOf(17)) >= 0 && resultCalcIMC.compareTo(BigDecimal.valueOf(18.5)) < 0){
            return "Magreza leve";
        }
        else if (resultCalcIMC.compareTo(BigDecimal.valueOf(18.5)) >= 0 && resultCalcIMC.compareTo(BigDecimal.valueOf(25)) < 0){
            return "Saudável";
        }
        else if (resultCalcIMC.compareTo(BigDecimal.valueOf(25)) >= 0 && resultCalcIMC.compareTo(BigDecimal.valueOf(30)) < 0){
            return "Sobrepeso";
        }
        else if (resultCalcIMC.compareTo(BigDecimal.valueOf(30)) >= 0 && resultCalcIMC.compareTo(BigDecimal.valueOf(35)) < 0){
            return "Obesidade Grau I";
        }
        else if (resultCalcIMC.compareTo(BigDecimal.valueOf(35)) >= 0 && resultCalcIMC.compareTo(BigDecimal.valueOf(40)) < 0){
            return "Obesidade Grau II (severa)";
        }
        else if (resultCalcIMC.compareTo(BigDecimal.valueOf(40)) > 0){
            return "Obesidade Grau III (mórbida)";
        }
        else {
            return "Erro ao calcular!";
        }

    }

    protected BigDecimal calculaIMC(BigDecimal peso, BigDecimal altura) {
        altura = altura.divide(BigDecimal.valueOf(100));
        return peso.divide(altura.multiply(altura), 2, RoundingMode.HALF_DOWN);
    }

}
