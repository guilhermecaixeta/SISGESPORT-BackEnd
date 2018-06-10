import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ServidorRoutingModule } from './servidor-routing.module';
import { ServidorComponent } from './servidor.component';
import { MenuLoginHeaderModule } from '../menu-login-header/menu-login.module';
import { BaseModule } from '../base/base.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ServidorEtapa1Module } from './servidor-etapa1/servidor-etapa1.module';
import { EnderecoModule } from '../endereco/endereco.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ServidorRoutingModule,
    MenuLoginHeaderModule,
    ServidorEtapa1Module,
    EnderecoModule,
    BaseModule
    ],
  declarations: [ServidorComponent]
})
export class ServidorModule { }
