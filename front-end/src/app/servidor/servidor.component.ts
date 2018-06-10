import { Component, OnInit } from '@angular/core';
import { BaseCrudComponent } from '../base';
import { Servidor } from '../model/servidor.model';
import { Validators } from '@angular/forms';
import { requiredMinLength, SomenteNumeros } from '../utils/validators.utils.component';
import { routerTransition } from '../router.animations';

@Component({
  selector: 'app-servidor',
  templateUrl: './servidor.component.html',
  styleUrls: ['./servidor.component.scss'],
  animations: [routerTransition()]
})
export class ServidorComponent extends BaseCrudComponent {
  validacaoCustomizada: any;
  rota= 'servidor';  
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
      cargo: [null],
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
      let servidor = new Servidor(this.formulario.value.cadastro);
      servidor.adicionarEndereco(this.formulario.controls.endereco.value, this.LimparCaracterEspecial(this.formulario.controls.endereco.value.cep));
      this.Persistir(servidor);
    }else{
      this.TocarTodos(this.formulario);
  }
  }
}
