package br.com.lukas.relatoriodenotas;

import javax.swing.JOptionPane;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class RelatorioDeNotas {
    
    public static void main(String[] args) {
        
        JOptionPane.showMessageDialog(null, "BEM-VINDO AO RELATÓRIO DE NOTAS!");

        int quantidadeDeAlunos = 0;
        int quantidadeDeNotasPorAluno = 0;
        boolean quantidadeInvalida = false;

        /*NESTE BLOCO É DEFINIDO A QUANTIDADE DE ALUNOS PARA O RELATÓRIO,
        O DADO É ARMAZENADO NA VARIÁVEL 'quantidadeDeAlunos' QUE SERÁ USADA COMO SUPORTE POSTERIORMENTE,
        CASO O USUÁRIO INSIRA ALGUM VALOR QUE NÃO SEJA INTEIRO A VARIÁVEL quantidadeInvalida É ACIONADA
        DANDO UM RESETE NO LOOP PEDINDO PARA O USUÁRIO INSERIR NOVAMENTE A INFORMAÇÃO CORRETA*/
        do {
            quantidadeInvalida = false;
            try {
                quantidadeDeAlunos = Integer.parseInt(JOptionPane.showInputDialog(null, "DEFINA A QUANTIDADE DE ALUNOS"));
            } catch (NumberFormatException e) {
                quantidadeInvalida = true;
                JOptionPane.showMessageDialog(null, "info: ENTRADA INVÁLIDA!");
            }
            
            //VALIDAÇÃO PARA ENTRADA SER MAIOR QUE ZERO:
            if (quantidadeDeAlunos <= 0 && !quantidadeInvalida) {
                quantidadeInvalida = true;
                JOptionPane.showMessageDialog(null, "info: A QUANTIDADE DEVE SER MAIOR QUE ZERO!");
            }
            
        } while (quantidadeInvalida == true);

        /*NESTE BLOCO É DEFINIDO A QUANTIDADE DE NOTAS POR ALUNO DE FORMA GLOBAL PARA TODOS OS ALUNOS,
        O DADO É ARMAZENADO NA VARIÁVEL 'quantidadeDeNotasPorAluno' QUE SERÁ TAMBÉM USADA COMO SUPORTE POSTERIORMENTE,
        CASO O USUÁRIO INSIRA ALGUM VALOR QUE NÃO SEJA INTEIRO A VARIÁVEL quantidadeInvalida É ACIONADA
        DANDO UM RESETE NO LOOP PEDINDO PARA O USUÁRIO INSERIR NOVAMENTE A INFORMAÇÃO CORRETA*/
        do {
            quantidadeInvalida = false;
            try {
                quantidadeDeNotasPorAluno = Integer.parseInt(JOptionPane.showInputDialog(null, "DEFINA A QUANTIDADE DE NOTAS POR ALUNO: "));
            } catch (NumberFormatException e) {
                quantidadeInvalida = true;
                JOptionPane.showMessageDialog(null, "info: ENTRADA INVÁLIDA!");
            }
            
            //VALIDAÇÃO PARA ENTRADA SER MAIOR QUE ZERO:
            if (quantidadeDeNotasPorAluno <= 0 && !quantidadeInvalida) {
                quantidadeInvalida= true;
                JOptionPane.showMessageDialog(null, "info: A QUANTIDADE DEVE SER MAIOR QUE ZERO!");
            }
            
        } while (quantidadeInvalida == true);

        /*VETORES (ARRAYS)*/
        String[] nomeDosAlunos = new String[quantidadeDeAlunos];
        String[] statusDeAprovacao = new String[quantidadeDeAlunos];
        BigDecimal[] somaDasNotasDeCadaAluno = new BigDecimal[quantidadeDeAlunos];
        BigDecimal[] mediaFinalDeCadaAluno = new BigDecimal[quantidadeDeAlunos];
        /*VARIÁVEIS SIMPLES*/
        double notaParaConversao = 0.00;
        String notaString = "";
        boolean notaInvalida = false;

        /*NESTE PRIMEIRO FOR É ARMAZENADO NO VETOR 'nomeDosAlunos' O NOME DE TODOS OS ALUNOS PARA RELATÓRIO,
        É INICIALIZADO TAMBÉM O VETOR 'somaDasNotasDeCadaAluno' COMO VALOR ZERO EM CADA POSIÇÃO,
        NO FINAL É FEITO O CÁLCULO DA MÉDIA DE CADA ALUNO COM BASE NA SOMA DAS NOTAS DE CADA UM FEITO NO SEGUNDO FOR
        POR FIM É ARMAZENADO A INFORMAÇÃO NO VETOR 'mediaFinalDeCadaAluno'*/
        for (int i = 0; i < quantidadeDeAlunos; i++) {
            somaDasNotasDeCadaAluno[i] = BigDecimal.ZERO;
            nomeDosAlunos[i] = JOptionPane.showInputDialog(null, "NOME DO " + (i + 1) + "o. ALUNO: ");
            /*NESTE SEGUNDO FOR É FEITO A COLETA DE TODAS AS NOTAS DE CADA ALUNO
            PRIMEIRO É ARMAZENADO NA STRING 'notaString' E POSTERIORMENTE É PASSADA PARA O VETOR 'somaDasNotasDeCadaAluno'*/
            for (int j = 0; j < quantidadeDeNotasPorAluno; j++) {
                do {
                    notaInvalida = false;
                    try {
                        notaParaConversao = Double.parseDouble(JOptionPane.showInputDialog(null, "DIGITE A " + (j + 1) + "a. NOTA DE " + nomeDosAlunos[i] + ": "));
                    } catch (NumberFormatException e) {
                        notaInvalida = true;
                        JOptionPane.showMessageDialog(null, "ERRO! NOTA INVÁLIDA!");
                    }
                    
                    //VALIDAÇÃO PARA NOTA NÃO SER MENOR QUE ZERO:
                    if (notaParaConversao < 0.00 && !notaInvalida) {
                        notaInvalida = true;
                        JOptionPane.showMessageDialog(null, "info: A NOTA NÃO PODE SER MENOR QUE ZERO!");
                    }
                    
                } while (notaInvalida == true);
                notaString = Double.toString(notaParaConversao);
                somaDasNotasDeCadaAluno[i] = somaDasNotasDeCadaAluno[i].add(new BigDecimal(notaString));
            }
            mediaFinalDeCadaAluno[i] = somaDasNotasDeCadaAluno[i].divide(BigDecimal.valueOf(quantidadeDeNotasPorAluno), 2, RoundingMode.HALF_UP);
        }

        /*NESTE FOR É DEFINIDO O STATUS DE APROVAÇÃO DE CADA ALUNO COMO BASE NA MEDIA FINAL
        O DADO É ARMAZENADO NO VETOR 'statusDeAprovacao'*/
        for (int i = 0; i < nomeDosAlunos.length; i++) {
            if (mediaFinalDeCadaAluno[i].doubleValue() < 5.0) {
                statusDeAprovacao[i] = "ALUNO REPROVADO";
            } else if (mediaFinalDeCadaAluno[i].doubleValue() >= 5.0 && mediaFinalDeCadaAluno[i].doubleValue() < 7.0) {
                statusDeAprovacao[i] = "ALUNO EM RECUPERAÇÃO";
            } else if (mediaFinalDeCadaAluno[i].doubleValue() >= 7.0 && mediaFinalDeCadaAluno[i].doubleValue() < 10.0) {
                statusDeAprovacao[i] = "ALUNO APROVADO";
            } else {
                statusDeAprovacao[i] = "ALUNO APROVADO COM LOUVOR!";
            }
        }

        /*AQUI É CHAMADO A CLASSE StringBuilder PARA FAZER A CONCATENAÇÃO DE STRINGS
        DOS DADOS COLETADOS DE TODOS OS ALUNOS QUE VÃO SER ARMAZENADOS NO OBJETO 'mensagem'
        PARA POSTERIORMENTE SER MOSTRADO EM JOPTIONPANE*/
        StringBuilder mensagem = new StringBuilder();
        for (int i = 0; i < nomeDosAlunos.length; i++) {

            mensagem.append("<html>"
                    + "ALUNO " + (i + 1) + "<br>"
                    + "NOME: " + nomeDosAlunos[i] + "<br>"
                    + "MÉDIA: " + mediaFinalDeCadaAluno[i] + "<br>"
                    + "STATUS: " + statusDeAprovacao[i] + "<br>"
                    + "<br>"
                    + "</html>");
        }

        JOptionPane.showMessageDialog(
                null,
                mensagem,
                "RELATÓRIO FINAL",
                JOptionPane.INFORMATION_MESSAGE);
        
    }
    
}