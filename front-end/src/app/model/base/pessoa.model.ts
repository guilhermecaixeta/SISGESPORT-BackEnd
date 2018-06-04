import { EntidadeComum } from "./entidade-comum.model";

export class Pessoa extends EntidadeComum{
    public nome: string;
    public sexo: string;
    public matricula: string;
    public senha: string;
    public email: string;
    public dataNascimento: Date;
    public perfil: string;

    constructor(){
        super();
        this.perfil = "ROLE_USUARIO";
    }
}