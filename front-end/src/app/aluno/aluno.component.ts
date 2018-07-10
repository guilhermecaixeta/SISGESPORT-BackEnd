import { CadastroCrudComponent } from '../cadastrar/cadastro-crud/cadastro-crud.component';
import { Validators } from '@angular/forms';
import { SomenteNumeros, requiredMinLength } from '../utils/validators.util.component';
import { Aluno } from '../model/aluno.model';

export class AlunoComponent extends CadastroCrudComponent {
  validacaoCustomizada: any = true;
  rota = 'aluno';
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
      curso: [null, [Validators.required]],
      turma: [null, [Validators.required]],
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
      let aluno = new Aluno(this.formulario.value.cadastro);
      aluno.adicionarEndereco(this.formulario.controls.endereco.value, this.LimparCaracterEspecial(this.formulario.controls.endereco.value.cep));
      this.Persistir(aluno);
    } else {
      this.TocarTodos(this.formulario);
    }
  }
}