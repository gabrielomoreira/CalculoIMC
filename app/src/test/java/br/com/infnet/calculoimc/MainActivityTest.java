package br.com.infnet.calculoimc;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class MainActivityTest {

    private static final MainActivity mainActivity = new MainActivity();


    @Test
    public void deve_RetornarValorDoCalculoIMCComDoisDigitos_QuandoReceverValoresPesoAltura() {
        assertThat(mainActivity.calculaIMC(BigDecimal.valueOf(67.5), BigDecimal.valueOf(167)), is(BigDecimal.valueOf(24.20).setScale(2)));
    }

    @Test
    public void deve_RetornarGrauObesidade_QuandoReceverValorDoIMC() {
        assertThat(mainActivity.verificaGrauObesidade(BigDecimal.valueOf(16.4)), is("Magreza moderada"));
        assertThat(mainActivity.verificaGrauObesidade(BigDecimal.valueOf(18.1)), is("Magreza leve"));
        assertThat(mainActivity.verificaGrauObesidade(BigDecimal.valueOf(20.9)), is("Saudável"));
        assertThat(mainActivity.verificaGrauObesidade(BigDecimal.valueOf(27.2)), is("Sobrepeso"));
        assertThat(mainActivity.verificaGrauObesidade(BigDecimal.valueOf(32.7)), is("Obesidade Grau I"));
        assertThat(mainActivity.verificaGrauObesidade(BigDecimal.valueOf(37.9)), is("Obesidade Grau II (severa)"));
        assertThat(mainActivity.verificaGrauObesidade(BigDecimal.valueOf(42.1)), is("Obesidade Grau III (mórbida)"));
    }


    @Test
    public void deve_RetornarUmaResposta_QuandoReceverAlturaEPeso() {
        assertThat(mainActivity.validacaoInputs(BigDecimal.valueOf(0), BigDecimal.valueOf(1)), is("Altura deve ser maior que 0!"));
        assertThat(mainActivity.validacaoInputs(BigDecimal.valueOf(1), BigDecimal.valueOf(0)), is(" Peso deve ser maior que 0!"));
        assertThat(mainActivity.validacaoInputs(BigDecimal.valueOf(0), BigDecimal.valueOf(0)), is("Altura deve ser maior que 0! Peso deve ser maior que 0!"));
        assertThat(mainActivity.validacaoInputs(BigDecimal.valueOf(1), BigDecimal.valueOf(1)), is(""));
    }

}