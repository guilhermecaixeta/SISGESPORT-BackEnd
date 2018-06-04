import { Municipio } from "./municipio.model";

export class Endereco{
    public id: number;
    public cep: string;
    public complemento: string;
    public logradouro: string;
    public bairro: string;

    public municipio: Municipio;

    constructor(){
        this.municipio = new Municipio;
    }
}