import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CadastroCrudEtapa1RoutingModule } from './cadastro-crud-etapa1-routing.module';
import { BaseModule } from '../../../base/base.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CadastroCrudEtapa1Component } from './cadastro-crud-etapa1.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {NgxMaskModule} from 'ngx-mask';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CadastroCrudEtapa1RoutingModule,
    BaseModule,
    NgbModule.forRoot(),
    NgxMaskModule.forRoot(),
    ],
  exports:[CadastroCrudEtapa1Component],
  declarations: [CadastroCrudEtapa1Component]
})
export class CadastroCrudEtapa1Module { }
