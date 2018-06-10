import { Pessoa } from "./base/pessoa.model";
import { Turma } from "./turma.model";
import { FormGroup } from "@angular/forms";

export class Aluno extends Pessoa {
    public turma: Turma;

    constructor(obj: any){
        super(obj);
        this.turma = {
            id: obj.turma
          };
    }
}