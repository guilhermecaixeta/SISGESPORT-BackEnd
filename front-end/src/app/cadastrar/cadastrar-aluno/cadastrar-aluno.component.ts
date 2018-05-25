import { Component, OnInit } from '@angular/core';
import { BaseCrudComponent } from '../../base-components/base-crud.component';

@Component({
  selector: 'app-cadastrar-aluno',
  templateUrl: './cadastrar-aluno.component.html',
  styleUrls: ['./cadastrar-aluno.component.scss']
})
export class CadastrarAlunoComponent extends BaseCrudComponent {
  formulario = this.form.group({
    nome: [],
    matricula: [],
    email: [],
    senha: [],
    confimarSenha: [],
    dataNascimento: [],
    sexo: [],
    endereco: this.form.group({
      complemento: []
    })
  });

  init() {
    this.formulario.get('confimarSenha').valueChanges.subscribe(data => {
      if (this.Contains(this.formulario.get('senha').value, data)) {

      }
    });
  }
}
