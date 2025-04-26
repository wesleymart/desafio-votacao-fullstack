package com.desafiofullstask.votacao.util;

import org.springframework.stereotype.Service;

@Service
public class ValidationUtil {

    public static boolean validateCPF(String cpf) {

            if (cpf.isEmpty()) return false;
            String number = cpf;

            /*
                * Adiciona zeros à esquerda
             */
            while (number.length() < 11) {
                number = "0" + number;
            }

            /*
                * Remove caracteres não numéricos
             */
            String filteredNumber = number.replaceAll("[^\\d]", "");
            if (filteredNumber.length() != 11) {
                return false;
            }

            /*
                * Converte para array de inteiros
             */
            int[] numbers = new int[11];
            for (int i = 0; i < 11; i++) {
                numbers[i] = Character.getNumericValue(filteredNumber.charAt(i));
            }

            /*
                * Verifica se todos os dígitos são iguais
             */
            boolean allEqual = true;
            for (int i = 1; i < numbers.length; i++) {
                if (numbers[i] != numbers[0]) {
                    allEqual = false;
                    break;
                }
            }
            if (allEqual) return false;

            /*
                * Calculo do digito 1
             */
            int dv1 = 0;
            for (int i = 0; i <= 8; i++) {
                dv1 += (i + 1) * numbers[i];
            }
            dv1 = dv1 % 11;
            if (dv1 >= 10) dv1 = 0;

            /*
                * Calculo do digito 2
             */
            int dv2 = 0;
            for (int i = 0; i <= 8; i++) {
                dv2 += i * numbers[i];
            }
            dv2 = (dv2 + (dv1 * 9)) % 11;
            if (dv2 >= 10) dv2 = 0;

            /*
                * Verifica se os digitos calculados são iguais aos informados
                * Se sim, o CPF é válido
             */
        return numbers[9] == dv1 && numbers[10] == dv2;
        }
    }

