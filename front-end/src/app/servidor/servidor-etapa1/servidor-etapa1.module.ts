import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServidorEtapa1RoutingModule } from './servidor-etapa1-routing.module';
import { BaseModule } from '../../base/base.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ServidorEtapa1Component } from './servidor-etapa1.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {NgxMaskModule} from 'ngx-mask';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ServidorEtapa1RoutingModule,
    BaseModule,
    NgbModule.forRoot(),
    NgxMaskModule.forRoot(),
    ],
  exports:[ServidorEtapa1Component],
  declarations: [ServidorEtapa1Component]
})
export class ServidorEtapa1Module { }
