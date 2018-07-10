import { FormControl } from "@angular/forms";

/**
 * Verifica se o campo possui somente números.
 * @param form Control a ser validada
 */
export function SomenteNumeros(form: FormControl){
    const regexSoNumero: RegExp = /[^0-9]/;
    if(String(form.value).match(regexSoNumero)){
        return {soNumero: true}
    }
}

/**
 * Verifica se a UF inserida é válida.
 * @param control UF a ser validada
 */
export function validateUF(control: FormControl) {
    const regUFIsValid: RegExp = /(A|a)(C|c|L|l|M|m|P|p)|(B|b)(A|a)|(C|c)(E|e)|(D|d)(F|f)|(E|e)(S|s)|(G|g)(O|o)|(M|m)(A|a|G|g|S|s|T|t)|(P|p)(A|a|B|b|E|e|I|i|R|r)|(R|r)(J|j|N|n|O|o|R|r|S|s)|(S|s)(C|c|E|e|P|p)|(T|t)(O|o)/;
    const uf = control.value ? String(control.value).replace(/[^0-9A-Za-z]/g, '') : '';
    if (uf.search(regUFIsValid) && uf.length >= 2) {
        return { ufValidation: true };
    }
}

/**
 * Verifica se o valor do campo é menor que o valor da variavel passada, retorna mensagem de erro caso verdadeiro
 * @param valueComparer Valor a ser comparado com o tamanho da string do FormControl
 * @param withSpecialCharacter Variavel usada para verificar se o valor a ser comparado irá validar caracteres especiais
 */
export function requiredMinLength(valueComparer, withSpecialCharacter = false) {
    return function (control: FormControl) {
        let value;
        if (!withSpecialCharacter) {
            value = control.value ? String(control.value).replace(/[^0-9A-Za-z]/g, '') : '';
        } else {
            value = control.value ? String(control.value) : '';
        }
        if (value.length > 0) {
            if (value.length < valueComparer) {
                return { minLengthCustom: 'Quantidade de caracter(es) inválido(s).' };
            }
        }
    }
}

/**
 * Verifica se o valor do campo é maior que o valor da variavel passada, retorna mensagem de erro caso verdadeiro
 * @param valueComparer Valor a ser comparado com o tamanho da string do FormControl
 * @param withSpecialCharacter Variavel usada para verificar se o valor a ser comparado irá validar caracteres especiais
 */
export function requiredMaxLength(valueComparer, withSpecialCharacter = false) {
    return function (control: FormControl) {
        let value;
        if (!withSpecialCharacter) {
            value = control.value ? String(control.value).replace(/[^0-9A-Za-z]/g, '') : '';
        } else {
            value = control.value ? String(control.value) : '';
        }
        if (value.length > valueComparer) {
            return { maxLengthCustom: 'Limite máximo de ' + valueComparer + ' caracteres excedido.' };
        }
    }
}
/**
 * Verifica se o valor é maior do que a data passada, retornando mensagem de erro caso verdadeiro.
 * @param dateComparer Data a ser comparada, caso nenhum valor seja passado essa data pega a data atual.
 */
export function validateDateMoreThen(dateComparer: Date = new Date) {
    return function (control: FormControl) {
        if (new Date(control.value) > dateComparer)
            return { dateMoreThen: 'O campo não pode possuir valor maior que a data:' + dateComparer.toLocaleDateString() + '.' }
    }
}

/**
 * Verifica se o valor é menor do que a data passada, retornando mensagem de erro caso verdadeiro.
 * @param dateComparer Data a ser comparada, caso nenhum valor seja passado essa data pega a data atual.
 */
export function validateDateLessThen(dateComparer: Date = new Date) {
    return function (control: FormControl) {
        if (new Date(control.value) < dateComparer)
            return { dateLessThen: 'O campo não pode possuir valor menor que a data:' + dateComparer.toLocaleDateString() + '.' }
    }
}