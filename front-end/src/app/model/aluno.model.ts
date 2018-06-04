import { Pessoa } from "./base/pessoa.model";
import { Turma } from "./turma.model";
import { FormGroup } from "@angular/forms";

export class Aluno extends Pessoa {
    public turma: Turma;
}