import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { BaseEtapaComponent } from '../../base';
import { CustomLocalePtBR, I18n } from '../../utils/locale.util.component';
import { NgbDatepickerI18n } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-aluno-etapa1',
  templateUrl: './aluno-etapa1.component.html',
  styleUrls: ['./aluno-etapa1.component.scss'],
  providers: [I18n, {provide: NgbDatepickerI18n, useClass: CustomLocalePtBR}]
})
export class AlunoEtapa1Component extends BaseEtapaComponent {
  senhasIguais: boolean = true;
  @Input() validacaoCustomizada = true;
  @Input() multiValidacao: any;
  @Output() multiValidacaoEmit: EventEmitter<any> = new EventEmitter<any>(true);
  
  init() {
    Object.assign(this.multiValidacao, this.multiValidacao, {emitirValidacao: () => this.validarEtapa()});
    
    this.multiValidacaoEmit.emit(this.multiValidacao);
    
    this.formulario.get('confirmarSenha').valueChanges.subscribe(data => {
      if (this.formulario.get('senha').value == null || this.formulario.get('senha').value !== data) {
        this.senhasIguais = false;
      }else{
        this.senhasIguais = true;
      }
    });
  }
 
  validarEtapa(){
      if( this.formulario.get('confirmarSenha').value == null ||  this.formulario.get('senha').value == null)
      //   this.multiValidacao.eValido = false;
      // else if(this.senhasIguais && this.formulario.valid)
      //   this.multiValidacao.eValido = true;
      // else this.multiValidacao.eValido = false;
      this.multiValidacao.eValido = true;
  }
}
