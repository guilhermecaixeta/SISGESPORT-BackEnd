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

    public adicionarEndereco(form: any, cep: any){

        this.endereco.push({
            id: form.id,
            bairro: form.bairro,
            cep: cep,
            complemento: form.complemento,
            logradouro: form.logradouro,
            municipio: {
                id: form.municipio,
                estado: null,
                nome: null
            }
        });
    }
}