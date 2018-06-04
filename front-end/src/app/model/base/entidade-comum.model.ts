import { Endereco } from "../endereco.model";
import { Imagem } from "../imagem.model";
import { FormGroup } from "@angular/forms";
import { Municipio } from "../municipio.model";

export class EntidadeComum{
    public id: number;
    public endereco: Endereco[];
    public imagem: Imagem[];
    
    constructor(){
        this.endereco = [];
        this.imagem = [];
    }

    public adicionarEndereco(form: FormGroup){

        this.endereco.push({
            id: form.value.id,
            bairro: form.value.bairro,
            cep: form.value.cep,
            complemento: form.value.complemento,
            logradouro: form.value.logradouro,
            municipio: {
                id: form.value.municipio,
                estado: null,
                nome: null
            }
        });
    }
}