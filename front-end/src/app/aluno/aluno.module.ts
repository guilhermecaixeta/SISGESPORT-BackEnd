import { AlunoComponent } from './aluno.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlunoRoutingModule } from './aluno-routing.module';
import { BaseModule } from '../base/base.module';
import { EnderecoModule } from '../endereco/endereco.module';
import { CadastroCrudEtapa1Module } from '../cadastrar/cadastro-crud/cadastro-crud-etapa1/cadastro-crud-etapa1.module';
import { CadastroCrudModule } from '../cadastrar/cadastro-crud/cadastro-crud.module';
import { MenuLoginHeaderModule } from '../menu-login-header/menu-login.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AlunoRoutingModule,
    MenuLoginHeaderModule,
    CadastroCrudModule,
    CadastroCrudEtapa1Module,
    EnderecoModule,
    BaseModule
  ],
  declarations: [AlunoComponent]
})
export class AlunoModule { }
