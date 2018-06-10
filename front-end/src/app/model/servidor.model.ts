import { Pessoa } from "./base/pessoa.model";
import { Cargo } from "./Cargo.model";

export class Servidor extends Pessoa {
    public cargo: Cargo;

    constructor(obj: any){
        super(obj);
        this.cargo = {
            id: obj.cargo
          };
    }
}