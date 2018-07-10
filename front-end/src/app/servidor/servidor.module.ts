import { CadastroCrudEtapa1Module } from './../cadastrar/cadastro-crud/cadastro-crud-etapa1/cadastro-crud-etapa1.module';
import { CadastroCrudModule } from './../cadastrar/cadastro-crud/cadastro-crud.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ServidorRoutingModule } from './servidor-routing.module';
import { ServidorComponent } from './servidor.component';
import { MenuLoginHeaderModule } from '../menu-login-header/menu-login.module';
import { BaseModule } from '../base/base.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { EnderecoModule } from '../endereco/endereco.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ServidorRoutingModule,
    MenuLoginHeaderModule,
    CadastroCrudModule,
    CadastroCrudEtapa1Module,
    EnderecoModule,
    BaseModule
    ],
  declarations: [ServidorComponent]
})
export class ServidorModule { }
