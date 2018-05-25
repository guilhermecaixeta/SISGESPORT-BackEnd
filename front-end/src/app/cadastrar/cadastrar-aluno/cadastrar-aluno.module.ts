import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CadastrarAlunoRoutingModule } from './cadastrar-aluno-routing.module';
import { CadastrarAlunoComponent } from './cadastrar-aluno.component';
import { MenuLoginHeaderModule } from '../../menu-login-header/menu-login.module';

@NgModule({
  imports: [
    CommonModule,
    CadastrarAlunoRoutingModule,
    MenuLoginHeaderModule
  ],
  declarations: [CadastrarAlunoComponent, CadastrarAlunoComponent]
})
export class CadastrarAlunoModule { }
