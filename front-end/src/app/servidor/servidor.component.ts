import { CadastroCrudComponent } from './../cadastrar/cadastro-crud/cadastro-crud.component';
import { Servidor } from '../model/servidor.model';
import { Validators } from '@angular/forms';
import { requiredMinLength, SomenteNumeros } from '../utils/validators.util.component';

export class ServidorComponent extends CadastroCrudComponent {
  validacaoCustomizada: any = true;
  rota = 'servidor';
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
      cargo: [null, [Validators.required]],
      instituicao: [null, [Validators.required]]
    }),
    endereco: this.construtorFormulario.group({
      id: [null],
      estado: [null, [Validators.required]],
      municipio: [null, [Validators.required]],
      cep: [null, [Validators.required, requiredMinLength(8, true)]],
      complemento: [null, [Validators.required, Validators.maxLength(255)]],
      logradouro: [null, [Validators.required, Validators.maxLength(255)]],
      bairro: [null, [Validators.required, Validators.maxLength(255)]]
    })
  });

  finalizar() {
    if (this.formulario.valid) {
      let servidor = new Servidor(this.formulario.value.cadastro);
      servidor.adicionarEndereco(this.formulario.controls.endereco.value, this.LimparCaracterEspecial(this.formulario.controls.endereco.value.cep));
      this.Persistir(servidor);
    } else {
      this.TocarTodos(this.formulario);
    }
  }
}
