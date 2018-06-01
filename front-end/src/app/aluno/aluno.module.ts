import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlunoRoutingModule } from './aluno-routing.module';
import { AlunoComponent } from './aluno.component';
import { MenuLoginHeaderModule } from '../menu-login-header/menu-login.module';
import { BaseModule } from '../base/base.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AlunoEtapa1Module } from './aluno-etapa1/aluno-etapa1.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AlunoRoutingModule,
    MenuLoginHeaderModule,
    AlunoEtapa1Module,
    BaseModule
    ],
  declarations: [AlunoComponent]
})
export class AlunoModule { }
