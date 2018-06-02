import { Component, OnInit } from '@angular/core';
import { BaseEtapaComponent } from '../../base';

@Component({
  selector: 'app-aluno-etapa2',
  templateUrl: './aluno-etapa2.component.html',
  styleUrls: ['./aluno-etapa2.component.scss']
})
export class AlunoEtapa2Component extends BaseEtapaComponent {
  estadosLista: any[];
  municipioLista: any[];

  init(){
    this.service.Get('estado/BuscarTodos').subscribe(data => {
      console.log(data);
      this.estadosLista = data.data;
    });
    console.log(this.estadosLista);
    this.formulario.get('estado').valueChanges.subscribe(data =>{
      this.service.Get('municipio/BuscarPorIdEstado', data).subscribe(data => this.municipioLista = data.data);
    });
  }
}
