import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { BaseEtapaComponent } from '../../base';

@Component({
  selector: 'app-servidor-etapa1',
  templateUrl: './servidor-etapa1.component.html',
  styleUrls: ['./servidor-etapa1.component.scss']
})
export class ServidorEtapa1Component extends BaseEtapaComponent{
  senhasIguais: boolean = true;
  @Input() validacaoCustomizada = true;
  @Input() multiValidacao: any;
  @Output() multiValidacaoEmit: EventEmitter<any> = new EventEmitter<any>(true);
  
  turmaLista: any;
  cargoLista: any;
  instituicaoLista: any;
  hasMatricula: boolean = false;

  init() {
    Object.assign(this.multiValidacao, this.multiValidacao, {emitirValidacao: () => this.validarEtapa()});
    
    this.multiValidacaoEmit.emit(this.multiValidacao);
    
    this.service.Get('instituicao/BuscarTodos').subscribe(object => this.instituicaoLista = object.data);
    
    this.formulario.get('instituicao').valueChanges.subscribe(id => {
      this.service.Get('cargo/BuscarPorInstituicaoId', id).subscribe(object => this.cargoLista = object.data);
    });
    
    this.formulario.get('confirmarSenha').valueChanges.subscribe(data => {
      if (this.formulario.get('senha').value == null || this.formulario.get('senha').value !== data) {
        this.senhasIguais = false;
      }else{
        this.senhasIguais = true;
      }
    });

    this.formulario.get('matricula').valueChanges.subscribe(data => {
      if(String(data).length > 13)
        this.service.Get('servidor/BuscarPorMatricula', data).subscribe(
          r =>  this.hasMatricula = true,
        error => this.hasMatricula = false)
    });
  }
  
  validarEtapa(){
      if( this.formulario.get('confirmarSenha').value == null ||  this.formulario.get('senha').value == null || this.hasMatricula)
        this.multiValidacao.eValido = false;
      else if(this.senhasIguais && this.formulario.valid && !this.hasMatricula)
        this.multiValidacao.eValido = true;
      else this.multiValidacao.eValido = false;
  }
}