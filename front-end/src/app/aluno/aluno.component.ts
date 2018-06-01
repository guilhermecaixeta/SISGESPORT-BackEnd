import { Component, OnInit } from '@angular/core';
import { BaseCrudComponent } from '../base/base-crud.component';
import { routerTransition } from '../router.animations';
import { Validators } from '@angular/forms';

@Component({
  selector: 'app-aluno',
  templateUrl: './aluno.component.html',
  styleUrls: ['./aluno.component.scss'],
  animations: [routerTransition()]
})
export class AlunoComponent extends BaseCrudComponent {
  validacaoCustomizada: any;

  formulario = this.construtorFormulario.group({
    id: [null],
    nome: [null, [Validators.required, Validators.minLength(5)]],
    matricula: [null, [Validators.required]],
    email: [null, [Validators.required, Validators.email]],
    senha: [null, [Validators.required, Validators.minLength(5)]],
    confirmarSenha: [null, [Validators.required]],
    dataNascimento: [null, [Validators.required]],
    sexo: [null, [Validators.required]],
    endereco: this.construtorFormulario.group({
      id:[null],
      cep:[null,[Validators.required, Validators.maxLength(8)]],
      complemento: [null,[Validators.required, Validators.maxLength(255)]],
      logradouro:[null,[Validators.required, Validators.maxLength(255)]],
      bairro: [null,[Validators.required, Validators.maxLength(255)]],
      municipio: [null],
    })
  });

  multiValidacao = 
  {
    formulario: this.formulario,
    eValido: false
  };
   
  finalizar(){
    this.Persistir(this.formulario.value);
  }
}
