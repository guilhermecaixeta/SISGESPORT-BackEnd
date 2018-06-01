import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { BaseEtapaComponent } from '../../base';

@Component({
  selector: 'app-aluno-etapa1',
  templateUrl: './aluno-etapa1.component.html',
  styleUrls: ['./aluno-etapa1.component.scss']
})
export class AlunoEtapa1Component extends BaseEtapaComponent {
  senhasIguais: boolean = true;
  @Input() validacaoCustomizada = true;
  @Input() multiValidacao: any;
  @Output() multiValidacaoEmit: EventEmitter<any> = new EventEmitter<any>(true);
  
  ngOnInit() {
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
        this.multiValidacao.eValido = false;
      else this.multiValidacao.eValido = this.senhasIguais;
  }
}
