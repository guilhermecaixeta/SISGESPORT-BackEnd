import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AlunoEtapa2RoutingModule } from './aluno-etapa2-routing.module';
import { BaseModule } from '../../base/base.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AlunoEtapa2Component } from './aluno-etapa2.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AlunoEtapa2RoutingModule,
    BaseModule,
    NgbModule.forRoot()
    ],
  exports:[AlunoEtapa2Component],
  declarations: [AlunoEtapa2Component]
})
export class AlunoEtapa2Module { }
