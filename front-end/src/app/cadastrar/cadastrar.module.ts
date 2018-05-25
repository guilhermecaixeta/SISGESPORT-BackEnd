import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CadastrarRoutingModule } from './cadastrar-routing.module';
import { CadastrarComponent } from './cadastrar.component';
import { MenuLoginHeaderModule } from '../menu-login-header/menu-login.module';
import { CadastrarAlunoComponent } from './cadastrar-aluno/cadastrar-aluno.component';

@NgModule({
  imports: [
    CommonModule,
    CadastrarRoutingModule,
    MenuLoginHeaderModule
  ],
  declarations: [CadastrarComponent, CadastrarAlunoComponent]
})
export class CadastrarModule { }
