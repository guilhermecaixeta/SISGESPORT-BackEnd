import { FormControl } from "@angular/forms";

export function SomenteNumeros(form: FormControl){
    const regexSoNumero: RegExp = /[^0-9]/;
    if(String(form.value).match(regexSoNumero)){
        return {soNumero: true}
    }
}