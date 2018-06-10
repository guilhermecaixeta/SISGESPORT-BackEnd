import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EnderecoRoutingModule } from './endereco-routing.module';
import { BaseModule } from '../base/base.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { EnderecoComponent } from './endereco.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgxMaskModule } from 'ngx-mask';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    EnderecoRoutingModule,
    BaseModule,
    NgbModule.forRoot(),
    NgxMaskModule.forRoot(),
    ],
  exports:[EnderecoComponent],
  declarations: [EnderecoComponent]
})
export class EnderecoModule { }
