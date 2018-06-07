import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AlunoEtapa1RoutingModule } from './aluno-etapa1-routing.module';
import { BaseModule } from '../../base/base.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AlunoEtapa1Component } from './aluno-etapa1.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {NgxMaskModule} from 'ngx-mask';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AlunoEtapa1RoutingModule,
    BaseModule,
    NgbModule.forRoot(),
    NgxMaskModule.forRoot(),
    ],
  exports:[AlunoEtapa1Component],
  declarations: [AlunoEtapa1Component]
})
export class AlunoEtapa1Module { }
