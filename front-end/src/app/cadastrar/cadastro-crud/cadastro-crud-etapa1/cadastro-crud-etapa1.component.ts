import { Component, Input, EventEmitter, Output } from '@angular/core';
import { BaseEtapaComponent } from '../../../base';
import { CustomLocalePtBR, I18n } from '../../../utils/locale.util.component';
import { NgbDatepickerI18n } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-cadastro-crud-etapa1',
  templateUrl: './cadastro-crud-etapa1.component.html',
  styleUrls: ['./cadastro-crud-etapa1.component.scss'],
  providers: [I18n, { provide: NgbDatepickerI18n, useClass: CustomLocalePtBR }]
})
export class CadastroCrudEtapa1Component extends BaseEtapaComponent {
  turmaLista: any;
  cursoLista: any;
  cargoLista: any;
  instituicaoLista: any;

  iniciar() {
    this.service.Get('instituicao/BuscarTodos').subscribe(object => this.instituicaoLista = object.data);
    if (this.rota == 'aluno') {
      this.formulario.get('instituicao').valueChanges.subscribe(id => {
        this.service.Get('curso/BuscarEquipePorIdInstituicao', id).subscribe(object => this.cursoLista = object.data);
      });

      this.formulario.get('curso').valueChanges.subscribe(id => {
        this.service.Get('turma/BuscarPorCursoId', id).subscribe(object => this.turmaLista = object.data);
      });
    } else {
      this.formulario.get('instituicao').valueChanges.subscribe(id => {
        this.service.Get('cargo/BuscarPorInstituicaoId', id).subscribe(object => this.cargoLista = object.data);
      });
    }
  }
}
