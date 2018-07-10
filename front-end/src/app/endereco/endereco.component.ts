import { Component, OnInit } from '@angular/core';
import { BaseEtapaComponent } from '../base';
import { MaskField } from '../utils/mask.util.component';

@Component({
  selector: 'app-endereco',
  templateUrl: './endereco.component.html',
  styleUrls: ['./endereco.component.scss']
})
export class EnderecoComponent extends BaseEtapaComponent {
  estadosLista: any[];
  municipioLista: any[];
  cepMask: any;
  iniciar(){
    this.service.Get('estado/BuscarTodos').subscribe(object => {
      this.estadosLista = object.data;
    });
    this.formulario.get('estado').valueChanges.subscribe(id =>{
      this.service.Get('municipio/BuscarPorIdEstado', id).subscribe(object => this.municipioLista = object.data);
    });
  }
}
