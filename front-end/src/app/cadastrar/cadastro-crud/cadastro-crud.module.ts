import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CadastroCrudRoutingModule } from './cadastro-crud-routing.module';
import { CadastroCrudComponent } from './cadastro-crud.component';
import { MenuLoginHeaderModule } from '../../menu-login-header/menu-login.module';
import { BaseModule } from '../../base/base.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CadastroCrudEtapa1Module } from './cadastro-crud-etapa1/cadastro-crud-etapa1.module';
import { EnderecoModule } from '../../endereco/endereco.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CadastroCrudRoutingModule,
    MenuLoginHeaderModule,
    CadastroCrudEtapa1Module,
    EnderecoModule,
    BaseModule
  ],
  exports: [CadastroCrudComponent],
  declarations: [CadastroCrudComponent]
})
export class CadastroCrudModule { }
