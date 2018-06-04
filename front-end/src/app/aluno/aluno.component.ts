import { Component, OnInit } from '@angular/core';
import { BaseCrudComponent } from '../base/base-crud.component';
import { routerTransition } from '../router.animations';
import { Validators, FormGroup, FormArray, FormControl } from '@angular/forms';
import { SomenteNumeros, requiredMinLength } from '../utils/validators.utils.component';
import { Pessoa } from '../model/base/pessoa.model';
import { Aluno } from '../model/aluno.model';
import { FormComponent } from '../layout/form/form.component';

@Component({
  selector: 'app-aluno',
  templateUrl: './aluno.component.html',
  styleUrls: ['./aluno.component.scss'],
  animations: [routerTransition()]
})
export class AlunoComponent extends BaseCrudComponent {
  validacaoCustomizada: any;
  rota= 'aluno';  
  formulario = this.construtorFormulario.group({
    cadastro: this.construtorFormulario.group({
      id: [null],
      nome: [null, [Validators.required, Validators.minLength(5)]],
      matricula: [null, [Validators.required, SomenteNumeros]],
      email: [null, [Validators.required, Validators.email]],
      senha: [null, [Validators.required, Validators.minLength(5)]],
      confirmarSenha: [null, [Validators.required]],
      dataNascimento: [null, [Validators.required]],
      sexo: [null, [Validators.required]],
      curso: [null],
      turma: [null],
      instituicao: [null]
    }),
    endereco: this.construtorFormulario.group({
      id:[null],
      estado: [null,[Validators.required]],
      municipio: [null,[Validators.required]],
      cep:[null,[Validators.required, requiredMinLength(8, true)]],
      complemento: [null,[Validators.required, Validators.maxLength(255)]],
      logradouro:[null,[Validators.required, Validators.maxLength(255)]],
      bairro: [null,[Validators.required, Validators.maxLength(255)]]
    })
  });

  multiValidacao = 
  {
    formulario: this.formulario.controls.cadastro,
    eValido: false
  };
 
  finalizar(){
    if(this.formulario.valid){
      let aluno = new Aluno;
      aluno.id = this.formulario.value.cadastro.id;
      aluno.nome = this.formulario.value.cadastro.nome;
      aluno.dataNascimento = new Date(`${this.formulario.value.cadastro.dataNascimento.year}-${this.formulario.value.cadastro.dataNascimento.month}-${this.formulario.value.cadastro.dataNascimento.day}`);
      aluno.sexo = this.formulario.value.cadastro.sexo;
      aluno.senha = this.formulario.value.cadastro.senha; 
      aluno.email = this.formulario.value.cadastro.email;
      aluno.matricula = this.formulario.value.cadastro.matricula;
      aluno.turma = {
        id: this.formulario.value.cadastro.turma
      };
      this.formulario.get('endereco.cep').setValue(this.LimparCaracterEspecial(this.formulario.controls.endereco.value.cep));
      aluno.adicionarEndereco(this.formulario.controls.endereco as FormGroup);
      this.Persistir(aluno);
    }else{
      this.TocarTodos(this.formulario);
  }
    
  }
}
